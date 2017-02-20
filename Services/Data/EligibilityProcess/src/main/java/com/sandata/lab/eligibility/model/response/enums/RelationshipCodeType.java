package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of relationship codes allowed.
*/
public enum RelationshipCodeType
{
    Unknown,
    Spouse,
    Self,
    Child,
    Employee,
    OrganDonor,
    CadaverDonor,
    LifePartner,
    OtherRelationship;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RelationshipCodeType forValue(int value)
    {
        return values()[value];
    }
}