package com.sandata.lab.rules.exceptions.drools;


import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/17/2015
 * Time: 2:02 AM
 */
public class HelperFunctions {

    public static long diffDates(Date date1, Date date2) {
        return Math.abs(date2.getTime() - date1.getTime());
    }
    public static Date CreateDateWithOffsetHourMinute(Date start, int i, int j) {
        Calendar calendar = DateToCalendar(start);
        calendar.add(Calendar.HOUR_OF_DAY, i);
        calendar.add(Calendar.MINUTE, j);
        return calendar.getTime();
    }

    public static Date CreateDateWithOffset(Date startDate, int i) {
        Calendar calendar = DateToCalendar(startDate);
        calendar.add(Calendar.HOUR_OF_DAY, i);
        return calendar.getTime();
    }

    private static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
