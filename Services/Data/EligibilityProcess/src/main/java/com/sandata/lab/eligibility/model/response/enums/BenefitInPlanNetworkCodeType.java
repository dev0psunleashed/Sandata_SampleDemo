package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit in plan network codes allowed.
*/
public enum BenefitInPlanNetworkCodeType
{
    Unknown,
    NotInPlanNetwork,
    InPlanNetwork,
    NotApplicable;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitInPlanNetworkCodeType forValue(int value)
    {
        return values()[value];
    }
}