package com.lottery.orm.dto;

import java.math.BigDecimal;

public class ProAccAmountDto {
	
	private Integer aaid;

    private String username;

    private String supusername;
    
    private Double percentage; 

    private BigDecimal loss;

    private BigDecimal earns;

    private BigDecimal gains;
    
    private BigDecimal cfee;
    
    private BigDecimal profits;

	public Integer getAaid() {
		return aaid;
	}

	public void setAaid(Integer aaid) {
		this.aaid = aaid;
	}

	public String getSupusername() {
		return supusername;
	}

	public void setSupusername(String supusername) {
		this.supusername = supusername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getLoss() {
		return loss;
	}

	public void setLoss(BigDecimal loss) {
		this.loss = loss;
	}

	public BigDecimal getEarns() {
		return earns;
	}

	public void setEarns(BigDecimal earns) {
		this.earns = earns;
	}

	public BigDecimal getGains() {
		return gains;
	}

	public void setGains(BigDecimal gains) {
		this.gains = gains;
	}

	public BigDecimal getProfits() {
		return profits;
	}

	public void setProfits(BigDecimal profits) {
		this.profits = profits;
	}

	public BigDecimal getCfee() {
		return cfee;
	}

	public void setCfee(BigDecimal cfee) {
		this.cfee = cfee;
	}

}
