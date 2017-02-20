package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit authorization required codes allowed.
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