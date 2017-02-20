package com.sandata.lab.imports.calls.api;

import org.apache.camel.Exchange;

public interface DataService {
    public void getUnprocessedCalls(Exchange exchange);
}
