package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of benefit coverage level codes allowed.
 Use the CoverageLevelCodes static class to get more information about each code.
*/
public enum BenefitCoverageLevelCodeType
{
    Unknown,
    ChildrenOnly,
    DependentsOnly,
    EmployeeAndChildren,
    EmployeeAndSpouse,
    Family,
    Individual,
    SpouseAndChildren,
    SpouseOnly;

    public int getValue()
    {
        return this.ordinal();
    }

    public static BenefitCoverageLevelCodeType forValue(int value)
    {
        return values()[value];
    }
}