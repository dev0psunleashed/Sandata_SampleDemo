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

package com.sandata.lab.security.auth;

import com.sandata.lab.data.model.dl.model.ApplicationUserKeyConfiguration;
import com.sandata.lab.data.model.dl.model.ApplicationUserSetting;
import com.sandata.lab.data.model.dl.model.extended.ApplicationRoleExt;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.security.auth.model.Email;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class RestfulServices {

    @POST
    @Path("/user")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response CREATE_USER(ApplicationUserExt applicationUserExt) {
        return null;
    }

    @PUT
    @Path("/user")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response UPDATE_USER(ApplicationUserExt applicationUserExt) {
        return null;
    }

    @GET
    @Path("/user/locked/{username}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response IS_USER_LOCKED(@PathParam("username") String userName) {
        return null;
    }

    @GET
    @Path("/user/{username}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER(@PathParam("username") String userName,
                                   @MatrixParam("bsn_ent_id") String businessEntityID
    ) {
        return null;
    }

    @GET
    @Path("/user/exists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response USER_EXISTS_FOR_BSN_ENT_ID(@MatrixParam("username") String userName,
                                                     @MatrixParam("bsn_ent_id") String businessEntityID
    ) {
        return null;
    }

    @GET
    @Path("/user/exists/{username}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response USER_EXISTS(@PathParam("username") String userName) {
        return null;
    }

    @DELETE
    @Path("/user/{user_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response DELETE_USER(@PathParam("user_sk") long userSk,
                                      @MatrixParam("bsn_ent_id") String businessEntityID) {
        return null;
    }

    @GET
    @Path("/user/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response FIND_USER(
        @MatrixParam("ln") String lastName,
        @MatrixParam("fn") String firstName,
        @MatrixParam("un") String userName,
        @MatrixParam("rn") String roleName,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @PUT
    @Path("/user/change_password/{hash}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response CHANGE_USER_PASSWORD(@PathParam("hash") String userPassHash) {
        return null;
    }

    @PUT
    @Path("/user/reset_password")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response RESET_USER_PASSWORD(String userName) {
        return null;
    }

    @GET
    @Path("/token/user_access/{hash}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER_TOKEN(@PathParam("hash") String userPassHash,
                                         @MatrixParam("bsn_ent_id") String businessEntityID) {
        return null;
    }

    @GET
    @Path("/token/access/{hash}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_ACCESS_TOKEN(@PathParam("hash") String userPassHash) {
        return null;
    }

    @GET
    @Path("/token/refresh/{guid}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_REFRESH_TOKEN(@PathParam("guid") String guid) {
        return null;
    }

    @GET
    @Path("/token/info/{guid}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_TOKEN_INFO(@PathParam("guid") String guid) {
        return null;
    }

    @POST
    @Path("/role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response INSERT_ROLE(ApplicationRoleExt applicationRoleExt) {
        return null;
    }

    @PUT
    @Path("/role")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response UPDATE_ROLE(ApplicationRoleExt applicationRoleExt) {
        return null;
    }

    @GET
    @Path("/role/{role_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_ROLE(
        @PathParam("role_sk") long roleSk
    ) {
        return null;
    }

    @DELETE
    @Path("/role/{role_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response DELETE_ROLE(
        @PathParam("role_sk") long roleSk
    ) {
        return null;
    }

    @GET
    @Path("/modules")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_MODULES() {
        return null;
    }

    @GET
    @Path("/roles/{bsn_ent_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_ROLES(
        @PathParam("bsn_ent_id") String businessEntityId
    ) {
        return null;
    }

    @POST
    @Path("/email/{user_name}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response EMAIL_USER(@PathParam("user_name") String userName, Email email) {
        return null;
    }

    @GET
    @Path("/secure_groups")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_SECURE_GROUPS() {
        return null;
    }

    @POST
    @Path("/user/setting")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response CREATE_USER_SETTING(ApplicationUserSetting applicationUserSetting) {
        return null;
    }

    @PUT
    @Path("/user/setting")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response UPDATE_USER_SETTING(ApplicationUserSetting applicationUserSetting) {
        return null;
    }

    @GET
    @Path("/user/setting/{user_guid}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER_SETTING_BY_USER_GUID(@PathParam("user_guid") String userGUID) {
        return null;
    }

    @GET
    @Path("/user/setting")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER_SETTING_BY_USER_GUID_AND_KEY(
    		@MatrixParam("user_guid") String userGUID,
    		@MatrixParam("key") String key) {
        return null;
    }

    @GET
    @Path("/user/appuserkeyconfig")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER_APP_CONFIGS_BY_USER_GUID_AND_KEY(
            @MatrixParam("user_guid") String userGUID,
            @MatrixParam("key") String key) {
        return null;
    }

    @DELETE
    @Path("/user/setting/{user_guid}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response DELETE_USER_SETTING_BY_USER_GUID(@PathParam("user_guid") String user_guid) {
        return null;
    }

    @POST
    @Path("/appuserkeyconfig")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response INSERT_USER_CONFIG(ApplicationUserKeyConfiguration data) {

        return null;
    }

    @PUT
    @Path("/appuserkeyconfig")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response UPDATE_USER_CONFIG(ApplicationUserKeyConfiguration data) {

        return null;
    }

    @DELETE
    @Path("/appuserkeyconfig/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response DELETE_USER_CONFIG(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }
/*
    @GET
    @Path("/appuserkeyconfig/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_USER_CONFIG(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }*/
}
