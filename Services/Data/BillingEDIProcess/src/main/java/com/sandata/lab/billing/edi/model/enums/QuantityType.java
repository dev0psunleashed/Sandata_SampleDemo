package com.sandata.lab.billing.edi.model.enums;


public enum QuantityType
{
    Unknown,
    CoInsuredActual,
    CoveredActual,
    FederalMedicareOrMedicaidPaymentMandateCategory1,
    FederalMedicareOrMedicaidPaymentMandateCategory2,
    FederalMedicareOrMedicaidPaymentMandateCategory3,
    FederalMedicareOrMedicaidPaymentMandateCategory4,
    FederalMedicareOrMedicaidPaymentMandateCategory5,
    LifetimeReserveActual,
    LifetimeReserveEstimated,
    NonCoveredEstimated,
    NotReplacedBloodUnits,
    OutlierDays,
    Patients,
    Prescription,
    Units,
    Visits;

    public int getValue()
    {
        return this.ordinal();
    }

    public static QuantityType forValue(int value)
    {
        return values()[value];
    }
}