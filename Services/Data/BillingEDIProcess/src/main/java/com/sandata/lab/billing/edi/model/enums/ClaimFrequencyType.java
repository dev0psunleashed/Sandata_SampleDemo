package com.sandata.lab.billing.edi.model.enums;


public enum ClaimFrequencyType
{
    Unknown,
    AdmissionElectionNotice,
    AdmitThruDischargeClaim,
    FinalClaimForHomeHealthPPSEpisode,
    HospiceChangeOfProviderNotice,
    HospiceCMSDemoTerminationRevocation,
    InterimContinuingClaim,
    InterimFirstClaim,
    InterimLastClaim,
    LateCharges,
    ReplacementOfPriorClaim,
    VoidCancelOfPriorClaim;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ClaimFrequencyType forValue(int value)
    {
        return values()[value];
    }
}