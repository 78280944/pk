package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateSubAccountVo extends BaseAccountInfoVo{
	
	    @ApiModelProperty(value = "用户id", required = true)
	    private int userid;

	   @ApiModelProperty(value = "用户名")
	    private String username;
	    
	    @ApiModelProperty(value = "别名")
	    private String ausername;
	    
	    @ApiModelProperty(value = "密码")
	    private String password;

	    @ApiModelProperty(value = "查询权限")
	    private String query;
	    
	    @ApiModelProperty(value = "管理权限")
	    private String manage;
	    
	    @ApiModelProperty(value = "状态")
	    private String state;

	    

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getAusername() {
			return ausername;
		}

		public void setAusername(String ausername) {
			this.ausername = ausername;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}

		public String getManage() {
			return manage;
		}

		public void setManage(String manage) {
			this.manage = manage;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
	    
}
