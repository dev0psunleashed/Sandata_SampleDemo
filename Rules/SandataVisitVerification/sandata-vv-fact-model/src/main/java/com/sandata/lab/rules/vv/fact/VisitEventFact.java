package com.sandata.lab.rules.vv.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.vv.model.CallPreferences;
import com.sandata.lab.rules.vv.model.GPSLocation;
import com.sandata.lab.rules.vv.model.VisitState;


/**
 * Created by tom.dornseif on 11/17/2015.
 */
public class VisitEventFact extends VisitEvent implements Serializable {

    static final long serialVersionUID = 1L;


    private VisitState visitState;
    private String scheduleId;
    private List<VisitTaskList> tasks;
    private GPSLocation gPSLocation;
    private String ani;//callerId
    private String dnis;//1800 toll number unique to an agency
    private Date callInTime;
    private UUID requestId;
    private String businessEntityId;

    //flag visit has extranous call that this visit event belong to
    private boolean visitHasExtraCall;

    private ScheduleEventFact scheduleEventFact;

    private StaffFact staffFact;

    private PatientFact patientFact;

    private CallPreferences callPreferences;

    //the visit that the current/existing visit event belong to
    private Visit parentVisit;
    
    private String sid ;
    

    public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public VisitEventFact() {
        dnis = "";
        ani = "";
        staffID = "";
    }
    public VisitEventFact(VisitEvent visitEvent) {
        this.setVisit(visitEvent);

    }
    public void setVisitEventExt(VisitEventExt visitEventExt ) {
        if(!(visitEventExt.getVisitTaskLists() == null || visitEventExt.getVisitTaskLists().isEmpty())) {
            this.tasks = new ArrayList<VisitTaskList>();
            for (VisitTaskList vtl : visitEventExt.getVisitTaskLists()) {
                this.tasks.add(vtl);
            }
        }
        VisitEvent v = new VisitEvent();
        if(visitEventExt.getVisitEventDatetime() != null)
            v.setVisitEventDatetime(visitEventExt.getVisitEventDatetime());
        v.setStaffID(visitEventExt.getStaffID());
        v.setDialedNumberIdentificationService(visitEventExt.getDialedNumberIdentificationService());
        v.setPatientID(removePadding(visitEventExt.getPatientID()));

        v.setAutomaticNumberIdentification(visitEventExt.getAutomaticNumberIdentification());
        v.setCoordinateAccuracy(visitEventExt.getCoordinateAccuracy());
        v.setCoordinateAltitude(visitEventExt.getCoordinateAltitude());
        v.setCoordinateLatitude(visitEventExt.getCoordinateLatitude());
        v.setCoordinateLongitide(visitEventExt.getCoordinateLongitide());
        v.setCoordinateHeading(visitEventExt.getCoordinateHeading());
        v.setCoordinateSpeed(visitEventExt.getCoordinateSpeed());
        v.setInformationDigits(visitEventExt.getInformationDigits());
        v.setEquipmentID(visitEventExt.getEquipmentID());
        if(visitEventExt.getTimezoneName() == null) {
            //TMD defaulting timezone to US/Eastern until evv starts sending it.
            v.setTimezoneName("US/Eastern");
        }
        else {
            v.setTimezoneName(visitEventExt.getTimezoneName());
        }
        this.setVisit(v);

    }

    private String removePadding(String paddedString) {
        String returnString = paddedString;
        if(paddedString != null && paddedString.length() > 0 ) {
            returnString = paddedString.replaceFirst("^0+(?!$)","");
            if(returnString.equals("0"))
                returnString = paddedString;
        }
        return returnString;
    }

