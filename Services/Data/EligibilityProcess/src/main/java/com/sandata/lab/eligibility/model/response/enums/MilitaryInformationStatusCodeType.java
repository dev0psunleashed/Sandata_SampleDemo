package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of military information status codes allowed.
*/
public enum MilitaryInformationStatusCodeType
{
    Unknown,
    Partial,
    Current,
    Latest,
    Oldest,
    Prior,
    SecondMostCurrent,
    ThirdMostCurrent;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MilitaryInformationStatusCodeType forValue(int value)
    {
        return values()[value];
    }
}