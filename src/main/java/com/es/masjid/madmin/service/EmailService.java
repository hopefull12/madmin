package com.es.masjid.madmin.service;

import com.es.masjid.madmin.model.EmailBean;

public interface EmailService {

	public abstract void sendEmail(EmailBean emailBean);

}