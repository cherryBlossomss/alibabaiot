package com.alibabaiot.business.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:40
 */
public class C_Getwater {
    @ApiModelProperty("标识id")
    private String cid="c_getwater";
    @ApiModelProperty("订单编号")
    private String  orderid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
