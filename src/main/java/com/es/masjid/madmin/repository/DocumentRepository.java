package com.es.masjid.madmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.masjid.madmin.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>, DocumentRepositoryCustom{

}
