package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of Follow-up action codes allowed.
 Use the FollowUpActionCodes static class to get more information about each code.
*/
public enum FollowUpActionCodeType
{
    Unknown,
    PleaseCorrectAndResubmit,
    ResubmissionNotAllowed,
    PleaseResubmitOriginalTransaction,
    ResubmissionAllowed,
    DoNotResubmitInquiryInitiatedToThirdParty,
    PleaseWait30DaysAndResubmit,
    PleaseWait10DaysAndResubmit,
    DoNotResubmitWeWillHoldYourRequest;

    public int getValue()
    {
        return this.ordinal();
    }

    public static FollowUpActionCodeType forValue(int value)
    {
        return values()[value];
    }
}