package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryResultDto {
	
	    @ApiModelProperty(value = "(流水号)ID", required = true)
	    private Integer lgrsid;

	    @ApiModelProperty(value = "游戏期次", required = true)
		private String lotteryterm;

	    @ApiModelProperty(value = "游戏号", required = true)
		private Integer sid;
		
	    @ApiModelProperty(value = "桌号", required = true)
		private Integer noid;

	    @ApiModelProperty(value = "结果号码", required = true)
		private String results;

	    @ApiModelProperty(value = "游戏结果代号", required = true)
		private String resultno;
	    
	    @ApiModelProperty(value = "游戏结果名称", required = true)
		private String resultvalue;

	    @ApiModelProperty(value = "排名", required = true)
		private Integer ascc;

		public Integer getLgrsid() {
			return lgrsid;
		}

		public void setLgrsid(Integer lgrsid) {
			this.lgrsid = lgrsid;
		}

		public String getLotteryterm() {
			return lotteryterm;
		}

		public void setLotteryterm(String lotteryterm) {
			this.lotteryterm = lotteryterm;
		}

		public Integer getSid() {
			return sid;
		}

		public void setSid(Integer sid) {
			this.sid = sid;
		}

		public Integer getNoid() {
			return noid;
		}

		public void setNoid(Integer noid) {
			this.noid = noid;
		}

		public String getResults() {
			return results;
		}

		public void setResults(String results) {
			this.results = results;
		}

		public String getResultno() {
			return resultno;
		}

		public void setResultno(String resultno) {
			this.resultno = resultno;
		}

		public String getResultvalue() {
			return resultvalue;
		}

		public void setResultvalue(String resultvalue) {
			this.resultvalue = resultvalue;
		}

		public Integer getAscc() {
			return ascc;
		}

		public void setAscc(Integer ascc) {
			this.ascc = ascc;
		}
		
		

}
