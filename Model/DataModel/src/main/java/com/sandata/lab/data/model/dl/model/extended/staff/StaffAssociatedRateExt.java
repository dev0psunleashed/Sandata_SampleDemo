package com.sandata.lab.data.model.dl.model.extended.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.StaffAssociatedRate;
import com.sandata.lab.data.model.dl.model.extended.payroll.CurrentStaffRate;
import org.springframework.beans.BeanUtils;

/**
 * Extends the StaffAssociatedRate to expose the Patient Name as requested by the UI team.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffAssociatedRateExt extends StaffAssociatedRate {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("CurrentStaffRate")
    private CurrentStaffRate currentStaffRate;

    public StaffAssociatedRateExt(StaffAssociatedRate associatedRate) {
        BeanUtils.copyProperties(associatedRate, this);
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public CurrentStaffRate getCurrentStaffRate() {
        return currentStaffRate;
    }

    public void setCurrentStaffRate(CurrentStaffRate currentStaffRate) {
        this.currentStaffRate = currentStaffRate;
    }
}
