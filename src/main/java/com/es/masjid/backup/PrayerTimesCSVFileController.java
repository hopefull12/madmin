package com.es.masjid.backup;

import com.es.masjid.madmin.util.MasjidUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * Created by myachb on 10/6/2015.
 */
public class PrayerTimesCSVFileController {

    private static final String DATA_PATH = "data.path";
    private static final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";

    Logger logger = LoggerFactory.getLogger(PrayerTimesCSVFileController.class);

    private ServletContext servletContext;

    @Autowired
    private MasjidUtility utility;

    @Resource
    private Environment env;
}
