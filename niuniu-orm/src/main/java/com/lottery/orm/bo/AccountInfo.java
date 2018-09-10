package com.lottery.orm.bo;

import java.math.BigDecimal;
import java.util.Date;

public class AccountInfo {
    private Integer accountid;

    private String username;

    private String ausername;

    private String password;

    private Double limited;

    private Double ratio;

    private String ip;

    private Date inputdate;

    private String updateip;

    private Date updatedate;

    private String state;

    private Integer supuserid;

    private String level;

    private String phone;

    private String webchat;

    private BigDecimal usermoney;

    private String offtype;

    private Double percentage;

    private String query;

    private String budget;

    private String code;

    private String sfcode;

    private String bankid;

    private String bankname;

    private String bankaddress;

    private String bankaccount;
    
    private String bankno;
    
    private String bankloproname;
    
    private String banklocityname;
    
    private String phoneno;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAusername() {
        return ausername;
    }

    public void setAusername(String ausername) {
        this.ausername = ausername == null ? null : ausername.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Double getLimited() {
        return limited;
    }

    public void setLimited(Double limited) {
        this.limited = limited;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public String getUpdateip() {
        return updateip;
    }

    public void setUpdateip(String updateip) {
        this.updateip = updateip == null ? null : updateip.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getSupuserid() {
        return supuserid;
    }

    public void setSupuserid(Integer supuserid) {
        this.supuserid = supuserid == null ? null : supuserid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWebchat() {
        return webchat;
    }

    public void setWebchat(String webchat) {
        this.webchat = webchat == null ? null : webchat.trim();
    }

    public BigDecimal getUsermoney() {
        return usermoney;
    }

    public void setUsermoney(BigDecimal usermoney) {
        this.usermoney = usermoney;
    }

    public String getOfftype() {
        return offtype;
    }

    public void setOfftype(String offtype) {
        this.offtype = offtype == null ? null : offtype.trim();
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query == null ? null : query.trim();
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget == null ? null : budget.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSfcode() {
        return sfcode;
    }

    public void setSfcode(String sfcode) {
        this.sfcode = sfcode == null ? null : sfcode.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress == null ? null : bankaddress.trim();
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount == null ? null : bankaccount.trim();
    }

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankloproname() {
		return bankloproname;
	}

	public void setBankloproname(String bankloproname) {
		this.bankloproname = bankloproname;
	}

	public String getBanklocityname() {
		return banklocityname;
	}

	public void setBanklocityname(String banklocityname) {
		this.banklocityname = banklocityname;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
    
    
}