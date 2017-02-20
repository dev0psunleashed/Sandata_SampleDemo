package com.sandata.lab.rest.am.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import static com.sandata.lab.rest.am.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

public class ReportRestDataService {

    private OracleDataService oracleDataService;

    public void getReportTypes(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            exchange.getIn().setBody(oracleDataService.getReportTypes());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getReportNames(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String reportType = (String) mcl.get(0);
            if (StringUtil.IsNullOrEmpty(reportType)) {
                throw new SandataRuntimeException("ReportType (report_type) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getReportNames(reportType));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getReportParams(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String reportId = (String) mcl.get(0);
            if (StringUtil.IsNullOrEmpty(reportId)) {
                throw new SandataRuntimeException("ReportID (report_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getReportParams(reportId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
