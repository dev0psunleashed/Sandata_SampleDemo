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

package com.sandata.lab.eligibility.utils.constants;

public class App {

    private App() {
        // noop
    }

    /**
     * The keyword is used to easily differentiate the application and its
     * routes with others in FUSE as well as in Splunk log.
     * This keyword should be unique for more efficiently and exactly.
     * This keyword used to search logs in overall for Eligibility Process in the splunk : [Eligibility]
     **/
    public static final String APPLICATION_KEYWORD = "Eligibility";

    /**
     * Application name that is used to update database record's CREATED_BY and UPDATED_BY
     */
    public static final String APPLICATION_NAME = "EligibilityProcess";

    /**
     * Exchange header name to keep TraceNumber in inquiry that was sent to ELIGBILL
     */
    public static final String TRACE_NUMBER_HEADER = "TRACE_NUMBER_HEADER";

    /**
     * Exchange header name to keep inquiry record in Map<String, Object>
     */
    public static final String INQUIRY_RECORD_HEADER = "INQUIRY_RECORD_HEADER";

    /**
     * quartz2 group name for sending eligibility inquiry to ELIGBILL
     */
    public static final String SEND_ELIGIBILITY_INQUIRY_QUARTZ2_GROUP_NAME = "send-eligibility-inquiry-all-agencies-group";

    /**
     * Default date time format is used in GSONProvider when converting inquiry object to inquiry JSON to send to ELIGIBILL
     */
    public static final String DEFAULT_DATE_TIME_FORMAT_IN_INQUIRY_JSON = "yyyy-MM-dd";

    /**
     * Default date time format is used in GSONProvider when converting every object to JSON and vice versa
     */
    public static final String DEFAULT_DATE_TIME_FORMAT_IN_JSON = "yyyy-MM-dd HH:mm:ss";

    /**
     * The date time format is used for logging message when needed
     */
    public static final String DEFAULT_DATE_TIME_FORMAT_IN_LOGGING = "MM/dd/yyyy HH:mm:ss.SSS";

    /**
     * Print out current date time in Camel logging
     */
    public static final String DATE_NOW_IN_CAMEL_LOGGING = String.format("${date:now:%s}", DEFAULT_DATE_TIME_FORMAT_IN_LOGGING);

    /**
     * Key name for storing/retrieving configuration value (TENANT_KEY_CONF_VAL) from the APP_TENANT_KEY_CONF table
     */
    public static final String ELIGIBILITY_PROCESS_CONF_KEY_NAME = "MW_ELIGIBILITY_PROCESS";
}
