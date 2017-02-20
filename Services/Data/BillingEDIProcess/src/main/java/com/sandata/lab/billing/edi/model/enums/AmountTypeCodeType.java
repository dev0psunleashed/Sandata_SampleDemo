package com.sandata.lab.billing.edi.model.enums;


public enum AmountTypeCodeType
{
    Unknown,
    AllowedActual,
    Coverage,
    Deduction,
    Discount,
    FederalMedicareOrMedicaidPaymentMandateCategory1,
    FederalMedicareOrMedicaidPaymentMandateCategory2,
    FederalMedicareOrMedicaidPaymentMandateCategory3,
    FederalMedicareOrMedicaidPaymentMandateCategory4,
    FederalMedicareOrMedicaidPaymentMandateCategory5,
    GoodsandServicesTax,
    Interest,
    MiscellaneousTaxes,
    NegativeLedgerBalance,
    NonCoveredChargesActual,
    Owed,
    PatientPaid,
    PatientResponsibilityEstimated,
    PayerPaid,
    PerDayLimit,
    Tax,
    TotalClaimBeforeTaxes,
    TotalSubmittedCharges;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AmountTypeCodeType forValue(int value)
    {
        return values()[value];
    }
}