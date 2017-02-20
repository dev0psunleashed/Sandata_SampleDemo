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

package com.sandata.lab.wcf.intake.integration.http;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.wcf.response.WcfResponse;
import com.sandata.lab.wcf.intake.integration.BaseIntegrationTestSupport;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Tests the integration of WCF REST call for Patients.
 * <p/>
 *
 * @author David Rutgos
 */
public class HttpImportPatientIntegrationTests extends BaseIntegrationTestSupport {

    @Test
    public void should_return_wcf_response_after_performing_import_patient() throws Exception {

        Assert.notNull(service);

        String json = "[\n" +
                "  {\n" +
                "    \"FirstName\": \"David\",\n" +
                "    \"LastName\": \"Rutgos2\",\n" +
                "    \"MiddleInitial\": \"M\",\n" +
                "    \"Email\": \"drutgos@sandata.com\",\n" +
                "    \"CellPhone\": \"(912)-616-5337\",\n" +
                "    \"HomePhone\": \"(230)-898-7825\",\n" +
                "    \"Address1\": \"1675 East 18th Street\",\n" +
                "    \"Address2\": \"JMeter \",\n" +
                "    \"City\": \"Brooklyn\",\n" +
                "    \"Zip\": \"11229\",\n" +
                "    \"IsOvernightOK\": false,\n" +
                "    \"InsuranceID\": 98000945960,\n" +
                "    \"MedicaidID\": 78069408861,\n" +
                "    \"MedicareID\": 14458783703,\n" +
                "    \"MedicalRecordNumber\": 23010361612,\n" +
                "    \"HourlyRate\": 24.37,\n" +
                "    \"BillRate\": 1.57,\n" +
                "    \"AgencyId\": 1,\n" +
                "    \"DateofBirth\": \"1/21/1962 00:00:00\",\n" +
                "    \"SSN\": \"816-46-9325\",\n" +
                "    \"MemberNumber\": 20064978498,\n" +
                "    \"TypeId\": 1,\n" +
                "    \"Age\": 41,\n" +
                "    \"MaritalStatusId\": 1,\n" +
                "    \"GenderId\": 1,\n" +
                "    \"LanguageId\": 76,\n" +
                "    \"EthnicityId\": 3,\n" +
                "    \"ReligionId\": 5,\n" +
                "    \"StateId\": 3,\n" +
                "    \"FirstDayWorked\": \"2/22/1968 00:00:00\",\n" +
                "    \"AgencyLocation\": \"934 Howard Place, Loomis, Rhode Island, 4747\",\n" +
                "    \"ReleasedDate\": \"8/10/1984 00:00:00\"\n" +
                "  }\n" +
                "]";

        String result = service.post(String.format("%s/ImportPatients", url), json, contentType);

        Assert.notNull(result);

        WcfResponse response = (WcfResponse) GSONProvider.FromJson(result, WcfResponse.class);

        Assert.notNull(response);
        Assert.notNull(response.getErrors());
        Assert.isTrue(response.getErrors().size() == 0);
        Assert.isTrue(response.getFailedCount() == 0);
        Assert.isTrue(response.getStatus().equals("Succeeded"));
        Assert.isTrue(response.getSucceededCount() == 1);

        System.out.println(result);
    }
}
