package com.sandata.lab.billing.edi.model.enums;


public enum ClaimReleaseOfInformationCodeType
{
    Unknown,
    InformedConsentToReleaseWithNoSignature,
    ReleaseSigned;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimReleaseOfInformationCodeType forValue(int value)
    {
        return values()[value];
    }
}