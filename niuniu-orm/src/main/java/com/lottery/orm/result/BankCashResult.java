package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.BankCash;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class BankCashResult extends BaseRestResult {
	@ApiModelProperty(value = "银行列表数据", required = true)
	private List<BankCash> data = null;

	public void success(List<BankCash> data) {
		success();
		this.data = data;
	}

	public List<BankCash> getData() {
		return data;
	}

	public void setData(List<BankCash> list) {
		this.data = list;
	}
}
