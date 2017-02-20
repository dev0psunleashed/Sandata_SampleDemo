package com.sandata.lab.billing.edi.model.enums;


public enum MeasurementType
{
    Unknown,
    Height,
    Hemoglobin,
    Hematocrit,
    EpoetinStartingDoseage,
    Creatinine;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MeasurementType forValue(int value)
    {
        return values()[value];
    }
}