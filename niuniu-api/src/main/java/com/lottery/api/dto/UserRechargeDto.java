package com.lottery.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserRechargeDto {
	@ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountId;
	
	@ApiModelProperty(value = "请求流水号", required = true)
    @NotNull(message = "请求流水号不能为空")
	private String requestNo;
	
	@ApiModelProperty(value = "版本号", required = true)
    @NotNull(message = "版本号不能为空")
	private String version;
	
	@ApiModelProperty(value = "商户号", required = true)
    @NotNull(message = "商户号不能为空")
	private String merNo;
	
	@ApiModelProperty(value = "产品编号", required = true)
    @NotNull(message = "产品编号不能为空")
	private String productId;
	
	@ApiModelProperty(value = "交易日期,日期格式yyyyMMdd", required = true)
    @NotNull(message = "交易日期不能为空")
	private String orderDate;
	
	@ApiModelProperty(value = "订单号", required = true)
    @NotNull(message = "订单号不能为空")
	private String orderNo;
	
	@ApiModelProperty(value = "交易金额", required = true)
    @NotNull(message = "交易金额不能为空")
	private Double transAmt;
	
	@ApiModelProperty(value = "页面通知地址", required = true)
	private String returnUrl;
	
	@ApiModelProperty(value = "异步通知地址", required = true)
    private String notifyUrl;

	@ApiModelProperty(value = "商品描述信息", required = true)
    private String commodityName;

	@ApiModelProperty(value = "支付链接", required = true)
    private String mwebUrl;

	@ApiModelProperty(value = "交易备注", required = true)
    private String remark;

	@ApiModelProperty(value = "扩展参数", required = true)
    private String extendField;

	@ApiModelProperty(value = "应答码", required = true)
    private String respCode;

	@ApiModelProperty(value = "应答码描述", required = true)
    private String respDesc;

	@ApiModelProperty(value = "验签字段", required = true)
    private String signature;

	@ApiModelProperty(value = "IP", required = true)
    private String orderIp;
	
	@ApiModelProperty(value = "充值状态，01：充值成功；02：充值失败", required = true)
    private String orderState;
}
