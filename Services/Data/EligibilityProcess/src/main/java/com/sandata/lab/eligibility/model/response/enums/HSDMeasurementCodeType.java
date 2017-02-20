package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of health care services delivery measurement codes allowed.
*/
public enum HSDMeasurementCodeType
{
    Unknown,
    Days,
    Months,
    Visits,
    Week,
    Years;

    public int getValue()
    {
        return this.ordinal();
    }

    public static HSDMeasurementCodeType forValue(int value)
    {
        return values()[value];
    }
}