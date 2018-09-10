package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.OffAccountDto;
import com.lottery.orm.dto.OffsAccountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OffAccountListResult extends BaseRestResult {
	
	@ApiModelProperty(value = "代理账户列表数据", required = true)
	private List<OffsAccountDto> data;

	public void success(List<OffsAccountDto> data) {
		success();
		this.data = data;
	}

	public List<OffsAccountDto> getData() {
		return data;
	}

	public void setData(List<OffsAccountDto> list) {
		this.data = list;
	}
}
