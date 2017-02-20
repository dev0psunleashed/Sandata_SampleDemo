package com.sandata.lab.billing.edi.model.enums;


public enum PayerPriorityType
{
    Unknown,
    PayerResponsibilityFour,
    PayerResponsibilityFive,
    PayerResponsibilitySix,
    PayerResponsibilitySeven,
    PayerResponsibilityEight,
    PayerResponsibilityNine,
    PayerResponsibilityTen,
    PayerResponsibilityEleven,
    Primary,
    Secondary,
    Tertiary;

    public int getValue()
    {
        return this.ordinal();
    }

    public static PayerPriorityType forValue(int value)
    {
        return values()[value];
    }
}