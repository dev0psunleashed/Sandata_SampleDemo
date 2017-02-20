package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of insurance type codes allowed.
*/
public enum BenefitInsuranceTypeCodeType
{
    Unknown,
    MedicareSecondaryWorkingAgedBeneficiaryOrSpouseWithEmployerGroupHealthPlan,
    MedicareSecondaryEndStageRenalDiseaseBeneficicaryInTheMandatedCoordinationPeriodWithAnEmployersGroupHealthPlan,
    MedicareSecondaryNofaultInsuranceIncludingAutoIsPrimary,
    MedicareSecondaryWorkersCompensation,
    MedicareSecondaryPublicHealthServiceOrOtherFederalAgency,
    MedicareSecondaryBlackLung,
    MedicareSecondaryVeteransAdministration,
    MedicareSecondaryDisabledBenficiaryUnderAge65WithLargeGroupHealthPlan,
    MedicareSecondaryOtherLiabilityInsuranceIsPrimary,
    AutoInsurancePolicy,
    Commercial,
    ConsolidatedOmnibusBudgetReconciliationAct,
    MedicareConditionallyPrimary,
    Disability,
    DisabilityBenefits,
    ExclusiveProviderOrganization,
    FamilyOrFriends,
    GroupPolicy,
    HealthMaintenanceOrganization,
    HealthMaintenanceOrganizationMedicareRisk,
    SpecialLowIncomeMedicareBeneficiary,
    Indemnity,
    IndividualPolicy,
    LongTermCare,
    LongTermPolicy,
    LifeInsurance,
    Litigation,
    MedicarePartA,
    MedicarePartB,
    Medicaid,
    MedigapPartA,
    MedigapPartB,
    MedicarePrimary,
    Other,
    PropertyInsurancePersonal,
    Personal,
    PersonalPayment,
    PreferredProviderOrganization,
    PointOfService,
    QualifiedMedicareBeneficiary,
    PropertyInsuranceReal,
    SupplementalPolicy,
    TaxEquityFiscalResponsibilityAct,
    WorkersCompensation,
    WrapUpPolicy;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitInsuranceTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}