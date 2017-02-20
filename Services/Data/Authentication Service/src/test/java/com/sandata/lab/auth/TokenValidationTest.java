package com.sandata.lab.auth;

import com.sandata.lab.security.auth.impl.TokenValidationService;
import com.sandata.lab.security.auth.services.OpenAMService;
import mockit.Mocked;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenValidationTest extends BaseTestSupport {

    private TokenValidationService tokenValidationService;

    @Mocked
    private OpenAMService openAMService;

    @Override
    protected void onSetup() throws IOException {

        tokenValidationService = new TokenValidationService();
        tokenValidationService.setOpenAMService(openAMService);
    }

    @Test
    public void token_is_valid_and_guid_is_added_to_headers() throws Exception
    {
        Map<String,List<String>> headers =  new HashMap<>();

        List<String> token = new ArrayList<>();
        token.add("testtoken");

        headers.put("Token", token);

        List<String> refreshToken = new ArrayList<>();
        refreshToken.add("reftoken");

        headers.put("Refresh-Token", token);

        tokenValidationService.validate(headers);

    }
}
