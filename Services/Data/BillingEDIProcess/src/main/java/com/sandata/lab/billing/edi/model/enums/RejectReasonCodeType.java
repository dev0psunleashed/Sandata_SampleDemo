package com.sandata.lab.billing.edi.model.enums;


/** 
 This is the list of Reject reason codes allowed.
 Use the RejectReasonCodes static class to get more information about each code.
*/
public enum RejectReasonCodeType
{
    Unknown,
    AuthorizedQuantityExceeded,
    AuthorizationAccessRestrictions,
    UnableToRespondAtCurrentTime,
    InvalidParticipantIdentification,
    NoResponseReceivedTransactionTerminated,
    PayerNameOrIdentifierMissing,
    RequiredApplicationDataMissing,
    InvalidMissingProviderIdentification,
    InvalidMissingProviderName,
    InvalidMissingProviderSpecialty,
    InvalidMissingProviderPhoneNumber,
    InvalidMissingProviderState,
    InvalidMissingReferrringProviderId,
    ProviderIneligibleForInquiries,
    ProviderNotOnFile,
    InvalidOrMissingProviderAddress,
    OutOfNetwork,
    ProviderIsNotPrimaryPhysician,
    ServiceDatesNotWithinPlanEnrollment,
    InappropriateDate,
    InvalidMissingDateOfService,
    InvalidMissingDOB,
    DOBFollowsDateOfService,
    DateOfDeathPrecedesDateOfService,
    DateOfServiceNotWithinInquiryPeriod,
    DateOfServiceInFuture,
    PatientDOBDoesNotMatchPatientOnDatabase,
    InvalidMissingSubscriberID,
    InvalidMissingSubscriberName,
    InvalidMissingSubscriberGender,
    SubscriberNotFound,
    DuplicateSubscriberID,
    SubscriberNotInGroupIdentified,
    InputErrors,
    BenefitInconsistentWithProviderType,
    InappropriateProductIDQualifier,
    InappropriateProductID,
    InconsistentWithPatientAge,
    InconsistentWithPatientGender,
    ExperimentalServiceOrProcedure,
    AuthorizationNumberNotFound,
    RequiresPrimaryPhysicianAuthorization,
    InvalidMissingDiagnosisCode,
    InvalidMissingProcedureCode,
    AdditionalPatientConditionInformationRequired,
    CertificationInformationDoesNotMatch,
    RequiresMedicalReview,
    InvalidAuthorizationNumberFormat,
    MissingAuthorizationNumber,
    InvalidMissingPatientID,
    InvalidMissingPatientName,
    InvalidMissingPatientGenderCode,
    SubscriberFoundPatientNotFound,
    PatientNotFound,
    DuplicatePatientID;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RejectReasonCodeType forValue(int value)
    {
        return values()[value];
    }
}