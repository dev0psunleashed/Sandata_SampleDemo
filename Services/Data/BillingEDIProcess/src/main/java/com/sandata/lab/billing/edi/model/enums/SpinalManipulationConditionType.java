package com.sandata.lab.billing.edi.model.enums;


public enum SpinalManipulationConditionType
{
    Unknown,
    AcuteCondition,
    AcuteManifestationOfAChronicCondition,
    ChronicCondition,
    Nonacute,
    NonLifeThreatening,
    Routine,
    Symptomatic;

    public int getValue()
    {
        return this.ordinal();
    }

    public static SpinalManipulationConditionType forValue(int value)
    {
        return values()[value];
    }
}