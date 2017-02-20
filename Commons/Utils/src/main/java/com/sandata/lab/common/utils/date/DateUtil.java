/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.common.utils.date;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;

import org.apache.camel.Exchange;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date: 1/28/14
 * Time: 7:39 AM
 */

public class DateUtil {

    public static org.slf4j.Logger agencyExceptionLogger = LoggerFactory.getLogger("agencyException");
    private static String EMPTY_STRING = "";

    private static String SANDATA_TIME_FORMAT = "h:mm aa";
    private static String SANDATA_DATE_FORMAT = "MMM dd, yyyy";
    private static String SANDATA_DATETIME_FORMAT = "yyyy-MM-dd HH:mm"; //"yyyy-MM-dd h:mm aa"

    public static String SANDATA_DATABASE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static String SANDATA_DATABASE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String SANDATA_UTC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";

    private static final DateTimeFormatter SANDATA_TIME_FORMATTER = DateTimeFormat.forPattern(SANDATA_TIME_FORMAT);


    public static String convertJodaDateTimetoString(LocalDate localDate, LocalTime localTime,String dateFormat) {
        LocalDateTime localDateTime = new LocalDateTime(localDate.toString()+"T"+ localTime.toString());
        DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
        return localDateTime.toString(fmt);
    }

    public static String format(LocalDateTime dateTime) {

        if(dateTime == null) return EMPTY_STRING;

        return DateTimeFormat.forPattern(SANDATA_DATETIME_FORMAT).print(dateTime);
    }

    public static String format(LocalDate date) {

        if(date == null) return EMPTY_STRING;

        return DateTimeFormat.forPattern(SANDATA_DATE_FORMAT).print(date);
    }

    public static String format(LocalDate date, String format) {

        if(date == null) return EMPTY_STRING;

        return DateTimeFormat.forPattern(format).print(date);
    }

    public static String format(LocalTime time) {

        if(time == null) return EMPTY_STRING;

        return time.toString(SANDATA_TIME_FORMAT).trim();
    }

    public static String format(LocalTime time, String format) {

        if(time == null) return EMPTY_STRING;

        return time.toString(format).trim();
    }

    /**
     * Parse a string of format "hh:mm aa" to <code>LocalTime</code>
     *
     * @param shortTime
     * @return
     */
    public static LocalTime parseShortTime(String shortTime) {
        return LocalTime.parse(shortTime, SANDATA_TIME_FORMATTER);
    }

    public static LocalDate formatEvvDateToLocalDate(String date){
        try{
            final DateTimeFormatter dtf = DateTimeFormat.forPattern("MMddyyyy");
            return dtf.parseLocalDate(date);
        }
        catch(Exception e){
            agencyExceptionLogger.info("formatEvvDateToLocalDate Exception"+e.toString());
            return null;
        }
    }

    public static String getDateTimeFromSqlTimestamp(Timestamp timestamp){
        if(timestamp != null){
            return timestamp.toString().substring(0,19);
        }
        return "";
    }

    public static String StringFromFormat(final Date date, final String format) throws NullPointerException {

        if (date == null) {
            throw  new NullPointerException("DateUtil: StringFromFormat: date == null");
        }

        if (format == null) {
            throw  new NullPointerException("DateUtil: StringFromFormat: format == null");
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static Date DateFromStringFormat(final String date, final String format) throws NullPointerException, ParseException {

        if (date == null) {
            throw  new NullPointerException("DateUtil: DateFromStringFormat: date == null");
        }

        if (format == null) {
            throw  new NullPointerException("DateUtil: DateFromStringFormat: format == null");
        }

        DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);

        return dateFormat.parse(date);
    }

    public static Date ConvertDateToUTC(Date localDate) throws ParseException {
        DateTimeZone tz = DateTimeZone.getDefault();
        Date utcDate = new Date(tz.convertLocalToUTC(localDate.getTime(), false));
        return utcDate;
    }

    public static boolean IsNull(Date date) throws SandataRuntimeException {

        if (date == null) {
            return true;
        }

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("DateUtil: IsNull: EXCEPTION: %s", e.getMessage()));
        }

