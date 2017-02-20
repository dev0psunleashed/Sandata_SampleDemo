package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of military service rank codes allowed.
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
    MajorGenral,
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