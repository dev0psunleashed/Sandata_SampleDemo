package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.rules.vv.model.VisitState;

import java.io.Serializable;

/**
 *
 */
public class PatientFact extends BaseObject {
    private static  final long serialVersionUID = 1L;

    private String patientId;
    private String ani;
    private String businessEntId;
    private VisitState visitState;
    private String patientVisitVerificationId;

    public PatientFact(){}


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PatientFact)) {
            return false;
        }
        PatientFact that = (PatientFact ) obj;

        if (visitState != that.visitState) return false;
        if (ani != null ? !ani.equals(that.ani) : that.ani != null) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;
        if (businessEntId != null ? !businessEntId.equals(that.businessEntId) : that.businessEntId != null) return false;
        if (patientVisitVerificationId != null ? !patientVisitVerificationId.equals(that.patientVisitVerificationId) : that.patientVisitVerificationId != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (visitState != null ? visitState.hashCode() : 0);
        result = 31 * result + (ani != null ? ani.hashCode() : 0);
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        result = 31 * result + (businessEntId != null ? businessEntId.hashCode() : 0);
        result = 31 * result + (patientVisitVerificationId != null ? patientVisitVerificationId.hashCode() : 0);
        return result;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getBusinessEntId() {
        return businessEntId;
    }

    public void setBusinessEntId(String businessEntId) {
        this.businessEntId = businessEntId;
    }

    public VisitState getVisitState() {
        return visitState;
    }

    public void setVisitState(VisitState visitState) {
        this.visitState = visitState;
    }

    public String getPatientVisitVerificationId() {
        return patientVisitVerificationId;
    }

    public void setPatientVisitVerificationId(String patientVisitVerificationId) {
        this.patientVisitVerificationId = patientVisitVerificationId;
    }
}
