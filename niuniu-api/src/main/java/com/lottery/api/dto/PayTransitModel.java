package com.lottery.api.dto;

public class PayTransitModel {
	private String version;	    //必填	当前接口版本号 2
	private String agent_id;	//必填	商户编号如1001
	private String batch_no;	//必填	批量付款定单号（要保证唯一）。长度最长50字符	11<批量付款定单号<50
	private String batch_amt;	//必填	付款总金额不可为空，单位：元，小数点后保留两位。12.37
	private String batch_num;	//必填	该次付款总笔数，付给多少人的数目，“单笔数据集”里面的数据总笔数
	private String detail_data;	//必填	批付到银行帐户格式:“商户流水号^银行编号^对公对私^收款人帐号^收款人姓名^付款金额^付款理由^省份^城市^收款支行名称”来组织数据，每条整数据间用“|”符号分隔商户流水号长度最长20字符银行编号：参照枚举类型1,对公对私：参照枚举类型2
	private String notify_url;	//必填	支付后返回的商户处理页面，URL参数是以http://或https://开头的完整URL地址(后台处理)
	private String ext_param1;	//必填	商户自定义原样返回,长度最长50字符
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getBatch_amt() {
		return batch_amt;
	}
	public void setBatch_amt(String batch_amt) {
		this.batch_amt = batch_amt;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	public String getDetail_data() {
		return detail_data;
	}
	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getExt_param1() {
		return ext_param1;
	}
	public void setExt_param1(String ext_param1) {
		this.ext_param1 = ext_param1;
	}
	
}
