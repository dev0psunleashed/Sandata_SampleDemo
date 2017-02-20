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

package com.sandata.lab.wcf.lookup;

import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServices {

    @GET
    @Path("/religions")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_RELIGIONS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/stafftypes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_STAFF_TYPES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/ethnicities")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_ETHNICITIES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/maritalstatuses")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_MARITAL_STATUSES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/genders")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_GENDERS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/medclassifications")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_MED_CLASSIFICATIONS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/medstrengths")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_MED_STRENGTHS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/medroutes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_MED_ROUTES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/states")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_STATES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/languages")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_LANGUAGES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/servicecodes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_SERVICE_CODES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/disasterlevels")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_DISASTER_LEVELS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/dnrs")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_DNRS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/referenceformats")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_REF_FORMATS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/limitbys")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_LIMIT_BYS(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/eligibilities")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_ELIGIBILITIES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/agencies")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_AGENCIES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/admintypes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_ADMIN_TYPES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/paytypes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_PAY_TYPES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/staffclasses")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_STAFF_CLASSES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/staffstatuses")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_STAFF_STATUSES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/statetaxes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_STATE_TAXES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }

    @GET
    @Path("/citytaxes")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_LOOKUP_CITY_TAXES(final @MatrixParam("update_cache") boolean updateCache) {
        return null;
    }
}
