package com.sandata.lab.billing.edi.model.enums;


public enum RemittanceDeliveryType
{
    Unknown,
    ByMail,
    Email,
    FileTransfer,
    OnLine;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RemittanceDeliveryType forValue(int value)
    {
        return values()[value];
    }
}