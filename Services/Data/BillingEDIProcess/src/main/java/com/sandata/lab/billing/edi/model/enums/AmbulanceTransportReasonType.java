package com.sandata.lab.billing.edi.model.enums;


public enum AmbulanceTransportReasonType
{
    Unknown,
    EmergencyCare,
    FamilyLocality,
    PrefferedPhysician,
    Rehabilitation,
    SpecialistCare;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AmbulanceTransportReasonType forValue(int value)
    {
        return values()[value];
    }
}