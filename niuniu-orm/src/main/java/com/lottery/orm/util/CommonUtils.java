package com.lottery.orm.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.LotteryGameDetail;
import com.lottery.orm.dao.LotteryGameDetailMapper;
import com.lottery.orm.service.AccountInfoService;
import com.lottery.orm.service.LotteryRoundService;
import com.lottery.orm.service.LotteryTaskService;

@Service
@Transactional
public class CommonUtils {

	

	//01,本日;02,上周;03,本周;04,上期;05,本期;
	public static Date[] getDateBetween(Date startTime,Date endTime,String time) throws ParseException {
		Date[] sTime = new Date[2];
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (time.equals("01")){
			String str = getCurrentTime();
			sTime[0] = sdf.parse(str.split(",")[0]);
			sTime[1] = sdf.parse(str.split(",")[1]);
		}else if (time.equals("02")){
			String str = getLastTimeInterval();
			sTime[0] = sdf.parse(str.split(",")[0]);
			sTime[1] = sdf.parse(str.split(",")[1]);
		}else if (time.equals("03")){
			String str = getTimeInterval();
			sTime[0] = sdf.parse(str.split(",")[0]);
			sTime[1] = sdf.parse(str.split(",")[1]);
		}
		
		return sTime;
	}
	
	//01,本日;02,上周;03,本周;04,上期;05,本期;
   public static Date[] getDateTime(Date startTime,Date endTime) throws ParseException {
			Date[] sTime = new Date[2];
			DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String imptimeBegin = sdf1.format(startTime);
			String imptimeEnd = sdf1.format(endTime);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			imptimeBegin = imptimeBegin+" 00:00:01"; 
			imptimeEnd = imptimeEnd+" 23:59:59";
			sTime[0] = sdf.parse(imptimeBegin);
			sTime[1] = sdf.parse(imptimeEnd);
			return sTime;  
		}
   
