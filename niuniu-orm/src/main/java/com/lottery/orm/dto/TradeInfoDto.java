package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeInfoDto {

    @ApiModelProperty(value = "存/取款金额")
    private  BigDecimal tradeamount;
       
    @ApiModelProperty(value = "存/取款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tradetime;
    
    @ApiModelProperty(value = "状态，01，成功；02，失败；03，处理中;04,审批不通过")
    private String orderstate;

	public BigDecimal getTradeamount() {
		return tradeamount;
	}

	public void setTradeamount(BigDecimal tradeamount) {
		this.tradeamount = tradeamount;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
   
    
}
