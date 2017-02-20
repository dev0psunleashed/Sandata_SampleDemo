package com.sandata.lab.rules.dl.processors.test;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;

public abstract class BaseTestSupport extends CamelBlueprintTestSupport {

    protected MockEndpoint resultEndpoint;
    protected Exchange exchange;

    protected abstract void onSetup();

    @Before
    public void setUp() throws Exception {
        resultEndpoint = new MockEndpoint("mock:result", new Component() {

            @Override
            public CamelContext getCamelContext() {
                return new DefaultCamelContext();
            }

            @Override
            public void setCamelContext(CamelContext camelContext) {
            }

            @Override
            public ComponentConfiguration createComponentConfiguration() {
                return null;
            }

            @Override
            public EndpointConfiguration createConfiguration(String uri) throws Exception {
                return null;
            }

            @Override
            public Endpoint createEndpoint(String uri) throws Exception {
                return null;
            }

            @Override
            public boolean useRawUri() {
                return false;
            }

        });

        exchange = new DefaultExchange(resultEndpoint);

        // Default Pattern
        exchange.setPattern(ExchangePattern.InOut);

        Message in = new DefaultMessage();
        exchange.setIn(in);

        onSetup();
    }
}
