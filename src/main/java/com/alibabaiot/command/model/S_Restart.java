package com.alibabaiot.command.model;

/**
 * @Description:
 * @Author: wengjl
 * @Date: 2018/11/20/020 17:39
 */
public class S_Restart {
    private String cid="s_restart";
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
