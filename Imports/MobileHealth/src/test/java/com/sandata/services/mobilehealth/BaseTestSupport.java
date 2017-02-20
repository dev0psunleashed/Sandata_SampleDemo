package com.sandata.services.mobilehealth;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;

import com.sandata.up.commons.app.AppComponent;

public abstract class BaseTestSupport extends CamelBlueprintTestSupport {

    protected MockEndpoint resultEndpoint;

    protected Exchange exchange;

    protected abstract void onSetup();
    
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint-test.xml";
    }
    
    @Override
    protected String getBundleFilter() {
      /* prevent sandata-commons bundle from starting */
      return "(!(Bundle-SymbolicName=sandata-commons))";
    }

    @Before
    public void setUp() throws Exception {

        resultEndpoint = new MockEndpoint("mock:result", new AppComponent());

        exchange = new DefaultExchange(resultEndpoint);

        // Default Pattern
        exchange.setPattern(ExchangePattern.InOut);

        Message in = new DefaultMessage();
        exchange.setIn(in);
        
        super.setUp();

        onSetup();
    }
    
    
}
