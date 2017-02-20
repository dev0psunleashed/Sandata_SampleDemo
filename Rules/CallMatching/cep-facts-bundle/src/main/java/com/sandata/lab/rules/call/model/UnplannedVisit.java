package com.sandata.lab.rules.call.model;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnplannedVisit implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Schedule schedule;
	private Visit visit;
	private VisitEventFact visitEventFact;
	private List<VisitTaskList> tasks;
	private VisitEventFact callCallIn;
	private VisitEventFact callCallOut;
	/***
	 * UnplannedVisit constructor when you have an existing schedule.  
	 * Will create a copy of the passed in schedule, same staff and PatientId, and use new start and end time
	 * End time is unknown so will default to 1 hour.
	 * @param schedule
	 * @param visitEventFact
	 * @param newStartTime
	 * @param newEndTime
	 */
	public UnplannedVisit(Schedule schedule, VisitEventFact visitEventFact, Date newStartTime, Date newEndTime) {
		this.schedule = schedule;
		this.visitEventFact = visitEventFact;
		this.schedule.deleteExtraneousCalls();
		this.setStartTime(newStartTime);
		if(newEndTime != null)
			this.setEndTime(newEndTime);
		else
			this.setEndTime(Schedule.CreateDateWithOffset(newStartTime, 1));
		if(this.getCallIn()==null) {
			this.setCallIn(visitEventFact.getCallInTime());
			this.setCallCallIn(visitEventFact);
		}
		tasks = new ArrayList<VisitTaskList>();
		if(visitEventFact.getTasks() != null && visitEventFact.getTasks().size() > 0) {
				this.tasks.addAll(visitEventFact.getTasks());

		}

	}
	

	@Override 
	   public boolean equals(Object obj) {
		   if(obj == null) {
			   return false;
		   }
		   if(! (obj instanceof UnplannedVisit)) {
			   return false;
		   }
		   UnplannedVisit visit = (UnplannedVisit) obj;
		   return visit.schedule.getCallerIdForPatient().equals(schedule.getCallerIdForPatient()) && visit.schedule.getStartTime().equals(schedule.getStartTime()) && 
				   visit.schedule.getEndTime().equals(schedule.getEndTime()) && visit.schedule.getStaffId().equals(schedule.getStaffId()); 
		   
	   }
	   @Override
	   public int hashCode() {
		   int result = 17;
		   result = 31 * result + schedule.getStaffId().hashCode();
		   result = 31 * result + schedule.getStartTime().hashCode();
		   result = 31 * result + schedule.getEndTime().hashCode();
		   result = 31 * result + schedule.getCallerIdForPatient().hashCode();
		   return result;
	   }
	
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule=schedule;
	}
	public String getCallerIdForPatient() {
		return schedule.getCallerIdForPatient();
	}
	public void setCallerIdForPatient(String callerIdForPatient) {
		if(schedule != null) { this.schedule.setCallerIdForPatient(callerIdForPatient);}
	}
	public String getStaffId() {
		return schedule.getStaffId();
	}
	public void setStaffId(String staffId) {
		if(schedule != null) { this.schedule.setStaffId(staffId);}
	}
	public State getState() {
		return schedule.getState();
	}
	public void setState(State state) {
		if(schedule != null) { this.schedule.setState(state);}
	}
	public Date getStartTime() {
		return schedule.getStartTime();
	}
	public void setStartTime(Date startTime) {
		if(schedule != null) { this.schedule.setStartTime(startTime);}
	}
	public Date getEndTime() {
		return schedule.getEndTime();
	}
	public void setEndTime(Date endTime) {
		if(schedule != null) { this.schedule.setEndTime(endTime);}
	}
	public Date getCallIn() {
		return schedule.getCallIn();
	}
	public void setCallIn(Date callIn) {
		if(schedule != null) { this.schedule.setCallIn(callIn);}
	}
	public Date getCallOut() {
		return schedule.getCallOut();
	}
	public void setCallOut(Date callOut) {
		if(schedule != null) { this.schedule.setCallOut(callOut);}
	}
	public long getTimeSpan() {
		return schedule.getTimeSpan();
	}
	
	public VisitEventFact getCallCallIn() {
		return this.callCallIn;
	}
	public void setCallCallIn(VisitEventFact callCallIn) {
		this.callCallIn = callCallIn;

	}
	public VisitEventFact getCallCallOut() {

        return this.callCallOut;
	}
	public void setCallCallOut(VisitEventFact callCallOut) {
		this.callCallOut = callCallOut;
	}
	public java.util.List<VisitEventFact> getExtraneousCalls() {
		return schedule.getExtraneousCalls();
	}
	public void addExtraneousCalls(VisitEventFact extraneousCall) {
		if(schedule != null) { this.schedule.addExtraneousCalls(extraneousCall);}
		
	}


	public Visit getVisit() {
		this.visit = new Visit();
		if(this.getCallOut() != null) {
			this.visit.setVisitActualEndTimestamp(this.getCallOut());
		}
		if(this.getCallIn() != null ) {
			this.visit.setVisitActualStartTimestamp(this.getCallIn());
		}
		
		if(this.getCallCallIn() != null) {
			this.visit.getVisitEvent().add(this.getCallCallIn().getVisit());
		}
		if(this.getCallCallOut() != null) {
			this.visit.getVisitEvent().add(this.getCallCallOut().getVisit());
		}

		this.visit.getVisitException();
		if(this.tasks.size()>0) {
			for(VisitTaskList vt : this.tasks) {
				VisitTaskListExt vtle = new VisitTaskListExt(vt);
				this.visit.getVisitTaskList().add(vtle);
			}

		}
		return visit;
	}


}
