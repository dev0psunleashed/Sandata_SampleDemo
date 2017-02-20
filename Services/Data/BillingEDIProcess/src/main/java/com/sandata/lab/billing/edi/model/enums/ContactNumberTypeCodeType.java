package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of PER03 Communication Number Qualifier codes allowed.
 Use the ContactNumberTypeCodes static class to get more information about each code.
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