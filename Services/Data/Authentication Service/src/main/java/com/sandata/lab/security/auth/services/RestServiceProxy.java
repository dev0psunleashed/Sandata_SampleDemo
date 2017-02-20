package com.sandata.lab.security.auth.services;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


import java.util.HashMap;
import java.util.Map;

public class RestServiceProxy
{
    protected String postRequest(String url, HashMap<String,String> headerValue, String content) throws Exception
    {
        HttpPost httpPost = null;


        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try
        {

            logger.info(String.format("%s: Sending postRequest to %s ", this.getClass().getName(), url));

            httpPost = new HttpPost(url);


            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            if(!StringUtil.IsNullOrEmpty(content)) {
                httpPost.setEntity(new StringEntity(content));
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {

            // Release current connection to the connection pool once you are
            // done
            if (httpPost!=null)
                httpPost.releaseConnection();


        }
    }

    protected String putRequest(String url, HashMap<String,String> headerValue, String content) throws Exception
    {
        HttpPut httpPut = null;




        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try
        {

            logger.info(String.format("%s: Sending putRequest to %s ", this.getClass().getName(), url));

            httpPut = new HttpPut(url);


            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }

            if(!StringUtil.IsNullOrEmpty(content)) {
                httpPut.setEntity(new StringEntity(content));
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpPut);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {

            // Release current connection to the connection pool once you are
            // done
            if (httpPut!=null)
                httpPut.releaseConnection();


        }
    }

    protected String patchRequest(String url, HashMap<String,String> headerValue, String content) throws Exception
    {
        HttpPatch httpPatch = null;

        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try
        {

            logger.info(String.format("%s: Sending patchRequest to %s ", this.getClass().getName(), url));


            httpPatch = new HttpPatch(url);


            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httpPatch.setHeader(entry.getKey(), entry.getValue());
            }

            if(!StringUtil.IsNullOrEmpty(content)) {
                httpPatch.setEntity(new StringEntity(content));
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpPatch);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {

            // Release current connection to the connection pool once you are
            // done
            if (httpPatch!=null)
                httpPatch.releaseConnection();


        }
    }

    protected String getRequest(String url, HashMap<String,String> headerValue) throws Exception
    {
        HttpGet httpGET = null;
        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try
        {
            logger.info(String.format("%s: Sending getRequest to %s ", this.getClass().getName(), url));


            httpGET = new HttpGet(url);

            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httpGET.setHeader(entry.getKey(), entry.getValue());
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpGET);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {
            logger.stop();
            // Release current connection to the connection pool once you are
            // done
            if (httpGET!=null)
                httpGET.releaseConnection();
        }
    }

    protected String deleteRequest(String url, HashMap<String,String> headerValue) throws Exception
    {
        HttpDelete httpDelete = null;
        SandataLogger logger = AuthenticationLogger.CreateLogger();
        try
        {
            logger.info(String.format("%s: Sending deleteRequest to %s ", this.getClass().getName(), url));


            httpDelete = new HttpDelete(url);

            for (Map.Entry<String, String> entry : headerValue.entrySet())
            {
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpDelete);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {
            logger.stop();
            // Release current connection to the connection pool once you are
            // done
            if (httpDelete!=null)
                httpDelete.releaseConnection();
        }
    }


}
