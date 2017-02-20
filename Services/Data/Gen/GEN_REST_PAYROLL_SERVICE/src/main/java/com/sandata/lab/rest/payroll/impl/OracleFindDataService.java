package com.sandata.lab.rest.payroll.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.extended.PayrollOutputExt;
import com.sandata.lab.rest.payroll.api.OracleService;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.camel.PropertyInject;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OracleFindDataService extends OracleDataService implements OracleService {

    @PropertyInject("{{payroll.batch.size}}")
    private int batchSize = 200;

    public List<PayrollOutputExt> getPROutputForPayrollPeriod(String businessEntityID, Date payrollEndDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT COUNT(*) AS TOTAL FROM COREDATA.PR_OUTPUT T1 " +
                    " WHERE T1.BE_ID = ? " +
                    " AND T1.WEEK_END_DATE <= ? " +
                    " AND T1.CURR_REC_IND = 1 AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " AND (TO_CHAR(T1.PR_EXPORT_DATE, 'YYYY-MM-DD') = '9999-12-31')");


            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setDate(2, new java.sql.Date(payrollEndDate.getTime()));

            resultSet = preparedStatement.executeQuery();

            int numRows = 0;

            while (resultSet.next()) {

                numRows = resultSet.getInt("TOTAL");

            }

            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            List<PayrollOutputExt> payrollOutputList = new ArrayList<>();

            int page = 1;
            int pageSize = batchSize;

            while (payrollOutputList.size() < numRows) {

                connection = connectionPoolDataService.getConnection();
                connection.setAutoCommit(true);

                int toRow = page * pageSize;
                int fromRow = toRow - (pageSize - 1);

                sql = String.format(" SELECT * from (SELECT ROWNUM ROW_NUM,r1.* FROM " +
                        " (SELECT * FROM COREDATA.PR_OUTPUT T1 " +
                        " JOIN COREDATA.STAFF T2 ON T1.STAFF_ID = T2.STAFF_ID AND T1.BE_ID = T2.BE_ID" +
                        //Get the Staff rate that falls within the date range of the payroll week end date for the service
                        " LEFT OUTER JOIN COREDATA.STAFF_RATE T3" +
                        " ON T2.STAFF_ID = T3.STAFF_ID AND T2.STAFF_POSITION_NAME = T3.SVC_NAME " +
                        " AND T1.WEEK_END_DATE BETWEEN T3.STAFF_RATE_EFF_DATE AND T3. STAFF_RATE_TERM_DATE " +
                        " LEFT OUTER JOIN (SELECT BE_ID, BE_LOB FROM COREDATA.BE_LOB_LKUP WHERE CURR_REC_IND = 1) T4" +
                        "   ON T1.BE_LOB_ID = T4.BE_ID " +
                        " WHERE T1.BE_ID = ? " +
                        " AND T1.WEEK_END_DATE <= ?" +
                        " AND T1.CURR_REC_IND = 1 AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')" +
                        " AND  (TO_CHAR(T1.PR_EXPORT_DATE, 'YYYY-MM-DD') = '9999-12-31')" +
                        " ORDER BY T1.REC_CREATE_TMSTP DESC) r1) " +
                        " WHERE ROW_NUM BETWEEN ? AND ? ");

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, businessEntityID);
                preparedStatement.setDate(2, new java.sql.Date(payrollEndDate.getTime()));
                preparedStatement.setInt(3, fromRow);
                preparedStatement.setInt(4, toRow);

                resultSet = preparedStatement.executeQuery();

                //Stop loop since there are not more results.
                if (!resultSet.isBeforeFirst()) {

                    break;
                }

                page++;


                while (resultSet.next()) {

                    PayrollOutputExt payrollOutputExt = new PayrollOutputExt();

                    payrollOutputExt.setPayrollOutputSK(resultSet.getBigDecimal("PR_OUTPUT_SK").toBigInteger());
                    payrollOutputExt.setPayrollOutputID(resultSet.getString("PR_OUTPUT_ID"));
                    payrollOutputExt.setLineOfBusiness(resultSet.getString("BE_LOB"));
                    payrollOutputExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                    payrollOutputExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
                    payrollOutputExt.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
                    payrollOutputExt.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
                    payrollOutputExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
                    payrollOutputExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
                    payrollOutputExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
                    payrollOutputExt.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
                    payrollOutputExt.setChangeVersionID(resultSet.getBigDecimal("CHANGE_VERSION_ID").toBigInteger());
                    payrollOutputExt.setBusinessEntityID(resultSet.getString("BE_ID"));
                    payrollOutputExt.setStaffID(resultSet.getString("STAFF_ID"));
                    payrollOutputExt.setTimesheetSummarySK(resultSet.getBigDecimal("TIMESHEET_SUMMARY_SK").toBigInteger());
                    payrollOutputExt.setWeekEndDate(resultSet.getTimestamp("WEEK_END_DATE"));
                    payrollOutputExt.setPayrollExportDate(resultSet.getTimestamp("PR_EXPORT_DATE"));
                    payrollOutputExt.setPayrollCode(resultSet.getString("PR_CODE"));
                    payrollOutputExt.setPayrollHours(resultSet.getBigDecimal("PR_HRS"));
                    payrollOutputExt.setPayrollRate(resultSet.getBigDecimal("PR_RATE"));
                    payrollOutputExt.setPayrollAmount(resultSet.getBigDecimal("PR_AMT"));
                    payrollOutputExt.setCheckMemo(resultSet.getString("CHECK_MEMO"));
                    payrollOutputExt.setStaffTaxpayerIdentificationNumber(resultSet.getString("STAFF_TIN"));
                    payrollOutputExt.setStaffRateAmount(resultSet.getBigDecimal("STAFF_RATE_AMT"));
                    payrollOutputExt.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                    payrollOutputExt.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

                    String staffPositionName = resultSet.getString("STAFF_POSITION_NAME");


                    if (!StringUtil.IsNullOrEmpty(staffPositionName)) {
                        payrollOutputExt.setStaffPositionName(ServiceName.fromValue(staffPositionName));
                    }

                    payrollOutputList.add(payrollOutputExt);
                }

                this.connectionPoolDataService.close(connection);
                resultSet.close();
                preparedStatement.close();

                if (payrollOutputList.size() == numRows) {
                    break;
                }
            }

            return payrollOutputList;


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

    public void setExportedDate(List<BigInteger> prOutputSks, long exportTimeStamp) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor("NUMBER_ARRAY", connection);
            ARRAY prOutputSkArray = new ARRAY(arrayDes, connection, prOutputSks.toArray());

            String callMethod = "{?=call PKG_PR_UTIL.SET_EXPORTED_DATE(?, ?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setArray(index++, prOutputSkArray);
            callableStatement.setDate(index++, new java.sql.Date(exportTimeStamp));

            callableStatement.execute();

            connection.commit();

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
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
}
