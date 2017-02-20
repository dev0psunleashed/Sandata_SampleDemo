package com.sandata.lab.security.auth.impl;


import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.security.auth.api.ValidationService;
import com.sandata.lab.security.auth.utils.log.AuthenticationLogger;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;


public class AuthenticationInterceptor extends AbstractPhaseInterceptor<Message>{

    private ValidationService tokenValidationService;

    public void setTokenValidationService(ValidationService tokenValidationService) {
        this.tokenValidationService = tokenValidationService;
    }


    public AuthenticationInterceptor() {
        super(Phase.PRE_INVOKE);
     //   tokenValidationService = TokenValidationService.getInstance();
    }

    public void handleMessage(Message message) {
        Map<String, List<String>> headers = (Map) message.get(Message.PROTOCOL_HEADERS);

        try {

            SandataLogger logger = AuthenticationLogger.CreateLogger();

            logger.info("Validating token headers...");

            if (!tokenValidationService.validate(headers)) {

                Response response = Response.status(javax.ws.rs.core.Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
                message.getExchange().put(Response.class, response);
            } else {

                message.put(Message.PROTOCOL_HEADERS, headers);
            }

        } catch (Exception e) {
            throw new SandataRuntimeException(e.getMessage(), e);
        }
    }
}
