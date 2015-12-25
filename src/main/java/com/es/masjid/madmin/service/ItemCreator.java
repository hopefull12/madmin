package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.Document;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by myachb on 10/6/2015.
 */
@Component
public class ItemCreator {

    protected Item item = null;
    protected ItemBean itemBean = null;

    public Item create(ItemBean itemBean){
        
        initialize(itemBean);
        preProcessing();
        validate();
        setBaseProperties();
        setCustomProperties();
        setAttachments();

        return item;
    }

    private void initialize(ItemBean itemBean){
        this.item = new Item();
        this.itemBean = itemBean;
    }

    protected void preProcessing(){}

    protected void validate(){

    }

    protected void setBaseProperties(){
        this.item.setItemType(this.itemBean.getItemType());
        this.item.setCategory(this.itemBean.getCategory());
        this.item.setValidFrom(this.itemBean.getValidFrom());
        this.item.setValidTo(this.itemBean.getValidTo());
        this.item.setShortDescription(this.itemBean.getShortDescription());
    }

    protected void setCustomProperties(){
        // for subclasses to add custom properties
    }

    protected void setAttachments(){
       // Set<Document> documents = new HashSet<>();
        if(this.itemBean.getAttachments() != null){
	        for(MultipartFile multipartFile : this.itemBean.getAttachments()){
	            Document document = new Document();
                document.setName(setDocumentName(multipartFile));
	            document.setDisplayName(itemBean.getDisplayName());	           
	            document.setFile(multipartFile);
	            document.setItem(this.item);
	            document.setCategory(itemBean.getCategory().toString());
	            document.setValidFrom(item.getValidFrom());
	            document.setValidTo(item.getValidTo());
	            document.setSortOrder(1);
	            //documents.add(document);
	            this.item.getAttachments().add(document);
	        }
        }
    }

    protected String getFileNameSuffix(){
        return new SimpleDateFormat("yyyyMMd-hhmmss-SSS").format(new Date());
    }

    private String getFileExtension(String fileName){

        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    protected String setDocumentName(MultipartFile multipartFile){

        return getFileNameSuffix()+"."+getFileExtension(multipartFile.getOriginalFilename());
    }

}
