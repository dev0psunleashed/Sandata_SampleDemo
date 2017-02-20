package com.sandata.services.mobilehealth.data.models.contexts;

import com.sandata.services.mobilehealth.data.models.StatusCode;


public class ProcessFileContext {

    private String vendorList;
    private StatusCode returnStatus;
    private String returnMessage;
    
    private FileSetContext fileSetContext;
    private DbLogContext dbLogContext;
    
    public String getVendorList() {
        return vendorList;
    }

    public StatusCode getReturnStatus() {
        return returnStatus;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public FileSetContext getFileSetContext() {
        return fileSetContext;
    }

    public DbLogContext getDbLogContext() {
        return dbLogContext;
    }

    public void setVendorList(String vendorList) {
        this.vendorList = vendorList;
    }

    public void setReturnStatus(StatusCode returnStatus) {
        this.returnStatus = returnStatus;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public void setFileSetContext(FileSetContext fileSetContext) {
        this.fileSetContext = fileSetContext;
    }

    public void setDbLogContext(DbLogContext dbLogContext) {
        this.dbLogContext = dbLogContext;
    }

}
