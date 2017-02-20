package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of PER03 Communication Number Qualifier codes allowed.
*/
public enum ContactNumberTypeCodeType
{
    Unknown,
    ElectronicDataInterchangeAccessNumber,
    ElectronicMail,
    TelephoneExtension,
    Facsimile,
    Telephone,
    UniformResourceLocator,
    WorkPhoneNumber;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ContactNumberTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}