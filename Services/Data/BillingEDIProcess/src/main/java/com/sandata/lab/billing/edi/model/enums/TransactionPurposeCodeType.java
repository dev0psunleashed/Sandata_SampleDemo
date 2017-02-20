package com.sandata.lab.billing.edi.model.enums;


public enum TransactionPurposeCodeType
{
    Unknown,
    Original,
    Reissue,
    Request,
    Response;

    public int getValue()
    {
        return this.ordinal();
    }

    public static TransactionPurposeCodeType forValue(int value)
    {
        return values()[value];
    }
}