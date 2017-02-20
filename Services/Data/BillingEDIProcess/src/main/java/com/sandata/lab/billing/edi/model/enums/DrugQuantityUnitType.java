package com.sandata.lab.billing.edi.model.enums;


public enum DrugQuantityUnitType
{
    Unknown,
    Gram,
    InternationalUnit,
    Milligram,
    Milliliter,
    Unit;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DrugQuantityUnitType forValue(int value)
    {
        return values()[value];
    }
}