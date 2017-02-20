package com.sandata.lab.billing.edi.model.enums;


public enum AttachmentReportType
{
    Unknown,
    AdmissionSummary,
    AllergiesSensitivitiesDocument,
    AmbulanceCertification,
    AutopsyReport,
    Baseline,
    BenchmarkTestingResults,
    BlanketTestResults,
    Certification,
    CertifiedTestReport,
    ChemicalAnalysis,
    ChiropracticJustification,
    ConsentForms,
    ContinuedTreatment,
    DeathNotification,
    DentalModels,
    DiagnosticReport,
    DischargeMonitoringReport,
    DischargeSummary,
    DrugProfileDocument,
    DrugsAdministered,
    DurableMedicalEquipmentPrescription,
    ExplanationOfBenefits,
    FunctionalGoals,
    HealthCertificate,
    HealthClinicRecords,
    ImmunizationRecord,
    InitialAssessment,
    JustificationForAdmission,
    LaboratoryResults,
    MedicalRecordAttachment,
    Models,
    NursingNotes,
    ObjectivePhysicalExaminationDocument,
    OperativeNote,
    OrdersAndTreatmentsDocument,
    OxygenContentAveragingReport,
    OxygenTherapyCertification,
    ParamedicalResults,
    ParenternalOrEnternalCertification,
    PathologyReport,
    PatientMedicalHistoryDocument,
    Photographs,
    PhysicalTherapyCertification,
    PhysicalTherapyNotes,
    PhysicianOrder,
    PhysiciansReport,
    PlanOfTreatment,
    Prescription,
    ProgressReport,
    ProstheticsOrOrthoticCertification,
    RadiologyFilms,
    RadiologyReports,
    RecoveryPlan,
    ReferralForm,
    RenewableOxygenContentAveragingReport,
    ReportJustifyingTreatmentBeyondUtilizationGuidelines,
    ReportOfTestsAndAnalysisReport,
    StateSchoolImmunizationRecords,
    SupportDataForClaim,
    SymptomsDocument,
    TreatmentDiagnosis;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AttachmentReportType forValue(int value)
    {
        return values()[value];
    }
}