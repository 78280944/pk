package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.LotteryGameDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameResult extends RestResult{
	
	@ApiModelProperty(value = "游戏类型", required = true)
	public List<LotteryGameDto> data = null;
	
	public void success(List<LotteryGameDto> data) {
		success();
		this.data = data;
	}
	public List<LotteryGameDto> getData() {
		return data;
	}

	public void setData(List<LotteryGameDto> data) {
		this.data = data;
	}
}
