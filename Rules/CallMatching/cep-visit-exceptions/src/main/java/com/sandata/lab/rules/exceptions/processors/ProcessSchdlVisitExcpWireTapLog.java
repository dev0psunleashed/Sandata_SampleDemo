package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitAndScheduleType;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 9/29/2016
 * Time: 5:41 AM
 */
public class ProcessSchdlVisitExcpWireTapLog implements org.apache.camel.Processor {
    private Logger schdldVisitExcpWiretapLog = LoggerFactory.getLogger("schdldVisitExcpWiretapLog");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitAndSchedule) {
            VisitAndSchedule visitAndSchedule = in.getBody(VisitAndSchedule.class);
            if(visitAndSchedule.getType() == VisitAndScheduleType.VISIT) {
                VisitFact visitFact = visitAndSchedule.getVisit();
                String visitSk = "(EMPTY OR NULL)";
                String scheduleEvntSk = "(EMPTY OR NULL)";
                Date actStart = visitFact.getCallIn();
                String actStartStr = "(EMPTY OR NULL)";
                Date actEnd = visitFact.getCallOut();
                String actEndStr = "(EMPTY OR NULL)";
                String ptId = visitFact.getPatientId();
                String staffId = visitFact.getStaffId();
                Date adjStart= null;
                String adjStartStr = "(EMPTY OR NULL)";
                Date adjEnd = null;
                String adjEndStr = "(EMPTY OR NULL)";
                if (visitFact.getVisit() != null || visitFact.getVisit().getVisitSK() != null) {
                    visitSk = visitFact.getVisit().getVisitSK().toString();
                    adjStart = visitFact.getVisit().getVisitAdjustedStartTimestamp();
                    adjEnd = visitFact.getVisit().getVisitAdjustedEndTimestamp();
                    if (visitFact.getVisit().getScheduleEventSK() != null) {
                        scheduleEvntSk = visitFact.getVisit().getScheduleEventSK().toString();
                    }
                }

                if (actStart != null) {
                    actStartStr = sdf.format(actStart);
                }
                if (actEnd != null) {
                    actEndStr = sdf.format(actEnd);
                }
                if (adjStart != null) {
                    adjStartStr = sdf.format(adjStart);
                }
                if (adjEnd != null) {
                    adjEndStr = sdf.format(adjEnd);
                }
                try {
                    String infoString = String.format("SCHED_EVNT_SK = %s, VISIT_SK = %s, PT_ID = %s, STAFF_ID = %s, ACT_START = %s, ACT_END = %s, ADJ_START = %s, ADJ_END = %s",
                            scheduleEvntSk, visitSk, ptId, staffId, actStartStr, actEndStr, adjStartStr, adjEndStr);
                    getSchdldVisitExcpWiretapLog().info(infoString);
                } catch (Exception ex) {
                    getSchdldVisitExcpWiretapLog().info(ex.getLocalizedMessage());
                }
            }
            else {
                Schedule schedule = visitAndSchedule.getSchedule();
                String scheduleEvntSk = visitAndSchedule.getScheduleEventSk();
                String ptId = schedule.getPatientId();
                String staffId = schedule.getStaffId();
                Date startTime = schedule.getStartTime();
                String startTimeString = "NULL";
                Date endTime = schedule.getEndTime();
                String endTimeString = "NULL";
                if (startTime != null) {
                    startTimeString = sdf.format(startTime);
                }
                if (endTime != null) {
                    endTimeString = sdf.format(endTime);
                }
                try {
                    String infoString = String.format("SCHED_EVNT_SK = %s, PT_ID = %s, STAFF_ID = %s, START = %s, END = %s",
                            scheduleEvntSk, ptId, staffId, startTimeString, endTimeString);
                    getSchdldVisitExcpWiretapLog().info(infoString);
                } catch (Exception ex) {
                    getSchdldVisitExcpWiretapLog().info(ex.getLocalizedMessage());
                }
            }
        }
        else {
            getSchdldVisitExcpWiretapLog().info("WARN::ProcessWireTapLog::UNEXPECTED MESSAGE BODY SENT TO WIRETAP");
        }
    }

    public Logger getSchdldVisitExcpWiretapLog() {
        if(this.schdldVisitExcpWiretapLog == null) {
            this.schdldVisitExcpWiretapLog = LoggerFactory.getLogger("schdldVisitExcpWiretapLog");
        }
        return this.schdldVisitExcpWiretapLog;
    }

    public void setSchdldVisitExcpWiretapLog(Logger schdldVisitExcpWiretapLog) {
        this.schdldVisitExcpWiretapLog = schdldVisitExcpWiretapLog;
    }
}
