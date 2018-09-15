package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomHisOrderDto {
	@ApiModelProperty(value = "订单ID", required = true)
	private Integer lgmid;

	@ApiModelProperty(value = "游戏名称", required = true)
	private String gamename;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private BigDecimal orderamount;
	
	@ApiModelProperty(value = "订单号", required = true)
	private String result;
	
	@ApiModelProperty(value = "输赢", required = true)
	private String lastamount;

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public BigDecimal getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(BigDecimal orderamount) {
		this.orderamount = orderamount;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLastamount() {
		return lastamount;
	}

	public void setLastamount(String lastamount) {
		this.lastamount = lastamount;
	}
	
	
}
