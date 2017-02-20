package com.sandata.lab.rest.am;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Expose the REST endpoints for the oracle PKG_ADMIN methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class AdminRestfulServices extends ReportRestfulServices {

    //region AdministrativeStaff

    @POST
    @Path("/admin/staff")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaff_AdministrativeStaff(AdministrativeStaff data) {

        return null;
    }

    @PUT
    @Path("/admin/staff")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaff_AdministrativeStaff(AdministrativeStaff data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaff_AdministrativeStaff(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaff_AdministrativeStaff(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffPK_AdministrativeStaff(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region AdministrativeStaffPatient

    @POST
    @Path("/admin/staff/patient")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaffPt_AdministrativeStaffPatient(AdministrativeStaffPatient data) {

        return null;
    }

    @PUT
    @Path("/admin/staff/patient")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaffPt_AdministrativeStaffPatient(AdministrativeStaffPatient data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/patient/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaffPt_AdministrativeStaffPatient(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/patient/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffPt_AdministrativeStaffPatient(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/patient")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffPtPK_AdministrativeStaffPatient(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("id") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region AdministrativeStaffRelationship

    @POST
    @Path("/admin/staff/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaffRel_AdministrativeStaffRelationship(AdministrativeStaffRelationship data) {

        return null;
    }

    @PUT
    @Path("/admin/staff/relationship")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaffRel_AdministrativeStaffRelationship(AdministrativeStaffRelationship data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaffRel_AdministrativeStaffRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/relationship/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffRel_AdministrativeStaffRelationship(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/relationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffRel_AdministrativeStaffRelationship(
            @MatrixParam("admin_staff_id") String adminStaffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //region AdministrativeStaffStaffCrosswalk

    @POST
    @Path("/admin/staff/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaffStaffXwalk_AdministrativeStaffStaffCrosswalk(AdministrativeStaffStaffCrosswalk data) {

        return null;
    }

    @PUT
    @Path("/admin/staff/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaffStaffXwalk_AdministrativeStaffStaffCrosswalk(AdministrativeStaffStaffCrosswalk data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaffStaffXwalk_AdministrativeStaffStaffCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffStaffXwalk_AdministrativeStaffStaffCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/crosswalk")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffStaffXwalkPK_AdministrativeStaffStaffCrosswalk(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("id") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region AdministrativeStaffStaffCrossReference

    @POST
    @Path("/admin/staff/crossref")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaffStaffXref_AdministrativeStaffStaffCrossReference(AdministrativeStaffStaffCrossReference data) {

        return null;
    }

    @PUT
    @Path("/admin/staff/crossref")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaffStaffXref_AdministrativeStaffStaffCrossReference(AdministrativeStaffStaffCrossReference data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/crossref/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaffStaffXref_AdministrativeStaffStaffCrossReference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/crossref/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffStaffXref_AdministrativeStaffStaffCrossReference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/crossref")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffStaffXrefPK_AdministrativeStaffStaffCrossReference(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("id") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region AdministrativeStaffRoleCrossReference

    @POST
    @Path("/admin/staff/role/crossref")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_insertAdminStaffRoleXref_AdministrativeStaffRoleCrossReference(AdministrativeStaffRoleCrossReference data) {

        return null;
    }

    @PUT
    @Path("/admin/staff/role/crossref")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_updateAdminStaffRoleXref_AdministrativeStaffRoleCrossReference(AdministrativeStaffRoleCrossReference data) {

        return null;
    }

    @DELETE
    @Path("/admin/staff/role/crossref/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_deleteAdminStaffRoleXref_AdministrativeStaffRoleCrossReference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/role/crossref/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffRoleXref_AdministrativeStaffRoleCrossReference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin/staff/role/crossref")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffRoleXref_AdministrativeStaffRoleCrossReference(
            @MatrixParam("admin_staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion
}
