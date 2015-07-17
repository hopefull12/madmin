package com.es.masjid.madmin.repository;

import java.util.List;

import com.es.masjid.madmin.model.NewsItem;

public interface NewsRepositoryCustom {
	
	List<NewsItem> findAllValidNewsItems();

}
