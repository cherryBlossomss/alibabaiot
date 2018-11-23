package com.alibabaiot.command.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 17:40
 */
public class C_Restart {
    private String cid="c_restart";
    @ApiModelProperty("重新获取注册信息（recapture）")
    private Integer recapture;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getRecapture() {
        return recapture;
    }

    public void setRecapture(Integer recapture) {
        this.recapture = recapture;
    }
}
