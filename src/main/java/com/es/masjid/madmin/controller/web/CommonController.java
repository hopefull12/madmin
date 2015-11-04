package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.model.EmailBean;
import com.es.masjid.madmin.service.EmailService;
import com.es.masjid.madmin.service.EmailServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by myachb on 11/2/2015.
 */

@PropertySource("classpath:application.properties")
@Controller
public class CommonController {

    Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping(value={"/sendEmailTest"}, method= RequestMethod.POST)
    public @ResponseBody
    String sendEmail(@ModelAttribute EmailBean emailBean) {
        emailService.sendEmail(emailBean);
        return "";
    }

    /**
     *
     * <form method="POST" action="http://localhost:8080/cms/c" id="frmRegister" name ="frmRegister">
     *
     */
    @RequestMapping(value={"/sendEmail"}, method= RequestMethod.GET)
    public @ResponseBody
    String sendEmail(@RequestParam(value="fromEmail", required=false) String fromEmail,
                     @RequestParam(value="name", required=false) String name,
                     @RequestParam(value="phone", required=false) String phone,
                     @RequestParam(value="subject", required=false) String subject,
                     @RequestParam(value="body", required=false) String body) {

        EmailBean emailBean = new EmailBean(fromEmail, name, subject, body, phone);
        emailService.sendEmail(emailBean);
        return "";
    }

    /**
     *
     * <form method="POST" action="http://localhost:8080/cms/c" id="frmRegister" name ="frmRegister" enctype="application/x-www-form-urlencoded">
     *
     */
    @RequestMapping(value = "/create_customer", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public @ResponseBody
    String sendEmail2(@RequestParam(value="fromEmail", required=false) String fromEmail,
                      @RequestParam(value="name", required=false) String name,
                      @RequestParam(value="body", required=false) String phone,
                      @RequestParam(value="subject", required=false) String subject,
                      @RequestParam(value="body", required=false) String body) {

        EmailBean emailBean = new EmailBean(fromEmail, name, subject, body, phone);
        emailService.sendEmail(emailBean);
        return "";
    }

}
