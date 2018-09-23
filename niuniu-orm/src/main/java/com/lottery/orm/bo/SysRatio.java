package com.lottery.orm.bo;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SysRatio {
	
	@ApiModelProperty(value = "流水ID", required = true)
    private Integer srid;

	@ApiModelProperty(value = "号码", required = true)
    private String itemno;

	@ApiModelProperty(value = "赔率", required = true)
    private BigDecimal ratio;

	@ApiModelProperty(value = "剩余金额", required = true)
    private Long amount;
    
	@ApiModelProperty(value = "总金额/金额差值", required = true)
    private Long amounts;

	@ApiModelProperty(value = "期次", required = true)
    private String lotteryterm;
    
	@ApiModelProperty(value = "备注", required = true)
    private String remark;
	
    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno == null ? null : itemno.trim();
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

	public Long getAmounts() {
		return amounts;
	}

	public void setAmounts(Long amounts) {
		this.amounts = amounts;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}