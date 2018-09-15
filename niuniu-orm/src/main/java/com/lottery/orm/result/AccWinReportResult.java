package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.AccAmountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccWinReportResult extends BaseRestResult {

	@ApiModelProperty(value = "佣金数据", required = true)
	private List<AccAmountDto> data;

	public void success(List<AccAmountDto> data) {
		success();
		this.data = data;
	}

	public List<AccAmountDto> getData() {
		return data;
	}

	public void setData(List<AccAmountDto> data) {
		this.data = data;
	}
}
