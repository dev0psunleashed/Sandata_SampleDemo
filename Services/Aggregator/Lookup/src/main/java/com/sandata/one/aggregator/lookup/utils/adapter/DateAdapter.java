package com.sandata.one.aggregator.lookup.utils.adapter;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date: 8/29/15
 * Time: 9:45 AM
 */

public class DateAdapter {

    public static Date parseDate(final String date) {
        return DatatypeConverter.parseDate(date).getTime();
    }

    public static String printDate(final Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeConverter.printDate(calendar);
    }
}
