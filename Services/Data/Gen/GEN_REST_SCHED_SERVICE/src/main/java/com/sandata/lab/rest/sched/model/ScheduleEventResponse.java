package com.sandata.lab.rest.sched.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.Patient;
import com.sandata.lab.data.model.dl.model.PlanOfCare;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Staff;

/**
 * Custom response that returns a ScheduleEvent entity as well as the Patient and Staff entities.
 * <p/>
 *
 * @author David Rutgos
 */
public class ScheduleEventResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ScheduleEvent")
    private ScheduleEvent scheduleEvent;

    @SerializedName("Patient")
    private Patient patient;

    @SerializedName("Staff")
    private Staff staff;
    
    @SerializedName("Authorization")
    private Authorization authorization;
    
    @SerializedName("PlanOfCare")
    private PlanOfCare planOfCare;

    public ScheduleEvent getScheduleEvent() {
        return scheduleEvent;
    }

    public void setScheduleEvent(ScheduleEvent scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public PlanOfCare getPlanOfCare() {
		return planOfCare;
	}

	public void setPlanOfCare(PlanOfCare planOfCare) {
		this.planOfCare = planOfCare;
	}
}
