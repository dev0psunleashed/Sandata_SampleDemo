package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of gender codes allowed.
 Use the GenderCodes static class to get more information about each code.
*/
public enum GenderCodeType
{
    Unknown,
    Female,
    Male;

    public int getValue()
    {
        return this.ordinal();
    }

    public static GenderCodeType forValue(int value)
    {
        return values()[value];
    }
}