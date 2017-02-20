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

package com.sandata.lab.wcf.lookup.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("WCF_LOOKUP_RESTFUL_ROUTE_SERVICES_ID"),

        REST_LOOKUP_RELIGIONS ("REST_LOOKUP_RELIGIONS"),
        REST_LOOKUP_STAFF_TYPES ("REST_LOOKUP_STAFF_TYPES"),
        REST_LOOKUP_ETHNICITIES ("REST_LOOKUP_ETHNICITIES"),
        REST_LOOKUP_MARITAL_STATUSES ("REST_LOOKUP_MARITAL_STATUSES"),
        REST_LOOKUP_GENDERS ("REST_LOOKUP_GENDERS"),
        REST_LOOKUP_MED_CLASSIFICATIONS ("REST_LOOKUP_MED_CLASSIFICATIONS"),
        REST_LOOKUP_MED_STRENGTHS ("REST_LOOKUP_MED_STRENGTHS"),
        REST_LOOKUP_MED_ROUTES ("REST_LOOKUP_MED_ROUTES"),
        REST_LOOKUP_STATES ("REST_LOOKUP_STATES"),
        REST_LOOKUP_LANGUAGES ("REST_LOOKUP_LANGUAGES"),

        // Renamed from Services to Service_Codes because the resource url "/services" for cxf was causing an issue
        REST_LOOKUP_SERVICE_CODES ("REST_LOOKUP_SERVICE_CODES"),

        REST_LOOKUP_DISASTER_LEVELS ("REST_LOOKUP_DISASTER_LEVELS"),
        REST_LOOKUP_DNRS ("REST_LOOKUP_DNRS"),
        REST_LOOKUP_REF_FORMATS ("REST_LOOKUP_REF_FORMATS"),
        REST_LOOKUP_LIMIT_BYS ("REST_LOOKUP_LIMIT_BYS"),
        REST_LOOKUP_ELIGIBILITIES ("REST_LOOKUP_ELIGIBILITIES"),
        REST_LOOKUP_AGENCIES ("REST_LOOKUP_AGENCIES"),
        REST_LOOKUP_ADMIN_TYPES ("REST_LOOKUP_ADMIN_TYPES"),
        REST_LOOKUP_PAY_TYPES ("REST_LOOKUP_PAY_TYPES"),
        REST_LOOKUP_STAFF_CLASSES ("REST_LOOKUP_STAFF_CLASSES"),
        REST_LOOKUP_STAFF_STATUSES ("REST_LOOKUP_STAFF_STATUSES"),
        REST_LOOKUP_STATE_TAXES ("REST_LOOKUP_STATE_TAXES"),
        REST_LOOKUP_CITY_TAXES ("REST_LOOKUP_CITY_TAXES");

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
