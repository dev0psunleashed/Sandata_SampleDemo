package com.sandata.lab.billing.edi.model.enums;


public enum ClaimStatusType
{
    Unknown,
    Denied,
    NotOurClaimForwardedToAdditionalPayers,
    PredeterminationPricingOnlyNoPayment,
    ProcessedAsPrimary,
    ProcessedAsPrimaryForwardedToAdditionalPayers,
    ProcessedAsSecondary,
    ProcessedAsSecondaryForwardedToAdditionalPayers,
    ProcessedAsTertiary,
    ProcessedAsTertiaryForwardedToAdditionalPayers,
    ReversalOfPreviousPayment;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimStatusType forValue(int value)
    {
        return values()[value];
    }
}