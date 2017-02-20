package com.sandata.lab.data.model.dl.model.extended.schedule;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.AuthorizationServiceUnitName;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;

public class ScheduleEvntExt extends ScheduleEvent {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("WorkHours")
    private BigDecimal workHours;
    
    @SerializedName("ScheduleHours")
    private BigDecimal scheduleHours;

    @SerializedName("ScheduleEventOvernight")
    private boolean scheduleEventOvernight;

    @SerializedName("AuthorizationServiceUnitName")
    private AuthorizationServiceUnitName authServiceUnitName;


    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public BigDecimal getScheduleHours() {
        return scheduleHours;
    }

    public void setScheduleHours(BigDecimal scheduleHours) {
        this.scheduleHours = scheduleHours;
    }

    public boolean isScheduleEventOvernight() {
        return scheduleEventOvernight;
    }

    public void setScheduleEventOvernight(boolean scheduleEventOvernight) {
        this.scheduleEventOvernight = scheduleEventOvernight;
    }

    public AuthorizationServiceUnitName getAuthServiceUnitName() {
        return authServiceUnitName;
    }

    public void setAuthServiceUnitName(AuthorizationServiceUnitName authServiceUnitName) {
        this.authServiceUnitName = authServiceUnitName;
    }
}
