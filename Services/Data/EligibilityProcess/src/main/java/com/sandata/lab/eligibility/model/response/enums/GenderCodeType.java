package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of gender codes allowed.
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