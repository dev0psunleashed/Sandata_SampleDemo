package com.sandata.lab.billing.edi.model.enums;


public enum AttachmentTransmissionType
{
    Unknown,
    AvailableonRequestatProviderSite,
    ByFax,
    ByMail,
    CertificationIncludedInThisClaim,
    ElectronicallyOnly,
    Email,
    FileTransfer,
    NoDocumentationRequired,
    NotSpecified,
    PreviouslySubmitterToPayer;

    public int getValue()
    {
        return this.ordinal();
    }

    public static AttachmentTransmissionType forValue(int value)
    {
        return values()[value];
    }
}