package com.alibabaiot.command.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 17:29
 */
public class C_Heat {
    @ApiModelProperty(value = "标识id")
    private String cid="c_heat";
    @ApiModelProperty(value = "加热状态")
    private Integer heat;
    @ApiModelProperty(value = "订单编号")
    private String orderid;
    @ApiModelProperty(value = "出水状态")
    private Integer outlet;
    @ApiModelProperty(value = "tds值")
    private Integer tds;
    @ApiModelProperty(value = "热水温度")
    private Integer temperature;
    @ApiModelProperty(value = "是否启用")
    private Integer using;
    @ApiModelProperty(value = "是否制水")
    private Integer water;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
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

    public Integer getTds() {
        return tds;
    }

    public void setTds(Integer tds) {
        this.tds = tds;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getUsing() {
        return using;
    }

    public void setUsing(Integer using) {
        this.using = using;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }
}
