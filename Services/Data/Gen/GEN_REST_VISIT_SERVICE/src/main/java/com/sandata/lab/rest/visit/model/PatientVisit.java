package com.sandata.lab.rest.visit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.extended.visit.VisitExt;

import java.math.BigInteger;

/**
 * Models the visits for a given patient.
 * <p/>
 *
 * @author David Rutgos
 */
public class PatientVisit extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffSK")
    private BigInteger staffSk;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("ServiceName")
    private ServiceName serviceName;

    @SerializedName("Visit")
    private VisitExt visitExt;

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public VisitExt getVisitExt() {
        return visitExt;
    }

    public void setVisitExt(VisitExt visitExt) {
        this.visitExt = visitExt;
    }

    public BigInteger getStaffSk() {
        return staffSk;
    }

    public void setStaffSk(BigInteger staffSk) {
        this.staffSk = staffSk;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }
}
