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

package com.sandata.lab.dl.doc.utils.constants;

/**
 * Enumerates the constants for camel/cxf endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class Messaging {

    public enum Names {

        COMPONENT_TYPE_TOPIC ("activemq:topic:"),

        COMPONENT_TYPE_QUEUE ("activemq:queue:"),

        COMPONENT_TYPE_SEDA ("seda:"),

        COMPONENT_TYPE_INTERVAL ("timer://"),

        COMPONENT_TYPE_FTP ("ftp://"),

        COMPONENT_TYPE_FILE ("file:"),

        CXFRS ("cxfrs://bean://");

        private final String Names;

        private Names(final String Names) {
            this.Names = Names;
        }

        @Override
        public String toString() {
            return Names;
        }
    }
}
