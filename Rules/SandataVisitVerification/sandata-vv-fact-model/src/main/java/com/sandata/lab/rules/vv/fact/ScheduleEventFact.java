package com.sandata.lab.rules.vv.fact;

import java.io.Serializable;
import java.util.List;

import com.sandata.lab.data.model.dl.model.ScheduleEvent;


/**
 * 
 * @author thanhxle
 *
 */
public class ScheduleEventFact extends ScheduleEvent {

    static final long serialVersionUID = 1L;

    private String ani;

    private String staffVisitVerificationId;

    private String patientVisitVerificationId;

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getStaffVisitVerificationId() {
        return staffVisitVerificationId;
    }

    public void setStaffVisitVerificationId(String staffVisitVerificationId) {
        this.staffVisitVerificationId = staffVisitVerificationId;
    }

    public String getPatientVisitVerificationId() {
        return patientVisitVerificationId;
    }

    public void setPatientVisitVerificationId(String patientVisitVerificationId) {
        this.patientVisitVerificationId = patientVisitVerificationId;
    }
}
