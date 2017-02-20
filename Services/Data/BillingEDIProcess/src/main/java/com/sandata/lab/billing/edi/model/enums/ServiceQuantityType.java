package com.sandata.lab.billing.edi.model.enums;


public enum ServiceQuantityType
{
    Unknown,
    Days,
    Minutes,
    Units;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ServiceQuantityType forValue(int value)
    {
        return values()[value];
    }
}