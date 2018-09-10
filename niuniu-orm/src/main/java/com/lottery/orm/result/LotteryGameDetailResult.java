package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.LotteryGameDetailDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameDetailResult  extends RestResult{
	
	@ApiModelProperty(value = "输赢明细", required = true)
	private List<LotteryGameDetailDto> data = null;

	public void success(List<LotteryGameDetailDto> data) {
		success();
		this.data = data;
	}

	public List<LotteryGameDetailDto> getData() {
		return data;
	}

	public void setData(List<LotteryGameDetailDto> list) {
		this.data = list;
	}
}
