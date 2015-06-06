package com.es.masjid.madmin.controller;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.es.masjid.madmin.model.DailyScheduleBean;
import com.es.masjid.madmin.model.PrayerTimesUpload;
import com.es.masjid.madmin.util.MasjidUtility;

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
	
	@RequestMapping(value={"/prayertimescreate"}, method=RequestMethod.GET)
	public ModelAndView newShopPage() {
		
		String[] months = new DateFormatSymbols().getMonths();		
				
		ModelAndView mav = new ModelAndView("uploadPrayerTimes", "prayerTimes", new PrayerTimesUpload());
		mav.addObject("months",months);
		return mav;
	}
	
	@RequestMapping(value={"/prayertimescreate"}, method=RequestMethod.POST)
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
		mav.setViewName("redirect:/prayerfilesdisplay.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}	
	
	@RequestMapping(value={"/prayertimesdisplay"}, method=RequestMethod.GET)
	public ModelAndView displayPrayerTimes(@RequestParam("fileName") String fileName){
				
		ModelAndView mv = new ModelAndView("displayPrayerTimes");
		
		List<DailyScheduleBean> lines = utility.getScheduleByFileName2(fileName);
		mv.addObject("prayertimes",lines);
		
		return mv;
	}


	
	@RequestMapping(value={"/dailySchedule"}, method=RequestMethod.GET)
	public @ResponseBody DailyScheduleBean dailySchedule(){
				
//		DailyScheduleBean bean = new DailyScheduleBean();
//		bean.setFajarBeginTime("5:45am");
//		bean.setFajarIqamaTime("6:00am");
		
		DailyScheduleBean bean = utility.getTodaysSchedule();
		
		return bean;
	}		
	
	@RequestMapping(value={"/prayerfilesdisplay"}, method=RequestMethod.GET)
	public ModelAndView displayPrayerTimeFiles(){
				
		ModelAndView mv = new ModelAndView("displayFiles");
		List<String> fileNames = utility.getScheduleFileNames();		
		
		mv.addObject("prayerfiles",fileNames);
		
		return mv;
	}
	
	@RequestMapping(value={"/displayPtPDFFiles"}, method=RequestMethod.GET)
	public ModelAndView displayMonthlyPDFPrayerTimeFiles(){
				
		ModelAndView mv = new ModelAndView("displayMontlyPtPDFFiles");
		List<String> fileNames = utility.getMonthlyPrayerTimePDFFileNames();		
		
		mv.addObject("prayerfiles",fileNames);
		
		return mv;
	}	
	
	@RequestMapping(value={"/ptPDFFiles"}, method=RequestMethod.GET)
	public @ResponseBody List<String> getPrayerTimePDFFiles(){
						
		List<String> fileNames = utility.getMonthlyPrayerTimePDFFileNames();				
		return fileNames;
	}	

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
