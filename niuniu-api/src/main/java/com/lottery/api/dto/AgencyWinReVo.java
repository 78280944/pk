package com.lottery.api.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AgencyWinReVo  extends PageParamVo {

	@ApiModelProperty(value = "账户ID")
	@Min(value = 0, message = "账户ID格式不正确")
	private Integer accountId;

	@ApiModelProperty(value = "开始时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;

	@ApiModelProperty(value = "结束时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;
	
	@ApiModelProperty(value = "管理账户级别,0：超级管理员,1: 一级代理,2：二级代理,3：三级代理", required = true)
    private String level;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
}
