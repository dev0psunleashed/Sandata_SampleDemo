package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of military information status codes allowed.
 Use the MilitaryInformationStatusCodes static class to get more information about each code.
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