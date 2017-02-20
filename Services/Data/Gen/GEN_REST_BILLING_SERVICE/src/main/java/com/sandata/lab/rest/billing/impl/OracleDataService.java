package com.sandata.lab.rest.billing.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindResult;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindType;
import com.sandata.lab.data.model.icdcodes.ICDCode;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.logger.api.LoggerService;
import com.sandata.lab.rest.billing.api.OracleService;
import com.sandata.lab.rest.billing.model.BillingDetailExt;
import com.sandata.lab.rest.billing.model.BillingDetailHeaderExt;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {
    private static final SimpleDateFormat ORACLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private ConnectionPoolDataService connectionPoolDataService;

    private LoggerService loggerService;

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.COREDATA);
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }
    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }
    @Override
    public Object executeGet(String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private ICDCode mapICDCode(final ResultSet resultSet) throws SQLException {

        ICDCode icdCode = new ICDCode();

        icdCode.setIcdDiagnosisCodeSk(BigInteger.valueOf(resultSet.getBigDecimal("ICD_DX_LKUP_SK").longValue()));
        icdCode.setIcdDiagnosisCode(resultSet.getString("ICD_DX_CODE"));
        icdCode.setIcdDiagnosisCodeRevision(resultSet.getString("ICD_DX_CODE_REVISION"));

        Timestamp createTmstp = resultSet.getTimestamp("REC_CREATE_TMSTP");
        if (createTmstp != null) {
            icdCode.setRecordCreateTimestamp(new Date(createTmstp.getTime()));
        }

        Timestamp updateTmstp = resultSet.getTimestamp("REC_UPDATE_TMSTP");
        if (updateTmstp != null) {
            icdCode.setRecordUpdateTimestamp(new Date(updateTmstp.getTime()));
        }

        BigDecimal currentVersionId = resultSet.getBigDecimal("CHANGE_VERSION_ID");
        if (currentVersionId != null) {
            icdCode.setChangeVersionID(BigInteger.valueOf(currentVersionId.longValue()));
        }

        icdCode.setIcdDiagnosisCodeShortDescription(resultSet.getString("ICD_DX_CODE_SHORT_DESC"));
        icdCode.setIcdDiagnosisCodeLongDescription(resultSet.getString("ICD_DX_CODE_LONG_DESC"));

        Timestamp effTmstp = resultSet.getTimestamp("ICD_DX_CODE_EFF_DATE");
        if (effTmstp != null) {
            icdCode.setIcdDiagnosisCodeEffectiveDate(new Date(effTmstp.getTime()));
        }

        Timestamp termTmstp = resultSet.getTimestamp("ICD_DX_CODE_TERM_DATE");
        if (termTmstp != null) {
            icdCode.setIcdDiagnosisCodeTerminationDate(new Date(termTmstp.getTime()));
        }

        return icdCode;
    }

    public Response getPayerContracts(final String businessEntityID) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM CONTR WHERE BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            connection.commit();

            Response response = new Response();
            List<Contract> payerContracts = (List<Contract>)
                                    new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Contract");

            response.setData(payerContracts);
            return response;
        }
        catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private Visit getVisits(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<Visit> visits = (List<Visit>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Visit");

            if (visits.size() > 0) {

                Visit visit = visits.get(0);
                List<VisitException> visitExceptions = getVisitExceptions(connection, visitSk);

                if (visitExceptions != null && visitExceptions.size() > 0) {
                    visit.getVisitException().addAll(visitExceptions);
                }

                List<VisitService> serviceVisits = getServiceVisits(connection, visitSk);

                if (serviceVisits != null && serviceVisits.size() > 0) {
                    visit.getVisitService().addAll(serviceVisits);
                }

                List<VisitTaskList> visitTaskList = getVisitTaskList(connection, visitSk);

                if (visitTaskList != null && visitTaskList.size() > 0) {

                    List<VisitTaskListExt> visitTaskListExtList = new ArrayList<>(visitTaskList.size());
                    for (VisitTaskList visitTaskLst : visitTaskList) {

                        VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTaskLst);
                        visitTaskListExtList.add(visitTaskListExt);
                    }

                    visit.getVisitTaskList().addAll(visitTaskListExtList);
                }

                return visit;
            }

            return null;
        }
        catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }

    }

    private List<VisitTaskList> getVisitTaskList(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_TASK_LST WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitTaskList>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitTaskList");
        }
        catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<VisitService> getServiceVisits(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_SVC WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitService>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitService");
        }
        catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<VisitException> getVisitExceptions(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_EXCP WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitException>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitException");
        }
        catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    
    public List<BillingRate> getBillingRatePK(String patientId, String bsnEntId) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
    
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
    
            String sql = "SELECT * FROM BILLING_RATE WHERE PT_ID = ? AND BE_ID = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);
    
            resultSet = preparedStatement.executeQuery();
    
            List<BillingRate> resultList =
                    (List<BillingRate>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BillingRate");
    
            return resultList;
    
        } catch (Exception e) {
    
            // Rollback
            if (connection != null) {
    
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
    
        } finally {
    
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response findBilling(
            String bsnEntId,
            String payerName,
            String patientId,
            String contract,
            Date fromDateTime,
            Date toDateTime,
            String invoiceNum,
            String patientFirstName,
            String patientLastName,
            String location,
            String lineOfBusiness,
            String submissionStatus,
            String status,
            String editErrorReason,
            String sortOn,
            String direction,
            int page,
            int pageSize,
            InvoiceFindType invoiceFindType) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = orderByColumn(sortOn, invoiceFindType);

            if (!StringUtil.IsNullOrEmpty(status)) {
                return findInvoicesForStatus(
                        connection,
                        bsnEntId,
                        payerName,
                        patientId,
                        contract,
                        fromDateTime,
                        toDateTime,
                        invoiceNum,
                        patientFirstName,
                        patientLastName,
                        location,
                        lineOfBusiness,
                        submissionStatus,
                        status,
                        orderByColumn,
                        direction,
                        page,
                        pageSize
                );
            }

            if (invoiceFindType == InvoiceFindType.DELETED) {
                return findDeletedInvoices(
                        connection,
                        bsnEntId,
                        payerName,
                        patientId,
                        contract,
                        fromDateTime,
                        toDateTime,
                        invoiceNum,
                        patientFirstName,
                        patientLastName,
                        location,
                        lineOfBusiness,
                        submissionStatus,
                        orderByColumn,
                        direction,
                        page,
                        pageSize
                );
            }

            if (invoiceFindType == InvoiceFindType.UNBILLABLE) {
                return findUnBillableInvoices(
                        connection,
                        bsnEntId,
                        payerName,
                        patientId,
                        contract,
                        fromDateTime,
                        toDateTime,
                        invoiceNum,
                        patientFirstName,
                        patientLastName,
                        location,
                        lineOfBusiness,
                        submissionStatus,
                        editErrorReason,
                        orderByColumn,
                        direction,
                        page,
                        pageSize
                );
            }

            if (invoiceFindType == InvoiceFindType.BILLABLE) {
                return findBillableInvoices(
                        connection,
                        bsnEntId,
                        payerName,
                        patientId,
                        contract,
                        fromDateTime,
                        toDateTime,
                        invoiceNum,
                        patientFirstName,
                        patientLastName,
                        location,
                        lineOfBusiness,
                        submissionStatus,
                        orderByColumn,
                        direction,
                        page,
                        pageSize
                );
            }

            throw new SandataRuntimeException("UNHANDLED InvoiceFindType!");

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private Response findDeletedInvoices(
            Connection connection,
            String bsnEntId,
            String payerName,
            String patientId,
            String contract,
            Date fromDateTime,
            Date toDateTime,
            String invoiceNum,
            String patientFirstName,
            String patientLastName,
            String location,
            String lineOfBusiness,
            String submissionStatus,
            String orderByColumn,
            String direction,
            int page,
            int pageSize) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            StringBuilder filter = new StringBuilder();
            List<Object> params = new ArrayList<>();

            String fromDateTimeString = ORACLE_DATE_TIME_FORMAT.format(fromDateTime);
            params.add(fromDateTimeString);

            String toDateTimeString = ORACLE_DATE_TIME_FORMAT.format(toDateTime);
            params.add(toDateTimeString);

            params.add(bsnEntId);

            String filterByLocationColumn = "";
            String filterByLocationJoin = "";
            if (!StringUtil.IsNullOrEmpty(location)) {
                filterByLocationColumn = ",T6.BE_NAME";

                filterByLocationJoin = " LEFT JOIN (SELECT BE_ID,BE_NAME " +
                        "            FROM BE " +
                        "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                        "            ON T1.BE_LOC_ID = T6.BE_ID";

                filter.append(" AND UPPER(BE_NAME) = ?");
                params.add(location.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(payerName)) {
                filter.append(" AND UPPER(PAYER_NAME) LIKE ?");
                params.add("%" + payerName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                filter.append(" AND T5.PT_ID = ?");
                params.add(patientId);
            }

            if (!StringUtil.IsNullOrEmpty(contract)) {
                filter.append(" AND UPPER(CONTR_DESC) LIKE ?");
                params.add("%" + contract.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(invoiceNum)) {
                filter.append(" AND INV_NUM = ?");
                params.add(invoiceNum);
            }

            if (!StringUtil.IsNullOrEmpty(patientFirstName)) {
                filter.append(" AND UPPER(PT_FIRST_NAME) LIKE ?");
                params.add("%" + patientFirstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientLastName)) {
                filter.append(" AND UPPER(PT_LAST_NAME) LIKE ?");
                params.add("%" + patientLastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(lineOfBusiness)) {
                filter.append(" AND UPPER(BE_LOB) = ?");
                params.add(lineOfBusiness.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(submissionStatus)) {

                try {
                    filter.append(" AND UPPER(BILLING_SUBM_TYP_NAME) = ?");
                    InvoiceSubmissionTypeName invoiceSubmissionTypeName = InvoiceSubmissionTypeName.fromValue(submissionStatus);
                    params.add(invoiceSubmissionTypeName.name());
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("findDeletedInvoices: InvoiceSubmissionTypeName [%s] is NOT valid!", submissionStatus));
                }
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT T1.BE_ID,T1.BE_LOC_ID,T1.BILLING_SK,T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE, " +
                            "          BIX.INV_NUM,T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM, " +
                            "          T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,T1.BILLING_FMT_TYP_NAME, " +
                            "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID " +
                            " " +
                            "            %s " +
                            " " +
                            "        FROM BILLING T1 " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID " +
                            "            FROM BILLING_INV_XWALK " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX " +
                            "          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID " +
                            "            AND T1.PT_ID = BIX.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BE_LOB " +
                            "            FROM BE_LOB_LKUP " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                            "            ON T1.BE_LOB_ID = T2.BE_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "            FROM PAYER " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "            FROM CONTR " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                            " " +
                            "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME " +
                            "            FROM PT " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                            " " +
                            "            %s " +
                            " " +
                            "          WHERE BILLING_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            " " +
                            "            AND EXISTS (SELECT 1 FROM BILLING_HIST WHERE BILLING_HIST.BILLING_SK = T1.BILLING_SK AND ACTION_CODE = 'D') " +
                            " " +
                            "            AND T1.BE_ID = ? " +
                            " " +
                            "            %s " +
                            " " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filterByLocationColumn,
                    filterByLocationJoin,
                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            return findInvoiceResponse(connection, preparedStatement, location, orderByColumn, direction, page, pageSize);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private Response findUnBillableInvoices(
            Connection connection,
            String bsnEntId,
            String payerName,
            String patientId,
            String contract,
            Date fromDateTime,
            Date toDateTime,
            String invoiceNum,
            String patientFirstName,
            String patientLastName,
            String location,
            String lineOfBusiness,
            String submissionStatus,
            String editErrorReason,
            String orderByColumn,
            String direction,
            int page,
            int pageSize) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            StringBuilder filter = new StringBuilder();
            List<Object> params = new ArrayList<>();

            String fromDateTimeString = ORACLE_DATE_TIME_FORMAT.format(fromDateTime);
            params.add(fromDateTimeString);

            String toDateTimeString = ORACLE_DATE_TIME_FORMAT.format(toDateTime);
            params.add(toDateTimeString);

            params.add(bsnEntId);

            String filterByLocationColumn = "";
            String filterByLocationJoin = "";
            if (!StringUtil.IsNullOrEmpty(location)) {
                filterByLocationColumn = ",T6.BE_NAME";

                filterByLocationJoin = " LEFT JOIN (SELECT BE_ID,BE_NAME " +
                        "            FROM BE " +
                        "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                        "            ON T1.BE_LOC_ID = T6.BE_ID";

                filter.append(" AND UPPER(BE_NAME) = ?");
                params.add(location.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(payerName)) {
                filter.append(" AND UPPER(PAYER_NAME) LIKE ?");
                params.add("%" + payerName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                filter.append(" AND T5.PT_ID = ?");
                params.add(patientId);
            }

            if (!StringUtil.IsNullOrEmpty(contract)) {
                filter.append(" AND UPPER(CONTR_DESC) LIKE ?");
                params.add("%" + contract.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(invoiceNum)) {
                filter.append(" AND INV_NUM = ?");
                params.add(invoiceNum);
            }

            if (!StringUtil.IsNullOrEmpty(patientFirstName)) {
                filter.append(" AND UPPER(PT_FIRST_NAME) LIKE ?");
                params.add("%" + patientFirstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientLastName)) {
                filter.append(" AND UPPER(PT_LAST_NAME) LIKE ?");
                params.add("%" + patientLastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(lineOfBusiness)) {
                filter.append(" AND UPPER(BE_LOB) = ?");
                params.add(lineOfBusiness.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(submissionStatus)) {

                try {
                    filter.append(" AND UPPER(BILLING_SUBM_TYP_NAME) = ?");
                    InvoiceSubmissionTypeName invoiceSubmissionTypeName = InvoiceSubmissionTypeName.fromValue(submissionStatus);
                    params.add(invoiceSubmissionTypeName.name());
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("findUnBillableInvoices: InvoiceSubmissionTypeName [%s] is NOT valid!", submissionStatus));
                }
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT T1.BE_ID,T1.BE_LOC_ID,T1.BILLING_SK,T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE, " +
                            "          BIX.INV_NUM,T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM, " +
                            "          T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,T1.BILLING_FMT_TYP_NAME, " +
                            "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T1.BILLING_STATUS_NAME " +
                            " " +
                            "            %s " +
                            " " +
                            "        FROM BILLING T1 " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID " +
                            "            FROM BILLING_INV_XWALK " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX " +
                            "          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID " +
                            "            AND T1.PT_ID = BIX.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BE_LOB " +
                            "            FROM BE_LOB_LKUP " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                            "            ON T1.BE_LOB_ID = T2.BE_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "            FROM PAYER " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "            FROM CONTR " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                            " " +
                            "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME " +
                            "            FROM PT " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                            " " +
                            "            %s " +
                            " " +
                            "          WHERE BILLING_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            " " +
                            "            AND NOT EXISTS (SELECT 1 FROM BILLING_HIST WHERE BILLING_HIST.BILLING_SK = T1.BILLING_SK AND ACTION_CODE = 'D') " +
                            " " +
                            "            AND ((BILLING_MANUAL_OVERRIDE_IND IS NULL OR BILLING_MANUAL_OVERRIDE_IND = 0) AND " +
                            "                    EXISTS ( " +
                            "                      SELECT 1 FROM BILLING_DET_EXCP " +
                            "                      INNER JOIN BILLING_DET " +
                            "                      ON BILLING_DET_EXCP.BILLING_DET_SK = BILLING_DET.BILLING_DET_SK " +
                            "                      WHERE BILLING_DET.BE_ID = T1.BE_ID AND BILLING_DET.PT_ID = T1.PT_ID " +
                            "                          AND BILLING_DET.PAYER_ID = T1.PAYER_ID AND BILLING_DET.CONTR_ID = T1.CONTR_ID " +
                            "                          AND (TO_CHAR(BILLING_DET_EXCP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BILLING_DET_EXCP.CURR_REC_IND = 1) " +
                            "                    )) " +
                            " " +
                            "            OR (BILLING_TOTAL_AMT IS NULL OR BILLING_TOTAL_AMT <= 0) " +
                            " " +
                            "            AND T1.BE_ID = ? " +
                            " " +
                            "            %s " +
                            " " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filterByLocationColumn,
                    filterByLocationJoin,
                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            return findInvoiceResponse(connection, preparedStatement, location, orderByColumn, direction, page, pageSize);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private Response findBillableInvoices(
            Connection connection,
            String bsnEntId,
            String payerName,
            String patientId,
            String contract,
            Date fromDateTime,
            Date toDateTime,
            String invoiceNum,
            String patientFirstName,
            String patientLastName,
            String location,
            String lineOfBusiness,
            String submissionStatus,
            String orderByColumn,
            String direction,
            int page,
            int pageSize) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            StringBuilder filter = new StringBuilder();
            List<Object> params = new ArrayList<>();

            String fromDateTimeString = ORACLE_DATE_TIME_FORMAT.format(fromDateTime);
            String toDateTimeString = ORACLE_DATE_TIME_FORMAT.format(toDateTime);

            // BILLING_START_DATE
            params.add(fromDateTimeString);
            params.add(toDateTimeString);

            // BILLING_END_DATE
            params.add(fromDateTimeString);
            params.add(toDateTimeString);

            params.add(bsnEntId);

            String filterByLocationColumn = "";
            String filterByLocationJoin = "";
            if (!StringUtil.IsNullOrEmpty(location)) {
                filterByLocationColumn = ",T6.BE_NAME";

                filterByLocationJoin = " LEFT JOIN (SELECT BE_ID,BE_NAME " +
                        "            FROM BE " +
                        "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                        "            ON T1.BE_LOC_ID = T6.BE_ID";

                filter.append(" AND UPPER(BE_NAME) = ?");
                params.add(location.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(payerName)) {
                filter.append(" AND UPPER(PAYER_NAME) LIKE ?");
                params.add("%" + payerName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                filter.append(" AND T5.PT_ID = ?");
                params.add(patientId);
            }

            if (!StringUtil.IsNullOrEmpty(contract)) {
                filter.append(" AND UPPER(CONTR_DESC) LIKE ?");
                params.add("%" + contract.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(invoiceNum)) {
                filter.append(" AND INV_NUM = ?");
                params.add(invoiceNum);
            }

            if (!StringUtil.IsNullOrEmpty(patientFirstName)) {
                filter.append(" AND UPPER(PT_FIRST_NAME) LIKE ?");
                params.add("%" + patientFirstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientLastName)) {
                filter.append(" AND UPPER(PT_LAST_NAME) LIKE ?");
                params.add("%" + patientLastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(submissionStatus)) {

                try {
                    filter.append(" AND UPPER(BILLING_SUBM_TYP_NAME) = ?");
                    InvoiceSubmissionTypeName invoiceSubmissionTypeName = InvoiceSubmissionTypeName.fromValue(submissionStatus);
                    params.add(invoiceSubmissionTypeName.name());
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("findBillableInvoices: InvoiceSubmissionTypeName [%s] is NOT valid!", submissionStatus));
                }
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT T1.BE_ID,T1.BE_LOC_ID,T1.BILLING_SK,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE, " +
                            "          BIX.INV_NUM,T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM,T6.BE_NAME, " +
                            "          T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,T1.BILLING_FMT_TYP_NAME, " +
                            "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T1.BILLING_STATUS_NAME " +
                            " " +
                            "          %s " +
                            " " +
                            "        FROM BILLING T1 " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID " +
                            "            FROM BILLING_INV_XWALK " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX " +
                            "          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID " +
                            "            AND T1.PT_ID = BIX.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE, " +
                            "                        PAYER_ID,CONTR_ID " +
                            "            FROM BILLING_RATE_MATRIX " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                            "            ON T1.BE_ID = T2.BE_ID " +
                            "              AND T1.BILLING_START_DATE BETWEEN T2.BILLING_RATE_MATRIX_EFF_DATE AND T2.BILLING_RATE_MATRIX_TERM_DATE " +
                            "              AND T1.PAYER_ID = T2.PAYER_ID AND T1.CONTR_ID = T2.CONTR_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "            FROM PAYER " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "            FROM CONTR " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                            " " +
                            "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME " +
                            "            FROM PT " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                            " " +
                            "            %s " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BE_NAME " +
                            "            FROM BE " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                            "            ON T1.BE_LOC_ID = T6.BE_ID " +
                            " " +
                            "          WHERE BILLING_START_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            "                  AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            "              AND " +
                            "                BILLING_END_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            "                  AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            " " +
                            "            AND NOT EXISTS (SELECT 1 FROM BILLING_HIST WHERE BILLING_HIST.BILLING_SK = T1.BILLING_SK AND ACTION_CODE = 'D') " +
                            " " +
                            "            AND (BILLING_MANUAL_OVERRIDE_IND = 1 OR " +
                            "                    NOT EXISTS ( " +
                            "                      SELECT 1 FROM BILLING_DET_EXCP " +
                            "                      INNER JOIN BILLING_DET " +
                            "                      ON BILLING_DET_EXCP.BILLING_DET_SK = BILLING_DET.BILLING_DET_SK " +
                            "                      WHERE BILLING_DET.BE_ID = T1.BE_ID AND BILLING_DET.PT_ID = T1.PT_ID " +
                            "                          AND BILLING_DET.PAYER_ID = T1.PAYER_ID AND BILLING_DET.CONTR_ID = T1.CONTR_ID " +
                            "                          AND (TO_CHAR(BILLING_DET_EXCP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BILLING_DET_EXCP.CURR_REC_IND = 1) " +
                            "                    )) " +
                            " " +
                            "            AND (BILLING_TOTAL_AMT IS NOT NULL AND BILLING_TOTAL_AMT > 0) " +
                            " " +
                            "            AND T1.BE_ID = ? " +
                            " " +
                            "            AND UPPER(T1.BILLING_STATUS_NAME) = 'PENDING' " +
                            " " +
                            "            %s " +
                            " " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filterByLocationColumn,
                    filterByLocationJoin,
                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            return findInvoiceResponse(connection, preparedStatement, location, orderByColumn, direction, page, pageSize);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private Response findInvoicesForStatus(
            Connection connection,
            String bsnEntId,
            String payerName,
            String patientId,
            String contract,
            Date fromDateTime,
            Date toDateTime,
            String invoiceNum,
            String patientFirstName,
            String patientLastName,
            String location,
            String lineOfBusiness,
            String submissionStatus,
            String status,
            String orderByColumn,
            String direction,
            int page,
            int pageSize) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            StringBuilder filter = new StringBuilder();
            List<Object> params = new ArrayList<>();

            String fromDateTimeString = ORACLE_DATE_TIME_FORMAT.format(fromDateTime);
            params.add(fromDateTimeString);

            String toDateTimeString = ORACLE_DATE_TIME_FORMAT.format(toDateTime);
            params.add(toDateTimeString);

            params.add(bsnEntId);

            String filterByLocationColumn = "";
            String filterByLocationJoin = "";
            if (!StringUtil.IsNullOrEmpty(location)) {
                filterByLocationColumn = ",T6.BE_NAME";

                filterByLocationJoin = " LEFT JOIN (SELECT BE_ID,BE_NAME " +
                        "            FROM BE " +
                        "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                        "            ON T1.BE_LOC_ID = T6.BE_ID";

                filter.append(" AND UPPER(BE_NAME) = ?");
                params.add(location.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(payerName)) {
                filter.append(" AND UPPER(PAYER_NAME) LIKE ?");
                params.add("%" + payerName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                filter.append(" AND T5.PT_ID = ?");
                params.add(patientId);
            }

            if (!StringUtil.IsNullOrEmpty(contract)) {
                filter.append(" AND UPPER(CONTR_DESC) LIKE ?");
                params.add("%" + contract.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(invoiceNum)) {
                filter.append(" AND INV_NUM = ?");
                params.add(invoiceNum);
            }

            if (!StringUtil.IsNullOrEmpty(patientFirstName)) {
                filter.append(" AND UPPER(PT_FIRST_NAME) LIKE ?");
                params.add("%" + patientFirstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientLastName)) {
                filter.append(" AND UPPER(PT_LAST_NAME) LIKE ?");
                params.add("%" + patientLastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(lineOfBusiness)) {
                filter.append(" AND UPPER(BE_LOB) = ?");
                params.add(lineOfBusiness.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(submissionStatus)) {

                try {
                    filter.append(" AND UPPER(BILLING_SUBM_TYP_NAME) = ?");
                    InvoiceSubmissionTypeName invoiceSubmissionTypeName = InvoiceSubmissionTypeName.fromValue(submissionStatus);
                    params.add(invoiceSubmissionTypeName.name());
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("findDeletedInvoices: InvoiceSubmissionTypeName [%s] is NOT valid!", submissionStatus));
                }
            }

            if (!StringUtil.IsNullOrEmpty(status)) {
                filter.append(" AND UPPER(T1.BILLING_STATUS_NAME) = ?");
                params.add(status.toUpperCase());
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT T1.BE_ID,T1.BE_LOC_ID,T1.BILLING_SK,T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T1.BILLING_START_DATE,T1.BILLING_END_DATE, " +
                            "          BIX.INV_NUM,T1.BILLING_DATE,T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T1.PT_INS_ID_NUM, " +
                            "          T1.BILLING_TOTAL_AMT,T1.BILLING_SUBM_TYP_NAME,T1.BILLING_FMT_TYP_NAME, " +
                            "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T1.BILLING_STATUS_NAME " +
                            " " +
                            "            %s " +
                            " " +
                            "        FROM BILLING T1 " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID " +
                            "            FROM BILLING_INV_XWALK " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX " +
                            "          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID " +
                            "            AND T1.PT_ID = BIX.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BE_LOB " +
                            "            FROM BE_LOB_LKUP " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                            "            ON T1.BE_LOB_ID = T2.BE_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "            FROM PAYER " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "            FROM CONTR " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                            " " +
                            "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME " +
                            "            FROM PT " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                            " " +
                            "            %s " +
                            " " +
                            "          WHERE BILLING_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                            " " +
                            "            AND T1.BE_ID = ? " +
                            " " +
                            "            %s " +
                            " " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filterByLocationColumn,
                    filterByLocationJoin,
                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            return findInvoiceResponse(connection, preparedStatement, location, orderByColumn, direction, page, pageSize);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private Response findInvoiceResponse(
                    Connection connection,
                    PreparedStatement preparedStatement,
                    String location,
                    String orderByColumn,
                    String direction,
                    int page,
                    int pageSize) throws SandataRuntimeException {

        ResultSet resultSet = null;

        try {

            List<InvoiceFindResult> invoiceFindResultList = new ArrayList<>();

            Response response = new Response();
            response.setData(invoiceFindResultList);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            Map<String, String> locationMap = new HashMap<>();
            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                InvoiceFindResult invoiceFindResult = new InvoiceFindResult();

                invoiceFindResult.setBillingSk(BigInteger.valueOf(resultSet.getBigDecimal("BILLING_SK").longValue()));
                //invoiceFindResult.setLineOfBusiness(resultSet.getString("BE_LOB"));
                invoiceFindResult.setPayerName(resultSet.getString("PAYER_NAME"));
                invoiceFindResult.setPayerInvoiceType(resultSet.getString("BILLING_FMT_TYP_NAME"));
                invoiceFindResult.setContractDescription(resultSet.getString("CONTR_DESC"));

                Timestamp fromDateTimestamp = resultSet.getTimestamp("BILLING_START_DATE");
                if (fromDateTimestamp != null) {
                    invoiceFindResult.setInvoiceFromDate(new Date(fromDateTimestamp.getTime()));
                }

                Timestamp toDateTimestamp = resultSet.getTimestamp("BILLING_END_DATE");
                if (toDateTimestamp != null) {
                    invoiceFindResult.setInvoiceToDate(new Date(toDateTimestamp.getTime()));
                }

                invoiceFindResult.setInvoiceNumber(resultSet.getString("INV_NUM"));

                Timestamp invoiceTimestamp = resultSet.getTimestamp("BILLING_DATE");
                if (invoiceTimestamp != null) {
                    invoiceFindResult.setInvoiceDate(new Date(invoiceTimestamp.getTime()));
                }

                invoiceFindResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                invoiceFindResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                invoiceFindResult.setPatientInsuranceIdNumber(resultSet.getString("PT_INS_ID_NUM"));
                invoiceFindResult.setBilledAmount(resultSet.getBigDecimal("BILLING_TOTAL_AMT"));
                invoiceFindResult.setSubmissionStatus(resultSet.getString("BILLING_SUBM_TYP_NAME"));

                if (!StringUtil.IsNullOrEmpty(location)) {

                    invoiceFindResult.setLocation(resultSet.getString("BE_NAME"));

                } else {
                    String bsnEntLocationName = null;
                    String bsnEntLocationId = resultSet.getString("BE_LOC_ID");
                    if (!StringUtil.IsNullOrEmpty(bsnEntLocationId)) {
                        bsnEntLocationName = locationMap.get(bsnEntLocationId);
                        if (StringUtil.IsNullOrEmpty(bsnEntLocationName)) {
                            bsnEntLocationName = getLocation(connection, bsnEntLocationId);
                            if (!StringUtil.IsNullOrEmpty(bsnEntLocationName)) {
                                locationMap.put(bsnEntLocationId, bsnEntLocationName);
                            }
                        }
                    }

                    invoiceFindResult.setLocation(bsnEntLocationName);
                }

                invoiceFindResult.setBusinessEntityId(resultSet.getString("BE_ID"));
                invoiceFindResult.setPatientId(resultSet.getString("PT_ID"));
                invoiceFindResult.setPayerId(resultSet.getString("PAYER_ID"));
                invoiceFindResult.setContractId(resultSet.getString("CONTR_ID"));

                invoiceFindResult.setEditErrorReasons(getEditErrorReasons(connection, invoiceFindResult));

                invoiceFindResult.setStatus(resultSet.getString("BILLING_STATUS_NAME"));

                invoiceFindResultList.add(invoiceFindResult);
            }

            return response;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private String orderByColumn(String sortOn, InvoiceFindType invoiceFindType) {

        String orderByColumn = "PT_LAST_NAME"; // Default

        switch (sortOn.toLowerCase()) {
            case "patient_first_name":
                orderByColumn = "PT_FIRST_NAME";
                break;
            case "lob":
                orderByColumn = "BE_LOB";
                break;
            case "payer":
                orderByColumn = "PAYER_NAME";
                break;
            case "contract":
                orderByColumn = "CONTR_DESC";
                break;
            case "inv_from_date":
                orderByColumn = "INV_START_DATE";
                break;
            case "inv_to_date":
                orderByColumn = "INV_START_DATE";
                break;
            case "inv_date":
                orderByColumn = "INV_DATE";
                break;
            case "inv_number":
                orderByColumn = "INV_NUM";
                break;
            case "patient_ins_number":
                orderByColumn = "PT_INS_ID_NUM";
                break;
            case "location":
                orderByColumn = "BE_NAME";
                break;
            case "billed_amount":
                orderByColumn = "INV_TOTAL_AMT";
                break;

            default:
                if (invoiceFindType == null) {
                    return "BILLING_START_DATE";
                }

                else if (invoiceFindType == InvoiceFindType.DELETED) {

                    //TODO: Talk to Ralph about including the User GUID in the exchange so we can use it in audit.
                    if (sortOn.equalsIgnoreCase("deleted_by")) {
                        orderByColumn = "INV_DELETED_BY";

                    } else if (sortOn.equalsIgnoreCase("reason_code")) {
                        orderByColumn = "CHANGE_REASON_CODE";
                    }
                }
        }

        return orderByColumn;
    }

    private String getLocation(Connection connection, String bsnEntLocationId) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(bsnEntLocationId)) {
            return null;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT BE_NAME FROM BE WHERE BE_ID = ?");

            int index = 1;
            preparedStatement.setString(index++, bsnEntLocationId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("BE_NAME");
            }

            return null;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<String> getEditErrorReasons(Connection connection, InvoiceFindResult invoiceFindResult) throws SandataRuntimeException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> errors = new ArrayList<>();
        try {

            preparedStatement = connection.prepareStatement("SELECT DISTINCT T2.EXCP_NAME  " +
                    "  FROM BILLING_DET_EXCP T1  " +
                    "  " +
                    "  INNER JOIN (SELECT EXCP_ID,EXCP_NAME  " +
                    "    FROM EXCP_LKUP) T2  " +
                    "  ON T1.EXCP_ID = T2.EXCP_ID  " +
                    "  " +
                    "  INNER JOIN (SELECT BILLING_DET_SK,BE_ID,PT_ID,CONTR_ID,PAYER_ID  " +
                    "    FROM BILLING_DET) T3  " +
                    "  ON T1.BILLING_DET_SK = T3.BILLING_DET_SK  " +
                    "  " +
                    "WHERE T3.BE_ID = ? AND T3.PT_ID = ?  " +
                    "  AND T3.PAYER_ID = ? AND T3.CONTR_ID = ?");

            int index = 1;
            preparedStatement.setString(index++, invoiceFindResult.getBusinessEntityId());
            preparedStatement.setString(index++, invoiceFindResult.getPatientId());
            preparedStatement.setString(index++, invoiceFindResult.getPayerId());
            preparedStatement.setString(index++, invoiceFindResult.getContractId());

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                errors.add(resultSet.getString("EXCP_NAME"));
            }

            return errors;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public Object getEntitiesForId(final Connection connection, final String sql, final String className, final Object... params) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            return result;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public BillingDetailHeaderExt getBillingDetailForPK(Long billingSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.*, " +
                    "          T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T5.PT_FIRST_NAME,T5.PT_LAST_NAME, " +
                    "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T5.PT_MIDDLE_NAME,T5.PT_MEDICARE_ID,T5.PT_MEDICAID_ID,T5.PT_DOB,T5.PT_GENDER_TYP_NAME, " +
                    "          T5.PT_TIN_QLFR,T5.PT_TIN,T5.PT_DISCHARGE_DATE, " +
                    "          T7.PT_ADDR1,PT_ADDR2,T7.PT_APT_NUM,T7.PT_CITY,T7.PT_STATE,T7.PT_PSTL_CODE, " +
                    "          T7.PT_ZIP4,T7.PT_COUNTY,T7.PT_REGION,T7.PT_BOROUGH, " +
                    "          T8.PT_CONT_PHONE_QLFR,T8.PT_PHONE,T8.PT_PHONE_EXT, " +
                    "          T9.BE_ID,T9.BE_FEDERALTAX_ID,T9.BE_NPI, " +
                    "          T10.AUTH_START_TMSTP, " +
                    "          T11.PT_INS_GRP_NUM,T11.PT_INS_GRP_NAME,BIX.INV_NUM " +
                    " " +
                    "        FROM BILLING T1 " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,INV_NUM,PAYER_ID,CONTR_ID,PT_ID " +
                    "            FROM BILLING_INV_XWALK " +
                    "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) BIX " +
                    "          ON T1.BE_ID = BIX.BE_ID AND T1.PAYER_ID = BIX.PAYER_ID AND T1.CONTR_ID = BIX.CONTR_ID " +
                    "            AND T1.PT_ID = BIX.PT_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,BE_LOB " +
                    "            FROM BE_LOB_LKUP " +
                    "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "            ON T1.BE_LOB_ID = T2.BE_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                    "            FROM PAYER " +
                    "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                    "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                    "            FROM CONTR " +
                    "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                    "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                    " " +
                    "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MEDICARE_ID,PT_MEDICAID_ID, " +
                    "                        PT_DOB,PT_GENDER_TYP_NAME,PT_TIN_QLFR,PT_TIN,PT_DISCHARGE_DATE " +
                    "            FROM PT " +
                    "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                    "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                    " " +
                    "          LEFT JOIN (SELECT ICD_DX_CODE,ICD_DX_CODE_SHORT_DESC " +
                    "            FROM ICD_DX_LKUP) T6 " +
                    "          ON T1.ICD_DX_CODE_PRMY = T6.ICD_DX_CODE " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,PT_ID,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE, " +
                    "                      PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_REGION,PT_BOROUGH " +
                    "            FROM PT_CONT_ADDR " +
                    "              WHERE (ADDR_PRIO_NAME = 'PRIMARY' AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T7 " +
                    "          ON T5.BE_ID = T7.BE_ID AND T5.PT_ID = T7.PT_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,PT_ID,PT_CONT_PHONE_QLFR,PT_PHONE,PT_PHONE_EXT " +
                    "            FROM PT_CONT_PHONE " +
                    "              WHERE ((ADDR_PRIO_NAME = 'PRIMARY' OR PT_PHONE_PRMY_IND = 1) AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T8 " +
                    "          ON T5.BE_ID = T8.BE_ID AND T5.PT_ID = T8.PT_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_FEDERALTAX_ID,BE_ID,BE_NPI " +
                    "            FROM BE WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T9 " +
                    "          ON T1.BE_ID = T9.BE_ID " +
                    " " +
                    "          LEFT JOIN (SELECT AUTH_START_TMSTP,PAYER_ID,PT_ID,BE_ID,AUTH_ID " +
                    "            FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T10 " +
                    "          ON T1.BE_ID = T10.BE_ID AND T1.PT_ID = T10.PT_ID AND T1.PAYER_ID = T10.PAYER_ID AND T1.AUTH_ID = T10.AUTH_ID " +
                    " " +
                    "          LEFT JOIN (SELECT BE_ID,PT_ID,PAYER_ID,PT_INS_GRP_NUM,PT_INS_GRP_NAME " +
                    "            FROM PT_PAYER_INS WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T11 " +
                    "          ON T1.BE_ID = T11.BE_ID AND T1.PT_ID = T11.PT_ID AND T1.PAYER_ID = T11.PAYER_ID " +
                    " " +
                    "WHERE BILLING_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, billingSk);

            BillingDetailHeaderExt billingDetailHeaderExt = new BillingDetailHeaderExt();

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                billingDetailHeaderExt.setBillingSk(BigInteger.valueOf(resultSet.getBigDecimal("BILLING_SK").longValue()));
                billingDetailHeaderExt.setLineOfBusiness(resultSet.getString("BE_LOB"));
                billingDetailHeaderExt.setPayerName(resultSet.getString("PAYER_NAME"));
                billingDetailHeaderExt.setContractDescription(resultSet.getString("CONTR_DESC"));

                Timestamp fromDateTimestamp = resultSet.getTimestamp("BILLING_START_DATE");
                if (fromDateTimestamp != null) {
                    billingDetailHeaderExt.setInvoiceFromDate(new Date(fromDateTimestamp.getTime()));
                }

                Timestamp toDateTimestamp = resultSet.getTimestamp("BILLING_END_DATE");
                if (toDateTimestamp != null) {
                    billingDetailHeaderExt.setInvoiceToDate(new Date(toDateTimestamp.getTime()));
                }

                billingDetailHeaderExt.setInvoiceNumber(resultSet.getString("INV_NUM"));

                Timestamp invoiceTimestamp = resultSet.getTimestamp("BILLING_DATE");
                if (invoiceTimestamp != null) {
                    billingDetailHeaderExt.setInvoiceDate(new Date(invoiceTimestamp.getTime()));
                }

                billingDetailHeaderExt.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                billingDetailHeaderExt.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                billingDetailHeaderExt.setPatientInsuranceIdNumber(resultSet.getString("PT_INS_ID_NUM"));
                billingDetailHeaderExt.setBilledAmount(resultSet.getBigDecimal("BILLING_TOTAL_AMT"));
                billingDetailHeaderExt.setSubmissionStatus(resultSet.getString("BILLING_SUBM_TYP_NAME"));

                String bsnEntLocationId = resultSet.getString("BE_LOC_ID");

                billingDetailHeaderExt.setLocation(getLocation(connection, bsnEntLocationId));

                billingDetailHeaderExt.setEditErrorReasons(getEditErrorReasons(connection, billingDetailHeaderExt));

                billingDetailHeaderExt.setIcdDiagnosisCodePrimary(resultSet.getString("ICD_DX_CODE_PRMY"));
                String invoiceTypeQlfr = resultSet.getString("BILLING_TYP_QLFR");
                if (!StringUtil.IsNullOrEmpty(invoiceTypeQlfr)) {
                    billingDetailHeaderExt.setInvoiceType(InvoiceTypeQualifier.fromValue(invoiceTypeQlfr));
                }

                billingDetailHeaderExt.setAuthorizationIdNumber(resultSet.getString("AUTH_ID"));
                billingDetailHeaderExt.setInvoiceFormat(resultSet.getString("BILLING_FMT_TYP_NAME"));
                billingDetailHeaderExt.setInvoiceTotalAmount(resultSet.getBigDecimal("BILLING_TOTAL_AMT"));

                //TODO: Where are these coming from?
                billingDetailHeaderExt.setPlaceOfService("Home");
                billingDetailHeaderExt.setProviderFirstName("Firstname");
                billingDetailHeaderExt.setProviderLastName("Lastname");
                //

                billingDetailHeaderExt.setPayerId(resultSet.getString("PAYER_ID"));
                billingDetailHeaderExt.setContractId(resultSet.getString("CONTR_ID"));
                billingDetailHeaderExt.setPatientId(resultSet.getString("PT_ID"));

                //GEOR-5488
                Boolean invManualOverride = resultSet.getBoolean("BILLING_MANUAL_OVERRIDE_IND");
                if (invManualOverride != null) {
                    billingDetailHeaderExt.setInvoiceManualOverrideIndicator(invManualOverride);
                }

                //BAR-131: HCFA-1500
                billingDetailHeaderExt.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));
                billingDetailHeaderExt.setPatientMedicareID(resultSet.getString("PT_MEDICARE_ID"));
                billingDetailHeaderExt.setPatientMedicaidID(resultSet.getString("PT_MEDICAID_ID"));

                Timestamp patientDob = resultSet.getTimestamp("PT_DOB");
                if (patientDob != null) {
                    billingDetailHeaderExt.setPatientDateOfBirth(new Date(patientDob.getTime()));
                }

                billingDetailHeaderExt.setPatientGender(resultSet.getString("PT_GENDER_TYP_NAME"));
                billingDetailHeaderExt.setPatientTaxpayerIdentificationNumberQualifier(resultSet.getString("PT_TIN_QLFR"));
                billingDetailHeaderExt.setPatientTaxpayerIdentificationNumber(resultSet.getString("PT_TIN"));
                billingDetailHeaderExt.setPatientAddress1(resultSet.getString("PT_ADDR1"));
                billingDetailHeaderExt.setPatientAddress2(resultSet.getString("PT_ADDR2"));
                billingDetailHeaderExt.setPatientApartmentNumber(resultSet.getString("PT_APT_NUM"));
                billingDetailHeaderExt.setPatientCity(resultSet.getString("PT_CITY"));
                billingDetailHeaderExt.setPatientState(resultSet.getString("PT_STATE"));
                billingDetailHeaderExt.setPatientPostalCode(resultSet.getString("PT_PSTL_CODE"));
                billingDetailHeaderExt.setPatientZip4(resultSet.getString("PT_ZIP4"));
                billingDetailHeaderExt.setPatientCounty(resultSet.getString("PT_COUNTY"));
                billingDetailHeaderExt.setPatientRegion(resultSet.getString("PT_REGION"));
                billingDetailHeaderExt.setPatientBorough(resultSet.getString("PT_BOROUGH"));

                billingDetailHeaderExt.setPatientPhoneQualifier(resultSet.getString("PT_CONT_PHONE_QLFR"));
                billingDetailHeaderExt.setPatientPhone(resultSet.getString("PT_PHONE"));
                billingDetailHeaderExt.setPatientPhoneExtension(resultSet.getString("PT_PHONE_EXT"));
                //

                //BAR-132: UB-04
                billingDetailHeaderExt.setBusinessEntityId(resultSet.getString("BE_ID"));
                billingDetailHeaderExt.setBusinessEntityFederalTaxID(resultSet.getString("BE_FEDERALTAX_ID"));
                billingDetailHeaderExt.setBusinessEntityNPI(resultSet.getString("BE_NPI"));

                //dmr--08/04/2016: Email 11:24 AM: Robert Nick: 52: Claim/Release of information code - The acceptable values are "Y" or "N" and "Y" should be the value used.
                billingDetailHeaderExt.setReleaseOfInformationCode("Y");
                //dmr--08/04/2016: Email 11:24 AM: Robert Nick: 53: Claim/Patient Signature source code - Upon review this is no longer  a 5010 requirement on the  UB04
                //dmr--billingDetailHeaderExt.setPatientSignatureSourceCode("Y");
                //dmr--08/04/2016: Email 11:24 AM: Robert Nick: 59: Claim/Individual relationship code- default value of "18" is currently being used, "18" translates to self. There are a table of entries driven by "What is the relationship of the patient to the insured"
                billingDetailHeaderExt.setIndividualRelationshipCode("18");

                //TODO
                billingDetailHeaderExt.setRenderingProviderNPI("111");
                billingDetailHeaderExt.setReferringProviderNPI("222");
                billingDetailHeaderExt.setAttendingProviderNPI("333");
                billingDetailHeaderExt.setBillingProviderNPI("444");
                billingDetailHeaderExt.setPayToProviderNPI("555");

                billingDetailHeaderExt.setClaimID("1");
                billingDetailHeaderExt.setClaimBillTypeName("Standard");
                //

                billingDetailHeaderExt.setInsuredGroupName(resultSet.getString("PT_INS_GRP_NAME"));
                billingDetailHeaderExt.setInsuredGroupNumber(resultSet.getString("PT_INS_GRP_NUM"));

                Timestamp patientDischargeDate = resultSet.getTimestamp("PT_DISCHARGE_DATE");
                if (patientDischargeDate != null) {
                    billingDetailHeaderExt.setPatientDischargeDate(new Date(patientDischargeDate.getTime()));
                }

                Timestamp authDate = resultSet.getTimestamp("AUTH_START_TMSTP");
                if (authDate != null) {
                    billingDetailHeaderExt.setAuthorizationDate(new Date(authDate.getTime()));
                }

                String primaryIcdQualfr = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_PRMY");
                if (primaryIcdQualfr != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifierPrimary(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode2(resultSet.getString("ICD_DX_CODE_2"));
                String primaryIcdQualfr2 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_2");
                if (primaryIcdQualfr2 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier2(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr2));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode3(resultSet.getString("ICD_DX_CODE_3"));
                String primaryIcdQualfr3 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_3");
                if (primaryIcdQualfr3 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier3(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr3));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode4(resultSet.getString("ICD_DX_CODE_4"));
                String primaryIcdQualfr4 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_4");
                if (primaryIcdQualfr4 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier4(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr4));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode5(resultSet.getString("ICD_DX_CODE_5"));
                String primaryIcdQualfr5 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_5");
                if (primaryIcdQualfr5 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier5(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr5));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode6(resultSet.getString("ICD_DX_CODE_6"));
                String primaryIcdQualfr6 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_6");
                if (primaryIcdQualfr6 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier6(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr6));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode7(resultSet.getString("ICD_DX_CODE_7"));
                String primaryIcdQualfr7 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_7");
                if (primaryIcdQualfr7 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier7(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr7));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode8(resultSet.getString("ICD_DX_CODE_8"));
                String primaryIcdQualfr8 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_8");
                if (primaryIcdQualfr8 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier8(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr8));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode9(resultSet.getString("ICD_DX_CODE_9"));
                String primaryIcdQualfr9 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_9");
                if (primaryIcdQualfr9 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier9(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr9));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode10(resultSet.getString("ICD_DX_CODE_10"));
                String primaryIcdQualfr10 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_10");
                if (primaryIcdQualfr10 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier10(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr10));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode11(resultSet.getString("ICD_DX_CODE_11"));
                String primaryIcdQualfr11 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_11");
                if (primaryIcdQualfr11 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier11(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr11));
                }

                billingDetailHeaderExt.setIcdDiagnosisCode12(resultSet.getString("ICD_DX_CODE_12"));
                String primaryIcdQualfr12 = resultSet.getString("ICD_DX_CODE_REVISION_QLFR_12");
                if (primaryIcdQualfr12 != null) {
                    billingDetailHeaderExt.setIcdDiagnosisCodeRevisionQualifier12(ICDDiagnosisCodeRevisionQualifier.fromValue(primaryIcdQualfr12));
                }

                billingDetailHeaderExt.setBillingDetailServiceLineItems(getInvoiceServiceListItems(billingDetailHeaderExt));

                List<BillingDetailExt> billingDetailExtList = billingDetailHeaderExt.getBillingDetailServiceLineItems();

                if (billingDetailExtList != null) {
                    for (BillingDetailExt billingDetailExt : billingDetailExtList) {

                        billingDetailExt.getBillingDetailException().addAll(getBillingDetailExceptions(connection, billingDetailExt.getBillingDetailSK().longValue()));
                    }
                }
                //
            }

            return billingDetailHeaderExt;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private List<BillingDetailExt> getInvoiceServiceListItems(BillingDetailHeaderExt billingDetailHeaderExt) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(billingDetailHeaderExt.getPatientId())
                || StringUtil.IsNullOrEmpty(billingDetailHeaderExt.getContractId())
                || StringUtil.IsNullOrEmpty(billingDetailHeaderExt.getPayerId())
                || StringUtil.IsNullOrEmpty(billingDetailHeaderExt.getBusinessEntityId())) {

            return null;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
    
            String sql = "SELECT T2.VISIT_SK,T1.* FROM BILLING_DET T1 " +
                    "  LEFT JOIN (SELECT TIMESHEET_DET_SK,VISIT_SK FROM TIMESHEET_DET) T2 " +
                    "  ON T1.TIMESHEET_DET_SK = T2.TIMESHEET_DET_SK " +
                    "  WHERE PT_ID = ? " +
                    "    AND PAYER_ID = ?  " +
                    "    AND CONTR_ID = ?  " +
                    "    AND BE_ID = ?  " +
                    "ORDER BY BILLING_DET_DATE ASC";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setString(index++, billingDetailHeaderExt.getPatientId());
            preparedStatement.setString(index++, billingDetailHeaderExt.getPayerId());
            preparedStatement.setString(index++, billingDetailHeaderExt.getContractId());
            preparedStatement.setString(index++, billingDetailHeaderExt.getBusinessEntityId());

            resultSet = preparedStatement.executeQuery();

            List<BillingDetailExt> resultList = new ArrayList<>();
            while (resultSet.next()) {

                BigDecimal visitSk = resultSet.getBigDecimal("VISIT_SK");
                BillingDetail billingDetail =
                        (BillingDetail)new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.BillingDetail", 1);
                BillingDetailExt billingDetailExt = new BillingDetailExt(billingDetail);
                if (visitSk != null) {
                    billingDetailExt.setVisitSK(visitSk.toBigInteger());
                }

                resultList.add(billingDetailExt);
            }

            return resultList;
    
        } catch (Exception e) {
    
            // Rollback
            if (connection != null) {
    
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
    
        } finally {
    
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public boolean recordExists(Connection connection, String sql, long skValue) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index,skValue);

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<BillingDetailException> getBillingDetailExceptions(Connection connection, long billingDetSK) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM BILLING_DET_EXCP WHERE BILLING_DET_SK = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, billingDetSK);

            resultSet = preparedStatement.executeQuery();

            List<BillingDetailException> resultList =
                    (List<BillingDetailException>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BillingDetailException");

            return resultList;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
