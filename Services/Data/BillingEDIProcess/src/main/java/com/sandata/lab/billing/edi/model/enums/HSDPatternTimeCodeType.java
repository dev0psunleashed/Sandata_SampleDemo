package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of health care services delivery ship/delivery pattern time codes allowed.
 Use the HSDPatternTimeCodes static class to get more information about each code.
*/
public enum HSDPatternTimeCodeType
{
    Unknown,
    FirstShift,
    SecondShift,
    ThirdShift,
    AM,
    PM,
    AsDirected,
    AnyShift,
    None;

    public int getValue()
    {
        return this.ordinal();
    }

    public static HSDPatternTimeCodeType forValue(int value)
    {
        return values()[value];
    }
}