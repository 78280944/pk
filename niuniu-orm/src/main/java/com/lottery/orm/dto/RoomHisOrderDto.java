package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomHisOrderDto {
	@ApiModelProperty(value = "订单ID", required = true)
	private Integer lgmid;

	@ApiModelProperty(value = "玩法", required = true)
	private String gamename;
	
	@ApiModelProperty(value = "游戏厅", required = true)
	private String gamelobbyname;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "房号", required = true)
	private String roomid;
	
	@ApiModelProperty(value = "台号", required = true)
	private String noid;
	
	@ApiModelProperty(value = "庄闲", required = true)
	private String playoridle;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private BigDecimal orderamount;
	
	@ApiModelProperty(value = "战绩", required = true)
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

	public String getGamelobbyname() {
		return gamelobbyname;
	}

	public void setGamelobbyname(String gamelobbyname) {
		this.gamelobbyname = gamelobbyname;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
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
