package com.sandata.staff.imports.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RestServiceProxy
{
    private Logger logger = LoggerFactory.getLogger(RestServiceProxy.class);

    public HttpResponse request(String url, HashMap<String,String> headerValue, String content) throws Exception
    {
        HttpPost httppost = null;
        try
        {
            logger.info(this.getClass().getName(), "Sending request to " + url);

            HttpClient httpclient = HttpClientBuilder.create().build();
            httppost = new HttpPost(url);

            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httppost.setHeader(entry.getKey(), entry.getValue());
            }

            httppost.setEntity(new StringEntity(content));

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            logger.info("Response: " + responseString);

            return response;
        }
        catch (Exception e)
        {
            logger.error(this.getClass().getName(),e);
            throw e;
        }
        finally
        {
            // Release current connection to the connection pool once you are
            // done
            if (httppost!=null)
                httppost.releaseConnection();
        }
    }
}
