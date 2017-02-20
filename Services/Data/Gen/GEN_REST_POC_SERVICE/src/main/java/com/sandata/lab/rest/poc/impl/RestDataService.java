package com.sandata.lab.rest.poc.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.PlanOfCare;
import com.sandata.lab.data.model.dl.model.PlanOfCareService;
import com.sandata.lab.data.model.dl.model.PlanOfCareTaskList;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.poc.api.DataService;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.rest.poc.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sandata.lab.rest.poc.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

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

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null) {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                    packageName,
                    methodName,
                    className,
                    sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    if (methodParts[3].equals("PlanOfCare")) {
                        exchange.getIn().setBody(getRelatedPOCData((PlanOfCare) result));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                Object[] params = new Object[mcl.size()];

                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = mcl.get(index);
                }

                result = oracleDataService.executeGet(
                    packageName,
                    methodName,
                    className,
                    params
                );

                if (methodParts[3].equals("PlanOfCare")) {
                    for (PlanOfCare planOfCare : (List<PlanOfCare>) result) {
                        getRelatedPOCData(planOfCare);
                    }
                }

                exchange.getIn().setBody(result);
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private PlanOfCare getRelatedPOCData(PlanOfCare planOfCare) {

        if (planOfCare == null) {
            return null;
        }

        List<PlanOfCareTaskList> pocTaskList = (List<PlanOfCareTaskList>) oracleDataService.getEntitiesForId(
            format("SELECT * FROM %s.POC_TASK_LST WHERE POC_SK = ?", ConnectionType.COREDATA),
            "com.sandata.lab.data.model.dl.model.PlanOfCareTaskList",
            planOfCare.getPlanOfCareSK());

        List<PlanOfCareService> planOfCareServiceList = (List<PlanOfCareService>) oracleDataService.getEntitiesForId(
            format("SELECT * FROM %s.POC_SVC WHERE POC_SK = ?", ConnectionType.COREDATA),
            "com.sandata.lab.data.model.dl.model.PlanOfCareService",
            planOfCare.getPlanOfCareSK());

        planOfCare.getPlanOfCareTaskList().addAll(pocTaskList);
        planOfCare.getPlanOfCareService().addAll(planOfCareServiceList);

        return planOfCare;
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

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                packageName,
                methodName,
                sequenceKey
            );

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

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

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
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
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

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

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, true, -999);
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
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getPocTaskListForPocSk(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String planOfCareSk = (String) exchange.getIn().getHeader("poc_sk");
            if (planOfCareSk == null
                || !NumberUtils.isNumber(planOfCareSk)) {
                throw new SandataRuntimeException("Parameter poc_sk required and must be numeric!");
            }

            exchange.getIn().setBody(oracleDataService.getPlanOfCareTaskListForPlanOfCareSk(Long.valueOf(planOfCareSk)));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPocTaskLstForTaskId(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String taskId = (String) mcl.get(0);
            if (taskId == null || taskId.length() == 0) {
                throw new SandataRuntimeException("TaskID (task_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getPocTaskLstForTaskId(taskId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal)
        throws SandataRuntimeException {

        try {

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setPocSk");

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                    connection,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
                );
            } else {

                if (data instanceof PlanOfCare) {
                    returnVal = ((PlanOfCare) data).getPlanOfCareSK().longValue();
                } else if (data instanceof PlanOfCareTaskList) {
                    returnVal = ((PlanOfCareTaskList) data).getPlanOfCareTaskListSK().longValue();
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

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                    object.getClass().getName()));
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
