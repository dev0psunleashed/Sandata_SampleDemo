package com.sandata.lab.rules.call.matching.service.cache;

import java.util.List;

/**
 * Created by thanhxle on 10/14/2016.
 */
public abstract class AbstractCallMatchingStore {
    /**
     * stores a unique string that combination of dnis,ani,staff and call date time
     * @param callIdentifierKey String
     */
    public abstract void storeCallIdentifier(String callIdentifierKey, String callIdentifierValue);

    /**
     * get from cache by key that combination of dnis,ani,staff and call date time
     * @param callIdentifierKey String
     */
    public abstract String getCallIdentifier(String callIdentifierKey);


}
