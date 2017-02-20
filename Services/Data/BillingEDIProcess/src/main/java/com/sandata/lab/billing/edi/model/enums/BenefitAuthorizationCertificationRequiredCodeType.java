package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit authorization required codes allowed.
 Use the BenefitAuthorizationCertificationRequiredCodes static class to get more information about each code.
*/
public enum BenefitAuthorizationCertificationRequiredCodeType
{
    Unknown,
    No,
    Yes;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitAuthorizationCertificationRequiredCodeType forValue(int value)
    {
        return values()[value];
    }
}