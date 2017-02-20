package com.sandata.lab.imports.payroll.utils.date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class DateTimeConverter {

    private DateTimeFormatter formatter;

    public DateTimeConverter()
    {
        DateTimeParser[] parsers = {
            DateTimeFormat.forPattern( "yyyy-MM-dd HH:mm:ss.SSS Z" ).getParser(),
            DateTimeFormat.forPattern( "yyyy-MM-dd HH:mm:ss Z" ).getParser() };

        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    public DateTime convertLocalTimeToUTC(String dateTime, int offset)
    {
        if(StringUtils.isNotEmpty(dateTime)) {

           String offsetString = createOffsetStringZoneId(offset);

            dateTime += " " + offsetString;

            DateTime date = formatter.parseDateTime(dateTime);


            return date.toDateTime();
        }

        return null;
    }

    public DateTime convertStringToTime(String dateTime)
    {
        if(StringUtils.isNotEmpty(dateTime)) {


            DateTime date = formatter.parseDateTime(dateTime);


            return date.toDateTime();
        }

        return null;
    }


    private String createOffsetStringZoneId(int offset)
    {
        String id;

        if(offset < 0)
        {
            id = "-";
        }
        else
        {
            id = "+";
        }

        String offsetString = Integer.toString(Math.abs(offset));

        if(offsetString.length() > 1)
        {
            id += offsetString;
        }
        else
        {
            id += "0" + offsetString;
        }

        id += ":00";

        return id;
    }


}
