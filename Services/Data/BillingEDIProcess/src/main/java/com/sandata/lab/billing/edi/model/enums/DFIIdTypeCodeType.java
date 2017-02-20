package com.sandata.lab.billing.edi.model.enums;


public enum DFIIdTypeCodeType
{
    Unknown,
    ABATransitRoutingNumberIncludingCheckDigits,
    CanadianBranchAndInstitutionNumber;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DFIIdTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}