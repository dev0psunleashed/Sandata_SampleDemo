package com.sandata.lab.rules.call.matching.service.strategy;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.CallPreferences;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Call matching enrichment strategy for requests to Visit Verification data service.</p>
 *
 * @author jasonscott
 */
public class CallMatchingEnrichmentStrategy implements AggregationStrategy {
    private Logger callMatchingEnrichmentStrategyLogger = LoggerFactory.getLogger(CallMatchingEnrichmentStrategy.class);

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

        return original;
    }

    private void processExchangeForList(List list, Map<String, Object> headers, Exchange exchange) {
        Object body = exchange.getIn().getBody();
        if (body != null) {
            if (body instanceof List) {
                list.addAll((List) body);
            } else if (body instanceof CallPreferences) {
                handleCallPreferences(exchange, headers, (CallPreferences) body);
                list.add(body);
            } else {
                list.add(body);
            }
        }
    }

    /**
     * Set lower and upper limit date strings and store in exchange as headers
     * for schedule event enrichment.
     *
     * @param exchange        Specified Exchange.
     * @param callPreferences Specified CallPreferences.
     */
    private void handleCallPreferences(Exchange exchange, Map<String, Object> headers, CallPreferences callPreferences) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lowerLimitDateString = (String) exchange.getIn().getHeader("lowerLimitDateString");
        String upperLimitDateString = (String) exchange.getIn().getHeader("upperLimitDateString");
        Integer deviationThresholdMilliseconds = callPreferences.getDeviationThresholdMinutes() * 60 * 1000;
        try {
            Date lowerLimitDateWithThreshold = new Date(dateFormat.parse(lowerLimitDateString).getTime() - deviationThresholdMilliseconds.longValue());
            lowerLimitDateString = dateFormat.format(lowerLimitDateWithThreshold);
        } catch (ParseException e) {
        	 callMatchingEnrichmentStrategyLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
              		, this.getClass(), "handleCallPreferences", "Unexpected error while calculate lower and upper call datetime= {}"),e );
             
           throw new SandataRuntimeException("Unexpected error while calculate lower and upper call datetime");
        }
        try {
            Date upperLimitDateWithThreshold = new Date(dateFormat.parse(upperLimitDateString).getTime() + deviationThresholdMilliseconds.longValue());
            upperLimitDateString = dateFormat.format(upperLimitDateWithThreshold);
        } catch (ParseException e) {
        	
        	 callMatchingEnrichmentStrategyLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
             		, this.getClass(), "handleCallPreferences", "Unexpected error while calculate lower and upper call datetime= {}"),e );
             
            throw new SandataRuntimeException("Unexpected error while calculate lower and upper call datetime");
        }


        headers.put("lowerLimitDateString", lowerLimitDateString);
        headers.put("upperLimitDateString", upperLimitDateString);
        
        //loggings
        callMatchingEnrichmentStrategyLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
        		, this.getClass(), "handleCallPreferences", "Call DeviationThresholdMinutes  (x*60*1000)= {}"),callPreferences.getDeviationThresholdMinutes() );
        
        callMatchingEnrichmentStrategyLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
        		, this.getClass(), "handleCallPreferences", "Call lowerLimitDateString = {}"),lowerLimitDateString );
        
        callMatchingEnrichmentStrategyLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
        		, this.getClass(), "handleCallPreferences", "Call upperLimitDateString = {}"),upperLimitDateString );
    }
}
