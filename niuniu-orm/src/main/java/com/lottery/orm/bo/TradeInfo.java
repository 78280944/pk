package com.lottery.orm.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TradeInfo {

	private Integer tradeid;

    private Integer accountid;

    private String tradetype;

    private Integer relativeid;

    private String relativetype;

    private Double tradeamount;
    
    private Double budgetamount;

    private BigDecimal accountamount;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date inputtime;

    private String remark;
    
    private String orderid;
    
    private Double giftamount;
    
    private Double seramount;
    
    private Double actamount;
    
    private String ip;
    
    private int operateuserid;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date operatetime;
    
    private int updateuserid;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatetime;
    
    private String state;
    
    private String operatestate;

    


	public Double getBudgetamount() {
		return budgetamount;
	}

	public void setBudgetamount(Double budgetamount) {
		this.budgetamount = budgetamount;
	}

	public Integer getTradeid() {
		return tradeid;
	}

	public void setTradeid(Integer tradeid) {
		this.tradeid = tradeid;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public Integer getRelativeid() {
		return relativeid;
	}

	public void setRelativeid(Integer relativeid) {
		this.relativeid = relativeid;
	}

	public String getRelativetype() {
		return relativetype;
	}

	public void setRelativetype(String relativetype) {
		this.relativetype = relativetype;
	}

	public Double getTradeamount() {
		return tradeamount;
	}

	public void setTradeamount(Double tradeamount) {
		this.tradeamount = tradeamount;
	}

	public BigDecimal getAccountamount() {
		return accountamount;
	}

	public void setAccountamount(BigDecimal accountamount) {
		this.accountamount = accountamount;
	}

	public Date getInputtime() {
		return inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Double getGiftamount() {
		return giftamount;
	}

	public void setGiftamount(Double giftamount) {
		this.giftamount = giftamount;
	}

	public Double getSeramount() {
		return seramount;
	}

	public void setSeramount(Double seramount) {
		this.seramount = seramount;
	}

	public Double getActamount() {
		return actamount;
	}

	public void setActamount(Double actamount) {
		this.actamount = actamount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getOperateuserid() {
		return operateuserid;
	}

	public void setOperateuserid(int operateuserid) {
		this.operateuserid = operateuserid;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public int getUpdateuserid() {
		return updateuserid;
	}

	public void setUpdateuserid(int updateuserid) {
		this.updateuserid = updateuserid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOperatestate() {
		return operatestate;
	}

	public void setOperatestate(String operatestate) {
		this.operatestate = operatestate;
	}

}