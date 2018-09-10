package com.lottery.orm.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryOrderDto {
    @ApiModelProperty(value = "投注单ID", required = true)
    private Integer orderid;

	@ApiModelProperty(value = "游戏ID", required = true)
    private Integer roundid;

	@ApiModelProperty(value = "账户ID", required = true)
    private Integer accountid;

	@ApiModelProperty(value = "投注时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date ordertime;
	
	@ApiModelProperty(value = "投注金额", required = true)
    private Double orderamount;

    
	@ApiModelProperty(value = "投注单详情")
    List<OrderDetailDto> orderDetailList;

    public Integer getOrderid() {
	return orderid;
    }

    public void setOrderid(Integer orderid) {
	this.orderid = orderid;
    }

    public Integer getRoundid() {
	return roundid;
    }

    public void setRoundid(Integer roundid) {
	this.roundid = roundid;
    }

    public Integer getAccountid() {
	return accountid;
    }

    public void setAccountid(Integer accountid) {
	this.accountid = accountid;
    }

    public Double getOrderamount() {
	return orderamount;
    }

    public void setOrderamount(Double orderamount) {
	this.orderamount = orderamount;
    }


    public Date getOrdertime() {
	return ordertime;
    }

    public void setOrdertime(Date ordertime) {
	this.ordertime = ordertime;
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}