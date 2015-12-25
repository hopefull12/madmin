package com.es.masjid.madmin.web.util;

import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;

import java.text.DateFormatSymbols;

/**
 * Created by myachb on 10/7/2015.
 */
public class PrayerTimeCSVItemModelCreator extends DefaultItemModelCreator {

    public PrayerTimeCSVItemModelCreator(ItemType itemType, GenericItemService genericItemService){
        super(itemType, genericItemService);
    }
    protected void addCustomObjects(){
        String[] months = new DateFormatSymbols().getMonths();
        this.mav.addObject("months",months);
    }
}
