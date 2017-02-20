package com.sandata.lab.billing.edi.model.enums;


public enum AdditionalInformationCodeCategoryCodeType
{
    Unknown,
    NatureOfInjury;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AdditionalInformationCodeCategoryCodeType forValue(int value)
    {
        return values()[value];
    }
}