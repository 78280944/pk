package com.lottery.orm.result;

import com.lottery.orm.bo.LotteryRound;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoundResult extends BaseRestResult {

	@ApiModelProperty(value = "该期游戏信息", required = true)
	private LotteryRound data;

	public void success(LotteryRound data) {
		success();
		this.data = data;
	}

	public LotteryRound getData() {
		return data;
	}

	public void setData(LotteryRound data) {
		this.data = data;
	}

}
