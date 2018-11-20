package com.alibabaiot.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibabaiot.model.Mqtt;
import com.alibabaiot.service.IMqttService;
import com.aliyun.iot.util.SimpleClient4IOT;
import com.aliyun.iot.util.Tip;
import com.aliyun.iot.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

@RestController
@RequestMapping("/api/iot")
public class Mqttcontroller {

	@Autowired
	private IMqttService mqttService;

	/**
	 * 查询发布消息
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String starttime, @RequestParam(required = false) String endtime) {
		if (ToolUtil.isOneEmpty(starttime, endtime)) {
			return Tip.ERROR(300, "time为空");
		}
		List<Mqtt> maqq = mqttService.selectList(new EntityWrapper<Mqtt>().between("time",starttime,endtime));
		Tip t = Tip.SUCCESS("查询成功");
		t.put("maqq", maqq);
		return t;
	}

	/**
	 * 发送
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Object send(@RequestParam(required = false) String message) throws Exception {
		Map<String, Object> map = SimpleClient4IOT.send(message);
         System.out.println("map="+map);
		if (map.containsKey("message")) {
			Tip t = Tip.ERROR(300, "发送错误");
			t.put("message", (String) map.get("message"));
			return t;
		}
		Mqtt mqtt = new Mqtt();
		mqtt.setMessage((String) map.get("contents"));
		mqtt.setTime((Date) map.get("time"));
		if (mqttService.insert(mqtt)) {
			Tip t = Tip.SUCCESS("发送成功");
			t.put("mqtt", map);
			return t;
		} else {
			return Tip.ERROR(300, "发送失败");
		}
	}
}
