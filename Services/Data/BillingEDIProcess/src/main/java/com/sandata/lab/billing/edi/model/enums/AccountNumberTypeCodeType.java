package com.sandata.lab.billing.edi.model.enums;


public enum AccountNumberTypeCodeType
{
    Unknown,
    DemandDeposit,
    Savings;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AccountNumberTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}