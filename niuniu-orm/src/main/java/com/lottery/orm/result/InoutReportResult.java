package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.InoutReportDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class InoutReportResult extends BaseRestResult {

	@ApiModelProperty(value = "点数出入报表数据", required = true)
	private List<InoutReportDto> data;

	public void success(List<InoutReportDto> data) {
		success();
		this.data = data;
	}

	public List<InoutReportDto> getData() {
		return data;
	}

	public void setData(List<InoutReportDto> data) {
		this.data = data;
	}

}
