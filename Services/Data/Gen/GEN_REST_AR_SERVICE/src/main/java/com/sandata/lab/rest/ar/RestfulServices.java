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

package com.sandata.lab.rest.ar;


import com.sandata.lab.data.model.dl.model.AccountsReceivable;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatch;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.math.BigInteger;

public class RestfulServices {

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_insertAr_AccountsReceivable(AccountsReceivable data) {

        return null;
    }


    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_updateAr_AccountsReceivable(AccountsReceivable data) {

        return null;
    }

    @DELETE
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_delete(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getAr_AccountsReceivable(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_deleteAr_AccountsReceivable(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/post/auto/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findARDetail_Response(

            @DefaultValue("") @MatrixParam("provider_name") String providerName,
            @DefaultValue("") @MatrixParam("tax_id") String taxId,
            @DefaultValue("") @MatrixParam("payer_id") String payerId,
            @DefaultValue("") @MatrixParam("check_date") String checkDate,
            @DefaultValue("") @MatrixParam("era_rcvd_date") String eraReceivedDate,
            @DefaultValue("") @MatrixParam("check_rcvd_date") String checkReceivedDate,
            @DefaultValue("") @MatrixParam("check_num") String checkNumber,
            @MatrixParam("check_amount") BigDecimal checkAmount,
            @MatrixParam("is_plb") boolean isPLB,
            @DefaultValue("all") @MatrixParam("status") String status,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("bsn_ent_id") String businessEntityId) {
        return null;
    }

    @GET
    @Path("/post/auto/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getARDetails_Response(
            @PathParam("sequence_key") long sk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/post/auto/summary/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getARSummary_Response(
            @PathParam("sequence_key") long sk) {
        return null;
    }

    // region Batch

    @GET
    @Path("/transaction/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findArTxnBatch_ArTxnBatchManualPostFindResult(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                               @MatrixParam("batch_number") String batchNumber,
                                                                               @MatrixParam("payer_id") String payerId,
                                                                               @MatrixParam("check_number") String checkNumber,
                                                                               @MatrixParam("check_deposit_date") String checkDepositDate,
                                                                               @MatrixParam("total_amount") BigDecimal totalAmount,
                                                                               @MatrixParam("payment_posted_unapplied") BigDecimal paymentPostedUnapplied,
                                                                               @MatrixParam("total_denied") BigDecimal totalDenied,
                                                                               @MatrixParam("total_paid") BigDecimal totalPaid,
                                                                               @MatrixParam("total_open") BigDecimal totalOpen,
                                                                               @MatrixParam("batch_posted") Boolean isBatchPosted,
                                                                               @DefaultValue("1") @MatrixParam("page") int page,
                                                                               @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                                               @DefaultValue("batch_id") @MatrixParam("sort_on") String sortOn,
                                                                               @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/find/batch/number")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findArByBatchNumber_Response(
                                @MatrixParam("bsn_ent_id") String bsnEntId,
                                @MatrixParam("batch_number") String batchNumber,
                                @MatrixParam("invoice_number") String invoiceNumber,
                                @MatrixParam("batch_det_sk") long batchDetailSK,
                                @MatrixParam("unapplied_ind") boolean unapplied,
                                @DefaultValue("1") @MatrixParam("page") int page,
                                @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                @DefaultValue("pln") @MatrixParam("sort_on") String sortOn,
                                @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/invoice/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_INV_findInvoiceByInvoiceNumber_InvoiceExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("invoice_number") String batchNumber,
            @MatrixParam("payer_id") String payerId) {
        return null;
    }

    @POST
    @Path("/transaction")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_insertArTxnBatch_AccountsReceivableTransactionBatch(AccountsReceivableTransactionBatch data) {

        return null;
    }

    @PUT
    @Path("/transaction")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_updateArTxnBatch_AccountsReceivableTransactionBatch(AccountsReceivableTransactionBatch data) {

        return null;
    }

    @GET
    @Path("/transaction/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getArTxnBatch_AccountsReceivableTransactionBatch(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/transaction/detail/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getArTxnBatchDetail_AccountsReceivableTransactionBatchDetail(@PathParam("sequence_key") BigInteger sequenceKey) {

        return null;
    }

    @GET
    @Path("/transactions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getArTxnBatchByInvoiceNumber_AccountsReceivableTransactionsBatch(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("invoice_number") String invoiceNumber,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("50") @MatrixParam("page_size") int pageSize,
            @DefaultValue("transaction_sk") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @DELETE
    @Path("/transaction/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_deleteArTxnBatch_AccountsReceivableTransactionBatch(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findAR_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                 @MatrixParam("payer_id") String payerId,
                                                 @MatrixParam("contract_id") String contractId,
                                                 //date between dbo.inv_date
                                                 @MatrixParam("inv_date_from") String invDateFrom,
                                                 @MatrixParam("inv_date_to") String invDateTo,
                                                 @MatrixParam("inv_num") String invoiceNum,
                                                 @MatrixParam("inv_start_date") String invStartDate,
                                                 @MatrixParam("inv_end_date") String invEndDate,
                                                 @MatrixParam("pt_first_name") String ptFirstName,
                                                 @MatrixParam("pt_last_name") String ptLastName,
                                                 @MatrixParam("be_loc_id") String beLocId,
                                                 @MatrixParam("be_lob_id") String beLobId,
                                                 //inv_totla_amt
                                                 @MatrixParam("billed") BigDecimal billed,
                                                 //AR_TXN_BATCH.PMT_AMT
                                                 @MatrixParam("paid") BigDecimal paid,
                                                 @MatrixParam("open_balance") boolean openBalance,
                                                 @MatrixParam("inv_status_code") String invStatusCode,
                                                 @MatrixParam("ar_txn_code") String arTxnCode,
                                                 @DefaultValue("1") @MatrixParam("page") int page,
                                                 @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                 //default sorting by patient last name
                                                 @DefaultValue("ptln") @MatrixParam("sort_on") String sortOn,
                                                 @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/service/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getARServiceDetails_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                              @MatrixParam("payer_id") String payerId,
                                                              @MatrixParam("contract_id") String contractId,
                                                              @MatrixParam("inv_num") String invoiceNum,
                                                              @DefaultValue("1") @MatrixParam("page") int page,
                                                              @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                              @DefaultValue("dos") @MatrixParam("sort_on") String sortOn,
                                                              @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    // endregion

    @GET
    @Path("/transaction/batch/open")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getArTxnOpenBatch_AccountsReceivableTransactionBatch(
            @MatrixParam("username") String userName,
            @MatrixParam("user_guid") String userGuid,
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/transaction/batch/payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findBatchByPayer_Response(@MatrixParam("payer_id") String payerId,
                                                           @MatrixParam("bsn_ent_id") String bsnEntId,
                                                           @MatrixParam("batch_number") String batchNumber) {
        return null;
    }
    
    @GET
    @Path("/transaction/open_check")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getOpenCheck_Response(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("batch_number") String batchNumber,
            @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @POST
    @Path("/transaction_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_insertArTxnBatchDet_AccountsReceivableTransactionBatchDetail(AccountsReceivableTransactionBatchDetail data) {

        return null;
    }

    @PUT
    @Path("/transaction_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_updateArTxnBatchDet_AccountsReceivableTransactionBatchDetail(AccountsReceivableTransactionBatchDetail data) {

        return null;
    }

    @GET
    @Path("/transaction_detail/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_getArTxnBatchDet_AccountsReceivableTransactionBatchDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @DELETE
    @Path("/transaction_detail/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_deleteArTxnBatchDet_AccountsReceivableTransactionBatchDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/unapplied/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findUnappliedTransactions_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                 @MatrixParam("payer_id") String payerId,
                                                 @MatrixParam("balance_from") BigDecimal unappliedBalanceFrom,
                                                 @MatrixParam("balance_to") BigDecimal unappliedBalanceTo,
                                                 @MatrixParam("transaction_from_date") String transactionFromDate,
                                                 @MatrixParam("transaction_to_date") String transactionToDate,
                                                 @MatrixParam("transaction_code") String transactionCode,

                                                 @MatrixParam("transaction_type") String transactionType,
                                                 //AR_TXN_BATCH.PMT_AMT
                                                 @MatrixParam("user_name") String userName,
                                                 @MatrixParam("payment_number") String paymentNumber,
                                                 @MatrixParam("check_date") String checkDate,
                                                 @MatrixParam("batch_number") String batchNumber,
                                                 @DefaultValue("1") @MatrixParam("page") int page,
                                                 @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                 //default sorting by patient last name
                                                 @DefaultValue("transaction_date") @MatrixParam("sort_on") String sortOn,
                                                 @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/unapplied/users")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_findUnappliedUsers_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                             @DefaultValue("1") @MatrixParam("page") int page,
                                                             @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                             //default sorting by patient last name
                                                             @DefaultValue("user_name") @MatrixParam("sort_on") String sortOn,
                                                             @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }


    @GET
    @Path("/payment/exists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AR_CheckExists_Response(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                      @MatrixParam("payment_number") String paymentNumber,
                                                      @MatrixParam("payer_id") String payerId,
                                                      @MatrixParam("payment_amount") String paymentAmount) {
        return null;
    }
}
