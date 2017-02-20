package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of Additional Id Type  codes allowed.
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
    PatientAccoungNumber,
    HealthInsuranceClaimNumber,
    DrugFormularyNumber,
    PriorAuthorizationNumber,
    IdentificatinCardSerialNumber,
    IdentityCardNumber,
    IssueNumber,
    InsurancePolicyNumber,
    MedicalAssistanceCategory,
    EligibilityCategory,
    PlanNetworkIdentificationNumber,
    MedicaidRecipientIdentificationNumber,
    PriorIdentifierNumber,
    SocialSecurityNumber,
    AgencyClaimNumber;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AdditionalIdTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}