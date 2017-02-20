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
package ${package}.routes;

import com.sandata.up.commons.camel.AbstractRouteBuilder;
import com.sandata.up.commons.service.data.transformer.FormatTransformer;
import ${package}.impl.RestExampleService;
import ${package}.utils.constants.App;
import ${package}.utils.constants.Messaging;

/**
 * This is just an example. Delete this file.
 * <p/>
 *
 * @author David Rutgos
 */
public class ExampleRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        // TODO: Delete/Rename ...
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EXAMPLE_METHOD.toString())
                .routeId(App.ID.EXAMPLE_METHOD.toString())
                .bean(RestExampleService.class, "example")
                .bean(FormatTransformer.class, "toResponse");
    }
}
