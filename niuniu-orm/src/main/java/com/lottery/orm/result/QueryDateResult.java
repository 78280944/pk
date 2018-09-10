package com.lottery.orm.result;

import com.lottery.orm.dto.QueryDateDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * POJO class for rest process result.
 * 
 */
public class QueryDateResult extends BaseRestResult {

	@ApiModelProperty(value = "输赢报表数据", required = true)
	private QueryDateDto data;

	public void success(QueryDateDto data) {
		success();
		this.data = data;
	}

	public QueryDateDto getData() {
		return data;
	}

	public void setData(QueryDateDto data) {
		this.data = data;
	}

}
