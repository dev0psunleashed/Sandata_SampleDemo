package com.sandata.lab.billing.edi.model.enums;


public enum ClaimPricingMethodologyType
{
    Unknown,
    AdjustmentPricing,
    BundledPricing,
    CombinationPricing,
    CostReimbursed,
    FlatRatePricing,
    LowerOfCost,
    MaternityPricing,
    OtherPricing,
    PeerReviewPricing,
    PerDiemPricing,
    PricedAsBilledAt100percent,
    PricedAtAContractualPercentage,
    PricedAtTheStandardFeeSchedule,
    RatioOfCost,
    ZeroPricing;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimPricingMethodologyType forValue(int value)
    {
        return values()[value];
    }
}