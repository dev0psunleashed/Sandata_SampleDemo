package com.sandata.lab.billing.edi.model.enums;


public enum RemittanceHandlingCodeType
{
    Unknown,
    HandlingPartysOptionToSplitPaymentAndRemittance,
    MakePaymentOnly,
    NotificationOnly,
    PaymentAccompaniesRemittanceAdvice,
    PrenotificationOfFutureTransfers,
    RemittanceInformationOnly,
    SplitPaymentandRemittance;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RemittanceHandlingCodeType forValue(int value)
    {
        return values()[value];
    }
}