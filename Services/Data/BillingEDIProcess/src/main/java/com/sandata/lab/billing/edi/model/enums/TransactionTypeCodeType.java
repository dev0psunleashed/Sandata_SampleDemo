package com.sandata.lab.billing.edi.model.enums;


public enum TransactionTypeCodeType
{
    Unknown,
    Chargeable,
    Reporting,
    Response,
    SubrogationDemand;

    public int getValue()
    {
        return this.ordinal();
    }

    public static TransactionTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}