package com.es.masjid.madmin.repository;

import java.util.List;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;

public interface ItemRepositoryCustom {
	
	List<Item> getValidItemsByType(ItemType type);
	
	List<Item> getCurrentValidItemsByType(ItemType type);

}
