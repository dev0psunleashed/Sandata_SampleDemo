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

package com.sandata.lab.imports.calls.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class App {

    public enum ID {

        CALLS_IMPORT_ROUTE ("CALLS_IMPORT_ROUTE"),
        PROCESSED_CALLS_IMPORT_ROUTE("PROCESSED_CALLS_IMPORT_ROUTE"),
        PROCESS_CALLS ("PROCESS_CALLS"),
        PROCESSED_CALLS ("PROCESSED_CALLS");

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
