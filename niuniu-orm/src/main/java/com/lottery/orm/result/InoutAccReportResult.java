package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.InoutAccReportDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class InoutAccReportResult extends BaseRestResult {
	@ApiModelProperty(value = "点数出入报表数据", required = true)
	private List<InoutAccReportDto> data;

	public void success(List<InoutAccReportDto> data) {
		success();
		this.data = data;
	}

	public List<InoutAccReportDto> getData() {
		return data;
	}

	public void setData(List<InoutAccReportDto> data) {
		this.data = data;
	}
}
