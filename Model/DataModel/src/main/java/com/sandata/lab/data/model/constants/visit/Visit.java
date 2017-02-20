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

package com.sandata.lab.data.model.constants.visit;


public class Visit {

    public enum EVENT {

        SCHEDULE_EVENT_REQUEST ("SCHEDULE_EVENT_REQUEST"),

        SCHEDULE_EVENT_RESPONSE ("SCHEDULE_EVENT_RESPONSE"),

        CREATE_VISIT_REQUEST ("CREATE_VISIT_REQUEST"),

        CREATE_VISIT_EVENT_REQUEST ("CREATE_VISIT_EVENT_REQUEST"),

        CREATE_VISIT_RESPONSE ("CREATE_VISIT_RESPONSE"),

        CREATE_VISIT_EVENT_RESPONSE ("CREATE_VISIT_EVENT_RESPONSE"),

        PATIENT_STAFF_REQUEST ("PATIENT_STAFF_REQUEST"),

        PATIENT_STAFF_RESPONSE ("PATIENT_STAFF_RESPONSE");

        private final String event;

        private EVENT(final String event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return this.event;
        }
    }
}
