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

package com.sandata.lab.dl.vv.test.utils.constants;


public class App {

    public enum ID {

        TEST_SCHEDULE_EVENT_ROUTE ("TEST_SCHEDULE_EVENT_ROUTE"),
        TEST_CREATE_VISIT_REQUEST_ROUTE ("TEST_CREATE_VISIT_REQUEST_ROUTE"),
        TEST_CREATE_VISIT_RESPONSE_ROUTE ("TEST_CREATE_VISIT_RESPONSE_ROUTE"),
        TEST_PATIENT_STAFF_ROUTE ("TEST_PATIENT_STAFF_ROUTE");

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
