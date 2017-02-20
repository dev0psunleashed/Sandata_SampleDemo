package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 10/23/15
 * Time: 5:48 PM
 */

public class SaveScheduleEventsResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ScheduleEventSK")
    private long scheduleEventSk;

    @SerializedName("Index")
    private int index;

    @SerializedName("ErrorMessage")
    private String errorMessage;

    public long getScheduleEventSk() {
        return scheduleEventSk;
    }

    public void setScheduleEventSk(long scheduleEventSk) {
        this.scheduleEventSk = scheduleEventSk;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
