package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import com.es.masjid.madmin.util.ItemUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 12/2/2015.
 */
public class MediaItemResponseCreator  extends AbstractClientResponseCreator {

    public MediaItemResponseCreator(GenericItemService genericItemService) {
        super(genericItemService);
    }

    public List<Map<String, String>> createAllItemsResponse(ItemType itemType, Pageable pageRequest){
        Page<Item> items = getAllCurrentItems(itemType, pageRequest);

        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");                

        for(Item item : ItemUtil.safe(items.getContent())){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("title", item.getDisplayName());
            map.put("name", item.getName());
            map.put("description", item.getShortDescription());
            map.put("link", item.getLongDescription());
            map.put("dateCreated", sdf.format(item.getDateCreated()));
            this.clientResponse.add(map);
        }
        return this.clientResponse;
    }
}
