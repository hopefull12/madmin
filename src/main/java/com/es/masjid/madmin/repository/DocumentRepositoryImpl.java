package com.es.masjid.madmin.repository;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.es.masjid.madmin.model.Document;

public class DocumentRepositoryImpl implements DocumentRepositoryCustom{
	
	@PersistenceContext
	private EntityManager em;	

	public List<Document> getValidDocuments() {
		
		TypedQuery<Document> query = em.createQuery("select d from Document d where d.validFrom <= :currentDate and d.validTo >= :currentDate", Document.class);
		return query.setParameter("currentDate", new Date()).getResultList();				
	}

}
