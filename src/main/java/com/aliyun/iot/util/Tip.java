/**
 * 
 */
package com.aliyun.iot.util;

import java.util.HashMap;

/**
 * @author linyb
 *
 */
public class Tip extends HashMap<String, Object> {
	public static Tip SUCCESS(String msg) {
		Tip tip = new Tip();
		tip.put("code", 200);
		tip.put("message", msg);
		return tip;
	}
	public static Tip ERROR(int code,String msg) {
		Tip tip = new Tip();
		tip.put("code", code);
		tip.put("message", msg);
		return tip;
	}
}
