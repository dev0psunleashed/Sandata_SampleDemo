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

package com.sandata.lab.rest.payment;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmt_Payment(Payment data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmt_Payment(Payment data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmt_Payment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt_Payment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @POST
    @Path("/applied")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmtApplied_PaymentApplied(PaymentApplied data) {

        return null;
    }

    @PUT
    @Path("/applied")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmtApplied_PaymentApplied(PaymentApplied data) {

        return null;
    }

    @DELETE
    @Path("/applied/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmtApplied_PaymentApplied(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/applied/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmtApplied_PaymentApplied(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/applied")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmtApplied_PaymentApplied(
            @MatrixParam("payment_sk") String paymentSk) {

        return null;
    }

    @POST
    @Path("/terms")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmtTerms_PaymentTerms(PaymentTerms data) {

        return null;
    }

    @PUT
    @Path("/terms")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmtTerms_PaymentTerms(PaymentTerms data) {

        return null;
    }

    @DELETE
    @Path("/terms/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmtTerms_PaymentTerms(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/terms/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmtTerms_PaymentTerms(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/terms")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmtTerms_PaymentTerms(
            @MatrixParam("payment_terms_id") String paymentTermsId) {

        return null;
    }

    @POST
    @Path("/835/rec_detl")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmt835RecDet_Payment835RecordDetail(Payment835RecordDetail data) {

        return null;
    }

    @PUT
    @Path("/835/rec_detl")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmt835RecDet_Payment835RecordDetail(Payment835RecordDetail data) {

        return null;
    }

    @DELETE
    @Path("/835/rec_detl/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmt835RecDet_Payment835RecordDetail(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/rec_detl/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835RecDet_Payment835RecordDetail(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/rec_detl")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835RecDet_Payment835RecordDetail(
            @MatrixParam("rec_detl_id") String recDetlId) {

        return null;
    }

    @POST
    @Path("/835/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmt835Hist_Payment835History(Payment835History data) {

        return null;
    }

    @PUT
    @Path("/835/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmt835Hist_Payment835History(Payment835History data) {

        return null;
    }

    @DELETE
    @Path("/835/history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmt835Hist_Payment835History(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835Hist_Payment835History(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835Hist_Payment835History(
            @MatrixParam("history_id") String historySk) {

        return null;
    }

    @POST
    @Path("/835/batch")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_insertPmt835Batch_Payment835Batch(Payment835Batch data) {

        return null;
    }

    @PUT
    @Path("/835/batch")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_updatePmt835Batch_Payment835Batch(Payment835Batch data) {

        return null;
    }

    @DELETE
    @Path("/835/batch/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_deletePmt835Batch_Payment835Batch(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/batch/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835Batch_Payment835Batch(
            @PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/835/batch")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYMENT_getPmt835Batch_Payment835Batch(
            @MatrixParam("batch_id") String batchSk) {

        return null;
    }
}
