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

package com.sandata.lab.imports.payroll;


import com.sandata.lab.common.utils.app.AppComponent;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * Abstract base class that has common test properties and methods.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public abstract class BaseTestSupport extends CamelBlueprintTestSupport {

    public String username = "JUnit_Test_Case";

    protected MockEndpoint resultEndpoint;

    protected Exchange exchange;

    protected abstract void onSetup() throws IOException;

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

        resultEndpoint = new MockEndpoint("mock:result", new AppComponent());
        exchange = new DefaultExchange(resultEndpoint);

        // Default Pattern
        exchange.setPattern(ExchangePattern.InOut);

        Message in = new DefaultMessage();
        exchange.setIn(in);

        onSetup();
    }
}
