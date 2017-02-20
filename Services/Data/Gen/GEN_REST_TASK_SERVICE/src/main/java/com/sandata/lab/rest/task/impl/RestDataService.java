package com.sandata.lab.rest.task.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.Task;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.task.api.DataService;
import com.sandata.lab.rest.task.model.TaskExt;
import com.sandata.lab.rest.task.utils.data.DataMapper;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sandata.lab.rest.task.utils.log.OracleDataLogger.CreateLogger;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try
        {
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

                String[] params = new String[mcl.size()];

                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = (String)mcl.get(index);
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
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public long delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

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

            return result;
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public long update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                return returnVal;
            }
            else {
                throw new SandataRuntimeException("Update was not successful!");
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
            throw new SandataRuntimeException(errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public long insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

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

                return returnVal;
            }
            else {
                throw new SandataRuntimeException("Insert was not successful!");
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
            throw new SandataRuntimeException(errMsg, e);
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
            throw e;
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal)
            throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (data instanceof Task) {
                setSk(jpubType, returnVal, "setTaskSk");
            }

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

                if (data instanceof Task) {
                    returnVal = ((Task)data).getTaskSK().longValue();
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

    /**
     * Gets tasks for a specified Business Entity ID with sorting options
     * @param exchange the exchange
     */
    public void getTasks(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            // Default sorting column
            String orderByColumn = "TASK_ID";
            switch (sortOn) {
                case "TaskID":
                    orderByColumn = "TASK_ID";
                    break;
                case "BusinessEntityTaskName":
                    orderByColumn = "BE_TASK_NAME";
                    break;
                case "TaskDescription":
                    orderByColumn = "TASK_DESC";
                    break;
            }

            String direction = (String) exchange.getIn().getHeader("direction");
            if (StringUtils.isEmpty(direction)) {
                direction = "ASC";
            }

            Response response = oracleDataService.getTasks(bsnEntId, orderByColumn, direction);

            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = String.format("%s: getTasks: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getTasksForService(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) required!");
            }

            long serviceSk = (long)exchange.getIn().getHeader("service_sk");

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            // Default sorting column
            String orderByColumn = "TASK_ID";
            switch (sortOn) {
                case "TaskID":
                    orderByColumn = "TASK_ID";
                    break;
                case "BusinessEntityTaskName":
                    orderByColumn = "BE_TASK_NAME";
                    break;
                case "TaskDescription":
                    orderByColumn = "TASK_DESC";
                    break;
            }

            String direction = (String) exchange.getIn().getHeader("direction");
            if (StringUtils.isEmpty(direction)) {
                direction = "ASC";
            }

            Response response = oracleDataService.getTasksForService(bsnEntId, serviceSk, orderByColumn, direction);

            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = String.format("%s: getTasks: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void insertTask(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            TaskExt task = (TaskExt) exchange.getIn().getBody();
            if (task == null) {
                throw new SandataRuntimeException("Task is required!");
            }

            // If the SVC_SK is provided, associate task with SVC
            if (task.getServiceSK() != null) {
                //TODO: Since ServiceTask is removed 12/21/2016: Need to associate task with service
                /*ServiceTask serviceTask = new ServiceTask();
                serviceTask.setServiceSK(task.getServiceSK());
                serviceTask.setBusinessEntityID(task.getBusinessEntityID());
                serviceTask.setBusinessEntityTaskID(task.getBusinessEntityTaskID());
                serviceTask.setRecordCreateTimestamp(new Date());
                serviceTask.setRecordUpdateTimestamp(new Date());
                exchange.getIn().setBody(serviceTask);
                long svcTaskSk = insert(exchange);
                logger.info(String.format("%s: insertTask: insertSvcTask: [SVC_TASK_SK: %d]",
                        getClass().getSimpleName(), svcTaskSk));*/
            }

            exchange.getIn().setBody(task.getTask());

            long result = insert(exchange);

            exchange.getIn().setBody(result);

        } catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void updateTask(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            TaskExt task = (TaskExt) exchange.getIn().getBody();
            if (task == null) {
                throw new SandataRuntimeException("Task is required!");
            }

            // If the SVC_SK is provided, associate task with SVC
            if (task.getServiceSK() != null) {
                //TODO: Since ServiceTask is removed 12/21/2016: Need to associate task with service
                /*
                ServiceTask serviceTask = oracleDataService.getServiceTask(task.getBusinessEntityID(), task.getBusinessEntityTaskID());
                if (serviceTask != null && task.getServiceSK().longValue() != serviceTask.getServiceSK().longValue()) {
                    serviceTask.setServiceSK(task.getServiceSK());
                    serviceTask.setRecordUpdateTimestamp(new Date());
                    exchange.getIn().setBody(serviceTask);
                    long svcTaskUpdate = update(exchange);
                    logger.info(String.format("%s: updateTask: updateSvcTask: [UPDATE Response: %d]",
                            getClass().getSimpleName(), svcTaskUpdate));
                }*/
            }

            task.setRecordUpdateTimestamp(new Date());
            exchange.getIn().setBody(task.getTask());

            long result = update(exchange);

            exchange.getIn().setBody(result);

        } catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void deleteTask(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long sequenceKey = (long)exchange.getIn().getHeader("sequence_key");

            Task task = oracleDataService.getTaskForSK(sequenceKey);
            if (task == null) {
                throw new SandataRuntimeException(String.format("[TASK_SK=%d] was not found!", sequenceKey));
            }

            exchange.getIn().setBody(delete(exchange));

        } catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
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
