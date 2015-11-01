package com.es.masjid.madmin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Properties;

/**
 * Created by myachb on 10/27/2015.
 */
public class EmailServiceImpl {

	Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    
    public void sendEmail(String from1, String body){
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
            message.addRecipients(Message.RecipientType.TO, "info@masjidalhuda.org");
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
    
//
//    public void sendEmail(final Order order) throws MessagingException {
//
////        mailSender.setHost("mail.yahibaba.com");
////        mailSender.setPort(8025);
////        mailSender.setUsername("amohammad@yahibaba.com");
////        mailSender.setPassword("Papimachicago1");
//        //mailSender.set
//        //.order.mailSender.s
//        String host = "mail.yahibaba.com";
//        String from = "support@yahibaba.com";
//        String password = "Krishna1";
//        Properties props = System.getProperties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.user", from);
//        props.put("mail.smtp.password", password);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        mailSender.setJavaMailProperties(props);
//        mailSender.setUsername(from);
//        mailSender.setPassword(password);
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setTo("support@yahibaba.com");
//        helper.setText("This is a test email from Muneer");
//        helper.setSubject("Test");
//        helper.setReplyTo("muneer.yachb@gmail.com");
//
//        mailSender.send(message);
//    }

//    public void sendEmailWithAttachment() throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//
//// use the true flag to indicate you need a multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo("mail.masjid@masjidalhuda.com");
//
//        helper.setText("Check out this image!");
//
//// let's attach the infamous windows Sample file (this time copied to c:/)
//        FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
//        helper.addAttachment("CoolImage.jpg", file);
//
//        mailSender.send(message);
//    }

//    public void sendEmailOld(final Order order) {
//
//        // Do the business calculations...
//
//        // Call the collaborators to persist the order...
//
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//
//                mimeMessage.setRecipient(Message.RecipientType.TO,
//                        new InternetAddress("mail.masjid@masjidalhuda.com"));
//                mimeMessage.setFrom(new InternetAddress("mail@mycompany.com"));
//                mimeMessage.setText("This is the body!!");
//            }
//        };
//
//        try {
//            this.mailSender.send(pnoreparator);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }

    public static void main(String args[]){
        EmailServiceImpl emailService = new EmailServiceImpl();
        //emailService.sendEmail();
        
    }


}
