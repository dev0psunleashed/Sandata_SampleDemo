/**
 * 
 */
package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.aggregate.ResubmissionAggregation;
import com.sandata.lab.rules.call.matching.exceptions.CepException;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 27, 2016
 * 
 * 
 */
public class ResubmissionAggregationTest extends BaseTestSupport{

    
    ResubmissionAggregation resubmissionAggregation;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        resubmissionAggregation = new ResubmissionAggregation();
    }
    
    @Test
    public void test_support_body_is_list_empty() throws CepException{
        
        //data test - an empty list
        List inputs = new ArrayList<>();
        
        exchange.getIn().setBody(inputs);
        
        //executing the method
        resubmissionAggregation.processMatches(exchange);
        
        //expectation : have to check null, empty of list, if yes, do nothing
        assertEquals(inputs,exchange.getIn().getBody());
    }
    
    
    @Test
    public void test_support_body_is_list_with_VisitEvent() throws CepException{
        
        //data test - an empty list
        List inputs = new ArrayList<>();
        VisitEvent visitEvent  = new VisitEvent();
        visitEvent.setPatientID("TestPatientID");
        inputs.add(visitEvent);
        exchange.getIn().setBody(inputs);
        //executing the method
        resubmissionAggregation.processMatches(exchange);
        
        //expectation : have to check null, empty of list, if yes, do nothing
        assertEquals(visitEvent,exchange.getIn().getBody());
    }
    
    
    

}
