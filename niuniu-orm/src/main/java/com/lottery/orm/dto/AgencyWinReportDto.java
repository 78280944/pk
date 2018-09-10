package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AgencyWinReportDto {
	@ApiModelProperty(value = "代理人", required = true)
	private String userName;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private Double orderAmount;

	@ApiModelProperty(value = "派彩", required = true)
	private Double actualAmount;
	
	@ApiModelProperty(value = "洗码费", required = true)
	private Double feeAmount;
	
	@ApiModelProperty(value = "代理占比", required = true)
	private Double percentage;

	@ApiModelProperty(value = "输赢", required = true)
	private Double winAmount;
	
	@ApiModelProperty(value = "公司损益", required = true)
	private Double systemAmount;
	
	@ApiModelProperty(value = "公司输赢", required = true)
	private Double companyWin;

	@ApiModelProperty(value = "结算", required = true)
	private Double balanceAmount;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Double winAmount) {
		this.winAmount = winAmount;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Double getSystemAmount() {
		return systemAmount;
	}

	public void setSystemAmount(Double systemAmount) {
		this.systemAmount = systemAmount;
	}

	public Double getCompanyWin() {
		return companyWin;
	}

	public void setCompanyWin(Double companyWin) {
		this.companyWin = companyWin;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

}
