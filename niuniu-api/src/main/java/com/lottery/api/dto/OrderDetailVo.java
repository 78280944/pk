package com.lottery.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OrderDetailVo {

    @ApiModelProperty(value = "投注台编号", required = true)
    private Integer noId;

    @ApiModelProperty(value = "投注金额", required = true)
    @NotNull(message = "投注金额不能为空")
    @DecimalMin(value="0.01", message = "金额必须大于零")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "庄闲；1：庄；2：闲", required = true)
    private String playOridle;

	public Integer getNoId() {
		return noId;
	}

	public void setNoId(Integer noId) {
		this.noId = noId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPlayOridle() {
		return playOridle;
	}

	public void setPlayOridle(String playOridle) {
		this.playOridle = playOridle;
	}
    
}
