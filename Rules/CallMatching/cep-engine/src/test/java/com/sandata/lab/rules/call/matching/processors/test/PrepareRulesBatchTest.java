/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.SetGlobalCommand;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.junit.Test;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.matching.processors.PrepareRulesBatch;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 19, 2016
 * 
 * 
 */
public class PrepareRulesBatchTest  extends BaseTestSupport{

    PrepareRulesBatch prepareRulesBatch;
    
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        // TODO Auto-generated method stub
        prepareRulesBatch = new PrepareRulesBatch();
    }
    
    
    
    @Test
    public void test_process_method_exchange_is_ArrayList_instance_with_one_VisitFact() throws Exception{
        
        //prepare data
        VisitFact visitFact = new VisitFact();
        List list = new ArrayList();
        list.add(visitFact);
        exchange.getIn().setBody(list);
        
        //executing
        prepareRulesBatch.process(exchange);
        
        
        //check
        assertTrue(exchange.getIn().getBody() instanceof BatchExecutionCommandImpl);
        BatchExecutionCommandImpl command = exchange.getIn().getBody(BatchExecutionCommandImpl.class);
        List commands = command.getCommands();
        assertEquals(4, commands.size());
        assertTrue(commands.get(0) instanceof SetGlobalCommand);
        assertTrue(commands.get(1) instanceof AgendaGroupSetFocusCommand);
        assertTrue(commands.get(2) instanceof InsertObjectCommand);
        assertTrue(commands.get(3) instanceof FireAllRulesCommand);
        
        
        
        
    }
    
    
    
    @Test
    public void test_process_method_exchange_is_ArrayList_instance_with_2_VisitFact() throws Exception{
        
        //prepare data
        VisitFact inserted = new VisitFact();
        VisitFact scheduled = new VisitFact();
        Visit visit = new Visit();
        visit.setStaffID("Staff01");
        scheduled.setVisit(visit);
        
        
        List list = new ArrayList();
        list.add(inserted);
        list.add(scheduled);
        exchange.getIn().setBody(list);
        
        //executing
        prepareRulesBatch.process(exchange);
        
        
        //check
        assertTrue(exchange.getIn().getBody() instanceof BatchExecutionCommandImpl);
        BatchExecutionCommandImpl command = exchange.getIn().getBody(BatchExecutionCommandImpl.class);
        List commands = command.getCommands();
        assertEquals(5, commands.size());
        assertTrue(commands.get(0) instanceof SetGlobalCommand);
        assertTrue(commands.get(1) instanceof AgendaGroupSetFocusCommand);
        assertTrue(commands.get(2) instanceof InsertObjectCommand);
        assertTrue(commands.get(3) instanceof InsertObjectCommand);
        assertTrue(commands.get(4) instanceof FireAllRulesCommand);
        
        //checking visit id
        InsertObjectCommand df = (InsertObjectCommand) commands.get(2);
        VisitFact visitFact = (VisitFact) df.getObject();
        Visit visitInsered = visitFact.getVisit();
        assertEquals(visitInsered.getStaffID(),inserted.getVisit().getStaffID());
        
    }
    
    
    @Test
    public void test_process_method_exchange_is_ArrayList_instance_with_3_VisitFact() throws Exception{
        
        //prepare data
        VisitFact inserted = new VisitFact();
        VisitFact scheduled = new VisitFact();
        Visit visit = new Visit();
        visit.setStaffID("Staff01");
        scheduled.setVisit(visit);
        
        
        VisitFact scheduled2 = new VisitFact();
        visit = new Visit();
        visit.setStaffID("Staff02");
        scheduled2.setVisit(visit);
        
        
        List list = new ArrayList();
        list.add(inserted);
        list.add(scheduled);
        list.add(scheduled2);
        
        exchange.getIn().setBody(list);
        
        //executing
        prepareRulesBatch.process(exchange);
        
        
        //check
        assertTrue(exchange.getIn().getBody() instanceof BatchExecutionCommandImpl);
        BatchExecutionCommandImpl command = exchange.getIn().getBody(BatchExecutionCommandImpl.class);
        List commands = command.getCommands();
        assertEquals(6, commands.size());
        assertTrue(commands.get(0) instanceof SetGlobalCommand);
        assertTrue(commands.get(1) instanceof AgendaGroupSetFocusCommand);
        assertTrue(commands.get(2) instanceof InsertObjectCommand);
        assertTrue(commands.get(3) instanceof InsertObjectCommand);
        assertTrue(commands.get(4) instanceof InsertObjectCommand);
        assertTrue(commands.get(5) instanceof FireAllRulesCommand);
        
        //checking visit id
        InsertObjectCommand df = (InsertObjectCommand) commands.get(4);
        VisitFact visitFact = (VisitFact) df.getObject();
        Visit visitInsered = visitFact.getVisit();
        assertEquals(visitInsered.getStaffID(),scheduled2.getVisit().getStaffID());
        
    }
    
}
