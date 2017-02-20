package com.sandata.lab.rest.payroll.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.extended.PayrollOutputExt;
import com.sandata.lab.rest.payroll.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static com.sandata.lab.rest.payroll.utils.log.OracleDataLogger.CreateLogger;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class OSGIDataService {

    public void setOracleFindDataService(OracleFindDataService oracleFindDataService) {
        this.oracleFindDataService = oracleFindDataService;
    }

    private OracleFindDataService oracleFindDataService;

    /**
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getPayrollOutputForPayrollPeriod(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        try {
            String businessEntityID = (String) exchange.getIn().getHeader("bsnEntID");
            long payrollPeriodEndTime = (long) exchange.getIn().getHeader("prEndTime");

            Date payrollPeriodEndDate = new Date(payrollPeriodEndTime);


            List<PayrollOutputExt> payrollOutputList = oracleFindDataService.getPROutputForPayrollPeriod(businessEntityID, payrollPeriodEndDate);

            logger.info(String.format("%d pending payroll records were returned for business entity id: %s", payrollOutputList.size(), businessEntityID));

            exchange.getIn().setBody(payrollOutputList);


        } catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

        }
    }

    /**
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void setPROutputExportDate(Exchange exchange) throws SandataRuntimeException {

        try {
            List<BigInteger> prOutputSks = (List<BigInteger>) exchange.getIn().getBody();

            long payrollPeriodEndTime = (long) exchange.getIn().getHeader("prExportTimestamp");

            oracleFindDataService.setExportedDate(prOutputSks, payrollPeriodEndTime);

        } catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        }
    }
}
