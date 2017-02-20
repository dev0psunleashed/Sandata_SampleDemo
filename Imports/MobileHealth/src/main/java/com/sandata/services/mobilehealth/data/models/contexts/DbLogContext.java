package com.sandata.services.mobilehealth.data.models.contexts;

import org.joda.time.LocalDateTime;

import com.sandata.services.mobilehealth.data.models.StatusCode;

public class DbLogContext {

    private String fileName;
    private String message;
    private String notify;
    private LocalDateTime processStartTime;
    private LocalDateTime processEndTime;
    private Integer sessionId;
    private StatusCode status;
    private String message2;
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the notify
     */
    public String getNotify() {
        return notify;
    }
    /**
     * @param notify the notify to set
     */
    public void setNotify(String notify) {
        this.notify = notify;
    }
    /**
     * @return the processStartTime
     */
    public LocalDateTime getProcessStartTime() {
        return processStartTime;
    }
    /**
     * @param processStartTime the processStartTime to set
     */
    public void setProcessStartTime(LocalDateTime processStartTime) {
        this.processStartTime = processStartTime;
    }
    /**
     * @return the processEndTime
     */
    public LocalDateTime getProcessEndTime() {
        return processEndTime;
    }
    /**
     * @param processEndTime the processEndTime to set
     */
    public void setProcessEndTime(LocalDateTime processEndTime) {
        this.processEndTime = processEndTime;
    }
    /**
     * @return the sessionId
     */
    public Integer getSessionId() {
        return sessionId;
    }
    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * @return the status
     */
    public StatusCode getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(StatusCode status) {
        this.status = status;
    }
    /**
     * @return the message2
     */
    public String getMessage2() {
        return message2;
    }
    /**
     * @param message2 the message2 to set
     */
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
