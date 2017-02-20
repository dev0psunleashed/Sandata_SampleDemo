package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of gender codes allowed.
*/
public enum DiagnosisCodeType
{
    Unknown,
    PrincipalDiagnosisICD10,
    PrincipalDiagnosisICD9,
    DiagnosisICD10,
    DiagnosisICD9;

    public int getValue()
    {
        return this.ordinal();
    }

    public static DiagnosisCodeType forValue(int value)
    {
        return values()[value];
    }
}