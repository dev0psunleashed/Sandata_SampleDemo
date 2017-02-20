package com.sandata.lab.rules.visit.exception.service.utils;

import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.rules.vv.fact.PatientFact;
import com.sandata.lab.rules.vv.fact.ScheduleEventFact;
import com.sandata.lab.rules.vv.fact.StaffFact;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.model.VisitState;

import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by thanhxle on 11/3/2016.
 */
public class VisitExceptionUtils {

    /**
     *
     * @param exchange Visit from UI (manual edit)
     */
    public static void convertVisitToVisitEventFact (final Exchange exchange) {

        //assume that visit from Visit manually update includes visit event
        //visit from sched rest, nothing but only visit info, may recalculated by JASON'quartz route
        VisitEventFact visitEventFact = new VisitEventFact();
        Visit visit = exchange.getIn().getBody(Visit.class);

        //set ANI and schedule event
        if(visit.getVisitEvent() != null && visit.getVisitEvent().size() > 0 && visit.getVisitEvent().get(0) != null
                && ((VisitEvent)visit.getVisitEvent().get(0)).getAutomaticNumberIdentification() != null) {
            visitEventFact.setAni(((VisitEvent)visit.getVisitEvent().get(0)).getAutomaticNumberIdentification());
            visitEventFact.setVisit(visit.getVisitEvent().get(0));
        }

        visitEventFact.setBusinessEntityId(visit.getBusinessEntityID());
        //determine if planned visit or not
        if (visit.getScheduleEventSK() != null) {
            ScheduleEventFact scheduleEventFact = new ScheduleEventFact();
            //scheduleEventFact.set
            scheduleEventFact.setScheduleEventSK(visit.getScheduleEventSK());
            visitEventFact.setScheduleEventFact(scheduleEventFact);
        }

        //visit sk
        visitEventFact.setVisitSK(visit.getVisitSK());

        //visitEventFact.getVisit()
        visitEventFact.setParentVisit(visit);

        //staff fact
        if (visit.getStaffID() != null) {
            visitEventFact.setStaffID(visit.getStaffID());
        }

        if (visit.getPatientID() != null) {
            visitEventFact.setPatientID(visit.getPatientID());
        }

        // manual calls from UI, with task or not ?
        if (visit.getVisitTaskList() != null) {
            visitEventFact.getTasks().addAll(visit.getVisitTaskList());
        }

        exchange.getIn().setBody(visitEventFact);
        //set BE_ID to header
        exchange.getIn().setHeader("bsnEntId",visit.getBusinessEntityID());
    }

    public static void moveExceptionLookupMapToBodyList(final Exchange exchange) {
        Map<String, ExceptionLookup> exceptionLookupMap = (Map<String, ExceptionLookup>) exchange.getIn().getHeader("exceptionLookupList");
        List body = (List) exchange.getIn().getBody();
        if (body != null
            && exceptionLookupMap != null) {
            body.add(exceptionLookupMap);
        }
    }

    public void setApplicableExcpToVisitfact(final Exchange exchange) {
    	
    	List<BusinessEntityExceptionListExt> beExceptionListExts = new ArrayList<BusinessEntityExceptionListExt>();
        List<ContractExceptionListExt> contrExceptionListExts = new ArrayList<ContractExceptionListExt>();

        VisitFact visitFact = null;
        List objects = exchange.getIn().getBody(List.class);
        for (Object object : objects) {
            if (object instanceof VisitFact) {
                visitFact = (VisitFact) object;
                break;
            }
        }

        if (null == visitFact) {
            return;
        }

        for (Object object : objects) {
            if (object instanceof BusinessEntityExceptionListExt) {
                beExceptionListExts.add((BusinessEntityExceptionListExt)object);
            } else if (object instanceof ContractExceptionListExt) {
                contrExceptionListExts.add((ContractExceptionListExt)object);
            }
        }

        visitFact.setApplicableBeExceptionList(beExceptionListExts);
        visitFact.setApplicableContractExceptionList(contrExceptionListExts);
    	
    }
}
