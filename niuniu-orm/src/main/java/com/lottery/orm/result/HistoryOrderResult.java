package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.HistoryOrderDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class HistoryOrderResult extends BaseRestResult {

	@ApiModelProperty(value = "注单数据", required = true)
	private List<HistoryOrderDto> data;

	public void success(List<HistoryOrderDto> data) {
		success();
		this.data = data;
	}

	public List<HistoryOrderDto> getData() {
		return data;
	}

	public void setData(List<HistoryOrderDto> data) {
		this.data = data;
	}

}
