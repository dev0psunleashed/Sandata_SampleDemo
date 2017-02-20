package com.sandata.lab.rest.visit.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 2/3/2017
 * Time: 12:11 PM
 */
public class TestProcessor implements Processor {
    private long visit_sk;
    public TestProcessor(long visitSk) {
        visit_sk = visitSk;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
           exchange.getIn().setHeader("visit_sk", new Long(visit_sk));
    }
}
