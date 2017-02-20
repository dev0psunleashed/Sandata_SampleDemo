package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of health care services delivery time period codes allowed.
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