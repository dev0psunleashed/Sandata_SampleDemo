package com.sandata.lab.common.utils.cache;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

/**
 * Date: 8/24/15
 * Time: 9:06 AM
 */

public class CacheUtil {

    public static boolean CacheExpired(final LocalDateTime cachedDateTime, final long cacheIntervalMinutes) {

        // Cached is disabled
        if (cacheIntervalMinutes == 0) {
            return true;
        }

        boolean bResult = false;

        LocalDateTime now = LocalDateTime.now();

        Duration duration = new Duration(cachedDateTime.toDateTime(DateTimeZone.UTC), now.toDateTime(DateTimeZone.UTC));
        //long seconds = duration.getStandardSeconds();
        long minutes = duration.getStandardMinutes();

        if(minutes >= cacheIntervalMinutes) {
            bResult = true;
        }

        return bResult;
    }
}
