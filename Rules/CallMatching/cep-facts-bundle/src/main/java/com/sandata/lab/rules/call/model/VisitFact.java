package com.sandata.lab.rules.call.model;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by tom.dornseif on 11/17/2015.
 */
public class VisitFact implements Serializable {

    Logger logger = LoggerFactory.getLogger(VisitFact.class);
    private static  final long serialVersionUID = 1L;
    private String businessEntityId;
    private java.util.List<VisitEventFact> extraneousCalls;
    private Visit visit;
    private String ani;
    private String dnis;
    private String staffId;
    private String vv_id;
    private String patientId;
    private boolean clientEntered = false;
    private State state;
    private Date scheduledFrom;
    private Date scheduledTo;
    private Date callIn;
    private Date callOut;
    private VisitEventFact visitEventFact;
    private CallPreferences callPreferences;

    public boolean insertedPatientMatchesScheduled(String scheduledAni, String scheduledPatientId ) {
        if(this.isClientEntered()) {
            return ( this.getPatientId() != null && this.getPatientId().equals(scheduledPatientId));
        }
        else {
            return this.getAni().equals(scheduledAni);
        }
    }
    public boolean stateIsLessThan(State checkState) {
        if( this.state.compareTo(checkState) < 0) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean staffIsNullOrZeros() {
        if(visit.getStaffID() == null ) {
            return true;
        }
        String staffId = visit.getStaffID().replace("0", "").trim();
        if(staffId.length() == 0) {
            return true;
        }
        else {
            return false;
        }

    }
    public State scheduledStaffMatchesInserted(String insertedStaffId) {
        if(insertedStaffId.equals( this.staffId) || insertedStaffId.equals(this.vv_id)) {
            //if it matches no need to check anything else
            return State.MATCHED;
        }
        else if(getScheduledFrom() != null || getScheduledTo() != null) {
            //CHECK THE ACCURACY OF THE USE OF THESE FIELDS TO DETERMINE SCHEDULED VISIT
            return State.NOT_MATCHED;
        }
        else {

            return State.UNSCHEDULED;
        }
    }

    private int getMatchCount(String staff, int minLength) {
        if(this.staffId == null || staff == null || this.staffId.length() < minLength) {
            return 0;
        }
        else if ( this.staffId.length() >= minLength && staff.contains(this.staffId)) {
            return this.staffId.length();
        }
        else {

            int count = 0;
            int origLength = this.staffId.length();
            int length = origLength - 1;
            int offset = length - minLength;
            int minIndex = minLength - 1;
            String testStaffId = this.staffId;

            while (length > minLength) {
                int endPos = length;
                for (int startPos = 0; startPos <= origLength - length; startPos++) {
                    testStaffId = this.staffId.substring(startPos, endPos);
                    if (staff.contains(testStaffId))
                        count = length;
                    endPos++;
                }
                length--;
            }
            return count;
        }
    }

    public VisitFact() {
        this.visit = new Visit();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VisitFact)) {
            return false;
        }
        VisitFact visit = (VisitFact) obj;
        return ( ( visit.getDnis() == this.getDnis()) && ( visit.getStaffId() == this.getStaffId() ) &&
                ( visit.getState() == this.getState() ) && ( this.getAni() == visit.getAni() ) &&
                ( (visit.getScheduledFrom() == null && this.getScheduledFrom() == null) ||
                        (visit.getScheduledFrom().compareTo(this.getScheduledFrom()) == 0)) &&
                ( (visit.getScheduledTo() == null && this.getScheduledTo() == null) ||
                        (visit.getScheduledTo().compareTo(this.getScheduledTo()) == 0)));
    }
    @Override
    public int hashCode() {
        int result = 17;
        if(this.getStaffId() != null)
            result += (int) (31 * result + this.getStaffId().hashCode());
        if(this.getDnis() != null)
            result += 31 * result + this.getDnis().hashCode();
        if(this.getState() != null)
            result += this.getState().hashCode();
        if(this.getAni() != null)
            result += this.getAni().hashCode();
        if(this.getScheduledFrom() != null )
            result += this.getScheduledFrom().hashCode();
        if(this.getScheduledTo() != null )
            result += this.getScheduledTo().hashCode();

        return result;
    }

    public VisitFact(Visit visit) {
        this.visit = visit;
        if( this.visit.getScheduleEventSK()!= null && this.visit.getVisitActualStartTimestamp() == null &&
        this.visit.getVisitActualEndTimestamp() == null) {

            if(visit.getPatientID() != null && visit.getPatientID().length() > 0)
                this.patientId = visit.getPatientID();
            if(visit.getStaffID() != null && visit.getStaffID().length() > 0)
                this.staffId = visit.getStaffID();
        }
        else {

            this.patientId = visit.getPatientID();
            this.staffId = visit.getStaffID();

            //this is only set if a visit is created from a callevent first time
            this.visitEventFact = null;

        }
        if(visit.getVisitEvent().size() > 0 && visit.getVisitEvent().get(0) != null && ((VisitEvent)visit.getVisitEvent().get(0)).getAutomaticNumberIdentification() != null) {
            this.ani = ((VisitEvent)visit.getVisitEvent().get(0)).getAutomaticNumberIdentification();
        }

        this.callIn = visit.getVisitActualStartTimestamp();
        this.callOut = visit.getVisitActualEndTimestamp();
        this.addTaskList(visit.getVisitTaskList());

    }
    public VisitFact(VisitEventFact visitEventFact, State state) {
        this.visit = new Visit();
        this.state = state;
        this.visitEventFact = visitEventFact;
        this.ani = visitEventFact.getAni();
        this.dnis = visitEventFact.getDnis();
        this.setCallIn(visitEventFact.getCallInTime());
        //If VisitFact is serving as a schedule then we can do this.
        //if(this.state != State.AGG_WAITING_FOR_RESPONSE ) {
            this.setCallCallIn(visitEventFact);
        //}
        this.addTaskList(visitEventFact.getTasks());
        this.setStaffId(visitEventFact.getStaffID());
        this.setPatientId(visitEventFact.getPatientID());
        //this.visit.setStaffID(this.staffId);Happens in VisitFact.setter
        //this.visit.setPatientID(this.patientId);Happens in VisitFact.setter

    }

    public VisitFact(VisitEventFact visitEventFact) {
        this.visit = new Visit();

        this.visitEventFact = visitEventFact;
        this.ani = visitEventFact.getAni();
        this.dnis = visitEventFact.getDnis();
        this.setCallIn(visitEventFact.getCallInTime());
        //If VisitFact is serving as a schedule then we can do this.
        this.setCallCallIn(visitEventFact);
        this.addTaskList(visitEventFact.getTasks());
        this.setStaffId(visitEventFact.getStaffID());
        this.setPatientId(visitEventFact.getPatientID());
        //this.visit.setStaffID(this.staffId);Happens in VisitFact.setter
        //this.visit.setPatientID(this.patientId);Happens in VisitFact.setter

    }
    public void setCallCallIn(VisitEventFact callCallIn) {
        if(this.visit != null) {
            VisitEvent visitEvent = callCallIn.getVisit();
            if(visitEvent != null) {
                String staffID = visitEvent.getStaffID();
                if (this.vv_id != null && this.vv_id.equals(staffID)) {
                    visitEvent.setStaffID(this.getStaffId());
                }
                this.visit.getVisitEvent().add(0, visitEvent);

            }
            this.callIn = callCallIn.getCallInTime();
            this.visit.setVisitActualStartTimestamp(callCallIn.getCallInTime());
        }
    }
    public boolean getDiffDatesLessThan(Date d1, Date d2, long compareTo) {
        long diff = diffDates(d1, d2);
        return ( diff < compareTo );
    }
    public boolean getDiffDatesLessThanEqualTo(Date d1, Date d2, long compareTo) {
        long diff = diffDates(d1, d2);
        return ( diff <= compareTo );
    }

    public boolean getDiffDatesGreaterThan(Date d1, Date d2, long compareTo) {
        long diff = diffDates(d1, d2);
        return  diff > compareTo;
    }
    public boolean getDiffDatesGreaterThanEqualTo(Date d1, Date d2, long compareTo) {
        long diff = diffDates(d1, d2);
        return  diff >= compareTo;
    }
    public long diffDates(Date d1, Date d2) {
        long retResult = 0L;
        logger.info(String.format("First Date d1 = %s, Second Date d2 = %s", d1.toString(), d2.toString()));
        logger.info(String.format("getTime method results d1.getTime() = %d, d2.getTime() = %d", d1.getTime(), d2.getTime()));

        if(d1.after(d2)) {
            retResult = d1.getTime() - d2.getTime();
            logger.info(String.format("First Date is After Second Date (d1.getTime() - d2.getTime() = %d", retResult));
        }
        else
        {
            retResult = d2.getTime() - d1.getTime();
            logger.info(String.format("First Date is Equal To or Before Second Date (d2.getTime() - d1.getTime() = %d", retResult));

        }

        return retResult;
    }


    public void setCallCallOut(VisitEventFact callCallOut) {
        if(this.visit != null) {
            VisitEvent visitEvent = callCallOut.getVisit();
            this.visit.getVisitEvent().add(visitEvent);
            this.callOut = callCallOut.getCallInTime();
            this.visit.setVisitActualEndTimestamp(this.callOut);
        }

    }

    public Date getCallIn() {
        if(this.visit == null )
            return this.callIn;
        else
            return this.visit.getVisitActualStartTimestamp();
    }

    public void setCallIn(Date callIn) {
        this.callIn = callIn;
        if(this.visit != null) {
            this.visit.setVisitActualStartTimestamp(callIn);
        }
    }

    public void setScheduleEventSK(BigInteger sk) {
        if(this.visit != null) {
            this.visit.setScheduleEventSK(sk);
        }
    }

    public Date getCallOut() {
        if(this.visit != null)
            return this.visit.getVisitActualEndTimestamp();
        return this.callOut;
    }

    public void setCallOut(Date callOut) {
        if(this.visit != null)
            this.visit.setVisitActualEndTimestamp(callOut);
        else
            this.callOut = callOut;
    }

    public VisitEventFact getCallCallIn() {
        VisitEventFact vf = null;
        if(this.visit != null) {
            if (this.visit.getVisitEvent() != null && this.visit.getVisitEvent().size() > 0) {
                vf = new VisitEventFact();
                vf.setVisit(this.visit.getVisitEvent().get(0));
            }
        }
        else
            vf = this.visitEventFact;

        return vf;
    }

    public void addTaskList(List<VisitTaskList> tasks) {
        if (tasks != null) {
            if (this.visit != null) {
                this.visit.getVisitTaskList().addAll(tasks);
            }
        }
    }


    public VisitEventFact getCallCallOut() {
        VisitEventFact vf = null;
        if( this.visit.getVisitEvent()!= null && ( ( this.visit.getVisitEvent().size() > 1 ) ||
                ( this.visit.getVisitEvent().size() == 1 &&
                        this.callIn == null && this.callOut != null ) ) ) {
            vf = new VisitEventFact();
            vf.setVisit(this.visit.getVisitEvent().get(this.visit.getVisitEvent().size() - 1));
        }

        return vf;

    }



    public void addMiddleCall(VisitEventFact call) {
        if(this.visit.getVisitEvent().size() > 2) {
            this.visit.getVisitEvent().add(this.visit.getVisitEvent().size() -1, call.getVisit());
        }
        else {
            this.visit.getVisitEvent().add(call.getVisit());
        }


    }

    public java.util.List<VisitEventFact> getExtraneousCalls() {
        return extraneousCalls;
    }

    public void addExtraneousCalls(VisitEventFact extraneousCall) {
        if(this.extraneousCalls == null) {

            this.extraneousCalls = new ArrayList<VisitEventFact>();
        }
        this.extraneousCalls.add(extraneousCall);
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisitFactVisit(VisitFact visitFact) {
        if(visitFact.getState() == State.AGG_INSERTED_FROM_RESPONSE) {
            this.visit = visitFact.getVisit();
            if(this.visitEventFact != null && this.getState() == State.AGG_WAITING_FOR_RESPONSE ) {
                if( this.visitEventFact.getCallInTime().after(this.visit.getVisitActualEndTimestamp())) {
                    this.visit.setVisitActualEndTimestamp(this.visitEventFact.getCallInTime());
                    this.visit.getVisitEvent().add(this.visitEventFact.getVisit());
                    this.setState(State.INSERTED);
                }
            }
        }
    }
    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getStaffId() {
        if(this.staffId != null)
            return this.staffId;
        else
            return this.visit.getStaffID();

    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
        this.visit.setStaffID(staffId);
    }

    public List<VisitTaskList> getTaskList() {

        return this.visit.getVisitTaskList();
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId)
    {
        this.patientId = patientId;
        this.visit.setPatientID(patientId);
    }

    public Date getScheduledFrom() {
        return scheduledFrom;
    }

    public void setScheduledFrom(Date scheduledFrom) {
        this.scheduledFrom = scheduledFrom;
    }

    public Date getScheduledTo() {
        return scheduledTo;
    }

    public void setScheduledTo(Date scheduledTo) {
        this.scheduledTo = scheduledTo;
    }

    public boolean isMissingCriticalTasks() {
        List<VisitTaskList> list = this.getTaskList();
        //This has a different meaning according to POC???????
        /*
        boolean missingCriticalTask = true;

        if(list != null && list.size() > 0) {
            Iterator<VisitTaskList> iterator = list.iterator();

            while (iterator.hasNext()) {
                VisitTaskList task = iterator.next();
                if (task.isCriticalTaskIndicator().booleanValue()) {
                    missingCriticalTask = false;
                }
            }
        } */
        return false;//missingCriticalTask;
    }

    public boolean isClientEntered() {
        return clientEntered;
    }

    public void setClientEntered(boolean clientEntered) {
        this.clientEntered = clientEntered;
    }

    public CallPreferences getCallPreferences() {
        return callPreferences;
    }

    public void setCallPreferences(CallPreferences callPreferences) {
        this.callPreferences = callPreferences;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getVv_id() {
        return vv_id;
    }

    public void setVv_id(String vv_id) {
        this.vv_id = vv_id;
    }
}
