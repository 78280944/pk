package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryNoidDto {
	
	@ApiModelProperty(value = "游戏号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "房间号", required = true)
	private Integer rmid;
	
	@ApiModelProperty(value = "桌号", required = true)
	private Integer noid;
	
	@ApiModelProperty(value = "人数", required = true)
	private Integer numbers;
	
	@ApiModelProperty(value = "庄闲", required = true)
	private String playoridle;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}
	

}
