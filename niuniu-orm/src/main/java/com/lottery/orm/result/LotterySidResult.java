package com.lottery.orm.result;

import com.lottery.orm.dto.LotteryGameCurDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotterySidResult extends BaseRestResult{
	
	@ApiModelProperty(value = "当前游戏接口结果信息", required = true)
	private LotteryGameCurDto data;

	public void success(LotteryGameCurDto data) {
		success();
		this.data = data;
	}

	public LotteryGameCurDto getData() {
		return data;
	}

	public void setData(LotteryGameCurDto data) {
		this.data = data;
	}
}
