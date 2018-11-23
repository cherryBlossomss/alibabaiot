package com.alibabaiot.model.test.model;
/**
 * @author linyibeng 
 * @date 2018-11-12 16:23
 */
public class CB_Heat {
	private String cid = "c_heat";
	private Integer water = 0;
	private Integer heat = 1;
	private Integer using = 1;
	private String orderid = "BT201810070100004";
	private Integer outlet = 0;
	private Integer temperature = 75;
	private Integer tds = 100;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Integer getWater() {
		return water;
	}
	public void setWater(Integer water) {
		this.water = water;
	}
	public Integer getHeat() {
		return heat;
	}
	public void setHeat(Integer heat) {
		this.heat = heat;
	}
	public Integer getUsing() {
		return using;
	}
	public void setUsing(Integer using) {
		this.using = using;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getOutlet() {
		return outlet;
	}
	public void setOutlet(Integer outlet) {
		this.outlet = outlet;
	}
	public Integer getTemperature() {
		return temperature;
	}
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	public Integer getTds() {
		return tds;
	}
	public void setTds(Integer tds) {
		this.tds = tds;
	}
}
