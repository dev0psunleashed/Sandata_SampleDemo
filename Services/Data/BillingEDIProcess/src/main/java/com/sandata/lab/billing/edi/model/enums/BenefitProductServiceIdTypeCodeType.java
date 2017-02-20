package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit product service type codes allowed.
 Use the BenefitProductServiceIdTypeCodes static class to get more information about each code.
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