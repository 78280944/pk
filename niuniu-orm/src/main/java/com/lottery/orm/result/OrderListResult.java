package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.ResultDataDto;
import com.lottery.orm.dto.RoomHisOrderDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OrderListResult extends RestResult{
	
	@ApiModelProperty(value = "历史注单", required = true)
	private List<RoomHisOrderDto> data = null;

	public void success(List<RoomHisOrderDto> data) {
		success();
		this.data = data;
	}

	public List<RoomHisOrderDto> getData() {
		return data;
	}

	public void setData(List<RoomHisOrderDto> list) {
		this.data = list;
	}
}
