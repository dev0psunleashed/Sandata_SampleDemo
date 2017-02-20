package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of Follow-up action codes allowed.
*/
public enum FollowUpActionCodeType
{
    Unknown,
    PleaseCorrectAndResubmit,
    ResubmissionNotAllowed,
    PleaseResubmitOriginalTransaction,
    ResubmissionAllowed,
    DoNotResubmitInquiryInitiatedtoThirdParty,
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