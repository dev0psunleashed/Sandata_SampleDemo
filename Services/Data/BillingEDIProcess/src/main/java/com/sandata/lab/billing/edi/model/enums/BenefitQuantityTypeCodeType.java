package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit quantity type codes allowed.
 Use the BenefitQuantityTypeCodes static class to get more information about each code.
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