   public static Date getNextDay(Date date) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.setTime(date);  
       calendar.add(Calendar.DAY_OF_MONTH, -1);  
       date = calendar.getTime();  
       return date;  
   }
	
	public static String getMonday(){
		String monday = "";
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, -1*7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-mm-dd").format(cal.getTime());
		return monday+"00:00:00";
	}
	
	public static String getSunday(){
		String sunday = "";
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, -1*7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sunday = new SimpleDateFormat("yyyy-mm-dd").format(cal.getTime());
		return sunday+"00:00:00";
	}
	
	 /** 
	* 根据当前日期获得上周的日期区间（上周周一和周日日期） 
	*  
	* @return 
	* @author zhaoxuepu 
	*/  
	public static String getLastTimeInterval() {  
	     Calendar calendar1 = Calendar.getInstance();  
	     Calendar calendar2 = Calendar.getInstance();  
	     int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
	     int offset1 = 1 - dayOfWeek;  
	     int offset2 = 7 - dayOfWeek;  
	     calendar1.add(Calendar.DATE, offset1 - 7);  
	     calendar2.add(Calendar.DATE, offset2 - 7);  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     // System.out.println(sdf.format(calendar1.getTime()));// last Monday  
	     String lastBeginDate = sdf.format(calendar1.getTime())+" 00:00:01";  
	     // System.out.println(sdf.format(calendar2.getTime()));// last Sunday  
	     String lastEndDate = sdf.format(calendar2.getTime())+" 23:59:59"; 
	     return lastBeginDate + "," + lastEndDate;  
	}  
	
	
	 /** 
	* 根据当前日期获得所在周的日期区间（周一和周日日期） 
	*  
	* @return 
	* @author zhaoxuepu 
	* @throws ParseException 
	*/  
	public static String getTimeInterval() {  
		 Date date = new Date();
	     Calendar cal = Calendar.getInstance();  
	     cal.setTime(date);  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	     int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	     if (1 == dayWeek) {  
	        cal.add(Calendar.DAY_OF_MONTH, -1);  
	     }  
	     // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
	     // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	     cal.setFirstDayOfWeek(Calendar.MONDAY);  
	     // 获得当前日期是一个星期的第几天  
	     int day = cal.get(Calendar.DAY_OF_WEEK);  
	     // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	     cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
	     String imptimeBegin = sdf.format(cal.getTime())+" 00:00:01";  
	     // System.out.println("所在周星期一的日期：" + imptimeBegin);  
	     cal.add(Calendar.DATE, 6);  
	     String imptimeEnd = sdf.format(cal.getTime())+" 23:59:59";
	     // System.out.println("所在周星期日的日期：" + imptimeEnd);  
	     return imptimeBegin + "," + imptimeEnd;  
	}  
	
	public static String[] getResultValue(int order){
		String[] str = new String[2];
		if (order == 1){
			str[0] = EnumType.LotteryResultBan.Result_banjiu_01.ID;
			str[1] = EnumType.LotteryResultBan.Result_banjiu_01.NAME;
		}else if (order ==2){
			str[0] = EnumType.LotteryResultBan.Result_banjiu_02.ID;
			str[1] = EnumType.LotteryResultBan.Result_banjiu_02.NAME;
		}else if (order ==3){
			str[0] = EnumType.LotteryResultBan.Result_banjiu_03.ID;
			str[1] = EnumType.LotteryResultBan.Result_banjiu_03.NAME;
		}else if (order ==4){
			str[0] = EnumType.LotteryResultBan.Result_banjiu_04.ID;
			str[1] = EnumType.LotteryResultBan.Result_banjiu_04.NAME;
		}else if (order ==5){
			str[0] = EnumType.LotteryResultBan.Result_banjiu_05.ID;
			str[1] = EnumType.LotteryResultBan.Result_banjiu_05.NAME;
		}
		return str;
	}
	
	public static String getCurrentTime() {  
		 Date date = new Date();
	     Calendar cal = Calendar.getInstance();  
	     cal.setTime(date);  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	     String imptimeBegin = sdf.format(cal.getTime())+" 00:00:01";  
	     // System.out.println("所在周星期一的日期：" + imptimeBegin);  
	    
	     String imptimeEnd = sdf.format(cal.getTime())+ " 23:59:59";  
	     // System.out.println("所在周星期日的日期：" + imptimeEnd);  
	     return imptimeBegin + "," + imptimeEnd;  
	}
	
	public static String getCurrentMills(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    String sTime = sdf.format(date);
	    return sTime;
	}
	
	public static Date getChangeDate(Integer seconds){
		Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.SECOND, seconds);
	    Date date = calendar.getTime();
	    return date;
	}
	
	public static String getArrayString(String str){
		String res = "";
		for (int i = 0; i < str.length(); i++) {
		    res += ("," + str.charAt(i));
		}
		return res.substring(1, res.length());
	}
	
	public static Date getStringToDate(String dateString){
		 Date date = new Date();
		 dateString = dateString.replaceAll("/", "-");
	    try  
	    {  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        date = sdf.parse(dateString);  
	    }  
	    catch (ParseException e)  
	    {  
	        System.out.println(e.getMessage());  
	    }  
	    return date;
	}
	
	public static Date getStringToMillon(String dateString,Integer seconds){
		 Date date = new Date();
		 dateString = dateString.replaceAll("/", "-");
	    try  
	    {  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        date = sdf.parse(dateString);  
	        long times = date.getTime() - seconds*1000;
	        date = new Date(times);
	        //dateStr = sdf.format(date);
	    }  
	    catch (ParseException e)  
	    {  
	        System.out.println(e.getMessage());  
	    }  
	    return date;
	}
	
	public static String[] getStringResult(String[] results,String orders,String type){
		String[] ors = orders.split(",");
		String result = "";
		int count = 0;
		int mod = 0;
		for (int i =0;i<results.length;i++){
			for (int j=0;j<ors.length;j++){
				if (i==Integer.valueOf(ors[j])-1){
					//System.out.println("..."+i+"..."+j+".."+results[i]);
					result = result + results[i]+",";
					count = count + Integer.valueOf(results[i]);
				
					break;
				}
			}
		}
	    String [] value = new String[8];
	    String [] strs = new String[3];
	    String [] order = new String[3];
	    value[0] = result.substring(0,result.length()-1);
	    value[1] = String.valueOf(count);
		mod = count%10;
		//value[2]=String.valueOf(mod);
		order = getOrdeNum(value[0],type);
		value[2] = order[0];
		value[3] = order[1];
		value[4] = order[2];
		strs = getStringResultNo(value[0],mod,type);
		value[5] = strs[0];
		value[6] = strs[1];
		value[7] = strs[2];
	    return value;
	}
	
	public static String[] getOrdeNum(String result,String type){
		String[] str = result.split(",");
		String[] stemp = new String[3];

		String t ="";
		if (type.equals("01")&&str.length==3){
			stemp[0] = str[0];
			stemp[1] = str[1];
			stemp[2] = str[2];
			if(Integer.valueOf(stemp[0])<Integer.valueOf(stemp[1])){
				t=stemp[0];
				stemp[0]=stemp[1];
				stemp[1]=t;
			}//取得a b 大的
			if(Integer.valueOf(stemp[0])<Integer.valueOf(stemp[2])){
				t=stemp[0];
				stemp[0]=stemp[2];
				stemp[2]=t;
		    }
			if(Integer.valueOf(stemp[1])<Integer.valueOf(stemp[2])){
				t=stemp[1];
				stemp[1]=stemp[2];
				stemp[2]=t;
			}
		} else if (type.equals("02")&&str.length==2){
			stemp[0] = str[0];
			stemp[1] = str[1];
			stemp[2] = null;
			if(Integer.valueOf(stemp[0])<Integer.valueOf(stemp[1])){
				t = stemp[0];
				stemp[0] = stemp[1];
				stemp[1] = t;
			}
		
		}
		return stemp;
	}
	
	public static String[] getStringResultNo(String result,int mod,String type){
		String[] str = new String[3];
		if (type.equals("01")){
		    String[] re = result.split(",");
			if (re[0].equals(re[1])&&re[0].equals(re[2])&&re[1].equals(re[2])){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_bz.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_bz.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_bz.RATIO);
			    return str;
			}
			if (mod==0){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_nn.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_nn.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_nn.RATIO);
			    return str;
			}
			if (Integer.valueOf(re[2])>Integer.valueOf(re[1])&&Integer.valueOf(re[1])>Integer.valueOf(re[0])){
				if ((Integer.valueOf(re[2])==Integer.valueOf(re[1])+1)&&(Integer.valueOf(re[1])==Integer.valueOf(re[0])+1)){
				    str[0] = EnumType.LotteryResultNiu.Result_niuniu_sz.ID;
				    str[1] = EnumType.LotteryResultNiu.Result_niuniu_sz.NAME;
				    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_sz.RATIO);
			        return str;
				}
			}
			if (Integer.valueOf(re[0])>Integer.valueOf(re[1])&&Integer.valueOf(re[1])>Integer.valueOf(re[2])){
				if ((Integer.valueOf(re[0])==Integer.valueOf(re[1])+1)&&(Integer.valueOf(re[1])==Integer.valueOf(re[2])+1)){
				    str[0] = EnumType.LotteryResultNiu.Result_niuniu_ds.ID;
				    str[1] = EnumType.LotteryResultNiu.Result_niuniu_ds.NAME;
				    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_ds.RATIO);
				    return str;
				}
			}
			//System.out.println("0--"+re[0]+".."+re[1]+".."+re[2]);
			if (re[0].equals(re[1])||re[0].equals(re[2])||re[1].equals(re[2])){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_dz.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_dz.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_dz.RATIO);
			    return str;
			}
			if (mod==1){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n1.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n1.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n1.RATIO);
			    return str;
			}
			if (mod==2){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n2.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n2.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n2.RATIO);
			    return str;
			}
			if (mod==3){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n3.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n3.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n3.RATIO);
			    return str;
			}
			if (mod==4){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n4.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n4.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n4.RATIO);
			    return str;
			}
			if (mod==5){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n5.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n5.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n5.RATIO);
			    return str;
			}
			if (mod==6){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n6.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n6.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n6.RATIO);
			    return str;
			}
			if (mod==7){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n7.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n7.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n7.RATIO);
			    return str;
			}
			if (mod==8){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n8.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n8.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n8.RATIO);
			    return str;
			}
			if (mod==9){
			    str[0] = EnumType.LotteryResultNiu.Result_niuniu_n9.ID;
			    str[1] = EnumType.LotteryResultNiu.Result_niuniu_n9.NAME;
			    str[2] = String.valueOf(EnumType.LotteryResultNiu.Result_niuniu_n9.RATIO);
			    return str;
			}
		}else if (type.equals("02")){
			str[0] = String.valueOf(9-mod);
			str[1] = String.valueOf(mod)+"点";
			str[2] = "1";
		}
        return str;
	}
	
	 public static int RamdomNum(){    
	        Date date = new Date();    
	        long timeMill = date.getTime();    
	        System.out.println(timeMill);    
	        Random rand = new Random(timeMill);    
	       /*
	        for(int i = 0; i < 20; i++)    
	        {    
	            System.out.println(rand.nextInt(50));    
	        }
	        */
	        return rand.nextInt(50);
	    }    
	 
	   /**
      * 随机产生几位字符串：例：maxLength=3,则结果可能是 aAz
      * @param maxLength 传入数必须是正数。
      */
     public static String produceString(int maxLength){
             String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
             return doProduce(maxLength, source);
     }
	
     /**
      * 生产结果
      */
     private static String doProduce(int maxLength, String source) {
             StringBuffer sb = new StringBuffer(100);
             for (int i = 0; i < maxLength; i++) {
                     final int number =  produceNumber(source.length());
                     sb.append(source.charAt(number));
             }
             return sb.toString();
     }
     
     /**
      * 随机产生几位数字：例：maxLength=3,则结果可能是 012
      */
     public static final int produceNumber(int maxLength){
             Random random = new Random();
             return random.nextInt(maxLength);
     }
     
    	 
     /**
      * 判断给定时间在否在给定两个时间之前
      */
     public static boolean dateRange(){
    	 Calendar cal = Calendar.getInstance();// 当前日期
    	 int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
    	 int minute = cal.get(Calendar.MINUTE);// 获取分钟
    	
    	 if (hour >= 22 || hour < 2)
    	 {
    	   // 晚上22点（含）到凌晨2点（不含）之间
    		 return true;
    	 }
    	 else
    	 {
    		 return false;
    	   // 上述之外的时间段
    	 }
     }
     
     /**
      * 判断比较
      */
     public static String[][] doHandle(String[][] str){
    	 String[][] d = new String[2][5];
    	 d[0][0] = "";
    	 d[0][1] = "";
    	 d[0][2] = "";
    	 d[0][3]="";
    	 d[0][4] = "";
         str[0][0]="4000";
         str[0][1]="4000";
         str[0][2]="4000";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[1][0]="5000";
         str[1][1]="5000";
         str[1][2]="5000";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="4";
         str[2][0]="8000";
         str[2][1]="8000";
         str[2][2]="8000";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="2";
         String[][] str1 = str;
         int i = 0;
         int j=str.length-1;
         System.out.println("7---"+j);
         int tmp = Integer.valueOf(str[0][1]);
         while (i<j){
        	 if (tmp>=Integer.valueOf(str[j][1])){
        		 tmp = tmp - Integer.valueOf(str[j][1]);
        		 str[j][1] = "0";
        		 str[j][5] = "1";
        		 j--;
        		 if(i==j){
        			 str[i][1] = String.valueOf(Integer.valueOf(str[i][1]) - tmp);
        		 }
        			System.out.println("98---"+tmp);
        	 }else{
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][1]) - tmp));
        		 str[j][5] = "1";
        		 i++;
        		 tmp = Integer.valueOf(str[i][1]);
        		//System.out.println("9---"+tmp);
        	 }
         }
         /*
         for (i=0;i<=str.length-1;i++){
         	System.out.println("1----"+i+".."+str[i][1]);
         }
         */
         for (i=str.length-1;i>=j;i--){
        	 if(str[i][5].equals("1")){
        		 str[i][1] = String.valueOf(Integer.valueOf(str[i][1])  - Integer.valueOf(str[i][0]));
        		 // System.out.println("0----0-"+i+".."+str1[i][1]);
        	 }
         }
         /*
        for (i=0;i<=str1.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][1]);
        }
         */
         return str;
         
     }
     
     /**
      * 判断比较,无庄逻辑处理
      */
     public static String[][] doNoBankerHandle(String[][] str){
    		
   
         str[0][0]="1000";
         str[0][1]="0";
         str[0][2]="0";
         str[0][3]="1027";
         str[0][4]="1001";
         str[0][5]="0";  //对于赔付用户，是否需要显示负值
         str[0][6]="3";  //倍数
         str[0][7]="0";  //对于赔付用户，倍率是否更新
         str[0][8]="6点";
         str[0][9]="5";  //noid
         str[0][10]="3"; //排名
         str[1][0]="1000";
         str[1][1]="0";
         str[1][2]="0";
         str[1][3]="1020";
         str[1][4]="1002";
         str[1][5]="0";
         str[1][6]="3";
         str[1][7]="0";
         str[1][8]="3点";
         str[1][9]="5";
         str[1][10]="3";
         str[2][0]="1000";
         str[2][1]="0";
         str[2][2]="0";
         str[2][3]="1013";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="2";
         str[2][7]="0";
         str[2][8]="3点";
         str[2][9]="8";
         str[2][10]="4";
         str[3][0]="1000";
         str[3][1]="0";
         str[3][2]="0";
         str[3][3]="1019";
         str[3][4]="1001";
         str[3][5]="0";
         str[3][6]="2";
         str[3][7]="0";
         str[3][8]="3点";
         str[3][9]="8";
         str[3][10]="4";
         str[4][0]="1000";
         str[4][1]="0";
         str[4][2]="0";
         str[4][3]="1019";
         str[4][4]="1001";
         str[4][5]="0";
         str[4][6]="1";
         str[4][7]="0";
         str[4][8]="3点";
         str[4][9]="7";
         str[4][10]="5";
         str[5][0]="1000";
         str[5][1]="0";
         str[5][2]="0";
         str[5][3]="1019";
         str[5][4]="1001";
         str[5][5]="0";
         str[5][6]="1";
         str[5][7]="0";
         str[5][8]="3点";
         str[5][9]="7";
         str[5][10]="5";
         str[6][0]="1000";
         str[6][1]="0";
         str[6][2]="0";
         str[6][3]="1019";
         str[6][4]="1001";
         str[6][5]="0";
         str[6][6]="1";
         str[6][7]="0";
         str[6][8]="3点";
         str[6][9]="6";
         str[6][10]="7";
         str[7][0]="1000";
         str[7][1]="0";
         str[7][2]="0";
         str[7][3]="1019";
         str[7][4]="1001";
         str[7][5]="0";
         str[7][6]="1";
         str[7][7]="0";
         str[7][8]="3点";
         str[7][9]="6";
         str[7][10]="7";
      
         int i = 0;
         int j=str.length-1;
        // System.out.println("j--------------"+j);
        // System.out.println("7---"+j);
         if (str[0][9].equals(str[j][9])||str[0][10].equals(str[j][10]))
        	 return str;
         
         str[0][1] = String.valueOf(Integer.valueOf(str[0][0])*Integer.valueOf(str[0][6]));
         str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[0][6]));
         str[j][6] = str[0][6];
         str[j][7] = String.valueOf(1);
         int tmp = Integer.valueOf(str[0][1]);
         //System.out.println("7ddd---"+i+".."+j);
         while ((i<j) && (!(str[i][9].equals(str[j][9])||(str[i][10].equals(str[j][10]))))){
        	 if (tmp>=Integer.valueOf(str[j][1])){
        		 tmp = tmp - Integer.valueOf(str[j][1]);
        		 System.out.println("j1--------------"+i+"..扣除。"+str[j][3]+"..-"+str[j][1]+"..."+j+".."+str[i][3]+".."+str[j][1]+".."+tmp);
        		 
        		 str[j][1] = "0";
        		 str[j][5] = "1";
        		
        		// System.out.println("tmp1--------------"+tmp);
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 j--;
        		 if(i==j){
        			 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]) - tmp);
        			 str[i][2] = str[i][1];
        		 }else{
        			 if (!(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10]))){
        			     str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[i][6]));
        			    // i++;
        			     System.out.println("j2--34------------"+i+".."+j+".."+str[j][1]);
        			 }
        			 else{
            			 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]) - tmp);
            			 str[i][2] = str[i][1];
            			 System.out.println("i1--------------"+i+".."+j+".."+str[i][2]+"..."+str[j][2]);
            			 //System.out.println("stri2--------------"+str[i][2]+".."+tmp);
            		 }
        		 }
        	 }else{
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][1]) - tmp));
        		 System.out.println("i12--------------"+i+".."+str[j][1]+".."+str[j][3]+"..-"+tmp+".."+str[i][3]+"..+"+tmp);
        		
        		// System.out.println("strj1--------------"+str[j][1]);
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 i++;
        		 if(i < j){
        			 //System.out.println("indexi--------------"+i);
        			// System.out.println("indexj--------------"+j);
        			// System.out.println("tablei--------------"+str[i][9]);
        			// System.out.println("tablej--------------"+str[j][9]);
        			 if (!(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10]))){
		        		 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])); 		
		        		 tmp = Integer.valueOf(str[i][1]);
		        		System.out.println("i21--233------------"+i+".."+j+".."+tmp);
		        		// System.out.println("stri1--------------"+str[i][1]);
            			// System.out.println("tmp2--------------"+tmp);
        			 }
        		 }
        		//System.out.println("9---"+tmp);
        	 }
         }
        // System.out.println("7ddd-sdsd--"+i+".."+j);
         for (i=str.length-1;i>=j;i--){
        	 if(str[i][5].equals("1")){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][1])  - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		 
        		  System.out.println("0----0-"+i+".."+str[i][2]);
        	 }
         }
       //  System.out.println("12----------"+j);
         
        for (i=0;i<j;i++){
        	 str[i][2] =  str[i][1];
        	 System.out.println("12----------"+str[i][2]);
        }
        
        for (i=0;i<str.length;i++){
        	    str[i][1] =  str[i][0]; 
       	    System.out.println("98-----"+str[i][1]);
       	    
       	    
       }
 
       for (int w = 0;w<str.length;w++)
       System.out.println("90------------"+str[w][0]+"..."+str[w][1]+".."+str[w][2]);
       
         return str;
        
     }
     
     
     /**
      * 庄相等
      */
     public synchronized static Map<Integer, Object> doBankerHandleSameEqual(int gains,int count,int values,int times,String[][] str){
    	  Map<Integer, Object> map = new HashMap<Integer, Object>();
              map.put(1, gains);
              map.put(2, count);
              map.put(3, values);
              map.put(4, str); 
         return map;
     }
     
     
     /**
      * 庄判断比较,输庄
      */
     public static Map<Integer, Object> doBankerHandleLess(int gains,int count,int values,int times,String[][] str){
       /*
    	 str[0][0]="1000";
         str[0][1]="1000";
         str[0][2]="1000";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[0][9]="1";
         str[0][10]="3";
         str[1][0]="2000";
         str[1][1]="2000";
         str[1][2]="2000";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="5";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[1][9]="1";
         str[1][10]="3";
         str[2][0]="3000";
         str[2][1]="3000";
         str[2][2]="3000";
         str[2][3]="1001";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="1";
         str[2][7]="0";
         str[2][9]="1";
         str[2][10]="3";
        */
         int i = 0;
         int j=str.length-1;
         String[][] strtemp = new String[str.length][11];
         Map<Integer, Object> map = new HashMap<Integer, Object>();
        
         int base = values-gains-count;
         if (base > count)
        	 base = count;
         System.out.println("7---"+j+".."+base);
         int tempgain = base;
         
         if (base == 0){
             map.put(1, gains);
             map.put(2, count);
             map.put(3, values);
             map.put(4, str); 
         }
         
         for (int m = 0;m<str.length;m++){
         	strtemp[j-m][0]=str[m][0];
         	strtemp[j-m][1]=str[m][1];
         	strtemp[j-m][2]=str[m][2];
         	strtemp[j-m][3]=str[m][3];
         	strtemp[j-m][4]=str[m][4];
         	strtemp[j-m][5]=str[m][5];
         	strtemp[j-m][6]=str[m][6];
         	strtemp[j-m][7]=str[m][7];
         	strtemp[j-m][8]=str[m][8];
         	strtemp[j-m][9]=str[m][9];
         	strtemp[j-m][10]=str[m][10];
         }
         str = strtemp;
         for (;j>=0;j--){
        	 System.out.println("90---"+str[j][1]+".."+times+".."+base);
        	 if (Integer.valueOf(str[j][1])*times<base){
        		 base = base - Integer.valueOf(str[j][0])*times;
        		 str[j][1] = "0";
        		 System.out.println("09----"+str[j][3]+"..-"+(Integer.valueOf(str[j][0])*times)+"...庄"+"...+"+(Integer.valueOf(str[j][0])*times)+".."+base);
        	 }else{
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][0])*times - base));
        		 System.out.println("09-34---庄"+"..+"+base+"..."+str[j][3]+"...-"+base);
                 
        		 base = 0;
        		 break;
        	 }
         }
         for (i=0;i<=str.length-1;i++){
         	if (i<j){
         		str[i][2] = String.valueOf(0);
         		str[i][1] = str[i][0];
         	}
         	else{
         		str[i][2] = String.valueOf(Integer.valueOf(str[i][1]) - Integer.valueOf(str[i][0])*times);
         		str[i][1] = str[i][0];
         	    //count = count - Integer.valueOf(str[i][2]);
         	}
         }
         gains = gains+tempgain-base;
         System.out.println("gains="+gains+"..count="+count+"..values="+values);
       
        for (i=0;i<=str.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][2]+".."+str[i][3]);
        }
        
       // System.out.println("0----"+count);
        map.put(1, gains);
        map.put(2, count);
        map.put(3, values);
        map.put(4, str); 
        return map;
         
     }
     
     public static int doCompareCount(int gains,int count,int values){
    	 int base = 0;
    	 if ((values - gains - count)>count){
    		 base = count;
    	 }
    	 else 
    		 base = values - gains -count;
    	 return base;
     } 
     
     /**
      * 庄判断比较,赢庄
      */
     public static Map<Integer, Object> doBankerHandleMore(int gains,int count,int values,int times,String[][] str){
         
    	/*
        // count  =120;
    	 str[0][0]="2000";
         str[0][1]="2000";
         str[0][2]="0";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[0][9]="牛牛";
         str[1][0]="3000";
         str[1][1]="3000";
         str[1][2]="0";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="5";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[2][0]="500";
         str[2][1]="500";
         str[2][2]="0";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="5";
         str[2][7]="0";
         /*
         str[3][0]="1000";
         str[3][1]="1000";
         str[3][2]="0";
         str[3][3]="1011";
         str[3][4]="1001";
         str[3][5]="0";
         str[3][6]="1";
         str[3][7]="0";
         */
  
    	 
         int i = 0;
         int j=str.length-1;
         Map<Integer, Object> map = new HashMap<Integer, Object>();
         
         int base = doCompareCount(gains,count,values);
         System.out.println("7---"+base);
         for (i=0;i<=j;i++){
        	 if (base>(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]))){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		 base = base - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        		// count = count - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        		 System.out.println("ying-1---"+str[i][3]+"..+"+Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])+"..庄"+"..-"+Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		 
        	 }else{
        		 str[i][2] = String.valueOf(base);
        		// count = 0;
        		 System.out.println("ying-2---"+str[i][3]+"..+"+base+"..庄"+"..-"+base);
        		 
        		 base = 0;
                 break;
        	 }
         }
         System.out.println("gq--"+count);
         for (i=0;i<=str.length-1;i++){
        	// System.out.println("800-"+str[i][2]);
        	 count = count - Integer.valueOf(str[i][2]);
         }
         /*
         System.out.println("gains="+gains+"..count="+count+"..values="+values);
        
        for (i=0;i<=str.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][2]+"..");
        }
       */
        map.put(1, gains);
        map.put(2, count);
        map.put(3, values);
        map.put(4, str);
        
        return map;
         
     }
     
     
     /**
      * 内部收益比较，分配
      */
     public static String[][] doBankerHandleEqual(int count,String[][] str){
       /*
    	 str[0][0]="1000";
         str[0][1]="1000";
         str[0][2]="0";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="1";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[1][0]="2000";
         str[1][1]="2000";
         str[1][2]="0";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="1";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[2][0]="20000";
         str[2][1]="20000";
         str[2][2]="0";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="1";
         str[2][6]="1";
         str[2][7]="0"; 
         str[2][8]="牛牛"; 
         str[3][0]="300";
         str[3][1]="300";
         str[3][2]="0";
         str[3][3]="1011";
         str[3][4]="1001";
         str[3][5]="1";
         str[3][6]="1";
         str[3][7]="0"; 
         str[3][8]="牛牛"; 
         */
         int i = 0;
         int j=str.length-1;
         System.out.println("7---"+j);
         int base = Math.abs(count);
         /*
         if (base<0){
        	 String[][] str1 = new String[str.length][10];
        	 int t1 = j;
        	 for (int m =0;m<=j;m++){
        		 str1[m][0] = str[t1][0]; 
        		 str1[m][1] = str[t1][1]; 
        		 str1[m][2] = str[t1][2]; 
        		 str1[m][3] = str[t1][3]; 
        		 str1[m][4] = str[t1][4]; 
        		 str1[m][5] = str[t1][5]; 
        		 str1[m][6] = str[t1][6];
        		 str1[m][7] = str[t1][7]; 
        		 str1[m][8] = str[t1][8]; 
        		 str1[m][9] = str[t1][9]; 
        		 t1--;
        	 }
        	 str = str1;
         }
         */
         for (i=0;i<=j;i++){
        	 if (base>(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]))){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		 base = base - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        		// count = count - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        	 }else{
        		 str[i][2] = String.valueOf(base);
        		// count = 0;
                 break;
        	 }
         }
         if (count<0){
        	 for (i=0;i<=j;i++){
        	     str[i][2] = String.valueOf(0-Integer.valueOf(str[i][2]));
        		// System.out.println("0--45-6-"+i+".."+str[i][2]+"..");  
        	 }
         }
         /*
         for (i=0;i<=str.length-1;i++){
        	 System.out.println("0---6-"+i+".."+str[i][2]+"..");  
         }
  
     
        for (i=0;i<=str.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][2]+"..");
        }
        */
        //System.out.println("0----"+count);
       
         return str;
     }
     
     
     /**
      * 判断比较,无庄逻辑处理,备份
      */
     public static String[][] doNoBankerHandle1_BF1(String[][] str){
    	/*
         str[0][0]="50";
         str[0][1]="50";
         str[0][2]="50";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[1][0]="2000";
         str[1][1]="2000";
         str[1][2]="2000";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="5";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[2][0]="2000";
         str[2][1]="2000";
         str[2][2]="2000";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="1";
         str[2][7]="0";
         str[2][8]="牛牛";
         str[3][0]="700";
         str[3][1]="700";
         str[3][2]="700";
         str[3][3]="1011";
         str[3][4]="1001";
         str[3][5]="0";
         str[3][6]="1";
         str[3][7]="0";
         str[3][8]="牛牛";
         */
         int i = 0;
         int j=str.length-1;
        // System.out.println("7---"+j);
         str[0][1] = String.valueOf(Integer.valueOf(str[0][0])*Integer.valueOf(str[0][6]));
         str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[0][6]));
         str[j][6] = str[0][6];
         str[j][7] = String.valueOf(1);
         int tmp = Integer.valueOf(str[0][1]);
         //System.out.println("7ddd---"+i+".."+j);
         while (i<j){
        	 if (tmp>=Integer.valueOf(str[j][1])){
        		 tmp = tmp - Integer.valueOf(str[j][1]);
        		 str[j][1] = "0";
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 j--;
        		 if(i==j){
        			 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]) - tmp);
        			 str[i][2] = str[i][1];
        		 }else{
        			 str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[i][6]));
        		 }
        	 }else{
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][1]) - tmp));
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 i++;
        		 if(i < j){
	        		 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])); 		
	        		 tmp = Integer.valueOf(str[i][1]);
        		 }
        		//System.out.println("9---"+tmp);
        	 }
         }
        // System.out.println("7ddd-sdsd--"+i+".."+j);
         for (i=str.length-1;i>=j;i--){
        	 if(str[i][5].equals("1")){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][1])  - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		  //System.out.println("0----0-"+i+".."+str1[i][1]);
        	 }
         }
        for (i=0;i<j;i++){
        	 str[i][2] =  str[i][1] ;
        	//System.out.println("0----"+i+".."+str[i][1]);
        }

         return str;
         
     }
     
     /**
      * 庄判断比较,赢庄备份
      */
     public static Map<Integer, Object> doBankerHandleMore_BF(int count,int times,String[][] str){
         /*
         count  =120;
    	 str[0][0]="500";
         str[0][1]="500";
         str[0][2]="0";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[0][9]="牛牛";
         str[1][0]="300";
         str[1][1]="300";
         str[1][2]="0";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="5";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[2][0]="400";
         str[2][1]="400";
         str[2][2]="0";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="1";
         str[2][7]="0";
         str[3][0]="1000";
         str[3][1]="1000";
         str[3][2]="0";
         str[3][3]="1011";
         str[3][4]="1001";
         str[3][5]="0";
         str[3][6]="1";
         str[3][7]="0";
         
       */
         int i = 0;
         int j=str.length-1;
         Map<Integer, Object> map = new HashMap<Integer, Object>();
         System.out.println("7---"+j);
         int base = count;
         for (i=0;i<=j;i++){
        	 if (base>(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]))){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		 base = base - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        		 count = count - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]);
        	 }else{
        		 str[i][2] = String.valueOf(base);
        		 count = 0;
                 break;
        	 }
         }
        /*
         for (i=0;i<=str.length-1;i++){
        	 System.out.println("0----"+i+".."+str[i][2]+"..");
         }
   */
        
        for (i=0;i<=str.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][2]+"..");
        }
       
        System.out.println("0----"+count);
        map.put(1, count);
        map.put(2, str);
        return map;
         
     } 
     /**
      * 庄判断比较,输庄备份
      */
     public static Map<Integer, Object> doBankerHandleLess_BF(int count,int times,String[][] str){
         /*
    	 str[0][0]="100";
         str[0][1]="100";
         str[0][2]="100";
         str[0][3]="1019";
         str[0][4]="1001";
         str[0][5]="0";
         str[0][6]="5";
         str[0][7]="0";
         str[0][8]="牛牛";
         str[1][0]="200";
         str[1][1]="200";
         str[1][2]="200";
         str[1][3]="1020";
         str[1][4]="1001";
         str[1][5]="0";
         str[1][6]="5";
         str[1][7]="0";
         str[1][8]="牛牛";
         str[2][0]="5000";
         str[2][1]="5000";
         str[2][2]="5000";
         str[2][3]="1011";
         str[2][4]="1001";
         str[2][5]="0";
         str[2][6]="1";
         str[2][7]="0";
         String[][] str1 = str;
         */
         int i = 0;
         int j=str.length-1;
         Map<Integer, Object> map = new HashMap<Integer, Object>();
         System.out.println("7---"+j);
         int base = count;
         for (;j>=0;j--){
        	 //System.out.println("90---"+str[j][1]);
        	 if (Integer.valueOf(str[j][1])*times<base){
        		 base = base - Integer.valueOf(str[j][0])*times;
        		 str[j][1] = "0";
        	 }else{
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][0])*times - base));
                 break;
        	 }
         }
         for (i=0;i<=str.length-1;i++){
         	if (i<j)
         		str[i][2] = String.valueOf(0);
         	else{
         		str[i][2] = String.valueOf(Integer.valueOf(str[i][1]) - Integer.valueOf(str[i][0])*times);
         	    count = count - Integer.valueOf(str[i][2]);
         	}
         }
  
         /*
        for (i=0;i<=str.length-1;i++){
        	System.out.println("0----"+i+".."+str[i][2]+"..");
        }
        */
        System.out.println("0----"+count);
        map.put(1, count);
        map.put(2, str);
        return map;
         
     }
     public static long getCompareMin(Date end,Date begin){
    	   //SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	   //Date begin=dfs.parse("2004-01-02 11:30:24");
    	  // Date end = dfs.parse("2004-03-26 13:31:40");
    	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
    	   long min=between/60;
		   return min;
    	 
     }
     
     /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
     public static Date StrToDate(String str) {
       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
         date = format.parse(str);
        } catch (ParseException e) {
         e.printStackTrace();
        }
        return date;
     }
     
     public static String dateAddMin(int min) throws Exception{
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 Calendar nowTime = Calendar.getInstance();
    	 nowTime.add(Calendar.MINUTE, min); 
    	 nowTime.add(Calendar.SECOND, 10); 
    	 String sTime = sdf.format(nowTime.getTime());
    	 System.out.println(new Date());
    	 return sTime;
     }
     
     /**
      * 判断比较,无庄逻辑处理,备份
      */
     public static String[][] doNoBankerHandle1_BFYNL(String[][] str){

         str[0][0]="100";
         str[0][1]="0";
         str[0][2]="0";
         str[0][3]="1027";
         str[0][4]="1840";
         str[0][5]="0";  //对于赔付用户，是否需要显示负值
         str[0][6]="3";  //倍数
         str[0][7]="0";  //对于赔付用户，倍率是否更新
         str[0][8]="6点";
         str[0][9]="4";  //noid桌号
         str[0][10]="4"; //排名
         str[1][0]="1000";
         str[1][1]="0";
         str[1][2]="0";
         str[1][3]="1020";
         str[1][4]="1842";
         str[1][5]="0";
         str[1][6]="2";
         str[1][7]="0";
         str[1][8]="3点";
         str[1][9]="2";
         str[1][10]="5";
         str[2][0]="50";
         str[2][1]="0";
         str[2][2]="0";
         str[2][3]="1013";
         str[2][4]="1841";
         str[2][5]="0";
         str[2][6]="2";
         str[2][7]="0";
         str[2][8]="3点";
         str[2][9]="2";
         str[2][10]="5";
         str[3][0]="100";
         str[3][1]="0";
         str[3][2]="0";
         str[3][3]="1019";
         str[3][4]="1008";
         str[3][5]="0";
         str[3][6]="1";
         str[3][7]="0";
         str[3][8]="3点";
         str[3][9]="8";
         str[3][10]="7";
         str[4][0]="100";
         str[4][1]="0";
         str[4][2]="0";
         str[4][3]="1019";
         str[4][4]="1008";
         str[4][5]="0";
         str[4][6]="1";
         str[4][7]="0";
         str[4][8]="3点";
         str[4][9]="8";
         str[4][10]="7";
       
         str[5][0]="1000";
         str[5][1]="0";
         str[5][2]="0";
         str[5][3]="1019";
         str[5][4]="1010";
         str[5][5]="0";
         str[5][6]="1";
         str[5][7]="0";
         str[5][8]="3点";
         str[5][9]="3";
         str[5][10]="7";
       
         str[6][0]="1000";
         str[6][1]="0";
         str[6][2]="0";
         str[6][3]="1019";
         str[6][4]="1001";
         str[6][5]="0";
         str[6][6]="1";
         str[6][7]="0";
         str[6][8]="3点";
         str[6][9]="5";
         str[6][10]="9";
         /*
         str[7][0]="100";
         str[7][1]="0";
         str[7][2]="0";
         str[7][3]="1019";
         str[7][4]="1001";
         str[7][5]="0";
         str[7][6]="1";
         str[7][7]="0";
         str[7][8]="3点";
         str[7][9]="3";
         str[7][10]="10";
         /*
         str[8][0]="1000";
         str[8][1]="0";
         str[8][2]="0";
         str[8][3]="1019";
         str[8][4]="1001";
         str[8][5]="0";
         str[8][6]="3";
         str[8][7]="0";
         str[8][8]="3点";
         str[8][9]="5";
         str[8][10]="5";
         */
         int i = 0;
         int j=str.length-1;
        // System.out.println("7---"+j);
         str[0][1] = String.valueOf(Integer.valueOf(str[0][0])*Integer.valueOf(str[0][6]));
         str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[0][6]));
         str[j][6] = str[0][6];
         str[j][7] = String.valueOf(1);
         int tmp = Integer.valueOf(str[0][1]);
         //System.out.println("7ddd---"+i+".."+j);
         while (i<j){
  		    if(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10])){
 			    break;
 			}

        	 if (tmp==Integer.valueOf(str[j][1])){
        		 tmp = 0;
        		 str[j][1] = "0";
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 
        		 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])); 
                 
        		 j--;
                 i++;
                       		 
        		 if(i < j){
        			 if(!(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10]))){
        				 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])); 
    	        		 tmp = Integer.valueOf(str[i][1]);
            			 str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[i][6]));
        			 }       			
        		 }else if(i == j){
        			 str[i][1] = "0"; 		
        		 }else{ 
        			 j = i;
        		 }
        	 }
        	 else if (tmp>Integer.valueOf(str[j][1])){
        		 tmp = tmp - Integer.valueOf(str[j][1]);
        		 str[j][1] = "0";
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        		 }
        		 j--;
        		 
        		 if(i==j){
        			 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]) - tmp);
        			 str[i][2] = str[i][1];
        		 }else if(i < j){
        			 if(!(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10]))){
        			     str[j][1] = String.valueOf(Integer.valueOf(str[j][0])*Integer.valueOf(str[i][6]));
        			 }
        			 else{
        				 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]) - tmp);
            			 str[i][2] = str[i][1];
        			 }
        		 }
        	 }else{
        		 System.out.println("9---"+tmp);
        		 str[j][1] = String.valueOf((Integer.valueOf(str[j][1]) - tmp));
        		 str[j][5] = "1";
        		 if (Integer.valueOf(str[j][7]) == 0){
        			 str[j][6] = str[i][6];
        			 str[j][7] = String.valueOf(1);
        			//System.out.println("9---"+j);
        		 }
        		 i++;
        		 if(i < j){
        			 if(!(str[i][9].equals(str[j][9])||str[i][10].equals(str[j][10]))){
		        		 str[i][1] = String.valueOf(Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6])); 		
		        		 tmp = Integer.valueOf(str[i][1]);
	        		 }
        		 }
        		//System.out.println("9---"+tmp);
        	 }
         }
        // System.out.println("7ddd-sdsd--"+i+".."+j);
         for (i=str.length-1;i>=j;i--){
        	 if(str[i][5].equals("1")){
        		 str[i][2] = String.valueOf(Integer.valueOf(str[i][1])  - Integer.valueOf(str[i][0])*Integer.valueOf(str[i][6]));
        		  System.out.println("0----0-"+i+".."+str[i][1]);
        	 }
         }
        for (i=0;i<j;i++){
        	 str[i][2] =  str[i][1] ;
        	System.out.println("0----"+i+".."+str[i][1]);
        }

       for (int w = 0;w<str.length;w++)
       System.out.println("90------------"+str[w][0]+"..."+str[w][1]+".."+str[w][2]);
   
       return str;
        
     }
     
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
		//Date[] param1 = CommonUtils.getDateTime(param.getStartDate(), param.getEndDate());
		//Date currentTime = AppUtils.getCurrentDate();
		  //获取昨天时间
		Date s = CommonUtils.getStringToMillon(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),1*60);
	 System.out.println("ik--"+getChangeDate(20));
		String s1="bankCode=CMB&cardType=0&commodityName=1009,充值金额：2,IP:&keyType=file&merNo=850610050942302&notifyUrl=http://api.niuniu668.com/swagger/wx_wap_notify.jsp&orderDate=20180126&orderNo=20180126133128&productId=1053&remark=充值金额:2,充值时间:2018-01-26 13:31:28&requestNo=20180126133128211&returnUrl=http://api.niuniu668.com/swagger/wx_wap_result.jsp&transAmt=200&transId=70&version=V1.0";
        String s2="keyType=file&merNo=850610050942302&requestNo=20180126133128211&version=V1.0&productId=1053&transId=70&orderDate=20180126&orderNo=20180126133128&returnUrl=http://api.niuniu668.com/swagger/wx_wap_result.jsp&notifyUrl=http://api.niuniu668.com/swagger/wx_wap_notify.jsp&commodityName=1009,充值金额：2,IP:&remark=充值金额:2,充值时间:2018-01-26 13:31:28&transAmt=200&cardType=0&bankCode=CMB";
		String[] r1 = s1.split("&");
        String[] r2 = s2.split("&");
        /*
        System.out.println("m---sd.."+r1.length);
        for (int m = 0;m<r2.length;m++){
        	String temp = r2[m];
        	for (int n = 0;n<r1.length;n++){
        		if (temp.equals(r1[n]))
        			System.out.println("m---"+m+".."+temp+"..通过");
        	}
        }
			System.out.println(new SimpleDateFormat("yyyyMMddHHmmss123").format(new Date()));
			
		System.out.println(new DecimalFormat("#.00").format(223.678));
		  Date backupTime=DateUtils.addDays(new Date(), -1);
		System.out.println("12----"+(185-(185%10)));
		Date[] param1 = getDateTime(new Date(),new Date());
		String str = "20171119096";
		String str1 = "92001001";
		System.out.println("0---"+str1.substring(1, 2));
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, "23");
		System.out.println("90----"+map.size()+".."+str.substring(0,str.length()-2)+String.valueOf(Integer.valueOf(str.substring(str.length()-2,str.length()))-3));
		//BigDecimal.valueOf(Integer.valueOf(str[j][2])).subtract(fee.doubleValue()>0?fee:BigDecimal(0)))
		//testDate();
		int gains=200;
		int count=20000;
		int values=40000;
		String[][] d =new String[7][11];
		//System.out.println(doBankerHandleLess(gains, count, values,4,d));
		doNoBankerHandle1_BFYNL(d);
		/*
		//System.out.println(doCompareCount(gains, count, values));
		
		int []numbers = {50,100,200};
        Random random = new Random();
        int index = random.nextInt(numbers.length);
        System.out.println("12----"+numbers[index]);
		System.out.println(",---------"+BigDecimal.valueOf(30).subtract(BigDecimal.valueOf(0)));
		
		//System.out.println("fe--------"+doBankerHandleEqual(2000,d));
		int times =5;
		System.out.println(doBankerHandleMore(gains,count,values,times,d));
		//System.out.println(doBankerHandleLess(gains,count,values,times,d));
		System.out.println(doNoBankerHandle(d));
		//System.out.println(doBankerHandleEqual(18000,d));
		String[] a = "2,9,2,2,7".split(",");
		String[] c =CommonUtils.getOrdeNum("2,10","02");
		//System.out.println("7----"+c[0]+".."+c[1]+".."+c[2]+"..."+System.currentTimeMillis());
		//public static String[] getStringResultNo(String result,int mod,String type){
		String[] b = CommonUtils.getStringResultNo("5,9,5", 9,"01");
		int t = 9%10;
		System.out.println(".."+b[0]+"..."+b[1]+".."+b[2]+"..");
		String m = (CommonUtils.getCurrentMills());
		System.out.println(".."+CommonUtils.getStringToMillon("2017-10-21 22:40:45",470));
		Date[] data = CommonUtils.getDateBetween(new Date(),new Date(),"03");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(data[0]+".."+data[1]+"..."+sdf.format(data[0])+".."+sdf.format(data[1]));*/
       
	}
}
