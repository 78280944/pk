package com.lottery.api.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lottery.api.dto.PageParamVo;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ProWinReVo extends PageParamVo {
	@ApiModelProperty(value = "账户ID")
	@Min(value = 0, message = "账户ID格式不正确")
	private Integer accountId;

	@ApiModelProperty(value = "开始时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;

	@ApiModelProperty(value = "结束时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;

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
    
	
	
}
