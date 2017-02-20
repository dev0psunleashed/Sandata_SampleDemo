/**
 * 
 */
package com.sandata.services.mobilehealth.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author huyvo
 *
 */
public class MobileHealthExportStrategy implements AggregationStrategy {
    
    /**
     * Aggregate results into a List
     */
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {        
        List<Map<String, Object>> resultList = null;
        Map<String, Object> exportRow = newExchange.getIn().getBody(Map.class);
        
        if (oldExchange == null) {
            resultList = new ArrayList<>();
            if (exportRow != null) {
                resultList.add(exportRow);
            }
            newExchange.getIn().setBody(resultList);
            return newExchange;
            
        } else {            
            oldExchange.setProperty(Exchange.SPLIT_COMPLETE, newExchange.getProperty(Exchange.SPLIT_COMPLETE));
            resultList = oldExchange.getIn().getBody(List.class);
            if (exportRow != null) {
                resultList.add(exportRow);
            }
            return oldExchange;
        }
        
    }
}
