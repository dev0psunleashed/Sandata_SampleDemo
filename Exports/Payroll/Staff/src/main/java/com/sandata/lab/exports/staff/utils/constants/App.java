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

package com.sandata.lab.exports.staff.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class App {

    public enum ID {

        STAFF_PAYPRO_EXPORT_ROUTE ("STAFF-PAYPRO-EXPORT-ROUTE"),
        STAFF_PAYPRO_SCHEDULER_ROUTE ("STAFF-PAYPRO-SCHEDULER-ROUTE"),
        STAFF_PAYPRO_SCHEDULE_ROUTE ("STAFF-PAYPRO-SCHEDULE-ROUTE");

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
