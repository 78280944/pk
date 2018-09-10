package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.LotteryGame;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameItemResult  extends RestResult{
	
	@ApiModelProperty(value = "游戏类型大厅", required = true)
	public List<LotteryGame> data = null;
	
	public void success(List<LotteryGame> data) {
		success();
		this.data = data;
	}
	public List<LotteryGame> getData() {
		return data;
	}

	public void setData(List<LotteryGame> data) {
		this.data = data;
	}

}
