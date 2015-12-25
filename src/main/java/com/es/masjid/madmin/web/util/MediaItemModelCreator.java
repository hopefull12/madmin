package com.es.masjid.madmin.web.util;

import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;

/**
 * Created by myachb on 12/4/2015.
 */
public class MediaItemModelCreator extends DefaultItemModelCreator {

    public MediaItemModelCreator(ItemType itemType, GenericItemService genericItemService){
        super(itemType, genericItemService);
    }

    protected void addCustomObjects(){
        String[] mediaTypes = {ItemType.MEDIA_AUDIO.toString(), ItemType.MEDIA_VIDEO.toString(), ItemType.MEDIA_IMAGE.toString()};
        this.mav.addObject("mediaTypes", mediaTypes);
    }
}
