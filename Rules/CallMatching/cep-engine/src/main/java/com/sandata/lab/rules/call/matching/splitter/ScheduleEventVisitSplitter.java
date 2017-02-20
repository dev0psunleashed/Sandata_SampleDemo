package com.sandata.lab.rules.call.matching.splitter;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom.dornseif on 11/27/2015.
 */
public class ScheduleEventVisitSplitter {
    Logger logger = LoggerFactory.getLogger(ScheduleEventVisitSplitter.class);
    public List<VisitFact> splitVisits(Schedule schedule) {
        List<VisitFact> visits = new ArrayList<VisitFact>();
        List<Visit> dlVisits = schedule.getVisit();
        if( dlVisits != null && dlVisits.size() > 0 ) {
            for (Visit v : dlVisits) {
                VisitFact visitFact = new VisitFact(v);
                visits.add(createFromSchedule(visitFact, schedule));
                logger.info("Response returned visit list:  Added visitFact {}", visitFact);
            }
        }
        else {
            //we need to insert a new visit!
            VisitFact visitFact = new VisitFact();
            visits.add(createFromSchedule(visitFact, schedule));
            logger.info(" No Visit list in response created single visitFact {}", visitFact);
        }


        return visits;
    }

    private final VisitFact createFromSchedule(VisitFact visitFact, Schedule schedule) {
        visitFact.setState(State.AGG_INSERTED_FROM_RESPONSE);
        visitFact.setScheduledFrom(schedule.getStartTime());
        visitFact.setScheduledTo(schedule.getEndTime());
        visitFact.setPatientId(schedule.getPatientId());
        visitFact.setAni(schedule.getCallerIdForPatient());
        return visitFact;
    }
}
