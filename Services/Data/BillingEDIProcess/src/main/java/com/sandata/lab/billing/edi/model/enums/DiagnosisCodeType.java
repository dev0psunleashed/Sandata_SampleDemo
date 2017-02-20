package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of gender codes allowed.
 Use the GenderCodes static class to get more information about each code.
*/
public enum DiagnosisCodeType
{
    Unknown,
    PrincipalDiagnosisICD10,
    PrincipalDiagnosisICD9,
    DiagnosisICD10,
    DiagnosisICD9,
    AdmittingDiagnosisICD10,
    AdmittingDiagnosisICD9,
    AdvancedBillingConceptsCodes,
    Condition,
    DiagnosisRelatedGroup,
    ExternalCauseOfInjuryICD10,
    ExternalCauseOfInjuryICD9,
    HealthCareFinancingAdministrationCommonProcedurealCodingSystemPrincipalProcedure,
    HealthCareFinancingAdministrationCommonProcedurealCodingSystemProcedure,
    Occurrence,
    OccurrenceSpan,
    OtherProcedureICD10,
    OtherProcedureICD9,
    PrincipalProcedureICD10,
    PrincipalProcedureICD9,
    ReasonForVisitICD10,
    ReasonForVisitICD9,
    Treatment,
    Value;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DiagnosisCodeType forValue(int value)
    {
        return values()[value];
    }
}