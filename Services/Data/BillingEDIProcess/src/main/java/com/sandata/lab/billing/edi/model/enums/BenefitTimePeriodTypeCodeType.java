package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit time period type codes allowed.
 Use the BenefitTimePeriodTypeCodes static class to get more information about each code.
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