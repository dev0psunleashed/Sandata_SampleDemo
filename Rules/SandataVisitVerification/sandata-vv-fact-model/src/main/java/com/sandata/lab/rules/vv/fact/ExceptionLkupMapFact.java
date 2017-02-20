package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khangle on 01/25/2017.
 */
public class ExceptionLkupMapFact extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Map<Long, ExceptionLkupFact> exceptionLkupFactMap;

    /**
     * Default constructor
     */
    public ExceptionLkupMapFact() {

    }

    /**
     * Constructor
     *
     * @param exceptionLkupList
     */
    public ExceptionLkupMapFact(List<ExceptionLookup> exceptionLkupList) {
        exceptionLkupFactMap = new HashMap<Long, ExceptionLkupFact>();

        for (ExceptionLookup exceptionLookup : exceptionLkupList) {
            ExceptionLkupFact exceptionLkupFact = new ExceptionLkupFact(exceptionLookup);
            addExceptionLkup(exceptionLookup.getExceptionID().longValue(), exceptionLkupFact);
        }
    }

    /**
     * Add an ExceptionLkupFact into map
     *
     * @param exceptionLkupId
     * @param exceptionLkupFact
     */
    public void addExceptionLkup(Long exceptionLkupId, ExceptionLkupFact exceptionLkupFact) {
        this.exceptionLkupFactMap.put(exceptionLkupId, exceptionLkupFact);
    }

    /**
     * Get an ExceptionLkupFact by ID
     *
     * @param exceptionLkupId
     * @return
     */
    public ExceptionLkupFact getExceptionLkupById(Long exceptionLkupId) {
        return this.exceptionLkupFactMap.get(exceptionLkupId);
    }

    public Map<Long, ExceptionLkupFact> getExceptionLkupFactMap() {
        return exceptionLkupFactMap;
    }

    public void setExceptionLkupFactMap(Map<Long, ExceptionLkupFact> exceptionLkupFactMap) {
        this.exceptionLkupFactMap = exceptionLkupFactMap;
    }
}
