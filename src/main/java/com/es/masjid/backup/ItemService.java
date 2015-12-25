package com.es.masjid.backup;

import java.util.List;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;

public interface ItemService {
	
	void createItem(Item item);
	
	List<Item> getValidItemsByType(ItemType type);
	
	List<Item> getCurrentValidItemsByType(ItemType type);
	
	void deleteItem(Integer id);

}
