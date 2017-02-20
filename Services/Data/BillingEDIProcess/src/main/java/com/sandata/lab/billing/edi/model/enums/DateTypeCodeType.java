package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of Date type codes allowed.
 Use the DateTypeCodes static class to get more information about each code.
*/
public enum DateTypeCodeType
{
    Unknown,
    Discharge,
    Issue,
    EffectiveDateOfChange,
    PeriodStart,
    PeriodEnd,
    Completion,
    CoordinationOfBenefits,
    Plan,
    Benefit,
    PrimaryCareProvider,
    LatestVisitOrConsultation,
    Eligibility,
    Added,
    COBRABegin,
    COBRAEnd,
    PremiumPaidToDateBegin,
    PremiumPaidToDateEnd,
    PlanBegin,
    PlanEnd,
    BenefitBegin,
    BenefitEnd,
    EligibilityBegin,
    EligibilityEnd,
    Enrollment,
    Admission,
    DateOfDeath,
    Certification,
    Service,
    PolicyEffective,
    PolicyExpiration,
    DateOfLastUpdate,
    Status,
    Accident,
    AcuteManifestationOfAChronicCondition,
    BeginTherapy,
    CertificationRevision,
    ClaimStatementPeriodEnd,
    ClaimStatementPeriodStart,
    DateClaimPaid,
    Disability,
    Expiration,
    FirstVisitorConsultation,
    InitialDisabilityPeriodDateLastWorked,
    InitialDisabilityPeriodEnd,
    InitialDisabilityPeriodReturnToWork,
    InitialDisabilityPeriodStart,
    InitialTreatment,
    LastMenstrualPeriod,
    LastCertification,
    LastXRay,
    MostRecentHemoglobinOrHematocritOrBoth,
    MostRecentSerumCreatine,
    OnsetOfCurrentSymptomsOrIllness,
    Prescription,
    ProductionDate,
    Received,
    ReportEnd,
    ReportStart,
    ServicePeriodEnd,
    ServicePeriodStart,
    Shipped,
    Statement;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DateTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}