package com.alibabaiot.firmwaremodel;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: zhuzb
 * @Date: 2018/11/20 16:56
 */
public class C_Subpackage {
    @ApiModelProperty("标识id")
    private String cid="c_subpackage";
    @ApiModelProperty("包索引")
    private Integer packageindex;
    @ApiModelProperty("分包验证码")
    private String subpackageCode;
    @ApiModelProperty("包的总数量")
    private Integer packageSum;
    @ApiModelProperty("状态0失败需要重发，1成功")
    private Integer status;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getPackageindex() {
        return packageindex;
    }

    public void setPackageindex(Integer packageindex) {
        this.packageindex = packageindex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubpackageCode() {
        return subpackageCode;
    }

    public void setSubpackageCode(String subpackageCode) {
        this.subpackageCode = subpackageCode;
    }

    public Integer getPackageSum() {
        return packageSum;
    }

    public void setPackageSum(Integer packageSum) {
        this.packageSum = packageSum;
    }
}
