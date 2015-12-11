package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;

/**
 * Created by myachb on 10/7/2015.
 */
public class ItemModelCreatorFactory {

    public static ItemModelCreator getItemModelCreator(ItemType itemType, GenericItemService genericItemService){
        if(ItemType.PT_CSV.toString().equalsIgnoreCase(itemType.toString())){
            return new PrayerTimeCSVItemModelCreator(itemType, genericItemService);
        }if(ItemType.MEDIA.toString().equalsIgnoreCase(itemType.toString())){
            return new MediaItemModelCreator(itemType, genericItemService);
        }else{
            return new DefaultItemModelCreator(itemType, genericItemService);
        }
    }
}
