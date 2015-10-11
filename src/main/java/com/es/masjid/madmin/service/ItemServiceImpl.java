package com.es.masjid.madmin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.es.masjid.madmin.model.Category;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.repository.ItemRepository;

@Component
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;

	@Transactional
	public void createItem(Item item) {	
		
		item.setType(ItemType.NEWS);
		item.setCategory(Category.NONE);
		item.setCreatedBy("default");
		item.setModifiedBy("default");
		item.setDateCreated(new Date());
		item.setDateModified(new Date());
		
		itemRepository.save(item);
	}
	
	@Transactional
	public void deleteItem(Integer id){
		itemRepository.delete(id);
	}

	public List<Item> getValidItemsByType(ItemType type) {		
		return itemRepository.getValidItemsByType(type);
	}
	
	public List<Item> getCurrentValidItemsByType(ItemType type){
		return itemRepository.getCurrentValidItemsByType(type);
	}

}
