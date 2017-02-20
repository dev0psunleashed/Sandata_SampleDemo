package com.sandata.staff.imports.services;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.staff.imports.utils.log.StaffImportLogger;
import org.apache.camel.PropertyInject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffRestServiceProxy extends RestServiceProxy {

  //  @PropertyInject("{{staffintake.webservice.url}}")
    private String staffIntakeWebServiceUrl;


   // @PropertyInject("{{staffintake.webservice.username}}")
    private String staffIntakeWebServiceUsername;

   // @PropertyInject("{{staffintake.webservice.password}}")
    private String staffIntakeWebServicePassword;

   // @PropertyInject("{{staffintake.intake.method}}")
    private String staffIntakeMethod;

    private StaffImportLogger staffImportLogger;

    public void setStaffImportLogger(StaffImportLogger staffImportLogger) {
        this.staffImportLogger = staffImportLogger;
    }


    private String createAuthorizationString()
    {
        String authString = staffIntakeWebServiceUsername + ":" + staffIntakeWebServicePassword;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return "Basic " + authStringEnc;
    }

    public Response postStaffImports(ArrayList<Staff> staffArrayList) throws Exception
    {
        String staffContent = GSONProvider.ToJson(staffArrayList);

        try
        {
            String url = staffIntakeWebServiceUrl + staffIntakeMethod;

            HashMap<String,String> headerValues = new HashMap<String, String>();

            headerValues.put("Authorization", createAuthorizationString());
            headerValues.put("Content-type", "application/json");

            HttpResponse response = this.request(url, headerValues, staffContent);

            Response serverResponse = (Response) GSONProvider.FromJson(response.toString(), Response.class);

            return serverResponse;
        }
        catch (Exception e)
        {
            staffImportLogger.logger().error(this.getClass().getName(), e);
            throw new SandataRuntimeException(String.format("StaffIntakeProxy: ERROR: %s", e.getMessage()));
        }
    }

}
