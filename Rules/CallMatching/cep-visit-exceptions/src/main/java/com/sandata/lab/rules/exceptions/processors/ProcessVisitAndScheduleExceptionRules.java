package com.sandata.lab.rules.exceptions.processors;

import com.google.gson.Gson;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.rules.cache.client.CacheClient;
import com.sandata.lab.rules.call.model.*;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.command.impl.CommandFactoryServiceImpl;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.SetGlobalCommand;
//import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.slf4j.LoggerFactory;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/11/2015
 * Time: 8:55 AM
 */
public class ProcessVisitAndScheduleExceptionRules implements Processor {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {

        CallPreferences callPreferences = null;
        CacheClient client = CacheClient.getClient();
        CommandFactoryServiceImpl commandFactoryService = new CommandFactoryServiceImpl();
        Message in = exchange.getIn();

        BatchExecutionCommandImpl batchCommand = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commands = batchCommand.getCommands();
        Schedule schedule = null;
        VisitFact visitFact = null;
        String visitScheduleEventSk = "null";
        String scheduleEventSk = "null";
        if(in.getBody() instanceof ArrayList) {
            List<VisitAndSchedule> list = in.getBody(ArrayList.class);
            for(VisitAndSchedule vAndS : list) {
                if(vAndS.getType() == VisitAndScheduleType.SCHEDULE) {
                    schedule = vAndS.getSchedule();
                    schedule.setState(State.LOADED);
                    if(schedule.getScheduleEventExt()!= null && schedule.getScheduleEventExt().getScheduleEventSK() != null) {
                        scheduleEventSk = schedule.getScheduleEventExt().getScheduleEventSK().toString();
                    }

                }
                else if (vAndS.getType() == VisitAndScheduleType.VISIT) {
                    visitFact = vAndS.getVisit();
                    visitFact.setState(State.SCHEDULED);
                    if(visitFact.getVisit() != null && visitFact.getVisit().getScheduleEventSK() != null) {
                        visitScheduleEventSk = visitFact.getVisit().getScheduleEventSK().toString();
                    }

                }
            }
            String infoString = String.format("Visit schedule_Event_Sk = %s, schedule schedule_Event_Sk = %s ", visitScheduleEventSk, scheduleEventSk);
            if(visitScheduleEventSk.equals("null") || visitScheduleEventSk.equals("null")) {
                getVisitExcpLog().info("ERROR::" );
            }
            getVisitExcpLog().info(infoString);
        }
        else if(in.getBody() instanceof VisitAndSchedule ) {
            getVisitExcpLog().info("ERROR :: NOT EXPECTING TYPE VisitAndSchedule should be inside an ArrayList");
        }
        else if(in.getBody() instanceof ScheduleEventExt) {
            getVisitExcpLog().info("ERROR :: NOT EXPECTING TYPE ScheduleEventExt");
        }
        
        //prevent null pointer exception
        String bsnEntity = null;
        String dnis = null;
        String staffId = null;
        if(visitFact != null){
            
            bsnEntity = visitFact.getVisit().getBusinessEntityID();
            dnis = visitFact.getDnis();
            staffId = visitFact.getStaffId();
        }
        Gson gson = new Gson();
        if(bsnEntity != null) {
            String json = client.getCallPreferencesForAgencyId(bsnEntity);
            callPreferences = gson.fromJson(json, CallPreferences.class);
        }
        else if ( dnis != null) {
            String json = client.getCallPreferencesForDnis(dnis);
            callPreferences = gson.fromJson(json, CallPreferences.class);
        }
        if(callPreferences == null) {
            callPreferences = new CallPreferences();
            getVisitExcpLog().error("*****************************ERROR*********************");
            getVisitExcpLog().error("CALL PREFERENCES COULD NOT BE PULLED FROM CACHE CREATING FAKE TO PREVENT FAILURE FOR DEPLOYMENT!!");
            getVisitExcpLog().error("*****************************ERROR*********************");
        }

        commands.add(new SetGlobalCommand("callPreferences", callPreferences));
        commands.add(new SetGlobalCommand("visitExcpLog", getVisitExcpLog()));

        commands.add(new AgendaGroupSetFocusCommand("scheduled-visit"));
        //BigInteger staffSK = schedule.getScheduleEventExt().getStaffSK();
        commands.add(new InsertObjectCommand(visitFact, "visit-" + staffId));
        commands.add(new InsertObjectCommand(schedule, "schedule-" + staffId));
        commands.add(new FireAllRulesCommand());
        in.setBody(batchCommand,BatchExecutionCommandImpl.class);
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
