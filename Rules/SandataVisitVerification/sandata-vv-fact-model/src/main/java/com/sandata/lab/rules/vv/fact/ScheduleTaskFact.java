package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by thanhxle
 */
public class ScheduleTaskFact extends BaseObject {

    private static final long serialVersionUID = 1L;
    private String businessEntityId;
    private String taskId;
    private String beTaskId;
    private boolean criticalTaskInd;
    private BigInteger scheduleSk;


    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBeTaskId() {
        return beTaskId;
    }

    public void setBeTaskId(String beTaskId) {
        this.beTaskId = beTaskId;
    }

    public boolean isCriticalTaskInd() {
        return criticalTaskInd;
    }

    public void setCriticalTaskInd(boolean criticalTaskInd) {
        this.criticalTaskInd = criticalTaskInd;
    }

    public BigInteger getScheduleSk() {
        return scheduleSk;
    }

    public void setScheduleSk(BigInteger scheduleSk) {
        this.scheduleSk = scheduleSk;
    }
}
