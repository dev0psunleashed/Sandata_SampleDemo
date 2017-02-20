package com.sandata.lab.rest.auth.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.AuthorizationExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.auth.api.DataService;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sandata.lab.rest.auth.utils.log.OracleDataLogger.CreateLogger;
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

                if (methodName.equals("getAppUserKeyConf")) {

                    result = oracleDataService.executeGet(
                        ConnectionType.METADATA,
                        packageName,
                        methodName,
                        className,
                        sequenceKey);

                } else {

                    result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey);
                }

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    if (methodParts[3].equals("Order")) {
                        result = transformOrderToAuthorization((Order) result);
                        methodParts[3] = "Authorization";
                    } else if (methodParts[3].equals("OrderService")) {
                        result = transformOrderServiceToAuthorizationService((OrderService) result);
                    }

                    if (methodParts[3].equals("Authorization")) {
                        exchange.getIn().setBody(getRelatedAuthData((Authorization) result));
                    } else if (methodParts[3].equals("ApplicationUserKeyConfiguration")) {
                        exchange.getIn().setBody(getChildrenUserKeyConfData((ApplicationUserKeyConfiguration) result));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                if (className.equals("com.sandata.lab.data.model.dl.model.Authorization")) {

                    String patientId = (String) mcl.get(0);
                    if (patientId == null || patientId.length() == 0) {
                        throw new SandataRuntimeException("PatientID (patient_id) is required!");
                    }

                    String bsnEntId = (String) mcl.get(1);
                    if (bsnEntId == null || bsnEntId.length() == 0) {
                        throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
                    }

                    List<Authorization> authList = (List<Authorization>) oracleDataService.getEntitiesForId(
                        "SELECT * FROM "
                            + ConnectionType.COREDATA
                            + ".AUTH WHERE PT_ID = ? AND BE_ID = ? " +
                            "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                        "com.sandata.lab.data.model.dl.model.Authorization",
                        patientId,
                        bsnEntId
                    );

                    //dmr GEOR-793: Populate tasks and services
                    for (Authorization auth : authList) {

                        List<AuthorizationService> authorizationServiceList = (List<AuthorizationService>) oracleDataService.getEntitiesForId(
                            String.format("SELECT * FROM %s.AUTH_SVC WHERE AUTH_SK = ?", ConnectionType.COREDATA),
                            "com.sandata.lab.data.model.dl.model.AuthorizationService",
                            auth.getAuthorizationSK().longValue()
                        );

                        auth.getAuthorizationService().addAll(authorizationServiceList);
                    }

                    exchange.getIn().setBody(authList);

                } else {

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

                    exchange.getIn().setBody(result);
                }
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    private ApplicationUserKeyConfiguration getChildrenUserKeyConfData(final ApplicationUserKeyConfiguration appUserkeyConf) {

        if (appUserkeyConf != null) {
            List<ApplicationUserKeyConfiguration> childAppUserkeyConf = (List<ApplicationUserKeyConfiguration>) oracleDataService.getEntitiesForId(
                "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_USER_KEY_CONF WHERE APP_USER_KEY_CONF_PAR_SK = ? ",
                "com.sandata.lab.data.model.dl.model.ApplicationUserKeyConfiguration",
                appUserkeyConf.getApplicationUserKeyConfigurationSK());

            appUserkeyConf.getApplicationUserKeyConfiguration().addAll(childAppUserkeyConf);
        }

        return appUserkeyConf;
    }

    private GetAuthResult getRelatedAuthData(final Authorization authorization) {

        GetAuthResult getAuthResult = new GetAuthResult();
        getAuthResult.setAuthorization(authorization);

        List<Payer> payers = (List<Payer>) oracleDataService.getEntitiesForId(
            "SELECT * FROM "
                + ConnectionType.COREDATA
                + ".PAYER WHERE PAYER_ID = ? AND BE_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
            "com.sandata.lab.data.model.dl.model.Payer",
            authorization.getPayerID(),
            authorization.getBusinessEntityID());

        for (Payer item : payers) {
            getAuthResult.setPayer(item);
        }

        List<Patient> patients = (List<Patient>) oracleDataService.getEntitiesForId(
            "SELECT * FROM "
                + ConnectionType.COREDATA
                + ".PT WHERE PT_ID = ? AND BE_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
            "com.sandata.lab.data.model.dl.model.Patient",
            authorization.getPatientID(),
            authorization.getBusinessEntityID());

        for (Patient item : patients) {
            getAuthResult.setPatient(item);
        }

        authorization.getAuthorizationService().addAll(getAuthorizationServiceListForAuthorization(authorization));

        return getAuthResult;
    }

    /**
     * Returns List of AuthorizationService for the specified Authorization.  If Authorization
     * has no ID and is therefore an Order, OrderService are looked up and transformed to
     * AuthorizationService.
     *
     * @param authorization Specified Authorization.
     * @return List of AuthorizationService.
     */
    private List<AuthorizationService> getAuthorizationServiceListForAuthorization(Authorization authorization) {
        List<AuthorizationService> authorizationServiceList;
        if (authorization.getAuthorizationID() == null
            || authorization.getAuthorizationID().isEmpty()) {
            authorizationServiceList = new ArrayList<>();

            List<OrderService> orderServiceList = (List<OrderService>) oracleDataService.getAuthOrOdSvcForSk(authorization.getAuthorizationSK().longValue(),
                "ORD_SVC",
                "ORD_SK",
                "ORD_SVC_START_DATE",
                "OrderService");
            for (OrderService orderService : orderServiceList) {
                authorizationServiceList.add(transformOrderServiceToAuthorizationService(orderService));
            }

        } else {
            authorizationServiceList = (List<AuthorizationService>)
                oracleDataService.getAuthOrOdSvcForSk(authorization.getAuthorizationSK().longValue(),
                    "AUTH_SVC",
                    "AUTH_SK",
                    "AUTH_SVC_START_DATE",
                    "AuthorizationService");
        }

        return authorizationServiceList;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            if (operationName.contains("deleteAuth_Authorization")) {
                // Get orderSk from Authorization and delete order.

                ArrayList<Authorization> resultList = (ArrayList<Authorization>) oracleDataService.executeGet(packageName,
                    "getAuth",
                    "com.sandata.lab.data.model.dl.model.Authorization",
                    sequenceKey);

                if (resultList != null
                    && !resultList.isEmpty()) {
                    Authorization authorization = resultList.get(0);

                    if (authorization.getOrderSK() != null
                        && !authorization.getOrderSK().equals(BigInteger.ZERO)) {
                        long result = oracleDataService.execute(packageName,
                            "deleteOrd",
                            authorization.getOrderSK());
                        if (result != 1) {
                            throw new SandataRuntimeException("Failed to delete Authorizations underlying Order!");
                        }
                    }

                }
            }

            long result = 0;
            //FOR DELETE APP_USER_KEY_CONF
            if (methodName.equals("deleteAppUserKeyConf")) {
                ConnectionType connectionType = ConnectionType.METADATA;
                result = oracleDataService.execute(
                    connectionType,
                    packageName,
                    methodName,
                    sequenceKey
                );
            } else {

                result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
                );

            }

            exchange.getIn().setBody(result);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");
            Object data = exchange.getIn().getBody();

            if (operationName.contains("_updateOrdSvc_OrderService")) {
                data = transformAuthorizationServiceToOrderService((AuthorizationService) data);
            }

            //connection = oracleDataService.getOracleConnection();
            if (operationName.contains("_updateAppUserKeyConf")) {
                connection = oracleDataService.getOracleConnection(ConnectionType.METADATA);
            } else {
                connection = oracleDataService.getOracleConnection();
            }

            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException("Update was not successful!");
            }

            logger.stop();
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");
            Object data = exchange.getIn().getBody();

            if (data instanceof Authorization) {
                //dmr--GEOR-2785 3/18/2016
                //dmr--GEOR-2995 3/29/2016: It should only do this for a date that is null per Hiren's comments;
                Authorization authorization = (Authorization) data;
                if (authorization.getAuthorizationEndTimestamp() == null) {
                    authorization.setAuthorizationEndTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("9999-12-31 00:00:00"));
                }
            }

            if (operationName.contains("_insertOrd_Order")) {
                // Transform Authorization to Order.
                data = transformAuthorizationToOrder((Authorization) data);
            } else if (operationName.contains("_insertOrdSvc_OrderService")) {
                data = transformAuthorizationServiceToOrderService((AuthorizationService) data);
            }

            //connection = oracleDataService.getOracleConnection();

            if (operationName.contains("_insertAppUserKeyConf")) {
                connection = oracleDataService.getOracleConnection(ConnectionType.METADATA);
            } else {
                connection = oracleDataService.getOracleConnection();
            }

            connection.setAutoCommit(false);

            long returnVal = executeRecursive(connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException("Insert was not successful!");
            }

            logger.stop();
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    private void setSk(final Object jpubType, final long sequenceKey, final String skSetMethodName) throws Exception {

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

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal) throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setAuthSk");
            setSk(jpubType, returnVal, "setOrdSk");

            long result = 0;

            if (bShouldInsert) {

                //for meta data
                if (data instanceof ApplicationUserKeyConfiguration) {
                    result = oracleDataService.execute(
                        connection,
                        ConnectionType.METADATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                    );

                } else {

                    result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                    );

                }

            } else {

                //dmr--GEOR-5747: The PKG_HIST.updateAuth will return the new SK value
                /*if (data instanceof Authorization) {
                    returnVal = ((Authorization) data).getAuthorizationSK().longValue();
                }*/

                // UPDATE - for meta data
                if (data instanceof ApplicationUserKeyConfiguration) {

                    returnVal = ((ApplicationUserKeyConfiguration) data).getApplicationUserKeyConfigurationSK().longValue();
                    result = oracleDataService.execute(
                        connection,
                        ConnectionType.METADATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                    );
                } else {

                    String packageName = oracleMetadata.packageName();

                    //TODO: Don't like this but...
                    if (data instanceof Authorization) {
                        packageName = "PKG_HIST";
                    }

                    result = oracleDataService.execute(
                        connection,
                        packageName,
                        oracleMetadata.updateMethod(),
                        jpubType
                    );

                    if (data instanceof Authorization) {
                        result = ((Authorization) data).getAuthorizationSK().longValue();
                    } else if (data instanceof AuthorizationService) {
                        result = ((AuthorizationService) data).getAuthorizationServiceSK().longValue();
                    } else if (data instanceof Order) {
                        result = ((Order) data).getOrderSK().longValue();
                    } else if (data instanceof OrderService) {
                        result = ((OrderService) data).getOrderServiceSK().longValue();
                    }
                }
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
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                        object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
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
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void getActiveOrHistoricAuthAndOrderList(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        String operationName = (String) exchange.getIn().getHeader("operationName");

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null
                || patientId.isEmpty()) {
                throw new SandataRuntimeException("PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) mcl.get(2);
            int pageSize = (Integer) mcl.get(3);
            String orderByColumn = (String) mcl.get(4);
            String orderByDirection = (String) mcl.get(5);
            String historicClauseAuth;
            String historicClauseOrder;

            if (operationName.contains("_getActiveAuth")) {
                historicClauseAuth = "(AUTH_END_TMSTP IS NULL OR AUTH_END_TMSTP >= CURRENT_TIMESTAMP)";
                historicClauseOrder = "(ORD.ORD_END_TMSTP IS NULL OR ORD.ORD_END_TMSTP >= CURRENT_TIMESTAMP)";
            } else if (operationName.contains("_getHistoricAuth")) {
                historicClauseAuth = "(AUTH_END_TMSTP IS NOT NULL AND AUTH_END_TMSTP < CURRENT_TIMESTAMP)";
                historicClauseOrder = "(ORD.ORD_END_TMSTP IS NOT NULL AND ORD.ORD_END_TMSTP < CURRENT_TIMESTAMP)";
            } else {
                throw new SandataRuntimeException(String.format("Unrecognized operation %s!", operationName));
            }

            Response response = oracleDataService.getHistoricOrActiveAuthAndOrderList(patientId,
                bsnEntId,
                page,
                pageSize,
                orderByColumn,
                orderByDirection,
                historicClauseAuth,
                historicClauseOrder);

            List<Authorization> authorizationList = (List<Authorization>) response.getData();
            List<AuthorizationExt> authorizationExtList = new ArrayList<>();

            // Get child lists for each Authorization.
            for (Authorization authorization : authorizationList) {
                // Instantiate extension Object and get AuthorizationService List.
                AuthorizationExt authorizationExt = new AuthorizationExt(authorization);
                authorizationExt.getAuthorizationService().addAll(getAuthorizationServiceListForAuthorization(authorization));


                // Attach PatientPayer.
                List<PatientPayer> patientPayerList = (List<PatientPayer>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM "
                        + ConnectionType.COREDATA
                        + ".PT_PAYER"
                        + " WHERE BE_ID=?"
                        + "  AND PAYER_ID=?"
                        + "  AND PT_ID=?"
                        + "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.PatientPayer",
                    authorizationExt.getBusinessEntityID(),
                    authorizationExt.getPayerID(),
                    authorizationExt.getPatientID());

                if (patientPayerList != null
                    && !patientPayerList.isEmpty()) {
                    authorizationExt.setPatientPayer(patientPayerList.get(0));
                }

                // Get Payer name.
                List<Payer> payerList = (List<Payer>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM "
                        + ConnectionType.COREDATA
                        + ".PAYER"
                        + " WHERE BE_ID=?"
                        + "  AND PAYER_ID=?"
                        + "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.Payer",
                    authorizationExt.getBusinessEntityID(),
                    authorizationExt.getPayerID());

                if (payerList != null
                    && !payerList.isEmpty()) {
                    authorizationExt.setPayerName(payerList.get(0).getPayerName());
                }

                authorizationExtList.add(authorizationExt);
            }

            // Get all Plan of Care Entities for each authorization.
            attachPlanOfCareForAuthorizations(authorizationExtList);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(authorizationExtList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getAuthOrOrdSvcForSk(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");
            String className = methodParts[3];

            String skKey;
            String table;
            String column;
            String sortAscColumn;

            if (className.equalsIgnoreCase("AuthorizationService")) {
                skKey = "auth_sk";
                table = "AUTH_SVC";
                column = "AUTH_SK";
                sortAscColumn = "AUTH_SVC_START_DATE";
            } else if (className.equalsIgnoreCase("OrderService")) {
                skKey = "ord_sk";
                table = "ORD_SVC";
                column = "ORD_SK";
                sortAscColumn = "ORD_SVC_START_DATE";
            } else {
                throw new SandataRuntimeException(String.format("Unknown entity %s for operation %s!", className, operationName));
            }

            Long entitySk = (Long) exchange.getIn().getHeader(skKey);
            if (entitySk == null) {
                throw new SandataRuntimeException(String.format("Parameter %s required!", skKey));
            }

            List results = oracleDataService.getAuthOrOdSvcForSk(entitySk, table, column, sortAscColumn, className);

            if (className.equalsIgnoreCase("OrderService")) {
                List<AuthorizationService> authorizationServiceList = new ArrayList<>();
                for (OrderService orderService : (List<OrderService>) results) {
                    authorizationServiceList.add(transformOrderServiceToAuthorizationService(orderService));
                }
                results = authorizationServiceList;
            }

            exchange.getIn().setBody(results);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Returns an Order with properties copied over from the Specified Authorization.
     *
     * @param authorization Specified Authorization.
     * @return Order.
     */
    public Order transformAuthorizationToOrder(Authorization authorization) {
        Order order = new Order();

        order.setOrderSK(authorization.getAuthorizationSK());

        // Recursively handle any child Authorizations.
        if (authorization.getAuthorization() != null) {
            // Order can have child Authorizations and child Orders.
            List<Authorization> authorizationList = new ArrayList<>();
            List<Order> orderList = new ArrayList<>();

            for (Authorization authorization1 : authorization.getAuthorization()) {
                if (authorization1.getAuthorizationID() == null
                    || authorization1.getAuthorizationID().isEmpty()) {
                    orderList.add(transformAuthorizationToOrder(authorization1));
                } else {
                    authorizationList.add(authorization1);
                }
            }

            order.getAuthorization().addAll(authorizationList);
            order.getOrder().addAll(orderList);
        }

        // Transform any child authorization services.
        if (authorization.getAuthorizationService() != null) {
            List<OrderService> orderServiceList = new ArrayList<>();
            for (AuthorizationService authorizationService : authorization.getAuthorizationService()) {
                orderServiceList.add(transformAuthorizationServiceToOrderService(authorizationService));
            }
            order.getOrderService().addAll(orderServiceList);
        }

        order.setRecordCreateTimestamp(authorization.getRecordCreateTimestamp());
        order.setRecordUpdateTimestamp(authorization.getRecordUpdateTimestamp());
        order.setRecordEffectiveTimestamp(authorization.getRecordEffectiveTimestamp());
        order.setRecordTerminationTimestamp(authorization.getRecordTerminationTimestamp());
        order.setRecordCreatedBy(authorization.getRecordCreatedBy());
        order.setRecordUpdatedBy(authorization.getRecordUpdatedBy());
        order.setChangeReasonMemo(authorization.getChangeReasonMemo());
        order.setCurrentRecordIndicator(authorization.isCurrentRecordIndicator());
        order.setChangeVersionID(authorization.getChangeVersionID());
        order.setBusinessEntityID(authorization.getBusinessEntityID());
        order.setPatientID(authorization.getPatientID());
        order.setPayerID(authorization.getPayerID());
        order.setOrderIssuedDate(authorization.getAuthorizationIssuedDate());
        order.setOrderStartTimestamp(authorization.getAuthorizationStartTimestamp());
        order.setOrderEndTimestamp(authorization.getAuthorizationEndTimestamp());
        order.setOrderComment(authorization.getAuthorizationComment());
        order.setOrderServiceUnitName(authorization.getAuthorizationServiceUnitName());
        order.setOrderLimitTypeName(authorization.getAuthorizationLimitTypeName());
        order.setOrderLimitTotal(authorization.getAuthorizationLimitTotal());
        order.setOrderLimitDay1(authorization.getAuthorizationLimitDay1());
        order.setOrderLimit(authorization.getAuthorizationLimit());
        order.setOrderLimitStartTimeDay1(authorization.getAuthorizationLimitStartTimeDay1());
        order.setOrderLimitEndTimeDay1(authorization.getAuthorizationLimitEndTimeDay1());
        order.setOrderLimitDay2(authorization.getAuthorizationLimitDay2());
        order.setOrderLimitStartTimeDay2(authorization.getAuthorizationLimitStartTimeDay2());
        order.setOrderLimitEndTimeDay2(authorization.getAuthorizationLimitEndTimeDay2());
        order.setOrderLimitDay3(authorization.getAuthorizationLimitDay3());
        order.setOrderLimitStartTimeDay3(authorization.getAuthorizationLimitStartTimeDay3());
        order.setOrderLimitEndTimeDay3(authorization.getAuthorizationLimitEndTimeDay3());
        order.setOrderLimitDay4(authorization.getAuthorizationLimitDay4());
        order.setOrderLimitStartTimeDay4(authorization.getAuthorizationLimitStartTimeDay4());
        order.setOrderLimitEndTimeDay4(authorization.getAuthorizationLimitEndTimeDay4());
        order.setOrderLimitDay5(authorization.getAuthorizationLimitDay5());
        order.setOrderLimitStartTimeDay5(authorization.getAuthorizationLimitStartTimeDay5());
        order.setOrderLimitEndTimeDay5(authorization.getAuthorizationLimitEndTimeDay5());
        order.setOrderLimitDay6(authorization.getAuthorizationLimitDay6());
        order.setOrderLimitStartTimeDay6(authorization.getAuthorizationLimitStartTimeDay6());
        order.setOrderLimitEndTimeDay6(authorization.getAuthorizationLimitEndTimeDay6());
        order.setOrderLimitDay7(authorization.getAuthorizationLimitDay7());
        order.setOrderLimitStartTimeDay7(authorization.getAuthorizationLimitStartTimeDay7());
        order.setOrderLimitEndTimeDay7(authorization.getAuthorizationLimitEndTimeDay7());

        return order;
    }

    /**
     * Returns an OrderService with properties copied from the specified AuthorizationService.
     *
     * @param authorizationService Specified AuthorizationService.
     * @return OrderService.
     */
    public OrderService transformAuthorizationServiceToOrderService(AuthorizationService authorizationService) {
        OrderService orderService = new OrderService();

        orderService.setOrderServiceSK(authorizationService.getAuthorizationServiceSK());
        orderService.setRecordCreateTimestamp(authorizationService.getRecordCreateTimestamp());
        orderService.setRecordUpdateTimestamp(authorizationService.getRecordUpdateTimestamp());
        orderService.setChangeVersionID(authorizationService.getChangeVersionID());
        orderService.setOrderSK(authorizationService.getAuthorizationSK());
        orderService.setBusinessEntityID(authorizationService.getBusinessEntityID());
        orderService.setServiceName(authorizationService.getServiceName());
        orderService.setBillingCode(authorizationService.getBillingCode());
        orderService.setRateTypeName(authorizationService.getRateTypeName());
        orderService.setRateQualifierCode(authorizationService.getRateQualifierCode());
        orderService.setOrderServiceRateAmount(authorizationService.getAuthorizationServiceRateAmount());
        orderService.setOrderServiceStartDate(authorizationService.getAuthorizationServiceStartDate());
        orderService.setOrderServiceEndDate(authorizationService.getAuthorizationServiceEndDate());
        orderService.setOrderLimitTypeName(authorizationService.getAuthorizationLimitTypeName());
        orderService.setOrderServiceUnitName(authorizationService.getAuthorizationServiceUnitName());
        orderService.setOrderServiceLimit(authorizationService.getAuthorizationServiceLimit());
        orderService.setOrderServiceLimitStartTimeDay1(authorizationService.getAuthorizationServiceLimitStartTimeDay1());
        orderService.setOrderServiceLimitEndTimeDay1(authorizationService.getAuthorizationServiceLimitEndTimeDay1());
        orderService.setOrderServiceLimitDay1(authorizationService.getAuthorizationServiceLimitDay1());
        orderService.setOrderServiceLimitStartTimeDay2(authorizationService.getAuthorizationServiceLimitStartTimeDay2());
        orderService.setOrderServiceLimitEndTimeDay2(authorizationService.getAuthorizationServiceLimitEndTimeDay2());
        orderService.setOrderServiceLimitDay2(authorizationService.getAuthorizationServiceLimitDay2());
        orderService.setOrderServiceLimitStartTimeDay3(authorizationService.getAuthorizationServiceLimitStartTimeDay3());
        orderService.setOrderServiceLimitEndTimeDay3(authorizationService.getAuthorizationServiceLimitEndTimeDay3());
        orderService.setOrderServiceLimitDay3(authorizationService.getAuthorizationServiceLimitDay3());
        orderService.setOrderServiceLimitStartTimeDay4(authorizationService.getAuthorizationServiceLimitStartTimeDay4());
        orderService.setOrderServiceLimitEndTimeDay4(authorizationService.getAuthorizationServiceLimitEndTimeDay4());
        orderService.setOrderServiceLimitDay4(authorizationService.getAuthorizationServiceLimitDay4());
        orderService.setOrderServiceLimitStartTimeDay5(authorizationService.getAuthorizationServiceLimitStartTimeDay5());
        orderService.setOrderServiceLimitEndTimeDay5(authorizationService.getAuthorizationServiceLimitEndTimeDay5());
        orderService.setOrderServiceLimitDay5(authorizationService.getAuthorizationServiceLimitDay5());
        orderService.setOrderServiceLimitStartTimeDay6(authorizationService.getAuthorizationServiceLimitStartTimeDay6());
        orderService.setOrderServiceLimitEndTimeDay6(authorizationService.getAuthorizationServiceLimitEndTimeDay6());
        orderService.setOrderServiceLimitDay6(authorizationService.getAuthorizationServiceLimitDay6());
        orderService.setOrderServiceLimitStartTimeDay7(authorizationService.getAuthorizationServiceLimitStartTimeDay7());
        orderService.setOrderServiceLimitEndTimeDay7(authorizationService.getAuthorizationServiceLimitEndTimeDay7());
        orderService.setOrderServiceLimitDay7(authorizationService.getAuthorizationServiceLimitDay7());

        return orderService;
    }

    /**
     * Returns an Authorization with properties copied over from the Specified Order.
     *
     * @param order Specified Order.
     * @return Authorization.
     */
    public Authorization transformOrderToAuthorization(Order order) {
        Authorization authorization = new Authorization();

        authorization.setAuthorizationSK(order.getOrderSK());

        // Recursively handle any child Orders.
        if (order.getOrder() != null) {
            List<Authorization> authorizationList = new ArrayList<>();

            for (Order order1 : order.getOrder()) {
                authorizationList.add(transformOrderToAuthorization(order1));
            }

            authorization.getAuthorization().addAll(authorizationList);
        }

        // Recursively transform any child order services.
        if (order.getOrderService() != null) {
            List<AuthorizationService> authorizationServiceList = new ArrayList<>();
            for (OrderService orderService : order.getOrderService()) {
                authorizationServiceList.add(transformOrderServiceToAuthorizationService(orderService));
            }
            authorization.getAuthorizationService().addAll(authorizationServiceList);
        }

        authorization.setRecordCreateTimestamp(order.getRecordCreateTimestamp());
        authorization.setRecordUpdateTimestamp(order.getRecordUpdateTimestamp());
        authorization.setRecordEffectiveTimestamp(order.getRecordEffectiveTimestamp());
        authorization.setRecordTerminationTimestamp(order.getRecordTerminationTimestamp());
        authorization.setRecordCreatedBy(order.getRecordCreatedBy());
        authorization.setRecordUpdatedBy(order.getRecordUpdatedBy());
        authorization.setChangeReasonMemo(order.getChangeReasonMemo());
        authorization.setCurrentRecordIndicator(order.isCurrentRecordIndicator());
        authorization.setChangeVersionID(order.getChangeVersionID());
        authorization.setBusinessEntityID(order.getBusinessEntityID());
        authorization.setPatientID(order.getPatientID());
        authorization.setPayerID(order.getPayerID());
        authorization.setAuthorizationIssuedDate(order.getOrderIssuedDate());
        authorization.setAuthorizationStartTimestamp(order.getOrderStartTimestamp());
        authorization.setAuthorizationEndTimestamp(order.getOrderEndTimestamp());
        authorization.setAuthorizationComment(order.getOrderComment());
        authorization.setAuthorizationServiceUnitName(order.getOrderServiceUnitName());
        authorization.setAuthorizationLimitTypeName(order.getOrderLimitTypeName());
        authorization.setAuthorizationLimitTotal(order.getOrderLimitTotal());
        authorization.setAuthorizationLimitDay1(order.getOrderLimitDay1());
        authorization.setAuthorizationLimit(order.getOrderLimit());
        authorization.setAuthorizationLimitStartTimeDay1(order.getOrderLimitStartTimeDay1());
        authorization.setAuthorizationLimitEndTimeDay1(order.getOrderLimitEndTimeDay1());
        authorization.setAuthorizationLimitDay2(order.getOrderLimitDay2());
        authorization.setAuthorizationLimitStartTimeDay2(order.getOrderLimitStartTimeDay2());
        authorization.setAuthorizationLimitEndTimeDay2(order.getOrderLimitEndTimeDay2());
        authorization.setAuthorizationLimitDay3(order.getOrderLimitDay3());
        authorization.setAuthorizationLimitStartTimeDay3(order.getOrderLimitStartTimeDay3());
        authorization.setAuthorizationLimitEndTimeDay3(order.getOrderLimitEndTimeDay3());
        authorization.setAuthorizationLimitDay4(order.getOrderLimitDay4());
        authorization.setAuthorizationLimitStartTimeDay4(order.getOrderLimitStartTimeDay4());
        authorization.setAuthorizationLimitEndTimeDay4(order.getOrderLimitEndTimeDay4());
        authorization.setAuthorizationLimitDay5(order.getOrderLimitDay5());
        authorization.setAuthorizationLimitStartTimeDay5(order.getOrderLimitStartTimeDay5());
        authorization.setAuthorizationLimitEndTimeDay5(order.getOrderLimitEndTimeDay5());
        authorization.setAuthorizationLimitDay6(order.getOrderLimitDay6());
        authorization.setAuthorizationLimitStartTimeDay6(order.getOrderLimitStartTimeDay6());
        authorization.setAuthorizationLimitEndTimeDay6(order.getOrderLimitEndTimeDay6());
        authorization.setAuthorizationLimitDay7(order.getOrderLimitDay7());
        authorization.setAuthorizationLimitStartTimeDay7(order.getOrderLimitStartTimeDay7());
        authorization.setAuthorizationLimitEndTimeDay7(order.getOrderLimitEndTimeDay7());

        return authorization;
    }

    /**
     * Returns an AuthorizationService with properties copied from the specified OrderService.
     *
     * @param orderService Specified OrderService.
     * @return AuthorizationService.
     */
    public AuthorizationService transformOrderServiceToAuthorizationService(OrderService orderService) {
        AuthorizationService authorizationService = new AuthorizationService();

        authorizationService.setAuthorizationServiceSK(orderService.getOrderServiceSK());
        authorizationService.setRecordCreateTimestamp(orderService.getRecordCreateTimestamp());
        authorizationService.setRecordUpdateTimestamp(orderService.getRecordUpdateTimestamp());
        authorizationService.setChangeVersionID(orderService.getChangeVersionID());
        authorizationService.setAuthorizationSK(orderService.getOrderSK());
        authorizationService.setBusinessEntityID(orderService.getBusinessEntityID());
        authorizationService.setServiceName(orderService.getServiceName());
        authorizationService.setBillingCode(orderService.getBillingCode());
        authorizationService.setRateTypeName(orderService.getRateTypeName());
        authorizationService.setRateQualifierCode(orderService.getRateQualifierCode());
        authorizationService.setAuthorizationServiceRateAmount(orderService.getOrderServiceRateAmount());
        authorizationService.setAuthorizationServiceStartDate(orderService.getOrderServiceStartDate());
        authorizationService.setAuthorizationServiceEndDate(orderService.getOrderServiceEndDate());
        authorizationService.setAuthorizationLimitTypeName(orderService.getOrderLimitTypeName());
        authorizationService.setAuthorizationServiceUnitName(orderService.getOrderServiceUnitName());
        authorizationService.setAuthorizationServiceLimit(orderService.getOrderServiceLimit());
        authorizationService.setAuthorizationServiceLimitStartTimeDay1(orderService.getOrderServiceLimitStartTimeDay1());
        authorizationService.setAuthorizationServiceLimitEndTimeDay1(orderService.getOrderServiceLimitEndTimeDay1());
        authorizationService.setAuthorizationServiceLimitDay1(orderService.getOrderServiceLimitDay1());
        authorizationService.setAuthorizationServiceLimitStartTimeDay2(orderService.getOrderServiceLimitStartTimeDay2());
        authorizationService.setAuthorizationServiceLimitEndTimeDay2(orderService.getOrderServiceLimitEndTimeDay2());
        authorizationService.setAuthorizationServiceLimitDay2(orderService.getOrderServiceLimitDay2());
        authorizationService.setAuthorizationServiceLimitStartTimeDay3(orderService.getOrderServiceLimitStartTimeDay3());
        authorizationService.setAuthorizationServiceLimitEndTimeDay3(orderService.getOrderServiceLimitEndTimeDay3());
        authorizationService.setAuthorizationServiceLimitDay3(orderService.getOrderServiceLimitDay3());
        authorizationService.setAuthorizationServiceLimitStartTimeDay4(orderService.getOrderServiceLimitStartTimeDay4());
        authorizationService.setAuthorizationServiceLimitEndTimeDay4(orderService.getOrderServiceLimitEndTimeDay4());
        authorizationService.setAuthorizationServiceLimitDay4(orderService.getOrderServiceLimitDay4());
        authorizationService.setAuthorizationServiceLimitStartTimeDay5(orderService.getOrderServiceLimitStartTimeDay5());
        authorizationService.setAuthorizationServiceLimitEndTimeDay5(orderService.getOrderServiceLimitEndTimeDay5());
        authorizationService.setAuthorizationServiceLimitDay5(orderService.getOrderServiceLimitDay5());
        authorizationService.setAuthorizationServiceLimitStartTimeDay6(orderService.getOrderServiceLimitStartTimeDay6());
        authorizationService.setAuthorizationServiceLimitEndTimeDay6(orderService.getOrderServiceLimitEndTimeDay6());
        authorizationService.setAuthorizationServiceLimitDay6(orderService.getOrderServiceLimitDay6());
        authorizationService.setAuthorizationServiceLimitStartTimeDay7(orderService.getOrderServiceLimitStartTimeDay7());
        authorizationService.setAuthorizationServiceLimitEndTimeDay7(orderService.getOrderServiceLimitEndTimeDay7());
        authorizationService.setAuthorizationServiceLimitDay7(orderService.getOrderServiceLimitDay7());

        return authorizationService;
    }

    /**
     * Attaches associated Plan of Cares for each Authorization in the specified List.
     *
     * @param authorizationExtList Specified Authorization List.
     */
    private void attachPlanOfCareForAuthorizations(List<AuthorizationExt> authorizationExtList) {

        try {

            for (AuthorizationExt authorizationExt : authorizationExtList) {

                String entity;
                if (authorizationExt.getAuthorizationID() != null
                    && !authorizationExt.getAuthorizationID().isEmpty()) {
                    entity = "Authorization";
                } else {
                    entity = "Order";
                }

                if (authorizationExt.getAuthorizationService() == null
                    || authorizationExt.getAuthorizationService().isEmpty()) {
                    throw new SandataRuntimeException(String.format("%s must have a service for %s SK %s!", entity, entity, authorizationExt.getAuthorizationSK()));
                }

                if (authorizationExt.getAuthorizationStartTimestamp() == null) {
                    throw new SandataRuntimeException(String.format("%s must have a start date for %s SK %s!", entity, entity, authorizationExt.getAuthorizationSK()));
                }

                // Look up PlanOfCareList based on AUTH.
                List<PlanOfCare> planOfCareList = oracleDataService.getPlanOfCareForAuthorization(
                    authorizationExt.getBusinessEntityID(),
                    authorizationExt.getPatientID(),
                    authorizationExt.getAuthorizationID(),
                    authorizationExt.getAuthorizationStartTimestamp(),
                    authorizationExt.getAuthorizationEndTimestamp());

                // Plan of Care not required for an Authorization/Order.
                if (planOfCareList != null) {

                    for (PlanOfCare planOfCare : planOfCareList) {
                        List<PlanOfCareTaskList> pocTaskList = (List<PlanOfCareTaskList>) oracleDataService.getEntitiesForId(
                            String.format("SELECT * FROM %s.POC_TASK_LST WHERE POC_SK = ?", ConnectionType.COREDATA),
                            "com.sandata.lab.data.model.dl.model.PlanOfCareTaskList",
                            planOfCare.getPlanOfCareSK());

                        List<PlanOfCareService> planOfCareServiceList = (List<PlanOfCareService>) oracleDataService.getEntitiesForId(
                            String.format("SELECT * FROM %s.POC_SVC WHERE POC_SK = ?", ConnectionType.COREDATA),
                            "com.sandata.lab.data.model.dl.model.PlanOfCareService",
                            planOfCare.getPlanOfCareSK());

                        planOfCare.getPlanOfCareTaskList().addAll(pocTaskList);
                        planOfCare.getPlanOfCareService().addAll(planOfCareServiceList);

                        authorizationExt.getPlanOfCareList().add(planOfCare);
                    }
                }
            }

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }


    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void updateAuthOrOrd(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {
            Authorization authorization = (Authorization) exchange.getIn().getBody();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String operationName = (String) exchange.getIn().getHeader("operationName");
            
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            // Cancel all Schedule Events after Authorization's end date
            if (authorization.getAuthorizationEndTimestamp() != null
                && !dateFormat.format(authorization.getAuthorizationEndTimestamp()).equals("12/31/9999")) {
                
                logger.info(String.format("UpdateAuthOrOrd: The Auth's end date is (%s), need to cancel schedule event(s) after the end date. "
                        + "AuthSK = %s, PatientID=%s, BeID=%s, AuthID=%s",
                        authorization.getAuthorizationEndTimestamp(),
                        authorization.getAuthorizationSK(),
                        authorization.getPatientID(),
                        authorization.getBusinessEntityID(),
                        authorization.getAuthorizationID()));
                // if auth/order has been end-dated, cancel all schedule events after this date.
                int numSchedEventsCancelled = oracleDataService.cancelSchedEventsForEndDatedAuth(connection,
                    authorization.getBusinessEntityID(),
                    authorization.getPatientID(),
                    authorization.getAuthorizationEndTimestamp());

                logger.info(String.format("UpdateAuthOrOrd: %s schedule event(s) cancelled "
                        + "for business entity ID (%s) and patient ID (%s) and AuthSK (%s) after date %s.",
                    numSchedEventsCancelled,
                    authorization.getBusinessEntityID(),
                    authorization.getPatientID(),
                    authorization.getAuthorizationSK(),
                    authorization.getAuthorizationEndTimestamp()));
            } else {
                logger.info(String.format("UpdateAuthOrOrd: The Auth's end date is (%s) and NOT to cancel schedule event(s) after the end date. "
                        + "AuthSK = %s, PatientID=%s, BeID=%s, AuthID = %s",
                        authorization.getAuthorizationEndTimestamp(),
                        authorization.getAuthorizationSK(),
                        authorization.getPatientID(),
                        authorization.getBusinessEntityID(),
                        authorization.getAuthorizationID()));
            }

            long resultSK = 0;
            BigInteger oldSK = authorization.getAuthorizationSK();
            // Authorization Update
            if (operationName.contains("_updateAuth_")) {
                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(authorization);
                Object jpubType = new DataMapper().map(authorization);
                
                // update history
                resultSK = oracleDataService.execute(connection, 
                        "PKG_HIST", 
                        oracleMetadata.updateMethod(), 
                        jpubType);
                if (resultSK < 0) {
                    throw new SandataRuntimeException("UpdateAuthOrOrd - ERROR: Update History for Authorization was not successful!");
                }
                
                logger.info(String.format("UpdateAuthOrOrd: Update history for Authorization successfully. "
                        + "OldAuthSK = %s, NewAuthSK=%s, PatientID=%s, BeID=%s",
                        oldSK,
                        resultSK,
                        authorization.getPatientID(),
                        authorization.getBusinessEntityID()));
                
                // update child elements sent to MW
                executeRecursiveForChildListElements(connection, authorization, resultSK, false /* UPDATE */);
                
                // update relevant tables
                int result = oracleDataService.updateAuthOrOrderRelevantTables(connection, 
                        oldSK.longValue(), 
                        (long) resultSK, 
                        AuthorizationQualifier.AUTHORIZATION.toString());
                if (result < 0) {
                    throw new SandataRuntimeException(
                            String.format("UpdateAuthOrOrd - ERROR: Update relevant tables for new Authorization SK = %s was not successful!", resultSK));
                }
                
            // No Authorization ID provided, this is Order Update!
            } else if (operationName.contains("_updateOrd_Order")
                    && (authorization.getAuthorizationID() == null || authorization.getAuthorizationID().isEmpty())) {
                Order order = transformAuthorizationToOrder(authorization);
                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(order);
                Object jpubType = new DataMapper().map(order);
                
                // update history
                resultSK = oracleDataService.execute(connection, 
                        "PKG_HIST", 
                        oracleMetadata.updateMethod(), 
                        jpubType);
                if (resultSK < 0) {
                    throw new SandataRuntimeException("UpdateAuthOrOrd - ERROR: Update History for Order was not successful!");
                }
                
                logger.info(String.format("UpdateAuthOrOrd: Update history for Order successfully. "
                        + "OldOrderSK = %s, NewOrderSK = %s, PatientID=%s, BeID=%s",
                        oldSK,
                        resultSK,
                        authorization.getPatientID(),
                        authorization.getBusinessEntityID()));
                
                // update child elements sent to MW
                executeRecursiveForChildListElements(connection, order, resultSK, false /* UPDATE */);
                
                // update relevant tables
                int result = oracleDataService.updateAuthOrOrderRelevantTables(connection, 
                        oldSK.longValue(), 
                        (long) resultSK, 
                        AuthorizationQualifier.ORDER.toString());
                if (result < 0) {
                    throw new SandataRuntimeException(
                            String.format("UpdateAuthOrOrd - ERROR: Update relevant tables for new Order SK = %s was not successful!", resultSK));
                }
                
            // Check if Authorization ID exists turning this Order into an Authorization.
            } else if (operationName.contains("_updateOrd_Order")
                    && (authorization.getAuthorizationID() != null && !authorization.getAuthorizationID().isEmpty())) {
                transformExistingOrderToAuthorization(connection, authorization);
                
                // insert new Authorization which is transformed from Order with Authorization ID
                resultSK = executeRecursive(connection, authorization, true /* INSERT */, -999);
                if (resultSK < 0) {
                    throw new SandataRuntimeException("UpdateAuthOrOrd - ERROR: INSERT new Authorization from Order was not successful!");
                }
                
                logger.info(String.format("UpdateAuthOrOrd: Insert new Authorization transformed from Order successfully. "
                        + "OldOrderSK = %s, NewAuthSK = %s, PatientID=%s, BeID=%s",
                        oldSK,
                        resultSK,
                        authorization.getPatientID(),
                        authorization.getBusinessEntityID()));
                
                int result = oracleDataService.updateOrderRelevantTablesForTransformedAuthorization(connection, oldSK.longValue(), (long) resultSK);
                if (result < 0) {
                    throw new SandataRuntimeException(
                            String.format("UpdateAuthOrOrd - ERROR: Update relevant tables for new Authorization SK = %s transformed from Order SK = %s was not successful!", resultSK, oldSK));
                }
            }
            
            logger.info(String.format("UpdateAuthOrOrd: Update relevant tables successfully. "
                    + "OldSK = %s, NewSK = %s, PatientID=%s, BeID=%s",
                    oldSK,
                    resultSK,
                    authorization.getPatientID(),
                    authorization.getBusinessEntityID()));
            
            exchange.getIn().setBody(resultSK);
            connection.commit();

        } catch (Exception e) {

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            String errMsg = format("UpdateAuthOrOrd - ERROR: %s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void executeRecursiveForChildListElements(Connection connection, Object data, long resultSK, boolean isInsert)
            throws IllegalArgumentException, IllegalAccessException {
        for (Field field : data.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            Object property = field.get(data);
            if (property != null && property instanceof List) {

                List list = (List) property;
                for (Object object : list) {

                    // WARNING: RECURSIVE!!!!
                    long updateResponse = executeRecursive(connection, object, isInsert, resultSK);
                    if (updateResponse == -1) {
                        throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
                            object.getClass().getName()));
                    }
                }
            }
        }
    }
    
    private void transformExistingOrderToAuthorization(Connection connection, Authorization authorization) {
        try {
            authorization.setOrderSK(authorization.getAuthorizationSK());
            authorization.setAuthorizationSK(null);
            if (authorization.getAuthorizationService() == null
                || authorization.getAuthorizationService().isEmpty()) {
                List<OrderService> orderServiceList = getOrderServiceListForOrder(connection, 
                        authorization.getOrderSK(), 
                        authorization.getBusinessEntityID());
                List<AuthorizationService> authorizationServiceList = new ArrayList<>();
                
                for (OrderService orderService : orderServiceList) {
                    authorizationServiceList.add(transformOrderServiceToAuthorizationService(orderService));
                }
                authorization.getAuthorizationService().addAll(authorizationServiceList);
            }
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }

    }

    private List<OrderService> getOrderServiceListForOrder(BigInteger authorizationSK, String businessEntityID) {
        try {
            List<OrderService> orderServiceList = new ArrayList<>();
            Object result = oracleDataService.executeGet("PKG_AUTH",
                "getOrdSvc",
                "com.sandata.lab.data.model.dl.model.OrderService",
                authorizationSK,
                businessEntityID);

            if (result != null) {
                orderServiceList.addAll((List<OrderService>) result);
            }

            return orderServiceList;
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }
    
    private List<OrderService> getOrderServiceListForOrder(Connection connection, BigInteger authorizationSK, String businessEntityID) {
        try {
            return (List<OrderService>) oracleDataService.executeGet(
                connection,
                "PKG_AUTH",
                "getOrdSvc",
                "com.sandata.lab.data.model.dl.model.OrderService",
                authorizationSK,
                businessEntityID);
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }
}
