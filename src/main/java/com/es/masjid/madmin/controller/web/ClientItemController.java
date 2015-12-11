package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.service.GenericItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 10/8/2015.
 */
@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("/api")
public class ClientItemController {

    Logger logger = LoggerFactory.getLogger(GenericItemController.class);

    @Autowired
    private GenericItemService genericItemService;

    @RequestMapping(value={"/items"}, method=RequestMethod.GET)
    public @ResponseBody  List<Map<String, String>> getAllItems(@RequestParam ItemType itemType){
        ClientResponseCreator clientResponseCreator = ClientItemResponseCreatorFactory.getItemModelCreator(itemType, genericItemService);
        return clientResponseCreator.createAllItemsResponse(itemType);
    }

    @RequestMapping(value={"/itemspage"}, method=RequestMethod.GET)
    public @ResponseBody  List<Map<String, String>> getAllItems(@RequestParam ItemType itemType, Pageable pageable){
        ClientResponseCreator clientResponseCreator = ClientItemResponseCreatorFactory.getItemModelCreator(itemType, genericItemService);
        return clientResponseCreator.createAllItemsResponse(itemType, pageable);
    }

    @RequestMapping(value={"/items/{id}"}, method=RequestMethod.GET)
    public @ResponseBody  Map<String, String> getOneItem(@RequestParam ItemType itemType, @RequestParam Integer id){
        ClientResponseCreator clientResponseCreator = ClientItemResponseCreatorFactory.getItemModelCreator(itemType, genericItemService);
        return clientResponseCreator.createOneItemResponse(id);
    }
}
