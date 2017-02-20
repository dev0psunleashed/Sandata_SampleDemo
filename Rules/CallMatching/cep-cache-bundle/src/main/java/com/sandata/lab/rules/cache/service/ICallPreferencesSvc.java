package com.sandata.lab.rules.cache.service;

import com.sandata.lab.rules.cache.routes.CacheRoute;
import org.apache.camel.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/8/2016
 * Time: 8:40 AM
 */
public interface ICallPreferencesSvc {
    @Consume(uri = "direct:reply_" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID)
    public void onGetResultFromQueue(Exchange exchange);
    @Consume(uri = "direct:reply_" + CacheRoute.GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS)
    public void onGetAgencyIdResultFromQueue(Exchange exchange);
    @Consume(uri = "direct:reply_" + CacheRoute.GET_AGENCY_ID_FOR_DNIS_2)
    public void onGetAgencyIdResult2FromQueue(Exchange exchange);
    public void getCallPreferencesForAgency(String agencyId, IResultReady resultReady);
    public void getCallPreferencesForDnis(String dnis, IResultReady resultReady);
    public void getAgencyIdForDnis(String dnis, IResultReady resultReady);
    public void startUp();
    public void destroy();
}
