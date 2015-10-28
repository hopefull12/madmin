package com.es.masjid.madmin.util;
import com.es.masjid.madmin.model.DailyScheduleBean;
import com.es.masjid.madmin.model.PrayerTimesUpload;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MasjidUtility {
	
	@Resource
	private Environment env;	
	
	private final String DATA_PATH = "data.path";
	private final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";	
	
	Logger logger = LoggerFactory.getLogger(MasjidUtility.class);


	
    public List<DailyScheduleBean> getPrayerTimes(String type){
    	
    	List<DailyScheduleBean> result = new ArrayList<>();  	
    	Date dataDate = whichDayDataToShow();
    	if("DAILY".equalsIgnoreCase(type)){
    			result = getPrayerTimes(dataDate, dataDate);
    	}else{
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(dataDate);
    		cal.set(Calendar.DATE, 1);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);    		
    		Date firstDateOfMonth = cal.getTime();     
    		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    		Date lastDateOfMonth = cal.getTime();       
    		result = getPrayerTimes(firstDateOfMonth, lastDateOfMonth);
    	}
        return result;
    }	

    public List<DailyScheduleBean> getPrayerTimes(Date fromDate, Date toDate){

        DailyScheduleBean bean = null;
        List<DailyScheduleBean> result = new ArrayList<>();
        try {
            Calendar cal = Calendar.getInstance();
            String monthName = cal.getDisplayName(Calendar.MONDAY, Calendar.LONG, Locale.getDefault());
            String fileName = monthName.toLowerCase() + "-" + PRAYER_TIMES_FILE_NAME;

            List<DailyScheduleBean> lines = getScheduleByFileName2(fileName);

            SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
            Date today = new Date();
            Date todayWithZeroTime =sdf.parse(sdf.format(today));

            for(DailyScheduleBean line : lines){

                Date d = sdf.parse(line.getDate());

                if((d.after(fromDate) && d.before(toDate)) || d.equals(fromDate) || d.equals(toDate)){
                    result.add(line);
                }

//                if(d.equals(todayWithZeroTime)){
//                    bean = line;
//                    break;
//                }
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }
    
    private DailyScheduleBean getPrayerTimes(Date targetDate){

        DailyScheduleBean bean = null;
        
        try {
        	//Basing on target date get name of the month
            Calendar cal = Calendar.getInstance();
            cal.setTime(targetDate);
            String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            String fileName = monthName.toLowerCase() + "-" + PRAYER_TIMES_FILE_NAME;

            //Get file contents
            List<DailyScheduleBean> lines = getScheduleByFileName2(fileName);

            SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
            Date targetDateWithoutTime =sdf.parse(sdf.format(targetDate));

            for(DailyScheduleBean line : lines){
                Date d = sdf.parse(line.getDate());
                if(d.equals(targetDateWithoutTime)){
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
    
	
	public DailyScheduleBean getTodaysSchedule(){
		
		DailyScheduleBean bean = null;
		try {
			Calendar cal = Calendar.getInstance();
			String monthName = cal.getDisplayName(Calendar.MONDAY, Calendar.LONG, Locale.getDefault());
			String fileName = monthName.toLowerCase() + "-" + PRAYER_TIMES_FILE_NAME;			
			
			List<DailyScheduleBean> lines = getScheduleByFileName2(fileName);
			
			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
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
    
	private Date whichDayDataToShow(){
		Date dataDate = null;
        try {
            
        //get current client date with time
        Date clientDate = getCurrentClientDateWithTime();

        //get today isha time
        DailyScheduleBean todayBean = getPrayerTimes(new Date(clientDate.getTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm  a");
        Date ishaDate = sdf.parse(todayBean.getDate()+" "+todayBean.getIshaIqamaTime());
        Date ishaDateWithBufferTime = new Date(ishaDate.getTime() + (new Integer(todayBean.getIshaBufferTime())*60*1000));
        
        //if isha not over then return today data or else return tomorrow data
        if(clientDate.before(ishaDateWithBufferTime)){
        	dataDate = clientDate;
        } else {
        	
        	Date d = new Date();
        	Calendar c = Calendar.getInstance(); 
        	c.setTime(d); 
        	c.add(Calendar.DATE, 1);
        	dataDate = c.getTime();        	
        	
        }
        sdf = new SimpleDateFormat("M/d/yyyy");
        dataDate = sdf.parse(sdf.format(dataDate));        

        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }		

        return dataDate;
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
		
		String filePath = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/";
		logger.debug("Reading the PRAYER TIMES FILE with name "+fileName+" from this location: "+filePath);
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		try {
			lines = Files.readAllLines(path, Charset.defaultCharset() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> it = lines.iterator();
		while(it.hasNext()){
			String line = (String)it.next();
			
			logger.debug("Line: "+line.length());
			if(!line.startsWith("date") && line.length() > 100){
				beans.add(createDailyScheduleBean(line));
			}
		}		
		Collections.sort(lines);
		return beans;
	}

//    public List<DailyScheduleBean> getSchedule(String fileName)
//    {
//        List<String> lines = null;
//        List<DailyScheduleBean> beans = new ArrayList<>();
//
//        List<Map<String, String>> linesList = new LinkedList<>();
//
//        String filePath = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/";
//        logger.debug("Reading the PRAYER TIMES FILE with name "+fileName+" from this location: "+filePath);
//        Path path = FileSystems.getDefault().getPath(filePath, fileName);
//        try {
//            lines = Files.readAllLines(path, Charset.defaultCharset() );
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        Iterator<String> it = lines.iterator();
//        while(it.hasNext()){
//            String line = (String)it.next();
//
//            logger.debug("Line: "+line.length());
//            if(!line.startsWith("date") && line.length() > 100){
//                beans.add(createDailyScheduleBean(line));
//            }
//        }
//        Collections.sort(lines);
//        return beans;
//    }
	
	public File getFileByFileName(String fileName){
		
		String filePath = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/" + fileName;
		
		return new File(filePath);
	}
	
	
	
	public List<String> getScheduleFileNames() {
		List<String> fileNames = getFileNames("csv");
		
		List<String> names = new ArrayList<>();
		
		for(String name: fileNames){
			
			names.add(name.substring(0, name.indexOf(".")));
		}
		
		return names;
	}	
	
	public List<String> getMonthlyPrayerTimePDFFileNames() {
		List<String> fileNames = getFileNames("pdf");
		
		List<String> names = new ArrayList<>();
		
		for(String name: fileNames){
			
			names.add(name.substring(0, name.indexOf(".")));
		}		
		
		return names;
	}	
	
	public List<String> getFileNames(String type) {
		List<String> fileNames = new ArrayList<>();
		//Creating a DirectoryStream inside a try-with-resource block
		try (DirectoryStream<Path> ds = 
		  Files.newDirectoryStream(FileSystems.getDefault().getPath(env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId()),"*."+type)) {
			
			for (Path p : ds) {
		       
				fileNames.add(p.getFileName().toString());
		 }

		} catch (IOException e) {
		   e.printStackTrace();
		}
		return fileNames;
	}	
	
	public void saveScheduleFile(PrayerTimesUpload upload) throws IOException {
		
		String fileName = upload.getMonth().toLowerCase()+"-"+PRAYER_TIMES_FILE_NAME;
		saveFile(fileName, upload.getPrayerTimesFile());
		
//		try {
//			
//			//String fileName = upload.getMonth().toLowerCase()+"-"+PRAYER_TIMES_FILE_NAME;
//			String path = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/" + fileName;
//			
//			logger.debug("The prayer times file path: "+path);
//			
//			File file = new File(path);
//			
//			if(!file.exists() && !file.isDirectory())
//			{				
//			    file.createNewFile();
//			    logger.debug("Created new file");
//			}				
//			 
//			FileUtils.writeByteArrayToFile(file, upload.getPrayerTimesFile().getBytes());			
//			
//			logger.info("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
//		} 
//		catch (IOException e) {
//			throw e;
//		}
	}	
	
	public void saveFile(String fileName, MultipartFile multiPartFile) throws IOException {
		try {
			
			//String fileName = upload.getMonth().toLowerCase()+"-"+PRAYER_TIMES_FILE_NAME;
			String path = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/" + fileName;
			
			logger.debug("The prayer times file path: "+path);
			
			File file = new File(path);
			
			if(!file.exists() && !file.isDirectory())
			{				
			    file.createNewFile();
			    logger.debug("Created new file");
			}				
			 
			FileUtils.writeByteArrayToFile(file, multiPartFile.getBytes());			
			
			logger.info("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
		} 
		catch (IOException e) {
			throw e;
		}
	}		
	
    private Date getCurrentClientDateWithTime(){
    	
    	Date nowClient = null;    	
		try {
			Date now = new Date();
			System.out.println("Now in default time zone: "+now);
			
			//First create a string representation of date using client timezone
			SimpleDateFormat sdfChicago = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
			TimeZone tzInAmerica = TimeZone.getTimeZone("America/Chicago");
			sdfChicago.setTimeZone(tzInAmerica);
			String nowClientStr = sdfChicago.format(now);
			//System.out.println(nowClientStr);
			
			SimpleDateFormat sdfDefault = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
			
			nowClient = sdfDefault.parse(nowClientStr);
			//System.out.println(nowClient);
			
			//Date isha = sdfDefault.parse("25-10-2015 11:29:04 PM");
			//System.out.println("Isha overe?: "+isha.before(nowClient));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
		return nowClient;
    }	
    
	
	public DailyScheduleBean createDailyScheduleBean(String  schedule){
		DailyScheduleBean bean = new DailyScheduleBean();
		
		String[] tokens = schedule.split(",");
		logger.debug("Tokens: "+tokens.toString());
		
		bean.setDate(tokens[0]);
		
		bean.setFajarTime(addSpaceBeforeAMOrPM(tokens[1]));
		bean.setFajarIqamaTime(addSpaceBeforeAMOrPM(tokens[2]));
		
		bean.setSunriseTime(addSpaceBeforeAMOrPM(tokens[3]));
		
		bean.setDhuhrTime(addSpaceBeforeAMOrPM(tokens[4]));
		bean.setDhuhrIqamaTime(addSpaceBeforeAMOrPM(tokens[5]));
		
		bean.setAsrTime(addSpaceBeforeAMOrPM(tokens[6]));
		bean.setAsrIqamaTime(addSpaceBeforeAMOrPM(tokens[7]));
		
		bean.setMaghribTime(addSpaceBeforeAMOrPM(tokens[8]));
		bean.setMaghribIqamaTime(addSpaceBeforeAMOrPM(tokens[9]));
		
		bean.setIshaTime(addSpaceBeforeAMOrPM(tokens[10]));
		bean.setIshaIqamaTime(addSpaceBeforeAMOrPM(tokens[11]));		
		
		bean.setJumah1Time(tokens[12]);
		bean.setJumah1IqamaTime(tokens[13]);
		
		bean.setJumah2Time(tokens[14]);
		bean.setJumah2IqamaTime(tokens[15]);
		
		bean.setFajarBufferTime("30");
		bean.setDhuhrBufferTime("1");
		bean.setAsrBufferTime("45");
		bean.setMaghribBufferTime("30");
		bean.setIshaBufferTime("120");
		bean.setJumah1BufferTime("20");
		bean.setJumah2BufferTime("60");
		return bean;
	}
	
	private String addSpaceBeforeAMOrPM(String s){
		String s1 = new StringBuilder(s).insert(s.length()-2, " ").toString().toUpperCase();
		return s1;
	}    
	
	public static void main(String[] args) {
		
		
		
//		 String[] result = "06012015,3:39 AM,4:50 AM,5:19 AM,12:50 PM,1:30 PM,6:04 PM,6:30 PM,8:21 PM,8:21 PM,10:02 PM,10:20 PM,1:00 PM,1:30 PM,2:00 PM,2:30 PM".split(",");
//	     for (int x=0; x<result.length; x++)
//	         System.out.println(result[x]);		
//	     
MasjidUtility mu = new MasjidUtility();
//mu.createDailyScheduleBean("06012015,3:39 AM,4:50 AM,5:19 AM,12:50 PM,1:30 PM,6:04 PM,6:30 PM,8:21 PM,8:21 PM,10:02 PM,10:20 PM,1:00 PM,1:30 PM,2:00 PM,2:30 PM");
		mu.getTodaysSchedule();
	}

}