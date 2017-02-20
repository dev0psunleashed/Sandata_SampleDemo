package com.sandata.lab.rules.call.model;


import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule implements Serializable {
	static final long serialVersionUID = 1L;

	private State state = State.LOADED;
	private long timeSpan;

	private Date startTime;//SCHED.SCHED_START_DATE cant be NULL
	private Date endTime;//SCHED.SCHED_END_DATE

	List<Visit> visits;
	private String dnis;

    private String patientId;
	private boolean matchPatient;
	private boolean matchStaff;
	private boolean callMatched;
    private ScheduleEventExt scheduleEventExt;
	private java.util.List<VisitEventFact> extraneousCalls;

	public long diff(Date date1, Date date2) {
		return Math.abs(date2.getTime() - date1.getTime());
	}
    public Schedule(ScheduleEventExt scheduleEventExt) {
        this.scheduleEventExt = scheduleEventExt;
    }
	public Schedule(java.util.Date startDate, java.util.Date endDate) {
        scheduleEventExt = new ScheduleEventExt();
		this.setStartTime(startDate);
		this.setEndTime(endDate);
		this.timeSpan = this.endTime.getTime() - this.startTime.getTime();
		this.extraneousCalls = new java.util.ArrayList<VisitEventFact>();
		this.state = State.LOADED;
	}


	public boolean forVisit(VisitFact visitFact) {
		if(visitFact.getVisit() != null && visitFact.getVisit().getScheduleEventSK() != null &&
				this.getScheduleEventExt() != null && this.getScheduleEventExt().getScheduleEventSK() != null &&
				this.getScheduleEventExt().getScheduleEventSK().equals(visitFact.getVisit().getScheduleEventSK())) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<Visit> getVisit() {
		List<Visit> tmp = this.scheduleEventExt.getVisit();

		if (this.visits == null) {
			if(tmp != null) {
				this.visits = tmp;
			}
			else {
				this.visits = new ArrayList<Visit>();
			}

		}
		return this.visits;
	}

	public void setVisits(List<Visit> visitList) {
		List<Visit> tmp = this.scheduleEventExt.getVisit();
		if (this.visits == null) {
			this.visits = new ArrayList<Visit>();
			if (tmp.size() > 0) {
				this.visits = tmp;
			}
		}
		this.visits.addAll(visitList);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Schedule)) {
			return false;
		}
		Schedule schedule = (Schedule) obj;
		boolean startTimeMatched = false;
		if(schedule.getStartTime() == null && this.startTime == null) {
			startTimeMatched = true;
		}
		else if(schedule.getStartTime() != null ) {
			startTimeMatched = schedule.getStartTime().equals(this.startTime);
		}
		boolean endTimeMatched = false;
		if(schedule.getEndTime() == null && this.endTime == null) {
			endTimeMatched = true;
		}
		else if(schedule.getEndTime() != null ) {
			endTimeMatched = schedule.getEndTime().equals(this.endTime);
		}
		boolean aniMatched = false;
		if(schedule.getCallerIdForPatient() == null && ( this.scheduleEventExt == null || this.scheduleEventExt.getAni() == null) ) {
			aniMatched = true;
		}
		else if(schedule.getCallerIdForPatient() != null) {
			if(this.scheduleEventExt == null) {
				aniMatched = false;
			}
			else {
				aniMatched = schedule.getCallerIdForPatient().equals(this.scheduleEventExt.getAni());
			}
		}
		boolean staffMatched = false;
		if(schedule.getStaffId() == null && ( this.scheduleEventExt == null || this.scheduleEventExt.getStaffID() == null) ) {
			staffMatched = true;
		}
		else if(schedule.getStaffId() != null ) {
			if(this.scheduleEventExt == null) {
				staffMatched = false;
			}
			else {
				staffMatched = schedule.getStaffId().equals(this.scheduleEventExt.getStaffID());
			}
		}
		return ( aniMatched && startTimeMatched && endTimeMatched && staffMatched );

	}

	@Override
	public int hashCode() {
		int result = 17;
		if(this.scheduleEventExt != null) {
			if( this.scheduleEventExt.getStaffID() != null) {
				result = 31 * result + this.scheduleEventExt.getStaffID().hashCode();
			}
			if(this.scheduleEventExt.getAni() != null) {
				result = 31 * result + this.scheduleEventExt.getAni().hashCode();
			}
		}
		if(this.startTime != null) {
			result = 31 * result + this.startTime.hashCode();
		}
		if(this.endTime != null) {
			result = 31 * result + endTime.hashCode();
		}

		return result;
	}


	public State getState() {

		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	public Date getCallIn() {
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            VisitFact vf = new VisitFact((Visit) this.scheduleEventExt.getVisit().get(0));
            return vf.getCallIn();
		} else {
			return null;
		}
	}

	public void setCallIn(Date callIn) {
        VisitFact vf = null;
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
			vf = new VisitFact(((Visit) this.scheduleEventExt.getVisit().get(0)));
            vf.setCallIn(callIn);
            this.scheduleEventExt.getVisit().remove(0);
            this.scheduleEventExt.getVisit().add(0, vf.getVisit());

		} else {
			if (this.scheduleEventExt.getVisit() == null) {
				throw new UnsupportedOperationException("ScheduleEvent from dl did not have a visit list!  Implement code to create unplanned visit to recover! TBD");
			}
			VisitFact visit = createVisitFact();
			visit.setCallIn(callIn);
			this.scheduleEventExt.getVisit().add(0, visit.getVisit());
		}
	}

	private VisitFact createVisitFact() {
		Visit visit = new Visit();
		visit.setScheduleEventSK(this.scheduleEventExt.getScheduleEventSK());
		visit.setStaffID(this.scheduleEventExt.getStaffID());
		visit.setPatientID(this.scheduleEventExt.getPatientID());
        VisitFact visitFact = new VisitFact(visit);
		return visitFact;
	}

	public Date getCallOut() {
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            VisitFact visitFact = new VisitFact((Visit) this.scheduleEventExt.getVisit().get(0));// we need to search for the correct visit
            return visitFact.getCallOut();
		} else {
			return null;
		}
	}

	public void setCallOut(Date callOut) {
        VisitFact vf;
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            vf = new VisitFact(this.scheduleEventExt.getVisit().get(0));// we need to search for the correct visit
            vf.setCallOut(callOut);
		} else {
			if (this.scheduleEventExt.getVisit() == null) {
				throw new UnsupportedOperationException("ScheduleEvent from dl did not have a visit list!  Implement code to create unplanned visit to recover! TBD");
			}
			vf = createVisitFact();
			vf.setCallOut(callOut);
			this.scheduleEventExt.getVisit().add(0, vf.getVisit());
		}
	}

	public VisitEventFact getCallCallIn() {
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            VisitFact vf = new VisitFact((Visit) this.scheduleEventExt.getVisit().get(0));// we need to search for the correct visit
            return vf.getCallCallIn();
		} else {
			return null;
		}
	}

	public void setCallCallIn(VisitEventFact callCallIn) {
        VisitFact vf;
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            vf = new VisitFact(((Visit) this.scheduleEventExt.getVisit().get(0)));
            vf.setCallCallIn(callCallIn);
            this.scheduleEventExt.getVisit().remove(0);
            this.scheduleEventExt.getVisit().add(0, vf.getVisit());

        } else {
			if (this.scheduleEventExt.getVisit() == null) {
				throw new UnsupportedOperationException("ScheduleEvent from dl did not have a visit list!  Implement code to create unplanned visit to recover! TBD");
			}
			VisitFact visit = createVisitFact();
	        visit.setCallCallIn( callCallIn);
			this.scheduleEventExt.getVisit().add(0, visit.getVisit());
		}

	}

	public VisitEventFact getCallCallOut() {
		if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            VisitFact vf = new VisitFact((Visit) this.scheduleEventExt.getVisit().get(0));// we need to search for the correct visit
            return vf.getCallCallOut();
		} else {
			return null;
		}
	}

	public void setCallCallOut(VisitEventFact callCallOut) {
        VisitFact vf;
        if (this.scheduleEventExt.getVisit() != null && this.scheduleEventExt.getVisit().size() > 0) {
            vf = new VisitFact(((Visit) this.scheduleEventExt.getVisit().get(0)));
            vf.setCallCallOut(callCallOut);
            this.scheduleEventExt.getVisit().remove(0);
            this.scheduleEventExt.getVisit().add(0, vf.getVisit());

        } else {
            if (this.scheduleEventExt.getVisit() == null) {
                throw new UnsupportedOperationException("ScheduleEvent from dl did not have a visit list!  Implement code to create unplanned visit to recover! TBD");
            }
            VisitFact visit = createVisitFact();
            visit.setCallCallOut( callCallOut);
            this.scheduleEventExt.getVisit().add(0, visit.getVisit());
        }
	}

	public List<VisitEventFact> getExtraneousCalls() {
			return this.extraneousCalls;
	}

	public void addExtraneousCalls(VisitEventFact extraneousCall) {
		//TBD

	}


	public void deleteExtraneousCalls() {
		//TBD
	}

	public long getTimeSpan() {
		this.timeSpan = this.scheduleEventExt.getScheduleEventEndDatetime().getTime() - this.scheduleEventExt.getScheduleEventStartDatetime().getTime();
		return timeSpan;
	}

	public void setTimeSpan(long timeSpan) {
		this.timeSpan = timeSpan;
	}

	public boolean isCallMatched() {
		return callMatched;
	}

	public void setCallMatched(boolean callMatched) {
		this.callMatched = callMatched;
	}

	public String getCallerIdForPatient() {
		return this.scheduleEventExt.getAni();
	}

	public void setCallerIdForPatient(String callerIdForPatient) {
		this.scheduleEventExt.setAni(callerIdForPatient);
	}

	public Date getStartTime() {
		if(this.scheduleEventExt.getScheduleEventStartDatetime()!= null) {
			this.startTime = this.scheduleEventExt.getScheduleEventStartDatetime();
		}
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.scheduleEventExt.setScheduleEventStartDatetime(startTime);

	}

	public Date getEndTime() {
		if(this.scheduleEventExt.getScheduleEventEndDatetime()!= null) {
			this.endTime = this.scheduleEventExt.getScheduleEventEndDatetime();
		}
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.scheduleEventExt.setScheduleEventEndDatetime(endTime);
		this.endTime = endTime;
	}



	public boolean isMatchStaff() {
		return this.matchStaff;
	}

	public void setMatchStaff(boolean matchStaff) {
		this.matchStaff = matchStaff;
	}

	public boolean isMatchPatient() {
		return this.matchPatient;
	}

	public void setMatchPatient(boolean matchPatient) {
		this.matchPatient = matchPatient;
	}



	public String getDnis() {
		return this.dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	public static Date CreateDateWithOffsetHourMinute(Date start, int i, int j) {
		Calendar calendar = DateToCalendar(start);
		calendar.add(Calendar.HOUR_OF_DAY, i);
		calendar.add(Calendar.MINUTE, j);
		return calendar.getTime();
	}

	public static Date CreateDateWithOffset(Date startDate, int i) {
		Calendar calendar = DateToCalendar(startDate);
		calendar.add(Calendar.HOUR_OF_DAY, i);
		return calendar.getTime();
	}

	private static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

    public ScheduleEventExt getScheduleEventExt() {
        return scheduleEventExt;
    }

    public void setScheduleEventExt(ScheduleEventExt scheduleEventExt) {
        this.scheduleEventExt = scheduleEventExt;
    }

    public String getStaffId() {
        return this.scheduleEventExt.getStaffID();
    }

    public void setStaffId(String staffId) {
        this.scheduleEventExt.setStaffID(staffId);
    }

    public String getPatientId() {
        return this.scheduleEventExt.getPatientID();
    }

    public void setPatientId(String patientId) {
        this.scheduleEventExt.setPatientID(patientId);
    }
}
