package com.es.masjid.madmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {
	
	public static void test1() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		String dateInString = "5/23/2015";
		//Date d = sdf.format(new Date());
		//d.get
		System.out.println(sdf.parse(dateInString));
		
		String fileNameSuffix = new SimpleDateFormat("yyyyMMd-hhmmss").format(new Date());
		
		System.out.println(fileNameSuffix);		
	}
	
	public static void test2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		String dateInString = "10/23/2015";
		Date d = sdf.parse(dateInString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		cal.set(Calendar.DATE, 1);
		
		Date firstDateOfPreviousMonth = cal.getTime();
		
		System.out.println(firstDateOfPreviousMonth);
		
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Date lastDateOfPreviousMonth = cal.getTime();
		

    	System.out.println(lastDateOfPreviousMonth);		
	}

	public static void main(String[] args) throws ParseException {
		  Calendar now = Calendar.getInstance();
		  System.out.println(now.getTime());
	        now.set(Calendar.HOUR_OF_DAY, 0);
	        now.set(Calendar.MINUTE, 0);
	        now.set(Calendar.SECOND, 0);
	        System.out.println(now.getTime());
	}
}
