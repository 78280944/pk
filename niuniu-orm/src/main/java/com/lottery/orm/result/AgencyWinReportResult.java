package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.ProAccAmountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * POJO class for rest process result.
 * 
 */
public class AgencyWinReportResult extends BaseRestResult {

	@ApiModelProperty(value = "输赢报表数据", required = true)
	private List<ProAccAmountDto> data;

	public void success(List<ProAccAmountDto> data) {
		success();
		this.data = data;
	}

	public List<ProAccAmountDto> getData() {
		return data;
	}

	public void setData(List<ProAccAmountDto> data) {
		this.data = data;
	}

}
