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
package ${package}.impl;

import com.sandata.up.commons.utils.log.SandataLogger;
import ${package}.api.ExampleService;
import ${package}.utils.log.ExampleSandataLogger;
import org.apache.camel.Exchange;

/**
 * An example interface implementation. Delete/Rename before use!
 * <p/>
 *
 * @author David Rutgos
 */

// TODO: Delete/Rename ...
public class RestExampleService implements ExampleService {

    @Override
    public void example(Exchange exchange) {

        SandataLogger logger = ExampleSandataLogger.CreateLogger(exchange);

        logger.start();

        exchange.getIn().setBody("This is only an Example! M'Kay?");

        logger.stop();
    }
}
