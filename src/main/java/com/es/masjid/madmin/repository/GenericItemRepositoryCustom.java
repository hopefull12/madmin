package com.es.masjid.madmin.repository;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;

import java.util.List;

/**
 * Created by myachb on 10/6/2015.
 */
public interface GenericItemRepositoryCustom {


    List<Item> getCurrentAndFutureValidItemsByType(ItemType type);

    List<Item> getCurrentValidItemsByType(ItemType type);
}
