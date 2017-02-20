package com.sandata.lab.billing.edi.model.enums;


public enum ClaimPatientSignatureSourceType
{
    Unknown,
    SignatureGeneratedByProvider;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimPatientSignatureSourceType forValue(int value)
    {
        return values()[value];
    }
}