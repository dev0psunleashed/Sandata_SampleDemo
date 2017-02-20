package com.sandata.lab.data.model.request.visit;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.constants.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11/16/15
 * Time: 2:14 PM
 */

public class PatientStaffRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String dnis;
    private String patientId;
    private String staffId;
    private List<String> staffIdFilterList;
    private Filter.OPTIONS filter;
    private String ani;
    private boolean clientEntered = false;


    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Filter.OPTIONS getFilter() {
        return filter;
    }

    public void setFilter(Filter.OPTIONS filter) {
        this.filter = filter;
    }


    public List<String> getStaffIdFilterList() {
        if(staffIdFilterList == null)
            staffIdFilterList = new ArrayList<>();
        return staffIdFilterList;
    }

    public void setStaffIdFilterList(List<String> staffIdFilterList) {
        this.staffIdFilterList = staffIdFilterList;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public boolean isClientEntered() {
        return clientEntered;
    }

    public void setClientEntered(boolean clientEntered) {
        this.clientEntered = clientEntered;
    }
}
