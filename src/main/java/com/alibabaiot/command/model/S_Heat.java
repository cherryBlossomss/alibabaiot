package com.alibabaiot.command.model;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 17:28
 */
public class S_Heat {
    private String cid = "s_heat";
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
