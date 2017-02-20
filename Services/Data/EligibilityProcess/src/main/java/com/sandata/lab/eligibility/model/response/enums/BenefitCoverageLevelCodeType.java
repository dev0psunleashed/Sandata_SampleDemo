package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of benefit coverage level codes allowed.
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