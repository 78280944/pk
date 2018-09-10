package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.QueryRoomDateDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomListResult extends BaseRestResult {
	
	@ApiModelProperty(value = "本房战绩列表数据", required = true)
	private List<QueryRoomDateDto> data = null;

	public void success(List<QueryRoomDateDto> data) {
		success();
		this.data = data;
	}

	public List<QueryRoomDateDto> getData() {
		return data;
	}

	public void setData(List<QueryRoomDateDto> list) {
		this.data = list;
	}
}
