package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.VisitTaskList;
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
public class ProcessWireTapLog implements org.apache.camel.Processor {
    private Logger visitExcpWiretapLog = LoggerFactory.getLogger("visitExcpWiretapLog");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitFact) {
            VisitFact visitFact = in.getBody(VisitFact.class);

            String visitSk = "(EMPTY OR NULL)";
            String scheduleEvntSk = "(EMPTY OR NULL)";
            String tasksStr = "(EMPTY OR NULL)";
            List<VisitTaskList> tasks = visitFact.getTaskList();
            Date actStart = visitFact.getCallIn();
            String actStartStr = "(EMPTY OR NULL)";
            Date actEnd = visitFact.getCallOut();
            String actEndStr = "(EMPTY OR NULL)";
            String ptId = visitFact.getPatientId();
            String staffId = visitFact.getStaffId();
            if(visitFact.getVisit() != null || visitFact.getVisit().getVisitSK() != null) {
                visitSk = visitFact.getVisit().getVisitSK().toString();
                if(visitFact.getVisit().getScheduleEventSK() != null) {
                    scheduleEvntSk = visitFact.getVisit().getScheduleEventSK().toString();
                }
            }
            int cnt = 0;
            if(tasks != null && tasks.size() > 0) {
                tasksStr = "[ ";
                for(VisitTaskList task : tasks) {
                    cnt++;
                    if(cnt > 1) {
                        tasksStr += ", ";
                    }
                    tasksStr += "{ " + task.getVisitTaskListID() + " }";
                }
                tasksStr += " ]";
            }
            if(actStart != null) {
                actStartStr = sdf.format(actStart);
            }
            if(actEnd != null) {
                actEndStr = sdf.format(actEnd);
            }
            try {
                String infoString = String.format("VISIT_SK = %s, PT_ID = %s, STAFF_ID = %s, ACT_START = %s, ACT_END = %s, SCHED_EVNT_SK = %s, VISIT_TASK_LST = %s",
                        visitSk, ptId, staffId, actStartStr, actEndStr, scheduleEvntSk, tasksStr);
                getVisitExcpWiretapLog().info(infoString);
            }
            catch(Exception ex) {
                getVisitExcpWiretapLog().info(ex.getLocalizedMessage());
            }

        }
        else {
            getVisitExcpWiretapLog().info("WARN::ProcessWireTapLog::UNEXPECTED MESSAGE BODY SENT TO WIRETAP");
        }
    }

    public Logger getVisitExcpWiretapLog() {
        if(this.visitExcpWiretapLog == null) {
            this.visitExcpWiretapLog = LoggerFactory.getLogger("visitExcpWiretapLog");
        }
        return this.visitExcpWiretapLog;
    }

    public void setVisitExcpWiretapLog(Logger visitExcpWiretapLog) {
        this.visitExcpWiretapLog = visitExcpWiretapLog;
    }
}
