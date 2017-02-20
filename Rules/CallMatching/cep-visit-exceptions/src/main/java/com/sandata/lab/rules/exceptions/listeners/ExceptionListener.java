package com.sandata.lab.rules.exceptions.listeners;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.rules.exceptions.processors.ProducerPojo;
import com.sandata.lab.rules.call.model.*;
import com.sandata.lab.rules.exceptions.routes.VisitExceptionRoute;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import org.kie.internal.SystemEventListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExceptionListener implements RuleRuntimeEventListener {

	private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

	@Produce
	private ProducerTemplate templateOld;

	private ProducerPojo template;
	@Override
	public void objectDeleted(ObjectDeletedEvent ode) {
		String name = "";
		Rule rule = null;
		String msg = " objectDeleted : ";
		if(ode != null) {
			rule = ode.getRule();
			if(rule != null)
				name = rule.getName();
			if(ode.getOldObject() instanceof VisitFact) {
				msg += " was VisitFact Object.";
				VisitFact visit = (VisitFact)ode.getOldObject();
				if(visit != null)
				{   msg += printVisit(visit);
					msg += "Had state of " + visit.getState().name();
				}
			}
			else if(ode.getOldObject() instanceof Schedule) {
				
				msg += " was Schedule Object." ;

			}
			else
			{
				msg += " was unmonitored Object";
			}
			
			sendMsg(msg);
			getVisitExcpLog().info(msg);
		}
		
		
	}

	private void sendMsg(String msg) {
		//getVisitExcpLog().info(msg);
		this.getTemplate().sendBody("mock:CEP_ENGINE_MESSAGES", msg);
	}
	private void sendVisitException(VisitException visitExceptions) {
		this.getTemplate().sendBody("activemq:queue:CEP_VISIT_EXCEPTIONS?jmsMessageType=Object", visitExceptions);
	}
	@Override
	public void objectInserted(ObjectInsertedEvent ode) {
		String msg = " objectInserted : ";
		String name = "";
		Rule rule = null;
		
		if(ode != null) {
			rule = ode.getRule();
			if(rule != null)
				name = rule.getName();
		
			if(ode.getObject() instanceof VisitFact) {
				msg += " was VisitFact Object.";
				msg += printVisit((VisitFact)ode.getObject());
			}
			else if(ode.getObject() instanceof Schedule) {
				msg += " was Schedule Object.";
				Schedule s = (Schedule)ode.getObject();
				msg += printSchedule(s);//create visit
			}
			else
			{
				msg += " was unknown Object.";
			}
			sendMsg(msg);
			getVisitExcpLog().info(msg);
		}
		
	}
	private String printVisit(VisitFact visit) {
		String msg = "";
		try {
			msg += " , VisitFact ani : " + visit.getAni();
			msg += " , VisitFact staffId : " + visit.getStaffId();
			if (visit.getCallIn() != null) {
				msg += " , VisitFact callInTime : " + visit.getCallIn().toString();
			} else {
				msg += " , VisitFact callInTime : (NULL)";
			}
			if (visit.getCallOut() != null) {
				msg += " , VisitFact calloUTTime = " + visit.getCallOut().toString();
			} else {
				msg += " , VisitFact callOutTime : (NULL)";
			}
			sendMsg(msg);
			if (visit.getState() != null && visit.getState().name() != null) {
				msg += "VisitFact state = " + visit.getState().name();
			}

		}
		catch(Exception ex) {
			msg += ex.getLocalizedMessage();
		}
		return msg;
	}

	private String printSchedule(Schedule s) {
		String callin = "";
		String callout = "";
		String msg = "Schedule : ";
		try {
			if(s.getCallIn() != null) {
				callin = s.getCallIn().toString();
			}
			if(s.getCallOut() != null) {
				callout = s.getCallOut().toString();
			}
			if(s.getVisit() != null && s.getVisit().size() > 0 && s.getVisit().get(0) != null && s.getVisit().get(0).getVisitSK() != null) {
				msg += " , VISIT_SK=" + s.getVisit().get(0).getVisitSK().toString() + "\r\n";
			}
			else {
				msg += "VISIT_SK=null ";
			}

			msg += "Start:" + s.getStartTime().toString() + ", CallIn:" + callin + "\r\n";
			msg += "End:" + s.getEndTime().toString() + ", CallOut:" + callout + "\r\n";
			sendMsg(msg);
		}catch(Exception ex) {
			sendMsg(" : error " + ex.getLocalizedMessage() );
			msg += "Exception=" + ex.getLocalizedMessage();
		}
		return msg;
	}
	@Override
	public void objectUpdated(ObjectUpdatedEvent ode) {
		String name = "";
		Rule rule = null;

		String msg = " objectUpdated ";
		if(ode != null) {
			try {
				rule = ode.getRule();
				if(rule != null)
					name = rule.getName();
				
				if(ode.getObject() instanceof VisitFact) {
					msg += " was VisitFact by rule " + name;
					VisitFact visit = (VisitFact)ode.getObject();
					//check for State.LOADED_VSR1 to LOADED_VSR5
					if(visit.getState() == State.LOADED_VSR_1 ||
							visit.getState() == State.LOADED_VSR_2 ||
							visit.getState() == State.LOADED_VSR_3 ||
							visit.getState() == State.LOADED_VSR_4 ||
							visit.getState() == State.LOADED_VSR_5 ||
							visit.getState() == State.LOADED_VR_1 ||
							visit.getState() == State.LOADED_VR_2 ||
							visit.getState() == State.LOADED_VR_3 ||
							visit.getState() == State.LOADED_VR_4 ||
							visit.getState() == State.LOADED_VR_5 ||
							visit.getState() == State.LOADED_VR_6 ||
							visit.getState() == State.LOADED_VR_7 ||
							visit.getState() == State.LOADED_VR_8 ||
							visit.getState() == State.LOADED_VR_9) {
						if(visit.getVisit() != null && visit.getVisit().getVisitSK() != null) {
							msg += " visitSK = " + visit.getVisit().getVisitSK().toString();
							msg += ", ";
						}
						if(visit.getVisit() != null && visit.getVisit().getScheduleEventSK() != null) {
							msg += " scheduleSK = " + visit.getVisit().getScheduleEventSK().toString();
							msg += ", ";
						}
						msg += "Just incrementing state for next check in rules engine before retracting visit completely!";
						msg += visit.getState().getVisitExceptionName();
					}
					else {

						if (visit.getCallIn() == null && visit.getCallOut() == null &&
								(visit.getState() == State.MISSING_TASKS || visit.getState() == State.MISSING_CRITICAL_TASKS ||
										visit.getState() == State.VISIT_NO_SHOW)) {
							VisitException visitException = new VisitException();
							visitException.setVisitSK(visit.getVisit().getVisitSK());
							visitException.setExceptionID(visit.getState().getVisitException());
							getVisitExcpLog().info("Writing a visit exception for a visit without a callin and callout");
							sendVisitException(visitException);
						} else if (visit.getCallIn() != null || visit.getCallOut() != null) {
							VisitException visitException = new VisitException();
							visitException.setVisitSK(visit.getVisit().getVisitSK());
							visitException.setExceptionID(visit.getState().getVisitException());
							getVisitExcpLog().info("Writing a visit exception for a visit with a callin and/or callout");
							msg += visit.getState().getVisitExceptionName();
							sendVisitException(visitException);
						}
					}
				}
				else
				{
					msg += " was unknown by rule " + name;
				}
				sendMsg(msg);
			}catch(Exception ex) {
				sendMsg(" : error " + ex.getLocalizedMessage());
				msg += "Exception=" + ex.getLocalizedMessage();
			}
			
		}
		else {
			msg += " ObjectUpdatedEvent=null";
		}
		getVisitExcpLog().info(msg);
		
	}

	private VisitFact updateVisitWithCall(VisitFact visitFact, boolean isCallin, VisitEventFact visitFactNewCall) {

		if(visitFactNewCall != null ) {
			if(	isCallin ) {
				visitFact.setCallIn(visitFactNewCall.getCallInTime());
				visitFact.setCallCallIn(visitFactNewCall);
			}
			else {
				visitFact.setCallOut(visitFactNewCall.getCallInTime());
				visitFact.setCallCallOut(visitFactNewCall);
			}
			if(visitFactNewCall.getTasks() != null) {
				visitFact.addTaskList(visitFactNewCall.getTasks());
			}
			visitFact.getVisit().getVisitEvent().add(visitFactNewCall.getVisit());
		}


		return visitFact;
	}


	private VisitFact getTheVisitFromTheScheduleThatMatchesTheVisitEvent(Schedule updated, VisitEventFact visitFactNewCallIn) {
		//VisitFact vF;
		//simple for now.

		return new VisitFact(updated.getVisit().get(updated.getVisit().size()-1));
	}

	private VisitFact createVisit(boolean isCallin, VisitEventFact visitFactNewCall, Schedule schedule) {
		VisitFact visitFact = new VisitFact();

		visitFact = updateVisitWithCall(visitFact, isCallin, visitFactNewCall);
		visitFact.setDnis(schedule.getDnis());
		visitFact.getVisit().setStaffID(schedule.getScheduleEventExt().getStaffID());
		visitFact.getVisit().setScheduleEventSK(schedule.getScheduleEventExt().getScheduleEventSK());
		visitFact.getVisit().setPatientID(schedule.getScheduleEventExt().getPatientID());

		return visitFact;
	}


	public ProducerPojo getTemplate() {
		if(template == null) {
			this.template = new ProducerPojo();

		}
		return this.template;
	}

	public void setTemplate(ProducerPojo template) {
		if(template == null)
			this.template = new ProducerPojo();
		else
			this.template = template;
	}

	public Logger getVisitExcpLog() {
		if(this.visitExcpLog != null) {
			return this.visitExcpLog;
		}
		else {
			this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
			return this.visitExcpLog;
		}
	}

	public void setVisitExcpLog(Logger visitExcpLog) {
		this.visitExcpLog = visitExcpLog;
	}
}
