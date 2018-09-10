package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomOrderItemDto {

	@ApiModelProperty(value = "订单ID", required = true)
	private Integer lgmid;
	
	@ApiModelProperty(value = "排名", required = true)
	private Integer orderno;
	
	@ApiModelProperty(value = "昵称", required = true)
	private String ausername;
	
	@ApiModelProperty(value = "台号", required = true)
	private Integer noid;
	
	@ApiModelProperty(value = "投注金额", required = true)
	private BigDecimal orderamount;
	
	@ApiModelProperty(value = "投注占比", required = true)
	private Double ratio;
	
	@ApiModelProperty(value = "庄/闲", required = true)
	private String playoridle;
	

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getAusername() {
		return ausername;
	}

	public void setAusername(String ausername) {
		this.ausername = ausername;
	}

	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public BigDecimal getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(BigDecimal orderamount) {
		this.orderamount = orderamount;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}



	
	
}
