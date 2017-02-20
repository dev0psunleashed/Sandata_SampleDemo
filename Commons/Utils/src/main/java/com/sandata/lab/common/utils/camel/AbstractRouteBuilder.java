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

package com.sandata.lab.common.utils.camel;

import com.sandata.lab.common.utils.error.ServiceErrorHandler;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

/**
 * Abstract implementation of the RouteBuilder camel class.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class AbstractRouteBuilder extends RouteBuilder {

    public AbstractRouteBuilder() {
    }

    public void configure() throws Exception {
        this.onException(Exception.class).handled(true).bean(ServiceErrorHandler.class, "handleError");
        this.buildRoute();
    }

    public RouteDefinition from(String uri) {
        return super.from(uri).setHeader("karaf.name", this.simple("${sys.karaf.name}"));
    }

    protected abstract void buildRoute();
}
