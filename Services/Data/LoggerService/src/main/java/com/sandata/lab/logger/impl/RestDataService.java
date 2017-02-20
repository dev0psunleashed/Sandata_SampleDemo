package com.sandata.lab.logger.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.time.TimeUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ApplicationLog;
import com.sandata.lab.data.model.dl.model.ApplicationSession;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.logger.api.DataService;
import com.sandata.lab.logger.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.logger.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public void status(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            exchange.getIn().setBody("SUCCESS");

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);

        } finally {
            logger.stop();
        }
    }

    public void getAppLogPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            Long logThread = (Long) mcl.get(0);
            String logLevel = (String) mcl.get(1);
            String logProcess = (String) mcl.get(2);

            String fromDate = (String) mcl.get(3);
            String fromTime = (String) mcl.get(4);
            String toDate = (String) mcl.get(5);
            String toTime = (String) mcl.get(6);

            if (
                    logThread == null
                    && IsNullOrEmpty(logLevel)
                    && IsNullOrEmpty(logProcess)
            ) {
                throw new SandataRuntimeException("At least one parameter is required!");
            }

            if (!IsNullOrEmpty(fromDate)) {

                if (IsNullOrEmpty(toDate)) {
                    throw new SandataRuntimeException("To Date (to_date) is required!");
                }

                DateUtil.IsValidDate(fromDate, "yyyy-MM-dd", "From Date");
                TimeUtil.IsValidTime(fromTime, "From Time");
            }

            if (!IsNullOrEmpty(toDate)) {

                if (IsNullOrEmpty(fromDate)) {
                    throw new SandataRuntimeException("From Date (from_date) is required!");
                }

                DateUtil.IsValidDate(toDate, "yyyy-MM-dd", "To Date");
                TimeUtil.IsValidTime(toTime, "To Time");
            }

            exchange.getIn().setBody(oracleDataService.getAppLogPK(
                            logThread,
                            logLevel,
                            logProcess,
                            fromDate,
                            fromTime,
                            toDate,
                            toTime
            ));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void getAppSessPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String userGuid = (String) mcl.get(0);

            String fromDate = (String) mcl.get(1);
            String fromTime = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);
            String toTime = (String) mcl.get(4);

            if (IsNullOrEmpty(userGuid)) {
                throw new SandataRuntimeException("User GUID (user_guid) is required!");
            }

            if (!IsNullOrEmpty(fromDate)) {

                if (IsNullOrEmpty(toDate)) {
                    throw new SandataRuntimeException("To Date (to_date) is required!");
                }

                DateUtil.IsValidDate(fromDate, "yyyy-MM-dd", "From Date");
                TimeUtil.IsValidTime(fromTime, "From Time");
            }

            if (!IsNullOrEmpty(toDate)) {

                if (IsNullOrEmpty(fromDate)) {
                    throw new SandataRuntimeException("From Date (from_date) is required!");
                }

                DateUtil.IsValidDate(toDate, "yyyy-MM-dd", "To Date");
                TimeUtil.IsValidTime(toTime, "To Time");
            }

            exchange.getIn().setBody(oracleDataService.getAppSessPK(
                    userGuid,
                    fromDate,
                    fromTime,
                    toDate,
                    toTime
            ));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void getAppLogHistory(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            Long logThread = (Long) mcl.get(0);
            String logLevel = (String) mcl.get(1);
            String logProcess = (String) mcl.get(2);

            String fromDate = (String) mcl.get(3);
            String fromTime = (String) mcl.get(4);
            String toDate = (String) mcl.get(5);
            String toTime = (String) mcl.get(6);
            String userGuid = (String) mcl.get(7);

            int page = (int) mcl.get(8);
            int pageSize = (int) mcl.get(9);

            String sortOn = (String) mcl.get(10);
            String direction = (String) mcl.get(11);

            if (
                    logThread == null
                            && IsNullOrEmpty(logLevel)
                            && IsNullOrEmpty(logProcess)
                            && IsNullOrEmpty(userGuid)
                    ) {
                throw new SandataRuntimeException("At least one parameter is required!");
            }

            if (!IsNullOrEmpty(fromDate)) {

                if (IsNullOrEmpty(toDate)) {
                    throw new SandataRuntimeException("To Date (to_date) is required!");
                }

                DateUtil.IsValidDate(fromDate, "yyyy-MM-dd", "From Date");
                TimeUtil.IsValidTime(fromTime, "From Time");
            }

            if (!IsNullOrEmpty(toDate)) {

                if (IsNullOrEmpty(fromDate)) {
                    throw new SandataRuntimeException("From Date (from_date) is required!");
                }

                DateUtil.IsValidDate(toDate, "yyyy-MM-dd", "To Date");
                TimeUtil.IsValidTime(toTime, "To Time");
            }

            Response response = oracleDataService.getAppLogHistory(
                    userGuid,
                    logThread,
                    logLevel,
                    logProcess,
                    fromDate,
                    fromTime,
                    toDate,
                    toTime,
                    page,
                    pageSize,
                    sortOn,
                    direction
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void addLogForUserGUID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            String userGuid = (String) exchange.getIn().getHeader("user_guid");
            if (IsNullOrEmpty(userGuid)) {
                throw new SandataRuntimeException("UserGUID (user_guid) is required!");
            }

            ApplicationLog applicationLog = exchange.getIn().getBody(ApplicationLog.class);

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.addLogForUserGUID(connection, userGuid, applicationLog));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.stop();
        }
    }

    public void addLog(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            ApplicationLog applicationLog = exchange.getIn().getBody(ApplicationLog.class);

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.addLog(connection, applicationLog));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.stop();
        }
    }

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null) {

                int sequenceKey = (int) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        ConnectionType.METADATA,
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {

                    result = result2.get(0);
                    exchange.getIn().setBody(result);

                } else {

                    exchange.getIn().setBody(null);
                }

            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                    String[] params = new String[mcl.size()];

                    for (int index = 0; index < mcl.size(); index++) {
                        params[index] = (String) mcl.get(index);
                    }

                    result = oracleDataService.executeGet(
                            ConnectionType.METADATA,
                            packageName,
                            methodName,
                            className,
                            params
                    );

                    exchange.getIn().setBody(result);
            }

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            int sequenceKey = (int) exchange.getIn().getHeader("sequence_key");

            int result = oracleDataService.execute(
                    ConnectionType.METADATA,
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);
        } finally {
            logger.stop();
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);
            int returnVal = executeRecursive(exchange, connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);
            int returnVal = executeRecursive(exchange, connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.stop();
        }
    }

    private void setSk(final Object jpubType, final int sequenceKey, final String skSetMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(skSetMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int executeRecursive(final Exchange exchange, final Connection connection, final Object data, final boolean bShouldInsert, int returnVal) throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setAppLogSk");

            int result = 0;

            if (bShouldInsert) {

                result = oracleDataService.execute(
                        connection,
                        ConnectionType.METADATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );

            } else {

                // TODO: Need to find a better way of doing this...
                if (data instanceof ApplicationLog) {
                    returnVal = ((ApplicationLog) data).getApplicationLogSK().intValue();
                } else if (data instanceof ApplicationSession) {
                    returnVal = ((ApplicationSession) data).getApplicationSessionSK().intValue();
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
                        ConnectionType.METADATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );
            }

            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            int insertResponse = executeRecursive(exchange, connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(exchange, format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(exchange, format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg);
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
