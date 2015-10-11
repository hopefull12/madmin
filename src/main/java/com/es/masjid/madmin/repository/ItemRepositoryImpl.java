package com.es.masjid.madmin.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

	
	@PersistenceContext
	private EntityManager em;	
		
	public List<Item> getValidItemsByType(ItemType type) {
		
		TypedQuery<Item> query = em.createQuery("select i from Item i where i.validTo >= :currentDate and type = :type", Item.class);		
		return query.setParameter("currentDate", new Date()).setParameter("type", type).getResultList();
	}	
	
	public List<Item> getCurrentValidItemsByType(ItemType type){
		TypedQuery<Item> query = em.createQuery("select i from Item i where i.validFrom <= :currentDate and i.validTo >= :currentDate and type = :type", Item.class);		
		return query.setParameter("currentDate", new Date()).setParameter("type", type).getResultList();
		
	}

}
