package com.alibabaiot.firmwaremodel;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 16:59
 */
public class S_Firmware {
    private String cid="s_firmware";
    private Integer checkcode;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(Integer checkcode) {
        this.checkcode = checkcode;
    }
}
