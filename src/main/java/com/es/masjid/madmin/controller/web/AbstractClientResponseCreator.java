package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import org.springframework.core.env.Environment;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 10/9/2015.
 */
public class AbstractClientResponseCreator implements ClientResponseCreator{

    @Resource
    protected Environment env;

    protected GenericItemService genericItemService;
    protected List<Map<String, String>> clientResponse = new LinkedList<>();

    protected static String BASE_URL = "http://www.yahibaba.com/madmin/";


    public AbstractClientResponseCreator(GenericItemService genericItemService){
        this.genericItemService = genericItemService;
    }

    public List<Map<String, String>> createAllItemsResponse(ItemType itemType){
        return null;
    }

    public Map<String, String> createOneItemResponse(Integer id){
        return null;
    }

    protected List<Item> getAllCurrentAndFutureValidItems(ItemType itemType){
        return genericItemService.getCurrentAndFutureValidItemsByType(itemType);
    }

    protected Item getItem(Integer id){
        return genericItemService.findOne(id);
    }


}
