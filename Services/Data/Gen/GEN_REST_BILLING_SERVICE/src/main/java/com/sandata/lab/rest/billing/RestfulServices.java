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

package com.sandata.lab.rest.billing;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class RestfulServices {

    //region InvoiceNote

    @POST
    @Path("/invoice/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_insertInvNote_InvoiceNote(InvoiceNote data) {

        return null;
    }

    @PUT
    @Path("/invoice/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_updateInvNote_InvoiceNote(InvoiceNote data) {

        return null;
    }

    @DELETE
    @Path("/invoice/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_deleteInvNote_InvoiceNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/invoice/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_getInvNote_InvoiceNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/invoice/note")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_getInvNote_InvoiceNote(
            @MatrixParam("invoice_number") String invoiceNumber) {

        return null;
    }

    //endregion

    //region InvoiceDetailNote

    @POST
    @Path("/invoice/detail/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_insertInvDetNote_InvoiceDetailNote(InvoiceDetailNote data) {

        return null;
    }

    @PUT
    @Path("/invoice/detail/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_updateInvDetNote_InvoiceDetailNote(InvoiceDetailNote data) {

        return null;
    }

    @DELETE
    @Path("/invoice/detail/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_deleteInvDetNote_InvoiceDetailNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/invoice/detail/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_getInvDetNote_InvoiceDetailNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/invoice/detail/note")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INVOICE_getInvDetNote_InvoiceDetailNote(
            @MatrixParam("invoice_number") String invoiceNumber) {

        return null;
    }

    //endregion

    //region BillingRate

    @POST
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBillingRate_BillingRate(BillingRate data) {

        return null;
    }

    @PUT
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBillingRate_BillingRate(BillingRate data) {

        return null;
    }

    @DELETE
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBillingRate_BillingRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRate_BillingRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRatePK_BillingRate(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //region Billing

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBilling_Billing(Billing data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBilling_Billing(Billing data) {

        return null;
    }

    @DELETE
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBilling_Billing(List<Long> sequenceKeys) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBilling_Billing(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBilling_Billing(
            @MatrixParam("patient_id") String patientID,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_findBilling_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                           @MatrixParam("invoice_find_type") String invoiceFindType,
                                                           @MatrixParam("payer_name") String payerName,
                                                           @MatrixParam("patient_id") String patientId,
                                                           @MatrixParam("contract") String contract,
                                                           @MatrixParam("from_date_time") String fromDate,
                                                           @MatrixParam("to_date_time") String toDate,
                                                           @MatrixParam("invoice_num") String invoiceNum,
                                                           @MatrixParam("patient_first_name") String patientFirstName,
                                                           @MatrixParam("patient_last_name") String patientLastName,
                                                           @MatrixParam("location") String location,
                                                           @MatrixParam("line_of_business") String lineOfBusiness,
                                                           @MatrixParam("submission_status") String submissionStatus,
                                                           @MatrixParam("status") String status,
                                                           @MatrixParam("edit_error_reason") String editErrorReason,
                                                           @DefaultValue("1") @MatrixParam("page") int page,
                                                           @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                           @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
                                                           @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @POST
    @Path("/preview/lock")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingPreviewLock_Response(List<Long> sequenceKeys) {
        return null;
    }

    @GET
    @Path("/preview/lock")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_submitBillingPreviewLock_Response(@MatrixParam("transaction_id") String transactionId) {
        return null;
    }

    @DELETE
    @Path("/preview/lock")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_cancelBillingPreviewLock_Response(@MatrixParam("transaction_id") String transactionId) {
        return null;
    }

    //endregion

    //region BillingHistory

    /*@POST
    @Path("/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBillingHist_BillingHistory(BillingHistory data) {

        return null;
    }

    @PUT
    @Path("/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBillingHist_BillingHistory(BillingHistory data) {

        return null;
    }

    @GET
    @Path("/history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingHist_BillingHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }*/

    //endregion

    //region BillingDetail

    @POST
    @Path("/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBillingDet_BillingDetail(BillingDetail data) {

        return null;
    }

    @PUT
    @Path("/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBillingDet_BillingDetail(BillingDetail data) {

        return null;
    }

    @DELETE
    @Path("/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBillingDet_BillingDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingDet_BillingDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingDetailForPK_BillingDetail(
            @MatrixParam("billing_sk") Long billingSk) {

        return null;
    }

    //endregion

    //region BillingDetailException

    @POST
    @Path("/detail/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_insertBillingDetExcp_BillingDetailException(BillingDetailException data) {

        return null;
    }

    @PUT
    @Path("/detail/exceptions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_updateBillingDetExcp_BillingDetailException(BillingDetailException data) {

        return null;
    }

    @DELETE
    @Path("/detail/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBillingDetExcp_BillingDetailException(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail/exceptions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingDetExcp_BillingDetailException(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail/exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingDetExcp_BillingDetailException(
            @MatrixParam("billing_detail_sk") Long billingDetailSK) {

        return null;
    }

    //endregion

    @GET
    @Path("/payer_contracts")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getPayerContracts_Response(
            @MatrixParam("bsn_ent_id") String businessEntityID) {
        return null;
    }
}
