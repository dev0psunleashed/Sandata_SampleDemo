package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit in plan network codes allowed.
 Use the BenefitInPlanNetworkCodes static class to get more information about each code.
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