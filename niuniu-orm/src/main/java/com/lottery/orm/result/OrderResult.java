package com.lottery.orm.result;

import com.lottery.orm.dto.LotteryOrderDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OrderResult extends BaseRestResult {

	@ApiModelProperty(value = "投注信息", required = true)
	private LotteryOrderDto data;

	public void success(LotteryOrderDto data) {
		success();
		this.data = data;
	}

	public LotteryOrderDto getData() {
		return data;
	}

	public void setData(LotteryOrderDto data) {
		this.data = data;
	}

}
