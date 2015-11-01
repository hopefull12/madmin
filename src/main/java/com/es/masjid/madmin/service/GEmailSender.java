package com.es.masjid.madmin.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GEmailSender {

    public static void main(String[] args) {
        String host = "mail.yahibaba.com";
        String from = "support@yahibaba.com";
        String password = "Krishna1";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        try{
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, "masjid@masjidalhuda.org");
            message.setSubject("This is a Test");
            message.setText("Email sent by the system.");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);//CAUSES EXCEPTION
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Email sent successfully");
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

}
