package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of NM108 Identification Qualifier codes allowed.
 Use the NameIdentifierTypeCodes static class to get more information about each code.
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
    PlanID,
    NationalProviderIdentifier,
    BlueCrossBlueShieldAssociationPlanCode,
    BlueCrossProviderNumber,
    BlueShieldProviderNumber,
    CMSNationalPlanId,
    EmployersIdentificationNumber,
    FederalTaxpayersIdentificationNumber,
    HealthInsuranceClaimNumber,
    InsuredChangedUniqueIdentificationNumber,
    MedicaidProviderNumber,
    MedicaidRecipientIdentificationNumber,
    NationalAssociationofInsuranceCommissionersIdentification,
    ProviderCommercialNumber,
    StateLicenseNumber,
    UniquePhysicianIdentificationNumber;

    public int getValue()
    {
        return this.ordinal();
    }

    public static NameIdentifierTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}