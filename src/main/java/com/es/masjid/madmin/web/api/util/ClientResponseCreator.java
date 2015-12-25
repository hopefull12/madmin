package com.es.masjid.madmin.web.api.util;

import com.es.masjid.madmin.model.ItemType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by myachb on 10/9/2015.
 */
public interface ClientResponseCreator {
    List<Map<String, String>> createAllItemsResponse(ItemType itemType);
    List<Map<String, String>> createAllItemsResponse(ItemType itemType, Pageable pageRequest);
    Map<String, String> createOneItemResponse(Integer id);
}
