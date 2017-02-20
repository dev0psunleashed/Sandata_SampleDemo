package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of Date type codes allowed.
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
    Status;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DateTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}