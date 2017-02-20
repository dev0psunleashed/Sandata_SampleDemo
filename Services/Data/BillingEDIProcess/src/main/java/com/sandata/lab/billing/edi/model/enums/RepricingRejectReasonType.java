package com.sandata.lab.billing.edi.model.enums;


public enum RepricingRejectReasonType
{
    Unknown,
    CannotIdentifyProviderAsTPOParticipant,
    CannotIdentifyPayerAsTPOParticipant,
    CannotIdentifyInsuredAsTPOParticipant,
    PayerNameOrIdentifierMissing,
    CertificationInformationMissing,
    ClaimDoesNotContainEnoughInformationForRepricing;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RepricingRejectReasonType forValue(int value)
    {
        return values()[value];
    }
}