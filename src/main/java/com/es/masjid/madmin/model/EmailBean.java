package com.es.masjid.madmin.model;

public class EmailBean {
	
	private String fromEmail;
	private String name;
	private String phone;
	private String subject;
	private String body;
	
	public EmailBean(String fromEmail, String name, String subject, String body, String phone){
		this.fromEmail=fromEmail;
		this.name=name;
		this.subject=subject;
		this.body=body;
	}
	
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
