package com.sandata.lab.rules.vv.imports.data.api;

import org.apache.camel.Exchange;

/**
 * @since 21st 9 , 2016
 * @author thanh.le
 * interface for processor that interact to EVV database.
 */
public interface DataService {
    public void getUnprocessedCalls(Exchange exchange);
}
