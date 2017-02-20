package com.sandata.lab.rest.task.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Task;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;

public class TaskExt extends Task {

    private static final long serialVersionUID = 1L;

    public TaskExt(Task task) {
        BeanUtils.copyProperties(task, this);
    }

    @SerializedName("ServiceSK")
    protected BigInteger serviceSK;

    public BigInteger getServiceSK() {
        return serviceSK;
    }

    public void setServiceSK(BigInteger serviceSK) {
        this.serviceSK = serviceSK;
    }

    public Task getTask() {
        Task task = new Task();
        BeanUtils.copyProperties(this, task);
        return task;
    }
}
