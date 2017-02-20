package com.sandata.lab.billing.edi.model.enums;


public enum ContractType
{
    Unknown,
    DiagnosisRelatedGroup,
    PerDiem,
    VariablePerDiem,
    Flat,
    Capitated,
    Percent,
    Other;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ContractType forValue(int value)
    {
        return values()[value];
    }
}