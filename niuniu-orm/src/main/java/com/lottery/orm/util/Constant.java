package com.lottery.orm.util;

public class Constant {
	public final static String STATUS_NULL = "-1000";
	// 刚创建任务
	public final static String STATUS_CREATE = "0000";
	// 正在抓取
	public final static String STATUS_ING = "0001";
	// 抓取完成
	public final static String STATUS_COMPLETE = "0002";
	// 抓取错误
	public final static String STATUS_ERROR = "0003";
	// 抓取失败
	public final static String STATUS_ERROR_LOGIN = "0003_3002";
	// 抓取失败
	public final static String STATUS_ERROR_AUTHCODE = "0003_3003";
	
	// 已传输成功
	public final static String STATUS_INFORM_OK = "1000";
	// 已传输失败
	public final static String STATUS_INFORM_FAIL = "1003";

}
