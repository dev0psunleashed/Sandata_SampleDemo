package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of military service rank codes allowed.
 Use the MilitaryServiceRankCodes static class to get more information about each code.
*/
public enum MilitaryServiceRankCodeType
{
    Unknown,
    Admiral,
    Airman,
    AirmanFirstClass,
    BasicAirman,
    BrigadierGeneral,
    Captain,
    ChiefMasterSergeant,
    ChiefPettyOfficer,
    ChiefWarrant,
    Colonel,
    Commander,
    Commodore,
    Corporal,
    CorporalSpecialist4,
    Ensign,
    FirstLieutenant,
    FirstSergeant,
    FirstSergeantMasterSergeant,
    FleetAdmiral,
    General,
    GunnerySergeant,
    LanceCorporal,
    Lieutenant,
    LieutenantColonel,
    LieutenantCommander,
    LieutenantGeneral,
    LieutenantJuniorGrade,
    Major,
    MajorGeneral,
    MasterChiefPettyOfficer,
    MasterGunnerySergeantMajor,
    MasterSergeant,
    MasterSergeantSpecialist8,
    PettyOfficerFirstClass,
    PettyOfficerSecondClass,
    PettyOfficerThirdClass,
    Private,
    PrivateFirstClass,
    RearAdmiral,
    Recruit,
    Seaman,
    SeamanApprentice,
    SeamanRecruit,
    SecondLieutenant,
    SeniorChiefPettyOfficer,
    SeniorMasterSergeant,
    Sergeant,
    SergeantFirstClassSpecialist7,
    SergeantMajorSpecialist9,
    SergeantSpecialist5,
    StaffSergeant,
    StaffSergeantSpecialist6,
    TechnicalSergeant,
    ViceAdmiral,
    WarrantOfficer;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MilitaryServiceRankCodeType forValue(int value)
    {
        return values()[value];
    }
}