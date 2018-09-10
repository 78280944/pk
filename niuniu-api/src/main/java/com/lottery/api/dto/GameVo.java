package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameVo {
	
	@ApiModelProperty(value = "游戏类型，01：牛牛；02：板九", required = true)
	private String gametype;

	public String getGametype() {
		return gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype;
	}
	

}
