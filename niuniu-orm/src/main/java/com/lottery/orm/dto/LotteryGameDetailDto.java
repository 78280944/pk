package com.lottery.orm.dto;

import java.math.BigDecimal;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameDetailDto {
	
	@ApiModelProperty(value = "订单号", required = true)
    private Integer lgmid;
	
	@ApiModelProperty(value = "输赢桌号", required = true)
    private Integer rnoid;
	
	@ApiModelProperty(value = "输赢战绩", required = true)
    private String rresult;
	
	@ApiModelProperty(value = "输赢结果", required = true)
    private BigDecimal trademoney;


	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

	

	public Integer getRnoid() {
		return rnoid;
	}

	public void setRnoid(Integer rnoid) {
		this.rnoid = rnoid;
	}

	public String getRresult() {
		return rresult;
	}

	public void setRresult(String rresult) {
		this.rresult = rresult;
	}

	public BigDecimal getTrademoney() {
		return trademoney;
	}

	public void setTrademoney(BigDecimal trademoney) {
		this.trademoney = trademoney;
	}
    
    
}
