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

package com.sandata.one.aggregator.documents.utils.constants;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class App {

    public enum ID {

        REST_ROUTE_ENDPOINT ("SANDATA-DOCUMENT-DATA-SERVICE"),

        CXF_ROUTE_HANDLER("CXF_ROUTE_HANDLER"),
        CXF_ROUTE_RECIPIENT_LIST("CXF_ROUTE_RECIPIENT_LIST"),

        INSERT_DOCUMENT_FILE("INSERT_DOCUMENT_FILE"),
        GET_DOCUMENT_FILE("GET_DOCUMENT_FILE"),
        GET_PATIENT_SIG_FILE("GET_PATIENT_SIG_FILE"),
        GET_PATIENT_AUDIO_FILE("GET_PATIENT_AUDIO_FILE");

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
