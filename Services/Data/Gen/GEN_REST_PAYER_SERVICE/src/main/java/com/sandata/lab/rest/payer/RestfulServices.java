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

package com.sandata.lab.rest.payer;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices extends RestfulServicesAgencyMgmt {

    // region RateModifiers
    @POST
    @Path("/rate_modifiers")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_insertPayerRateMdfr_PayerRateModifier(PayerRateModifier data) {

        return null;
    }

    @PUT
    @Path("/rate_modifiers")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_updatePayerRateMdfr_PayerRateModifier(PayerRateModifier data) {

        return null;
    }

    @DELETE
    @Path("/rate_modifiers/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_deletePayerRateMdfr_PayerRateModifier(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate_modifiers/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayerRateMdfr_PayerRateModifier(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate_modifiers")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayerRateMdfr_PayerRateModifier(
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contrId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region Contract

    @GET
    @Path("/contract")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_CONTRACT_getContr_Contract(
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("ContractDescription") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    // region Payer

    @GET
    @Path("/business_entity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AM_getPayerHeadersByBsnEnt_PayerHdr(
                      @MatrixParam("bsn_ent_id") String bsnEntId,
                      @MatrixParam("payer_active_indicator") Boolean payerActiveIndicator,
                      @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
                      @DefaultValue("ASC") @MatrixParam("direction") String direction,
                      @DefaultValue("1") @MatrixParam("page") int page,
                      @DefaultValue("100") @MatrixParam("page_size") int pageSize) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayerForID_Payer(
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/patient")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayerByPatient_Payer( @MatrixParam("bsn_ent_id") String bsnEntId,
                                                             @MatrixParam("patient_id") String patientId) {

        return null;
    }

    @GET
    @Path("/bsn_ent/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayerByBsnEnt_Payer(
        @PathParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("100") @MatrixParam("page_size") int pageSize,
        @DefaultValue("INSURANCE") @MatrixParam("payer_type_qualifier") String payerTypeQualifier,
        @DefaultValue("true") @MatrixParam("payer_active_indicator") boolean payerActiveIndicator,
        @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction ) {
        return null;
    }

    // endregion

    // region PayerSettings

    @POST
    @Path("/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data,
                                                                                           @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                           @MatrixParam("payer_id") String payerId) {
        return null;
    }

    @PUT
    @Path("/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppTenantKeyConfPayer_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data) {
        return null;
    }

    @DELETE
    @Path("/settings/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppTenantKeyConfPayer_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/settings/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConfPayer_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConf_ApplicationTenantKeyConfiguration(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                        @MatrixParam("payer_id") String payerId,
                                                                                        @MatrixParam("key_name") String keyName) {
        return null;
    }

    // endregion

    // region ContractSettings
    @POST
    @Path("/contract/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data,
                                                                                           @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                           @MatrixParam("payer_id") String payerId,
                                                                                           @MatrixParam("contract_id") String contractId) {
        return null;
    }

    @PUT
    @Path("/contract/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppTenantKeyConfContract_ApplicationTenantKeyConfiguration(ApplicationTenantKeyConfiguration data) {
        return null;
    }

    @DELETE
    @Path("/contract/settings/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppTenantKeyConfContract_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/contract/settings/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConfContract_ApplicationTenantKeyConfiguration(@PathParam("sequence_key") long sequenceKey) {
        return null;
    }

    @GET
    @Path("/contract/settings")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppTenantKeyConf_ApplicationTenantKeyConfiguration(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                        @MatrixParam("payer_id") String payerId,
                                                                                        @MatrixParam("contract_id") String contractId,
                                                                                        @MatrixParam("key_name") String keyName) {
        return null;
    }
    // endregion
}
