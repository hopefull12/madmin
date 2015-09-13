package com.es.masjid.madmin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		String[] fileTypes = {"PDF", "CSV"};
		String[] months = new DateFormatSymbols().getMonths();
				
		ModelAndView mav = new ModelAndView("uploadDocumentTile", "docBean", new DocumentBean());
		mav.addObject("categories",categories);
		mav.addObject("months",months);
		mav.addObject("fileTypes", fileTypes);
		return mav;
	}	
	
	@RequestMapping(value={"/createFile"}, method=RequestMethod.POST)
	public ModelAndView createFile(@ModelAttribute DocumentBean docBean,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("uploadDocumentTile");
		
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
		mav.setViewName("redirect:/displayFiles.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}		
	
	@RequestMapping(value={"/", "/displayFiles"}, method=RequestMethod.GET)
	public ModelAndView displayFiles(){
				
		ModelAndView mv = new ModelAndView("displayUploadedDocumentsTile");
		List<Document> docs = docService.findAll();
		mv.addObject("uploadedfiles",docs);
		
		return mv;
	}	
	
	@RequestMapping(value = "/displayFiles/{fileName}/", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> displayFile(@PathVariable("fileName") String fileName)
	                                                                  throws IOException {
		File f = docService.getFileByFileName(fileName);
		InputStream is = new FileInputStream(f);
		
		String fileExt = fileName.substring(fileName.indexOf("."), fileName.length());
		String mimeTypeToUse = null;
		System.out.println("The file ext is: "+fileExt);
		
		if(".pdf".equals(fileExt)){
			mimeTypeToUse = "application/pdf";
		}else if(".docx".equals(fileExt)){
			mimeTypeToUse = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}else if(".pptx".equals(fileExt)){
			mimeTypeToUse = "application/vnd.ms-excel";
		}else if(".csv".equals(fileExt)){
			mimeTypeToUse = "text/csv";
		}		
		
	    HttpHeaders respHeaders = new HttpHeaders();
	    respHeaders.setContentType(MediaType.parseMediaType(mimeTypeToUse));
	    respHeaders.setContentLength(f.length());
	    if(".csv".equals(fileExt) || ".docx".equals(fileExt)){
	    	respHeaders.setContentDispositionFormData("attachment", fileName);
	    }

	    InputStreamResource isr = new InputStreamResource(is);
	    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteDocument/{id}/", method = RequestMethod.GET)
	public ModelAndView deleteDocuments(@PathVariable("id") Integer id){
		docService.deleteDocument(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/displayFiles.html");
		return mav;
	}
	
	/**
	 * Document REST API
	 */
	
	@RequestMapping(value={"/uploadedFiles"}, method=RequestMethod.GET)
	public @ResponseBody UploadedFilesBean getAllFiles(){
						
		List<Document> docs = docService.getValidDocuments();
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
	
	@RequestMapping(value = "/uploadedFiles/{fileName}/", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
	    return new FileSystemResource(docService.getFileByFileName(fileName)); 
	}	
	
}