    public void setVisit(VisitEvent visitEvent) {

        this.setCallInIndicator(visitEvent.isCallInIndicator());
        this.setCallOutIndicator(visitEvent.isCallOutIndicator());
        this.setDnis(visitEvent.getDialedNumberIdentificationService());
        this.setAni(visitEvent.getAutomaticNumberIdentification());
        this.setCallInTime(visitEvent.getVisitEventDatetime());
        this.setPatientID(visitEvent.getPatientID());
        this.setStaffID(visitEvent.getStaffID());
        this.setTimezoneName(visitEvent.getTimezoneName());
        this.setInternationalMobileStationEquipmentIdentity(visitEvent.getInternationalMobileStationEquipmentIdentity());

        //TODO: should not assign empty object because will make error at the matshalling at the wildfly side
        if (visitEvent.getCoordinateAccuracy() != null || visitEvent.getCoordinateAccuracy() != null
                ||  visitEvent.getCoordinateLatitude() != null
                || visitEvent.getCoordinateLongitide() != null ) {
            this.gPSLocation = new GPSLocation();
            this.gPSLocation.setAccuracy(visitEvent.getCoordinateAccuracy());
            this.gPSLocation.setAltitude(visitEvent.getCoordinateAltitude());
            this.gPSLocation.setLatitude(visitEvent.getCoordinateLatitude());
            this.gPSLocation.setLongitude(visitEvent.getCoordinateLongitide());
        }

    }
    public final VisitEvent getVisit(){
        VisitEvent visitEvent = new VisitEvent();
        if(this.gPSLocation == null) {
            this.gPSLocation = new GPSLocation();
        }
        visitEvent.setCoordinateAccuracy(this.gPSLocation.getAccuracy());
        visitEvent.setCoordinateAltitude(this.gPSLocation.getAltitude());
        visitEvent.setCoordinateLatitude(this.gPSLocation.getLatitude());
        visitEvent.setCoordinateLongitide(this.gPSLocation.getLongitude());

        visitEvent.setCallInIndicator(this.callInIndicator);
        visitEvent.setCallOutIndicator(this.callOutIndicator);
        visitEvent.setVisitEventSK(this.visitEventSK);
        visitEvent.setVisitSK(this.visitSK);
        visitEvent.setRecordCreateTimestamp(this.recordCreateTimestamp);
        visitEvent.setRecordUpdateTimestamp(this.recordUpdateTimestamp);
        visitEvent.setRecordEffectiveTimestamp(this.recordEffectiveTimestamp);
        visitEvent.setRecordTerminationTimestamp(this.recordTerminationTimestamp);
        visitEvent.setRecordCreatedBy(this.recordCreatedBy);
        visitEvent.setRecordUpdatedBy(this.recordUpdatedBy);
        visitEvent.setChangeReasonMemo(this.changeReasonMemo);
        visitEvent.setCurrentRecordIndicator(this.currentRecordIndicator);
        visitEvent.setChangeVersionID(this.changeVersionID);
        visitEvent.setVisitEventTypeCode(this.visitEventTypeCode);
        visitEvent.setVisitEventDatetime(this.visitEventDatetime);
        visitEvent.setTimezoneName(this.timezoneName);
        visitEvent.setAutomaticNumberIdentification(this.ani);
        visitEvent.setInformationDigits(this.informationDigits);
        visitEvent.setDialedNumberIdentificationService(this.dnis);
        visitEvent.setEquipmentID(this.equipmentID);

        if (this.getStaffFact() != null && StringUtils.isNotEmpty(this.getStaffFact().getStaffId())) {
            //data from database
            visitEvent.setStaffID(this.getStaffFact().getStaffId());
        } else {
            visitEvent.setStaffID(this.staffID);
        }

        if (this.getPatientFact() != null && StringUtils.isNotEmpty(this.getPatientFact().getPatientId())) {
            //data from database
            visitEvent.setPatientID(this.getPatientFact().getPatientId());
        } else {
            visitEvent.setPatientID(this.patientID);
        }

        visitEvent.setInternationalMobileStationEquipmentIdentity(this.internationalMobileStationEquipmentIdentity);
        visitEvent.setCallInIndicator(this.callInIndicator);
        visitEvent.setCallOutIndicator(this.callOutIndicator);
        return visitEvent;
    }
    public VisitEventExt getVisitEventExt() {
        VisitEventExt visitEvent = new VisitEventExt();
        visitEvent.setDialedNumberIdentificationService(this.getDnis());
        visitEvent.setAutomaticNumberIdentification(this.getAni());
        visitEvent.setVisitEventDatetime(this.getCallInTime());
        visitEvent.setPatientID(this.getPatientID());
        visitEvent.setStaffID(this.getStaffID());
        visitEvent.setTimezoneName(this.getTimezoneName());
        visitEvent.setInternationalMobileStationEquipmentIdentity(this.getInternationalMobileStationEquipmentIdentity());
        if (this.gPSLocation != null) {
	        visitEvent.setCoordinateAccuracy(this.gPSLocation.getAccuracy());
	        visitEvent.setCoordinateAltitude(this.gPSLocation.getAltitude());
	        visitEvent.setCoordinateLatitude(this.gPSLocation.getLatitude());
	        visitEvent.setCoordinateLongitide(this.gPSLocation.getLongitude());
        }
        visitEvent.setSid(this.sid);
        return visitEvent;
    }
    public String getAni() {
        if(super.getAutomaticNumberIdentification() != null && !super.getAutomaticNumberIdentification().isEmpty()) {
            this.ani = super.getAutomaticNumberIdentification();
        }

        return this.ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
        super.setAutomaticNumberIdentification(ani);
    }


