package com.lottery.api.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OrderParamVo {

    @ApiModelProperty(value = "账户ID", required = true)
    @NotNull(message = "账户ID不能为空")
    @Min(value=0, message = "账户ID格式不正确")
    private Integer accountId;

    @ApiModelProperty(value = "游戏ID", required = true)
    private Integer sid;

    @ApiModelProperty(value = "房间ID", required = true)
    private Integer rmid;
    
	@ApiModelProperty(value = "游戏期次", required = true)
    private String lotteryTerm;
    
    @ApiModelProperty(value = "投注详情", required = true)
    @NotEmpty(message = "投注详情不能为空")
    @Valid
    private List<OrderDetailVo> orderDetails;

    public Integer getAccountId() {
	return accountId;
    }

    public void setAccountId(Integer accountId) {
	this.accountId = accountId;
    }

   
    public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}



	public String getLotteryTerm() {
		return lotteryTerm;
	}

	public void setLotteryTerm(String lotteryTerm) {
		this.lotteryTerm = lotteryTerm;
	}

	public List<OrderDetailVo> getOrderDetails() {
	return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailVo> orderDetails) {
	this.orderDetails = orderDetails;
    }

}
