package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomOrderDetailDto {
	
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
	
	@ApiModelProperty(value = "输赢结果", required = true)
	private String result;
	
	@ApiModelProperty(value = "输赢结算", required = true)
	private BigDecimal lastamount;

	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}

	public BigDecimal getLastamount() {
		return lastamount;
	}

	public void setLastamount(BigDecimal lastamount) {
		this.lastamount = lastamount;
	}
	
	
	
}
