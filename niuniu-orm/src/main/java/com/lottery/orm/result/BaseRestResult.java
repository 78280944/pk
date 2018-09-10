package com.lottery.orm.result;

import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * POJO class for rest process result.
 * 
 */
public class BaseRestResult {
	@ApiModelProperty(value = "结果代码", required = true)
	protected int code;

	@ApiModelProperty(value = "结果信息", required = true)
	protected String message;

	public void success() {
		code = MessageTool.SuccessCode;
		message = MessageTool.getMsg(code);
	}

	public void error() {
		code = MessageTool.ErrorCode;
		message = MessageTool.getMsg(code);
	}

	public void error(String message) {
		error();
		this.message = message;
	}
	
	public void fail() {
		this.code = MessageTool.FailCode;
		this.message = MessageTool.getMsg(code);
	}

	public void fail(String message) {
		this.code = MessageTool.FailCode;
		this.message = message;
	}

	public void fail(int code) {
		this.code = code;
		this.message = MessageTool.getMsg(code);
	}

	public void fail(String msg, int code) {
		this.code = code;
		this.message = msg + MessageTool.getMsg(code);
	}

	public void fail(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		this.message = MessageTool.getMsg(code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
