/**
 * aliyun.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alibabaiot.service.impl;

import com.alibabaiot.model.Mqtt;
import com.alibabaiot.service.IMqttService;
import com.aliyun.iot.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.File;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;


/**
 * IoT套件JAVA版设备接入demo
 */
@Service
public class SimpleClient4IOT {
    @Autowired
    private IMqttService mqttService;
    private static SimpleClient4IOT simpleClient4IOT;

    @PostConstruct
    public void init() {
        System.err.println("<<<<<<<<<<<<<<<初始化已经执行>>>>>>>>>>>>>>>>>");
        simpleClient4IOT = this;
        simpleClient4IOT.mqttService = this.mqttService;
    }

    /******这里是客户端需要的参数*******/
    public static String deviceName = "innertest";
    public static String productKey = "a1l1qtLZEFC";
    public static String secret = "YFO2tDoMdSwDEnwkTvHB5wyUnISToTOn";

    //用于测试的topic
    private static String subTopic = "/" + productKey + "/" + deviceName + "/get";     //订阅
    private static String pubTopic = "/" + productKey + "/" + deviceName + "/update"; //发布
    private static MqttClient sampleClient;
    private final static Logger logger = LoggerFactory.getLogger(SimpleClient4IOT.class);
    private static StringBuffer stringBuffer = new StringBuffer();
    private static int i = 0;

    public static void SimpleClient() throws Exception {
        //客户端设备自己的一个标记，建议是MAC或SN，不能为空，32字符内
        String clientId = InetAddress.getLocalHost().getHostAddress();
        //设备认证
        Map<String, String> params = new HashMap<String, String>();
        params.put("productKey", productKey); //这个是对应用户在控制台注册的 设备productkey
        params.put("deviceName", deviceName); //这个是对应用户在控制台注册的 设备name
        params.put("clientId", clientId);
        String t = System.currentTimeMillis() + "";
        params.put("timestamp", t);

        //MQTT服务器地址，TLS连接使用ssl开头
        String targetServer = "ssl://" + productKey + ".iot-as-mqtt.cn-shanghai.aliyuncs.com:1883";

        //客户端ID格式，两个||之间的内容为设备端自定义的标记，字符范围[0-9][a-z][A-Z]
        String mqttclientId = clientId + "|securemode=2,signmethod=hmacsha1,timestamp=" + t + "|";
        String mqttUsername = deviceName + "&" + productKey; //mqtt用户名格式
        String mqttPassword = SignUtil.sign(params, secret, "hmacsha1"); //签名
        connectMqtt(targetServer, mqttclientId, mqttUsername, mqttPassword, deviceName);
    }

    /**
     * 发送
     *
     * @param mess
     * @return
     */
    public static Map<String, Object> controllerSend(String mess) throws Exception {
        if (sampleClient == null) {
            SimpleClient();
        }
        String content = mess;
        MqttMessage message = new MqttMessage(content.getBytes("utf-8"));
        message.setQos(0);
        //System.out.println(System.currentTimeMillis() + "消息发布:---");
        sampleClient.publish(pubTopic, message);
        Map<String, Object> map = new HashMap<>();
        map.put("contents", content);
        map.put("time", new Date());
        return map;
    }

