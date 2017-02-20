package com.sandata.lab.exports.payroll.impl;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.HashMap;


public class SchedulePayrollExportRoute extends RouteBuilder {

    private String fromUri;
    private String toUri;
    private String routeId;
    private HashMap<String, String> data;

    /**
     * Empty Constructor.
     */
    public SchedulePayrollExportRoute() {
    }

    /**
     * @param routeId
     * @param fromUri
     */
    public SchedulePayrollExportRoute(final String routeId,
                                      final String fromUri, String toUri, HashMap<String, String> data) {
        this.fromUri = fromUri;
        this.toUri = toUri;
        this.routeId = routeId;
        this.data = data;
    }


    @Override
    public final void configure() throws Exception {

        from(fromUri).routeId(routeId)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setHeader("bsnEntInfo", data);
                    }
                })
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setHeader("bsnEntID", data.get("bsnEntID"));
                    }
                })
                .to(toUri);

    }

}
