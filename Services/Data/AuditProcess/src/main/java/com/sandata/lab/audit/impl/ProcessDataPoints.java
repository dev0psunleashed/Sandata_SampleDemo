package com.sandata.lab.audit.impl;

import com.sandata.lab.common.utils.data.compare.CompareUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.data.Compare;
import com.sandata.lab.data.model.data.CompareResult;
import org.apache.camel.Exchange;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.sandata.lab.audit.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Process data points and determine what fields changed. Log changes to the audit table.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class ProcessDataPoints {

    private OracleDataService oracleDataService;

    public void handler(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            logger.trace(exchange.getFromRouteId(), null, "ProcessDataPoints: handler: START");

            Compare compare = exchange.getIn().getBody(Compare.class);

            if (compare == null) {
                logger.error(exchange.getExchangeId(), null, String.format("%s: handler: compare == NULL", getClass().getName()));
                return;
            }

            if (compare.getUpdated() == null) {
                logger.error(exchange.getExchangeId(), null, String.format("%s: handler: compare.getUpdated() == NULL", getClass().getName()));
                return;
            }

            List<CompareResult> compareResultList = CompareUtil.Compare(compare);
            if (compareResultList == null) {
                logger.error(exchange.getExchangeId(), null, String.format("%s: handler: compareResultList == NULL: [%s]",
                        getClass().getName(), compare.toString()));
                return;
            }

            String userGuid = (String)exchange.getIn().getHeader("SandataGUID");

            String bsnEntId = (String)exchange.getIn().getHeader("AuditBsnEntID");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                logger.error(exchange.getExchangeId(), null, String.format("%s: handler: [Header=AuditBsnEntID]: bsnEntId == NULL",
                        getClass().getName()));
                return;
            }

            Long skValue = (Long)exchange.getIn().getHeader("AuditSkValue");
            if (skValue == null) {
                logger.error(exchange.getExchangeId(), null, String.format("%s: handler: [Header=AuditSkValue]: skValue == null",
                        getClass().getName()));
                return;
            }

            // Insert audit record for each changed data point
            for (CompareResult compareResult : compareResultList) {

                long result = oracleDataService.insertAuditChanges(connection, userGuid, bsnEntId, skValue, compareResult, logger);

                logger.info(String.format("%s: handler: INSERT: [RESULT=%d]", getClass().getName(), result));
            }

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: EXCEPTION: %s", getClass().getName(), e.getMessage());
            logger.error(exchange.getFromRouteId(), null, errMsg);
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.trace(exchange.getFromRouteId(), null, "ProcessDataPoints: handler: END");
            logger.stop();
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
