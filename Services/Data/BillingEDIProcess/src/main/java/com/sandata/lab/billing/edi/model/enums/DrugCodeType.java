package com.sandata.lab.billing.edi.model.enums;


public enum DrugCodeType
{
    Unknown,
    CustomerOrderNumber,
    EANUCC13,
    EANUCC8,
    GTIN14DigitDataStructure,
    HIBCSupplierLabelingStandardPrimaryDataMessage,
    NationalDrugCodein542Format,
    UCC12;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DrugCodeType forValue(int value)
    {
        return values()[value];
    }
}