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

package com.sandata.lab.common.utils.http;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.HttpDataServiceLogger;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the implementation of the HTTP requests using HttpURLConnection class.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class HttpDataServiceTests {

    private HttpDataService service;

    @Test
    public void should_throw_ioexception_when_the_response_code_is_not_200_for_post() throws Exception {

        try {
            service.post("http://dev-lab-napp01.sandata.com:5005", "[]", null);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("post: Fatal: EXCEPTION: java.io.IOException: Server returned HTTP response code: 400 for URL: http://dev-lab-napp01.sandata.com:5005"));
        }
    }

    @Test
    public void should_throw_ioexception_when_the_response_code_is_not_200_for_get() throws Exception {

        try {
            service.get("http://dev-lab-napp01.sandata.com:5005", null);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("get: Fatal: EXCEPTION: java.io.IOException: Server returned HTTP response code: 400 for URL: http://dev-lab-napp01.sandata.com:5005"));
        }
    }

    @Test
    public void should_throw_nullpointerexception_when_the_postdata_is_null() throws Exception {

        try {
            service.post("http://dev-lab-napp01.sandata.com:5005", null, null);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("post: Fatal: EXCEPTION: java.lang.NullPointerException: null"));
        }
    }

    @Test
    public void should_throw_malformedurlexception_when_the_url_is_null_post() throws Exception {

        try {
            service.post(null, null, null);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("post: Fatal: EXCEPTION: java.net.MalformedURLException: null"));
        }
    }

    @Test
    public void should_throw_malformedurlexception_when_the_url_is_null_get() throws Exception {

        try {
            service.get(null, null);

            // Should not hit this since we are expecting an exception
            Assert.assertTrue(false);
        }
        catch(Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().contains("get: Fatal: EXCEPTION: java.net.MalformedURLException: null"));
        }
    }

    @Before
    public void setUp() throws Exception {
        service = new HttpDataService(HttpDataServiceLogger.CreateLogger());
    }
}
