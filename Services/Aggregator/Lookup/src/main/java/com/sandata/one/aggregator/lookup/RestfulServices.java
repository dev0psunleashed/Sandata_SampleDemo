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

package com.sandata.one.aggregator.lookup;


import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class RestfulServices {

    //region Role

    @GET
    @Path("/role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getRoles_ApplicationRole(
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("RoleName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Payer

    @GET
    @Path("/payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPayerLookup_Payer(
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Contract

    @GET
    @Path("/contract")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getContractLookup_Contract(
            @MatrixParam("payer_id") String payerId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("name") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Contract Service

    @GET
    @Path("/contract/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getContractService_ContractServiceList(
        @MatrixParam("payer_id") String payerId,
        @MatrixParam("contract_id") String contractId) {

        return null;
    }

    //endregion

    //region Service

    @GET
    @Path("/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getSvcLkup_ServiceLookup() {
        return null;
    }

    //endregion

    //region Exception

    @GET
    @Path("/exception")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getBeExcpLst_BusinessEntityExceptionListExt(
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("BusinessEntityID") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Coordinator

    @GET
    @Path("/coordinator")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getCoordinator_CoordinatorLookup(
            @MatrixParam("coordinator_name") String coordinatorName,
            @MatrixParam("coordinator_id") String coordinatorId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("LastName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Patient

    @GET
    @Path("/patient")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getPatient_PatientLookup(
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("medicaid_id") String medicaidId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("LastName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Staff Lookup

    @GET
    @Path("/staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getStaff_StaffLookup(
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("staff_ssn") String staffSSN,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("LastName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Visit Status

    @GET
    @Path("/visit_status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getVisitStatusNameLookup_VisitStatusName_REF() {
        return null;
    }

    //endregion

    //region Call Type

    @GET
    @Path("/call_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getCallTypeNameLookup_CallTypeName_REF() {
        return null;
    }

    //endregion

    //region Agency

    @GET
    @Path("/agency")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getAgency_AgencyLookup(
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("BusinessEntityName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Roles

    @GET
    @Path("/user/permissions")//fetching all the existing permission
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getUserPermission_ApplicationSecureGroup() {
        return null;
    }

    @GET
    @Path("/user/permissions/{role_sk}")//fetching all the existing permission
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getUserPermissionByRole_ApplicationSecureGroup(
            @PathParam("role_sk") long roleSk) {
        return null;
    }

    //endregion
}
