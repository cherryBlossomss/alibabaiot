package com.alibabaiot.model.test.model;

import com.alibaba.fastjson.JSON;

/**
 * @author linyibeng 
 * @date 2018-11-12 16:21
 */
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(JSON.toJSONString(new S_Heat()));
		System.out.println(JSON.toJSONString(new CB_Heat()));
	}

}
