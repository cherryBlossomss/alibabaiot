package com.alibabaiot.firmwaremodel;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 17:00
 */
public class C_Firmware {
    @ApiModelProperty("标识id")
    private String cid="c_firmware";
    @ApiModelProperty("状态0升级失败，1升级成功")
    private Integer status;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
