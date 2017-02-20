package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of III01 Code List qualifier codes allowed.
*/
public enum AdditionalInformationCodeListCodeType
{
    Unknown,
    NationalCouncilOnCompensationInsurance,
    NatureOfInjury,
    MutuallyDefined;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AdditionalInformationCodeListCodeType forValue(int value)
    {
        return values()[value];
    }
}