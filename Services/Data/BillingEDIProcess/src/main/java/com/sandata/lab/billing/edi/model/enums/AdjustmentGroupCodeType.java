package com.sandata.lab.billing.edi.model.enums;


public enum AdjustmentGroupCodeType
{
    Unknown,
    ContractualObligations,
    CorrectionAndReversals,
    OtherAdjustments,
    PatientResponsibility,
    PayorInitiatedReductions;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AdjustmentGroupCodeType forValue(int value)
    {
        return values()[value];
    }
}