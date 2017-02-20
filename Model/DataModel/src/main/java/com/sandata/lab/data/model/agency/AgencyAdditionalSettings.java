package com.sandata.lab.data.model.agency;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * <p>AgencyAdditionalSettings.java</p>
 * <p>Additional settings for agency management.</p>
 * <p>Date: 6/1/16</p>
 * <p>Time: 4:26 PM</p>
 * @author jasonscott
 */
public class AgencyAdditionalSettings extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientAssessmentFrequency")
    protected String patientAssessmentFrequency;

    @SerializedName("DefaultStatusForNewStaff")
    protected String defaultStatusForNewStaff;

    @SerializedName("NumberOfUnscheduledDaysForAutoTermination")
    protected String numberOfUnscheduledDaysForAutoTermination;

    @SerializedName("ShowStaffUnionStatus")
    protected boolean showStaffUnionStatus;

    @SerializedName("DefaultUnionStatus")
    protected String defaultUnionStatus;

    @SerializedName("AutoGeneratePatientAndStaffId")
    protected boolean autoGeneratePatientAndStaffId;

    public String getPatientAssessmentFrequency() {
        return patientAssessmentFrequency;
    }

    public void setPatientAssessmentFrequency(String patientAssessmentFrequency) {
        this.patientAssessmentFrequency = patientAssessmentFrequency;
    }

    public String getDefaultStatusForNewStaff() {
        return defaultStatusForNewStaff;
    }

    public void setDefaultStatusForNewStaff(String defaultStatusForNewStaff) {
        this.defaultStatusForNewStaff = defaultStatusForNewStaff;
    }

    public String getNumberOfUnscheduledDaysForAutoTermination() {
        return numberOfUnscheduledDaysForAutoTermination;
    }

    public void setNumberOfUnscheduledDaysForAutoTermination(String numberOfUnscheduledDaysForAutoTermination) {
        this.numberOfUnscheduledDaysForAutoTermination = numberOfUnscheduledDaysForAutoTermination;
    }

    public boolean isShowStaffUnionStatus() {
        return showStaffUnionStatus;
    }

    public void setShowStaffUnionStatus(boolean showStaffUnionStatus) {
        this.showStaffUnionStatus = showStaffUnionStatus;
    }

    public String getDefaultUnionStatus() {
        return defaultUnionStatus;
    }

    public void setDefaultUnionStatus(String defaultUnionStatus) {
        this.defaultUnionStatus = defaultUnionStatus;
    }

    public boolean isAutoGeneratePatientAndStaffId() {
        return autoGeneratePatientAndStaffId;
    }

    public void setAutoGeneratePatientAndStaffId(boolean autoGeneratePatientAndStaffId) {
        this.autoGeneratePatientAndStaffId = autoGeneratePatientAndStaffId;
    }
}
