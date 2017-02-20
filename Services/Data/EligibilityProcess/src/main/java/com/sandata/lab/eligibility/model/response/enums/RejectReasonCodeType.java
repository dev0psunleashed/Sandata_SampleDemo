package com.sandata.lab.eligibility.model.response.enums;


/** 
 This is the list of Reject reason codes allowed.
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