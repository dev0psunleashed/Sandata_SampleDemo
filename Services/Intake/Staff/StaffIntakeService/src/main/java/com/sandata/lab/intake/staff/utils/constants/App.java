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

package com.sandata.lab.intake.staff.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("STAFF_INTAKE_RESTFUL_ROUTE"),

        INSERT_STAFF_MEMBER ("INSERT_STAFF_MEMBER"),
        INSERT_STAFF_MEMBERS ("INSERT_STAFF_MEMBERS"),
        UPDATE_STAFF_MEMBER ("UPDATE_STAFF_MEMBER"),
        UPDATE_STAFF_MEMBERS ("UPDATE_STAFF_MEMBERS"),
        DELETE_STAFF_MEMBER ("DELETE_STAFF_MEMBER"),
        DELETE_STAFF_MEMBERS ("DELETE_STAFF_MEMBERS"),

        STAFF_GUID ("STAFF_GUID");

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
