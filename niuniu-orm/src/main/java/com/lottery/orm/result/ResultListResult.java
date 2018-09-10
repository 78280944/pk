package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.ResultDataDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ResultListResult extends RestResult{
	
	@ApiModelProperty(value = "开奖结果", required = true)
	private List<ResultDataDto> data = null;

	public void success(List<ResultDataDto> data) {
		success();
		this.data = data;
	}

	public List<ResultDataDto> getData() {
		return data;
	}

	public void setData(List<ResultDataDto> list) {
		this.data = list;
	}
}
