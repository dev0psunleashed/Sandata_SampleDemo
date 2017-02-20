package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of health care service delivery quantity codes allowed.
 Use the HSDQuantityCodes static class to get more information about each code.
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