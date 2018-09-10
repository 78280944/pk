package com.lottery.api.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeReportVo extends PageParamVo {

	@ApiModelProperty(value = "代理帐户ID")
	@Min(value = 0, message = "代理账户ID格式不正确")
	private Integer accountId;
	
	@ApiModelProperty(value = "会员名")
	@NotBlank(message = "会员名不能为空")
	private String playerUserName;

	@ApiModelProperty(value = "开始时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;

	@ApiModelProperty(value = "结束时间,格式:yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;
	
	@ApiModelProperty(value = "是否查询本期")
	private Boolean isCurPeriod;

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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Boolean getIsCurPeriod() {
		return isCurPeriod;
	}

	public void setIsCurPeriod(Boolean isCurPeriod) {
		this.isCurPeriod = isCurPeriod;
	}

	public String getPlayerUserName() {
		return playerUserName;
	}

	public void setPlayerUserName(String playerUserName) {
		this.playerUserName = playerUserName;
	}
	
	
}
