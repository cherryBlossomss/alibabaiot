package com.alibabaiot.business.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:39
 */
public class C_Endgetwater {
    @ApiModelProperty("标识id")
    private String cid = "c_endgetwater";
    @ApiModelProperty("总出水量")
    private Integer sumwateryield;
    @ApiModelProperty("订单编号")
    private String orderid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getSumwateryield() {
        return sumwateryield;
    }

    public void setSumwateryield(Integer sumwateryield) {
        this.sumwateryield = sumwateryield;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
