package com.sandata.lab.common.utils.date;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(JMockit.class)
public class DateUtilTests {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void should_check_if_date_is_not_null() throws Exception {

        Date date = format.parse("2016-06-30 00:00:00");
        Assert.assertTrue(!DateUtil.IsNull(date));
    }

    @Test
    public void should_check_if_date_is_null() throws Exception {

        Date date = format.parse("0001-01-01 00:00:00");
        Assert.assertTrue(DateUtil.IsNull(date));
    }

    @Test
    public void should_calculate_hours_between_two_dates() throws Exception {

        Date startDate = format.parse("2016-07-19 21:59:30");
        Date endDate = format.parse("2016-07-20 00:00:30");

        float hours = DateUtil.Hours(startDate, endDate);

        Assert.assertTrue(hours == 2.02f);
    }

    @Test
    public void should_throw_exception_when_start_date_is_null() throws Exception {

        try {
            Date endDate = format.parse("2016-07-20 00:00:30");

            DateUtil.Hours(null, endDate);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("DateUtil: Hours: startDate == null"));
        }
    }

    @Test
    public void should_throw_exception_when_end_date_is_before_start_date() throws Exception {

        try {
            Date startDate = format.parse("2016-07-19 21:59:30");
            Date endDate = format.parse("2016-07-18 00:00:30"); // Before StartDate

            DateUtil.Hours(startDate, endDate);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("DateUtil: Hours: endDate before startDate"));
        }
    }

    @Test
    public void should_calculate_seconds_between_two_dates() throws Exception {

        Date startDate = format.parse("2016-07-19 21:59:30");
        Date endDate = format.parse("2016-07-20 00:00:30");

        long seconds = DateUtil.Seconds(startDate, endDate);
        float hours = DateUtil.Hours(startDate, endDate);

        // Check that the seconds that are return, can be converted to the hours which will verify that the logic is correct
        float hrsCompare = Math.round((seconds/3600f) * 100f) / 100f;

        Assert.assertTrue(hrsCompare == hours);
    }
}
