package com.sandata.lab.rules.exceptions.drools;

import com.sandata.lab.rules.call.model.Schedule;

import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by tom.dornseif on 11/18/2015.
 */
public class BatchExecuters {
    private Logger logger = LoggerFactory.getLogger(BatchExecuters.class);
    private Hashtable<String, BatchExecuter> windows;
    private final static int offset = 24;
    public BatchExecuters() {
        windows = new Hashtable<String, BatchExecuter>();
    }

    public void addCall(VisitEventFact visitEventFact) {
        Date date = new Date();
        BatchExecuter b = getDnisWindowForCall(visitEventFact);
        if(date.after(b.getLastRequestSent())) {
            logger.info("schedulewindow : making update request");
            b.sendUpdateRequestForCall(visitEventFact);

        }
    }

    public BatchExecuter getDnisWindow(String dnis) {
        if(this.windows.containsKey(dnis)) {
            BatchExecuter b = this.windows.get(dnis);
            return b;
        }
        else {
            BatchExecuter b = new BatchExecuter(dnis);
            return b;
        }
    }
    public BatchExecuter getDnisWindowForCall(VisitEventFact call) {
        String dnis = call.getDnis();
        logger.info(String.format("Searching for schedulewindow : %s", dnis));
        if(this.windows.containsKey(dnis)) {
            logger.info(String.format("schedulewindow : found window, : dnis %s", dnis));
            BatchExecuter b = this.windows.get(dnis);
            b.addCall(call);
            return b;
        }
        else {
            BatchExecuter b = new BatchExecuter(dnis, call.getCallInTime(), -offset, offset, call.getStaffID());
            b.addCall(call);
            this.windows.put(dnis, b);
            logger.info(String.format("schedulewindow : returning window, dnis : %s", dnis));
            return b;
        }
    }
    public void cleanWindows(boolean expiredOnly) {
        if(!expiredOnly) {
            this.windows.clear();
        }
        else
        {
            List<String> expiredDnisList = new ArrayList<>();
            Date currentDateTime = new Date();
            for(Map.Entry<String, BatchExecuter> entry : this.windows.entrySet() ) {
                if(entry.getValue().getScheduleEventRequest().getToDate().before(currentDateTime)) {
                    expiredDnisList.add(entry.getKey());
                }
            }
            for(String key : expiredDnisList) {
                this.windows.remove(key);
            }
        }

    }

    public void addSchedule(Schedule body) {
        String dnis = body.getDnis();
        if(this.windows.containsKey(dnis)) {
            BatchExecuter b = this.windows.get(dnis);
            b.addSchedule(body);
        }
        else {

        }
    }

    public Hashtable<String, BatchExecuter> getWindows() {
        return windows;
    }


    public void addVisit(VisitFact visitFact, String dnis) {
        if(this.windows.containsKey(dnis)) {
            BatchExecuter b = this.windows.get(dnis);
            b.addVisit(visitFact);
        }
    }
}
