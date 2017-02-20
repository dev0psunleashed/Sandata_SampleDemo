package com.sandata.lab.rules.call.matching.listeners;

import com.sandata.lab.rules.call.matching.processors.ProducerPojo;
import com.sandata.lab.rules.call.model.*;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CEPWML implements RuleRuntimeEventListener {

	Logger logger = LoggerFactory.getLogger(CEPWML.class);
	@Produce
	private ProducerTemplate templateOld;

	private Logger rulesLog = LoggerFactory.getLogger("rulesLog");

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
			
			if(ode.getOldObject() instanceof VisitEventFact) {
				msg += " was VisitEventFact Object.";


				VisitEventFact call = (VisitEventFact)ode.getOldObject();
				if(call != null)
				{
					String ani = call.getAni();
					if(ani == null) {
						ani = "(null)";
					}
					String dnis = call.getDnis();
					if(dnis == null) {
						dnis = "(null)";
					}
					String ptId = call.getPatientID();
					if(ptId == null) {
						ptId = "(null)";
					}
					Date d = call.getCallInTime();
					String visitEventDataTime = "(null)";
					if(d != null) {
						visitEventDataTime = d.toString();
					}
					msg += " : ";
					msg += String.format("ANI=%s , DNIS=%s , PT_ID=%s , VISIT_EVNT_DATE_TIME=%s", ani, dnis, ptId, visitEventDataTime);
					if(call.getState() == State.EXTRANEOUS)
					{
						sendMsg(" : writing extraneous calls to extraneous calls queue ");
						getRulesLog().info(String.format("EXTRANEOUS CALL RETRACTED FROM RULES.  %s", msg));
						this.getTemplate().sendBody("activemq:queue:RETRACTED_CALLS?jmsMessageType=Object", (VisitEventFact)ode.getOldObject());
					}
					else {
						sendMsg(" : writing retracted calls to retracted calls queue ");
						getRulesLog().info(String.format("REGULAR CALL RETRACTED FROM RULES.  %s", msg));
						this.getTemplate().sendBody("activemq:queue:RETRACTED_CALLS?jmsMessageType=Object", (VisitEventFact)ode.getOldObject());
					}
				}
			}
			else if(ode.getOldObject() instanceof Schedule) {
				
				msg += " was Schedule Object." ;
				msg += printSchedule((Schedule)ode.getOldObject());
				getRulesLog().info(String.format("RETRACTED SCHEDULE FROM RULES: %s", msg));
				sendMsg(" schedule was retracted but we dont update a schedule unless it is the callin callout time associated with it");
				//so we might need to check what changed here or make sure we update() first before retraction better
				//template.sendBatchExecuteCommand("activemq:queue:CEP_ENGINE_RETRACTED_SCHEDULES", (Schedule)ode.getOldObject());
			}
			else
			{
				msg += " was unmonitored Object";
			}
			
			sendMsg(msg);
		}
		
		
	}

	private void sendMsg(String msg) {
		logger.info(msg);
		this.getTemplate().sendBody("mock:CEP_ENGINE_MESSAGES", msg);
	}
	private void sendVisit(VisitFact visit) {
		this.getTemplate().sendBody("activemq:queue:CEP_ENGINE_VISITS?jmsMessageType=Object", visit);
	}
	@Override
	public void objectInserted(ObjectInsertedEvent ode) {
		String msg = " objectInserted : ";
		String name = "";
		Rule rule = null;

		getRulesLog().info("objectInserted");

		if(ode != null) {
			rule = ode.getRule();
			if(rule != null)
				name = rule.getName();
		
			if(ode.getObject() instanceof VisitEventFact) {
				msg += " was VisitEventFact Object.";
				msg += printCall((VisitEventFact)ode.getObject());
			}
			else if(ode.getObject() instanceof Schedule) {
				
				msg += " was Schedule Object.";
				Schedule s = (Schedule)ode.getObject();
				
				//if(s.getState()!= State.LOADED)
					msg += printSchedule(s);//create visit
			}
			else if (ode.getObject() instanceof OraStaffFact) {
				OraStaffFact staffFact = (OraStaffFact)ode.getObject();
				if(staffFact.getState() == State.AGG_INSERTED_FROM_RESPONSE) {
					sendMsg("OraStaffFact with STATE == AGG_INSERTED_FROM_RESPONSE");
				}
				msg += " was OraStaffFact Object with State = " + staffFact.getState().name();

			}
			else if (ode.getObject() instanceof StaffFact) {
				StaffFact staffFact = (StaffFact)ode.getObject();
				if(staffFact.getState() == State.LOADED ) {
					sendMsg("StaffFact with STATE == LOADED and StaffIdFingered = " + staffFact.getStaffIdFingered());
				}
				msg += " was StaffFact Object with State = " + staffFact.getState().name();

			}
			else if(ode.getObject() instanceof UnplannedVisit) {
				msg += " was UnplannedVisit Object.";
				//We need to write this unplanned visit to data layer!!
				sendMsg(" writing unplanned visit to unplanned visit queue ");
				this.getTemplate().sendBody("activemq:queue:CEP_ENGINE_INSERTED_UNPLANNED_VISIT", (UnplannedVisit)ode.getObject());
			}
			else
			{
				msg += " was unknown Object.";
			}
			sendMsg(msg);
			getRulesLog().info(msg);
		}
		
	}
	private String printCall(VisitEventFact call) {

		String msg = "VisitEventFact ani=" + call.getAni();
		msg += ", VisitEventFact staffId=";
		msg += call.getStaffID();
		msg += ", VisitEventFact callInTime : ";
		msg += call.getCallInTime().toString();
		msg += ", ";
		sendMsg(msg);
		return msg;
	}

	private String printSchedule(Schedule s) {
		String callin = "";
		String callout = "";
		String msg = "Schedule:";
		try {
			if(s.getVisit() != null && s.getVisit().size() > 0 && s.getVisit().get(0) != null) {
				msg += "Staff_ID=";
				msg += s.getVisit().get(0).getStaffID();
				msg += " , ";

				if (s.getVisit().get(0).getPatientID() != null) {
					msg += "PatientID=";
					msg += s.getVisit().get(0).getPatientID();
					msg += " , ";

				}
				if (s.getVisit().get(0).getVisitSK() != null) {
					msg += "VisitSK=";
					msg += s.getVisit().get(0).getVisitSK().toString();
					msg += " , ";

				}
				if (s.getVisit().get(0).getVisitEvent() != null && s.getVisit().get(0).getVisitEvent().size() > 0 && s.getVisit().get(0).getVisitEvent().get(0) != null) {
					msg += "ANI=";
					msg += s.getVisit().get(0).getVisitEvent().get(0).getAutomaticNumberIdentification();
					msg += " , ";
					if (s.getVisit().get(0).getVisitEvent().get(0).getVisitEventDatetime() != null) {
					    msg += "VisitEventDateTime=";
						msg += s.getVisit().get(0).getVisitEvent().get(0).getVisitEventDatetime().toString();
						msg += " , ";

					}
				}

			}
			if(s.getCallIn() != null)
				callin = s.getCallIn().toString();
			if(s.getCallOut() != null)
				callout = s.getCallOut().toString();
			msg += "Start:" + s.getStartTime().toString() + ", CallIn:" + callin + "\r\n";
			msg += "End:" + s.getEndTime().toString() + ", CallOut:" + callout + "\r\n";
			sendMsg(msg);
		}catch(Exception ex) {
			msg = ex.getLocalizedMessage();
			sendMsg(" : error " + ex.getLocalizedMessage() );
		}
		return msg;

	}
	@Override
	public void objectUpdated(ObjectUpdatedEvent ode) {
		String name = "name=\"wasnt passed\"";
		Rule rule = null;
		String msg = " objectUpdated ";
		if(ode != null) {
			try {
				rule = ode.getRule();
				if(rule != null) {
					name = String.format("name=\"%s\"", rule.getName());
				}
				
				if(ode.getObject() instanceof VisitEventFact) {
					msg += " was VisitEventFact : " + name;
					VisitEventFact vef = (VisitEventFact)ode.getObject();
					if(vef.getState() == State.MATCHED) {
						msg += printCall(vef);
						sendMsg("VisitEventFact set to matched but should not be updated. " + msg);
					}
				}
				else if(ode.getObject() instanceof Schedule) {

					msg += " was a Schedule : " + name;


					Schedule s = (Schedule)ode.getObject();//new object

					msg += printSchedule(s);
					if(s.getState() == State.MATCHED) {
						sendMsg("This Schedule matched a visitEventFact schedule info : " + msg);
					}
					if(s.getState() == State.CALL_IN_INSERTED) {
							sendMsg("CALL_IN_INSERTED WAS CAUGHT BY LISTENER");
						VisitFact vf = createVisit(true, s.getCallCallIn(), s);
						sendVisit(vf);
					}
					else if(s.getState() == State.CALL_OUT_INSERTED) {
						sendMsg("CALL_OUT_INSERTED WAS CAUGHT BY LISTENER");
						VisitFact vf = createVisit(false, s.getCallCallOut(), s);
						sendVisit(vf);
					}
					else if(s.getState()==State.BOTH_INSERTED) {
						sendMsg("BOTH_INSERTED WAS CAUGHT BY LISTENER");
						VisitFact vf = createVisit(true, s.getCallCallIn(), s);
						vf = updateVisitWithCall(vf, false, s.getCallCallOut());
						sendVisit(vf);
					}

				}
				else if (ode.getObject() instanceof OraStaffFact) {
					OraStaffFact staffFact = (OraStaffFact)ode.getObject();
					msg += " was a OraStaffFact by rule " + name + " with State = " + staffFact.getState().name();

					if(staffFact.getState() == State.STAFF_MATCHED_9_OF_9 ) {
						sendMsg("OraStaffFact with STATE == STAFF_MATCHED_9_OF_9");
					}
					else if(staffFact.getState() == State.STAFF_MATCHED_7_OF_9 ) {
						sendMsg("OraStaffFact with STATE == STAFF_MATCHED_7_OF_9");
					}
					else if(staffFact.getState() == State.STAFF_MATCHED_6_OF_9 ) {
						sendMsg("OraStaffFact with STATE == STAFF_MATCHED_6_OF_9");
					}
					else if(staffFact.getState() == State.FAILED_9_OF_9_MATCH) {
						sendMsg("OraStaffFact with STATE == FAILED_9_OF_9_MATCH");
					}
					else if(staffFact.getState() == State.FAILED_7_OF_9_MATCH ) {
						sendMsg("OraStaffFact with STATE == FAILED_7_OF_9");
					}
					else if(staffFact.getState() == State.FAILED_6_OF_9_MATCH) {
						sendMsg("OraStaffFact with STATE == FAILED_6_OF_9");
					}
				}
				else if (ode.getObject() instanceof StaffFact) {
					StaffFact staffFact = (StaffFact)ode.getObject();
					msg += " was a StaffFact by rule " + name;
					if(staffFact.getState() == State.STAFF_MATCHED_9_OF_9 ) {
						sendMsg("StaffFact with STATE == STAFF_MATCHED_9_OF_9");
					}
					else if(staffFact.getState() == State.STAFF_MATCHED_7_OF_9 ) {
						sendMsg("StaffFact with STATE == STAFF_MATCHED_7_OF_9");
					}
					else if(staffFact.getState() == State.FAILED_9_OF_9_MATCH) {
						sendMsg("StaffFact with STATE == STAFF_MATCHED_9_OF_9");
					}
					else if(staffFact.getState() == State.FAILED_7_OF_9_MATCH ) {
						sendMsg("StaffFact with STATE == FAILED_7_OF_9_MATCH");
					}
					else if(staffFact.getState() == State.STAFF_MATCHED_6_OF_9 ) {
						sendMsg("StaffFact with STATE == STAFF_MATCHED_6_OF_9");
					}
					else if(staffFact.getState() == State.FAILED_6_OF_9_MATCH ) {
						sendMsg("StaffFact with STATE == FAILED_6_OF_9_MATCH");
					}
				}
				else
				{
					msg += " was unknown by rule " + name;
				}
				sendMsg(msg);
			}catch(Exception ex) {
				sendMsg(" : error " + ex.getLocalizedMessage());
			}
			
		}
		
		
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

	public Logger getRulesLog() {
		if(rulesLog != null) {
			return rulesLog;
		}
		else {
			rulesLog = LoggerFactory.getLogger("rulesLog");
			return rulesLog;
		}
	}

	public void setRulesLog(Logger rulesLog) {
		this.rulesLog = rulesLog;
	}
}
