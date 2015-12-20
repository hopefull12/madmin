package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemBean;
import com.es.masjid.madmin.model.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

/**
 * Created by myachb on 10/6/2015.
 */
public interface GenericItemService {

    void createItem(ItemBean itemBean) throws IOException;

    List<Item> getCurrentAndFutureValidItemsByType(ItemType type);

    List<Item> getCurrentValidItemsByType(ItemType type);

    Page<Item> getCurrentValidItemsByType(ItemType type, Pageable pageRequest);

    Page<Item> findAllItems(ItemType itemType, Pageable pageRequest);

    void deleteItem(Integer id);

    Item findOne(Integer id);
}
