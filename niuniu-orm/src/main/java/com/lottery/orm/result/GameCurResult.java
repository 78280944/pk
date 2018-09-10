package com.lottery.orm.result;

import com.lottery.orm.bo.LotteryGameRound;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameCurResult extends BaseRestResult{
	
	@ApiModelProperty(value = "当前期次结果信息", required = true)
	private LotteryGameRound data;

	public void success(LotteryGameRound data) {
		success();
		this.data = data;
	}

	public LotteryGameRound getData() {
		return data;
	}

	public void setData(LotteryGameRound data) {
		this.data = data;
	}
}
