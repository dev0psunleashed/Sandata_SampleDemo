package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit quantity type codes allowed.
*/
public enum BenefitQuantityTypeCodeType
{
    Unknown,
    Minimum,
    QuantityUsed,
    CoveredActual,
    CoveredEstimated,
    NumberCoInsuranceDays,
    DeductibleBloodUnits,
    Days,
    Hours,
    LifetimeReserveActual,
    LifetimeReserveEstimated,
    Maximum,
    Month,
    NumberOfServicesOrProcedures,
    QuantityApproved,
    AgeHighValue,
    AgeLowValue,
    Visits,
    Years;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitQuantityTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}