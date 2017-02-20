package com.sandata.lab.billing.edi.model.enums;


public enum ServiceCopayStatusType
{
    Unknown,
    CopayStatusExempt;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ServiceCopayStatusType forValue(int value)
    {
        return values()[value];
    }
}