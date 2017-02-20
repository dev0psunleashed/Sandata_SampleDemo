package com.sandata.imports.schedules.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BaseRouteIntegrationTest {


    public HttpResponse jsonRequest(Object requestObject, String url)
    {
        HttpResponse response = null;

        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);

            // add request header
            request.addHeader("Authorization", "Basic YWRtaW46YWRtaW4=");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();


            StringEntity stringEntity = new StringEntity(gson.toJson(requestObject));

            System.out.println("JSON " + stringEntity);

            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(stringEntity);


            response = client.execute(request);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                result.append(line);
            }


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return response;
    }

}
