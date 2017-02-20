package com.sandata.lab.rules.cache.service;

import com.sandata.lab.rules.call.model.CallPreferences;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/7/2016
 * Time: 8:54 PM
 */
public interface IResultReady {
    void handleResultReady(Hashtable hashTable, String agencyId, String dnis, boolean ready);
    String getCallPreferencesByDnis(String Dnis);
    String getAgencyId();
    Hashtable getTable();
    String getDnis();
    boolean isReady();
}

