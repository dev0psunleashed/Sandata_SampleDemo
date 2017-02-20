package com.sandata.lab.rest.visit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class AuditVisitTask extends BaseObject {

    private static final long serialVersionUID = -2591886387554783998L;

    @SerializedName("TaskID")
    private String taskId;

    @SerializedName("TaskDescription")
    private String taskDescription;

    @SerializedName("CriticalTaskIndicator")
    private Boolean criticalTaskIndicator;

    @SerializedName("TaskResultsReadingValue")
    private String taskResultsReadingValue;

    @SerializedName("VisitTaskPerformedIndicator")
    private Boolean visitTaskPerformedIndicator;

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskDescription
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * @param taskDescription the taskDescription to set
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * @return the criticalTaskIndicator
     */
    public Boolean getCriticalTaskIndicator() {
        return criticalTaskIndicator;
    }

    /**
     * @param criticalTaskIndicator the criticalTaskIndicator to set
     */
    public void setCriticalTaskIndicator(Boolean criticalTaskIndicator) {
        this.criticalTaskIndicator = criticalTaskIndicator;
    }

    /**
     * @return the taskResultsReadingValue
     */
    public String getTaskResultsReadingValue() {
        return taskResultsReadingValue;
    }

    /**
     * @param taskResultsReadingValue the taskResultsReadingValue to set
     */
    public void setTaskResultsReadingValue(String taskResultsReadingValue) {
        this.taskResultsReadingValue = taskResultsReadingValue;
    }

    /**
     * @return the visitTaskPerformedIndicator
     */
    public Boolean getVisitTaskPerformedIndicator() {
        return visitTaskPerformedIndicator;
    }

    /**
     * @param visitTaskPerformedIndicator the visitTaskPerformedIndicator to set
     */
    public void setVisitTaskPerformedIndicator(Boolean visitTaskPerformedIndicator) {
        this.visitTaskPerformedIndicator = visitTaskPerformedIndicator;
    }
}
