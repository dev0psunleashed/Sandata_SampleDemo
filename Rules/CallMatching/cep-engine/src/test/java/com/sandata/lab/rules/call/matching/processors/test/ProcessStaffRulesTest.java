/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.junit.Test;

import com.sandata.lab.rules.call.matching.processors.ProcessStaffRules;
import com.sandata.lab.rules.call.model.OraStaffFact;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.State;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 27, 2016
 * 
 * 
 */
public class ProcessStaffRulesTest extends BaseTestSupport{

    ProcessStaffRules processStaffRules;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        processStaffRules = new ProcessStaffRules();
    }

    @Test
    public void test_process_state_is_NOT_MATCHED() throws Exception{
        
        //data input
        exchange.getIn().setHeader("STATE", State.NOT_MATCHED);
        processStaffRules.process(exchange);
        
        //expectation - body exchange is null
        assertNull(exchange.getIn().getBody());
        
    }
    
    
    
    @Test
    public void test_process_state_is_not_NOT_MATCHED_and_body_is_not_list_instance() throws Exception{
        
        //data input
        exchange.getIn().setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE);
        processStaffRules.process(exchange);
        
        //expectation - body exchange is null
        assertNull(exchange.getIn().getBody());
        
    }
    
    
    
    @Test
    public void test_process_state_is_not_NOT_MATCHED_and_body_is_list_instance() throws Exception{
        
        //data input
        exchange.getIn().setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE);
        List body = new ArrayList<>();
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("StaffFace_Dnis");
        body.add(staffFact);
        exchange.getIn().setBody(body);
        
        //executing the mothod
        processStaffRules.process(exchange);
        
        //expectations
        BatchExecutionCommandImpl batchCommand = (BatchExecutionCommandImpl) exchange.getIn().getBody();
        //assertNull(batchCommand);
        assertEquals(3, batchCommand.getCommands().size());
        
        //first command element
        assertTrue(batchCommand.getCommands().get(0) instanceof AgendaGroupSetFocusCommand);
        assertEquals("staff-matching", ((AgendaGroupSetFocusCommand)batchCommand.getCommands().get(0)).getName());
        
        //the second command element
        assertTrue(batchCommand.getCommands().get(1) instanceof InsertObjectCommand);
        assertEquals("staff-0", ((InsertObjectCommand)batchCommand.getCommands().get(1)).getOutIdentifier());
        
        //the third command element
        assertTrue(batchCommand.getCommands().get(2) instanceof FireAllRulesCommand);
    }
    
    
    @Test
    public void test_process_state_is_not_NOT_MATCHED_and_body_is_list_instance_list_size_greater_than_1() throws Exception{
        
        //data input
        exchange.getIn().setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE);
        List body = new ArrayList<>();
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("StaffFace_Dnis1");
        body.add(staffFact);
        
        staffFact = new OraStaffFact("OracleStaffID", "OracleDnisId");
        body.add(staffFact);
        
        
        exchange.getIn().setBody(body);
        
        //executing the mothod
        processStaffRules.process(exchange);
        
        //expectations
        BatchExecutionCommandImpl batchCommand = (BatchExecutionCommandImpl) exchange.getIn().getBody();
        
      //assertNull(batchCommand);
        assertEquals(4, batchCommand.getCommands().size());
        
        //the second command element
        assertTrue(batchCommand.getCommands().get(1) instanceof InsertObjectCommand);
        assertEquals("staff-0", ((InsertObjectCommand)batchCommand.getCommands().get(1)).getOutIdentifier());
        
        //the the thirds command element
        assertTrue(batchCommand.getCommands().get(2) instanceof InsertObjectCommand);
        assertEquals("oraStaff-1", ((InsertObjectCommand)batchCommand.getCommands().get(2)).getOutIdentifier());
        
    }
}
