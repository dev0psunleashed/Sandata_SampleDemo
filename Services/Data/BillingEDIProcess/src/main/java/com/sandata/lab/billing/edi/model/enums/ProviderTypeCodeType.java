package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of provider type codes allowed.
 Use the ProviderTypeCodes static class to get more information about each code.
*/
public enum ProviderTypeCodeType
{
    Unknown,
    Admitting,
    Attending,
    Billing,
    Consulting,
    Covering,
    Hospital,
    HomeHealthCare,
    Laboratory,
    OtherPhysician,
    Pharmacist,
    Pharmacy,
    PrimaryCarePhysician,
    Performing,
    RuralHealthClinic,
    Referring,
    Submitting,
    SkilledNursingFacility,
    Supervising;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ProviderTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}