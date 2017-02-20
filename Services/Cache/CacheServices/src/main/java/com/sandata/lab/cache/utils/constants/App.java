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

package com.sandata.lab.cache.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class App {

    public enum ID {

        CACHE_ROUTE_ENDPOINT ("CACHE_ROUTE_SERVICES_ID");

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
