package com.sandata.lab.billing.edi.model.enums;


public enum IndustryCodeType
{
    Unknown,
    ClaimPaymentRemarkCodes,
    CMSDurableMedicalEquipmentRegionalCarrierCertificateofMedicalNecessity,
    FormTypeCode,
    NationalCouncilForPrescriptionDrugProgramsRejectPaymentCodes;

    public int getValue()
    {
        return this.ordinal();
    }

    public static IndustryCodeType forValue(int value)
    {
        return values()[value];
    }
}