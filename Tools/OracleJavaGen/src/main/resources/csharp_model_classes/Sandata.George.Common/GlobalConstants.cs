using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sandata.George.Common
{
    public static class GlobalConstants
    {
        public const string GEORGE = "George";

        #region Cache keys

        public const string SESSION_INTERNAL_SERVICE_PROXY_KEY = "InternalService_a34c42f8-eb7c-4781-bc4e-adb8942c5d64";
        public const string SESSION_LOG_SERVICE_PROXY_KEY = "LogService_e3de6ad1-ff96-4950-a92f-4c7b398eaff9";
        public const string SESSION_AUDIT_SERVICE_PROXY_KEY = "AuditService_085a4802-7860-40dc-8d20-a35c2b0d4fe9";
        public const string ETHNICITY_KEY = "Ethnicity_702fafd4-055d-4ae5-b7f9-416c601df2ae";
        public const string RELIGION_KEY = "Religion_22e87e63-8589-4e7e-847e-1eef8fdfa47a";
        public const string MARITAL_STATUS_KEY = "MaritalStatus_dbf4027b-0551-473f-91df-d598be4a7be4";
        public const string GENDER_KEY = "Gender_6cf9296c-ac54-4e78-93a7-6b636e78a7ca";
        public const string MEDICATION_ROUTE = "MedicationRoute_f0526e65-70f1-4737-b5ce-4e8462900629";
        public const string MEDICATION_CLASSIFICATION = "MedicationClassification_4dde3bca-c844-4082-b2ef-16e99174b77b";
        public const string MEDICATION_STRENGTH = "MedicationStrength_5cd829e2-8562-45b9-a1e8-6decd4b9c5bc";
        public const string REFERENCE_FORMAT = "ReferenceFormat_0c9786e7-3122-4ae5-a30f-48067df61e2c";
        public const string LIMIT_BY = "LimitBy_5b3a9afb-9333-4b03-b001-65a05d64ce3d";
        public const string ELIGIBILITY = "Eligibility_22de9633-8076-4cfb-b05e-748bdd71808d";
        public const string DISASTER_LEVEL = "DisasterLevel_e0a1cb2e-4572-48f9-a96c-2b1a7694f466";
        public const string DNR = "DNR_ce92db6e-95b4-406b-93aa-05d960a0360a";
        public const string STATES = "States_a2267aeb-37cc-4c19-aa17-5b45fac077e8";
        public const string LANGUAGES = "Languages_7bea523b-db33-4db9-aa0a-c727f3139871";
        public const string SERVICE = "Service_514e4662-22ec-4579-a615-7775b1cadf6f";
        public const string ADMISSION_TYPE_KEY = "AdmissionType_3241F42D-49C9-4C36-8FAF-D32E1E199BD0";
        public const string TRANSPORTATION_TYPE_KEY = "TransportationType_CADC3971-33F8-4C97-AB24-CA243FA97BE1";
        public const string TASKS = "Tasks_62bfc0ae-d4f7-49db-9e33-04a51a4bab9b";
        public const string POSITION_KEY = "Position_C0316E1A-E80A-4119-9364-A81E33AC593A";
        public const string MILITARY_STATUS_KEY = "MilitaryStatus_52CDB7B7-B4DB-4405-8CD3-40445295882E";
        public const string STAFF_TYPE_KEY = "StaffType_dcbd9078-b923-4522-9052-0616e4b1bc50";
        public const string STAFF_CLASS_KEY = "StaffClass_E78776A2-9A59-440E-B896-88768230EF78";
        public const string EMPLOYMENT_STATUS_KEY = "EmploymentStatus_8BF66AD7-2A0B-431A-8422-11D9D4ECD9E0";
        public const string MEDICAL_COMPLIANCE_KEY = "MedicalCompliance_e2ca8a91-42ee-4075-b2bf-1da76855a8b9";
        public const string OTHER_COMPLIANCE_SUBJECT_KEY = "OtherComplianceSubject_4246854f-fcc1-497b-bcd5-d0fa4d887be6";
        public const string AGENCY_TRAININGS = "AgencyTrainings_1a173012-ad4e-4cde-aed8-1f0e5c54c41f";
        public const string NOTIFICTAION_TYPES_KEY = "NotificationTypes_b3eefaf0-ba45-4688-abb0-99f51f503dc8";
        public const string STATE_TAX_KEY = "StateTax_3D564F7D-5C89-40F7-BFFA-68908B5925AC";
        public const string CITY_TAX_KEY = "CityTax_370E8E33-A6EF-4E8A-B955-163D04E37E6B";
        public const string PAY_FREQUENCY_KEY = "PayFrequency_6B53094B-3D73-409A-9401-262CA0E31026";
        public const string SKILL_KEY = "Skill_50FBAC7E-EABA-419B-960E-20E66DDF5183";
        public const string PREFERENCE_KEY = "Preference_06A1D41A-9398-45F6-A048-084003E7DDFC";
        public const string PAY_TYPE_KEY = "PayType_BA3E61C3-3B53-40B0-800E-B649CD17DED0";
        public const string CONTACT_TYPE_KEY = "ContactType_7d698a90-f58c-4423-9573-cbf825af0130";
        public const string PREFERRED_CONTACT_KEY = "PreferredContact_2cd11e56-8286-4e97-b8db-804e5f23cead";
        public const string AGENCIES_KEY = "AgenciesKey_d2008f78-5018-4863-b65b-7a52919315a1";
        public static string PATIENT_FIRST_NAMES_KEY = "PatienFirstNames_0c97dce1-c720-4032-8212-f569d1f936c2";
        public static string PATIENT_LAST_NAMES_KEY = "PatientLastNames_58a1ade6-832c-4d67-aeb9-de532453170c";
        public const string CACHE_DURATION_MIN_KEY = "CacheDurationMinutes";
        public const string USER_ID_KEY = "UserId_fd276564-34a8-4b6b-be22-c1ad7723e026";
        public const string NOTE_TYPE_KEY = "NoteType_93197952-D714-4C41-B6BE-8666A385C52F";
        public const string FREQUENCY_KEY = "Frequency_647e95a2-6b90-4677-8284-1449f0121921";
        public const string TYPE_OF_CARE_KEY = "TypeOfCare_cd70b2b2-17c5-4873-a3a6-8b71497b83e6";
        public const string ADMIT_TYPE_KEY = "AdmitType_cd70b2b2-17c5-4873-a3a6-8b71497b83e6";
        public const string SERVICE_CALL_NAMESPACE = "http://george.services.com";

        #endregion Cache keys

        #region Custom formats

        public const String DATE_FORMAT = "MM-dd-yyyy";
        public const String DATETIME_FORMAT = "MM-dd-yyyy HH:mm";
        public const String DATETIME_FORMAT_AMPM = "MM-dd-yyyy hh:mm tt";

        public const String TIMESPAN_TIME_FORMAT = @"hh\:mm";
        public const String TIMESPAN_TIME_FORMAT_AMPM = @"hh\:mm tt";

        #endregion Custom formats

        #region Data Annotations

        public const String INPUT_REQUIRED = "Required";

        public const String SSN_VALIDATION_REGEX = @"(^\d{3}-\d{2}-\d{4}$)";
        public const String SSN_VALIDATION_MSG = "Invalid SSN (XXX-XX-XXXX)";

        public const String ZIP_VALIDATION_REGEX = @"(^\d{5}$)";
        public const String ZIP_VALIDATION_MSG = @"Invalid Zip (XXXXX)";

        public const String PHONE_VALIDATION_REGEX = @"(^\(\d{3}\)-\d{3}-\d{4}$)";
        public const String PHONE_VALIDATION_MSG = @"Invalid Phone (XXX) XXX-XXXX";

        public const String DATE_VALIDATION_REGEX = @"(^\d{2}-\d{2}-\d{4}$)";
        public const String DATE_VALIDATION_MSG = "Invalid Date";

        public const String EMAIL_VALIDATION_MSG = "Invalid Email";
        public const string DATE_VALIDATION_FORMAT = "{0:MM-dd-yyyy}";

        #endregion Data Annotations

        #region Status Codes

        public const int STATUS_CODE_500 = 500;
        public const int STATUS_CODE_400 = 400;
        public const int STATUS_CODE_403 = 403;
        public const int STATUS_CODE_404 = 404;
        public const int STATUS_CODE_401 = 401;

        #endregion Status Codes

        public const string CLIENT_EXCEPTION = "ClientException";
        public const string INTERNAL_EXCEPTION = "InternalException";
        public const string GENERIC_SERVER_ERROR = "There was an error processing your request. Please contact your system admin for additional info.";
        public const string NOT_FOUND_ERROR = "The page you are looking for has not been found";
        public const string FAILED_REQUEST = "Failed to process request.";
        public const string AUTHORIZATION_HEADER = "Authorization";
        public const string NO_AUTHORIZATION_HEADER_MESSAGE = "No authorization header supplied.";
        public const string INVALID_AUTHORIZATION_TOKEN_MESSAGE = "Invalid authentication token.";
        public const string NOT_AVAILABLE = "n/a";
        public const bool IS_AM_PM = false; // TODO: This should be moved to other location and shouldn't be constant, once the agency info is in place
    }
}
