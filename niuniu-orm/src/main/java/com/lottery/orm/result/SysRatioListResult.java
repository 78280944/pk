package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.SysRatio;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class SysRatioListResult extends BaseRestResult {
	
	@ApiModelProperty(value = "赔率及剩余金额数据", required = true)
	private List<SysRatio> data = null;

	public void success(List<SysRatio> data) {
		success();
		this.data = data;
	}

	public List<SysRatio> getData() {
		return data;
	}

	public void setData(List<SysRatio> list) {
		this.data = list;
	}
}
