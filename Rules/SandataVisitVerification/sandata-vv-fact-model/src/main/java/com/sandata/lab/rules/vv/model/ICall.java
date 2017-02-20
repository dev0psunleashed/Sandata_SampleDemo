package com.sandata.lab.rules.vv.model;


import java.math.BigInteger;
import java.util.List;

/**
 * Created by tom.dornseif on 11/17/2015.
 */
public interface ICall {

    String getAni();

    void setAni(String ani);

    String getStaffId();

    void setStaffId(String staffId);

    java.util.Date getCallInTime();

    void setCallInTime(java.util.Date callInTime);


    int getState();

    void setState(int state);

    String getScheduleId();

    void setScheduleId(String scheduleId);

    String getVisitId();

    void setVisitId(String visitId);

    String getDnis();

    void setDnis(String dnis);

    List<VisitTask> getTasks();

    void setTasks(List<VisitTask> tasks);

    String getClientId();

    void setClientId(String clientId);


    GPSLocation getGps();

    void setGps(GPSLocation gps);

}
