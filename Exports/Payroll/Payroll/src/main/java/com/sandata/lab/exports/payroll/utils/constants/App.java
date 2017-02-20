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

package com.sandata.lab.exports.payroll.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class App {

    public enum ID {

        AGENCY_PAYROLL_SCHEDULER_ROUTE("AGENCY-PAYROLL-SCHEDULER-ROUTE"),
        PAYPRO_PAYROLL_EXPORT_ROUTE("PAYPRO-PAYROLL-EXPORT-ROUTE"),
        PAYROLL_MANUAL_ROUTE("PAYROLL-MANUAL-ROUTE"),
        PAYROLL_LOCATION_ROUTE("PAYROLL-LOCATION-ROUTE"),
        PAYROLL_STAFF_EXPORT_MANAGER_ROUTE("PAYROLL-STAFF-EXPORT-MANAGER-ROUTE");

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
