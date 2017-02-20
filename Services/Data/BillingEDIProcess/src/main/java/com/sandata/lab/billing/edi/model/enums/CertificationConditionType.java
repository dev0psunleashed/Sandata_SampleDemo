package com.sandata.lab.billing.edi.model.enums;


public enum CertificationConditionType
{
    Unknown,
    AmbulanceCertification,
    ContactLenses,
    DurableMedicalEquipmentCertification,
    FunctionalLimitations,
    Hospice,
    MutuallyDefined,
    SpectacleFrames,
    SpectacleLenses;

    public int getValue()
    {
        return this.ordinal();
    }

    public static CertificationConditionType forValue(int value)
    {
        return values()[value];
    }
}