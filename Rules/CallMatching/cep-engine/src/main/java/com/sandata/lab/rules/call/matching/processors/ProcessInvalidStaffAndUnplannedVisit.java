package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 12/20/2015.
 */
public class ProcessInvalidStaffAndUnplannedVisit implements org.apache.camel.Processor {
    Logger logger = LoggerFactory.getLogger(ProcessInvalidStaffAndUnplannedVisit.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            VisitFact visitFact = (VisitFact)list.get(0);
            if(in.getHeader("STATE") == State.INVALID_STAFF) {
                VisitEventFact visitEventFact = visitFact.getCallCallIn();
                if(visitEventFact == null) {
                    if(list.size() < 2)
                        logger.error(String.format("ERROR in ProcessInvalidStaffAndUnplannedVisit::ArrayList was size %d, visitEventFact was null", list.size()));
                    else {
                        visitFact = (VisitFact) list.get(1);
                        visitEventFact = visitFact.getCallCallIn();
                    }
                }
                if(visitEventFact == null) {
                    logger.error(String.format("ERROR in ProcessInvalidStaffAndUnplannedVisit::ArrayList was size %d, visitEventFact was null", list.size()));
                    throw new Exception("Error in route");
                }
                VisitEvent visitEvent = visitEventFact.getVisit();
                visitEvent.setCallInIndicator(true);
                if(visitEvent.getTimezoneName() == null) {
                    visitEvent.setTimezoneName("US/Eastern");
                    logger.warn("timezone was null!");
                }
                //in.setBody(visitEvent );
                //Lets try to save this as a Unplanned Visit in order to save tasks and allow user to manual figure it out.
                in.setBody(visitFact);
                if(visitFact.getTaskList().size() > 0) {
                    //rulesDefaultLogger.warn(String.format("CallMatching:INVALID_STAFF, VisitEvent saved but not tasks:  Task list follows : size (%d)", visitFact.getTaskList().size()));
                    logger.warn(String.format("CallMatching:INVALID_STAFF, VisitFact was saved :  Task list follows : size (%d)", visitFact.getTaskList().size()));

                    String msg = "";
                    for(int i = 0;i < visitFact.getTaskList().size();i++) {
                        msg += "\r\n\t\t\t\t taskList : " + visitFact.getTaskList().get(i).getVisitTaskListID();
                    }
                    logger.warn(String.format(" %s", msg));

                }

            }
            else if (in.getHeader("STATE") == State.NO_SCHEDULES) {

                in.setBody(visitFact);

            }
        }
    }
}
