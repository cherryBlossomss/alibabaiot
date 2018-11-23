package com.alibabaiot.business.model;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:35
 */
public class S_Pay {
    private String cid = "s_pay";
    private String orderid;

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
