package com.sandata.lab.auth.services;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.security.auth.model.forgeRock.AccessToken;
import com.sandata.lab.security.auth.model.forgeRock.TokenInfo;
import com.sandata.lab.security.auth.services.OpenAMService;
import org.junit.Test;

import java.io.IOException;

public class OpenAMServiceIntegrationTest extends BaseTestSupport {

    private OpenAMService openAMService;

    @Override
    protected void onSetup() throws IOException {

        openAMService = new OpenAMService();
    }

    @Test
    public void getToken() throws Exception
    {
        AccessToken accessToken = openAMService.tokenRequest("george@sandata.com","Test.110");

    }

    @Test
    public void tokenInfo() throws Exception
    {

        AccessToken accessToken = openAMService.tokenRequest("george@sandata.com","Test.112");

        String token = accessToken.getAccessToken();


       // String token = "c0d43004-a553-407f-aaa7-00cb0a83e7eb";

       TokenInfo tokenInfo =  openAMService.tokenInfoRequest(token);

        assertNotNull(tokenInfo);
    }

    @Test
    public void refreshToken() throws Exception
    {
        AccessToken accessToken = openAMService.tokenRequest("george@sandata.com","Test.101");

        String token = accessToken.getRefreshToken();

        AccessToken accessToken1 = openAMService.refreshToken(token);

        assertNotNull(accessToken1);

    }


}
