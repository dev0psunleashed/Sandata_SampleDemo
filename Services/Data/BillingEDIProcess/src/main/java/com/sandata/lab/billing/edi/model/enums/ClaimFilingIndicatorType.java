package com.sandata.lab.billing.edi.model.enums;


public enum ClaimFilingIndicatorType
{
    Unknown,
    AutomobileMedical,
    BlueCrossBlueShield,
    Champus,
    CommercialInsurance,
    DentalMaintenanceOrganization,
    Disability,
    ExclusiveProviderOrganization,
    FederalEmployeesProgram,
    HealthMaintenanceOrganization,
    HealthMaintenanceOrganizationMedicareRisk,
    IndemnityInsurance,
    LiabilityMedical,
    Medicaid,
    MedicarePartA,
    MedicarePartB,
    MutuallyDefined,
    OtherFederalProgram,
    OtherNonFederalPrograms,
    PointOfService,
    PreferredProviderOrganization,
    TitleV,
    VeteransAffairsPlan,
    WorkersCompensationHealthClaim;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimFilingIndicatorType forValue(int value)
    {
        return values()[value];
    }
}