package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of health care services delivery calendar pattern codes allowed.
*/
public enum HSDCalendarPatternCodeType
{
    Unknown,
    FirstWeekOfTheMonth,
    SecondWeekOfTheMonth,
    ThirdWeekOfTheMonth,
    FourthWeekOfTheMonth,
    FifthWeekOfTheMonth,
    FirstAndThirdWeeksOfTheMonth,
    SecondAndFourthWeeksOfTheMonth,
    FirstWorkingDayOfThePeriod,
    LastWorkingDayOfThePeriod,
    MondayThroughFriday,
    MondayThroughSaturday,
    MondayThroughSunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday,
    MondayThroughThursday,
    Immediately,
    AsDirected,
    DailyMondayThroughFriday,
    HalfMondayAndHalfThursday,
    HalfTuesdayAndHalfThursday,
    HalfWednesdayAndHalfFriday,
    OnceAnytimeMondayThroughFriday,
    TuesdayThroughFriday,
    MondayTuesdayAndThursday,
    MondayTuesdayAndFriday,
    WednesdayAndThursday,
    MondayWednesdayAndFriday,
    TuesdayThursdayAndFriday,
    HalfTuesdayAndHalfFriday,
    HalfMondayAndHalfWednesday,
    OneThirdMondayOneThirdWednesdayAndOneThirdFriday,
    WheneverNecessary,
    HalfByWednesdayBalanceByFriday,
    None;

    public int getValue()
    {
        return this.ordinal();
    }

    public static HSDCalendarPatternCodeType forValue(int value)
    {
        return values()[value];
    }
}