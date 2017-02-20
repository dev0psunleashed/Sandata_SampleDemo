package com.sandata.lab.rules.exceptions.aggregation;

import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitAndScheduleType;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/16/2016
 * Time: 5:21 PM
 */
public class StartVisitAndSchedulePredicate implements Predicate {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    @Override
    public boolean matches(Exchange exchange) {
        //could eventually get a visit a visit event and a schedule
        getVisitExcpLog().info(" IN StartVisitAndSchedulePredicate ");
        if (exchange.getIn().getBody() instanceof ArrayList) {
            ArrayList list = exchange.getIn().getBody(ArrayList.class);
            if (list.size() > 1 && exchange.getIn().getHeader("STATE") != null && ((String) exchange.getIn().getHeader("STATE")).equals(State.AGG_WAITING_FOR_RESPONSE.name())) {
                boolean removedSchedule = false;
                int schedules = 0;
                int visits = 0;
                getVisitExcpLog().info("==============================================================");
                getVisitExcpLog().info(" StartVisitPredicate : size was greater than 1 checking list ");
                getVisitExcpLog().info("==============================================================");
                Iterator i = list.iterator();
                Date to = null;
                Date from = null;
                while(i.hasNext()) {
                    Object o = i.next();
                    if(o instanceof VisitAndSchedule) {
                        VisitAndSchedule vAndS = (VisitAndSchedule) o;
                        if(vAndS.getType() == VisitAndScheduleType.SCHEDULE && schedules == 0) {
                            schedules = 1;
                            from = vAndS.getSchedule().getStartTime();
                            to = vAndS.getSchedule().getEndTime();
                            if(from != null && to != null) {
                                getVisitExcpLog().info(String.format("StartVisitAndSchedulePredicate Found schedule in match with from=%s and to=%s", from.toString(), to.toString()));
                            }
                        }
                        else if(vAndS.getType() == VisitAndScheduleType.SCHEDULE && schedules > 0) {
                            Date from2 = vAndS.getSchedule().getStartTime();
                            Date to2 = vAndS.getSchedule().getEndTime();
                            if(from != null && to != null) {
                                getVisitExcpLog().info(String.format("StartVisitAndSchedulePredicate Found second schedule in match with from=%s and to=%s, check against first, removing this one", from2.toString(), to2.toString()));
                            }
                            else {
                                getVisitExcpLog().info("found additional invalid schedule removing.");
                            }

                            i.remove();
                            removedSchedule = true;
                        }
                        else if (vAndS.getType() == VisitAndScheduleType.VISIT) {
                            getVisitExcpLog().info("StartVisitAndSchedulePredicate Found visit");
                            visits += 1;
                        }
                    }
                }
                exchange.getIn().setBody(list, ArrayList.class);
                if(schedules > 0 && visits > 0) {
                    return true;
                }
                else {
                    getVisitExcpLog().info("ERROR::::StartVisitAndSchedulePredicate List was greater than 2 but incorrect number of matching visit to schedules should be 1 to 1.");

                    return false;
                }
            }
            if (exchange.getIn().getHeader("STATE") != null && ((String) exchange.getIn().getHeader("STATE")).equals(State.AGG_WAITING_FOR_RESPONSE.name())) {
                getVisitExcpLog().info("header STATE was " + (String) exchange.getIn().getHeader("STATE"));
                getVisitExcpLog().info("waiting for vist from datatabase");
            }

        }
        getVisitExcpLog().info(" IN StartVisitPredicate : returning false");
        return false;
    }

    public Logger getVisitExcpLog() {
        if (this.visitExcpLog != null) {
            return this.visitExcpLog;
        } else {
            this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
            return this.visitExcpLog;
        }
    }

    public void setVisitExcpLog(Logger visitExcpLog) {
        this.visitExcpLog = visitExcpLog;
    }
}