package com.es.masjid.madmin.web.api.util;

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
public class NewsItemResponseCreator extends AbstractClientResponseCreator {

    public NewsItemResponseCreator(GenericItemService genericItemService){
        super(genericItemService);
    }

    public List<Map<String, String>> createAllItemsResponse(ItemType itemType) {
        List<Item> items = getAllCurrentAndFutureValidItems(itemType);
        for(Item item : ItemUtil.safe(items)){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("news", item.getShortDescription());
            this.clientResponse.add(map);
        }
        return this.clientResponse;
    }

}
