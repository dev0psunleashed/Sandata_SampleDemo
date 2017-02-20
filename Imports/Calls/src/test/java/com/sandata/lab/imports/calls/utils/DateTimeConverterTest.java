package com.sandata.lab.imports.calls.utils;

import com.sandata.lab.imports.calls.BaseTestSupport;
import com.sandata.lab.imports.calls.utils.date.DateTimeConverter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.io.IOException;

public class DateTimeConverterTest extends BaseTestSupport {

    private DateTimeConverter dateTimeConverter;

    @Override
    protected void onSetup() throws IOException {

        dateTimeConverter = new DateTimeConverter();
    }

    @Test
    public void eastern_local_time_is_converted_to_utc()
    {
        String time = "2015-10-05 11:40:43.0";
        int offset = -4;

        DateTime expectedDateTime = DateTime.parse("2015-10-05T11:40:43.000-04:00").toDateTime(DateTimeZone.UTC);


        DateTime utcTime = dateTimeConverter.convertLocalTimeToUTC(time, offset).toDateTime(DateTimeZone.UTC);


        assertEquals(expectedDateTime.toString(),utcTime.toString());
    }

    @Test
    public void time_parameter_is_null_and_null_is_returned()
    {
       DateTime dateTime = dateTimeConverter.convertLocalTimeToUTC(null, -5);
        assertNull(dateTime);
    }
}
