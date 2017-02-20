package com.sandata.lab.rest.sched.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Created by khangle on 09/12/2016.
 */
public class ScheduleValidationResponse extends BaseObject {
    private static final long serialVersionUID = 1L;

    @SerializedName("Status")
    protected boolean status;

    @SerializedName("Message")
    protected String message;

    /**
     * Constructor
     *
     * @param status
     * @param message
     */
    public ScheduleValidationResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
