package com.sandata.lab.rules.visit.exception.service.strategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.CallPreferences;

/**
 * <p>visit exception enrichment strategy for requests to Visit Verification data service.</p>
 *
 * @author thanhxle
 */

public class VisitExceptionEnrichmentStrategy implements AggregationStrategy {
    private Logger visitExcpEnrichmentStrategyLogger = LoggerFactory.getLogger(VisitExceptionEnrichmentStrategy.class);

    /*
     * (non-Javadoc)
     * @see org.apache.camel.processor.aggregate.AggregationStrategy#aggregate(org.apache.camel.Exchange, org.apache.camel.Exchange)
     * 
     * Enrich info by getting data from database
     */
    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {

        List body = new ArrayList();
        Map<String, Object> headers = new LinkedHashMap<>();

        if (resource != null) {
            processExchangeForList(body, headers, resource);
        }

        if (original != null) {
            processExchangeForList(body, headers, original);
        }

        for (Map.Entry<String, Object> header : headers.entrySet()) {
            original.getIn().setHeader(header.getKey(), header.getValue());
        }

        original.getIn().setBody(body);

        String message = "Completed the VisitExceptionEnrichmentStrategy";
        visitExcpEnrichmentStrategyLogger.info(LoggingUtils.getLogMessageForProcessor(
        		LoggingUtils.SUB_APP_VISIT_EXCEPTION_KEYWORD, VisitExceptionEnrichmentStrategy.class, "aggregate", message));
        
        return original;
    }

    private void processExchangeForList(List list, Map<String, Object> headers, Exchange exchange) {
        Object body = exchange.getIn().getBody();
        if (body != null) {
            if (body instanceof List) {
                list.addAll((List) body);
            } else if (body instanceof CallPreferences) {
                list.add(body);
            } else {
                list.add(body);
            }
        }
        
        //handling headers enrichment
        for (Map.Entry<String, Object> header : exchange.getIn().getHeaders().entrySet()) {
        	headers.put(header.getKey(), header.getValue());
        }
    }

    
}
