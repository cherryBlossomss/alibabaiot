package com.alibabaiot.event.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:29
 */
public class C_Failure {
    @ApiModelProperty(value = "标识id")
    private String cid="c_failure";
    @ApiModelProperty(value = "故障代码0")
    private String faultcode0;
    @ApiModelProperty(value = "故障代码01")
    private String faultcode1;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFaultcode0() {
        return faultcode0;
    }

    public void setFaultcode0(String faultcode0) {
        this.faultcode0 = faultcode0;
    }

    public String getFaultcode1() {
        return faultcode1;
    }

    public void setFaultcode1(String faultcode1) {
        this.faultcode1 = faultcode1;
    }
}
