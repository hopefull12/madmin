package com.es.masjid.madmin.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.masjid.madmin.controller.DocumentController;
import com.es.masjid.madmin.model.Document;
import com.es.masjid.madmin.model.DocumentBean;
import com.es.masjid.madmin.repository.DocumentRepository;
import com.es.masjid.madmin.util.MasjidUtility;

@Service
public class DocumentServiceImpl implements DocumentService {

	Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MasjidUtility utility;		
	
	@Transactional(readOnly = true)	
	public Document findOne(Integer id){
		return documentRepository.findOne(id);
	}

	@Transactional    
	public void createDocument(Document d){
		documentRepository.save(d);
	}
	
	@Transactional(readOnly = true)	
	public List<Document> findAll(){
		return documentRepository.findAll();
	}
	
	public List<Document> getValidDocuments() {
		return documentRepository.getValidDocuments();
	}
	
	/**
	 * All the files except .csv prayer schedule files are created by this method
	 */
	@Transactional
	public void createFile(DocumentBean bean) throws IOException{
		
		String fileNameSuffix = new SimpleDateFormat("yyyyMMd-hhmmss").format(new Date());
		logger.debug("File name suffix: "+fileNameSuffix);
		//write file meta data to the db
		Document doc = new Document();
		doc.setDisplayName(bean.getDisplayFileName());
		doc.setName(fileNameSuffix+ "-" +bean.getFile().getOriginalFilename());
		doc.setCategory(bean.getCategory());
		doc.setValidFrom(bean.getValidFrom());
		doc.setValidTo(bean.getValidTo());
		documentRepository.save(doc);
		logger.debug("Successfully saved file "+bean.getFile().getName()+" meta-data  to file Database");
		
		//write file to file system
		utility.saveFile(fileNameSuffix+ "-" +bean.getFile().getOriginalFilename(), bean.getFile());
		logger.debug("Successfully saved file "+bean.getFile().getName()+" to file system");
	}
	
	/**
	 * All the uploaded PDF files are retreived using below method for download or reading
	 */
	public File getFileByFileName(String fileName){
		//fileName = fileName + ".pdf";
		return utility.getFileByFileName(fileName);
	}

	@Override
	public void deleteDocument(Integer id) {
		documentRepository.delete(id);		
	}
}
