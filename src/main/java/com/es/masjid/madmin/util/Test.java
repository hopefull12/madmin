package com.es.masjid.madmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static void main(String[] args) throws ParseException {
		
		Date now = new Date();
		System.out.println("Now in default time zone: "+now);
		
		SimpleDateFormat sdfChicago = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		TimeZone tzInAmerica = TimeZone.getTimeZone("America/Chicago");
		sdfChicago.setTimeZone(tzInAmerica);
		String nowClientStr = sdfChicago.format(now);
		
		
		System.out.println(nowClientStr);
		
		
		SimpleDateFormat sdfDefault = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		//TimeZone tzInSD = TimeZone.getTimeZone("America/Los_Angeles");	
		//sdfDefault.setTimeZone(tzInSD);
		
		Date nowClient = sdfDefault.parse(nowClientStr);
		System.out.println(nowClient);
		
		Date isha = sdfDefault.parse("25-10-2015 11:29:04 PM");
		System.out.println("Isha overe?: "+isha.before(nowClient));
	}
}
