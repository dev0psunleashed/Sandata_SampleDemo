/**
 * 
 */
package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.List;

import org.junit.Test;

import com.sandata.lab.rules.call.matching.aggregate.PrepVisitResponseForAggregation;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 27, 2016
 * 
 * 
 */
public class PrepVisitResponseForAggregationTest  extends BaseTestSupport{

    
    PrepVisitResponseForAggregation prepVisitResponseForAggregation;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        prepVisitResponseForAggregation = new PrepVisitResponseForAggregation();
    }
    
    
    @Test
    public void test_process_body_is_not_Visit_instance() throws Exception{
        
        //data test 
        Integer messageBody = new Integer(200);
        exchange.getIn().setBody(messageBody);
        
        //executing the method
        prepVisitResponseForAggregation.process(exchange);
        
        //asserttions - expectation
        assertEquals(messageBody, exchange.getIn().getBody());
    }
    
    
    @Test
    public void test_process_body_is_Visit_instance() throws Exception{
        
        //data test 
        com.sandata.lab.data.model.dl.model.Visit visit = new com.sandata.lab.data.model.dl.model.Visit();
        visit.setStaffID("TestStaffID");
        
        
        exchange.getIn().setBody(visit);
        
        //executing the method
        prepVisitResponseForAggregation.process(exchange);
        
        //asserttions - expectation
        assertTrue(exchange.getIn().getBody() instanceof List);
        assertEquals("TestStaffID", exchange.getIn().getHeader(PrepVisitResponseForAggregation.DNIS_AND_STAFFID));
        
        List result = exchange.getIn().getBody(List.class);
        assertEquals(1, result.size());
        
        VisitFact visitFact = (VisitFact) result.get(0);
        assertEquals(State.AGG_INSERTED_FROM_RESPONSE, visitFact.getState());
        
    }

}
