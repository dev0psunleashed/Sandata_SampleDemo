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

/**
 * Utilities and helper methods to format time.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeFormat {

    public static String SecondsToString(long totalSeconds) {

        long hours = totalSeconds / TimeUtil.MINUTES_IN_AN_HOUR / TimeUtil.SECONDS_IN_A_MINUTE;
        long minutes = (totalSeconds - (TimeUtil.HoursToSeconds(hours))) / TimeUtil.SECONDS_IN_A_MINUTE;
        long seconds = totalSeconds - ((TimeUtil.HoursToSeconds(hours)) + (TimeUtil.MinutesToSeconds(minutes)));

        return hours + " Hours : " + minutes + " Minutes : " + seconds + " Seconds";
    }
}
