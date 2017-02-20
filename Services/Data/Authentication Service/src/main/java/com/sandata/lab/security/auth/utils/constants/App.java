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

package com.sandata.lab.security.auth.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class App {

    public enum ID {

        CXF_ROUTE_RECIPIENT_LIST("CXF_ROUTE_RECIPIENT_LIST"),
        GET_ACCESS_TOKEN("GET_ACCESS_TOKEN"),
        GET_REFRESH_TOKEN("GET_REFRESH_TOKEN"),
        GET_TOKEN_INFO("GET_TOKEN_INFO"),
        GET_USER_TOKEN("GET_USER_TOKEN"),
        RESET_USER_PASSWORD("RESET_USER_PASSWORD"),
        CREATE_USER("CREATE_USER"),
        GET_USER("GET_USER"),
        UPDATE_USER("UPDATE_USER"),
        DELETE_USER("DELETE_USER"),
        FIND_USER("FIND_USER"),
        USER_EXISTS("USER_EXISTS"),
        USER_EXISTS_FOR_BSN_ENT_ID("USER_EXISTS_FOR_BSN_ENT_ID"),
        CHANGE_USER_PASSWORD("CHANGE_USER_PASSWORD"),
        INSERT_ROLE("INSERT_ROLE"),
        GET_ROLE("GET_ROLE"),
        UPDATE_ROLE("UPDATE_ROLE"),
        UPDATE_ROLE_USERS("UPDATE_ROLE_USERS"),
        DELETE_ROLE("DELETE_ROLE"),
        GET_MODULES("GET_MODULES"),
        GET_SECURE_GROUPS("GET_SECURE_GROUPS"),
        GET_ROLES("GET_ROLES"),
        EMAIL_USER("EMAIL_USER"),
        IS_USER_LOCKED("IS_USER_LOCKED"),
        CREATE_USER_SETTING("CREATE_USER_SETTING"),
        UPDATE_USER_SETTING("UPDATE_USER_SETTING"),
        GET_USER_SETTING_BY_USER_GUID("GET_USER_SETTING_BY_USER_GUID"),
        GET_USER_SETTING_BY_USER_GUID_AND_KEY("GET_USER_SETTING_BY_USER_GUID_AND_KEY"),
        GET_USER_APP_CONFIGS_BY_USER_GUID_AND_KEY("GET_USER_APP_CONFIGS_BY_USER_GUID_AND_KEY"),
        DELETE_USER_SETTING_BY_USER_GUID("DELETE_USER_SETTING_BY_USER_GUID"),
        UPDATE_USER_CONFIG("UPDATE_USER_CONFIG"),
        DELETE_USER_CONFIG("DELETE_USER_CONFIG"),
        INSERT_USER_CONFIG("INSERT_USER_CONFIG");

        private final String id;

        private ID(final String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }
}
