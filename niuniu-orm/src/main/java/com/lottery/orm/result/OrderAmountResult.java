package com.lottery.orm.result;

import com.lottery.orm.dto.ResultAmountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OrderAmountResult  extends BaseRestResult{
	
	@ApiModelProperty(value = "金额汇总", required = true)
	private ResultAmountDto data;
	
	public void success(ResultAmountDto data) {
		success();
		this.data = data;
	}

	public ResultAmountDto getData() {
		return data;
	}

	public void setData(ResultAmountDto data) {
		this.data = data;
	}
}
