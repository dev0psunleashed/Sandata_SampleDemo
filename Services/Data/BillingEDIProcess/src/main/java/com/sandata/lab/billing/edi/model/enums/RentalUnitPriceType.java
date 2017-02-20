package com.sandata.lab.billing.edi.model.enums;


public enum RentalUnitPriceType
{
    Unknown,
    Daily,
    Monthly,
    Weekly;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RentalUnitPriceType forValue(int value)
    {
        return values()[value];
    }
}