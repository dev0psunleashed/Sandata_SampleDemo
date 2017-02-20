package com.sandata.lab.rest.payer.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.contract.ContractExt;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payer.api.DataService;
import com.sandata.lab.rest.payer.model.ReturnVal;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sandata.lab.rest.payer.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @PropertyInject("payer.parent.key.name")
    private String payerSettingsParentKeyName = "MW_PAYERS";

    @PropertyInject("contract.parent.key.name")
    private String contractSettingsParentKeyName = "MW_CONTRACTS";

    public void getPayerForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            //get related payer data
            Payer payer = oracleDataService.getPayerForID(payerId, bsnEntId);
            payer = getRelatedPayerData(connection, payer);
            exchange.getIn().setBody(payer);

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
    public void getPayerByBsnEnt(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }


            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);
            String payerTypeQualifier = (String) mcl.get(3);
            boolean payerActiveIndicator = (Boolean) mcl.get(4);

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getPayerByBsnEnt(bsnEntId, page, pageSize, payerTypeQualifier, payerActiveIndicator, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
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
    public void getPayerByPatient(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String patientId = (String) exchange.getIn().getHeader("patient_id");

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            List<Payer> payers = oracleDataService.getPayerByPatient(bsnEntId, patientId);

            processRelatedPayerData(payers);

            exchange.getIn().setBody(payers);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
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
                    connectionType,
                    packageName,
                    methodName,
                    className,
                    sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    // Get the subtables that are required for the Main Model... for example, Patient would also need to get Patient Contact Details...
                    if (methodParts[3].equals("Payer")) {

                        exchange.getIn().setBody(getRelatedPayerData(connection, (Payer) result));
                    } else if (methodParts[3].equals("Contract")) {
                        exchange.getIn().setBody(getRelatedContractData((Contract) result, logger));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                    return;
                }

            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                Object[] params = new Object[mcl.size()];
                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = mcl.get(index);
                }

                result = oracleDataService.executeGet(
                    connectionType,
                    packageName,
                    methodName,
                    className,
                    params
                );
            }

            if (className.equals("com.sandata.lab.data.model.dl.model.Contract")) {
                if (result instanceof ArrayList) {
                    for (Contract contract : (ArrayList<Contract>) result) {
                        getRelatedContractData(contract, logger);
                    }
                } else if (result instanceof Contract) {
                    result = getRelatedContractData((Contract) result, logger);
                }
            }
            
            if (result == null) {
                exchange.getIn().setHeader("TOTAL_ROWS", 0);
            } else if (result instanceof ArrayList) {
                exchange.getIn().setHeader("TOTAL_ROWS", ((ArrayList)result).size());
            } else {
                exchange.getIn().setHeader("TOTAL_ROWS", 1);
            }
            
            exchange.getIn().setBody(result);
            
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
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                connectionType,
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

        Connection connection = null;

        logger.start();

        try {
            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);
            ReturnVal returnVal = new ReturnVal();
            returnVal.setValue(-999);
            returnVal = executeRecursive(connection, data, false /* UPDATE */, returnVal);
            if (returnVal.getValue() > 0) {

                connection.commit();

                //dmr--GEOR-4774: Undo GEOR-4307
                /*
                if (data instanceof Payer) {
                    exchange.getIn().setBody(returnVal.getId()); //dmr--GEOR-4307: Return the PayerID not SK
                } else {
                    exchange.getIn().setBody(returnVal.getValue());
                }*/

                exchange.getIn().setBody(returnVal.getValue());

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

        Connection connection = null;

        logger.start();

        try {
            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);
            ReturnVal returnVal = new ReturnVal();
            returnVal.setValue(-999);
            returnVal = executeRecursive(connection, data, true, returnVal);
            if (returnVal.getValue() > 0) {

                connection.commit();

                //dmr--GEOR-4774: Undo GEOR-4307
                /*if (data instanceof Payer) {
                    String payerId = returnVal.getId();
                    //dmr--Since the PayerID might be generated on the backend, then we need to get it by SK first
                    if (StringUtil.IsNullOrEmpty(payerId)) {

                        payerId = oracleDataService.getPayerIdForSk(returnVal.getValue());
                    }

                    exchange.getIn().setBody(payerId); //dmr--GEOR-4307: Return the PayerID not SK
                } else {
                    exchange.getIn().setBody(returnVal.getValue());
                }*/

                exchange.getIn().setBody(returnVal.getValue());

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

    /**
     * Insert Payer with validation
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPayer(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();


        try {
            Payer payer = exchange.getIn().getBody(Payer.class);
            if (payer == null) {
                throw new SandataRuntimeException(exchange, "Payer is null");
            }

            List<Payer> result = oracleDataService.getPayerByIDAndEffectiveRange(
                    payer.getBusinessEntityID(), payer.getPayerID(), null,
                    payer.getPayerEffectiveDate(), payer.getPayerTerminationDate());

            if (result != null && result.size() > 0) {
                throw new SandataRuntimeException(exchange,
                        String.format("Duplicated Payer with PayerID=%s and PayerEffectiveDate=%s and PayerTerminationDate=%s",
                                payer.getPayerID(), payer.getPayerEffectiveDate(), payer.getPayerTerminationDate()));
            }

            insert(exchange);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Update Payer with validation
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updatePayer(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            Payer payer = exchange.getIn().getBody(Payer.class);
            if (payer == null) {
                throw new SandataRuntimeException(exchange, "Payer is null");
            }

            List<Payer> result = oracleDataService.getPayerByIDAndEffectiveRange(
                    payer.getBusinessEntityID(), payer.getPayerID(), payer.getPayerSK().longValue(),
                    payer.getPayerEffectiveDate(), payer.getPayerTerminationDate());

            if (result != null && result.size() > 0) {
                throw new SandataRuntimeException(exchange,
                        String.format("Duplicated Payer with PayerID=%s and PayerEffectiveDate=%s and PayerTerminationDate=%s",
                                payer.getPayerID(), payer.getPayerEffectiveDate(), payer.getPayerTerminationDate()));
            }

            update(exchange);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

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

    private ReturnVal executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, ReturnVal returnVal) throws SandataRuntimeException {

        try {
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal.getValue(), "setPayerSk");

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                    connection,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
                );

                if (data instanceof Payer) {
                    returnVal.setId(((Payer) data).getPayerID());
                    returnVal.setValue(result);
                }


            } else {

                if (data instanceof Payer) {
                    returnVal.setValue(((Payer) data).getPayerSK().longValue());
                    returnVal.setId(((Payer) data).getPayerID());
                } else if (data instanceof BillingRateMatrix) {
                    returnVal.setValue(((BillingRateMatrix) data).getBillingRateMatrixSK().longValue());
                } else if (data instanceof PayrollRateMatrix) {
                    returnVal.setValue(((PayrollRateMatrix) data).getPayrollRateMatrixSK().longValue());
                } else if (data instanceof Contract) {
                    returnVal.setValue(((Contract) data).getContractSK().longValue());
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

                if (returnVal.getValue() == -999) {
                    returnVal.setValue(result);
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
                            ReturnVal insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
                            if (insertResponse.getValue() == -1) {
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
            returnVal.setValue(-1);
            return returnVal;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    // TODO: Need to get the PatientID and BSN to get the sub tables...
    private Payer getRelatedPayerData(Connection connection, Payer payer) {
        
        if (payer != null) {

            // Get the subtables that are required for the Main Model... for example, Patient would also need to get Patient Contact Details...
    
            //Eligibility
    
            //PatientPayer
    
            //BusinessEntityPayer
    
            //ScheduleEvent
    
            //PayerRate
    
            //Authorization
            
            //get related contract data
            
            List<Contract> contractList = oracleDataService.getContractByPayerId(connection, payer.getPayerID(),payer.getBusinessEntityID());
            payer.getContract().addAll(contractList);
        }

        return payer;
    }

    private void processRelatedPayerData(List<Payer> payers) {

        for (Payer payer : payers) {

            List<PatientPayer> patientPayers = payer.getPatientPayer();
            for (PatientPayer patientPayer : patientPayers) {

                List<PatientPayerLimit> patientPayerLimits = (List<PatientPayerLimit>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM PT_PAYER_LMT WHERE PT_ID = ? AND PAYER_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.PatientPayerLimit",
                    patientPayer.getPatientID(),
                    patientPayer.getPayerID(),
                    patientPayer.getBusinessEntityID());

                for (PatientPayerLimit patientPayerLimit : patientPayerLimits) {
                    patientPayer.getPatientPayerLimit().add(patientPayerLimit);
                }
            }
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void getPayerLobByBsnEnt(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerLobByBsnEnt(connection, className, payerId, bsnEntId);//, page, pageSize);

            exchange.getIn().setBody(response.getData());

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

    public void getPayerByBsnEntAndPayer(String operationName, Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerByBsnEntAndPayer(className, payerId, bsnEntId);

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPayerHdrsByBsnEnt(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Boolean payerActiveIndicator = exchange.getIn().getHeader("payer_active_indicator", Boolean.class);
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");
            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");

            Response response = oracleDataService.getPayerHdrsByBsnEnt(bsnEntId, payerActiveIndicator, sortOn, direction, page, pageSize);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
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

    public void getPayerSvcLstBsnEntPayer(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);
        Connection connection = null;
        logger.start();

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerSvcLstBsnEntPayer(connection, className, payerId, bsnEntId);

            exchange.getIn().setBody(response.getData());
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

    public void getPayerRateTypLstBsnEntPayer(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerRateTypLstBsnEntPayer(connection, className, payerId, bsnEntId);

            exchange.getIn().setBody(response.getData());
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

    public void getPayerBillingCodeLstByBsnEntPayer(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerBillingCodeLstByBsnEntPayer(connection, className, payerId, bsnEntId);

            exchange.getIn().setBody(response.getData());
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

    public void getPayerMdfrLstByBsnEntPayer(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String className = "com.sandata.lab.data.model.dl.model." + operationName.substring(operationName.lastIndexOf("_") + 1);
            String payerId = (String) mcl.get(0);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }
            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayerMdfrLstByBsnEntPayer(connection, className, payerId, bsnEntId);//, page, pageSize);

            exchange.getIn().setBody(response.getData());
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

    public void getPayerContractHeadersByPayer(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();
        try {
            if (exchange.getIn().getHeader("sequence_key") != null) {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
                int page = (Integer) mcl.get(1);
                int pageSize = (Integer) mcl.get(2);

                Response response = oracleDataService.getPayerContractHeadersByPayer(sequenceKey, page, pageSize);

                exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
                exchange.getIn().setHeader("PAGE", page);
                exchange.getIn().setHeader("PAGE_SIZE", pageSize);

                exchange.getIn().setBody(response.getData());
            } else {
                throw new SandataRuntimeException(exchange, "PayerSK (sequence_key) is required!");
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }

    }

    public void getPayerContractListsByBsnEntPayerContr(String operationName, Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String[] methodParts = operationName.split("_");
            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2].substring(0, methodParts[2].indexOf("BsnEntPayerContr"));
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];
            Object result;
            Object body = exchange.getIn().getBody();
            MessageContentsList mcl = (MessageContentsList) body;
            Object[] params = new Object[mcl.size()];
            for (int index = 0; index < mcl.size(); index++) {
                params[index] = mcl.get(index);
            }
            int page = (Integer) params[3];
            int pageSize = (Integer) params[4];
            Response response = oracleDataService.getPayerContractSubtable(
                packageName,
                methodName,
                className,
                params
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Inserts ApplicationTenantKeyConfiguration and creates
     * settings hierarchy if it doesn't exist.
     *
     * @param exchange Specified Exchange.
     */
    public void createAppTenantKeyConfForPayerOrContract(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Parameter bsn_ent_id required!");
            }

            String payerId = (String) exchange.getIn().getHeader("payer_id");
            if (payerId == null
                || payerId.isEmpty()) {
                throw new SandataRuntimeException("Parameter payer_id required!");
            }

            String contractId = (String) exchange.getIn().getHeader("contract_id");

            ApplicationTenantKeyConfiguration payerOrContractSettingsAppTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();

            // Get or create settings hierarchy.
            ApplicationTenantBusinessEntityCrosswalk appTenantBeCrossWalk = getAppTenantBeCrosswalk(bsnEntId);

            BigInteger appTenantSk = appTenantBeCrossWalk.getApplicationTenantSK();

            // Get or create PAYERS AppTenantKeyConfig for APP_TENANT_SK.
            ApplicationTenantKeyConfiguration payersParentAppTenantKeyConf = getOrCreateAppTenantKeyConfForTenantParentAndSk(
                exchange,
                appTenantSk,
                null,
                payerSettingsParentKeyName);

            // Use payer_id to find AppTenantKeyConfig for that payer.
            ApplicationTenantKeyConfiguration payerAppTenantKeyConf = getOrCreateAppTenantKeyConfForTenantParentAndSk(
                exchange,
                appTenantSk,
                payersParentAppTenantKeyConf.getApplicationTenantKeyConfigurationSK(),
                String.format("MW_%s", payerId));

            // Use contract_id to find AppTenantKeyConfig for that contract.
            if (contractId != null
                && !contractId.isEmpty()) {

                // Get CONTRACTS AppTenantKeyConfig for APP_TENANT_SK.
                ApplicationTenantKeyConfiguration contractsParentAppTenantKeyConf = getOrCreateAppTenantKeyConfForTenantParentAndSk(
                    exchange,
                    appTenantSk,
                    payerAppTenantKeyConf.getApplicationTenantKeyConfigurationSK(),
                    contractSettingsParentKeyName);

                // Use contract_id to find AppTenantKeyConfig for that payer.
                ApplicationTenantKeyConfiguration contractAppTenantKeyConf = getOrCreateAppTenantKeyConfForTenantParentAndSk(
                    exchange,
                    appTenantSk,
                    contractsParentAppTenantKeyConf.getApplicationTenantKeyConfigurationSK(),
                    String.format("MW_%s", contractId));


                // Check if settings already exist and throw exception if so.
                if (oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                    appTenantSk.longValue(),
                    contractAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue(),
                    payerOrContractSettingsAppTenantKeyConf.getKeyName()) != null) {
                    throw new SandataRuntimeException(String.format("Contract settings already exist for business entity ID %s, payer ID %s, contract ID %s, and key name %s!",
                        bsnEntId,
                        payerId,
                        contractId,
                        payerOrContractSettingsAppTenantKeyConf.getKeyName()));
                }

                // Set this data's parent SK for Contract.
                payerOrContractSettingsAppTenantKeyConf.setApplicationTenantKeyConfigurationParentSK(
                    contractAppTenantKeyConf.getApplicationTenantKeyConfigurationSK());

            } else {
                // Check if settings already exist and throw exception if so
                if (oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                    appTenantSk.longValue(),
                    payerAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue(),
                    payerOrContractSettingsAppTenantKeyConf.getKeyName()) != null) {
                    throw new SandataRuntimeException(String.format("Payer settings already exist for business entity ID %s, payer ID %s, and key name %s!",
                        bsnEntId,
                        payerId,
                        payerOrContractSettingsAppTenantKeyConf.getKeyName()));
                }

                // Set this data's parent SK for Payer.
                payerOrContractSettingsAppTenantKeyConf.setApplicationTenantKeyConfigurationParentSK(
                    payerAppTenantKeyConf.getApplicationTenantKeyConfigurationSK());
            }

            // Set this data's tenant SK.
            payerOrContractSettingsAppTenantKeyConf.setApplicationTenantSK(
                appTenantSk);

            // Set data back to body.
            exchange.getIn().setBody(payerOrContractSettingsAppTenantKeyConf);

            // Call insert.
            insert(exchange);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * There can be only one!
     *
     * @param bsnEntId Specified business entity ID.
     * @return ApplicationTenantBusinessEntityCrosswalk
     */
    private ApplicationTenantBusinessEntityCrosswalk getAppTenantBeCrosswalk(String bsnEntId) {

        // Use bsnEntId to find tenant SK.
        List<ApplicationTenantBusinessEntityCrosswalk> appTenantBeCrossWalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
            ConnectionType.METADATA,
            "PKG_APP",
            "getAppTenantBeXwalk",
            "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
            bsnEntId
        );

        // Returned List must not be null, empty, or contain more than one.
        if (appTenantBeCrossWalkList == null
            || appTenantBeCrossWalkList.isEmpty()
            || appTenantBeCrossWalkList.size() != 1) {
            throw new SandataRuntimeException(String.format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", bsnEntId));
        }

        return appTenantBeCrossWalkList.get(0);
    }

    /**
     * Retrieves or creates ApplicationTenantKeyConfiguration for specified parameters.
     *
     * @param exchange    Specified Exchanged required for creating.
     * @param appTenantSk Specified ApplicationTenant SK.
     * @param parentSk    Specified parent ApplicationTenantKeyConfiguration SK.
     * @param keyName     Specified key name.
     * @return ApplicationTenantKeyConfiguration.
     */
    private ApplicationTenantKeyConfiguration getOrCreateAppTenantKeyConfForTenantParentAndSk(Exchange exchange,
                                                                                              BigInteger appTenantSk,
                                                                                              BigInteger parentSk,
                                                                                              String keyName) {
        ApplicationTenantKeyConfiguration appTenantKeyConf;

        if (parentSk == null) {
            appTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantSkAndKeyName(
                appTenantSk.longValue(),
                keyName);
        } else {
            appTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                appTenantSk.longValue(),
                parentSk.longValue(),
                keyName);
        }

        if (appTenantKeyConf == null) {
            appTenantKeyConf = createAppTenantKeyConfForTenantParentAndSk(exchange,
                appTenantSk,
                parentSk,
                keyName);

            Long resultSk = (Long) exchange.getIn().getBody();
            appTenantKeyConf.setApplicationTenantKeyConfigurationSK(BigInteger.valueOf(resultSk));
        }

        return appTenantKeyConf;
    }

    /**
     * Instantiates and inserts an ApplicationTenantKeyConfiguration for the specified parameters.
     *
     * @param exchange    Specified Exchange.
     * @param appTenantSk Specified SK for ApplicationTenant entity.
     * @param parentSk    Specified SK for parent ApplicationTenantKeyConfiguration if one exists.
     * @param keyName     Specified key name.
     * @return ApplicationTenantKeyConfiguration.
     */
    private ApplicationTenantKeyConfiguration createAppTenantKeyConfForTenantParentAndSk(Exchange exchange,
                                                                                         BigInteger appTenantSk,
                                                                                         BigInteger parentSk,
                                                                                         String keyName) {
        ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration = new ApplicationTenantKeyConfiguration();
        Date nowDate = new Date();
        applicationTenantKeyConfiguration.setRecordCreateTimestamp(nowDate);
        applicationTenantKeyConfiguration.setRecordUpdateTimestamp(nowDate);
        applicationTenantKeyConfiguration.setApplicationTenantSK(appTenantSk);
        applicationTenantKeyConfiguration.setApplicationTenantKeyConfigurationParentSK(parentSk);
        applicationTenantKeyConfiguration.setKeyName(keyName);

        // Call insert and set SK from response.
        exchange.getIn().setBody(applicationTenantKeyConfiguration);
        insert(exchange);

        return applicationTenantKeyConfiguration;

    }

    /**
     * Update for any ApplicationTenantKeyConfiguration.
     *
     * @param exchange Specified Exchange.
     */
    public void updateAppTenantKeyConf(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            ApplicationTenantKeyConfiguration appTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();

            // Make sure data has tenant SK and set it if not.
            if (appTenantKeyConf != null
                && appTenantKeyConf.getApplicationTenantSK() == null) {
                List<ApplicationTenantKeyConfiguration> appTenantKeyConfResultList = (List<ApplicationTenantKeyConfiguration>) oracleDataService.executeGet(
                    ConnectionType.METADATA,
                    "PKG_APP",
                    "getAppTenantKeyConf",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration",
                    appTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue()
                );

                if (appTenantKeyConfResultList == null
                    || appTenantKeyConfResultList.isEmpty()) {
                    throw new SandataRuntimeException(String.format("No ApplicationTenantKeyConfiguration found for SK %s!", appTenantKeyConf.getApplicationTenantKeyConfigurationSK()));
                }

                appTenantKeyConf.setApplicationTenantSK(
                    appTenantKeyConfResultList.get(0).getApplicationTenantSK());
            }

            // Make sure data has parent SK and set it if not.
            if (appTenantKeyConf != null
                && appTenantKeyConf.getApplicationTenantKeyConfigurationParentSK() == null) {
                List<ApplicationTenantKeyConfiguration> appTenantKeyConfResultList = (List<ApplicationTenantKeyConfiguration>) oracleDataService.executeGet(
                    ConnectionType.METADATA,
                    "PKG_APP",
                    "getAppTenantKeyConf",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration",
                    appTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue()
                );

                if (appTenantKeyConfResultList == null
                    || appTenantKeyConfResultList.isEmpty()) {
                    throw new SandataRuntimeException(String.format("No ApplicationTenantKeyConfiguration found for SK %s!", appTenantKeyConf.getApplicationTenantKeyConfigurationSK()));
                }

                appTenantKeyConf.setApplicationTenantKeyConfigurationParentSK(
                    appTenantKeyConfResultList.get(0).getApplicationTenantKeyConfigurationParentSK());
            }

            exchange.getIn().setHeader("operationName",
                "PKG_APP_updateAppTenantKeyConf_ApplicationTenantKeyConfiguration");
            update(exchange);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves ApplicationTenantKeyConfiguration for specified SK.
     *
     * @param exchange Specified Exchange.
     */
    public void getAppTenantKeyConfForSk(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            ApplicationTenantKeyConfiguration appTenantKeyConf = null;

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");
            if (sequenceKey < 0) {
                throw new SandataRuntimeException("Parameter sequence_key required!");
            }

            List<ApplicationTenantKeyConfiguration> result = (List<ApplicationTenantKeyConfiguration>)
                oracleDataService.executeGet(ConnectionType.METADATA,
                    "PKG_APP",
                    "getAppTenantKeyConf",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration",
                    sequenceKey);

            if (result != null
                && !result.isEmpty()) {
                appTenantKeyConf = result.get(0);
            }

            exchange.getIn().setBody(appTenantKeyConf);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves ApplicationTenantKeyConfiguration for business entity ID,
     * payer ID, contract ID (optional), and key name.
     *
     * @param exchange Specified Exchange.
     */
    public void getAppTenantKeyConfForBePayerContAndKey(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Parameter bsn_ent_id required!");
            }

            String payerId = (String) exchange.getIn().getHeader("payer_id");
            if (payerId == null
                || payerId.isEmpty()) {
                throw new SandataRuntimeException("Parameter payer_id required!");
            }

            String contractId = (String) exchange.getIn().getHeader("contract_id");

            String keyName = (String) exchange.getIn().getHeader("key_name");
            if (keyName == null
                || keyName.isEmpty()) {
                throw new SandataRuntimeException("Parameter key_name is required!");
            }

            ApplicationTenantBusinessEntityCrosswalk appTenantBeCrossWalk = getAppTenantBeCrosswalk(bsnEntId);

            BigInteger appTenantSk = appTenantBeCrossWalk.getApplicationTenantSK();

            Long parentSk;

            // Get PAYERS AppTenantKeyConfig for APP_TENANT_SK.
            ApplicationTenantKeyConfiguration payersParentAppTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantSkAndKeyName(
                appTenantSk.longValue(),
                payerSettingsParentKeyName);

            if (payersParentAppTenantKeyConf == null) {
                exchange.getIn().setBody(null);
                return;
            }

            // Use payer_id to find AppTenantKeyConfig for that payer.
            ApplicationTenantKeyConfiguration payerAppTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                appTenantSk.longValue(),
                payersParentAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue(),
                String.format("MW_%s", payerId));

            if (payerAppTenantKeyConf == null) {
                exchange.getIn().setBody(null);
                return;
            }

            ApplicationTenantKeyConfiguration contractAppTenantKeyConf;
            // Use contract_id to find AppTenantKeyConfig for that contract.
            if (contractId != null
                && !contractId.isEmpty()) {

                // Get CONTRACTS AppTenantKeyConfig for APP_TENANT_SK.
                ApplicationTenantKeyConfiguration contractsParentAppTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                    appTenantSk.longValue(),
                    payerAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue(),
                    contractSettingsParentKeyName);

                if (contractsParentAppTenantKeyConf == null) {
                    exchange.getIn().setBody(null);
                    return;
                }

                // Use contract_id to find AppTenantKeyConfig for that payer.
                contractAppTenantKeyConf = oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(
                    appTenantSk.longValue(),
                    contractsParentAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue(),
                    String.format("MW_%s", contractId));

                if (contractAppTenantKeyConf == null) {
                    exchange.getIn().setBody(null);
                    return;
                }

                parentSk = contractAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue();
            } else {
                parentSk = payerAppTenantKeyConf.getApplicationTenantKeyConfigurationSK().longValue();
            }

            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration =
                oracleDataService.getAppTenantKeyConfForTenantParentAndKeyName(appTenantSk.longValue(),
                    parentSk,
                    keyName);

            exchange.getIn().setBody(applicationTenantKeyConfiguration);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Deletes any ApplicationTenantKeyConfiguration by specified SK.
     *
     * @param exchange Specified Exchange.
     */
    public void deleteAppTenantKeyConf(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");
            if (sequenceKey < 0) {
                throw new SandataRuntimeException("Parameter sequence_key required!");
            }

            long result = oracleDataService.execute(
                ConnectionType.METADATA,
                "PKG_APP",
                "deleteAppTenantKeyConf",
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

    private ContractExt getRelatedContractData(Contract contract, SandataLogger logger) throws Exception {

        ContractExt payerContract = new ContractExt(contract);

        Object[] params = new Object[5];
        params[0] = payerContract.getBusinessEntityID();
        params[1] = payerContract.getPayerID();
        params[2] = payerContract.getContractID();
        params[3] = 1;
        params[4] = 100;
        String javaClassPkg = "com.sandata.lab.data.model.dl.model.";
        String packageName = "PKG_AM";

        String methodName = "getContrSvcLst";
        String className = javaClassPkg + "ContractServiceList";
        Response response = oracleDataService.getPayerContractSubtable(
            packageName,
            methodName,
            className,
            params
        );

        if (response.getData() != null) {
            payerContract.getContractServiceList().addAll((List<ContractServiceList>) response.getData());
        }

        methodName = "getContrRateTypLst";
        className = javaClassPkg + "ContractRateTypeList";
        response = oracleDataService.getPayerContractSubtable(
            packageName,
            methodName,
            className,
            params
        );

        if (response.getData() != null) {
            payerContract.getContractRateTypeList().addAll((List<ContractRateTypeList>) response.getData());
        }

        methodName = "getContrMdfrLst";
        className = javaClassPkg + "ContractModifierList";
        response = oracleDataService.getPayerContractSubtable(
            packageName,
            methodName,
            className,
            params
        );

        if (response.getData() != null) {
            payerContract.getContractModifierList().addAll((List<ContractModifierList>) response.getData());
        }

        packageName = "PKG_BILLING";
        methodName = "getBillingRateMatrix";
        className = javaClassPkg + "BillingRateMatrix";
        response = oracleDataService.getPayerContractSubtable(
            packageName,
            methodName,
            className,
            params
        );

        if (response.getData() != null) {
            payerContract.getBillingRateMatrix().addAll((List<BillingRateMatrix>) response.getData());
        }

        packageName = "PKG_PAYROLL";
        methodName = "getPrRateMatrix";
        className = javaClassPkg + "PayrollRateMatrix";
        response = oracleDataService.getPayerContractSubtable(
            packageName,
            methodName,
            className,
            params
        );

        if (response.getData() != null) {
            payerContract.getPayrollRateMatrix().addAll((List<PayrollRateMatrix>) response.getData());
        }

        payerContract.setPayrollRateMatrixExchangeList(
                oracleDataService.getPrRateMatrixExchange(
                        payerContract.getBusinessEntityID(),
                        payerContract.getPayerID(),
                        payerContract.getContractID(),
                        logger));

        return payerContract;
    }
    
    public void getPayerContract(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String payerId = (String) mcl.get(0);
            if (StringUtil.IsNullOrEmpty(payerId)) {
                throw new SandataRuntimeException("Parameter payer_id required!");
            }
            
            String bsnEntId = (String) mcl.get(1);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Parameter bsn_ent_id required!");
            }
            
            connection = oracleDataService.getOracleConnection();

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String sortByColumn = "CONTR_DESC";
            if (!StringUtil.IsNullOrEmpty(sortOn)) {
                switch (sortOn.toUpperCase()) {
                    case "CONTRACTDESCRIPTION":
                        sortByColumn = "CONTR_DESC";
                        break;
                    case "CONTRACTEFFECTIVEDATE":
                        sortByColumn = "CONTR_EFF_DATE";
                        break;
                    case "CONTRACTINVOICEFORMATTYPENAME":
                        sortByColumn = "NVL(CONTR_INV_FMT_TYP_NAME, 0)";
                        break;
                    case "CONTRACTACTIVEINDICATOR":
                        sortByColumn = "CONTR_ACTIVE_IND";
                        break;
                }
            }

            if(StringUtil.IsNullOrEmpty(direction)){
                direction = "ASC";
            }

            String orderBy = String.format("ORDER BY %s %s", sortByColumn, direction);

            List<Contract> contractList = (List<Contract>) oracleDataService.getEntitiesForId(connection, 
                    "SELECT * FROM CONTR WHERE PAYER_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " + orderBy,
                    "com.sandata.lab.data.model.dl.model.Contract", 
                    payerId, bsnEntId);
            
            List<ContractExt> contractExtList = new ArrayList<>();
            
            for (Contract contract : contractList) {
                ContractExt contractExt = new ContractExt(contract);
                
                List<PayrollRateMatrix> prRateMatrixList = (List<PayrollRateMatrix>) oracleDataService.getEntitiesForId(connection, 
                        "SELECT * FROM PR_RATE_MATRIX WHERE PAYER_ID=? AND BE_ID=? AND CONTR_ID=? " +
                                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ", 
                        "com.sandata.lab.data.model.dl.model.PayrollRateMatrix", 
                        payerId, bsnEntId, contract.getContractID());
                contractExt.getPayrollRateMatrix().addAll(prRateMatrixList);
                
                List<BillingRateMatrix> billingRateMatrixList = (List<BillingRateMatrix>) oracleDataService.getEntitiesForId(connection, 
                        "SELECT * FROM BILLING_RATE_MATRIX WHERE PAYER_ID=? AND BE_ID=? AND CONTR_ID=? " +
                                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ", 
                        "com.sandata.lab.data.model.dl.model.BillingRateMatrix", 
                        payerId, bsnEntId, contract.getContractID());
                contractExt.getBillingRateMatrix().addAll(billingRateMatrixList);
                
                List<PayrollRateMatrixExchange> payrollRateMatrixExchangeList = oracleDataService.getPrRateMatrixExchange(
                        contractExt.getBusinessEntityID(),
                        contractExt.getPayerID(),
                        contractExt.getContractID(),
                        logger);
                contractExt.setPayrollRateMatrixExchangeList(payrollRateMatrixExchangeList);
                
                contractExtList.add(contractExt);
            }
            
            exchange.getIn().setBody(contractExtList);
            exchange.getIn().setHeader("TOTAL_ROWS", contractList.size());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }
}

