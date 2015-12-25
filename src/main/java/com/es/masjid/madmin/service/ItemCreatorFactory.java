package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.ItemType;

/**
 * Created by myachb on 10/30/2015.
 */
public class ItemCreatorFactory {

    public static ItemCreator getItemCreator(ItemType itemType){

        if(ItemType.PT_CSV.equals(itemType)){
            return new PrayerTimesCSVItemCreator();
        }
        else if(ItemType.MEDIA_IMAGE.equals(itemType) || ItemType.MEDIA_AUDIO.equals(itemType) ||
        		ItemType.MEDIA_VIDEO.equals(itemType)){
        	return new MediaItemCreator();
        }
        else {
            return new ItemCreator();
        }
    }
}
