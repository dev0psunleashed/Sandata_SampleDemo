package com.sandata.lab.rest.visit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Date: 2/10/14.
 * Time: 9:10 AM
 */
public abstract class BaseTestSupport extends CamelBlueprintTestSupport {

    final protected DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected MockEndpoint resultEndpoint;

    protected Exchange exchange;

    protected abstract void onSetup() throws Exception;

    @Before
    public void setUp() throws Exception {


        CamelContext ctx = new DefaultCamelContext();
        exchange = new DefaultExchange(ctx);


        onSetup();
    }
}

