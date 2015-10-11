package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.ItemType;

import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 10/9/2015.
 */
public interface ClientResponseCreator {
    List<Map<String, String>> createAllItemsResponse(ItemType itemType);
    Map<String, String> createOneItemResponse(Integer id);
}
