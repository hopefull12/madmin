package com.es.masjid.madmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		String dateInString = "5/23/2015";
		//Date d = sdf.format(new Date());
		//d.get
		System.out.println(sdf.parse(dateInString));
		
		String fileNameSuffix = new SimpleDateFormat("yyyyMMd-hhmmss").format(new Date());
		
		System.out.println(fileNameSuffix);
	}
}
