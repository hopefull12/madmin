package com.es.masjid.backup;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;

import java.util.List;

/**
 * Created by myachb on 10/5/2015.
 */
public interface NewItemService {

    void createItem(Item item);

    void deleteItem(Integer id);

    List<Item> getAllItemsByType(ItemType type);

    List<Item> getCurrentValidItemsByType(ItemType type);
}
