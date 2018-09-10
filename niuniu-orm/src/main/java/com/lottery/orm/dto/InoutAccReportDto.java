package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class InoutAccReportDto {
	
	@ApiModelProperty(value = "操作时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date inputTime;
	
	@ApiModelProperty(value = "账户号", required = true)
	private String userName;
	
	@ApiModelProperty(value = "所属代理", required = true)
	private String supuserName;
	
	@ApiModelProperty(value = "存/取款", required = true)
	private Double tradeAmount;

	@ApiModelProperty(value = "账户余额", required = true)
	private BigDecimal accountAmount;

	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty(value = "状态，01，成功，02，失败，03，处理中")
	private String orderState;
	
	@ApiModelProperty(value = "充值/取现.In,充值;Out,取现")
	private String relativeType;

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSupuserName() {
		return supuserName;
	}

	public void setSupuserName(String supuserName) {
		this.supuserName = supuserName;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getRelativeType() {
		return relativeType;
	}

	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	
}
