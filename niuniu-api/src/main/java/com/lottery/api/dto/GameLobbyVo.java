package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameLobbyVo {
	
	@ApiModelProperty(value = "游戏大厅编号", required = true)
	private Integer sid;

	@ApiModelProperty(value = "玩家类型1：正式；2：试玩 ", required = true)
	private Integer type;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
