/**
 * 
 */
package com.sandata.lab.rules.dl.processors.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.rules.dl.processors.VisitProcessorProducer;
import com.sandata.lab.rules.call.model.VisitEventFact;
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
public class VisitProcessorProducerTest  extends BaseTestSupport{

    VisitProcessorProducer visitProcessorProducer;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        visitProcessorProducer = new VisitProcessorProducer();
    }
    
    
    @Test
    public void test_process_body_is_Visit_instance_visit_ID_empty() throws Exception{
        
        //input data
        Visit visit = new Visit();
        exchange.getIn().setBody(visit);
        //executing the method
        visitProcessorProducer.process(exchange);
        
        Visit result = exchange.getIn().getBody(Visit.class);
        
        //expectation - visit ID will be generate randomly
        //assertNotNull(result.getVisitID());
        //System.out.println(result.getVisitID());
    }
    
    
    @Test
    public void test_process_body_is_VisitFactt() throws Exception{
        
        //input data
        VisitFact visitFact = new VisitFact();
        //VisitTaskList != null
        VisitChild visitChild = new VisitChild();
        List<VisitTaskList> visitTaskLists = new ArrayList<>();
        VisitTaskList taskList = new VisitTaskList();
        visitTaskLists.add(taskList);
        visitChild.setVisitTaskList(visitTaskLists);
        visitFact.setVisit(visitChild);
        
        exchange.getIn().setBody(visitFact);
        //executing the method
        visitProcessorProducer.process(exchange);
        
        Visit result = exchange.getIn().getBody(Visit.class);
        
        //expectation - visit ID will be generate randomly
        //assertNotNull(result.getVisitID());
        //System.out.println(result.getVisitID());
    }

    
    @Test
    public void test_process_body_is_VisitFact_ExtraneousCalls_not_null() throws Exception{
        
        //input data
        VisitFact visitFact = new VisitFact();
        visitFact.addExtraneousCalls(new VisitEventFact());
        exchange.getIn().setBody(visitFact);
        //executing the method
        visitProcessorProducer.process(exchange);
        Visit result = exchange.getIn().getBody(Visit.class);
        //expectation - visit ID will be generate randomly
        //assertNotNull(result.getVisitID());
        //System.out.println(result.getVisitID());
        assertEquals(1, result.getVisitEvent().size());
    }
    
    

    class VisitChild extends Visit{
        
        public void setVisitEvent(List<VisitEvent> visitEvents){
            this.visitEvent = visitEvents;
        }
        
        public void setVisitTaskList(List<VisitTaskList> visitTaskList){
            this.visitTaskList = visitTaskList;
        }
        
        
        public void setTaskList(List<VisitTaskList> visitTaskList){
            this.visitTaskList = visitTaskList;
        }
        
        
    }
    


}
