package com.sandata.lab.rules.call.matching.processors;


import com.google.gson.Gson;
import com.sandata.lab.rules.cache.client.CacheClient;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.routes.AggregateVisitRoute;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.SetGlobalCommand;
import org.drools.core.command.runtime.rule.AgendaGroupSetFocusCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;

import org.drools.core.command.impl.CommandFactoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/15/2015
 * Time: 10:12 AM
 */
public class PrepareRulesBatch implements org.apache.camel.Processor {
    Logger logger = LoggerFactory.getLogger(PrepareRulesBatch.class);
    private Logger rulesLog = LoggerFactory.getLogger("rulesLog");

    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        //CallPreferences callPreferences = new CallPreferences();
        CacheClient client = CacheClient.getClient();
        CommandFactoryServiceImpl commandFactoryService = new CommandFactoryServiceImpl();

        if(in.getBody() instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
            List<GenericCommand<?>> commands = command.getCommands();
            Gson gson  = new Gson();
            CallPreferences callPreferences = new CallPreferences();
            String dnis = ((VisitFact)list.get(0)).getDnis();
            if(client != null) {
                String json = client.getCallPreferencesForDnis(dnis);
                callPreferences = gson.fromJson(json, CallPreferences.class);
            }
            else {
                logger.error("using defaults for callpreferences!");
            }

            String staffId = ((VisitFact)list.get(0)).getStaffId();
            commands.add(new SetGlobalCommand("callPreferences", callPreferences));
            commands.add(new SetGlobalCommand("rulesLog", getRulesLog()));
            commands.add(new AgendaGroupSetFocusCommand("pick-schedule"));
            if(list.size() > 1) {
                VisitFact inserted = (VisitFact) list.get(0);
                inserted.setVv_id((String)in.getHeader(AggregateVisitRoute.SANTRAX_ID));
                if(inserted.getVv_id() != null && !inserted.getVv_id().isEmpty()) {
                    inserted = updateAndLogStaffIdCorrection(inserted, (String)in.getHeader(PrepForAggregation.DATABASE_PROVIDED_STAFF_ID));
                }
                VisitFact scheduled = (VisitFact) list.get(1);
                String staffIDInserted = inserted.getVisit().getStaffID();
                if(scheduled.getVisit() != null) {
                    String staffIDScheduled = scheduled.getVisit().getStaffID();
                    if(staffIDScheduled != null && staffIDScheduled.length() > 0) {
                        logger.info(String.format("siphoned StaffSK : %s ", staffIDScheduled));
                        rulesLog.info(String.format("PrepareRulesBatch::siphoned StaffSK from first scheduled=%s ", staffIDScheduled));
                        //inserted.getVisit().setStaffID(staffID);
                    }
                }
                else
                {
                    logger.info("PrepareRulesBatch::scheduled VisitFact had no valid visit call to scheduled.getVisit() returned null!!");
                    rulesLog.info("PrepareRulesBatch::first scheduled VisitFact had no valid visit call to scheduled.getVisit() returned null!!");

                }
                if(staffIDInserted != null && staffIDInserted.length() > 0) {
                    rulesLog.info(String.format("PrepareRulesBatch::siphoned StaffSK from inserted visit=%s ", staffIDInserted));
                }
                Date scheduledFrom1 = scheduled.getScheduledFrom();
                if(scheduledFrom1 != null) {
                    rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleFrom = %s", 1, scheduledFrom1.toString()));
                }
                else {
                    rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleFrom = %s", 1, "<NULL>"));
                }
                Date scheduledTo1 = scheduled.getScheduledTo();
                if(scheduledTo1 != null) {
                    rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleTo = %s", 1, scheduledTo1.toString()));
                }
                else {
                    rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleTo = %s", 1, "<null>"));
                }

                commands.add(new InsertObjectCommand(inserted, "visit-" + Integer.toString(0)));
                commands.add(new InsertObjectCommand(scheduled, "visit-" + Integer.toString(1)));
                for (int i = 2; i < list.size(); i++) {
                    VisitFact visitFact = (VisitFact) list.get(i);
                    Date scheduledFrom = visitFact.getScheduledFrom();
                    if(scheduledFrom != null) {
                        rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleFrom = %s", i, scheduledFrom.toString()));
                    }
                    else {
                        rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleFrom = %s", i, "<NULL>"));
                    }
                    Date scheduledTo = visitFact.getScheduledTo();
                    if(scheduledTo != null) {
                        rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleTo = %s", i, scheduledTo.toString()));
                    }
                    else {
                        rulesLog.info(String.format("PrepareRulesBatch::Schedule %d ScheduleTo = %s", i, "<null>"));
                    }



                    commands.add(new InsertObjectCommand(visitFact, "visit-" + Integer.toString(i)));
                }
            }
            else {
                VisitFact inserted = (VisitFact) list.get(0);
                inserted.setVv_id((String)in.getHeader(AggregateVisitRoute.SANTRAX_ID));
                commands.add(new InsertObjectCommand(inserted, "visit-" + Integer.toString(0)));
            }
            commands.add(new FireAllRulesCommand());
            in.setBody(command,BatchExecutionCommandImpl.class);
        }
    }

    private VisitFact updateAndLogStaffIdCorrection(VisitFact inserted, String correctedStaffId) {
        //we might check to make sure santraxId matches the old staffId before changeing it.
        String updatedStaffId = correctedStaffId;
        String originalStaffId = inserted.getStaffId();
        String resultMsg = "PrepareRulesBatch::";
        if(updatedStaffId != null && ! updatedStaffId.isEmpty()) {
            inserted.setStaffId(updatedStaffId);
            resultMsg += "Updated staffId results met  VV_ID and CorrectedStaffId were present.  ";
        }
        else {
            resultMsg += "::WARNING::Could not Update! staffId results failed to be met.  VV_ID was present but CorrectedStaffId was not.  ";
            updatedStaffId = "(null)";
        }

        String infoString = String.format("%s:: Entered StaffId = %s to Corrected StaffId = %s.", resultMsg,
                originalStaffId, updatedStaffId);
        rulesLog.info(infoString);
        return inserted;
    }

    public Logger getRulesLog() {
        if(this.rulesLog != null) {
            return this.rulesLog;
        }
        else {
            rulesLog = LoggerFactory.getLogger("rulesLog");
            return this.rulesLog;
        }
    }

    public void setRulesLog(Logger rulesLog) {
        this.rulesLog = rulesLog;
    }
}
