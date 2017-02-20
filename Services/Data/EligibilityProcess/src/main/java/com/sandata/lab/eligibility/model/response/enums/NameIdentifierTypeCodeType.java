package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of NM108 Identification Qualifier codes allowed.
*/
public enum NameIdentifierTypeCodeType
{
    Unknown,
    EmployerIdentificationNumber,
    SocialSecurityNumber,
    ElectronicTransmitterIdentificationNumber,
    FacilityIdentification,
    FederalTaxpayerIdentificationNumber,
    StandardUniqueHealthIdentifier,
    MemberIdentificationNumber,
    NationalAssociationOfInsuranceCommisionersIdentification,
    PayorIdentification,
    PharmacyProcessorNumber,
    ServiceProviderNumber,
    CenterForMedicareMedicaidServicesPlanID,
    CenterForMedicareMedicaidServicesIdentifier;

    public int getValue()
    {
        return this.ordinal();
    }

    public static NameIdentifierTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}