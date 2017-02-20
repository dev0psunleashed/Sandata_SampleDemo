package com.sandata.lab.data.model.response.visit;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Patient;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;

import java.util.List;

/**
 * Date: 11/16/15
 * Time: 2:26 PM
 */

public class PatientStaffResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private PatientStaffRequest patientStaffRequest;
    private List<Patient> patients;
    private List<Staff> staffList;

    public PatientStaffRequest getPatientStaffRequest() {
        return patientStaffRequest;
    }

    public void setPatientStaffRequest(PatientStaffRequest patientStaffRequest) {
        this.patientStaffRequest = patientStaffRequest;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
