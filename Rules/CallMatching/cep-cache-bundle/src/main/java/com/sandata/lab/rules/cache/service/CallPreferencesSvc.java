package com.sandata.lab.rules.cache.service;

import com.sandata.lab.rules.cache.routes.CacheRoute;
import org.apache.camel.*;
import org.apache.camel.component.cache.CacheConstants;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/7/2016
 * Time: 8:18 PM
 */
public class CallPreferencesSvc implements ICallPreferencesSvc {

    private Hashtable<String, String> callPreferencesHashTable;
    private Hashtable<String, String> dnisHashTable;
    private Hashtable<String,ResultReadyItem> callPreferenceByDnisRequestList;
    private Hashtable<String,ResultReadyItem> callPreferenceByAgecnyIdRequestList;

    @Produce(uri = "activemq:queue:" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID)
    ProducerTemplate producer;

    @Produce(uri = "activemq:queue:" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS)
    ProducerTemplate producer1;

    @Produce(uri = "activemq:queue:" + CacheRoute.GET_AGENCY_ID_FOR_DNIS_2)
    ProducerTemplate producer2;

    public void startUp() {
        this.callPreferencesHashTable = new Hashtable<String, String>();
        this.dnisHashTable = new Hashtable<String, String>();
        this.callPreferenceByAgecnyIdRequestList = new Hashtable<>();
        this.callPreferenceByDnisRequestList = new Hashtable<>();
        System.out.println("========>Starting Cache Service for Call Preferences.");
    }

    public void destroy() {

        System.out.println("========>Destroying Cache Service for Call Preferences.");
    }

    @Consume(uri = "direct:reply_" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID)
    public void onGetResultFromQueue(Exchange exchange) {
        Message in = exchange.getIn();
        String agencyId = (String) in.getHeader(CacheConstants.CACHE_KEY);
        if(agencyId == null) {
            agencyId = (String) in.getHeader(CacheRoute.CALL_MATCHING_KEY);
        }
        ResultReadyItem resultReadyItem = callPreferenceByAgecnyIdRequestList.get(agencyId);
        if(resultReadyItem != null) {
            IResultReady resultReady = resultReadyItem.getResultReady();
            String dnis = resultReadyItem.getDnis();
            //CallPreferences callPreferences = in.getBody(CallPreferences.class);
            String jsonStrCallPref = in.getBody(String.class);
            if (jsonStrCallPref != null && agencyId != null) {
                callPreferencesHashTable.put(agencyId, jsonStrCallPref);
            }
            if (resultReady != null) {
                resultReady.handleResultReady(callPreferencesHashTable, agencyId, dnis, true);
            }
            callPreferenceByAgecnyIdRequestList.remove(agencyId);
        }
        else {
            String jsonStrCallPref = in.getBody(String.class);
            if (jsonStrCallPref != null && agencyId != null) {
                callPreferencesHashTable.put(agencyId, jsonStrCallPref);
            }
        }
    }
    @Consume(uri = "direct:reply_" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS)
    public void onGetAgencyIdResultFromQueue(Exchange exchange) {
        Message in = exchange.getIn();
        String dnis = (String) in.getHeader(CacheConstants.CACHE_KEY);
        if( dnis == null) {
            dnis = (String) in.getHeader(CacheRoute.CALL_MATCHING_KEY);
        }
        String agencyId = in.getBody(String.class);
        if(agencyId != null && dnis != null) {
            dnisHashTable.put(dnis, agencyId);
        }
        ResultReadyItem resultReadyItem = callPreferenceByDnisRequestList.get(dnis);
        IResultReady resultReady = resultReadyItem.getResultReady();

        if(resultReady != null) {
            //not ready yet unless we couldnt locate a agency, but it could not even
            // get to this call in that case so
            // 0% chance this next if code would run!  will test anyway for
            // debugging only!!!!
            if(agencyId == null) {//|| callPreferencesHashTable.get(agencyId) != null) {
                resultReady.handleResultReady(callPreferencesHashTable, agencyId, dnis, true);
            }
            else {
               //update the resultReady Lists.
                resultReadyItem.setAgencyId(agencyId);
                callPreferenceByAgecnyIdRequestList.put(agencyId, resultReadyItem);
                callPreferenceByDnisRequestList.remove(dnis);
                producer.sendBody(agencyId);
            }

        }
    }

