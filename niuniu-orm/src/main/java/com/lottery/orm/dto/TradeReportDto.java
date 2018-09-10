package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeReportDto {
	@ApiModelProperty(value = "账户ID", required = true)
	private Integer accountId;
	
	@ApiModelProperty(value = "用户", required = true)
	private String userName;
	
	@ApiModelProperty(value = "所属代理", required = true)
	private String supUserName;
	
	@ApiModelProperty(value = "游戏期次", required = true)
	private String lotteryTerm;
	
	@ApiModelProperty(value = "下注单ID", required = true)
	private Integer orderId;
	
	@ApiModelProperty(value = "投注时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date orderTime;

	@ApiModelProperty(value = "玩法", required = true)
	private String itemNameCN;

	@ApiModelProperty(value = "投注金额", required = true)
	private Double orderAmount;

	@ApiModelProperty(value = "输赢金额", required = true)
	private Double winAmount;

	@ApiModelProperty(value = "本期结束后账户余额", required = true)
	private BigDecimal accountAmount;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getLotteryTerm() {
		return lotteryTerm;
	}

	public void setLotteryTerm(String lotteryTerm) {
		this.lotteryTerm = lotteryTerm;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Double winAmount) {
		this.winAmount = winAmount;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getItemNameCN() {
		return itemNameCN;
	}

	public void setItemNameCN(String itemNameCN) {
		this.itemNameCN = itemNameCN;
	}

	
	
}
