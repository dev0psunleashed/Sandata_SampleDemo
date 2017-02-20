package com.sandata.lab.billing.edi.model.enums;


public enum MeasurementReferenceType
{
    Unknown,
    Original,
    TestResults;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MeasurementReferenceType forValue(int value)
    {
        return values()[value];
    }
}