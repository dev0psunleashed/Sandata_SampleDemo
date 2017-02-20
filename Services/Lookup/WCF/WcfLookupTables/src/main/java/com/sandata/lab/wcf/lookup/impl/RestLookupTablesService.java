/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.wcf.lookup.impl;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.time.TimeFormat;
import com.sandata.lab.data.model.wcf.lookup.*;
import com.sandata.lab.wcf.lookup.api.LookupTablesService;
import com.sandata.lab.wcf.lookup.utils.log.WcfLookupTablesLogger;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PropertyInject;
import org.apache.cxf.message.MessageContentsList;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;

/**
 * REST implementation of the LookupTablesService interface.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestLookupTablesService implements LookupTablesService {

    private final HashMap<String, List> lookupTableCache = new HashMap<>();
    private LocalDateTime lastCacheUpdateTime = null;

    @PropertyInject("cache.update.interval")
    private long updateCacheInterval;   // In minutes

    @PropertyInject("lookup.tables.url")
    private String url;

    // TODO: Common service to get authorizations
    private final String auth = "1234567890-FAKE";

    @Override
    public void getReligions(Exchange exchange) {

        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getReligions(exchange)");
        logger.start();

        exchange.getIn().setBody(getReligions(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfReligion> getReligions(boolean updateCache) {

        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getReligions()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetReligions", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getStaffTypes(Exchange exchange) {

        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getStaffTypes(exchange)");
        logger.start();

        exchange.getIn().setBody(getStaffTypes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfStaffType> getStaffTypes(boolean updateCache) {

        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getStaffTypes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetStaffTypes", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getEthnicities(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getEthnicities(exchange)");
        logger.start();

        exchange.getIn().setBody(getEthnicities(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfEthnicity> getEthnicities(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getEthnicities()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetEthnicities", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getMaritalStatuses(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getMaritalStatuses(exchange)");
        logger.start();

        exchange.getIn().setBody(getMaritalStatuses(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfMaritalStatus> getMaritalStatuses(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getMaritalStatuses()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetMaritalStatuses", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getGenders(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getGenders(exchange)");
        logger.start();

        exchange.getIn().setBody(getGenders(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfGender> getGenders(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getGenders()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetGenders", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getMedClassifications(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getMedClassifications(exchange)");
        logger.start();

        exchange.getIn().setBody(getMedClassifications(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfMedicationClassification> getMedClassifications(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getMedClassifications()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetMedicationClassifications", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getMedStrengths(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getMedStrengths(exchange)");
        logger.start();

        exchange.getIn().setBody(getMedStrengths(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfMedicationStrength> getMedStrengths(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getMedStrengths()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetMedicationStrengths", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getMedRoutes(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getMedRoutes(exchange)");
        logger.start();

        exchange.getIn().setBody(getMedRoutes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfMedicationRoute> getMedRoutes(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getMedRoutes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetMedicationRoutes", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getStates(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getStates(exchange)");
        logger.start();

        exchange.getIn().setBody(getStates(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfState> getStates(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getStates()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetStates", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getLanguages(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getLanguages(exchange)");
        logger.start();

        exchange.getIn().setBody(getLanguages(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfLanguage> getLanguages(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getLanguages()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetLanguages", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getServices(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getServices(exchange)");
        logger.start();

        exchange.getIn().setBody(getServices(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfService> getServices(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getServices()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetServices", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getDisasterLevels(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getDisasterLevels(exchange)");
        logger.start();

        exchange.getIn().setBody(getDisasterLevels(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfDisasterLevel> getDisasterLevels(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getDisasterLevels()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetDisasterLevels", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getDnrs(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getDnrs(exchange)");
        logger.start();

        exchange.getIn().setBody(getDnrs(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfDnr> getDnrs(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getDnrs()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetDNRs", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getRefFormats(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getRefFormats(exchange)");
        logger.start();

        exchange.getIn().setBody(getRefFormats(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfReferenceFormat> getRefFormats(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getRefFormats()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetReferenceFormats", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getLimitBys(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getLimitBys(exchange)");
        logger.start();

        exchange.getIn().setBody(getLimitBys(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfLimitBy> getLimitBys(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getLimitBys()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetLimitBys", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getEligibilities(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getEligibilities(exchange)");
        logger.start();

        exchange.getIn().setBody(getEligibilities(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfEligibility> getEligibilities(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getEligibilities()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetEligibilities", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getAgencies(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getAgencies(exchange)");
        logger.start();

        exchange.getIn().setBody(getAgencies(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfAgency> getAgencies(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getAgencies()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetAgencies", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getAdminTypes(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getAdminTypes(exchange)");
        logger.start();

        exchange.getIn().setBody(getAdminTypes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfAdmissionType> getAdminTypes(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getAdminTypes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetAdmissionTypes", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getPayTypes(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getPayTypes(exchange)");
        logger.start();

        exchange.getIn().setBody(getPayTypes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfPayType> getPayTypes(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getPayTypes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetPayTypes", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getStaffClasses(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getStaffClasses(exchange)");
        logger.start();

        exchange.getIn().setBody(getStaffClasses(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfStaffClass> getStaffClasses(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getStaffClasses()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetStaffClasses", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getStaffStatuses(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getStaffStatuses(exchange)");
        logger.start();

        exchange.getIn().setBody(getStaffStatuses(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfStaffStatus> getStaffStatuses(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getStaffStatuses()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetStaffStatuses", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getStateTaxes(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getStateTaxes(exchange)");
        logger.start();

        exchange.getIn().setBody(getStateTaxes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfStateTax> getStateTaxes(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getStateTaxes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetStateTaxes", updateCache, logger);

        logger.stop();

        return list;
    }

    @Override
    public void getCityTaxes(Exchange exchange) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger(exchange);
        logger.setMethodName("getCityTaxes(exchange)");
        logger.start();

        exchange.getIn().setBody(getCityTaxes(updateCache(exchange, logger)));

        logger.stop();
    }

    @Override
    public List<WcfCityTax> getCityTaxes(boolean updateCache) {
        SandataLogger logger = WcfLookupTablesLogger.CreateLogger();
        logger.setMethodName("getCityTaxes()");

        // If updateCache is false, check to see if the cache still needs to be refreshed based on the time.
        if (! updateCache) {
            updateCache = shouldUpdateCache(logger);
        }

        List list = httpRequest("GetCityTaxes", updateCache, logger);

        logger.stop();

        return list;
    }

    private List httpRequest(final String methodName, final boolean updateCache, final SandataLogger logger) {

        List cachedList = lookupTableCache.get(methodName);

        if (updateCache
                // If list is not cached, then get it from the service
                || cachedList == null) {

            HttpDataService service = new HttpDataService(logger);
            service.addHeader("Authorization", auth);

            String religionsJsonString = service.get(String.format("%s/%s", url, methodName), "application/json");

            List list = (List) GSONProvider.FromJson(religionsJsonString, List.class);

            updateCache(methodName, list);

            logger.info("***** Service *****");

            return list;
        }

        logger.info("***** Cache *****");

        return cachedList;
    }

    private boolean shouldUpdateCache(final SandataLogger logger) {

        if (lastCacheUpdateTime == null) {

            logger.info(String.format("Lookup table cache must be updated: lastCacheUpdateTime == null"));
            return true;
        }

        if (lastCacheUpdateTimeExpired(logger)) {

            logger.info(String.format("Lookup table cache time expired!"));
            return true;
        }

        return false;
    }

    private boolean updateCache(final Exchange exchange, final SandataLogger logger) {

        Message message = exchange.getIn();

        MessageContentsList params = message.getBody(MessageContentsList.class);

        Boolean bUpdateCache = (Boolean) params.get(0);

        logger.info(String.format("Update lookup table cache: Update Cache: %s", bUpdateCache));

        return bUpdateCache;
    }

    private void updateCache(final String key, final List value) {

        lastCacheUpdateTime = new LocalDateTime();
        lookupTableCache.put(key, value);
    }

    private boolean lastCacheUpdateTimeExpired(final SandataLogger logger) {

        boolean bResult = false;
        String method = logger.getMethodName();
        logger.setMethodName("lastCacheUpdateTimeExpired");

        LocalDateTime now = LocalDateTime.now();

        Duration duration = new Duration(lastCacheUpdateTime.toDateTime(DateTimeZone.UTC), now.toDateTime(DateTimeZone.UTC));
        long seconds = duration.getStandardSeconds();
        long minutes = duration.getStandardMinutes();

        logger.info(String.format("Last Cache Update: %s", TimeFormat.SecondsToString(seconds)));

        if(minutes >= updateCacheInterval) {
            bResult = true;
        }

        logger.setMethodName(method);
        return bResult;
    }
}
