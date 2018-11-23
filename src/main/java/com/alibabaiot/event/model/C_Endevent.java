package com.alibabaiot.event.model;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 16:50
 */
public class C_Endevent {
    private String cid = "c_endevent";
    private Integer sumwateryield;
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
