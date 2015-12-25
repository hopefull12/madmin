package com.es.masjid.backup;

import java.io.IOException;
import java.util.List;

import com.es.masjid.madmin.model.NewsItem;

public interface CommonService {
	
	void create(NewsItem bean) throws IOException;
	
	List<NewsItem> findAll();	
	
	

}
