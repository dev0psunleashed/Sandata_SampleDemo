package com.sandata.lab.dl.vv.test.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.constants.filter.Filter;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.dl.vv.api.VisitEventDataService;
import com.sandata.lab.dl.vv.test.utils.log.VisitEventRepoTestLogger;
import org.apache.camel.Exchange;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class VisitEventRepositoryTest {

    private VisitEventDataService visitEventDataService;

    public void testHandleCreateVisitResponse(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {

            Object response = exchange.getIn().getBody();

            if (response == null) {
                throw new SandataRuntimeException("testHandleCreateVisitResponse: request == null");
            }

            if (!(response instanceof Visit)) {
                throw new SandataRuntimeException("request is not an instance of Visit");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public void testHandleSchedEvntResponse(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {

            Object response = exchange.getIn().getBody();

            if (response == null) {
               throw new SandataRuntimeException("testHandleSchedEvntResponse: request == null");
            }

            if (!(response instanceof ScheduleEventResponse)) {
                throw new SandataRuntimeException("request is not an instance of ScheduleEventResponse");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public void testSchedEvntRequest(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            ScheduleEventRequest scheduleEventRequest = new ScheduleEventRequest();
            scheduleEventRequest.setStaffId("365090666");
            scheduleEventRequest.setFromDate(dateFormat.parse("2015-12-21"));
            scheduleEventRequest.setToDate(dateFormat.parse("2016-01-31"));
            scheduleEventRequest.setDnis("8776089781");

            exchange.getIn().setBody(scheduleEventRequest);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public void testHandlePatientStaffResponse(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {

            Object response = exchange.getIn().getBody();

            if (response == null) {
                throw new SandataRuntimeException("testHandlePatientStaffResponse: request == null");
            }

            if (!(response instanceof PatientStaffResponse)) {
                throw new SandataRuntimeException("request is not an instance of PatientStaffResponse");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public void testPatientStaffEqualsRequest(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {
            PatientStaffRequest patientStaffRequest = new PatientStaffRequest();
            patientStaffRequest.setStaffId("000848253");
            patientStaffRequest.setPatientId("20151105114440416");
            patientStaffRequest.setDnis("8005551212");
            patientStaffRequest.setFilter(Filter.OPTIONS.EQUALS);

            exchange.getIn().setBody(patientStaffRequest);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public void testCreateVisitRequest(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepoTestLogger.CreateLogger(exchange);

        logger.start();

        try {

            Visit visit = new Visit();
            visit.setVisitID(UUID.randomUUID().toString());
            visit.setScheduleEventSK(BigInteger.valueOf(38));

            VisitEvent callIn = new VisitEvent();
            callIn.setDialedNumberIdentificationService("8002129988");
            callIn.setTimezoneName("US/Eastern");
            callIn.setVisitEventDatetime(new Date());

            VisitEvent callOut = new VisitEvent();
            callOut.setDialedNumberIdentificationService("8002129988");
            callOut.setTimezoneName("US/Eastern");
            callOut.setVisitEventDatetime(new Date());

            visit.getVisitEvent().add(callIn);
            visit.getVisitEvent().add(callOut);

            VisitTaskList bathTask = new VisitTaskList();
            bathTask.setVisitTaskListID("0015");
            VisitTaskListExt bathTaskExt = new VisitTaskListExt(bathTask);

            VisitTaskList shampooTask = new VisitTaskList();
            shampooTask.setVisitTaskListID("0021");
            VisitTaskListExt shampooTaskExt = new VisitTaskListExt(shampooTask);

            visit.getVisitTaskList().add(bathTaskExt);
            visit.getVisitTaskList().add(shampooTaskExt);

            exchange.getIn().setBody(visit);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.stop();
    }

    public VisitEventDataService getVisitEventDataService() {
        return visitEventDataService;
    }

    public void setVisitEventDataService(VisitEventDataService visitEventDataService) {
        this.visitEventDataService = visitEventDataService;
    }
}
