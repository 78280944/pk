package com.lottery.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class HisOrderVo extends PageParamVo{

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountid;

    @ApiModelProperty(value = "游戏ID", required = true)
    @NotNull(message = "游戏ID不能为空")
    private Integer sid;

    public Integer getAccountId() {
	return accountid;
    }

    public void setAccountId(Integer accountId) {
	this.accountid = accountId;
    }

	public Integer getsId() {
		return sid;
	}

	public void setsId(Integer sId) {
		this.sid = sId;
	}



}
