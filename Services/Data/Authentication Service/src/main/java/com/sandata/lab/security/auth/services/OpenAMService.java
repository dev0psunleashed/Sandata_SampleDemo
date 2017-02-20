package com.sandata.lab.security.auth.services;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.security.auth.model.forgeRock.AccessToken;
import com.sandata.lab.security.auth.model.forgeRock.TokenInfo;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;
import org.apache.camel.PropertyInject;
import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;
import java.util.HashMap;

public class OpenAMService extends RestServiceProxy {

    @PropertyInject("{{openam.host}}")
    private String host = "http://dev-lab-oam01.sandata.com";

    @PropertyInject("{{openam.port}}")
    private String port = "8080";

    @PropertyInject("{{openam.path}}")
    private String path = "/openam/oauth2";

    @PropertyInject("{{openam.token.path}}")
    private String getTokenPath = "/access_token?realm=/George";

    @PropertyInject("{{openam.tokeninfo.path}}")
    private String tokenInfoPath = "/tokeninfo?access_token=";

    @PropertyInject("{{openam.user}}")
    private String userName = "weboagent@sandata.com";

    @PropertyInject("{{openam.password}}")
    private String password = "San26hpd";


    public static OpenAMService getInstance() {
        return new OpenAMService();
    }

    private String createAuthHeader() {
        String authHeader;

        String authString = userName + ":" + password;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        return "Basic " + new String(authEncBytes);
    }

    private String getRequestURL(String host, String port, String path, String requestPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(host);
        stringBuilder.append(":");
        stringBuilder.append(port + path + requestPath);

        return stringBuilder.toString();
    }


    public AccessToken tokenRequest(String userName, String password) throws Exception {
        try {

            SandataLogger logger = AuthenticationLogger.CreateLogger();

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/x-www-form-urlencoded");

            headerValues.put("Authorization", createAuthHeader());

            String request = "grant_type=password&username=" + userName + "&password=" + password
                    + "&scope=SandataGUID isMemberOf mail o ou cn lastLoginTime SandataAccess Persona BEID";

            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            logger.info(String.format("Getting token for username:'%s'", userName));

            String requestURL = getRequestURL(host, port, path, getTokenPath);
            String response = this.postRequest(requestURL, headerValues, request);
            return (AccessToken) GSONProvider.FromJson(response, AccessToken.class);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public TokenInfo tokenInfoRequest(String token) throws Exception {

        try {

            SandataLogger logger = AuthenticationLogger.CreateLogger();

            HashMap<String, String> headerValues = new HashMap<String, String>();

            headerValues.put("Authorization", createAuthHeader());

            logger.info(String.format("Getting tokenInfo for token:'%s'", token));

            //Expired token {"error_description":"Access Token not valid","error":"invalid_request"}
            String requestURL = getRequestURL(host, port, path, tokenInfoPath + token);
            String response = this.getRequest(requestURL, headerValues);

            return (TokenInfo) GSONProvider.FromJson(response, TokenInfo.class);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    public AccessToken refreshToken(String refreshToken) throws Exception {

        try {


            SandataLogger logger = AuthenticationLogger.CreateLogger();

            HashMap<String, String> headerValues = new HashMap<String, String>();
            headerValues.put("Content-type", "application/x-www-form-urlencoded");

            headerValues.put("Authorization", createAuthHeader());

            String request = "grant_type=refresh_token&refresh_token=" + refreshToken;

            URLEncoder.encode(request, java.nio.charset.StandardCharsets.UTF_8.toString());

            logger.info(String.format("Refreshing token :'%s'", refreshToken));

            String requestURL = getRequestURL(host, port, path, getTokenPath);
            String response = this.postRequest(requestURL, headerValues, request);

            return (AccessToken) GSONProvider.FromJson(response, AccessToken.class);
        }catch (Exception e){

            e.printStackTrace();
            throw e;
        }
    }

}
