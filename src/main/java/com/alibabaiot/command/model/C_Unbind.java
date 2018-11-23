package com.alibabaiot.command.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 17:38
 */
public class C_Unbind {
    @ApiModelProperty(value = "标识id")
    private String cid="c_unbind";
    @ApiModelProperty("解绑状态0未解绑1已经解绑")
    private  Integer bindstatus;
    @ApiModelProperty("未执行原因0 无 ,1 正在交易，2正在更新固件，3.其他")
    private  Integer reason;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getBindstatus() {
        return bindstatus;
    }

    public void setBindstatus(Integer bindstatus) {
        this.bindstatus = bindstatus;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }
}
