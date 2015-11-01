package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.Document;
import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemBean;
import com.es.masjid.madmin.model.ItemType;
import com.es.masjid.madmin.repository.DocumentRepository;
import com.es.masjid.madmin.repository.GenericItemRepository;
import com.es.masjid.madmin.util.ClientContext;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by myachb on 10/6/2015.
 */
@Component
public class GenericItemServiceImpl implements GenericItemService {

    @Resource
    private Environment env;

    private final String DATA_PATH = "data.path";
    private final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";

    Logger logger = LoggerFactory.getLogger(GenericItemServiceImpl.class);
    
    @Autowired
    private GenericItemRepository genericItemRepository;
    
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void createItem(ItemBean itemBean) throws IOException {

        //create item and save
        Item item = (ItemCreatorFactory.getItemCreator(itemBean.getItemType())).create(itemBean);
        setAudit(item);
        
        genericItemRepository.save(item);

        //save attachments
        for(Document document: item.getAttachments()){
            saveAttachment(document);
        }
    }
    
    private void setAudit(Item item){
    	item.setDateCreated(new Date());
    	item.setDateModified(new Date());
    	item.setCreatedBy("DEFAULT");
    	item.setModifiedBy("DEFAULT");
    }

    @Override
    public List<Item> getCurrentAndFutureValidItemsByType(ItemType type) {
        return genericItemRepository.getCurrentAndFutureValidItemsByType(type);
    }

    @Override
    public List<Item> getCurrentValidItemsByType(ItemType type) {
        return genericItemRepository.getCurrentValidItemsByType(type);
    }

    @Transactional
    public void deleteItem(Integer id) {
        genericItemRepository.delete(id);
    }

    public Item findOne(Integer id){
        return genericItemRepository.findOne(id);
    }

    public void saveAttachment(Document document) throws IOException {
        try {

            String path = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/" + document.getName();

            logger.debug("The prayer times file path: "+path);

            File file = new File(path);

            if(!file.exists() && !file.isDirectory())
            {
                file.createNewFile();
                logger.debug("Created new file");
            }

            FileUtils.writeByteArrayToFile(file, document.getFile().getBytes());

            logger.info("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
        }
        catch (IOException e) {
            throw e;
        }
    }

    public File getAttachment(String fileName){

        String filePath = env.getRequiredProperty(DATA_PATH) + ClientContext.getClientId() + "/" + fileName;

        return new File(filePath);
    }
}
