package com.es.masjid.madmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.ItemService;
import com.es.masjid.shared.UploadedFilesBean;

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("/item")
public class ItemController {

	Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	ItemService itemService;	

	@RequestMapping(value={"/news"}, method=RequestMethod.GET)
	public ModelAndView getAllNewsItems() {
				
		ModelAndView mav = new ModelAndView("displayAllNewsItemsTile");
		
		List<Item> newsItems = itemService.getValidItemsByType(ItemType.NEWS);
		mav.addObject("newsItems", newsItems);
		return mav;
	}
	
	@RequestMapping(value={"/news/create"}, method=RequestMethod.GET)
	public ModelAndView createNews() {
				
		ModelAndView mav = new ModelAndView("createNewsItemTile", "itemBean", new Item());
		return mav;
	}	
	
	@RequestMapping(value={"/news/delete/{id}/"}, method=RequestMethod.GET)
	public ModelAndView deleteNewsItem(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		
		itemService.deleteItem(id);
		
		ModelAndView mav = new ModelAndView();
		String message = "News Item has been successfully deleted.";
		
		//shopService.create(shop);
		mav.setViewName("redirect:/item/news.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}	
	
	@RequestMapping(value={"/news/create"}, method=RequestMethod.POST)
	public ModelAndView createFile(@ModelAttribute Item itemBean,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("createNewsItemTile");
		
		logger.debug("Getting ready to save the news item....");
		try {
			itemService.createItem(itemBean);
		}  catch (Exception e) {		
			logger.error("Failed saving the file: "+e.getMessage());
			e.printStackTrace();
		}
		logger.debug("Saved the file....");
		
		ModelAndView mav = new ModelAndView();
		String message = "News Item has been successfully created.";
		
		//shopService.create(shop);
		mav.setViewName("redirect:/item/news.html");
				
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}		
	
	@RequestMapping(value={"/rest/news"}, method=RequestMethod.GET)
	public @ResponseBody List<String> getAllFiles(){
		
		List<Item> items = itemService.getCurrentValidItemsByType(ItemType.NEWS);
		List<String> itemStrs = new ArrayList<>();
		
		for(Item item : items){
			itemStrs.add(item.getLongDescription());
		}

		return itemStrs;
	}		
	
}
