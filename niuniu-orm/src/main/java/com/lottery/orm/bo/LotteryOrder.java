package com.lottery.orm.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryOrder {

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

	@ApiModelProperty(value = "代理佣金")
    private Double commisionamount;
    
	@ApiModelProperty(value = "中奖")
    private Double prizeamount;

	@ApiModelProperty(value = "奖金实收")
    private Double actualamount;

	@ApiModelProperty(value = "返利")
    private Double returnamount;
	
	@ApiModelProperty(value = "代理返利")
	private Double agencyreturn;
	
	@ApiModelProperty(value = "公司损益")
	private Double systemamount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date prizetime;

	@ApiModelProperty(value = "账户余额")
    private BigDecimal accountamount;
    
	@ApiModelProperty(value = "投注详单")
    List<LotteryOrderDetail> orderDetailList;

	public Double getAgencyreturn() {
		return agencyreturn;
	}

	public void setAgencyreturn(Double agencyreturn) {
		this.agencyreturn = agencyreturn;
	}

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

    public Double getCommisionamount() {
	return commisionamount;
    }

    public void setCommisionamount(Double commisionamount) {
	this.commisionamount = commisionamount;
    }

    public Date getOrdertime() {
	return ordertime;
    }

    public void setOrdertime(Date ordertime) {
	this.ordertime = ordertime;
    }

    public Double getPrizeamount() {
	return prizeamount;
    }

    public void setPrizeamount(Double prizeamount) {
	this.prizeamount = prizeamount;
    }

    public Double getActualamount() {
	return actualamount;
    }

    public void setActualamount(Double actualamount) {
	this.actualamount = actualamount;
    }

    public Double getReturnamount() {
	return returnamount;
    }

    public void setReturnamount(Double returnamount) {
	this.returnamount = returnamount;
    }
    
    public Double getSystemamount() {
		return systemamount;
	}

	public void setSystemamount(Double systemamount) {
		this.systemamount = systemamount;
	}
	
    public Date getPrizetime() {
	return prizetime;
    }

    public void setPrizetime(Date prizetime) {
	this.prizetime = prizetime;
    }

    public BigDecimal getAccountamount() {
	return accountamount;
    }

    public void setAccountamount(BigDecimal accountamount) {
	this.accountamount = accountamount;
    }
    
    public List<LotteryOrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<LotteryOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}