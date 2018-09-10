package com.lottery.orm.result;

import java.util.ArrayList;
import java.util.List;

import com.lottery.orm.dto.RoomOrderDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameOrderList extends RestResult{
	@ApiModelProperty(value = "投注明细", required = true)
	public List<List<RoomOrderDto>> data = null;
	
	public void success(List<List<RoomOrderDto>> data) {
		success();
		this.data = data;
	}
	public List<List<RoomOrderDto>> getData() {
		return data;
	}

	public void setData(List<List<RoomOrderDto>> data) {
		this.data = data;
	}
	
}
