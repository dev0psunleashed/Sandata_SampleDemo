package com.sandata.one.aggregator.visit.review.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class ReviewVisitTaskResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("TaskID")
    private String taskID;

    @SerializedName("TaskDescription")
    private String taskDescription;

    @SerializedName("Critial")
    private boolean isCritial;

    @SerializedName("Reading")
    private String reading;

    @SerializedName("ManuallyAdded")
    private boolean isManuallyAdded;

    /**
     * @return the taskID
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
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
     * @return the isCritial
     */
    public boolean isCritial() {
        return isCritial;
    }

    /**
     * @param isCritial the isCritial to set
     */
    public void setCritial(boolean isCritial) {
        this.isCritial = isCritial;
    }

    /**
     * @return the reading
     */
    public String getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    /**
     * @return the isManuallyAdded
     */
    public boolean isManuallyAdded() {
        return isManuallyAdded;
    }

    /**
     * @param isManuallyAdded the isManuallyAdded to set
     */
    public void setManuallyAdded(boolean isManuallyAdded) {
        this.isManuallyAdded = isManuallyAdded;
    }
}
