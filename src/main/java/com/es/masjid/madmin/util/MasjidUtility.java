package com.es.masjid.madmin.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.es.masjid.madmin.model.DailyScheduleBean;
import com.es.masjid.madmin.model.PrayerTimesUpload;

@Component
public class MasjidUtility {
	
	@Resource
	private Environment env;	
	
	private final String DATA_PATH = "data.path";
	private final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";	
	
	public DailyScheduleBean createDailyScheduleBean(String  schedule){
		DailyScheduleBean bean = new DailyScheduleBean();
		
		bean.setDate(schedule.substring(0,8));
		
		bean.setFajarBeginTime(addSpaceBeforeAMOrPM(schedule.substring(9,15)));
		bean.setFajarIqamaTime(addSpaceBeforeAMOrPM(schedule.substring(16,22)));
		
		bean.setSunriseTime(addSpaceBeforeAMOrPM(schedule.substring(23,29)));
		
		bean.setDhuhrTIme(addSpaceBeforeAMOrPM(schedule.substring(30,37)));
		bean.setDhuhrIqamaTIme(addSpaceBeforeAMOrPM(schedule.substring(38,44)));
		
		bean.setAsrTime(addSpaceBeforeAMOrPM(schedule.substring(45,51)));
		bean.setAsrIqamaTIme(addSpaceBeforeAMOrPM(schedule.substring(52,58)));
		
		bean.setMaghribTIme(addSpaceBeforeAMOrPM(schedule.substring(59,65)));
		bean.setMaghribIqamaTIme(addSpaceBeforeAMOrPM(schedule.substring(66,72)));
		
		bean.setIshaTIme(addSpaceBeforeAMOrPM(schedule.substring(73,79)));
		bean.setIshaIqamaTIme(addSpaceBeforeAMOrPM(schedule.substring(80,86)));
		return bean;
	}
	
	private String addSpaceBeforeAMOrPM(String s){
		String s1 = new StringBuilder(s).insert(s.length()-2, " ").toString().toUpperCase();
		return s1;
	}
	
	public DailyScheduleBean getTodaysSchedule(){
		
		DailyScheduleBean bean = null;
		try {
			Calendar cal = Calendar.getInstance();
			String monthName = cal.getDisplayName(Calendar.MONDAY, Calendar.LONG, Locale.getDefault());
			String fileName = monthName.toLowerCase() + "-" + PRAYER_TIMES_FILE_NAME;			
			
			List<DailyScheduleBean> lines = getScheduleByFileName2(fileName);
			
			SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
			Date today = new Date();
			Date todayWithZeroTime =sdf.parse(sdf.format(today));
			
			for(DailyScheduleBean line : lines){
				
				Date d = sdf.parse(line.getDate());
				
				System.out.println();
				
				if(d.equals(todayWithZeroTime)){
					bean = line;
					break;
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
		
	}
	
//	public List<String> getScheduleByFileName(String fileName)
//			{		
//		List<String> lines = null;
//		Path path = FileSystems.getDefault().getPath(env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/"
//				, fileName);
//		try {
//			lines = Files.readAllLines(path, Charset.defaultCharset() );
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Iterator<String> it = lines.iterator();
//		while(it.hasNext()){
//			String line = (String)it.next();
//			if(line.startsWith("date")){
//				it.remove();
//			}
//		}		
//		Collections.sort(lines);
//		return lines;
//	}	
	
	public List<DailyScheduleBean> getScheduleByFileName2(String fileName)
	{		
		List<String> lines = null;
		List<DailyScheduleBean> beans = new ArrayList<>();
		Path path = FileSystems.getDefault().getPath(env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/"
				, fileName);
		try {
			lines = Files.readAllLines(path, Charset.defaultCharset() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> it = lines.iterator();
		while(it.hasNext()){
			String line = (String)it.next();
			if(!line.startsWith("date")){
				beans.add(createDailyScheduleBean(line));
			}
		}		
		Collections.sort(lines);
		return beans;
	}	
	
	
	
	public List<String> getScheduleFileNames() {
		List<String> fileNames = new ArrayList<>();
		//Creating a DirectoryStream inside a try-with-resource block
		try (DirectoryStream<Path> ds = 
		  Files.newDirectoryStream(FileSystems.getDefault().getPath(env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId()))) {
			
			for (Path p : ds) {
		       
				fileNames.add(p.getFileName().toString());
		 }

		} catch (IOException e) {
		   e.printStackTrace();
		}
		return fileNames;
	}	
	
	public void saveScheduleFile(PrayerTimesUpload upload) throws IOException {
		try {
			//String fileName = new SimpleDateFormat("yyyyMMddhhmm'.csv'").format(new Date());
			String fileName = upload.getMonth().toLowerCase()+"-"+PRAYER_TIMES_FILE_NAME;
			File file = new File(env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/"
			+ fileName);
			
			if(!file.exists() && !file.isDirectory())
			{
			    file.createNewFile();
			}				
			 
			FileUtils.writeByteArrayToFile(file, upload.getPrayerTimesFile().getBytes(),true);
			
			System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
		} 
		catch (IOException e) {
			throw e;
		}
	}	
	

}
