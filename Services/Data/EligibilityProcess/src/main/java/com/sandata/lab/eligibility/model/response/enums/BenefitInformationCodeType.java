package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit information codes allowed.
*/
public enum BenefitInformationCodeType
{
    Unknown,
    ActiveCoverage,
    ActiveFullRiskCapitation,
    ActiveServicesCapitated,
    ActiveServicesCapitatedToPrimaryCarePhysician,
    ActivePendingInvestigation,
    Inactive,
    InactivePendingEligibilityUpdate,
    InactivePendingInvestigation,
    CoInsurance,
    CoPayment,
    Deductible,
    CoverageBasis,
    BenefitDescription,
    Exclusions,
    Limitations,
    OutOfPocket,
    Unlimited,
    NonCovered,
    CostContainment,
    Reserve,
    PrimaryCareProvider,
    PreExistingCondition,
    ManagedCareCoorinator,
    ServiceRestrictedToFollowingProvider,
    NotDeemedAMedicalNecessity,
    BenefitDisclaimer,
    SecondSurgicalOpinionRequired,
    OtherOrAdditionalPayer,
    PriorYearsHistory,
    CardsReportedLostOrStolen,
    ContactFollowingEntityForEligibilityOrBenefitInformation,
    CannotProcess,
    OtherSourceOfData,
    HealthCareFacility,
    SpendDown;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitInformationCodeType forValue(int value)
    {
        return values()[value];
    }
}