package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.controller.DocumentController;
import com.es.masjid.madmin.model.ItemBean;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by myachb on 10/6/2015.
 */

@Component
public class GenericItemControllerUtil {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private GenericItemService genericItemService;

    public ModelAndView getItems(ItemType itemType, String viewName){

        ItemModelCreator itemModelCreator = ItemModelCreatorFactory.getItemModelCreator(itemType, genericItemService);
        ModelAndView mav = itemModelCreator.createModelForGet();
        mav.setViewName(viewName);

        return mav;
    }

    public ModelAndView createItem(@ModelAttribute ItemBean itemBean,
                                   BindingResult result,
                                   final RedirectAttributes redirectAttributes, String successViewName, String errorViewName){

        if (result.hasErrors())
            return new ModelAndView(errorViewName);
        logger.debug("Getting ready to save the news item....");

        try {
        	genericItemService.createItem(itemBean);
        }  catch (Exception e) {
            logger.error("Failed saving the file: "+e.getMessage());
            e.printStackTrace();
        }
        logger.debug("Saved the file....");

        ModelAndView mav = new ModelAndView();
        String message = "Item has been successfully created.";
        mav.setViewName("redirect:"+successViewName);
        redirectAttributes.addFlashAttribute("message", message);

        return mav;

    }
    
    public ModelAndView deleteItem(Integer id, ItemType itemType, final RedirectAttributes redirectAttributes, String successViewName) {

    	genericItemService.deleteItem(id);

        ModelAndView mav = new ModelAndView();
        String message = "Item has been successfully deleted.";
        mav.setViewName("redirect:"+successViewName);
        redirectAttributes.addFlashAttribute("message", message);

        return mav;
    }

//    public ModelAndView getPageableItems(ItemType itemType, boolean ajaxReq, Pageable pageable){
//
//        Page<Item> items = genericItemService.findAllItems(itemType, pageable);
//
//        ModelAndView mav = new ModelAndView();
//        if(ajaxReq){
//            mav.setViewName("itemsTileAjax");
//        }else{
//            mav.setViewName("itemsTile");
//        }
//        mav.addObject("items", items.getContent());
//        mav.addObject("mediaTypes", Arrays.asList(ItemType.MEDIA_AUDIO, ItemType.MEDIA_IMAGE, ItemType.MEDIA_VIDEO));
//        float nrOfPages = items.getTotalPages();
//        mav.addObject("maxPages", nrOfPages);
//        mav.addObject("nextPage", pageable.getPageNumber()+1);
//
//        return mav;
//    }

}
