package com.sandata.lab.rules.cache.client;


import com.sandata.lab.rules.cache.service.ICallPreferencesSvc;
import com.sandata.lab.rules.cache.service.IResultReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/13/2016
 * Time: 7:23 AM
 */
public class CacheClient implements ICacheClient {
    
    ICallPreferencesSvc callPreferencesSvc;
    Logger logger = LoggerFactory.getLogger(CacheClient.class);
    private static CacheClient client;
    public static CacheClient getClient() throws Exception {
        if( CacheClient.client == null) {
            throw new Exception("Lost References to CacheClient");
        }
        return CacheClient.client;
    }
    public static void setClient(CacheClient client) {
        CacheClient.client = client;
    }
    public CacheClient(){
        CacheClient.setClient(this);
    }

    //Bean Properties
    public ICallPreferencesSvc getCallPreferencesSvc() {
        return callPreferencesSvc;
    }
    public void setCallPreferencesSvc(ICallPreferencesSvc callPrefSvc) {
        this.callPreferencesSvc = callPrefSvc;
    }

    public void startUp() {
        CacheClient.client = this;
        System.out.println("=====>CacheClient StartUp() called");
        callPreferencesSvc.startUp();
    }
    public String getCallPreferencesForAgencyId(String agencyId) {

        IResultReady callBack = new ResultReady();
        this.callPreferencesSvc.getCallPreferencesForAgency(agencyId, callBack );
        while(!callBack.isReady())  {
            try {
                Thread.sleep(100);
            }catch( InterruptedException ie ) {
                logger.error("Thread threw interrupted exception!");
            }
        }
        String json = (String)callBack.getTable().get(agencyId);
        return json;
    }

    public String getCallPreferencesForDnis(String dnis) {
        boolean ready = false;
        IResultReady callBack = new ResultReady();
        this.callPreferencesSvc.getCallPreferencesForDnis(dnis, callBack );
        while(!callBack.isReady())  {
            try {
                Thread.sleep(10);
            }catch(InterruptedException ie) {
                logger.error("Thread threw interrupted exception!");
            }
        }
        String json = (String)callBack.getCallPreferencesByDnis(dnis);
        return json;

    }

    public String getDnisForAgencdyId(String dnis) {
        IResultReady callBack = new ResultReady();
        this.callPreferencesSvc.getAgencyIdForDnis(dnis, callBack );
        while(!callBack.isReady())  {
            try {
                Thread.sleep(10);
            }catch(InterruptedException ie){
                logger.error("Thread threw interrupted exception!");
            }
        }
        return (String)callBack.getTable().get(dnis);
    }

    class ResultReady implements IResultReady {
        Hashtable table;
        String agencyId;
        String dnis;
        boolean ready = false;
        @Override
        public void handleResultReady(Hashtable hashTable, String agencyId, String dnis, boolean ready) {
            this.table = hashTable;
            this.agencyId = agencyId;
            this.dnis = dnis;
            this.ready = ready;
        }

        @Override
        public String getCallPreferencesByDnis(String Dnis) {
            if(this.agencyId == null) {
                return null;
            }
            else {
                return (String)this.table.get(this.agencyId);
            }
        }

        @Override
        public Hashtable getTable() {
            return this.table;
        }
        @Override
        public String getAgencyId() {
            return this.agencyId;
        }
        @Override
        public String getDnis() {
            return this.dnis;
        }
        @Override
        public boolean isReady() {return this.ready;}
    }
}


