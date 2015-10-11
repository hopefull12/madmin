package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.controller.DocumentController;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.NewItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myachb on 10/5/2015.
 */

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("/item")
public class NewItemController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    private NewItemService newItemService;
    private ItemControllerUtil itemControllerUtil;

    @RequestMapping(value={"/news/all"}, method= RequestMethod.GET)
    public ModelAndView getAllNewsItems(@RequestParam ItemType itemType) {
        return itemControllerUtil.getAllItems(ItemType.NEWS, "displayAllNewsItemsTile");
    }

//    @RequestMapping(value={"/news/create"}, method=RequestMethod.GET)
//    public ModelAndView createNewsItem() {
//        return itemControllerUtil.createItemGet(ItemType.NEWS, "createNewsItemTile");
//    }
//
//    @RequestMapping(value={"/news/create1"}, method=RequestMethod.POST)
//    public ModelAndView createNewsItem(@ModelAttribute Item itemBean,
//                                   BindingResult result,
//                                   final RedirectAttributes redirectAttributes) {
//        return itemControllerUtil.createItemPost(itemBean, result, redirectAttributes, "createNewsItemTile", "/item/news/all.html");
//    }
//
//    @RequestMapping(value={"/news/delete"}, method=RequestMethod.GET)
//    public ModelAndView deleteNewsItem(@RequestParam Integer id, @RequestParam("id") ItemType itemType, final RedirectAttributes redirectAttributes) {
//        return itemControllerUtil.deleteItem(id, itemType, redirectAttributes, "/item/news/all");
//    }
//
//    @RequestMapping(value={"/api/news"}, method=RequestMethod.GET)
//    public @ResponseBody List<String> getAllFiles(){
//
//        List<Item> items = newItemService.getCurrentValidItemsByType(ItemType.NEWS);
//        List<String> itemStrs = new ArrayList<>();
//
//        for(Item item : items){
//            itemStrs.add(item.getLongDescription());
//        }
//
//        return itemStrs;
//    }
}
