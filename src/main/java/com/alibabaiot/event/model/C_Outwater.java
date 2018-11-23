package com.alibabaiot.event.model;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:17
 */
public class C_Outwater {
    private String cid = "c_outwater";
    private Integer startaddr;
    private Integer outlet;
    private Integer wateryield;
    private String orderid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getStartaddr() {
        return startaddr;
    }

    public void setStartaddr(Integer startaddr) {
        this.startaddr = startaddr;
    }

    public Integer getOutlet() {
        return outlet;
    }

    public void setOutlet(Integer outlet) {
        this.outlet = outlet;
    }

    public Integer getWateryield() {
        return wateryield;
    }

    public void setWateryield(Integer wateryield) {
        this.wateryield = wateryield;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
