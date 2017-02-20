package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit information codes allowed.
 Use the BenefitInformationCodes static class to get more information about each code.
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