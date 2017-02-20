package com.sandata.lab.dl.loc.services;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.dl.loc.services.google.model.GeoCodeResponse;
import org.apache.camel.PropertyInject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GoogleRestServiceProxy extends RestServiceProxy {

    @PropertyInject("{{google.geocode.path}}")
    private String geoCodeUrl = "https://maps.googleapis.com/maps/api/geocode";

    @PropertyInject("{{google.key}}")
    private String geoKey = "AIzaSyCl_WIAfod91_CevQSy3CPRt1UZki86C94";

    private GSONProvider gsonProvider;

    public void setGsonProvider(GSONProvider gsonProvider)
    {
        this.gsonProvider = gsonProvider;
    }

    private String createRequestUrl(String requestBodyType, Map<String, String> parameters) throws Exception
    {
        String requestUrl = geoCodeUrl;

        StringBuilder stringBuilder = new StringBuilder(requestUrl);

        stringBuilder.append("/" + requestBodyType);

        if(parameters.size() > 0)
        {
            stringBuilder.append("?");

            Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator() ;

            while(iterator.hasNext()){

                Map.Entry<String, String> entry = iterator.next();
                stringBuilder.append(entry.getKey() + "=" + entry.getValue());

                if(iterator.hasNext())
                {
                    stringBuilder.append("&");
                }
            }
        }

        stringBuilder.append("&key=" + geoKey);

        String response = this.request(stringBuilder.toString(),null, null);

        return response;
    }

    public Object geoCodeAddress(String address) throws Exception
    {
        Map<String, String> parametersMap = new HashMap<>();

        parametersMap.put("address", address);

        String response = createRequestUrl("json", parametersMap);

        GeoCodeResponse geoCodeResponse = (GeoCodeResponse) gsonProvider.fromJson(response, GeoCodeResponse.class);

        return response;
    }
}
