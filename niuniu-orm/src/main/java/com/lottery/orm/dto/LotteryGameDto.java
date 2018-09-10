package com.lottery.orm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameDto {
	
	@ApiModelProperty(value = "游戏类型", required = true)
	private String gametype;

	@ApiModelProperty(value = "游戏名称", required = true)
    private String gamename;

	@ApiModelProperty(value = "游戏期次", required = true)
    private String gameterm;
	
	@ApiModelProperty(value = "游戏开始时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gamestarttime;
    
	@ApiModelProperty(value = "游戏结束时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameovertime;

	public String getGametype() {
		return gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getGameterm() {
		return gameterm;
	}

	public void setGameterm(String gameterm) {
		this.gameterm = gameterm;
	}

	public Date getGamestarttime() {
		return gamestarttime;
	}

	public void setGamestarttime(Date gamestarttime) {
		this.gamestarttime = gamestarttime;
	}

	public Date getGameovertime() {
		return gameovertime;
	}

	public void setGameovertime(Date gameovertime) {
		this.gameovertime = gameovertime;
	}
	

}
