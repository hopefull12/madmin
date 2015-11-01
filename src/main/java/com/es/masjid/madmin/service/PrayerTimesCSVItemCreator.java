package com.es.masjid.madmin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by myachb on 10/6/2015.
 */
public class PrayerTimesCSVItemCreator extends ItemCreator {

    private final String PRAYER_TIMES_FILE_NAME = "prayertimes.csv";

    public void preProcessing(){
        Date temp = null;

        //Basing on the month create a date
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
        try {
            temp = sdf.parse(itemBean.getMonth() + "/01/2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //use the above date to create a calendar and then the first and last day
        Calendar cal = Calendar.getInstance();
        cal.setTime(temp);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date firstDateOfMonth = cal.getTime();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDateOfMonth = cal.getTime();

        this.itemBean.setValidFrom(firstDateOfMonth);
        this.itemBean.setValidTo(lastDateOfMonth);
    }

    protected String getFileNameSuffix(){
        return itemBean.getMonth().toLowerCase()+"-"+PRAYER_TIMES_FILE_NAME;
    }

}
