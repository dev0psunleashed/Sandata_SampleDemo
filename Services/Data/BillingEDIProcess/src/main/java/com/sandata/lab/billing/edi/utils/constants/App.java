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

package com.sandata.lab.billing.edi.utils.constants;

public class App {

    public enum Id {

        CONVERT_CLAIM_IN_JSON_TO_ANSI_X12 ("CONVERT_CLAIM_IN_JSON_TO_ANSI_X12");

        private final String id;

        private Id(final String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }

    private App() {
        // noop
    }

    /**
     * The keyword is used to easily differentiate the application and its
     * routes with others in FUSE as well as in Splunk log.
     * This keyword should be unique for more efficiently and exactly.
     * This keyword used to search logs in overall for Billing EDI process in the splunk : [BillingEDI]
     **/
    public static final String APPLICATION_KEYWORD = "BillingEDI";
}
