package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomOrderDto {
	@ApiModelProperty(value = "订单ID", required = true)
	private Integer lgmid;
	
	@ApiModelProperty(value = "下注时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date ordertime;

	@ApiModelProperty(value = "玩法", required = true)
	private String gamename;
	
	@ApiModelProperty(value = "游戏厅", required = true)
	private String gamelobbyname;
	
	@ApiModelProperty(value = "房号", required = true)
	private String roomid;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "台号", required = true)
	private String noid;
	
	@ApiModelProperty(value = "庄闲；1：庄；2：闲", required = true)
	private String playoridle;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private BigDecimal orderamount;

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
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

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
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

	
}
