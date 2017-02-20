package com.sandata.lab.billing.edi.model.enums;


public enum ClaimDelayReasonType
{
    Unknown,
    AdministrationDelayInPriorApprovalProcess,
    AuthorizationDelays,
    DelayInSupplyingBillingForms,
    DelayInCertifyingProvider,
    DelayInDeliveryOfCustommadeAppliances,
    DelayInEligibilityDetermination,
    Litigation,
    NaturalDisaster,
    OriginalClaimRejectedOrDeniedDueToAReasonUnrelatedToTheBillingLimitationRules,
    Other,
    ProofOfEligibilityUnknownOrUnavailable,
    ThirdPartyProcessingDelay;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimDelayReasonType forValue(int value)
    {
        return values()[value];
    }
}