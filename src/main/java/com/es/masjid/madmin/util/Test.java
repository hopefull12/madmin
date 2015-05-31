package com.es.masjid.madmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("K:mm a");
		String dateInString = "05/23/2015"+" 2:30 PM";
		//Date d = sdf.format(new Date());
		//d.get
		System.out.println(sdf.format(new Date()));
	}
}
