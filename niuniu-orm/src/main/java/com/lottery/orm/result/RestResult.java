package com.lottery.orm.result;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestResult extends BaseRestResult {
	private Object data;

	@ApiModelProperty(value = "结果数据")
	public void success(Object data) {
		success();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
