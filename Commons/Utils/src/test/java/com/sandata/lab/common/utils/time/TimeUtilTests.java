package com.sandata.lab.common.utils.time;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class TimeUtilTests {

    @Test
    public void should_convert_seconds_to_bigdecimal_hours() throws Exception {

        Assert.assertTrue(TimeUtil.SecondsToHours(3600).floatValue() == 1.00f);
        Assert.assertTrue(TimeUtil.SecondsToHours(6300).floatValue() == 1.75f);
    }

    @Test
    public void should_convert_string_time_to_float_hour() throws Exception {

        float hours = TimeUtil.TimeToHours("02:75");
        Assert.assertTrue(hours == 3.25);
    }

    @Test
    public void should_convert_hours_to_seconds() throws Exception {

        long seconds = TimeUtil.HoursToSeconds(2f);
        Assert.assertTrue(seconds == 7200);

        seconds = TimeUtil.HoursToSeconds(2.5f);
        Assert.assertTrue(seconds == 9000);

        seconds = TimeUtil.HoursToSeconds(TimeUtil.TimeToHours("3:15"));
        Assert.assertTrue(seconds == 11700);
    }

    @Test
    public void should_check_time_format_is_valid() throws Exception {

        Assert.assertTrue(TimeUtil.IsValidTime("00:00", "JUnit Test"));
    }
    
    @Test
    public void should_throw_exception_when_time_format_hh_is_not_valid() throws Exception {
    
        try {
            TimeUtil.IsValidTime("99:00", "JUnit Test");
            
            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {
    
            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().equals("JUnit Test: NumberFormatException: HH: [99:00]: Is NOT a valid time and/or the format is incorrect!"));
        }
    }

    @Test
    public void should_throw_exception_when_time_format_mm_is_not_valid() throws Exception {

        try {
            TimeUtil.IsValidTime("22:61", "JUnit Test");

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().equals("JUnit Test: NumberFormatException: MM: [22:61]: Is NOT a valid time and/or the format is incorrect!"));
        }
    }
}
