package com.sandata.lab.billing.edi.model.enums;


public enum ClaimRelatedCausesType
{
    Unknown,
    AutoAccident,
    Employment,
    OtherAccident;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimRelatedCausesType forValue(int value)
    {
        return values()[value];
    }
}