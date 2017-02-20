package com.sandata.one.aggregator.audit.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.one.aggregator.audit.api.DataService;
import org.apache.camel.Exchange;

import java.time.format.DateTimeFormatter;

import static com.sandata.one.aggregator.audit.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class RestDataService implements DataService {


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void forcedLogout(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            exchange.getIn().setBody(oracleDataService.forcedLogout((String)exchange.getIn().getHeader("SandataGUID")));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
