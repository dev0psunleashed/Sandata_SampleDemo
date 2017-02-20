package com.sandata.lab.billing.edi.model.enums;


public enum DurableMedicalEquipmentCertificationType
{
    Unknown,
    Initial,
    Renewal,
    Revised;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DurableMedicalEquipmentCertificationType forValue(int value)
    {
        return values()[value];
    }
}