package com.es.masjid.madmin.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.es.masjid.madmin.model.NewsItem;
import com.es.masjid.madmin.repository.NewsRepository;

public class CommonServiceImpl implements CommonService {
	

	Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private NewsRepository newsRepository;	

	@Transactional  
	public void create(NewsItem bean) throws IOException {
		newsRepository.save(bean);
		
	}

	@Transactional  
	public List<NewsItem> findAll() {
		
		return newsRepository.findAll();
	}
	
	@Transactional  
	public List<NewsItem> findAllValidNewsItems(){
		return newsRepository.findAllValidNewsItems();
	}

}
