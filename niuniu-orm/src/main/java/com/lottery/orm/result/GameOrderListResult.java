package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.QueryRoomDateDto;
import com.lottery.orm.dto.RoomOrderDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameOrderListResult extends RestResult{
	
	@ApiModelProperty(value = "本期注单", required = true)
	private List<RoomOrderDto> data = null;

	public void success(List<RoomOrderDto> data) {
		success();
		this.data = data;
	}

	public List<RoomOrderDto> getData() {
		return data;
	}

	public void setData(List<RoomOrderDto> list) {
		this.data = list;
	}
}
