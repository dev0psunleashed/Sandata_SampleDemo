package com.sandata.lab.billing.edi.model.enums;


public enum ProviderPlanParticipationCodeType
{
    Unknown,
    Assigned,
    AssignmentAcceptedOnClinicalLabServicesOnly,
    NotAssigned;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ProviderPlanParticipationCodeType forValue(int value)
    {
        return values()[value];
    }
}