package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.AccBankDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccBankResult extends BaseRestResult {
	
	@ApiModelProperty(value = "银行列表数据", required = true)
	private List<AccBankDto> data = null;

	public void success(List<AccBankDto> data) {
		success();
		this.data = data;
	}

	public List<AccBankDto> getData() {
		return data;
	}

	public void setData(List<AccBankDto> list) {
		this.data = list;
	}

}