    @Consume(uri = "direct:reply_" + CacheRoute.GET_AGENCY_ID_FOR_DNIS_2)
    public void onGetAgencyIdResult2FromQueue(Exchange exchange) {
        Message in = exchange.getIn();
        String dnis = (String) in.getHeader(CacheConstants.CACHE_KEY);
        if(dnis == null) {
            dnis = (String) in.getHeader(CacheRoute.CALL_MATCHING_KEY);
        }
        //let me try this first camel should marshal automagically String jsonString = in.getBody();
        String agencyId = null;
        if(in.getBody() != null && dnis != null) {
            agencyId = in.getBody(String.class);
            dnisHashTable.put(dnis, agencyId);
        }
        ResultReadyItem resultReadyItem = callPreferenceByDnisRequestList.get(dnis);
        IResultReady resultReady = null;
        if(resultReadyItem != null) {
            resultReady = resultReadyItem.getResultReady();
        }

        if(resultReady != null) {
            resultReady.handleResultReady(dnisHashTable, agencyId, dnis, true);
            if(agencyId != null && callPreferencesHashTable.get(agencyId) == null) {
                //we have an agency but no callPreference string
                //this will call the route and update the cache and trigger the
                // consume but no resultready will be in the hashtable so nothing
                // will handle it in code which is desired result.
                producer.sendBody(agencyId);
            }
            callPreferenceByDnisRequestList.remove(dnis);
        }
    }

    public void getCallPreferencesForAgency(String agencyId, IResultReady resultReady) {
        if(callPreferencesHashTable.contains(agencyId)) {
            resultReady.handleResultReady(callPreferencesHashTable, agencyId, null, true);
        }
        else {
            ResultReadyItem resultReadyItem = new ResultReadyItem(agencyId, null, resultReady);
            callPreferenceByAgecnyIdRequestList.put(agencyId, resultReadyItem);
            producer.sendBody(agencyId);
        }

    }

    public void getCallPreferencesForDnis(String dnis, IResultReady resultReady) {
        if(dnisHashTable.contains(dnis) && callPreferencesHashTable.contains(dnisHashTable.get(dnis))) {
            String agencyId = dnisHashTable.get(dnis);
            resultReady.handleResultReady(callPreferencesHashTable, agencyId, dnis, true);
        }
        else if(dnisHashTable.contains(dnis)){
            String agencyId = dnisHashTable.get(dnis);

            ResultReadyItem resultReadyItem = new ResultReadyItem(agencyId, dnis, resultReady);
            callPreferenceByAgecnyIdRequestList.put(agencyId, resultReadyItem);
            producer.sendBody(dnisHashTable.get(dnis));
        }
        else {
            ResultReadyItem resultReadyItem = new ResultReadyItem(null, dnis, resultReady);
            callPreferenceByDnisRequestList.put(dnis, resultReadyItem);
            producer1.sendBody(dnis);
        }

    }

    public void getAgencyIdForDnis(String dnis, IResultReady resultReady) {
        if(dnisHashTable.contains(dnis)) {
            resultReady.handleResultReady(dnisHashTable, null, dnis, true);
        }
        else {
            ResultReadyItem resultReadyItem = new ResultReadyItem(null, dnis, resultReady);
            callPreferenceByDnisRequestList.put(dnis, resultReadyItem);
            producer2.sendBody(dnis);
        }
    }

    public class ResultReadyItem {
        private String agencyId;
        private String dnis;
        private IResultReady resultReady;

        public ResultReadyItem(String agencyId, String dnis, IResultReady resultReady) {
            this.setAgencyId(agencyId);
            this.setDnis(dnis);
            this.setResultReady(resultReady);
        }

        public String getAgencyId() {
            return agencyId;
        }

        public void setAgencyId(String agencyId) {
            this.agencyId = agencyId;
        }

        public String getDnis() {
            return dnis;
        }

        public void setDnis(String dnis) {
            this.dnis = dnis;
        }

        public IResultReady getResultReady() {
            return resultReady;
        }

        public void setResultReady(IResultReady resultReady) {
            this.resultReady = resultReady;
        }
    }
}


