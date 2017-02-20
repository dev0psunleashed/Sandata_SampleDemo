package com.sandata.lab.rest.visit.model;

import java.math.BigInteger;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class AuditVisitException extends BaseObject {

    private static final long serialVersionUID = 7774845467212262657L;

    @SerializedName("ExceptionID")
    private BigInteger exceptionID;

    @SerializedName("ExceptionName")
    private String exceptionName;

    /**
     * @return the exceptionID
     */
    public BigInteger getExceptionID() {
        return exceptionID;
    }

    /**
     * @param exceptionID the exceptionID to set
     */
    public void setExceptionID(BigInteger exceptionID) {
        this.exceptionID = exceptionID;
    }

    /**
     * @return the exceptionName
     */
    public String getExceptionName() {
        return exceptionName;
    }

    /**
     * @param exceptionName the exceptionName to set
     */
    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
