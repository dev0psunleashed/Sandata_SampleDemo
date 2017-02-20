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

import com.sandata.lab.common.utils.error.AbstractErrorHandler;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements HTTP requests using HttpURLConnection class.
 * <p/>
 *
 * @author David Rutgos
 */
public class HttpDataService extends AbstractErrorHandler {

    private Map<String, String> header;

    public HttpDataService() {
    }

    public HttpDataService(final SandataLogger logger) {
        super(logger);
    }

    public String post(final String urlString, final String postData, final String contentType)
            throws SandataRuntimeException {

        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", contentType);

            // Set headers
            if (this.header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("CHARSET", "UTF-8");
            connection.setRequestProperty("CONTENT-LENGTH", Integer.toString(postData.length()));
            connection.setUseCaches(false);

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            try(DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postDataBytes);
            }

            // Read the response
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (connection.getResponseCode() == 200) {
                return result.toString();
            }

            throw new SandataRuntimeException(
                    String.format("%s: post: Exception: Unexpected response from [%s]: Response Code: [%d]",
                            getClass().getName(), urlString, connection.getResponseCode()));
        }
        catch (Exception e) {
            handleFatalError(e, "post");
        }

        return null;
    }

    public String get(final String urlString, final String contentType)
            throws SandataRuntimeException {

        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", contentType);

            // Set headers
            if (this.header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Read the response
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (connection.getResponseCode() == 200) {
                return result.toString();
            }

            throw new SandataRuntimeException(
                    String.format("%s: get: Exception: Unexpected response from [%s]: Response Code: [%d]",
                            getClass().getName(), urlString, connection.getResponseCode()));
        }
        catch (Exception e) {
            handleFatalError(e, "get");
        }

        return null;
    }

    public void addHeader(final String key, final String value) {
        if (this.header == null) {
            header = new LinkedHashMap<> ();
        }

        header.put(key, value);
    }
}
