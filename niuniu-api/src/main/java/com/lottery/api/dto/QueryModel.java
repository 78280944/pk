package com.lottery.api.dto;

public class QueryModel {

	private String version;	    //必填	当前接口版本号 2
	private String agent_id;	//必填	商户编号如1001
	private String batch_no;	//必填	批量付款定单号（要保证唯一）。长度最长50字符	11<批量付款定单号<50
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
}
