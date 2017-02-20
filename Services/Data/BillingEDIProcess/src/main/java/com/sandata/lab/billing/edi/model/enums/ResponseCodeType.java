package com.sandata.lab.billing.edi.model.enums;

public enum ResponseCodeType
{
    Unknown,
    No,
    NotApplicable,
    Yes;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ResponseCodeType forValue(int value)
    {
        return values()[value];
    }
}