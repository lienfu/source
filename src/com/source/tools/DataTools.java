package com.source.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DataTools {
	
	//static SimpleDateFormat df= new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat df1= new SimpleDateFormat("HH:mm:ss");
	static SimpleDateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static Calendar dataToCalendar(String input_time) {
		Calendar calendar = null ;
		try {
			Date date = new Date();
			String ggsxri = input_time+" "+df1.format(date);
			//解决时间差8个小时的问题。
			df2.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			Date time= df2.parse(ggsxri);
			calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(time.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return calendar;
	}
	
	public static Calendar dataToCalendar() {
		Calendar calendar = null ;
		Date date = new Date();
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}
	
	
	public static void main(String args[]) {
		Calendar calendar =  DataTools.dataToCalendar("2018/11/1");
	    System.out.println(df2.format(calendar.getTime()));
	//	System.out.println(df2.format(DataTools.dataToCalendar().getTime())); //2018/12/25 11:22:14
	}
}
