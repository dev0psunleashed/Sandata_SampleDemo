package com.sandata.lab.billing.edi.model.enums;


public enum ContactCodeType
{
    Unknown,
    InformationContact,
    PayersClaimOffice,
    TechnicalDepartment;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ContactCodeType forValue(int value)
    {
        return values()[value];
    }
}