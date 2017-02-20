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

package com.sandata.lab.rest.reference.app;

import org.apache.camel.CamelContext;


public class AppContext {

    /** The application camel context. **/
    private static CamelContext context;

    /**
     * Utility classes should not have a public or default constructor.
     */
    private AppContext() { }

    /** Returns the application camel context. **/
    public static CamelContext getContext() {
        return context;
    }

    /**
     * Returns initialized application context.
     * @param context Specified context to be initialized
     * @return CamelContext
     */
    public static CamelContext initCamelContext(final CamelContext context) {
        AppContext.context = context;
        return AppContext.context;
    }
}
