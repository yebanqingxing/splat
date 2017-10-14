package com.sml.sz.sys.pnr;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTool implements Serializable
{
	
	private Logger logger = LoggerFactory.getLogger(DateTool.class);
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Date date;
	private boolean fullFormat = true;

	  public DateTool()
	  {
	    this.date = new Date();
	  }

	  public DateTool(long date)
	  {
	    this.date = new Date(date);
	  }

	  public DateTool(Date date)
	  {
	    this.date = date;
	  }

	  public DateTool(String s)
	  {
	    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	    Date d = parse(s, sd);
	    if (d != null)
	    {
	      this.date = d;
	      return;
	    }

	    sd = new SimpleDateFormat("yyyy-MM-dd");
	    d = parse(s, sd);
	    if (d != null)
	    {
	      GregorianCalendar gc = new GregorianCalendar();
	      int hour = gc.get(11);
	      int minute = gc.get(12);
	      int second = gc.get(13);

	      gc.setTime(d);
	      gc.set(11, hour);
	      gc.set(12, minute);
	      gc.set(13, second);
	      this.date = gc.getTime();

	      this.fullFormat = false;

	      return;
	    }
	    throw new IllegalArgumentException();
	  }

	  private Date parse(String s, SimpleDateFormat sd)
	  {
	    try
	    {
	      return sd.parse(s);
	    }
	    catch (Exception e) {
	    	logger.error(e.getMessage(), e);
	    }
	    return null;
	  }

	  public String toString()
	  {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date);
	  }

	  public String toYearString() {
	    return new SimpleDateFormat("yyyy").format(this.date);
	  }

	  public String toMonthString() {
	    return new SimpleDateFormat("yyyy-MM").format(this.date);
	  }

	  public String toDateString() {
	    return new SimpleDateFormat("yyyy-MM-dd").format(this.date);
	  }

	  public static DateTool valueOf(String s)
	  {
	    return new DateTool(s);
	  }

	  public int compareTo(DateTool anotherDate)
	  {
	    return this.date.compareTo(anotherDate.date);
	  }

	  public int compareTo(Object anotherDate)
	  {
	    return this.date.compareTo(((DateTool)anotherDate).date);
	  }

	  public java.util.Date toDate()
	  {
	    return this.date;
	  }

	  public boolean isFullFormat()
	  {
	    return this.fullFormat;
	  }
	  /**
	   * 
	   * @param dataString
	   * @return
	   * @auth 冯俊伟
	   * @date 2016年3月15日
	   * 作用：将时间格式化，用于飞机起飞时间的计算 日期+1
	   */
	  public static String dataString(String dataString){
		  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		       // String str="2016-02-29";
		        Date dt = null;
				try {
					dt = sdf.parse(dataString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Calendar rightNow = Calendar.getInstance();
		        rightNow.setTime(dt);
		        //rightNow.add(Calendar.YEAR,-1);//日期减1年
		        //rightNow.add(Calendar.MONTH,3);//日期加3个月
		        rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加10天
		        Date dt1=rightNow.getTime();
		        String reStr = sdf.format(dt1);
		        System.out.println(reStr);
		        return reStr;
	  }
	  
	  public static String dataString(String dataStringFrom,String dataStringTo){
	  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
	       // String str="2016-02-29";
	        Date dtFrom = null;
	        Date dtTo = null;
			try {
				dtFrom = sdf.parse(dataStringFrom);
				dtTo = sdf.parse(dataStringTo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(dtFrom);
	        String[] split = dataStringTo.split("-");
	      
	        rightNow. add(Calendar.YEAR,-Integer.parseInt(split[0]));//日期减1年
	       // rightNow.add(Calendar.MONTH,-Integer.parseInt(split[1]));//日期加3个月
	        //rightNow.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(split[2]));//日期加10天
	       // rightNow.add(Calendar.HOUR,-Integer.parseInt(split[3]));//日期加10天
	       // rightNow.add(Calendar.MINUTE,-Integer.parseInt(split[4]));//日期加10天
	        
	        Date dt1=rightNow.getTime();
	        String reStr = sdf.format(dt1);
	        //System.out.println(reStr);
	        return reStr;
}
	  public static void main(String[] args) {
		  String dataStringFrom="2016-04-14-23-25";
		  	String dataStringTo="2016-04-14-05-35";
		  String dataString = dataString(dataStringFrom, dataStringTo);
		  System.out.println(dataString);
	}
	}