package com.sandata.lab.rest.patient.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.PatientContactAddress;
import com.sandata.lab.data.model.dl.model.PatientContactEmail;
import com.sandata.lab.data.model.dl.model.PatientContactPhone;

import java.util.List;

/**
 * Defines the patient contact details entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class PatientContactDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientContactAddress")
    List<PatientContactAddress> patientContactAddressList;

    @SerializedName("PatientContactPhone")
    List<PatientContactPhone> patientContactPhoneList;

    @SerializedName("PatientContactEmail")
    List<PatientContactEmail> patientContactEmailList;

    public List<PatientContactAddress> getPatientContactAddressList() {
        return patientContactAddressList;
    }

    public void setPatientContactAddressList(List<PatientContactAddress> patientContactAddressList) {
        this.patientContactAddressList = patientContactAddressList;
    }

    public List<PatientContactPhone> getPatientContactPhoneList() {
        return patientContactPhoneList;
    }

    public void setPatientContactPhoneList(List<PatientContactPhone> patientContactPhoneList) {
        this.patientContactPhoneList = patientContactPhoneList;
    }

    public List<PatientContactEmail> getPatientContactEmailList() {
        return patientContactEmailList;
    }

    public void setPatientContactEmailList(List<PatientContactEmail> patientContactEmailList) {
        this.patientContactEmailList = patientContactEmailList;
    }
}
