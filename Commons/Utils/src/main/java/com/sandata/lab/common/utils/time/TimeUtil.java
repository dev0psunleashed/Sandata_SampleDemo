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

package com.sandata.lab.common.utils.time;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Time utilities and helper methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final int MINUTES_IN_AN_HOUR = 60;
    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final float SECONDS_IN_AN_HOUR = 3600f;

    public static float TimeToHours(String timeString) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(timeString)) {
            return 0L;
        }

        float hours;
        float minutes;

        String[] timeArray = timeString.split(":");
        if (timeArray.length != 2) {
            throw new SandataRuntimeException(
                    String.format("TimeUtil: TimeToHours: EXCEPTION: [%s]: Is NOT a valid time and/or the format is incorrect! Expected format HH:mm!",
                            timeString));
        } else {

            try {
                hours = Long.parseLong(timeArray[0]);
                minutes = Long.parseLong(timeArray[1]);

                return (hours + (minutes / MINUTES_IN_AN_HOUR));

            } catch (NumberFormatException nfe) {
                throw new SandataRuntimeException(
                        String.format("TimeUtil: TimeToHours: EXCEPTION:  NumberFormatException: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                timeString));
            }
        }
    }

    public static long HoursToSeconds(final float hours) {

        return (long)(hours * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE);
    }

    public static BigDecimal SecondsToHours(final long seconds) {

        BigDecimal result = BigDecimal.valueOf(seconds / SECONDS_IN_AN_HOUR);
        return result.setScale(2, RoundingMode.CEILING);
    }

    public static long MinutesToSeconds(long minutes) {
        return minutes * SECONDS_IN_A_MINUTE;
    }

    public static String DateForTZ(Date date, String timeZoneName) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        formatter.setTimeZone(TimeZone.getTimeZone(timeZoneName));
        return formatter.format(calendar.getTime());
    }

    public static Date ToDate(final String dateString) throws ParseException {

        return formatter.parse(dateString);
    }

    public static String ToString(final Date date) {

        return formatter.format(date);
    }

    public static Date Convert(final Date date, final String timeZoneName) throws ParseException {

        formatter.setTimeZone(TimeZone.getTimeZone(timeZoneName));
        Date result = formatter.parse(formatter.format(date));
        return result;
    }

    public static boolean IsValidTime(String timeString, String fieldName) throws SandataRuntimeException {

        String[] fromTimeArray = timeString.split(":");
        if (fromTimeArray.length != 2) {
            throw new SandataRuntimeException(
                    String.format("%s: [%s]: Is NOT a valid time and/or the format is incorrect!",
                            fieldName, timeString));
        }
        else {

            try {
                int hour = Integer.parseInt(fromTimeArray[0]);
                if (hour < 0 || hour > 23) {
                    throw new SandataRuntimeException(
                            String.format("%s: NumberFormatException: HH: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                    fieldName, timeString));
                }

                int minutes = Integer.parseInt(fromTimeArray[1]);
                if (minutes < 0 || minutes >  59) {
                    throw new SandataRuntimeException(
                            String.format("%s: NumberFormatException: MM: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                    fieldName, timeString));
                }

                return true;
            }
            catch (NumberFormatException nfe) {
                throw new SandataRuntimeException(
                        String.format("%s: NumberFormatException: [%s]: Is NOT a valid time and/or the format is incorrect!",
                                fieldName, timeString));
            }
        }
    }
}
