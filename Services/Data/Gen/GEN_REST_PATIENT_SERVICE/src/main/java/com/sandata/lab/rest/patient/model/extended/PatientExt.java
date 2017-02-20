package com.sandata.lab.rest.patient.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.DoNotResuscitateName;
import com.sandata.lab.data.model.dl.model.Patient;
import org.springframework.beans.BeanUtils;

/**
 * GEOR-2629: Extends the Patient entity to allow DNR/AD indicator logic.
 *
 * REMINDER: The base class "Patient" is generated WITHOUT the setters for any LIST entities!
 *           Remember that you need to manually create the setters for all the Patient LIST entities!
 *
 * <p/>
 *
 * @author David Rutgos
 */
public class PatientExt extends Patient {

    private static final long serialVersionUID = 1L;

    public PatientExt() {
    }

    public PatientExt(Patient patient) {
        BeanUtils.copyProperties(patient, this);
        setDnrAdOption(this);
    }

    @SerializedName("NewPatient")
    private boolean newPatient = true;      //dmr-GEOR-2817 4/5/2016: "(rules to be defined). For now you can set it to true for all"

    @SerializedName("DNRADOption")
    private String dnrAdOption;

    @SerializedName("CoordinatorID")
    private String coordinatorId;

    @SerializedName("NurseID")
    private String nurseId;

    public String getDnrAdOption() {
        return dnrAdOption;
    }

    private void setDnrAdOption(DoNotResuscitateName dnrAdOptions) {
        this.dnrAdOption = dnrAdOptions.value();
    }

    public boolean isNewPatient() {
        return newPatient;
    }

    public boolean getNewPatient() {
        return newPatient;
    }

    public void setNewPatient(boolean newPatient) {
        this.newPatient = newPatient;
    }

    public boolean isDnrAdOptionValid() {

        if (getDnrAdOption() == null || getDnrAdOption().length() == 0) {
            return true;
        }

        DoNotResuscitateName doNotResuscitateName = getDnrOptionFromString(getDnrAdOption());
        if (doNotResuscitateName != null) {
            return true;
        }

        return false;
    }

    private DoNotResuscitateName getDnrOptionFromString(String option) {

        if (option != null && getDnrAdOption().length() > 0) {

            try {
                return DoNotResuscitateName.fromValue(getDnrAdOption());

            } catch (IllegalArgumentException e) {
            }
        }

        return null;
    }

    public Patient getPatient() {
        Patient patient = new Patient();
        BeanUtils.copyProperties(this, patient);

        //dmr--GEOR-2629
        /**
             a.	User selects “Yes”, PT_DNR_IND NUMBER = 1, PT_AD_IND NUMBER = 0
             b.	User selects “No”, PT_DNR_IND NUMBER = 0, PT_AD_IND NUMBER = 0
             c.	User selects “Unknown/Not Applicable”, PT_DNR_IND NUMBER = NULL, PT_AD_IND NUMBER = NULL
             d.	User selects “Advanced Directive”, PT_DNR_IND NUMBER = 0, PT_AD_IND NUMBER = 1
         */

        DoNotResuscitateName doNotResuscitateName = getDnrOptionFromString(getDnrAdOption());
        if (doNotResuscitateName != null) {

            if (doNotResuscitateName == DoNotResuscitateName.YES) {
                patient.setPatientDoNotResuscitateIndicator(true);
                patient.setPatientAdvanceDirectiveIndicator(false);

            } else if (doNotResuscitateName == DoNotResuscitateName.NO) {
                patient.setPatientDoNotResuscitateIndicator(false);
                patient.setPatientAdvanceDirectiveIndicator(false);

            } else if (doNotResuscitateName == DoNotResuscitateName.UNKNOWN_NOT_APPLICABLE) {
                patient.setPatientDoNotResuscitateIndicator(null);
                patient.setPatientAdvanceDirectiveIndicator(null);

            } else if (doNotResuscitateName == DoNotResuscitateName.ADVANCED_DIRECTIVE) {
                patient.setPatientDoNotResuscitateIndicator(false);
                patient.setPatientAdvanceDirectiveIndicator(true);
            }
        }

        return patient;
    }

    private void setDnrAdOption(PatientExt patientExt) {

        if (patientExt != null) {

            if (patientExt.isPatientDoNotResuscitateIndicator() == null
                    && patientExt.isPatientAdvanceDirectiveIndicator() == null) {

                patientExt.setDnrAdOption(DoNotResuscitateName.UNKNOWN_NOT_APPLICABLE);
            }
            else if ((patientExt.isPatientDoNotResuscitateIndicator() != null && patientExt.isPatientDoNotResuscitateIndicator())
                    && (patientExt.isPatientAdvanceDirectiveIndicator() == null || !patientExt.isPatientAdvanceDirectiveIndicator())) {

                patientExt.setDnrAdOption(DoNotResuscitateName.YES);
            }
            else if ((patientExt.isPatientDoNotResuscitateIndicator() == null || !patientExt.isPatientDoNotResuscitateIndicator())
                    && (patientExt.isPatientAdvanceDirectiveIndicator() == null || !patientExt.isPatientAdvanceDirectiveIndicator())) {

                patientExt.setDnrAdOption(DoNotResuscitateName.NO);
            }
            else if ((patientExt.isPatientDoNotResuscitateIndicator() == null || !patientExt.isPatientDoNotResuscitateIndicator())
                    && (patientExt.isPatientAdvanceDirectiveIndicator() != null && patientExt.isPatientAdvanceDirectiveIndicator())) {

                patientExt.setDnrAdOption(DoNotResuscitateName.ADVANCED_DIRECTIVE);
            }
        }
    }

    public void setDnrAdOption(String dnrAdOption) {
        this.dnrAdOption = dnrAdOption;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }
}
