package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.RoomOrderDetailDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomOrderDetaiResult extends RestResult{
	@ApiModelProperty(value = "投注结算明细", required = true)
	private List<RoomOrderDetailDto> data = null;

	public void success(List<RoomOrderDetailDto> data) {
		success();
		this.data = data;
	}

	public List<RoomOrderDetailDto> getData() {
		return data;
	}

	public void setData(List<RoomOrderDetailDto> list) {
		this.data = list;
	}
}
