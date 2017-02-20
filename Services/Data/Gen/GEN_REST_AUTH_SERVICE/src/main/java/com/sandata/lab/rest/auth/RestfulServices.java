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

package com.sandata.lab.rest.auth;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region AuthorizationService
    @POST
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_insertAuthSvc_AuthorizationService(AuthorizationService data) {

        return null;
    }

    @PUT
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_updateAuthSvc_AuthorizationService(AuthorizationService data) {

        return null;
    }

    @DELETE
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_deleteAuthSvc_AuthorizationService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getAuthSvc_AuthorizationService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getAuthSvc_AuthorizationService(
        @MatrixParam("auth_sk") Long authSk) {

        return null;
    }
    //endregion

    //region Authorization

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_insertAuth_Authorization(Authorization data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_HIST_updateAuth_Authorization(Authorization data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_deleteAuth_Authorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getAuth_Authorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getActiveAuth_Authorization(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/historic")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getHistoricAuth_Authorization(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region Order

    @POST
    @Path("/order")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_insertOrd_Order(Authorization data) {

        return null;
    }

    @PUT
    @Path("/order")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_updateOrd_Order(Authorization data) {

        return null;
    }

    @DELETE
    @Path("/order/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_deleteOrd_Order(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/order/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getOrd_Order(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    //endregion

    //region OrderService

    @POST
    @Path("/order/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_insertOrdSvc_OrderService(AuthorizationService data) {

        return null;
    }

    @PUT
    @Path("/order/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_updateOrdSvc_OrderService(AuthorizationService data) {

        return null;
    }

    @DELETE
    @Path("/order/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_deleteOrdSvc_OrderService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/order/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getOrdSvc_OrderService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/order/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getOrdSvc_OrderService(
        @MatrixParam("ord_sk") Long authSk) {

        return null;
    }

    //endregion

}
