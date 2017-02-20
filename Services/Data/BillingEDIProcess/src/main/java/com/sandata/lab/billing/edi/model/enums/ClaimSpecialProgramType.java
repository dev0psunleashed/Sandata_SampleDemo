package com.sandata.lab.billing.edi.model.enums;


public enum ClaimSpecialProgramType
{
    Unknown,
    Disability,
    PhysicallyHandicappedChildrensProgram,
    SecondOpinionOrSurgery,
    SpecialFederalFunding;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimSpecialProgramType forValue(int value)
    {
        return values()[value];
    }
}