package com.es.masjid.madmin.web.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@PropertySource("classpath:application.properties")
@Controller
public class HomeController {

    @RequestMapping(value={"/"}, method= RequestMethod.GET)
    public ModelAndView home() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("redirect:/item/all.html?itemType=NEWS");
        return mav;
    }	
	
}
