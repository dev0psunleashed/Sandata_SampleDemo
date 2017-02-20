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

package com.sandata.lab.wcf.lookup.api;

import com.sandata.lab.data.model.wcf.lookup.*;
import org.apache.camel.Exchange;

import java.util.List;

/**
 * Lookup table services that expose WCF lookup endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public interface LookupTablesService {

    public void getReligions(Exchange exchange);
    public List<WcfReligion> getReligions(boolean updateCache);

    public void getStaffTypes(Exchange exchange);
    public List<WcfStaffType> getStaffTypes(boolean updateCache);

    public void getEthnicities(Exchange exchange);
    public List<WcfEthnicity> getEthnicities(boolean updateCache);

    public void getMaritalStatuses(Exchange exchange);
    public List<WcfMaritalStatus> getMaritalStatuses(boolean updateCache);

    public void getGenders(Exchange exchange);
    public List<WcfGender> getGenders(boolean updateCache);

    public void getMedClassifications(Exchange exchange);
    public List<WcfMedicationClassification> getMedClassifications(boolean updateCache);

    public void getMedStrengths(Exchange exchange);
    public List<WcfMedicationStrength> getMedStrengths(boolean updateCache);

    public void getMedRoutes(Exchange exchange);
    public List<WcfMedicationRoute> getMedRoutes(boolean updateCache);

    public void getStates(Exchange exchange);
    public List<WcfState> getStates(boolean updateCache);

    public void getLanguages(Exchange exchange);
    public List<WcfLanguage> getLanguages(boolean updateCache);

    public void getServices(Exchange exchange);
    public List<WcfService> getServices(boolean updateCache);

    public void getDisasterLevels(Exchange exchange);
    public List<WcfDisasterLevel> getDisasterLevels(boolean updateCache);

    public void getDnrs(Exchange exchange);
    public List<WcfDnr> getDnrs(boolean updateCache);

    public void getRefFormats(Exchange exchange);
    public List<WcfReferenceFormat> getRefFormats(boolean updateCache);

    public void getLimitBys(Exchange exchange);
    public List<WcfLimitBy> getLimitBys(boolean updateCache);

    public void getEligibilities(Exchange exchange);
    public List<WcfEligibility> getEligibilities(boolean updateCache);

    public void getAgencies(Exchange exchange);
    public List<WcfAgency> getAgencies(boolean updateCache);

    public void getAdminTypes(Exchange exchange);
    public List<WcfAdmissionType> getAdminTypes(boolean updateCache);

    public void getPayTypes(Exchange exchange);
    public List<WcfPayType> getPayTypes(boolean updateCache);

    public void getStaffClasses(Exchange exchange);
    public List<WcfStaffClass> getStaffClasses(boolean updateCache);

    public void getStaffStatuses(Exchange exchange);
    public List<WcfStaffStatus> getStaffStatuses(boolean updateCache);

    public void getStateTaxes(Exchange exchange);
    public List<WcfStateTax> getStateTaxes(boolean updateCache);

    public void getCityTaxes(Exchange exchange);
    public List<WcfCityTax> getCityTaxes(boolean updateCache);
}
