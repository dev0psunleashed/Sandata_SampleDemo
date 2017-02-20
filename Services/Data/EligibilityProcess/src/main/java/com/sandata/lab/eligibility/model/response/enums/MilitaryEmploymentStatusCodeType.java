package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of military employment status codes allowed.
*/
public enum MilitaryEmploymentStatusCodeType
{
    Unknown,
    ActiveReserve,
    ActiveMilitaryOverseas,
    AcademyStudent,
    PresidentialAppointee,
    ActiveMilitaryUSA,
    Contractor,
    DishonorablyDischarged,
    HonorablyDischarged,
    InactiveReserves,
    LeaveOfAbsenceMilitary,
    PlanToEnlist,
    Recommissioned,
    RetiredMilitaryOverseas,
    RetiredWithoutRecall,
    RetiredMilitaryUSA;

    public int getValue()
    {
        return this.ordinal();
    }

    public static MilitaryEmploymentStatusCodeType forValue(int value)
    {
        return values()[value];
    }
}