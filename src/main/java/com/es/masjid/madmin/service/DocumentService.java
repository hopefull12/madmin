package com.es.masjid.madmin.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.es.masjid.madmin.model.Document;
import com.es.masjid.madmin.model.DocumentBean;

public interface DocumentService {
	
	void createFile(DocumentBean bean) throws IOException;
	
	List<Document> findAll();
	
	File getFileByFileName(String fileName);
	
	List<Document> getValidDocuments();
	
	void deleteDocument(Integer id);

}
