package com.lottery.orm.bo;

public class LotteryGameResults extends LotteryGameResultsKey {
    private Integer noid;
    
	private String orders;

	private String results;

	private Integer scount;

	private String first;

	private String second;

	private String third;

	private String resultno;

	private String resultvalue;

	private Integer ascc;

	private Double ratio;

	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders == null ? null : orders.trim();
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results == null ? null : results.trim();
	}

	public Integer getScount() {
		return scount;
	}

	public void setScount(Integer scount) {
		this.scount = scount;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first == null ? null : first.trim();
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second == null ? null : second.trim();
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third == null ? null : third.trim();
	}

	public String getResultno() {
		return resultno;
	}

	public void setResultno(String resultno) {
		this.resultno = resultno == null ? null : resultno.trim();
	}

	public String getResultvalue() {
		return resultvalue;
	}

	public void setResultvalue(String resultvalue) {
		this.resultvalue = resultvalue == null ? null : resultvalue.trim();
	}

	public Integer getAscc() {
		return ascc;
	}

	public void setAscc(Integer ascc) {
		this.ascc = ascc;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	
}