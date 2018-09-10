package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.NoticeInfo;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class NoticeResult extends BaseRestResult {
	@ApiModelProperty(value = "历史公告", required = true)
	private List<NoticeInfo> data = null;

	public void success(List<NoticeInfo> data) {
		success();
		this.data = data;
	}

	public List<NoticeInfo> getData() {
		return data;
	}

	public void setData(List<NoticeInfo> list) {
		this.data = list;
	}
}
