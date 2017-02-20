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
 * Tests the integration of WCF REST call for Schedules.
 * <p/>
 *
 * @author David Rutgos
 */
public class HttpImportScheduleIntegrationTests extends BaseIntegrationTestSupport {

    @Test
    public void should_return_wcf_response_after_performing_import_schedule() throws Exception {

        Assert.notNull(service);

        String json = "[{\n" +
                "  \"StaffId\": 95,\n" +
                "  \"PatientId\": 108,\n" +
                "  \"StartDate\": \"08-06-2015\",\n" +
                "  \"EndDate\": \"08-07-2015\",\n" +
                "  \"FromTime\": \"12:00\",\n" +
                "  \"EndTime\": \"16:00\",\n" +
                "  \"FrequencyId\": 2,\n" +
                "  \"NumberOfOccurrences\":10,\n" +
                "  \"DayOfMonth\":5,\n" +
                "  \"ScheduleWeekDays\":[1,2,3,4,5]\n" +
                "}]";

        String result = service.post(String.format("%s/ImportSchedules", url), json, contentType);

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
