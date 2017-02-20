package com.sandata.lab.rules.cache.client;

import com.sandata.lab.rules.cache.service.ICallPreferencesSvc;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/13/2016
 * Time: 7:28 AM
 */
public interface ICacheClient {
    ICallPreferencesSvc getCallPreferencesSvc();
    void setCallPreferencesSvc(ICallPreferencesSvc callPreferencesSvc);
    void startUp();
    String getCallPreferencesForAgencyId(String agencyId);
    String getCallPreferencesForDnis(String dnis);
    String getDnisForAgencdyId(String dnis);

}
