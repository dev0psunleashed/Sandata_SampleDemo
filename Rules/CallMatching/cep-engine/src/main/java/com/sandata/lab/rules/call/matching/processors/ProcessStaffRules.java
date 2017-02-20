package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.model.OraStaffFact;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.State;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/11/2015
 * Time: 8:55 AM
 */
public class ProcessStaffRules implements org.apache.camel.Processor {
    private Logger logger = LoggerFactory.getLogger(ProcessStaffRules.class);

    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        BatchExecutionCommandImpl batchCommand = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commandList = batchCommand.getCommands();

        if((State)in.getHeader("STATE") == State.NOT_MATCHED) {
            logger.warn(String.format("Staff was not located in business entity and will not be allowed to schedule an unplanned visit!  DNIS and StaffID = %s", (String)in.getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT)));
            exchange.getIn().setBody(null);
        }
        else {
            commandList.add(new AgendaGroupSetFocusCommand("staff-matching"));
            if (in.getBody() instanceof ArrayList) {
                ArrayList staffList = in.getBody(ArrayList.class);
                int size = staffList.size();

                for (int i = 0; i < size; i++) {
                    if (i == 0) {
                        //This should be the calls entered staffId
                        StaffFact staff = (StaffFact) staffList.get(i);
                        commandList.add(new InsertObjectCommand(staff, "staff-0"));
                    } else {
                        OraStaffFact staff = (OraStaffFact) staffList.get(i);
                        commandList.add(new InsertObjectCommand(staff, "oraStaff-" + Integer.toString(i)));
                    }

                }

                commandList.add(new FireAllRulesCommand());
                exchange.getIn().setBody(batchCommand);
            }
        }
    }
}
