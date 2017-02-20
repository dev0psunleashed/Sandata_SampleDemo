package com.sandata.lab.rules.call.matching.service.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.model.VisitState;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Utility class for call matching service.</p>
 *
 * @author jasonscott
 */
public class CallMatchingUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallMatchingUtil.class);

    public static void calculateLowerAndUpperDateTime(Exchange exchange) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lowerLimitDate = null;
        Date upperLimitDate = null;
        String lowerLimitDateString = null;
        String upperLimitDateString = null;

        try {
            lowerLimitDate = dateFormat.parse("9999-12-31 23:59:59");
            upperLimitDate = dateFormat.parse("0001-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("{}.calculateLowerAndUpperDateTime: {}", CallMatchingUtil.class.getSimpleName(), e.getMessage());
        }

        List<VisitEventFact> visitEventFactList = (List<VisitEventFact>) exchange.getIn().getBody();

        if (visitEventFactList == null) {
            String errorMessage = "VisitEventFact List is NULL!";
            LOGGER.error("{}.calculateLowerAndUpperDateTime: {}", CallMatchingUtil.class.getSimpleName(), errorMessage);
            throw new SandataRuntimeException(errorMessage);
        }

        if (lowerLimitDate != null
            && upperLimitDate != null) {
            if (visitEventFactList.size() == 1) {

                lowerLimitDate = visitEventFactList.get(0).getVisitEventDatetime();
                upperLimitDate = lowerLimitDate;

            } else {
                for (VisitEventFact visitEventFact : visitEventFactList) {
                    Date currentDate = visitEventFact.getVisitEventDatetime();
                    if (currentDate!= null && currentDate.before(lowerLimitDate)) {
                        lowerLimitDate = currentDate;
                    }

                    if (currentDate!= null && currentDate.after(upperLimitDate)) {
                        upperLimitDate = currentDate;
                    }
                }
            }

            lowerLimitDateString = dateFormat.format(lowerLimitDate);
            upperLimitDateString = dateFormat.format(upperLimitDate);

        }

        exchange.getIn().setHeader("lowerLimitDateString", lowerLimitDateString);
        exchange.getIn().setHeader("upperLimitDateString", upperLimitDateString);
    }
    
    /**
     * 
     * @param visitEventFact CallInfo from call server
     * @param scheduleEventList ScheduleEvent found from sql query by BE_ID, from date and to date within one hour
     * @return VisitEventFact 
     */
    public static VisitEventFact findAndMatchSchedule (VisitEventFact visitEventFact , List<ScheduleEvent> scheduleEventList) {
    	
    	for (ScheduleEvent scheduleEvent : scheduleEventList) {
			if(scheduleEvent.getStaffID() != null && scheduleEvent.getStaffID().equals(visitEventFact.getStaffID())
					&& scheduleEvent.getPatientID() != null && scheduleEvent.getPatientID().equals(visitEventFact.getPatientID())
					&& scheduleEvent.getScheduleEventStartDatetime().before(visitEventFact.getVisitEventDatetime())
					&& scheduleEvent.getScheduleEventEndDatetime().after(visitEventFact.getVisitEventDatetime())) {
					//TODO: need to get PatientHOme phone to compare to ANI , need modify the sql query to get schedule event info
				
				//found schedule matched , then udpate VisitEventFact
				visitEventFact.setStaffID(scheduleEvent.getStaffID());
				visitEventFact.setPatientID(scheduleEvent.getPatientID());
				visitEventFact.setScheduleId(scheduleEvent.getScheduleEventID());
				
				//mark as planned visit
				visitEventFact.setVisitState(VisitState.MATCHED);
			}
		}
    	
    	return null;
    }

    /**
     *
     * @param exchange List of Visit event after schedule matching process
     */
    public static void extractStaffIds (final Exchange exchange) {

        List<VisitEventFact>  visitEventFacts = ( List<VisitEventFact> ) exchange.getIn().getBody();
        List<String> staffIds = new ArrayList<String>();
        if(visitEventFacts != null ) {
            for (VisitEventFact visit : visitEventFacts ) {
                if(visit.getStaffID() != null && !visit.getStaffID().isEmpty()) {
                    staffIds.add(visit.getStaffID());
                }
            }
        }
        //put the staff id list to the header
        //exchange.getIn().setHeader("staffIds", CommonUtils.convertListToStringParams(staffIds));
        String result = CommonUtils.buildStaffIdLikeConditions(staffIds);
        exchange.getIn().setHeader("staffIds", result);
    }

    /**
     *
     * @param exchange List of Visit event after schedule matching process
     */
    public static void extractPatientIdAndANI (final Exchange exchange) {

        List<VisitEventFact>  visitEventFacts = ( List<VisitEventFact> ) exchange.getIn().getBody();
        List<String> patientIds = new ArrayList<String>();
        List<String> aniList = new ArrayList<String>();
        if(visitEventFacts != null ) {
            for (VisitEventFact visit : visitEventFacts ) {
                if(visit.getPatientID() != null && !visit.getPatientID().isEmpty()) {
                    patientIds.add(visit.getPatientID());
                }

                if(visit.getAni() != null && !visit.getAni().isEmpty()) {
                    aniList.add(visit.getAni());
                }
            }
        }
        //put the staff id list to the header
        exchange.getIn().setHeader("patientIds", CommonUtils.convertListToStringParams(patientIds));
        exchange.getIn().setHeader("aniList", CommonUtils.convertListToStringParams(aniList));
    }




}
