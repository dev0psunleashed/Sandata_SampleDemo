package com.sandata.lab.rules.call.matching.service.utils;

import com.sandata.lab.rules.call.matching.service.cache.AbstractCallMatchingStore;

import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created by thanhxle on 10/14/2016.
 */
public class CallValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallValidator.class);

    private final String CALL_DUPLICATED_CACHE_KEY_PREFIX = "DUPLICATED_KEY_";
    private AbstractCallMatchingStore callMatchingStore;

    public AbstractCallMatchingStore getCallMatchingStore() {
        return callMatchingStore;
    }

    public void setCallMatchingStore(AbstractCallMatchingStore callMatchingStore) {
        this.callMatchingStore = callMatchingStore;
    }

    /**
     * This method used to check duplicated call comming to the SCHEDULE matching route
     * @param exchange
     * @throws Exception
     */
    public void checkDuplicatedCall(final Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(exchange.getIn().getBody() instanceof VisitEventFact) {
            VisitEventFact visitEventFact = in.getBody(VisitEventFact.class);
            String callIdentifier = getCallIdentifier(visitEventFact);

            String cachedCallIdentifier = callMatchingStore.getCallIdentifier(CALL_DUPLICATED_CACHE_KEY_PREFIX + callIdentifier);

            if(cachedCallIdentifier != null) {
                in.setHeader("IS_DUPLICATED_CALL",true);
                LOGGER.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                		, this.getClass(),"checkDuplicatedCall", String.format("Duplicate call this call will be retracted , callIdentifier =%s", callIdentifier)));
            } else {
                in.setHeader("IS_DUPLICATED_CALL",false);
                callMatchingStore.storeCallIdentifier(CALL_DUPLICATED_CACHE_KEY_PREFIX + callIdentifier,callIdentifier);
                LOGGER.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                		, this.getClass(),"checkDuplicatedCall", String.format("Put call idetifier = %s to REDIS", callIdentifier)));
            }
        }
    }

    private String getCallIdentifier(VisitEventFact visitEventFact) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dnis = visitEventFact.getDnis();
        String staff = visitEventFact.getStaffID();
        String ani = visitEventFact.getAni();
        String callTime = simpleDateFormat.format(visitEventFact.getVisitEventDatetime());
        String callIdentifier =  String.format("%s%s%s%s",dnis,staff,ani,callTime);
        return callIdentifier;
    }

}
