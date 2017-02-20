package com.sandata.lab.billing.edi.model.enums;


public enum CreditDebitFlagCodeType
{
    Unknown,
    Credit,
    Debit;

    public int getValue()
    {
        return this.ordinal();
    }

    public static CreditDebitFlagCodeType forValue(int value)
    {
        return values()[value];
    }
}