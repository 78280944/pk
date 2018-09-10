package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RemarkDto {
	
	    @ApiModelProperty(value = "在线客服")
	    private String online;
	    
	    @ApiModelProperty(value = "分享链接")
	    private String share; 
	    
	    @ApiModelProperty(value = "分享二维码")
	    private String shareCode; 
	    
	    @ApiModelProperty(value = "规则说明")
	    private String rule;
	    
	    @ApiModelProperty(value = "安卓App版本")
	    private String androidAppVersion;
	    
	    @ApiModelProperty(value = "苹果App版本")
	    private String iosAppVersion;
	    
	    @ApiModelProperty(value = "有庄结算图")
	    private String playoridle;

	    @ApiModelProperty(value = "无庄结算图")
	    private String noplayoridle;
	    
	    
	    
		public String getPlayoridle() {
			return playoridle;
		}

		public void setPlayoridle(String playoridle) {
			this.playoridle = playoridle;
		}

		public String getNoplayoridle() {
			return noplayoridle;
		}

		public void setNoplayoridle(String noplayoridle) {
			this.noplayoridle = noplayoridle;
		}

		public String getOnline() {
			return online;
		}

		public void setOnline(String online) {
			this.online = online;
		}

		public String getShare() {
			return share;
		}

		public void setShare(String share) {
			this.share = share;
		}

		public String getRule() {
			return rule;
		}

		public void setRule(String rule) {
			this.rule = rule;
		}

		public String getShareCode() {
			return shareCode;
		}

		public void setShareCode(String shareCode) {
			this.shareCode = shareCode;
		}

		public String getAndroidAppVersion() {
			return androidAppVersion;
		}

		public void setAndroidAppVersion(String androidAppVersion) {
			this.androidAppVersion = androidAppVersion;
		}

		public String getIosAppVersion() {
			return iosAppVersion;
		}

		public void setIosAppVersion(String iosAppVersion) {
			this.iosAppVersion = iosAppVersion;
		}

}
