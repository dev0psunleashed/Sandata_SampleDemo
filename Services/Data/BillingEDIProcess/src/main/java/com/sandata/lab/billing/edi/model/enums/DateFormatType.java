package com.sandata.lab.billing.edi.model.enums;


public enum DateFormatType
{
    Unknown,
    Date,
    DateTime,
    Range;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DateFormatType forValue(int value)
    {
        return values()[value];
    }
}