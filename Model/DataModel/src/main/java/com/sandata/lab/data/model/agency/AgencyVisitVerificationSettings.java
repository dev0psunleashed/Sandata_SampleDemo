package com.sandata.lab.data.model.agency;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * <p>AgencyVisitVerificationSettings.java</p>
 * <p>Visit verification settings for agency management.</p>
 * <p>Date: 6/1/16</p>
 * <p>Time: 4:32 PM</p>
 *
 * @author jasonscott
 */
public class AgencyVisitVerificationSettings extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityId")
    protected String businessEntityId;

    @SerializedName("NumberOfCriticalTasksRequired")
    protected String numberOfCriticalTasksRequired;

    @SerializedName("RequireReasonCodes")
    protected boolean requireReasonCodes;

    @SerializedName("AllowMoreThanTwentyFourVerifiedHours")
    protected boolean allowMoreThanTwentyFourVerifiedHours;

    @SerializedName("PatientVisitVerificationIdentifier")
    protected PatientVisitVerificationIdentifier patientVisitVerificationIdentifier;

    @SerializedName("StaffVisitVerificationIdentifier")
    protected StaffVisitVerificationIdentifier staffVisitVerificationIdentifier;

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getNumberOfCriticalTasksRequired() {
        return numberOfCriticalTasksRequired;
    }

    public void setNumberOfCriticalTasksRequired(String numberOfCriticalTasksRequired) {
        this.numberOfCriticalTasksRequired = numberOfCriticalTasksRequired;
    }

    public boolean isRequireReasonCodes() {
        return requireReasonCodes;
    }

    public void setRequireReasonCodes(boolean requireReasonCodes) {
        this.requireReasonCodes = requireReasonCodes;
    }

    public boolean isAllowMoreThanTwentyFourVerifiedHours() {
        return allowMoreThanTwentyFourVerifiedHours;
    }

    public void setAllowMoreThanTwentyFourVerifiedHours(boolean allowMoreThanTwentyFourVerifiedHours) {
        this.allowMoreThanTwentyFourVerifiedHours = allowMoreThanTwentyFourVerifiedHours;
    }

    public PatientVisitVerificationIdentifier getPatientVisitVerificationIdentifier() {
        return patientVisitVerificationIdentifier;
    }

    public void setPatientVisitVerificationIdentifier(PatientVisitVerificationIdentifier patientVisitVerificationIdentifier) {
        this.patientVisitVerificationIdentifier = patientVisitVerificationIdentifier;
    }

    public StaffVisitVerificationIdentifier getStaffVisitVerificationIdentifier() {
        return staffVisitVerificationIdentifier;
    }

    public void setStaffVisitVerificationIdentifier(StaffVisitVerificationIdentifier staffVisitVerificationIdentifier) {
        this.staffVisitVerificationIdentifier = staffVisitVerificationIdentifier;
    }
}
