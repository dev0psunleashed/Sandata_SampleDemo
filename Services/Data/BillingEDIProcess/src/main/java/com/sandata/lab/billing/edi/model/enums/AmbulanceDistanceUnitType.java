package com.sandata.lab.billing.edi.model.enums;


public enum AmbulanceDistanceUnitType
{
    Unknown,
    Miles;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AmbulanceDistanceUnitType forValue(int value)
    {
        return values()[value];
    }
}