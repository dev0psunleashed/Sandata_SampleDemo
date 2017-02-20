package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by khangle on 09/22/2016.
 */
public abstract class AbstractEVVCallStore {
    public static final String EVV_CALL_STORE_KEY_PREFIX = "EVVMapDBCallStore.";

    /**
     * stores EVVCall to later used to compare duplicate calls
     * @param calls
     */
    public abstract void storeCalls(List<EVVCall> calls);

    /**
     * gets from Store that is store unprocessed calls
     * @param evvCall
     * @return
     */
    public abstract EVVCall getCallFromStore(EVVCall evvCall);

    /**
     * deletes a EVVCall from Store
     * @param evvCall
     */
    public abstract void deleteCallFromStore(EVVCall evvCall);

    /**
     * gets all unprocessed calls from Store 
     * @return
     */
    public abstract List<EVVCall> getUnprocessedCalls();

    /**
     * add unique key to every EVVCall to store
     * @param evvCall
     * @return
     */
    protected String getKey(EVVCall evvCall) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EVV_CALL_STORE_KEY_PREFIX);

        if(!StringUtil.IsNullOrEmpty(evvCall.getDnis())) {
            stringBuilder.append(evvCall.getDnis());
        }

        if(evvCall.getCallDtimeUTC() != null) {
            DateTime dateTime = evvCall.getCallDtimeUTC();
            stringBuilder.append(Long.toString(dateTime.getMillis()));
        }

        if(!StringUtil.IsNullOrEmpty(evvCall.getAni())) {
            stringBuilder.append(evvCall.getAni());
        }

        return stringBuilder.toString();
    }
}
