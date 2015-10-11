package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.controller.DocumentController;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.NewItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Created by myachb on 10/5/2015.
 */
public class ItemControllerUtil {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    private NewItemService newItemService;

    public ModelAndView getAllItems(ItemType itemType, String viewName){
        ModelAndView mav = new ModelAndView(viewName);
        List<Item> items = newItemService.getAllItemsByType(itemType);
        mav.addObject("items", items);
        return mav;
    }

    public ModelAndView createItemGet(ItemType itemType, String viewName){
        Item item = new Item();
        item.setItemType(itemType);
        return new ModelAndView(viewName, "itemBean", new Item());
    }

    public ModelAndView createItemPost(@ModelAttribute Item itemBean,
                                   BindingResult result,
                                   final RedirectAttributes redirectAttributes, String errorViewName, String successViewName) {

        if (result.hasErrors())
            return new ModelAndView(errorViewName);
        logger.debug("Getting ready to save the news item....");

        try {
            newItemService.createItem(itemBean);
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

        newItemService.deleteItem(id);

        ModelAndView mav = new ModelAndView();
        String message = "News Item has been successfully deleted.";
        mav.setViewName("redirect:"+successViewName);
        redirectAttributes.addFlashAttribute("message", message);

        return mav;
    }

}
