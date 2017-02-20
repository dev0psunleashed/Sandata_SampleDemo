package com.sandata.lab.rest.service.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.Service;
import com.sandata.lab.data.model.dl.model.ServiceActivityBillingCode;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.service.api.DataService;
import com.sandata.lab.rest.service.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import static com.sandata.lab.rest.service.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null)
            {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>)result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    exchange.getIn().setBody(result);
                }
                else {
                    exchange.getIn().setBody(null);
                }
            }
            else
            {
                MessageContentsList mcl = (MessageContentsList)body;

                if (className.equals("com.sandata.lab.data.model.dl.model.Service")) {

                    String bsnEntId = (String) mcl.get(0);
                    if (bsnEntId == null || bsnEntId.length() == 0) {
                        throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
                    }

                    List<Service> serviceList = (List<Service>) oracleDataService.getEntitiesForId(
                            "SELECT * FROM SVC WHERE BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ",
                            "com.sandata.lab.data.model.dl.model.Service",
                            bsnEntId
                    );

                    exchange.getIn().setBody(serviceList);
                }
                else if (className.equals("com.sandata.lab.data.model.dl.model.ServiceActivityBillCode")) {

                    Long serviceSk = (Long) mcl.get(0);
                    if (serviceSk == null || serviceSk <= 0) {
                        throw new SandataRuntimeException(exchange, "ServiceSK (service_sk) is required!");
                    }

                    Long activitySk = (Long) mcl.get(1);
                    if (activitySk == null || activitySk <= 0) {
                        throw new SandataRuntimeException(exchange, "ActivitySK (activity_sk) is required!");
                    }

                    List<ServiceActivityBillingCode> serviceActivityBillingCodes = (List<ServiceActivityBillingCode>) oracleDataService.getEntitiesForId(
                            "SELECT * FROM SVC_ACTIVITY_BILLING_CODE WHERE SVC_SK = ? AND ACTIVITY_SK = ?",
                            "com.sandata.lab.data.model.dl.model.ServiceActivityBillingCode",
                            serviceSk,
                            activitySk
                    );

                    exchange.getIn().setBody(serviceActivityBillingCodes);
                }
                else {
                    String[] params = new String[mcl.size()];

                    for (int index = 0; index < mcl.size(); index++) {
                        params[index] = (String) mcl.get(index);
                    }

                    result = oracleDataService.executeGet(
                            packageName,
                            methodName,
                            className,
                            params
                    );

                    exchange.getIn().setBody(result);
                }
            }
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get Service by bsnEntId with sorting supported
     * 
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getSvcByBsnEntId(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }
            
            String sortOn = (String) mcl.get(1);
            String direction = (String) mcl.get(2);
            String orderByColumn =  "SVC_NAME"; // Default sorting column
            switch (sortOn ) {
                case "svn":
                    orderByColumn = "SVC_NAME";
                    break;
                    
                case "svd":
                    orderByColumn = "SVC_DESC";
                    break;
                    
                case "bc":
                    orderByColumn = "BILLING_CODE";
                    break;
            }
            
            Response response = oracleDataService.getSvcByBsnEntId(bsnEntId, orderByColumn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
            
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
    
    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long)exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            }
            else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            }
            else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal)
            throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setServiceSk");

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            }
            else {

                if (data instanceof Service) {
                    returnVal = ((Service)data).getServiceSK().intValue();
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
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

                        List list = (List)property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(String.format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                                else {
                                    throw new SandataRuntimeException(String.format("UPDATE: Failed: [%s]",
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
        }
        catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }


    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
