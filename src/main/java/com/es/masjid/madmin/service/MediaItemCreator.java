package com.es.masjid.madmin.service;

public class MediaItemCreator extends ItemCreator  {

	@Override
	protected void setCustomProperties() {
		this.item.setDisplayName(this.itemBean.getDisplayName());
		this.item.setName(this.itemBean.getName());
	}
}
