package com.sandata.lab.rules.data.service.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by thanhxle on 10/19/2016.
 */
public class CommonUtils {

    private CommonUtils() {

    }

    public static Date createDateWithOffset(Date startDate, int i) {
        Calendar calendar = dateToCalendar(startDate);
        calendar.add(Calendar.MINUTE, i);
        return calendar.getTime();
    }

    private static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String convertDateToString (String formatPattern, Date input) {

        DateFormat dateFormat = new SimpleDateFormat(formatPattern);
        return  dateFormat.format(input);
    }

    public static String convertNumListToStringParams (List<Integer> values) {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i< values.size() ; i ++ ) {

            resultStr.append(values.get(i));
            if(i < values.size() - 1) {
                resultStr.append(",");
            }

        }

        return resultStr.toString();
    }



}
