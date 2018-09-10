package com.lottery.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class EndOrderVo {

    @ApiModelProperty(value = "下注单ID", required = true)
    @NotNull(message = "下注单ID不能为空")
    @Min(value=0, message = "下注单ID格式不正确")
    private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

    
}
