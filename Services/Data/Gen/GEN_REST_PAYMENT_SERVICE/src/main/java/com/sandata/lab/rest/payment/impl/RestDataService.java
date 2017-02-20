package com.sandata.lab.rest.payment.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.rest.payment.api.DataService;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.rest.payment.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

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

        SandataLogger logger = OracleDataLogger.CreateLogger();

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

                    if (methodParts[3].equals("Payment")) {

                        exchange.getIn().setBody(getRelatedPaymentData((Payment) result));
                    }
                    else if (methodParts[3].equals("PaymentTerms")) {

                        exchange.getIn().setBody(getRelatedPaymentTermsData((PaymentTerms) result));
                    }
                    else {
                        exchange.getIn().setBody(result);
                    }
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

                /*
                //dmr GEOR-1034: Extended VisitTaskList to logically determine if the task is accepted.
                if(methodParts[3].equals("VisitTaskList")) {

                    List<VisitTaskListExt> visitTaskListExtList = new ArrayList<>();

                    List<VisitTaskList> visitTaskLists = (List<VisitTaskList>)result;
                    for (VisitTaskList visitTaskList : visitTaskLists) {

                        VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTaskList);
                        visitTaskListExtList.add(visitTaskListExt);
                    }

                    exchange.getIn().setBody(visitTaskListExtList);
                }
                // TODO: Temporary fix...
                else if(methodParts[3].equals("VisitEvent")) {

                    List<VisitEvent> visitEvents = (List<VisitEvent>)result;
                    for (VisitEvent visitEvent : visitEvents) {

                        visitEvent.setChangeVersionID(BigInteger.valueOf(1));
                    }

                    exchange.getIn().setBody(result);
                }
                else {
                    */
                    exchange.getIn().setBody(result);
                //}
            }

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    private Payment getRelatedPaymentData(final Payment data) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return data;
    }

    private PaymentTerms getRelatedPaymentTermsData(final PaymentTerms data) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return data;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

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

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

        Connection connection = null;

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
                throw new SandataRuntimeException("Update was not successful!");
            }

            logger.stop();
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
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

        Connection connection = null;

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
                throw new SandataRuntimeException("Insert was not successful!");
            }

            logger.stop();
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

            if (data instanceof Payment) {
                setSk(jpubType, returnVal, "setPaymentSk");
            }
            else if (data instanceof PaymentTerms) {
                setSk(jpubType, returnVal, "setPaymentTermsSk");
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

                if (data instanceof TimesheetDetail) {
                    returnVal = ((TimesheetDetail)data).getTimesheetDetailSK().longValue();
                }
                else if (data instanceof TimesheetSummary) {
                    returnVal = ((TimesheetSummary)data).getTimesheetSummarySK().longValue();
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
