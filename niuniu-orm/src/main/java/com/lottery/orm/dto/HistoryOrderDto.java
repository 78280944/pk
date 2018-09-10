package com.lottery.orm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class HistoryOrderDto {
	@ApiModelProperty(value = "订单ID", required = true)
	private Integer orderId;
	
	@ApiModelProperty(value = "投注时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date orderTime;

	@ApiModelProperty(value = "游戏局号", required = true)
	private String lotteryTerm;
	
	@ApiModelProperty(value = "玩法", required = true)
	private String itemNameCN;

	@ApiModelProperty(value = "投注金额", required = true)
	private Double detailAmount;

	@ApiModelProperty(value = "派彩", required = true)
	private Double actualAmount;
	
	@ApiModelProperty(value = "返利", required = true)
	private Double returnAmount;
	
	@ApiModelProperty(value = "输赢", required = true)
	private Double winAmount;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getLotteryTerm() {
		return lotteryTerm;
	}

	public void setLotteryTerm(String lotteryTerm) {
		this.lotteryTerm = lotteryTerm;
	}

	public String getItemNameCN() {
		return itemNameCN;
	}

	public void setItemNameCN(String itemNameCN) {
		this.itemNameCN = itemNameCN;
	}

	
	public Double getDetailAmount() {
		return detailAmount;
	}

	public void setDetailAmount(Double detailAmount) {
		this.detailAmount = detailAmount;
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

}
