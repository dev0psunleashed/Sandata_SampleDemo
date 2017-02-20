package com.sandata.lab.rules.data.service.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.dl.model.PatientPayer;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for retrieving exceptions setting by payer/contract.</p>
 *
 * @author thanhxle
 */
public class GetContractLevelExceptionList extends AbstractRoute {

    public static final String CONTRACT_LEVEL_EXCP_LIST = "CONTRACT_LEVEL_EXCP_";
    public static final String PAYER_CONTRACT_LIST_ = "PAYER_CONTRACT_LIST_";

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.GET_CONTRACT_LEVEL_EXCP_LIST_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_CONTRACT_LEVEL_EXCP_LIST_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

       definition
       .threads().executorServiceRef("sandataVvDataServiceThreadPool")
       .log(LoggingLevel.INFO, "Querying visit excp setting at contract level from db or REDIS.")
       .bean("visitVerificationExceptionDataService", "getContractForPayer")
       //get data from cache by cache key
       .bean("visitVerificationExceptionDataService", "getContractExceptionListSetting")
       .setHeader("ContractExceptionListExts", body()) // Save List<ContractExceptionListExt> into header
       .log(LoggingLevel.INFO, "Body after GET_CONTRACT_LEVEL_EXCP_LIST_ROUTE: ${body}");
    }
}
