package com.sandata.lab.data.document.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * The entity that keeps track of the file upload status.
 * <p/>
 *
 * @author David Rutgos
 */
public class DocumentSaveStatus extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("FileSize")
    private long fileSize = 0L;

    @SerializedName("BytesTransferred")
    private long bytesTransferred = 0L;

    @SerializedName("Percentage")
    private int percentage = 0;

    @SerializedName("Status")
    private String status = "Pending";

    @SerializedName("ErrorMessage")
    private String errorMessage;

    @SerializedName("Started")
    private Date started;

    @SerializedName("Completed")
    private Date completed;

    @SerializedName("ElapsedTime")
    private String elapsedTime;

    @SerializedName("DocumentID")
    private String documentId;

    @SerializedName("StatusID")
    private String statusId;

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getBytesTransferred() {
        return bytesTransferred;
    }

    public void setBytesTransferred(long bytesTransferred) {
        this.bytesTransferred = bytesTransferred;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
