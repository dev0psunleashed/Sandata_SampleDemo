package com.sandata.lab.billing.edi.model.enums;


public enum RepricingPolicyComplianceType
{
    Unknown,
    EmergencyAdmittoNonNetworkHospital,
    NotFollowedCallNotMade,
    NotFollowedOther,
    NotMedicallyNecessary,
    ProcedureFollowed;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RepricingPolicyComplianceType forValue(int value)
    {
        return values()[value];
    }
}