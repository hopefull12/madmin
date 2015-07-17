package com.es.masjid.madmin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.es.masjid.madmin.model.NewsItem;

public class NewsRepositoryImpl implements NewsRepositoryCustom{

	 @PersistenceContext
	  private EntityManager em;

	public List<NewsItem> findAllValidNewsItems() {

		return (List<NewsItem>)em.createQuery("select n from NewsItem n where n.startDate <= :currentDate and n.endDate >= :currentDate").getResultList();		
	}

}
