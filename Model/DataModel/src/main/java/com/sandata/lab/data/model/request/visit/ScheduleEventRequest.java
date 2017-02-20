package com.sandata.lab.data.model.request.visit;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;
import java.util.UUID;

/**
 * Date: 11/12/15
 * Time: 11:40 AM
 */

public class ScheduleEventRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Date fromDate;
    private Date toDate;
    private String dnis;
    private String staffId;
    private String vv_id;
    private String ani;
    private String patientId;//will be included only if sent from call server
    private UUID requestId;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getVv_id() {
        return vv_id;
    }

    public void setVv_id(String vv_id) {
        this.vv_id = vv_id;
    }
}
