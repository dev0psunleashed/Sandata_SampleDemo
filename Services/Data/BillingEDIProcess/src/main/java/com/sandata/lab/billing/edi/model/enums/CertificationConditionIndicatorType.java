package com.sandata.lab.billing.edi.model.enums;


public enum CertificationConditionIndicatorType
{
    Unknown,
    AmbulanceServiceWasMedicallyNecessary,
    AvailableNotUsed,
    CertificationSignedByPhysicianIsOnFileAtProvidersOffice,
    GeneralStandardOf20DegreeOrHalfDiopterSphereOrCylinderChangeMet,
    IndependentAtHome,
    NewServicesRequested,
    NotUsed,
    Open,
    PatientHadToBePhysicallyRestrained,
    PatientHadVisibleHemorrhaging,
    PatientIsConfinedToABedOrAChair,
    PatientWasAdmittedToAHospital,
    PatientWasMovedByStretcher,
    PatientWasTransportedInAnEmergencySituation,
    PatientWasUnconsciousOrInShock,
    ReplacementDueToBreakageOrDamage,
    ReplacementDueToLossOrTheft,
    ReplacementDueToMedicalReason,
    ReplacementDueToPatientPreference,
    ReplacementItem,
    UnderTreatment;

    public int getValue()
    {
        return this.ordinal();
    }

    public static CertificationConditionIndicatorType forValue(int value)
    {
        return values()[value];
    }
}