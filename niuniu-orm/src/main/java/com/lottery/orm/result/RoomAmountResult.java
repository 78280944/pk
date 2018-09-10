package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.AccountInfoDto;
import com.lottery.orm.dto.RoomAmountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomAmountResult  extends BaseRestResult {

	@ApiModelProperty(value = "收益信息", required = true)
	private List<RoomAmountDto> data;
	
	public void success(List<RoomAmountDto> data) {
		success();
		this.data = data;
	}

	public List<RoomAmountDto> getData() {
		return data;
	}

	public void setData(List<RoomAmountDto> data) {
		this.data = data;
	}
}
