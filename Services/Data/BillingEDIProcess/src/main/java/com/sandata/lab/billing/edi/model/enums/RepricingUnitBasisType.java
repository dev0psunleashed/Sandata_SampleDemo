package com.sandata.lab.billing.edi.model.enums;


public enum RepricingUnitBasisType
{
    Unknown,
    Days,
    Units;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RepricingUnitBasisType forValue(int value)
    {
        return values()[value];
    }
}