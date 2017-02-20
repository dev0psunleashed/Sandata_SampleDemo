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

package com.sandata.lab.rest.staff.utils.constants;


public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("SANDATA-REST-STAFF-DATA-SERVICE"),
        SANDATA_GET_STAFF_BY_TIN("SANDATA-GET-STAFF-BY-TIN"),
        SANDATA_GET_STAFF_BY__BUSINESS_ENTITY_ID("SANDATA-GET-STAFF-BY-BUSINESS-ENTITY-ID");;

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
