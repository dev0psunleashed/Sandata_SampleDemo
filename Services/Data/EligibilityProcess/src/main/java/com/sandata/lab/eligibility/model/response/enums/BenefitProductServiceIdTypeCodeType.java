package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit product service type codes allowed.
*/
public enum BenefitProductServiceIdTypeCodeType
{
    Unknown,
    ADA,
    CPT,
    HCPCS,
    ICD9,
    HEIC,
    NDC542,
    ICD10;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitProductServiceIdTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}