package com.alibabaiot.business.model;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 16:55
 */
public class S_Getwater {
    private String cid="s_getwater";
    private Integer nopay;
    private String  orderid;
    private Integer volume;
    private String username;
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public Integer getNopay() {
        return nopay;
    }
    public void setNopay(Integer nopay) {
        this.nopay = nopay;
    }
    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
