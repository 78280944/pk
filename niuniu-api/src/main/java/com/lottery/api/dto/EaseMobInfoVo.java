package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class EaseMobInfoVo {

    @ApiModelProperty(value = "环信client id", required = true)
    @NotNull(message = "client id 不能为空")
    private String clientId;

    @ApiModelProperty(value = "环信client secret", required = true)
    @NotNull(message = "client secret 不能为空")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
