package com.lottery.orm.dto;


import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserAmountDto {
	
	@ApiModelProperty(value = "在线人数", required = true)
    private int count;

	@ApiModelProperty(value = "微信支付二维码", required = true)
    private String webaddress;
	
	@ApiModelProperty(value = "支付宝支付二维码", required = true)
    private String alipayaddress;
	
	@ApiModelProperty(value = "QQ支付二维码", required = true)
    private String qqaddress;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWebaddress() {
		return webaddress;
	}

	public void setWebaddress(String webaddress) {
		this.webaddress = webaddress;
	}

	public String getAlipayaddress() {
		return alipayaddress;
	}

	public void setAlipayaddress(String alipayaddress) {
		this.alipayaddress = alipayaddress;
	}

	public String getQqaddress() {
		return qqaddress;
	}

	public void setQqaddress(String qqaddress) {
		this.qqaddress = qqaddress;
	}

	
}
