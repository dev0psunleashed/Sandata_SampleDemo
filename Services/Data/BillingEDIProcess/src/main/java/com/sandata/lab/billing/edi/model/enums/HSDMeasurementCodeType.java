package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of health care services delivery measurement codes allowed.
 Use the HSDMeasurementCodes static class to get more information about each code.
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