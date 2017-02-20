package com.sandata.lab.rest.patient.impl;


import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import org.apache.camel.Exchange;

import static com.sandata.lab.rest.patient.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class OSGIDataService extends RestDataService{

    private OracleFindDataService oracleFindDataService;

    public void setOracleFindDataService(OracleFindDataService oracleFindDataService) {
        this.oracleFindDataService = oracleFindDataService;
    }
    
    public void patientExists(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String businessEntityID = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (StringUtil.IsNullOrEmpty(businessEntityID)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String firstName = (String) exchange.getIn().getHeader("first_name");

            if (StringUtil.IsNullOrEmpty(firstName)) {
                throw new SandataRuntimeException(exchange, "First Name (first_name) is required!");
            }

            String lastName = (String) exchange.getIn().getHeader("last_name");

            if (StringUtil.IsNullOrEmpty(lastName)) {
                throw new SandataRuntimeException(exchange, "Last Name (last_name) is required!");
            }

            String patientID = (String) exchange.getIn().getHeader("patient_id");

            if (StringUtil.IsNullOrEmpty(patientID)) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            boolean result = oracleFindDataService.patientExists(businessEntityID, firstName, lastName, patientID);

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
