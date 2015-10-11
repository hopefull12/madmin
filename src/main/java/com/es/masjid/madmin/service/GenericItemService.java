package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemBean;
import com.es.masjid.madmin.model.ItemType;

import java.io.IOException;
import java.util.List;

/**
 * Created by myachb on 10/6/2015.
 */
public interface GenericItemService {

    void createItem(ItemBean itemBean) throws IOException;

    List<Item> getCurrentAndFutureValidItemsByType(ItemType type);

    List<Item> getCurrentValidItemsByType(ItemType type);

    void deleteItem(Integer id);

    Item findOne(Integer id);
}
