package com.sandata.lab.billing.edi.model.enums;


public enum RepricingExceptionType
{
    Unknown,
    EmergencyCare,
    NonNetworkProfessionalProviderinNetworkHospital,
    Other,
    OutOfServiceArea,
    ServicesOrSpecialistNotInNetwork,
    StateMandates;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RepricingExceptionType forValue(int value)
    {
        return values()[value];
    }
}