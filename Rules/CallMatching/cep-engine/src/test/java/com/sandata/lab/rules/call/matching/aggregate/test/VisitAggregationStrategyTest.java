package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.aggregate.VisitAggregationStrategy;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;


/**
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 20, 2016
 * 
 *
 */
public class VisitAggregationStrategyTest extends BaseTestSupport{


    VisitAggregationStrategy visitAggregationStrategy;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        visitAggregationStrategy = new VisitAggregationStrategy();
    }
    
    
    @Test
    public void test_aggregate_first_time_run(){
        //first time run, old exchange is null
        Exchange oldExchange = null;
        
        exchange.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, "dnis01");
        exchange.getIn().setHeader("STATE", "State");
        exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "StaffId01");
        List<VisitFact> body = new ArrayList<>();
        body.add(new VisitFact());
        exchange.getIn().setBody(body);
        Exchange newExchange = exchange;
        
        //executing method
        Exchange resultExchange = visitAggregationStrategy.aggregate(oldExchange, newExchange);
        
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(1,actualListSize );
        assertEquals("State", resultExchange.getIn().getHeader("STATE"));
        assertEquals("dnis01", resultExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals("StaffId01", resultExchange.getIn().getHeader(PrepForAggregation.CORRECTED_STAFF_ID));
        
    }
    
    
    
    @Test
    public void test_aggregate_second_time_run(){
        //the second  time run, both exchanges is not null
        Exchange oldExchange = new DefaultExchange(context);

        
        exchange.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, "dnis01");
        exchange.getIn().setHeader("STATE", "State");
        exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "StaffId01");
        List<VisitFact> body = new ArrayList<>();
        body.add(new VisitFact());
        exchange.getIn().setBody(body);
        
        oldExchange.getIn().setBody(body);
        Exchange newExchange = exchange;
        
        //executing method
        Exchange resultExchange = visitAggregationStrategy.aggregate(oldExchange, newExchange);
        
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(2,actualListSize );
        assertEquals("State", resultExchange.getIn().getHeader("STATE"));
        assertEquals("dnis01", resultExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals("StaffId01", resultExchange.getIn().getHeader(PrepForAggregation.CORRECTED_STAFF_ID));
        
    }
    
    
    
    @Test
    public void test_aggregate_second_time_run_and_state_is_AGG_WAITING_FOR_RESPONSE(){
        //the second  time run, both exchanges is not null
        Exchange oldExchange = new DefaultExchange(context);

        
        exchange.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, "dnis01");
        exchange.getIn().setHeader("STATE", "State");
        exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "StaffId01");
        List<VisitFact> body = new ArrayList<>();
        body.add(new VisitFact());
        exchange.getIn().setBody(body);
        
        oldExchange.getIn().setBody(body);
        oldExchange.getIn().setHeader("STATE",  State.AGG_WAITING_FOR_RESPONSE.name());
       
        
        
        Exchange newExchange = exchange;
        
        //executing method
        Exchange resultExchange = visitAggregationStrategy.aggregate(oldExchange, newExchange);
        
        
        //assertions
        assertEquals(State.AGG_WAITING_FOR_RESPONSE.name(), resultExchange.getIn().getHeader("STATE"));
        
    }
    
    
    
}
