package com.sandata.lab.rules.vv.imports.model;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * 
 * @author thanh.le
 *
 */
public class EVVCallInfo extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * Call Exp Key
     */
    private String callExpKey;

    /**
     * stx account
     */
    private String account;
    
    /**
     * Export Key
     */
    private String exportKey;

    /**
     * Unique identifier for call
     */
    private String sid;

    /**
     * call key : key to get calls from EVV db
     */
    private String callKey;
    
    /**
     * sequence
     */
    private String sequence;
    
    /**
     * Task ID that entered in the call
     */
    private String taskId;
    
    /**
     * Task Info
     */
    private String taskInfo;

    public String getCallExpKey() {
        return callExpKey;
    }

    public void setCallExpKey(String callExpKey) {
        this.callExpKey = callExpKey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getExportKey() {
        return exportKey;
    }

    public void setExportKey(String exportKey) {
        this.exportKey = exportKey;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCallKey() {
        return callKey;
    }

    public void setCallKey(String callKey) {
        this.callKey = callKey;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    
}
