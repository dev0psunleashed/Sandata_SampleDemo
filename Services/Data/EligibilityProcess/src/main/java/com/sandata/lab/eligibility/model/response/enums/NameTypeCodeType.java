package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of NM101 Entity Identifier codes allowed.
*/
public enum NameTypeCodeType
{
    Unknown,
    Dependent,
    ContractedServiceProvider,
    PreferredProviderOrganization,
    Provider,
    ThirdPartyAdministrator,
    Employer,
    OtherPhysician,
    Facility,
    GatewayProvider,
    Group,
    IndependentPhysiciansAssociation,
    InsuredOrSubscriber,
    LegalRepresentative,
    OriginCarrier,
    PrimaryCareProvider,
    PriorInsuranceCarrier,
    PlanSponsor,
    Payer,
    PrimaryPayer,
    SecondaryPayer,
    TertiaryPayer,
    PartyPerformingVerification,
    Vendor,
    OrganizationCompletingConfigurationChange,
    UtilizationManagementOrganization,
    ManagedCareOrganization;

    public int getValue()
    {
        return this.ordinal();
    }

    public static NameTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}