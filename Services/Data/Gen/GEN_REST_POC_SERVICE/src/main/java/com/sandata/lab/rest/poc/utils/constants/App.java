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

package com.sandata.lab.rest.poc.utils.constants;


public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("SANDATA-REST-POC-DATA-SERVICE");

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
