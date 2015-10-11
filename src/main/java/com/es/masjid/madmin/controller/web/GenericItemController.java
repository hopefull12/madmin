package com.es.masjid.madmin.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.es.masjid.madmin.model.ItemBean;
import com.es.masjid.madmin.model.ItemType;

/**
 * Created by myachb on 10/6/2015.
 */

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("/item")
public class GenericItemController {

    Logger logger = LoggerFactory.getLogger(GenericItemController.class);
    
    @Autowired
    private GenericItemControllerUtil genericItemControllerUtil;

    @RequestMapping(value={"/all"}, method= RequestMethod.GET)
    public ModelAndView getItems(@RequestParam ItemType itemType) {
        return genericItemControllerUtil.getItems(itemType, "itemsTile");
    }

    @RequestMapping(value={"/create"}, method=RequestMethod.POST)
    public ModelAndView createItem(@ModelAttribute ItemBean itemBean,
                                   BindingResult result,
                                   final RedirectAttributes redirectAttributes) {
        return genericItemControllerUtil.createItem(itemBean, result, redirectAttributes, "/item/all?itemType="+itemBean.getItemType(), "itemsTile");
    }
    
  @RequestMapping(value={"/delete"}, method=RequestMethod.GET)
  public ModelAndView deleteNewsItem(@RequestParam Integer id, @RequestParam ItemType itemType, final RedirectAttributes redirectAttributes) {
      return genericItemControllerUtil.deleteItem(id, itemType, redirectAttributes, "/item/all?itemType="+itemType);
  }    

}
