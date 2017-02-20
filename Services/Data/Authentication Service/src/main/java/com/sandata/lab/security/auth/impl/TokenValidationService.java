package com.sandata.lab.security.auth.impl;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.security.auth.api.ValidationService;
import com.sandata.lab.security.auth.model.forgeRock.AccessToken;
import com.sandata.lab.security.auth.model.forgeRock.TokenInfo;
import com.sandata.lab.security.auth.services.OpenAMService;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TokenValidationService implements ValidationService {

    private OpenAMService openAMService;

    public TokenValidationService() {
        openAMService = OpenAMService.getInstance();
    }

    public static TokenValidationService getInstance() {
        return new TokenValidationService();
    }

    public void setOpenAMService(OpenAMService openAMService) {
        this.openAMService = openAMService;
    }

    public boolean validate(Map<String, List<String>> headers) throws Exception {


        SandataLogger logger = AuthenticationLogger.CreateLogger();


        List<String> tokenHeaders = headers.get("Token");
        List<String> refreshTokenHeaders = headers.get("Refresh-Token");

        TokenInfo tokenInfo = null;

        if (tokenHeaders != null && tokenHeaders.size() > 0) {

            String token = tokenHeaders.get(0);
            tokenInfo = openAMService.tokenInfoRequest(token);

        } else {

            logger.info("Token is null.");
        }

        // Attempt to refresh token if the tokeninfo was not returned
        if ((tokenInfo == null || !StringUtil.IsNullOrEmpty(tokenInfo.getError())) && refreshTokenHeaders != null && refreshTokenHeaders.size() > 0) {

            String refreshToken = refreshTokenHeaders.get(0);

            //Get new token using the refresh token call
            AccessToken accessToken = openAMService.refreshToken(refreshToken);

            String token = accessToken.getAccessToken();

            if (!StringUtil.IsNullOrEmpty(token)) {

                logger.info("Refreshing token...");

                tokenInfo = openAMService.tokenInfoRequest(token);
            }
        }else {

            logger.info("Refresh token is null");
        }

        if (tokenInfo != null && StringUtil.IsNullOrEmpty(tokenInfo.getError())) {

            logger.info(String.format("Validated token for SandataGUID: %s", tokenInfo.getSandataGuid()));

            headers.put("SandataGUID", Arrays.asList(tokenInfo.getSandataGuid()));
            headers.put("UserName", Arrays.asList(tokenInfo.getEmail()));
            return true;
        }

        return false;
    }
}
