package com.alibabaiot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.alibabaiot.model.Mqtt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
@Mapper
@Repository
public interface MqttMapper extends BaseMapper<Mqtt> {
	  
}
