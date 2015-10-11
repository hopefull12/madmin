package com.es.masjid.madmin.controller.web;

import com.es.masjid.madmin.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by myachb on 10/9/2015.
 */

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    DocumentService docService;

    @RequestMapping(value = "/{fileName}/", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
        return new FileSystemResource(docService.getFileByFileName(fileName));
    }

}
