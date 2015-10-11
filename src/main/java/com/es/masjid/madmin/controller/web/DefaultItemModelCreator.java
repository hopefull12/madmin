package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.DisplayItemBean;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myachb on 10/7/2015.
 */

public class DefaultItemModelCreator implements ItemModelCreator {

    Logger logger = LoggerFactory.getLogger(DefaultItemModelCreator.class);

    protected ItemType itemType;
    protected ModelAndView mav;
    protected GenericItemService genericItemService;

    public DefaultItemModelCreator(ItemType itemType, GenericItemService genericItemService){
        this.itemType = itemType;
        this.genericItemService = genericItemService;
        this.mav = new ModelAndView();
    }

    public ModelAndView createModelForGet(){
        addCommonObjects();
        addCustomObjects();
        return mav;
    }

    protected void addCommonObjects(){
        List<Item> items = genericItemService.getCurrentAndFutureValidItemsByType(itemType);
        mav.addObject("items", mapItemToDTO(items));

        Item item = new Item();
        item.setItemType(itemType);
        mav.addObject("item", item);
    }

    protected void addCustomObjects(){
        //subclasses can override this method to add specific data objects
    }

    protected List<DisplayItemBean> mapItemToDTO(List<Item> items){

        List<DisplayItemBean> itemBeans = new ArrayList<>();

        if(items != null){
            for(Item item: items){
                itemBeans.add(new DisplayItemBean(item));
            }
        }
        return  itemBeans;

    }
}
