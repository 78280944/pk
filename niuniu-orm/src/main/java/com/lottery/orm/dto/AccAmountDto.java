package com.lottery.orm.dto;

import java.math.BigDecimal;

public class AccAmountDto {
	
	private Integer aaid;

    private String username;
    
    private String supusername;

    private BigDecimal loss;

    private BigDecimal earns;

    private BigDecimal gains;

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

    
}
