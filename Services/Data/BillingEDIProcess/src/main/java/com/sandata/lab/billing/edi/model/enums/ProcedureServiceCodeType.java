package com.sandata.lab.billing.edi.model.enums;


public enum ProcedureServiceCodeType
{
    Unknown,
    AdvancedBillingConceptsCodes,
    AmericanDentalAssociationCodes,
    HealthCareFinancingAdministrationCommonProceduralCodingSystemCodes,
    HealthInsuranceProspectivePaymentSystemSkilledNursingFacilityRateCode,
    HomeInfusionEDICoalitionProductOrServiceCode,
    JurisdictionSpecificProcedureAndSupplyCodes,
    NationalDrugCodeIn542Format,
    NationalHealthRelatedItemCode46Format,
    NationalUniformBillingCommitteeRevenueCodes,
    UPCConsumerPackageCodes;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ProcedureServiceCodeType forValue(int value)
    {
        return values()[value];
    }
}