package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class WinningReportDto {
	@ApiModelProperty(value = "账户ID", required = true)
	private Integer accountId;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String userName;
	
	@ApiModelProperty(value = "代理人", required = true)
	private String supUserName;
	
	@ApiModelProperty(value = "交易次数", required = true)
	private Integer tradeCount;

	@ApiModelProperty(value = "投注金额", required = true)
	private Double orderAmount;

	@ApiModelProperty(value = "输赢金额", required = true)
	private Double actualAmount;
	
	@ApiModelProperty(value = "洗码比", required = true)
	private Double ratio;
	
	@ApiModelProperty(value = "洗码量", required = true)
	private Double returnAmount;

	@ApiModelProperty(value = "代理总金额", required = true)
	private Double commisionAmount;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Double getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(Double commisionAmount) {
		this.commisionAmount = commisionAmount;
	}
	
}
