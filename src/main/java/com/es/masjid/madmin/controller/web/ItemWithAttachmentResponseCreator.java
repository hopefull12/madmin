package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import com.es.masjid.madmin.util.ItemUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 10/9/2015.
 */
public class ItemWithAttachmentResponseCreator extends AbstractClientResponseCreator {

    public ItemWithAttachmentResponseCreator(GenericItemService genericItemService){
        super(genericItemService);
    }

    public List<Map<String, String>> createAllItemsResponse(ItemType itemType) {
        List<Item> items = getAllCurrentAndFutureValidItems(itemType);
        for(Item item : ItemUtil.safe(items)){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("name", item.getFirstAttachment().getDisplayName());
            map.put("link", BASE_URL+"file/"+item.getFirstAttachment().getName()+"/");
            map.put("fileName", item.getFirstAttachment().getName());
            this.clientResponse.add(map);
        }
        return this.clientResponse;
    }

}
