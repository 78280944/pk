package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.LotteryNoidDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryNoidResult extends BaseRestResult{
	
	@ApiModelProperty(value = "游戏桌台人数及庄闲", required = true)
	private List<LotteryNoidDto> data = null;

	public void success(List<LotteryNoidDto> data) {
		success();
		this.data = data;
	}

	public List<LotteryNoidDto> getData() {
		return data;
	}

	public void setData(List<LotteryNoidDto> list) {
		this.data = list;
	}
}
