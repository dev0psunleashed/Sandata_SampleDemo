package com.sandata.lab.rest.am.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.rest.am.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public class OSGIDataService  {


    private OracleFindDataService oracleFindDataService;

    public void setOracleFindDataService(OracleFindDataService oracleFindDataService) {
        this.oracleFindDataService = oracleFindDataService;
    }




    public void getTenantConfigsByBEIDs(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try
        {
            List beIDs = (ArrayList) exchange.getIn().getBody();

            HashMap<String, Map> result = oracleFindDataService.getTenantConfigsByBEIds(beIDs);

            exchange.getIn().setBody(result);

        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getTenantConfigsByKey(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try
        {
            String businessEntityID = (String) exchange.getIn().getHeader("bsnEntID");

            String keyName = (String) exchange.getIn().getHeader("key");

            Object result = oracleFindDataService.getTenantConfigByKey(businessEntityID, keyName);

            exchange.getIn().setBody(result);

        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

}
