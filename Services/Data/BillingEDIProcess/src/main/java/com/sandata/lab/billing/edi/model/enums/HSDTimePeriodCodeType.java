package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of health care services delivery time period codes allowed.
 Use the HSDTimePeriodCodes static class to get more information about each code.
*/
public enum HSDTimePeriodCodeType
{
        Unknown,
        Hour,
        Day,
        Years,
        ServiceYear,
        CalendarYear,
        YearToDate,
        Contract,
        Episode,
        Visit,
        Outlier,
        Remaining,
        Exceeded,
        NotExceeded,
        Lifetime,
        LifetimeRemaining,
        Month,
        Week;

        public int getValue()
        {
            return this.ordinal();
        }

        public static HSDTimePeriodCodeType forValue(int value)
        {
            return values()[value];
        }
}