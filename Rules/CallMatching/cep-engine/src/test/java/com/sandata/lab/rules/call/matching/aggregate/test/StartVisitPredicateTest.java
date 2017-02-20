package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.rules.call.matching.aggregate.StartVisitPredicate;
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
public class StartVisitPredicateTest extends BaseTestSupport{


    StartVisitPredicate startVisitPredicate;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        startVisitPredicate = new StartVisitPredicate();
    }
    
    
   @Test
   public void test_matches_method_body_size_greater_1(){
       List body = new ArrayList<>();
       body.add( new VisitFact());
       body.add( new VisitFact());
       exchange.getIn().setBody(body);
       exchange.getIn().setHeader("STATE", State.AGG_WAITING_FOR_RESPONSE.name());
       
       //executing
       boolean result = startVisitPredicate.matches(exchange);
       
       //assertions
       assertTrue(result);
   }
    
   
   @Test
   public void test_matches_method_body_size_is_1(){
       List body = new ArrayList<>();
       body.add( new VisitFact());
       exchange.getIn().setBody(body);
       exchange.getIn().setHeader("STATE", State.AGG_WAITING_FOR_RESPONSE.name());
       
       //executing
       boolean result = startVisitPredicate.matches(exchange);
       
       //assertions
       assertFalse(result);
   }
    
   @Test
   public void test_matches_method_body_size_greater_2_and_state_is_not_AGG_WAITING_FOR_RESPONSE(){
       List body = new ArrayList<>();
       body.add( new VisitFact());
       body.add( new VisitFact());
       exchange.getIn().setBody(body);
       exchange.getIn().setHeader("STATE", "Something_else");
       
       //executing
       boolean result = startVisitPredicate.matches(exchange);
       
       //assertions
       assertFalse(result);
   }
   
    
}
