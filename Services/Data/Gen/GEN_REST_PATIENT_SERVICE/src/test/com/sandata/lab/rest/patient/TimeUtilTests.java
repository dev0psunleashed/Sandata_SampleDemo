package com.sandata.lab.rest.patient;

import com.sandata.lab.common.utils.time.TimeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Date: 10/15/15
 * Time: 3:04 PM
 */

public class TimeUtilTests extends BaseTestSupport {

    @Test
    public void should_convert_date_string_to_date_and_back_again() throws Exception {

        String dateString = "2015-09-23 05:03:16";

        // String to Date
        Date date = TimeUtil.ToDate(dateString);

        Assert.assertNotNull(date);

        // Date to String
        String dateStringResult = TimeUtil.ToString(date);

        Assert.assertNotNull(dateStringResult);

        Assert.assertTrue(dateStringResult.length() > 0);
        Assert.assertTrue(dateStringResult.equals(dateString));
    }

    @Test
    public void should_convert_eastern_date_to_utc_date() throws Exception {

        String easternDateString = "2015-09-23 05:03:16";
        String expectedUTCDateString = "2015-09-23 09:03:16";

        Date easternDate = TimeUtil.ToDate(easternDateString);

        String easterDateAfterConversion = TimeUtil.DateForTZ(easternDate, "US/Eastern");

        Assert.assertTrue(easterDateAfterConversion.equals(easternDateString));

        // Eastern to UTC Date
        String utcDateString = TimeUtil.DateForTZ(easternDate, "UTC");

        Assert.assertNotNull(utcDateString);

        Assert.assertTrue(utcDateString.length() > 0);

        Assert.assertTrue(utcDateString.equals(expectedUTCDateString));
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
