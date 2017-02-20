package com.sandata.lab.billing.edi.model.enums;


public enum NoteCodeType
{
    Unknown,
    AdditionalInformation,
    Allergies,
    CertificationNarrative,
    DiagnosisDescription,
    DurableMedicalEquipmentAndSupplies,
    FunctionalLimitationsReasonHomeboundOrBoth,
    GoalsRehabilitationPotentialOrDischargePlans,
    Medications,
    NutritionalRequirements,
    OrdersForDisciplinesAndTreatments,
    ReasonsPatientLeavesHome,
    SafetyMeasures,
    SupplementaryPlanofTreatment,
    ThirdPartyOrganizatinNotes,
    ThirdPartyOrganizationNotes,
    TimesAndReasonsPatientNotAtHome,
    UnusualHomeSocialEnvironmentOrBoth,
    UpdatedInformation;

    public int getValue()
    {
        return this.ordinal();
    }

    public static NoteCodeType forValue(int value)
    {
        return values()[value];
    }
}