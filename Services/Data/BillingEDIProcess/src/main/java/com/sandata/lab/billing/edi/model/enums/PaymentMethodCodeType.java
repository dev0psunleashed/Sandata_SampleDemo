package com.sandata.lab.billing.edi.model.enums;


public enum PaymentMethodCodeType
{
    Unknown,
    AutomatedClearingHouse,
    Check,
    FederalReserveFundsWireTransferNonrepetitive,
    FinancialInstitutionOption,
    NonPaymentData;

    public int getValue()
    {
        return this.ordinal();
    }

    public static PaymentMethodCodeType forValue(int value)
    {
        return values()[value];
    }
}