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

package com.sandata.lab.wcf.export.integration.http;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.wcf.export.BaseTestSupport;
import com.sandata.lab.wcf.export.utils.log.HttpDataServiceLogger;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Tests the WCF export schedule call.
 *
 * NOTE: Integration tests package should be skipped during unit tests.
 * <p/>
 *
 * @author David Rutgos
 */
public class HttpExportScheduleIntegrationTests extends BaseTestSupport {

    private HttpDataService service;

    private final String url = "http://dev-lab-napp01.sandata.com:5005";
    private final String auth = "1234567890-FAKE";
    private final String contentType = "application/json";

    @Test
    public void should_return_response_after_performing_export_schedule() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/ExportSchedules/08-03-2015/08-04-2015/95/108", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_empty_response_array_after_performing_export_schedule_for_patient_that_does_exist()
            throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/ExportSchedules/08-03-2015/08-04-2015/95/1", url), contentType);

        Assert.notNull(result);

        Assert.isTrue(result.equals("[]"));
    }

    @Test
    public void should_throw_filenotfoundexception_when_params_are_missing() throws Exception {

        try {
            Assert.notNull(service);

            String result = service.get(String.format("%s/ExportSchedules/08-03-2015/08-04-2015", url), contentType);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().contains("get: Fatal: EXCEPTION: java.io.FileNotFoundException"));
        }
    }

    @Override
    protected void onSetup() throws Exception {
        service = new HttpDataService(HttpDataServiceLogger.CreateLogger());
        service.addHeader("Authorization", auth);
    }
}
