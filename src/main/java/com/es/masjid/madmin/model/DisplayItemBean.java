package com.es.masjid.madmin.model;

import java.util.Date;

/**
 * Created by myachb on 10/7/2015.
 */
public class DisplayItemBean {

    private Integer id;

    private Date validFrom;

    private Date validTo;

    private ItemType itemType;

    private Category category;

    private String name;

    private String displayName;

    private String shortDescription;

    private String longDescription;

    private Date dateCreated;

    private Date dateModified;

    private String createdBy;

    private String modifiedBy;

    private String attachment1Name;
    private String attachment1FileName;

    private String attachment2Name;
    private String attachment2FileName;

    private String attachment3Name;
    private String attachment3FileName;

    public DisplayItemBean(Item item){
        this.id = item.getId();
        this.validFrom = item.getValidFrom();
        this.validTo = item.getValidTo();
        this.itemType = item.getItemType();
        this.category = item.getCategory();
        this.name = item.getName();
        this.displayName = item.getDisplayName();
        this.shortDescription = item.getShortDescription();
        this.longDescription = item.getLongDescription();
        this.dateCreated = item.getDateCreated();
        this.dateModified = item.getDateModified();
        this.createdBy = item.getCreatedBy();
        this.modifiedBy = item.getModifiedBy();
        Object[] attachments = (item.getAttachments() != null) ? item.getAttachments().toArray() : null ;
        if(attachments != null && attachments.length > 0){
            this.attachment1Name = ((Document)attachments[0]).getDisplayName();
            this.attachment1FileName = ((Document)attachments[0]).getName();
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getAttachment1Name() {
        return attachment1Name;
    }

    public void setAttachment1Name(String attachment1Name) {
        this.attachment1Name = attachment1Name;
    }

    public String getAttachment2Name() {
        return attachment2Name;
    }

    public void setAttachment2Name(String attachment2Name) {
        this.attachment2Name = attachment2Name;
    }

    public String getAttachment3Name() {
        return attachment3Name;
    }

    public void setAttachment3Name(String attachment3Name) {
        this.attachment3Name = attachment3Name;
    }

	public String getAttachment1FileName() {
		return attachment1FileName;
	}

	public void setAttachment1FileName(String attachment1FileName) {
		this.attachment1FileName = attachment1FileName;
	}

	public String getAttachment2FileName() {
		return attachment2FileName;
	}

	public void setAttachment2FileName(String attachment2FileName) {
		this.attachment2FileName = attachment2FileName;
	}

	public String getAttachment3FileName() {
		return attachment3FileName;
	}

	public void setAttachment3FileName(String attachment3FileName) {
		this.attachment3FileName = attachment3FileName;
	}
    
}
