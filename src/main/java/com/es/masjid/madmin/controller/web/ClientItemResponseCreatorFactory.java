package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;

/**
 * Created by myachb on 10/9/2015.
 */
public class ClientItemResponseCreatorFactory {

    public static ClientResponseCreator getItemModelCreator(ItemType itemType, GenericItemService genericItemService){
        if(ItemType.NEWS.toString().equalsIgnoreCase(itemType.toString())){
            return new NewsItemResponseCreator(genericItemService);
        } else if(ItemType.PT_CSV.toString().equalsIgnoreCase(itemType.toString())) {
            return new NewsItemResponseCreator(genericItemService);
        } else{
            return new ItemWithAttachmentResponseCreator(genericItemService);
        }
    }
}
