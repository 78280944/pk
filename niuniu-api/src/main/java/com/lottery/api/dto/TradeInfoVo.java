package com.lottery.api.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeInfoVo extends PageParamVo{
	
	@ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountid;
	
	@ApiModelProperty(value = "查询类型(例如In,充值;Out,取现)", required = true)
    @NotNull(message = "查询类型不能为空")
	private String relativetype;
	
    @ApiModelProperty(value = "开始时间(例如2017-03-16)", required = true)
    @NotNull(message = "开始时间不能为空")
    private Date starttime;
    
    @ApiModelProperty(value = "结束时间(例如2017-03-16)", required = true)
    @NotNull(message = "结束时间不能为空")
    private Date overtime;
   

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	

	public String getRelativetype() {
		return relativetype;
	}

	public void setRelativetype(String relativetype) {
		this.relativetype = relativetype;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}
    
    
}
