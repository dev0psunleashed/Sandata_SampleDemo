package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;

/**
 * Fact for sending to Kie Execution Server
 */
public class ExceptionLkupFact extends BaseObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long exceptionLookupSK;

    private long exceptionID;

    private String exceptionName;

    /**
     * Default constructor
     */
    public ExceptionLkupFact() {

    }

    /**
     * Constructor
     *
     * @param exceptionLookup
     */
    public ExceptionLkupFact(ExceptionLookup exceptionLookup) {
        this.setExceptionLookupSK(exceptionLookup.getExceptionLookupSK().longValue());
        this.setExceptionID(exceptionLookup.getExceptionID().longValue());
        this.setExceptionName(exceptionLookup.getExceptionName());
    }

    public long getExceptionLookupSK() {
        return exceptionLookupSK;
    }

    public void setExceptionLookupSK(long exceptionLookupSK) {
        this.exceptionLookupSK = exceptionLookupSK;
    }

    public long getExceptionID() {
        return exceptionID;
    }

    public void setExceptionID(long exceptionID) {
        this.exceptionID = exceptionID;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
