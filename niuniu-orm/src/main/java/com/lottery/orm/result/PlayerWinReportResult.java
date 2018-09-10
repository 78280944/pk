package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.PlayerWinReportDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * POJO class for rest process result.
 * 
 */
public class PlayerWinReportResult extends BaseRestResult {

	@ApiModelProperty(value = "输赢报表数据", required = true)
	private List<PlayerWinReportDto> data;

	public void success(List<PlayerWinReportDto> data) {
		success();
		this.data = data;
	}

	public List<PlayerWinReportDto> getData() {
		return data;
	}

	public void setData(List<PlayerWinReportDto> data) {
		this.data = data;
	}

}
