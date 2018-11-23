package com.alibabaiot.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibabaiot.event.model.C_Endevent;
import com.alibabaiot.event.model.C_Failure;
import com.alibabaiot.event.model.C_Outwater;
import com.alibabaiot.event.model.C_Statechange;
import com.alibabaiot.service.impl.SimpleClient4IOT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibabaiot.model.Mqtt;
import com.alibabaiot.service.IMqttService;
import com.aliyun.iot.util.Tip;
import com.aliyun.iot.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(value = "上报", description = "上报")
@RestController
@RequestMapping("/api/iot")
@Component
@EnableSwagger2
public class Mqttcontroller implements CommandLineRunner {
    @Autowired
    private IMqttService mqttService;

    /**
     * 查询发布消息
     */
    @ApiOperation(value = "查询发布消息", notes = ""
            , httpMethod = "GET")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(required = false) String starttime, @RequestParam(required = false) String endtime) {
        if (ToolUtil.isOneEmpty(starttime, endtime)) {
            return Tip.ERROR(300, "time为空");
        }
        List<Mqtt> maqq = mqttService.selectList(new EntityWrapper<Mqtt>().between("time", starttime, endtime));
        Tip t = Tip.SUCCESS("查询成功");
        t.put("maqq", maqq);
        return t;
    }

    /**
     * 制水状态变更上报
     *
     * @throws Exception
     */
    @ApiOperation(value = "制水状态变更上报", notes = ""
            , httpMethod = "POST")
    @RequestMapping(value = "/statechange", method = RequestMethod.POST)
    public Map<String, Object> statechange(@ApiParam(value = "制水状态变更属性") C_Statechange cStatechange) throws Exception {
        String statechange = new ObjectMapper().writeValueAsString(cStatechange);
        System.err.println(statechange);
        Map<String, Object> map = SimpleClient4IOT.controllerSend(statechange);
        System.out.println("map=" + map);
        if (map.containsKey("message")) {
            Tip t = Tip.ERROR(300, "发送错误");
            t.put("message", (String) map.get("message"));
            return t;
        }
        Mqtt mqtt = new Mqtt();
        mqtt.setMessage((String) map.get("contents"));
        mqtt.setTime((Date) map.get("time"));
        mqtt.setType("上行");
        if (mqttService.insert(mqtt)) {
            Tip t = Tip.SUCCESS("发送成功");
            t.put("mqtt", map);
            return t;
        } else {
            return Tip.ERROR(300, "发送失败");
        }
    }

    /**
     * 故障事件上报
     *
     * @throws Exception
     */
    @ApiOperation(value = "故障事件上报", notes = ""
            , httpMethod = "POST")
    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public Map<String, Object> failure(@ApiParam(value = "故障事件属性") C_Failure cFailure) throws Exception {
        String failure = new ObjectMapper().writeValueAsString(cFailure);
        System.err.println(failure);
        Map<String, Object> map = SimpleClient4IOT.controllerSend(failure);
        System.out.println("map=" + map);
        if (map.containsKey("message")) {
            Tip t = Tip.ERROR(300, "发送错误");
            t.put("message", (String) map.get("message"));
            return t;
        }
        Mqtt mqtt = new Mqtt();
        mqtt.setMessage((String) map.get("contents"));
        mqtt.setTime((Date) map.get("time"));
        mqtt.setType("上行");
        if (mqttService.insert(mqtt)) {
            Tip t = Tip.SUCCESS("发送成功");
            t.put("mqtt", map);
            return t;
        } else {
            return Tip.ERROR(300, "发送失败");
        }
    }

    /**
     * 出水状态变更上报
     *
     * @throws Exception
     */
    @ApiOperation(value = "出水状态变更上报", notes = ""
            , httpMethod = "POST")
    @RequestMapping(value = "/outwater", method = RequestMethod.POST)
    public Map<String, Object> c_outwater(@ApiParam(value = "出水状态变更上报") C_Outwater cOutwater) throws Exception {
        String outWater = new ObjectMapper().writeValueAsString(cOutwater);
        System.err.println(outWater);
        Map<String, Object> map = SimpleClient4IOT.controllerSend(outWater);
        System.out.println("map=" + map);
        if (map.containsKey("message")) {
            Tip t = Tip.ERROR(300, "发送错误");
            t.put("message", (String) map.get("message"));
            return t;
        }
        Mqtt mqtt = new Mqtt();
        mqtt.setMessage((String) map.get("contents"));
        mqtt.setTime((Date) map.get("time"));
        mqtt.setType("上行");
        if (mqttService.insert(mqtt)) {
            Tip t = Tip.SUCCESS("发送成功");
            t.put("mqtt", map);
            return t;
        } else {
            return Tip.ERROR(300, "发送失败");
        }
    }

    /**
     * 结束事件上报
     *
     * @throws Exception
     */
    @ApiOperation(value = "结束事件上报", notes = ""
            , httpMethod = "POST")
    @RequestMapping(value = "/endevent", method = RequestMethod.POST)
    public Map<String, Object> statechange(@ApiParam(value = "制水状态变更属性") C_Endevent cEndevent) throws Exception {
        String endEvent = new ObjectMapper().writeValueAsString(cEndevent);
        System.err.println(endEvent);
        Map<String, Object> map = SimpleClient4IOT.controllerSend(endEvent);
        System.out.println("map=" + map);
        if (map.containsKey("message")) {
            Tip t = Tip.ERROR(300, "发送错误");
            t.put("message", (String) map.get("message"));
            return t;
        }
        Mqtt mqtt = new Mqtt();
        mqtt.setMessage((String) map.get("contents"));
        mqtt.setTime((Date) map.get("time"));
        mqtt.setType("上行");
        if (mqttService.insert(mqtt)) {
            Tip t = Tip.SUCCESS("发送成功");
            t.put("mqtt", map);
            return t;
        } else {
            return Tip.ERROR(300, "发送失败");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        SimpleClient4IOT.SimpleClient();
    }

}
