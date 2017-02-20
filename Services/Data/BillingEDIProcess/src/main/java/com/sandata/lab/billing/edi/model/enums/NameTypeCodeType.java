package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of NM101 Entity Identifier codes allowed.
 Use the NameTypeCodes static class to get more information about each code.
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
    ManagedCareOrganization,
    AttendingPhysician,
    BillingProvider,
    CorrectedInsured,
    DropOffLocation,
    LaboratoryOrFacility,
    OperatingPhysician,
    OrderingPhysician,
    OtherInsured,
    OtherOperatingPhysician,
    Patient,
    Payee,
    PayToProvider,
    PickupAddress,
    PurchasedServiceProvider,
    Receiver,
    ReferringPhysician,
    ReferringProvider,
    RenderingProvider,
    ServiceProvider,
    Submitter,
    SupervisingPhysician,
    TransferTo;

    public int getValue()
    {
        return this.ordinal();
    }

    public static NameTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}