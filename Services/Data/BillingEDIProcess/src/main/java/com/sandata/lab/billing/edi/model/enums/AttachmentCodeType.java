package com.sandata.lab.billing.edi.model.enums;


public enum AttachmentCodeType
{
    Unknown,
    AttachmentControlNumber;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AttachmentCodeType forValue(int value)
    {
        return values()[value];
    }
}