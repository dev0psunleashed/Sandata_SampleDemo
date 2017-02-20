package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of III01 Code List qualifier codes allowed.
 Use the AdditionalInformationCodeListCodes static class to get more information about each code.
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