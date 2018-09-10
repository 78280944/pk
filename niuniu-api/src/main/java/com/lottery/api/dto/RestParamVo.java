package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestParamVo {

    @ApiModelProperty(value = "接口调用口令", required = true)
    protected String apiToken;
    @ApiModelProperty(value = "调用接口客户端", required = true)
    private String remoteIP;
    @ApiModelProperty(value = "用户名", required = true)
    protected Integer accountID;

    public String getApiToken() {
	return apiToken;
    }

    public void setApiToken(String apiToken) {
	this.apiToken = apiToken;
    }

    public String getRemoteIP() {
	return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
	this.remoteIP = remoteIP;
    }

    public Integer getAccountID() {
	return accountID;
    }

    public void setAccountID(Integer accountID) {
	this.accountID = accountID;
    }

}
