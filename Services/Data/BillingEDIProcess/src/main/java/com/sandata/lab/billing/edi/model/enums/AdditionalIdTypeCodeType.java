package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of Additional Id Type  codes allowed.
 Use the AdditionalIdTypeCodes static class to get more information about each code.
*/
public enum AdditionalIdTypeCodeType
{
    Unknown,
    PlanNumber,
    GroupOrPolicyNumber,
    MemberIdentificationNumber,
    CaseNumber,
    FamilyUnitNumber,
    GroupNumber,
    ReferralNumber,
    AlternativeListId,
    ClassOfContractCode,
    CoverageListId,
    ContractNumber,
    MedicalRecordIdentificationNumber,
    PatientAccountNumber,
    HealthInsuranceClaimNumber,
    DrugFormularyNumber,
    PriorAuthorizationNumber,
    IdentificationCardSerialNumber,
    IdentityCardNumber,
    IssueNumber,
    InsurancePolicyNumber,
    MedicalAssistanceCategory,
    EligibilityCategory,
    PlanNetworkIdentificationNumber,
    MedicaidRecipientIdentificationNumber,
    PriorIdentifierNumber,
    SocialSecurityNumber,
    AgencyClaimNumber,
    AdjustedRepricedClaimReferenceNumber,
    AdjustedRepricedLineItemReferenceNumber,
    AmbulatoryPatientClassification,
    AmbulatoryPatientGroupNumber,
    AttachmentCode,
    AuthorizationNumber,
    BatchNumber,
    BillingType,
    BlueCrossProviderNumber,
    BlueShieldProviderNumber,
    ChampusIdentificationNumber,
    ClaimNumber,
    ClaimOfficeNumber,
    ClinicalLaboratoryImprovementAmendmentNumber,
    EmployeeIdentificationNumber,
    EmployersIdentificationNumber, // possibly some overlap with federal tax id
    FacilityCertificationNumber,
    FacilityIdNumber,
    FederalTaxpayersIdentificationNumber, // possibly some overlap with employers id
    HealthIndustryNumber,
    LineItemControlNumber,
    LinkSequenceNumber,
    LocationNumber,
    MammographyCertificationNumber,
    MedicaidProviderNumber,
    MedicareProviderNumber,
    MedicareVersionCode,
    NationalAssociationofInsuranceCommissionersCode,
    NationalCouncilforPrescriptionDrugProgramsPharmacyNumber,
    NationalProviderIdentifier,
    OriginalReferenceNumber,
    PayeeIdentification,
    PayerIdentificationNumber,
    PayorsClaimNumber,
    PeerReviewOrganizationApprovalNumber,
    PharmacyPrescriptionNumber,
    PolicyFormIdentifyingNumber,
    PredeterminationOfBenefitsIdentificationNumber,
    ProjectCode,
    ProviderCommercialNumber,
    ProviderUPINNumber,
    QualifiedProductsList,
    RateCode,
    ReceiverIdentificationNumber,
    RepricedClaimReferenceNumber,
    RepricedLineItemReferenceNumber,
    SignalCode,
    SpecialPaymentReferenceNumber,
    StateLicenseNumber,
    SubmitterIdentificationNumber,
    VersionCodeLocal;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AdditionalIdTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}