package com.es.masjid.backup;

import com.es.masjid.madmin.model.DailyScheduleBean;
import com.es.masjid.madmin.model.PrayerTimesUpload;
import com.es.masjid.madmin.util.MasjidUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:application.properties")
@Controller
public class PrayerTimesController implements ServletContextAware{
	
	private static final String DATA_PATH = "data.path";
	private static final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";
	
	Logger logger = LoggerFactory.getLogger(PrayerTimesController.class);
	
	private ServletContext servletContext;
	
	@Autowired
	private MasjidUtility utility;
	
	@Resource
	private Environment env;	
	
	@RequestMapping(value={"/createPTCSVFile"}, method=RequestMethod.GET)
	public ModelAndView newShopPage() {
		
		String[] months = new DateFormatSymbols().getMonths();		
				
		ModelAndView mav = new ModelAndView("uploadPrayerTimesTile", "prayerTimes", new PrayerTimesUpload());
		mav.addObject("months",months);
		return mav;
	}
	
	@RequestMapping(value={"/createPTCSVFile"}, method=RequestMethod.POST)
	public ModelAndView createPrayerTimes(@ModelAttribute PrayerTimesUpload upload,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("prayertimescreate");
		
		logger.debug("Getting ready to save the file....");
		try {
			saveFile(upload);
		}  catch (Exception e) {		
			logger.error("Failed saving the file: "+e.getMessage());
			e.printStackTrace();
		}
		logger.debug("Saved the file....");
		
		ModelAndView mav = new ModelAndView();
		String message = "Prayertimes has been successfully created.";
		
		//shopService.create(shop);
		mav.setViewName("redirect:/displayPTCSVFiles.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}	
	
	
	@RequestMapping(value={"/displayPTCSVFiles"}, method=RequestMethod.GET)
	public ModelAndView displayPrayerTimeFiles(){
				
		ModelAndView mv = new ModelAndView("displayCSVFilesTile");
		List<String> fileNames = utility.getScheduleFileNames();		
		
		mv.addObject("prayerfiles",fileNames);
		
		return mv;
	}	
	
	@RequestMapping(value={"/displayPTCSVFiles/{fileName}"}, method=RequestMethod.GET)
	public ModelAndView displayPrayerTimes(@PathVariable("fileName") String fileName){
				
		ModelAndView mv = new ModelAndView("displayPrayerTimesTile");
		
		List<DailyScheduleBean> lines = utility.getScheduleByFileName2(fileName);
		mv.addObject("prayertimes",lines);
		
		return mv;
	}


	
	@RequestMapping(value={"/dailySchedule"}, method=RequestMethod.GET)
	public @ResponseBody DailyScheduleBean dailySchedule(){	
		DailyScheduleBean bean = utility.getTodaysSchedule();
		return bean;
	}
	
	@RequestMapping(value={"/prayerTimes/{type}"}, method=RequestMethod.GET)
	public @ResponseBody List<DailyScheduleBean> dailySchedule2(@PathVariable("type") String type){	
		return utility.getPrayerTimes(type);
	}	

    @RequestMapping(value={"/prayerTimes"}, method=RequestMethod.GET)
    public @ResponseBody List<DailyScheduleBean> prayerTimes(@RequestParam Date fromDate, @RequestParam Date toDate){
        return utility.getPrayerTimes(fromDate, toDate);
    }



	
	@RequestMapping(value={"/displayPtPDFFiles"}, method=RequestMethod.GET)
	public ModelAndView displayMonthlyPDFPrayerTimeFiles(){
				
		ModelAndView mv = new ModelAndView("displayMontlyPtPDFFiles");
		List<String> fileNames = utility.getMonthlyPrayerTimePDFFileNames();		
		
		mv.addObject("prayerfiles",fileNames);
		
		return mv;
	}	
	
	@RequestMapping(value = "/displayPtPDFFiles/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadPDFFile(@PathVariable("fileName") String fileName)
	                                                                  throws IOException {
		
		InputStream is = new FileInputStream(utility.getFileByFileName(fileName+".pdf"));
		
	    HttpHeaders respHeaders = new HttpHeaders();
	    respHeaders.setContentType(MediaType.parseMediaType("application/pdf"));
	    respHeaders.setContentLength(12345678);
	    respHeaders.setContentDispositionFormData("attachment", fileName+".pdf");

	    InputStreamResource isr = new InputStreamResource(is);
	    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/ptPDFFiles"}, method=RequestMethod.GET)
	public @ResponseBody List<String> getPrayerTimePDFFiles(){
						
		List<String> fileNames = utility.getMonthlyPrayerTimePDFFileNames();				
		return fileNames;
	}	

//	@RequestMapping(value = "/ptPDFFiles/{fileName}", method = RequestMethod.GET)
//	@ResponseBody
//	public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
//	    return new FileSystemResource(utility.getFileByFileName(fileName+".pdf")); 
//	}	
	
	@RequestMapping(value = "/ptPDFFiles/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
	    return new FileSystemResource(utility.getFileByFileName(fileName+".pdf")); 
	}	
	
	
	
	private void saveFile(PrayerTimesUpload upload)
			throws RuntimeException, IOException {
		utility.saveScheduleFile(upload);
	}


	
	
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		 
		}	

}
