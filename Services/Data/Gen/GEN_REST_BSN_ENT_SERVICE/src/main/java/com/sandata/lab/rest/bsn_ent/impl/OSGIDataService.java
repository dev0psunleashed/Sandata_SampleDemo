package com.sandata.lab.rest.bsn_ent.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.rest.bsn_ent.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public class OSGIDataService {

    public void setOracleFindDataService(OracleFindDataService oracleFindDataService) {
        this.oracleFindDataService = oracleFindDataService;
    }

    private OracleFindDataService oracleFindDataService;


    public void getBusinessEntityCrossWalkByOrg(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {
            String vendor = (String) exchange.getIn().getHeader("vendor");
            String externalBsnEntID = (String) exchange.getIn().getHeader("externalBsnEntID");

            HashMap<String, String> businessEntity = oracleFindDataService.getBsnEntIDForPayCreatingOrg(externalBsnEntID, vendor);

            exchange.getIn().setBody(businessEntity);


        } catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Gets a list of business entity ids by the payroll vendor name set in the exhange body
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getBusinessEntityIDSsForPayroll(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {
            HashMap<String, Map> businessEntityIds = oracleFindDataService.getBsnEntIDsForPayroll();

            exchange.getIn().setBody(businessEntityIds);
        } catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Gets a list of business entity ids by the payroll vendor name set in the exhange body
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getBusinessEntityIDSsForPayrollByVendorID(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        String vendorName = (String) exchange.getIn().getHeader("vendorName");

        try {
            HashMap<String, Map> businessEntityIds = oracleFindDataService.getBsnEntIDsForPayrollByVendor(vendorName);

            exchange.getIn().setBody(businessEntityIds);
        } catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }


    /**
     * @param exchange
     */
    public void getBusinessEntity(Exchange exchange) {

        try {

            String bsnEntID = (String) exchange.getIn().getHeader("bsnEntID");

            String[] params = new String[1];
            params[0] = bsnEntID;

            // Get List of BusinessEntityContactDetail for BusinessEntity and add.
            List<BusinessEntity> businessEntityList = (List<BusinessEntity>) oracleFindDataService.executeGet(
                    "PKG_BSN",
                    "getBe",
                    "com.sandata.lab.data.model.dl.model.BusinessEntity",
                    params);

            BusinessEntity businessEntity = businessEntityList.get(0);


            exchange.getIn().setBody(businessEntity);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        }
    }

    /**
     * @param exchange
     */
    public void getPayrollBusinessEntityForID(Exchange exchange) {

        try {

            String bsnEntID = (String) exchange.getIn().getHeader("bsnEntID");

            HashMap bsnMap = oracleFindDataService.getBsnEntIDForPayrollByBusinessEntityID(bsnEntID);

            exchange.getIn().setBody(bsnMap);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        }
    }

    /**
      * @param exchange
     */
    public void getLocationIdsBusinessEntityForID(Exchange exchange) {

        try {

            String bsnEntID = (String) exchange.getIn().getHeader("bsnEntID");

            ArrayList ids = oracleFindDataService. getLocationsBusinessEntityIDsForBusinessEntity(bsnEntID);

            exchange.getIn().setBody(ids);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        }
    }


}
