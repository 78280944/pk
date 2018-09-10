package com.lottery.orm.dto;


import com.wordnik.swagger.annotations.ApiModelProperty;

public class QueryRoomDateDto {
	
	@ApiModelProperty(value = "战绩ID", required = true)
	private Integer ltdid;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "01台", required = true)
	private String no1;
	
	@ApiModelProperty(value = "02台", required = true)
	private String no2;
	
	@ApiModelProperty(value = "03台", required = true)
	private String no3;

	@ApiModelProperty(value = "04台", required = true)
	private String no4;

	@ApiModelProperty(value = "05台", required = true)
	private String no5;
	
	@ApiModelProperty(value = "06台", required = true)
	private String no6;
	
	@ApiModelProperty(value = "07台", required = true)
	private String no7;
	
	@ApiModelProperty(value = "08台", required = true)
	private String no8;
	
	@ApiModelProperty(value = "09台", required = true)
	private String no9;
	
	@ApiModelProperty(value = "10台", required = true)
	private String no10;
	
	
	public Integer getLtdid() {
		return ltdid;
	}

	public void setLtdid(Integer ltdid) {
		this.ltdid = ltdid;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public String getNo1() {
		return no1;
	}

	public void setNo1(String no1) {
		this.no1 = no1;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getNo3() {
		return no3;
	}

	public void setNo3(String no3) {
		this.no3 = no3;
	}

	public String getNo4() {
		return no4;
	}

	public void setNo4(String no4) {
		this.no4 = no4;
	}

	public String getNo5() {
		return no5;
	}

	public void setNo5(String no5) {
		this.no5 = no5;
	}

	public String getNo6() {
		return no6;
	}

	public void setNo6(String no6) {
		this.no6 = no6;
	}

	public String getNo7() {
		return no7;
	}

	public void setNo7(String no7) {
		this.no7 = no7;
	}

	public String getNo8() {
		return no8;
	}

	public void setNo8(String no8) {
		this.no8 = no8;
	}

	public String getNo9() {
		return no9;
	}

	public void setNo9(String no9) {
		this.no9 = no9;
	}

	public String getNo10() {
		return no10;
	}

	public void setNo10(String no10) {
		this.no10 = no10;
	}
	
}
