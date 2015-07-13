package com.es.masjid.madmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.es.masjid.madmin.model.Document;
import com.es.masjid.madmin.model.DocumentBean;
import com.es.masjid.madmin.service.DocumentService;
import com.es.masjid.shared.UploadedFilesBean;

@PropertySource("classpath:application.properties")
@Controller
public class DocumentController {

	Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentService docService;
	
	@RequestMapping(value={"/createFile"}, method=RequestMethod.GET)
	public ModelAndView displayCreateFile() {
		
		String[] categories = {"PrayerTimes","Ramadan","Misc"};		
				
		ModelAndView mav = new ModelAndView("uploadDocument", "docBean", new DocumentBean());
		mav.addObject("categories",categories);
		return mav;
	}	
	
	@RequestMapping(value={"/createFile"}, method=RequestMethod.POST)
	public ModelAndView createPrayerTimes(@ModelAttribute DocumentBean docBean,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("prayertimescreate");
		
		logger.debug("Getting ready to save the file....");
		try {
			docService.createFile(docBean);
		}  catch (Exception e) {		
			logger.error("Failed saving the file: "+e.getMessage());
			e.printStackTrace();
		}
		logger.debug("Saved the file....");
		
		ModelAndView mav = new ModelAndView();
		String message = "Prayertimes has been successfully created.";
		
		//shopService.create(shop);
		mav.setViewName("redirect:/createFile");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}		
	
	@RequestMapping(value={"/displayFiles"}, method=RequestMethod.GET)
	public ModelAndView displayFiles(){
				
		ModelAndView mv = new ModelAndView("displayUploadedFiles");
		
		List<Document> docs = docService.findAll();
		
//		List<String> prayerFiles = new ArrayList<>();
//		List<String> ramadanFiles = new ArrayList<>();
//		List<String> miscFiles = new ArrayList<>();
//		
//		for(Document doc : docs){
//			
//			if("PrayerTimes".equals(doc.getCategory())){
//				
//			} else if("Ramadan".equals(doc.getCategory())){
//				
//			} else if("Misc".equals(doc.getCategory())){
//				
//			}
//			
//		}
		//List<String> fileNames = utility.getMonthlyPrayerTimePDFFileNames();		
		
		mv.addObject("uploadedfiles",docs);
		
		return mv;
	}	
	
	@RequestMapping(value={"/uploadedFiles"}, method=RequestMethod.GET)
	public @ResponseBody UploadedFilesBean getAllFiles(){
						
		List<Document> docs = docService.findAll();
		List<String[]> prayerFiles = new ArrayList<>();
		List<String[]> ramadanFiles = new ArrayList<>();
		List<String[]> miscFiles = new ArrayList<>();		
		
		for(Document doc : docs){
			
			String[] attr = new String[2];
			attr[0] = doc.getDisplayName();
			attr[1] = doc.getName();			
			
			if("PrayerTimes".equals(doc.getCategory())){
				prayerFiles.add(attr);
			} else if("Ramadan".equals(doc.getCategory())){
				ramadanFiles.add(attr);
			} else if("Misc".equals(doc.getCategory())){
				miscFiles.add(attr);
			}
		}
		
		UploadedFilesBean bean = new UploadedFilesBean();
		bean.setPrayerTimeFiles(prayerFiles);
		bean.setRamadanFiles(ramadanFiles);
		bean.setMiscFiles(miscFiles);
		
		return bean;
	}	
	
	@RequestMapping(value = "/uploadedFiles/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
	    return new FileSystemResource(docService.getFileByFileName(fileName)); 
	}	
	
}
