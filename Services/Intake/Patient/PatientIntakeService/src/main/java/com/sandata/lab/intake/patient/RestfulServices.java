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

package com.sandata.lab.intake.patient;

import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServices {

    @POST
    @Path("/patient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response INSERT_PATIENT(Patient patient) {
        return null;
    }

    @POST
    @Path("/patients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response INSERT_PATIENTS(List<Patient> patients) {
        return null;
    }

    @PUT
    @Path("/patient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response UPDATE_PATIENT(Patient patient) {
        return null;
    }

    @PUT
    @Path("/patients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response UPDATE_PATIENTS(List<Patient> patients) {
        return null;
    }

    @DELETE
    @Path("/patient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response DELETE_PATIENT(Patient patient) {
        return null;
    }

    @DELETE
    @Path("/patients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response DELETE_PATIENTS(List<Patient> patients) {
        return null;
    }
}