    public void setStateFromRules (int state) {
    	
    	//state int not one of the below value 1-29 , 100, 101
    	switch(state) {
		case 31:
			this.visitState = VisitState.NOT_MATCHED;
	
		//TODO: add more cases here
	}
    	
    }

    public Date getCallInTime() {
        if(super.getVisitEventDatetime()!= null) {
            this.callInTime = super.getVisitEventDatetime();
        }
        return this.callInTime;
    }

    public void setCallInTime(Date callInTime) {
        this.callInTime = callInTime;
        super.setVisitEventDatetime(callInTime);
    }

    public VisitState getVisitState() {
        return visitState;
    }

    public void setVisitState(VisitState visitState) {
        this.visitState = visitState;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public boolean isVisitHasExtraCall() {
        return visitHasExtraCall;
    }

    public void setVisitHasExtraCall(boolean visitHasExtraCall) {
        this.visitHasExtraCall = visitHasExtraCall;
    }

    public String getDnis() {
        if(super.getDialedNumberIdentificationService()!= null && !super.getDialedNumberIdentificationService().isEmpty()) {
            this.dnis = super.getDialedNumberIdentificationService();
        }
        return this.dnis;
    }

    public void setDnis(String dnis) {

        super.setDialedNumberIdentificationService(dnis);
        this.dnis = dnis;
    }

    public List<VisitTaskList> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<VisitTaskList> tasks) {

        this.tasks = tasks;
    }

    public String getClientId() {
        return super.getPatientID();
    }

    public void setClientId(String clientId) {
        super.setPatientID(clientId);
    }


    public GPSLocation getGps() {
        this.gPSLocation.setAccuracy(super.getCoordinateAccuracy());
        this.gPSLocation.setLatitude(super.getCoordinateLatitude());
        this.gPSLocation.setLongitude(super.getCoordinateLongitide());
        this.gPSLocation.setAltitude(super.getCoordinateAltitude());
        return this.gPSLocation;
    }

    public void setGps(GPSLocation gps) {

        super.setCoordinateAccuracy(gps.getAccuracy());
        super.setCoordinateAltitude(gps.getAltitude());
        super.setCoordinateLatitude(gps.getLatitude());
        super.setCoordinateLongitide(gps.getLongitude());
        this.gPSLocation = gps;
    }


    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public ScheduleEventFact getScheduleEventFact() {
        return scheduleEventFact;
    }

    public void setScheduleEventFact(ScheduleEventFact scheduleEventFact) {
        this.scheduleEventFact = scheduleEventFact;
    }

    public StaffFact getStaffFact() {
        return staffFact;
    }

    public void setStaffFact(StaffFact staffFact) {
        this.staffFact = staffFact;
    }

    public PatientFact getPatientFact() {
        return patientFact;
    }

    public void setPatientFact(PatientFact patientFact) {
        this.patientFact = patientFact;
    }

    public CallPreferences getCallPreferences() {
        return callPreferences;
    }

    public void setCallPreferences(CallPreferences callPreferences) {
        this.callPreferences = callPreferences;
    }

    public void setParentVisit(Visit visit) {
        this.parentVisit = visit;
    }

    public Visit getParentVisit() {
        return this.parentVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VisitEventFact that = (VisitEventFact) o;

        if (visitState != that.visitState) return false;
        if (scheduleId != null ? !scheduleId.equals(that.scheduleId) : that.scheduleId != null) return false;
        if (tasks != null ? !tasks.equals(that.tasks) : that.tasks != null) return false;
        if (gPSLocation != null ? !gPSLocation.equals(that.gPSLocation) : that.gPSLocation != null) return false;
        if (ani != null ? !ani.equals(that.ani) : that.ani != null) return false;
        if (dnis != null ? !dnis.equals(that.dnis) : that.dnis != null) return false;
        if (callInTime != null ? !callInTime.equals(that.callInTime) : that.callInTime != null) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;
        if (businessEntityId != null ? !businessEntityId.equals(that.businessEntityId) : that.businessEntityId != null)
            return false;
        return scheduleEventFact != null ? scheduleEventFact.equals(that.scheduleEventFact) : that.scheduleEventFact == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (visitState != null ? visitState.hashCode() : 0);
        result = 31 * result + (scheduleId != null ? scheduleId.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (gPSLocation != null ? gPSLocation.hashCode() : 0);
        result = 31 * result + (ani != null ? ani.hashCode() : 0);
        result = 31 * result + (dnis != null ? dnis.hashCode() : 0);
        result = 31 * result + (callInTime != null ? callInTime.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (businessEntityId != null ? businessEntityId.hashCode() : 0);
        result = 31 * result + (scheduleEventFact != null ? scheduleEventFact.hashCode() : 0);
        return result;
    }
}
