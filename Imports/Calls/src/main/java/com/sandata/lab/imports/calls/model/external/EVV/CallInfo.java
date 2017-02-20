package com.sandata.lab.imports.calls.model.external.EVV;

import com.sandata.lab.data.model.base.BaseObject;

public class CallInfo extends BaseObject{

    private static final long serialVersionUID = 1L;

    String callExpKey;
    String account;
    String exportKey;
    String sid;
    String callKey;
    String sequence;
    String task_id;
    String task_info;

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

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_info() {
        return task_info;
    }

    public void setTask_info(String task_info) {
        this.task_info = task_info;
    }
}
