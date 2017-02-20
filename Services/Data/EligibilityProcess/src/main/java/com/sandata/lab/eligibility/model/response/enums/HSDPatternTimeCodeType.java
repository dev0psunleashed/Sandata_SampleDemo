package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of health care services delivery ship/delivery pattern time codes allowed.
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