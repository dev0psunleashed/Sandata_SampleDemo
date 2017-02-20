package com.sandata.lab.billing.edi.model.enums;


public enum MedicareSecondaryType
{
    Unknown,
    BlackLung,
    DisabledBeneficiaryUnderAge65WithLargeGroupHealthPlan,
    EndStageRenalDiseaseBeneficiaryInTheMandatedCoordinationPeriodWithEmployersGroupHealthPlan,
    NoFaultInsuranceIncludingAutoIsPrimary,
    OtherLiabilityInsuranceIsPrimary,
    PublicHealthServiceOrOtherFederalAgency,
    VeteransAdministration,
    WorkersCompensation,
    WorkingAgedBeneficiaryOrSpouseWithEmployerGroupHealthPlan;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MedicareSecondaryType forValue(int value)
    {
        return values()[value];
    }
}