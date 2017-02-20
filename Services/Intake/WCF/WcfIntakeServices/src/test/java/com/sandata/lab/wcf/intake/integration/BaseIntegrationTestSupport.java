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

package com.sandata.lab.wcf.intake.integration;

import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.wcf.intake.BaseTestSupport;
import com.sandata.lab.wcf.intake.utils.log.HttpDataServiceLogger;

/**
 * Abstract base class that has common test properties and methods for integration.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class BaseIntegrationTestSupport extends BaseTestSupport {

    protected HttpDataService service;
    protected final String url = "http://dev-lab-napp01.sandata.com:5005";
    protected final String auth = "1234567890-FAKE";
    protected final String contentType = "application/json";

    @Override
    protected void onSetup() throws Exception {
        service = new HttpDataService(HttpDataServiceLogger.CreateLogger());
        service.addHeader("Authorization", auth);
    }
}
