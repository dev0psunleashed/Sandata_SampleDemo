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

package com.sandata.lab.common.utils.app;

import org.apache.camel.*;

/**
 * Implements camel Component class.
 * <p/>
 *
 * @author David Rutgos
 */
public class AppComponent implements Component {

    public AppComponent() {
    }

    public Endpoint createEndpoint(String uri) throws Exception {
        return null;
    }

    public boolean useRawUri() {
        return false;
    }

    public EndpointConfiguration createConfiguration(String uri) throws Exception {
        return null;
    }

    public ComponentConfiguration createComponentConfiguration() {
        return null;
    }

    public void setCamelContext(CamelContext camelContext) {
    }

    public CamelContext getCamelContext() {
        return null;
    }
}
