package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.SubAccountDto;
import com.lottery.orm.dto.SubsAccountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class SubAccountListResult extends BaseRestResult {
	
	@ApiModelProperty(value = "子账户列表数据", required = true)
	private List<SubsAccountDto> data;

	public void success(List<SubsAccountDto> data) {
		success();
		this.data = data;
	}

	public List<SubsAccountDto> getData() {
		return data;
	}

	public void setData(List<SubsAccountDto> list) {
		this.data = list;
	}

}
