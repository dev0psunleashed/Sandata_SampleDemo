package com.sandata.lab.rest.bsn_ent.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.BusinessEntityRateExchange;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.bsn_ent.api.DataService;
import com.sandata.lab.rest.bsn_ent.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.bsn_ent.utils.log.OracleDataLogger.CreateLogger;
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

                    if (methodParts[3].equalsIgnoreCase("BusinessEntity")) {
                        exchange.getIn().setBody(getRelatedBusinessEntityTables((BusinessEntity) result));
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

                exchange.getIn().setBody(result);
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Returns specified BusinessEntity with sub-tables BusinessEntityContactDetail and BusinessEntityIDCrosswalk.
     *
     * @param businessEntity Specified BusinessEntity.
     * @return BusinessEntity.
     */
    private BusinessEntity getRelatedBusinessEntityTables(BusinessEntity businessEntity) {

        // Get List of BusinessEntityContactDetail for BusinessEntity and add.
        List<BusinessEntityContactDetail> businessEntityContactDetailList = (List<BusinessEntityContactDetail>) oracleDataService.executeGet(
            "PKG_BSN",
            "getBeContDet",
            "com.sandata.lab.data.model.dl.model.BusinessEntityContactDetail",
            businessEntity.getBusinessEntityID());

        businessEntity.getBusinessEntityContactDetail().addAll(businessEntityContactDetailList);

        // Get List of BusinessEntityIDCrossWalk for BusinessEntity and add.
        List<BusinessEntityIDCrosswalk> businessEntityIDCrosswalkList = (List<BusinessEntityIDCrosswalk>) oracleDataService.executeGet(
            "PKG_BSN",
            "getBeIdXwalk",
            "com.sandata.lab.data.model.dl.model.BusinessEntityIDCrosswalk",
            businessEntity.getBusinessEntityID()
        );

        businessEntity.getBusinessEntityIDCrosswalk().addAll(businessEntityIDCrosswalkList);

        return businessEntity;
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
            long returnValue = executeRecursive(connection, data, false /* UPDATE */);
            if (returnValue > 0) {
                connection.commit();
                exchange.getIn().setBody(returnValue);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            if(errMsg.contains("ORA-00054: resource busy and acquire with NOWAIT")){
                errMsg = "The record is being updated by another process";
            }

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
            long returnValue = executeRecursive(connection, data, true);
            if (returnValue > 0) {
                connection.commit();
                exchange.getIn().setBody(returnValue);
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

            oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert)
        throws SandataRuntimeException {

        try {
            // GEOR-6612
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                    connection,
                    ConnectionType.COREDATA,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
                );
            } else {
                // UPDATE
                result = oracleDataService.execute(
                    connection,
                    ConnectionType.COREDATA,
                    oracleMetadata.packageName(),
                    oracleMetadata.updateMethod(),
                    jpubType
                );
            }

            if (result > 0) {

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, object, bShouldInsert);
                            if (insertResponse == -1) {
                                throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                    object.getClass().getName()));
                            }
                        }
                    }
                }

                // SUCCESS
                return result;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void getBsnEntLocation(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntParentID = (String) mcl.get(0);
            if (bsnEntParentID == null) {
                throw new SandataRuntimeException(exchange, "BusinessEntityParentSK is required!");
            }

            String locationName = (String) mcl.get(1);

            Response response = oracleDataService.getBsnEntLocation(bsnEntParentID, locationName);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBusinessEntityRelationshipForPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Long bsnEntLevelSk = (Long) mcl.get(1);
            if (bsnEntLevelSk == null || bsnEntLevelSk <= 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityLevelSK (bsn_lvl_sk) is required!");
            }


            exchange.getIn().setBody(oracleDataService.getBusinessEntityRelationshipForPK(bsnEntId, bsnEntLevelSk));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBusinessEntityForPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            //dmr--GEOR-4367: Fixed method..
            String[] params = new String[1];
            params[0] = bsnEntId;

            Object result = oracleDataService.executeGet(
                packageName,
                methodName,
                className,
                params
            );

            ArrayList<Object> resultList = (ArrayList<Object>) result;

            if (resultList != null
                && !resultList.isEmpty() && resultList.get(0) instanceof BusinessEntity) {
                exchange.getIn().setBody(getRelatedBusinessEntityTables((BusinessEntity) resultList.get(0)));
            }
            else {
                exchange.getIn().setBody( (ArrayList)resultList);
            }

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBeCompRelDetPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {
            Long compRelSk = (Long)exchange.getIn().getHeader("comp_rel_sk");
            if (compRelSk == null || compRelSk <= 0) {
                throw new SandataRuntimeException("ComplianceRelationshipSK (comp_rel_sk) is required!");
            }

            String bsnEntId = (String)exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getBeCompRelDetPK(compRelSk, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBeRateExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String serviceNameString = (String) mcl.get(1);
            if (IsNullOrEmpty(serviceNameString)) {
                throw new SandataRuntimeException("ServiceName (service_name) is required!");
            }

            // Verify ServiceName Exists
            ServiceName serviceName;
            try {
                serviceName = ServiceName.fromValue(serviceNameString.toUpperCase());
            }
            catch (IllegalArgumentException iae) {
                logger.error(iae.getMessage());
                throw new SandataRuntimeException(format("[ServiceName=%s]: Is not a valid service!", serviceNameString), iae);
            }

            String rateTypeNameString = (String) mcl.get(2);
            if (IsNullOrEmpty(rateTypeNameString)) {
                throw new SandataRuntimeException("RateType (rate_type) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getBeRateExchange(bsnEntId, serviceName, rateTypeNameString, logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void deleteBeRateExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String serviceNameString = (String) mcl.get(1);
            if (IsNullOrEmpty(serviceNameString)) {
                throw new SandataRuntimeException("ServiceName (service_name) is required!");
            }

            // Verify ServiceName Exists
            ServiceName serviceName;
            try {
                serviceName = ServiceName.fromValue(serviceNameString.toUpperCase());
            }
            catch (IllegalArgumentException iae) {
                logger.error(iae.getMessage());
                throw new SandataRuntimeException(format("[ServiceName=%s]: Is not a valid service!", serviceNameString), iae);
            }

            String rateTypeNameString = (String) mcl.get(2);
            if (IsNullOrEmpty(rateTypeNameString)) {
                throw new SandataRuntimeException("RateType (rate_type) is required!");
            }

            exchange.getIn().setBody(oracleDataService.deleteBeRateExchange(bsnEntId, serviceName, rateTypeNameString));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void updateBeRateExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.updateBeRateExchange(connection, exchange.getIn().getBody(BusinessEntityRateExchange.class), logger));

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
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void insertBeRateExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.insertBeRateExchange(connection, exchange.getIn().getBody(BusinessEntityRateExchange.class), logger));

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
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getBeRateForBsnEntID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getBeRateForBsnEntID(bsnEntId, logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

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
