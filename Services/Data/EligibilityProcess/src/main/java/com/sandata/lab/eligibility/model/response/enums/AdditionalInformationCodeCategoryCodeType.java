package com.sandata.lab.eligibility.model.response.enums;


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