        return false;
    }

    public static boolean IsValidDate(String dateString, String format, String fieldName) throws SandataRuntimeException {

        if (!StringUtil.IsNullOrEmpty(dateString)) {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);

            try {
                Date date = dateFormat.parse(dateString);
                return true;
            }
            catch (ParseException pe) {
                throw new SandataRuntimeException(String.format("%s: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    fieldName, dateString));
            }
        }

        return false;
    }

    public static void ValidateFromToDateTime(
                String fromDate,
                String fromTime,
                String toDate,
                String toTime,
                Exchange exchange) throws SandataRuntimeException {

        if (!StringUtil.IsNullOrEmpty(fromDate)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            try {
                Date date = format.parse(fromDate);

            } catch (ParseException pe) {
                throw new SandataRuntimeException(exchange,
                        String.format("From Date: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                fromDate));
            }
        } else {
            throw new SandataRuntimeException("From Date (from_date) is null or empty!");
        }

        if (StringUtil.IsNullOrEmpty(fromTime)) {
            throw new SandataRuntimeException("From Time (from_time) is null or empty!");
        }

        String[] fromTimeArray = fromTime.split(":");
        if (fromTimeArray.length != 2) {
            throw new SandataRuntimeException(exchange,
                    String.format("From Time: [%s]: Is NOT a valid time and/or the format is incorrect!",
                            fromTime));
        } else {

            try {
                Integer.parseInt(fromTimeArray[0]);
                Integer.parseInt(fromTimeArray[1]);
            } catch (NumberFormatException nfe) {
                throw new SandataRuntimeException(exchange,
                        String.format("From Time: NumberFormatException: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                fromTime));
            }
        }

        if (!StringUtil.IsNullOrEmpty(toDate)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            try {
                Date date = format.parse(toDate);

            } catch (ParseException pe) {
                throw new SandataRuntimeException(exchange,
                        String.format("To Date: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                toDate));
            }
        } else {
            throw new SandataRuntimeException("To Date (to_date) is null or empty!");
        }

        if (StringUtil.IsNullOrEmpty(toTime)) {
            throw new SandataRuntimeException("To Time (to_time) is null or empty!");
        }

        String[] toTimeArray = toTime.split(":");
        if (toTimeArray.length != 2) {
            throw new SandataRuntimeException(exchange,
                    String.format("To Time: [%s]: Is NOT a valid time and/or the format is incorrect!",
                            toTime));
        } else {

            try {
                Integer.parseInt(toTimeArray[0]);
                Integer.parseInt(toTimeArray[1]);
            } catch (NumberFormatException nfe) {
                throw new SandataRuntimeException(exchange,
                        String.format("To Time: NumberFormatException: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                toTime));
            }
        }
    }

    /*
        http://www.calculatorsoup.com/calculators/time/time-to-decimal-calculator.php
        http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
        http://stackoverflow.com/questions/6118922/convert-seconds-value-to-hours-minutes-seconds
     */
    public static float Hours(Date startDate, Date endDate) throws SandataRuntimeException {

        if (DateUtil.IsNull(startDate)) {
            throw new SandataRuntimeException("DateUtil: Hours: startDate == null");
        }

        if (DateUtil.IsNull(endDate)) {
            throw new SandataRuntimeException("DateUtil: Hours: endDate == null");
        }

        if (endDate.before(startDate)) {
            throw new SandataRuntimeException("DateUtil: Hours: endDate before startDate");
        }

        try {
            long secs = (endDate.getTime() - startDate.getTime()) / 1000;
            float hours = (int) secs / 3600;
            float minutes = (int) secs % 3600f / 60f;
            float seconds = (int) secs % 60f;

            return (float) Math.round((hours + (minutes / 60f) + (seconds / 3600f)) * 100f) / 100f;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("DateUtil: Hours: EXCEPTION: %s", e.getMessage()));
        }
    }

    public static long Seconds(Date startDate, Date endDate) throws SandataRuntimeException {

        if (DateUtil.IsNull(startDate)) {
            throw new SandataRuntimeException("DateUtil: Seconds: startDate == null");
        }

        if (DateUtil.IsNull(endDate)) {
            throw new SandataRuntimeException("DateUtil: Seconds: endDate == null");
        }

        if (endDate.before(startDate)) {
            throw new SandataRuntimeException("DateUtil: Seconds: endDate before startDate");
        }

        try {
            return (endDate.getTime() - startDate.getTime()) / 1000;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("DateUtil: Seconds: EXCEPTION: %s", e.getMessage()));
        }
    }

    /**
     * This will format the input string '2016-09-23T04:00:00Z' to '2016-09-23'
     * @param stringUTC
     * @return
     */
    public static String convertStringUTCtoStringDate(String stringUTC) {
        return convertStringUTCtoStringDateFormat(stringUTC, SANDATA_DATABASE_DATE_FORMAT, "");
    }

    /**
     * This will format the input string '2016-09-23T04:00:00Z' to '2016-09-23'
     * And if the input cannot be parsed, throws exception with the input field_name
     * @param stringUTC
     * @param fieldName
     * @return
     */
    public static String convertStringUTCtoStringDate(String stringUTC, String fieldName) {
        return convertStringUTCtoStringDateFormat(stringUTC, SANDATA_DATABASE_DATE_FORMAT, fieldName);
    }

    /**
     * This will format the input string '2016-09-23T04:00:00Z' to '2016-09-23 04:00'
     * @param stringUTC
     * @return
     */
    public static String convertStringUTCtoStringDateTime(String stringUTC) {
        return convertStringUTCtoStringDateFormat(stringUTC, SANDATA_DATABASE_DATETIME_FORMAT, "");
    }

    /**
     * This will format the input string '2016-09-23T04:00:00Z' to '2016-09-23 04:00'
     * And if the input cannot be parsed, throws exception with the input field_name
     * @param stringUTC
     * @param fieldName
     * @return
     */
    public static String convertStringUTCtoStringDateTime(String stringUTC, String fieldName) {
        return convertStringUTCtoStringDateFormat(stringUTC, SANDATA_DATABASE_DATETIME_FORMAT, fieldName);
    }

    private static String convertStringUTCtoStringDateFormat(String stringUTC, String format, String fieldName) {

        String validatedName = StringUtil.IsNullOrEmpty(fieldName) ? "the input UTC date" : fieldName;

        if(StringUtil.IsNullOrEmpty(stringUTC)){
            throw new SandataRuntimeException(String.format("DateUtil: convertStringUTCtoStringDateFormat: [%s] is empty or null", validatedName));
        }

        DateFormat dateFormat = new SimpleDateFormat(SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);
        Date utcDate = null;
        try {
            utcDate = dateFormat.parse(stringUTC);
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("DateUtil: convertStringUTCtoStringDateFormat: Parsing [%s] with exception: %s", validatedName, e.getMessage()));
        }

        return new SimpleDateFormat(format).format(utcDate);
    }
    
    public static Date convertUTCToTargetTimeZone(Date date, String timezoneName) {
        return new LocalDateTime(date.getTime())
                .toDateTime(DateTimeZone.UTC)
                .toDateTime(DateTimeZone.forID(timezoneName))
                .toLocalDateTime()
                .toDate();
    }
    
    public static Date convertFromTimeZoneToUTC(Date date, String timezoneName) {
        return new LocalDateTime(date.getTime())
                .toDateTime(DateTimeZone.forID(timezoneName))
                .toDateTime(DateTimeZone.UTC)
                .toLocalDateTime()
                .toDate();
    }
    
    public static Date combineDateAndTime(Date date, LocalTime time) {
        return new LocalDateTime(date.getTime())
                .withHourOfDay(time.getHourOfDay())
                .withMinuteOfHour(time.getMinuteOfHour())
                .withSecondOfMinute(time.getSecondOfMinute())
                .withMillisOfSecond(time.getMillisOfSecond())
                .toDate();
    }
}
