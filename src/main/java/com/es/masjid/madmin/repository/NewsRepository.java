package com.es.masjid.madmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.masjid.madmin.model.NewsItem;

public interface NewsRepository extends JpaRepository<NewsItem, Integer>, NewsRepositoryCustom {

}
