package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.drools.core.command.impl.CommandFactoryServiceImpl;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.SetGlobalCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by tom.dornseif on 2/18/2016.
 */
public class ProcessVisitExceptionRules implements Processor {
    private Logger logger = LoggerFactory.getLogger(ProcessVisitExceptionRules.class);
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    @Override
    public void process(Exchange exchange) throws Exception {
        //CallPreferences callPreferences = new CallPreferences();

        CommandFactoryServiceImpl commandFactoryService = new CommandFactoryServiceImpl();
        Message in = exchange.getIn();

        BatchExecutionCommandImpl batchCommand = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commands = batchCommand.getCommands();

        //if((State)in.getHeader("STATE") == State) {
        if(in.getBody() instanceof VisitFact) {
            //Visit visit = in.getBody(VisitFact.class);
            VisitFact visitFact = in.getBody(VisitFact.class);
            //VisitFact visitFact = new VisitFact(visit);
            String dnis = visitFact.getDnis();
            String staffId = visitFact.getStaffId();
            if(visitFact.getVisit().getScheduleEventSK() != null && visitFact.getVisit().getScheduleEventSK().intValue() < 1)
                visitFact.getVisit().setScheduleEventSK(null);
            //commands.add(new SetGlobalCommand("callPreferences", callPreferences));
            //commands.add(new AgendaGroupSetFocusCommand("some-agenda"));
            commands.add(new InsertObjectCommand(visitFact, "visit-" + staffId));
            commands.add(new SetGlobalCommand("visitExcpLog", getVisitExcpLog()));
            commands.add(new FireAllRulesCommand());
            in.setBody(batchCommand,BatchExecutionCommandImpl.class);
        }
        else
        {
            in.setBody(null);
        }
    }

    public Logger getVisitExcpLog() {
        if(this.visitExcpLog != null) {
            return this.visitExcpLog;
        }
        else {
            this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
            return this.visitExcpLog;
        }

    }

    public void setVisitExcpLog(Logger visitExcpLog) {
        this.visitExcpLog = visitExcpLog;
    }
}
