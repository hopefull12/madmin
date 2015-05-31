package com.es.masjid.madmin.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PrayerTimesUpload {
	
	private String month;
	private Date validFrom;
	private Date validTo;
	
	private MultipartFile prayerTimesFile;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public MultipartFile getPrayerTimesFile() {
		return prayerTimesFile;
	}

	public void setPrayerTimesFile(MultipartFile prayerTimesFile) {
		this.prayerTimesFile = prayerTimesFile;
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
	
	
	

}
