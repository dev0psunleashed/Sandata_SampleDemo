package com.sandata.lab.rules.call.matching.drools;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.rules.call.matching.processors.ProducerPojo;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.ScheduleEventRequestExt;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.*;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;

import com.sandata.lab.rules.call.model.VisitEventFact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchExecuter {

    private Logger logger = LoggerFactory.getLogger(BatchExecuter.class);

    @Produce
    public static ProducerTemplate scheduleEventRequester;
    private String dNIS;
    private Date lastRequestSent;
	private BatchExecutionCommandImpl command;
    private List<GenericCommand<?>> commandList;
    private int initialSize;
    private ScheduleEventRequestExt scheduleEventRequest = null;
    public BatchExecuter () {}
    public BatchExecuter (String dnis) {
        /*  these are the defaults so no need to send
        String staff = null;
        Date center = new Date();//now
        int offsetPast = -24;
        int offsetFuture = 24;*/
        this.lastRequestSent = new Date(0L);
        this.dNIS = dnis;
        this.setScheduleEventRequest(this.createScheduleEventRequest(dnis));
        this.send(this.getScheduleEventRequest());
        reset();
    }
    public BatchExecuter (String dnis, Date center,
           int offsetPast, int offsetFuture, String staffId) {
        this.lastRequestSent = new Date(0L);
        this.dNIS = dnis;
        logger.info(String.format("cep-engine : BatchExecuter, schedulewindow : constructor, dnis : %s", dnis));
        if(center == null) {
            logger.info("=========================  eventTimestamp was null!! ================== we should report/throw an exception it can not happen");
            center = new Date();
        }
        this.setScheduleEventRequest(
                this.createScheduleEventRequest(
                        dnis, staffId, offsetPast, offsetFuture,
                        center));
        logger.info(String.format("cep-engine : BatchExecuter, schedulewindow : sending 1st request, dnis : %s", dnis));
        this.send(this.getScheduleEventRequest());

        reset();
    }
    private void send(ScheduleEventRequestExt scheduleEventRequest) {

        if(scheduleEventRequester != null) {
            scheduleEventRequester.sendBody("activemq:queue:SCHEDULE_EVENT_REQUEST", (com.sandata.lab.data.model.request.visit.ScheduleEventRequest)scheduleEventRequest.getScheduleRequest());
            this.lastRequestSent = new Date();
        }
        else {
            logger.info("==============================================");
            logger.info("==============================================");
            logger.info("     scheduleEventRequester was null!!!!!!!!!       ");
            logger.info("==============================================");

        }

    }
    public void sentExecuteCommand() {

       this.lastRequestSent = new Date();
       //TBD SHOULD KEEP SCHEDULES
       reset();
    }
    public void sendUpdateRequestForCall(VisitEventFact visitEvent) {
        this.setScheduleEventRequest(
                this.createScheduleEventRequest(
                        visitEvent.getDnis(), visitEvent.getStaffID(), -24, 24,
                        visitEvent.getCallInTime()));
        this.addCall(visitEvent);

        logger.info(String.format("cep-engine : BatchExecuter, schedulewindow : sending update request, dnis : %s", visitEvent.getDnis()));
        this.send(this.getScheduleEventRequest());
        this.lastRequestSent = new Date();
    }
    private ScheduleEventRequestExt createScheduleEventRequest(String dnis) {
        ScheduleEventRequestExt seRequest = new ScheduleEventRequestExt(dnis);

        return seRequest;

    }

    private ScheduleEventRequestExt createScheduleEventRequest(String dnis, String staffId, int offsetP, int offsetF, Date center) {
        ScheduleEventRequestExt seRequest = new ScheduleEventRequestExt(dnis, center, offsetP, offsetF, staffId);
        logger.info(String.format("cep-engine : BatchExecuter.createScheduleEventRequestExt(), schedulewindow : preparing request, dnis : %s", dnis));

        return seRequest;

    }
    private void reset() {
    	command = new BatchExecutionCommandImpl();
    	this.setCommandList(command.getCommands());
    	initialSize = this.commandList.size();
    }
    public void addSchedule(Schedule schedule)
    {
        String uid = UUID.randomUUID().toString();
    	this.getCommandList().add(new InsertObjectCommand(schedule, uid));
    }
    
    public BatchExecutionCommandImpl execute() {
    	this.getCommandList().add(new FireAllRulesCommand());
    	return this.command;
    }
    //logger.info("count of commands was " + Integer.toString(commands.size()));
    //commands.add(new InsertObjectCommand(body, "obj1"));
    //commands.add(new FireAllRulesCommand());

	private List<GenericCommand<?>> getCommandList() {
		return commandList;
	}

	private void setCommandList(final List<GenericCommand<?>> commandList) {
		this.commandList = commandList;
	}

	public int getSizeOfCommandList() {
		int currSize = this.commandList.size();
		return currSize - initialSize;
	}

	public void addCall(VisitEventFact call) {
		getCommandList().add(new InsertObjectCommand(call, Long.toString(call.getCallInTime().getTime())));
        ProducerPojo producerPojo = new ProducerPojo();
        producerPojo.sendBody("activemq:queue:VISIT_EVENTS", call);
	}

	public void clear() {
		reset();
	}

    public ScheduleEventRequestExt getScheduleEventRequest() {
        return this.scheduleEventRequest;
    }

    public void setScheduleEventRequest(ScheduleEventRequestExt scheduleEventRequest) {
        this.scheduleEventRequest = scheduleEventRequest;
    }


    public void setScheduleEventRequester(ProducerTemplate scheduleEventRequester) {
        this.scheduleEventRequester = scheduleEventRequester;
    }

    public Date getLastRequestSent() {
        return lastRequestSent;
    }

    public void setLastRequestSent(Date lastRequestSent) {
        this.lastRequestSent = lastRequestSent;
    }

    public void addVisit(VisitFact visitFact) {
        String id = UUID.randomUUID().toString();
        if(visitFact.getVisit() != null && visitFact.getVisit().getVisitSK() != null) {
            id = String.valueOf(visitFact.getVisit().getVisitSK().longValue());
        }
        this.getCommandList().add(new InsertObjectCommand(visitFact, id));
    }
}
