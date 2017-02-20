package com.sandata.lab.auth.impl;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.security.auth.impl.AuthenticationService;
import com.sandata.lab.security.auth.impl.RestDataService;
import com.sandata.lab.security.auth.services.IDMService;
import com.sandata.lab.security.auth.services.OpenAMService;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.math.BigInteger;

@RunWith(JMockit.class)
public class AuthenticationServiceIntegrationTest extends BaseTestSupport {

    @Mocked
    private OpenAMService openAMService;

    @Mocked
    private IDMService idmService;

    @Mocked
    private RestDataService restDataService;

    private AuthenticationService authenticationService;

    @Override
    protected void onSetup() throws IOException {

        authenticationService = new AuthenticationService();
        authenticationService.setIdmService(idmService);
        authenticationService.setOpenAMService(openAMService);
        authenticationService.setRestDataService(restDataService);

    }

    @Test
    public void call_create_user() throws Exception
    {
        ApplicationUserExt applicationUserExt = new ApplicationUserExt();

        applicationUserExt.setApplicationUserSK(BigInteger.valueOf(1));
        applicationUserExt.setUserName("username@test.com");

        exchange.getIn().setBody(applicationUserExt);


        authenticationService.createUser(exchange);
    }
}
