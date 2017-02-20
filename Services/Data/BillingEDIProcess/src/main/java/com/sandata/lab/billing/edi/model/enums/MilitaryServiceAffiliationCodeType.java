package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of military service affiliation codes allowed.
 Use the MilitaryServiceAffiliationCodes static class to get more information about each code.
*/
public enum MilitaryServiceAffiliationCodeType
{
    Unknown,
    AirForce,
    AirForceReserves,
    Army,
    ArmyReserves,
    CoastGuard,
    MarineCorps,
    MarineCorpsReserves,
    NationalGuard,
    Navy,
    NavyReserves,
    Other,
    PeaceCorp,
    RegularArmedForces,
    Reserves,
    USPublicHealthService,
    ForeignMilitary,
    AmericanRedCross,
    DepartmentOfDefense,
    UnitedServicesOrganization,
    MilitarySealiftCommand;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MilitaryServiceAffiliationCodeType forValue(int value)
    {
        return values()[value];
    }
}