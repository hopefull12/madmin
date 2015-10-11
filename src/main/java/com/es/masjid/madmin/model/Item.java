package com.es.masjid.madmin.model;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "valid_from")
	private Date validFrom;
	
	@Column(name = "valid_to")
	private Date validTo;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ItemType itemType;
	
	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "short_desc")
	private String shortDescription;
	
	@Column(name = "long_desc")
	private String longDescription;
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	@Column(name = "date_modified")
	private Date dateModified;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;

    @OneToMany(mappedBy="item", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Document> attachments = new HashSet<Document>();

    public Document getFirstAttachment(){
        Document document = null;
        if(attachments != null && attachments.size() > 0){
           document = (Document)attachments.toArray()[0];
        }
        return document;
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

    public Set<Document> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Document> attachments) {
        this.attachments = attachments;
    }
}
