package com.lottery.orm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class PlayerWinReportDto {
	
	@ApiModelProperty(value = "用户名", required = true)
	private String userName;
	
	@ApiModelProperty(value = "所属代理", required = true)
	private String supUserName;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private Double orderAmount;

	@ApiModelProperty(value = "派彩", required = true)
	private Double actualAmount;
	
	@ApiModelProperty(value = "返利", required = true)
	private Double returnAmount;

	@ApiModelProperty(value = "输赢", required = true)
	private Double winAmount;
	
	@ApiModelProperty(value = "公司损益", required = true)
	private Double systemAmount;

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

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Double winAmount) {
		this.winAmount = winAmount;
	}

	public Double getSystemAmount() {
		return systemAmount;
	}

	public void setSystemAmount(Double systemAmount) {
		this.systemAmount = systemAmount;
	}

}
