package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryServiceDto {
	 
    @ApiModelProperty(value = "登录服务;1:有效；0：无效")
    private String loginservice;
    
    @ApiModelProperty(value = "注册服务;1:有效；0：无效")
    private String registersercice;

    @ApiModelProperty(value = "增值服务;1:有效；0：无效")
    private String addedservice;

    @ApiModelProperty(value = "玩家服务;1:有效；0：无效")
    private String playservice;

    @ApiModelProperty(value = "牛牛房间数设置")
    private Integer lotteryniuroom;
    
    @ApiModelProperty(value = "牛牛房间数人数设置")
    private Integer lotteryniunpman;
    
    @ApiModelProperty(value = "牛牛上庄金额设置")
    private BigDecimal lotteryniunbanker;
    
    @ApiModelProperty(value = "牛牛投注金额设置")
    private BigDecimal lotteryniunpbet;
    
    @ApiModelProperty(value = "板九房间数设置")
    private Integer lotterybjroom;
    
    @ApiModelProperty(value = "板九房间人数设置")
    private Integer lotterybjman;
    
    @ApiModelProperty(value = "板九上庄金额设置")
    private  BigDecimal lotterybjbanker;
    
    @ApiModelProperty(value = "板九投注金额设置")
    private BigDecimal lotterybjbet;
    
    @ApiModelProperty(value = "金额倍数设置")
    private BigDecimal ratio;

	public String getLoginservice() {
		return loginservice;
	}

	public void setLoginservice(String loginservice) {
		this.loginservice = loginservice;
	}

	public String getRegistersercice() {
		return registersercice;
	}

	public void setRegistersercice(String registersercice) {
		this.registersercice = registersercice;
	}

	public String getAddedservice() {
		return addedservice;
	}

	public void setAddedservice(String addedservice) {
		this.addedservice = addedservice;
	}

	public String getPlayservice() {
		return playservice;
	}

	public void setPlayservice(String playservice) {
		this.playservice = playservice;
	}

	public Integer getLotteryniuroom() {
		return lotteryniuroom;
	}

	public void setLotteryniuroom(Integer lotteryniuroom) {
		this.lotteryniuroom = lotteryniuroom;
	}

	public Integer getLotteryniunpman() {
		return lotteryniunpman;
	}

	public void setLotteryniunpman(Integer lotteryniunpman) {
		this.lotteryniunpman = lotteryniunpman;
	}

	public BigDecimal getLotteryniunbanker() {
		return lotteryniunbanker;
	}

	public void setLotteryniunbanker(BigDecimal lotteryniunbanker) {
		this.lotteryniunbanker = lotteryniunbanker;
	}

	public BigDecimal getLotteryniunpbet() {
		return lotteryniunpbet;
	}

	public void setLotteryniunpbet(BigDecimal lotteryniunpbet) {
		this.lotteryniunpbet = lotteryniunpbet;
	}

	public Integer getLotterybjroom() {
		return lotterybjroom;
	}

	public void setLotterybjroom(Integer lotterybjroom) {
		this.lotterybjroom = lotterybjroom;
	}

	public Integer getLotterybjman() {
		return lotterybjman;
	}

	public void setLotterybjman(Integer lotterybjman) {
		this.lotterybjman = lotterybjman;
	}

	public BigDecimal getLotterybjbanker() {
		return lotterybjbanker;
	}

	public void setLotterybjbanker(BigDecimal lotterybjbanker) {
		this.lotterybjbanker = lotterybjbanker;
	}

	public BigDecimal getLotterybjbet() {
		return lotterybjbet;
	}

	public void setLotterybjbet(BigDecimal lotterybjbet) {
		this.lotterybjbet = lotterybjbet;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
     
    
}
