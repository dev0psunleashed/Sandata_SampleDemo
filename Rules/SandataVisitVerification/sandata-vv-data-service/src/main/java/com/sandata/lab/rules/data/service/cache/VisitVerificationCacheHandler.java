package com.sandata.lab.rules.data.service.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sandata.lab.rules.vv.fact.ExceptionLkupMapFact;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.agency.AgencyVisitVerificationSettings;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.rules.data.service.routes.GetBusinessEntityCallPreferences;
import com.sandata.lab.rules.data.service.routes.GetBusinessEntityVisitVerificationSettingsRoute;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.CallPreferences;

/**
 * Created by thanhxle on 10/17/2016.
 */
public class VisitVerificationCacheHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(VisitVerificationCacheHandler.class);

    private AbstractVVDataServiceStore vvDataServiceStore;

    public AbstractVVDataServiceStore getVvDataServiceStore() {
        return vvDataServiceStore;
    }

    public void setVvDataServiceStore(AbstractVVDataServiceStore vvDataServiceStore) {
        this.vvDataServiceStore = vvDataServiceStore;
    }

    /**
     * @param exchange Exchange with BE_ID in the header
     */
    public void getCachedData(final Exchange exchange) {

        try {
        	
            String cacheKey = exchange.getIn().getHeader("cacheKey", String.class);
            String cachedData = vvDataServiceStore.getCachedData(cacheKey);
            if (cachedData != null) {
                exchange.getIn().setBody(cachedData);
                LOGGER.info("Found cached by key = {} , data = {}", cacheKey, cachedData);
            } else {
                LOGGER.info("No cached data found by key = {} ", cacheKey);
                exchange.getIn().setBody(null);
            }
        } catch (Exception exception) {
            //do nothing, just return null to make sure the exception not break the main flow down
            LOGGER.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
              		, this.getClass(), "getCachedData", "There is an exception while get data from REDIS, {}"),exception );
             
            exchange.getIn().setBody(null);
        }
    }

    /**
     * @param exchange Exchange with BE_ID in the header
     */
    public void insertData(final Exchange exchange) {
    	
    	try {
    		
	        String cacheKey = exchange.getIn().getHeader("cacheKey", String.class);
	        //CallPreferences callPreferences = ( CallPreferences ) exchange.getIn().getBody();
	        String cachedData = GSONProvider.ToJson(exchange.getIn().getBody());
	        vvDataServiceStore.storeData(cacheKey, cachedData);
	        
	        LOGGER.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
              		, this.getClass(), "insertData", "Put data to cache key = {} , data = {}"),cacheKey, cachedData );
	        
    	} catch(Exception exception) {
    		 //do nothing, just return null to make sure the exception not break the main flow down
            LOGGER.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
              		, this.getClass(), "insertData", "There is an exception while set data to REDIS, {}"),exception );
    	}
    }

    /**
     * @param exchange Exchange with BE_ID in the header
     */
    public void setCallPreferenceCacheKeyToHeader(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
        exchange.getIn().setHeader("cacheKey", GetBusinessEntityCallPreferences.CALL_PREFERENCES_CACHE_KEY_PREFIX + bsnEntId);
    }

    public void toCallPreferences(final Exchange exchange) {
        exchange.getIn().setBody(GSONProvider.FromJson(exchange.getIn().getBody(String.class), CallPreferences.class));
    }

    public void toVisitVerificationException(final Exchange exchange) {
    	List<ExceptionLookup>  excpLkupList = (List<ExceptionLookup>) GSONProvider.FromJson(exchange.getIn().getBody(String.class),  new TypeToken<List<ExceptionLookup>>(){}.getType());
        exchange.getIn().setBody(new ExceptionLkupMapFact(excpLkupList));
        //System.out.println("dfd");
    }

    public void setVisitVerificationSettingsCacheKeyToHeader(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
        exchange.getIn().setHeader("cacheKey", GetBusinessEntityVisitVerificationSettingsRoute.VV_SETTINGS_CACHE_KEY_PREFIX + bsnEntId);
    }

    public void toAgencyVisitVerificationSettings(final Exchange exchange) {
        exchange.getIn().setBody(GSONProvider.FromJson(exchange.getIn().getBody(String.class), AgencyVisitVerificationSettings.class));
    }

    public void toBsnEntIdList(final Exchange exchange) {
        exchange.getIn().setBody(GSONProvider.FromJson(exchange.getIn().getBody(String.class), new TypeToken<List<String>>(){}.getType()));
    }
    
    
    public void fromJsonToBusinessEntityExceptionListExt(final Exchange exchange) {
    	 exchange.getIn().setBody(GSONProvider.FromJson(exchange.getIn().getBody(String.class), new TypeToken<List<BusinessEntityExceptionListExt>>(){}.getType()));
    }
    
    public void toContractExceptionListExt(final Exchange exchange) {
        exchange.getIn().setBody(GSONProvider.FromJson(exchange.getIn().getBody(String.class), new TypeToken<List<ContractExceptionListExt>>(){}.getType()));
    }

}