    public static void connectMqtt(String url, String clientId, String mqttUsername,
                                   String mqttPassword, final String deviceName) throws Exception {
        MemoryPersistence persistence = new MemoryPersistence();
        SSLSocketFactory socketFactory = createSSLSocket();
        sampleClient = new MqttClient(url, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setMqttVersion(4); // MQTT 3.1.1
        connOpts.setSocketFactory(socketFactory);

        //设置是否自动重连
        connOpts.setAutomaticReconnect(true);

        //如果是true，那么清理所有离线消息，即QoS1或者2的所有未接收内容
        connOpts.setCleanSession(false);

        connOpts.setUserName(mqttUsername);
        connOpts.setPassword(mqttPassword.toCharArray());
        connOpts.setKeepAliveInterval(65);

        LogUtil.print(clientId + "进行连接, 目的地: " + url);
        sampleClient.connect(connOpts);
        //回复RRPC响应(建立线程池)
        final ExecutorService executorService = new ThreadPoolExecutor(20,
                40, 600, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100), new CallerRunsPolicy());
        //一次订阅永久生效
        //这个是第一种订阅topic方式，回调到统一的callback
        sampleClient.subscribe(subTopic);
        //        这个是第二种订阅方式, 订阅某个topic，有独立的callback
        sampleClient.subscribe(subTopic, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                LogUtil.print("收到消息：" + message + ",topic=" + topic);
                String receive = new String(message.getPayload(), "UTF-8");
                Map<String, Object> map = new ObjectMapper().readValue(receive, Map.class);
                if (("s_subpackage".equals(map.get("cid").toString())) && (++i == (int) map.get("packageindex")) && (i <= (int) map.get("packageSum"))) {
                    stringBuffer.append(map.get("packagedata"));
                } else if (!"s_subpackage".equals(map.get("cid").toString())) {
                    Mqtt mqtt = new Mqtt();
                    mqtt.setMessage(receive);
                    mqtt.setTime((new Date()));
                    mqtt.setType("下行");
                    simpleClient4IOT.mqttService.insert(mqtt);
                }
                String messageId = topic.substring(topic.lastIndexOf('/') + 1);
                final String respTopic = "/sys/" + productKey + "/" + deviceName + "/rrpc/response/" + messageId;
                String content = null;
                if (map.get("packageindex") != null && map.get("packageindex").toString().length() >= 0) {
                    content = ToolUtil.switchchange(map.get("cid").toString(), (Integer) map.get("packageindex"));
                } else {
                    content = ToolUtil.switchchange(map.get("cid").toString(), 1);
                }
                final MqttMessage response = new MqttMessage(content.getBytes());
                response.setQos(0); //RRPC只支持QoS0
                //不能在回调线程中调用publish，会阻塞线程，所以使用线程池
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // TODO 固件校验码下发
                            if ("s_firmware".equals(map.get("cid").toString())) {
                                // 取到所有包拼装的数据并转化为byte数组
                                byte[] bytes = Base64Encrypt.decode(stringBuffer.toString());
                                // 生成文档
                                CheckCodeUtils.getFile(bytes, "D:\\", "data.jpg");
                                File dir = new File("D:\\data.jpg");
                                // 由此文档取得校验码
                                int checkCode = CheckCodeUtils.getCheckCode(dir);
                                //  比对校验码，若比对成功则返回状态为1，若失败则返回状态为0
                                if (checkCode == (int) map.get("checkcode")) {
                                    sampleClient.publish(pubTopic, response);
                                    stringBuffer.setLength(0);
                                    i=0;
                                } else {
                                    String content = "{\"cid\":\"c_firmware\",\"status\":0}";
                                    final MqttMessage responses = new MqttMessage(content.getBytes());
                                    sampleClient.publish(pubTopic, responses);
                                }
                            } else {
                                sampleClient.publish(pubTopic, response);
                            }
                            LogUtil.print("回复响应成功，topic=" + pubTopic);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


        LogUtil.print("连接成功:---");
        String reqTopic = "/sys/" + productKey + "/" + deviceName + "/rrpc/request/+";
        sampleClient.subscribe(reqTopic, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                LogUtil.print("收到请求：" + message + ", topic=" + topic);
                String messageId = topic.substring(topic.lastIndexOf('/') + 1);
                final String respTopic = "/sys/" + productKey + "/" + deviceName + "/rrpc/response/" + messageId;
                String content = "{\"cid\":\"c_subpackage\",\"packageindex\":1,\"status\":1}";
                final MqttMessage response = new MqttMessage(content.getBytes());
                response.setQos(0); //RRPC只支持QoS0
                //不能在回调线程中调用publish，会阻塞线程，所以使用线程池
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sampleClient.publish(respTopic, response);
                            LogUtil.print("回复响应成功，topic=" + respTopic);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    private static SSLSocketFactory createSSLSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLSV1.2");
        context.init(null, new TrustManager[]{new ALiyunIotX509TrustManager()}, null);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        return socketFactory;
    }


}
