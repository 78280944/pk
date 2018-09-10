package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.TradeReportDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeReportResult extends BaseRestResult {

	@ApiModelProperty(value = "交易报表数据", required = true)
	private List<TradeReportDto> data;

	public void success(List<TradeReportDto> data) {
		success();
		this.data = data;
	}

	public List<TradeReportDto> getData() {
		return data;
	}

	public void setData(List<TradeReportDto> data) {
		this.data = data;
	}

}
