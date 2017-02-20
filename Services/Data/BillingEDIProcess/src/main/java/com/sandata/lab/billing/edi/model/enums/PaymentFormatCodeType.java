package com.sandata.lab.billing.edi.model.enums;


public enum PaymentFormatCodeType
{
    Unknown,
    CashConcentrationDisbursementPlusAddenda,
    CorporateTradeExchange;

    public int getValue()
    {
        return this.ordinal();
    }

    public static PaymentFormatCodeType forValue(int value)
    {
        return values()[value];
    }
}