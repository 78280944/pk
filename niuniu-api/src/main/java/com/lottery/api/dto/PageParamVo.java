package com.lottery.api.dto;

import javax.validation.constraints.Min;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class PageParamVo {
    
	@ApiModelProperty(value = "当前页")
	@Min(value=1, message = "当前页必须大于0")
    private Integer curPage;
    
	@ApiModelProperty(value = "每页大小")
	@Min(value=1, message = "每页大小必须大于0")
    private Integer pageSize;

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getBeginRow() {
		return pageSize*(curPage-1);//limit start from 0, so do not plus 1
	}
}