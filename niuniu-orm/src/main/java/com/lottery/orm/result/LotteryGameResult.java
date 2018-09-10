package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.LotteryResultDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameResult extends BaseRestResult {

	@ApiModelProperty(value = "游戏主界面数据", required = true)
	private List<LotteryResultDto> data;

	public void success(List<LotteryResultDto> data) {
		success();
		this.data = data;
	}

	public List<LotteryResultDto> getData() {
		return data;
	}

	public void setData(List<LotteryResultDto> data) {
		this.data = data;
	}
}
