package com.sandata.lab.billing.edi.model.enums;


public enum DurableMedicalEquipmentCertificationTimeFrameType
{
    Unknown,
    Months;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DurableMedicalEquipmentCertificationTimeFrameType forValue(int value)
    {
        return values()[value];
    }
}