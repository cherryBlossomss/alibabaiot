package com.alibabaiot.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibabaiot.dao.MqttMapper;
import com.alibabaiot.model.Mqtt;
import com.alibabaiot.service.IMqttService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
@Transactional
public class MqttServiceImpl extends ServiceImpl<MqttMapper,Mqtt> implements IMqttService {
	  

}
