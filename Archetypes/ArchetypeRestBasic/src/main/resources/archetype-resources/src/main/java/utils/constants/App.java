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

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class App {

    public enum ID {

        // TODO: Delete/Rename ...
        REST_ROUTE_ENDPOINT ("RENAME_ME_RESTFUL_ROUTE_SERVICES_ID"),

        // TODO: Delete/Rename ...
        EXAMPLE_METHOD ("EXAMPLE_METHOD");

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
