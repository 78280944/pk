package com.lottery.orm.util;

public interface EnumType {
	enum Lottery {
	    YMZ("01", "玉米籽");
	    private Lottery(String ID, String NAME) {
	      this.ID = ID;
	      this.NAME = NAME;
	    }

	    public final String ID;
	    public final String NAME;
	    public final static String enumDesc = "游戏";
	  }
  enum LotteryType {

	  CQSSC("1001","重庆时时彩"),TJSSC("1002","天津时时彩"),HLJSSC("1003","黑龙江时时彩"),XJSSC("1004","新疆时时彩"),
	  YNSSC("1005","云南时时彩"),BJPK("2001","北京PK10"),XYFT("2002","幸运飞艇"),
	  CQ("01", "重庆快乐十分玉米籽"),GD("02", "广东快乐十分玉米籽"),TJ("03", "天津快乐十分玉米籽");
	 
    private LotteryType(String ID, String NAME) {
      this.ID = ID;
      this.NAME = NAME;
    }

    public final String ID;
    public final String NAME;
    public final static String enumDesc = "玉米籽类型";
  }
  
  enum LotteryResultNiu {
	  Result_niuniu_bz("01","豹子",6),Result_niuniu_nn("02","牛牛",5),Result_niuniu_ds("03","倒顺",4),Result_niuniu_sz("04","顺子",4),
	  Result_niuniu_dz("05","对子",3),Result_niuniu_n9("06","牛九",2),Result_niuniu_n8("07","牛八",2),
	  Result_niuniu_n7("08", "牛七",2),Result_niuniu_n6("09", "牛六",1),Result_niuniu_n5("10", "牛五",1),
	  Result_niuniu_n4("11", "牛四",1),Result_niuniu_n3("12", "牛三",1),Result_niuniu_n2("13", "牛二",1),Result_niuniu_n1("14", "牛一",1);
	 
    private LotteryResultNiu(String ID, String NAME,int RATIO) {
      this.ID = ID;
      this.NAME = NAME;
      this.RATIO = RATIO;
    }
    

    public final String ID;
    public final String NAME;
    public final int RATIO;
    public final static String enumDesc = "牛牛结果";
  }
  
  enum LotteryResultBan {
  	  Result_banjiu_01("01","第一名",1),Result_banjiu_02("02","第二名",1),Result_banjiu_03("03","第三名",1),Result_banjiu_04("04","第四名",1),
      Result_banjiu_05("05","第五名",1);
  	 
      private LotteryResultBan(String ID, String NAME,int RATIO) {
        this.ID = ID;
        this.NAME = NAME;
        this.RATIO = RATIO;
      }
  
      public final String ID;
      public final String NAME;
      public final int RATIO;
      public final static String enumDesc = "板九结果";
    }
  
  enum ItemType {
    Type_01("01", "番摊玉米籽"), Type_02("02", "广西快乐十分");
    private ItemType(String ID, String NAME) {
      this.ID = ID;
      this.NAME = NAME;
    }

    public final String ID;
    public final String NAME;
    public final static String enumDesc = "投注项类型";
  }
  
  enum RoundStatus {
    Open("Open", "开盘中"), Close("Close", "已封盘"), End("End", "已开奖");
    private RoundStatus(String ID, String NAME) {
      this.ID = ID;
      this.NAME = NAME;
    }

    public final String ID;
    public final String NAME;
    public final static String enumDesc = "游戏状态";
  }
  
  enum TradeType {
    Inout("Inout", "出入金"), Trade("Trade", "交易");
    private TradeType(String ID, String NAME) {
      this.ID = ID;
      this.NAME = NAME;
    }

    public final String ID;
    public final String NAME;
    public final static String enumDesc = "业务类型";
  }
  
  enum RalativeType {
    Trade("Trade",1001,"下注"),In("In", 1002,"入金"),Out("Out", 1003,"出金"), Commision("Commision",1004, "公司损益"),
    Order("Order",1005, "下注本金"), PlayerWin("PlayerWin", 1006,"会员输赢"),AgencyWin("AgencyWin",1007, "代理佣金"), Return("Return", 1008,"分享佣金");
    private RalativeType(String ID, int NOID,String NAME) {
      this.ID = ID;
      this.NOID  = NOID;
      this.NAME = NAME;
    }

    public final String ID;
    public final int NOID;
    public final String NAME;
    public final static String enumDesc = "业务相关类型";
  }
  
  enum OffType {
	    Admin("0", "超级账户"),Agency("1", "代理账户"), Sub("2", "子账户类型"), Play("3", "会员账户");
	    private OffType(String ID, String NAME) {
	      this.ID = ID;
	      this.NAME = NAME;
	    }

	    public final String ID;
	    public final String NAME;
	    public final static String enumDesc = "账户类型";
	  }
}
