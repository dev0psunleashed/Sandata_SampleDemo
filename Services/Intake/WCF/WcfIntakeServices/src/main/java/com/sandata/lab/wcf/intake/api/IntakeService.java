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

package com.sandata.lab.wcf.intake.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.staff.Staff;

import java.util.List;

/**
 * Intake services that exposes WCF import endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public interface IntakeService {

    public Response insertStaffMember(Staff staffMember);
    public Response insertStaffMembers(List<Staff> staffMembers);
    public Response insertPatient(Patient patient);
    public Response insertPatients(List<Patient> patients);
    public Response insertSchedules(List<Schedule> schedules) throws SandataRuntimeException;
}
