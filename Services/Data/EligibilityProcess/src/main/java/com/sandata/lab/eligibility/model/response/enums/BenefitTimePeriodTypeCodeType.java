package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit time period type codes allowed.
*/
public enum BenefitTimePeriodTypeCodeType
{
    Unknown,
    Hour,
    Day,
    TwentyfourHours,
    Years,
    ServiceYear,
    CalendarYear,
    YearToDate,
    Contact,
    Episode,
    Visit,
    Outlier,
    Remaining,
    Exceeded,
    NotExceeded,
    Lifetime,
    LifetimeRemaining,
    Month,
    Week,
    Admission;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitTimePeriodTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}