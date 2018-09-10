package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.LotteryRound;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class HisRoundResult extends BaseRestResult {

	@ApiModelProperty(value = "历史游戏数据", required = true)
	private List<LotteryRound> data;

	public void success(List<LotteryRound> data) {
		success();
		this.data = data;
	}

	public List<LotteryRound> getData() {
		return data;
	}

	public void setData(List<LotteryRound> data) {
		this.data = data;
	}

}
