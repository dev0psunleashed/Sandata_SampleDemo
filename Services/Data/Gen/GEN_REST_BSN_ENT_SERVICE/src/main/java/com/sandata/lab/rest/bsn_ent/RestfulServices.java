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

package com.sandata.lab.rest.bsn_ent;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.BusinessEntityRateExchange;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    // region BusinessEntityStaffRole

    @POST
    @Path("/staff_role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeStaffRole_BusinessEntityStaffRole(BusinessEntityStaffRole data) {

        return null;
    }

    @PUT
    @Path("/staff_role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeStaffRole_BusinessEntityStaffRole(BusinessEntityStaffRole data) {

        return null;
    }

    @DELETE
    @Path("/staff_role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeStaffRole_BusinessEntityStaffRole(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/staff_role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeStaffRole_BusinessEntityStaffRole(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/staff_role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeStaffRole_BusinessEntityStaffRole(
            @MatrixParam("staff_id") String staffID,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityRole

    @POST
    @Path("/role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeRole_BusinessEntityRole(BusinessEntityRole data) {

        return null;
    }

    @PUT
    @Path("/role")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeRole_BusinessEntityRole(BusinessEntityRole data) {

        return null;
    }

    @DELETE
    @Path("/role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeRole_BusinessEntityRole(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/role/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRole_BusinessEntityRole(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRole_BusinessEntityRole(
        @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityRelationship

    @POST
    @Path("/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeRel_BusinessEntityRelationship(BusinessEntityRelationship data) {

        return null;
    }

    @PUT
    @Path("/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeRel_BusinessEntityRelationship(BusinessEntityRelationship data) {

        return null;
    }

    @DELETE
    @Path("/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeRel_BusinessEntityRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRel_BusinessEntityRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/relationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRel_BusinessEntityRelationshipForPK(
        @MatrixParam("bsn_ent_id") String bsnEntID,
        @MatrixParam("bsn_lvl_sk") Long bsnLvlSk) {

        return null;
    }

    // endregion

    // region BusinessEntityPayer

    @POST
    @Path("/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBePayer_BusinessEntityPayer(BusinessEntityPayer data) {

        return null;
    }

    @PUT
    @Path("/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBePayer_BusinessEntityPayer(BusinessEntityPayer data) {

        return null;
    }

    @DELETE
    @Path("/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBePayer_BusinessEntityPayer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBePayer_BusinessEntityPayer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBePayer_BusinessEntityPayer(
            @MatrixParam("payer_sk") String payerSk,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityElectronicFundsTransfer
    @POST
    @Path("/eft")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeEft_BusinessEntityElectronicFundsTransfer(BusinessEntityElectronicFundsTransfer data) {

        return null;
    }

    @PUT
    @Path("/eft")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeEft_BusinessEntityElectronicFundsTransfer(BusinessEntityElectronicFundsTransfer data) {

        return null;
    }

    @DELETE
    @Path("/eft/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeEft_BusinessEntityElectronicFundsTransfer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/eft/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeEft_BusinessEntityElectronicFundsTransfer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/eft")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeEft_BusinessEntityElectronicFundsTransfer(
        @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityCredential

    @POST
    @Path("/credentials")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeCredential_BusinessEntityCredential(BusinessEntityCredential data) {

        return null;
    }

    @PUT
    @Path("/credentials")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeCredential_BusinessEntityCredential(BusinessEntityCredential data) {

        return null;
    }

    @DELETE
    @Path("/credentials/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeCredential_BusinessEntityCredential(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/credentials/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCredential_BusinessEntityCredential(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/credentials")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCredential_BusinessEntityCredential(
        @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityContactDetail

    @POST
    @Path("/contact_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeContDet_BusinessEntityContactDetail(BusinessEntityContactDetail data) {

        return null;
    }

    @PUT
    @Path("/contact_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeContDet_BusinessEntityContactDetail(BusinessEntityContactDetail data) {

        return null;
    }

    @DELETE
    @Path("/contact_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeContDet_BusinessEntityContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeContDet_BusinessEntityContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeContDet_BusinessEntityContactDetail(
        @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntity

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBe_BusinessEntity(BusinessEntity data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBe_BusinessEntity(BusinessEntity data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBe_BusinessEntity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBe_BusinessEntity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBe_BusinessEntity(@MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityLocation

    @GET
    @Path("/location")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeLocation_BusinessEntityLocation(
        @MatrixParam("bsn_ent_parent_id") String bsnEntParentID,
        @MatrixParam("location_name") String locationName) {

        return null;
    }

    // endregion

    // region BusinessEntityIDCrosswalk

    @POST
    @Path("/id_crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeIdXwalk_BusinessEntityIDCrosswalk(BusinessEntityIDCrosswalk data) {

        return null;
    }

    @PUT
    @Path("/id_crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeIdXwalk_BusinessEntityIDCrosswalk(BusinessEntityIDCrosswalk data) {

        return null;
    }

    @GET
    @Path("/id_crosswalk")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeIdXwalk_BusinessEntityIDCrosswalk(
        @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    @GET
    @Path("/id_crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeIdXwalk_BusinessEntityIDCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @DELETE
    @Path("/id_crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeIdXwalk_BusinessEntityIDCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    // endregion

    // region BusinessEntityInterface

    @POST
    @Path("/interface")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeIntf_BusinessEntityInterface(BusinessEntityInterface data) {

        return null;
    }

    @PUT
    @Path("/interface")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeIntf_BusinessEntityInterface(BusinessEntityInterface data) {

        return null;
    }

    @DELETE
    @Path("/interface/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeIntf_BusinessEntityInterface(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/interface/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeIntf_BusinessEntityInterface(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/interface")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeIntf_BusinessEntityInterface(
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityRateExchange

    @POST
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeRateExchange_BusinessEntityRateExchange(BusinessEntityRateExchange data) {

        return null;
    }

    @PUT
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeRateExchange_BusinessEntityRateExchange(BusinessEntityRateExchange data) {

        return null;
    }

    @DELETE
    @Path("/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeRateExchange_BusinessEntityRateExchange(
            @MatrixParam("bsn_ent_id") String bsnEntID,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("rate_type") String rateType) {

        return null;
    }

    @GET
    @Path("/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRateExchange_BusinessEntityRateExchange(
            @MatrixParam("bsn_ent_id") String bsnEntID,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("rate_type") String rateType) {

        return null;
    }

    @GET
    @Path("/rate/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRateForBsnEntID_BusinessEntityRate(
            @PathParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityRateTypePayrollCodeCrosswalk

    @POST
    @Path("/rate/type/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk(BusinessEntityRateTypePayrollCodeCrosswalk data) {

        return null;
    }

    @PUT
    @Path("/rate/type/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk(BusinessEntityRateTypePayrollCodeCrosswalk data) {

        return null;
    }

    @GET
    @Path("/rate/type/crosswalk")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk(
            @MatrixParam("rate_type") String rateType,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    @GET
    @Path("/rate/type/crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @DELETE
    @Path("/rate/type/crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    // endregion

    // region BusinessEntityComplianceRelationship

    @POST
    @Path("/compliance/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeCompRel_BusinessEntityComplianceRelationship(BusinessEntityComplianceRelationship data) {

        return null;
    }

    @PUT
    @Path("/compliance/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeCompRel_BusinessEntityComplianceRelationship(BusinessEntityComplianceRelationship data) {

        return null;
    }

    @DELETE
    @Path("/compliance/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeCompRel_BusinessEntityComplianceRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCompRel_BusinessEntityComplianceRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance/relationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCompRel_BusinessEntityComplianceRelationship(
            @MatrixParam("compliance_code") String complianceCode,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion

    // region BusinessEntityComplianceRelationshipDetail

    @POST
    @Path("/compliance/relationship/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_insertBeCompRelDet_BusinessEntityComplianceRelationshipDetail(BusinessEntityComplianceRelationshipDetail data) {

        return null;
    }

    @PUT
    @Path("/compliance/relationship/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_updateBeCompRelDet_BusinessEntityComplianceRelationshipDetail(BusinessEntityComplianceRelationshipDetail data) {

        return null;
    }

    @DELETE
    @Path("/compliance/relationship/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_deleteBeCompRelDet_BusinessEntityComplianceRelationshipDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance/relationship/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCompRelDet_BusinessEntityComplianceRelationshipDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance/relationship/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BSN_getBeCompRelDetPK_BusinessEntityComplianceRelationshipDetail(
            @MatrixParam("comp_rel_sk") Long complianceRelationshipSK,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }

    // endregion
}
