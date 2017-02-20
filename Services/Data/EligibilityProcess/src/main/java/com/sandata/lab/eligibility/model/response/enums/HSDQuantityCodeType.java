package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of health care service delivery quantity codes allowed.
*/
public enum HSDQuantityCodeType
{
    Unknown,
    Days,
    Units,
    Hours,
    Month,
    Visits;

    public int getValue()
    {
        return this.ordinal();
    }

    public static HSDQuantityCodeType forValue(int value)
    {
        return values()[value];
    }
}