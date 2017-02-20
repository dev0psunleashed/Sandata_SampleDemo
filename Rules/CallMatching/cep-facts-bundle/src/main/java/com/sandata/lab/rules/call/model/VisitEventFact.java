package com.sandata.lab.rules.call.model;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by tom.dornseif on 11/17/2015.
 */
public class VisitEventFact extends VisitEvent implements Serializable {

    static final long serialVersionUID = 1L;


    private State state;
    private String scheduleId;
    private List<VisitTaskList> tasks;
    private GPSLocation gPSLocation;

    private String ani;//callerId
    private String dnis;//1800 toll number unique to an agency
    private Date callInTime;
    private UUID requestId;
    private String businessEntityId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VisitEventFact)) {
            return false;
        }
        VisitEventFact visitEventFact = (VisitEventFact) obj;
        return (visitEventFact.getStaffID().equals(this.getStaffID()) &&
                visitEventFact.getDnis() == this.getDnis() &&
                visitEventFact.getAni().equals(this.getAni()) &&
                visitEventFact.getCallInTime() == this.getCallInTime() &&
                visitEventFact.getPatientID() == this.getPatientID());

    }
    @Override
    public int hashCode() {
        int result = 17;
        if(getDnis() != null)
            result += (31 * result + getDnis().hashCode());
        if(getStaffID() != null)
            result += 31 * getStaffID().hashCode();
        if(getAni() != null)
            result += 31 * getAni().hashCode();
        if(getPatientID() != null)
            result += getPatientID().hashCode();
        if(callInTime!=null)
            result += 31 * getCallInTime().hashCode();

        return result;
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

        this.setDnis(visitEvent.getDialedNumberIdentificationService());
        this.setAni(visitEvent.getAutomaticNumberIdentification());
        this.setCallInTime(visitEvent.getVisitEventDatetime());
        this.setPatientID(visitEvent.getPatientID());
        this.setStaffID(visitEvent.getStaffID());
        this.setTimezoneName(visitEvent.getTimezoneName());
        this.setInternationalMobileStationEquipmentIdentity(visitEvent.getInternationalMobileStationEquipmentIdentity());
        this.gPSLocation = new GPSLocation();
        this.gPSLocation.setAccuracy(visitEvent.getCoordinateAccuracy());
        this.gPSLocation.setAltitude(visitEvent.getCoordinateAltitude());
        this.gPSLocation.setLatitude(visitEvent.getCoordinateLatitude());
        this.gPSLocation.setLongitude(visitEvent.getCoordinateLongitide());

    }
    public final VisitEvent getVisit(){
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setDialedNumberIdentificationService(this.getDnis());
        visitEvent.setAutomaticNumberIdentification(this.getAni());
        visitEvent.setVisitEventDatetime(this.getCallInTime());
        visitEvent.setPatientID(this.getPatientID());
        visitEvent.setStaffID(this.getStaffID());
        visitEvent.setTimezoneName(this.getTimezoneName());
        visitEvent.setInternationalMobileStationEquipmentIdentity(this.getInternationalMobileStationEquipmentIdentity());
        if(this.gPSLocation == null) {
            this.gPSLocation = new GPSLocation();
        }

        visitEvent.setCoordinateAccuracy(this.gPSLocation.getAccuracy());
        visitEvent.setCoordinateAltitude(this.gPSLocation.getAltitude());
        visitEvent.setCoordinateLatitude(this.gPSLocation.getLatitude());
        visitEvent.setCoordinateLongitide(this.gPSLocation.getLongitude());

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
        visitEvent.setCoordinateAccuracy(this.gPSLocation.getAccuracy());
        visitEvent.setCoordinateAltitude(this.gPSLocation.getAltitude());
        visitEvent.setCoordinateLatitude(this.gPSLocation.getLatitude());
        visitEvent.setCoordinateLongitide(this.gPSLocation.getLongitude());

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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
}
