package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of relationship codes allowed.
 Use the RelationshipCodes static class to get more information about each code.
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