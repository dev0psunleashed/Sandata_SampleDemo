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

package com.sandata.one.aggregator.documents;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class RestfulServices {

    @GET
    @Path("/file/{document_id}")
    @Produces(value = {MediaType.APPLICATION_OCTET_STREAM})
    public final javax.ws.rs.core.Response GET_DOCUMENT_FILE(@PathParam("document_id") String docId,
                                                             @DefaultValue("document") @MatrixParam("data_field") String docDataField) {
        return null;
    }

    @GET
    @Path("/patient/signature/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_OCTET_STREAM})
    public final javax.ws.rs.core.Response GET_PATIENT_SIG_FILE(@PathParam("visit_sk") long visitSk) {
        return null;
    }

    @GET
    @Path("/patient/audio/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_OCTET_STREAM})
    public final javax.ws.rs.core.Response GET_PATIENT_AUDIO_FILE(@PathParam("visit_sk") long visitSk) {
        return null;
    }
}
