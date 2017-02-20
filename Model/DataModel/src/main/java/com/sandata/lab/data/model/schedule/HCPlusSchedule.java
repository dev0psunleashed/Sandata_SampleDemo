/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.data.model.schedule;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;
import java.util.List;

/**
 * Models the Schedule class.
 * <p/>
 *
 * @author David Rutgos
 */
public class HCPlusSchedule extends BaseObject {

    private static final long serialVersionUID = 1L;

    private int scheduleId;
    //agencyId was part of highpoint model and also most schedule imports currently
    //in use.  Not currently part of George. TMD
    private int agencyId;
    //payerId was part of highpoint model and also most schedule imports currently
    //in use.  Not currently part of George. TMD
    private int payerId;
    //taskID was part of highpoint model and also part of POC imports currently ask for HCPlus DB
    //Not currently part of George. TMD
    private List<Integer> taskList;
    //serviceId was part of highpoint model and also most schedule imports currently
    //in use.  Not currently part of George. TMD
    private int serviceId;
    private int staffId;
    private int patientId;
    private String patientNumber;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public List<Integer> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Integer> taskList) {
        this.taskList = taskList;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }
}
