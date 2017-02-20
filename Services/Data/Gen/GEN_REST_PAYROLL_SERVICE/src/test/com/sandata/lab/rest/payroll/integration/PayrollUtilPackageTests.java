package com.sandata.lab.rest.payroll.integration;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payroll.model.PayrollFindParams;
import com.sandata.lab.rest.payroll.model.PayrollFindStatus;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Date: 8/18/16
 * Time: 6:30 PM
 */
@SuppressWarnings("unchecked")
public class PayrollUtilPackageTests extends BaseIntegrationTest {

    private Connection connection = null;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String PACKAGE_NAME = "PKG_PR_UTIL";

    @Test
    public void should_validate_find_payroll_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            Object[] params = new Object[0];
            //params[0] = "RN";
            //params[0] = "LPN";

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY servicesArray = new ARRAY(des, connection, params);

            PayrollFindParams payrollFindParams = new PayrollFindParams();
            payrollFindParams.setBusinessEntityID("1");
            payrollFindParams.setPayrollFromDate(dateFormat.parse("2016-07-01 00:00:00"));
            payrollFindParams.setPayrollToDate(dateFormat.parse("2017-08-31 23:59:59"));
            payrollFindParams.setFromRow(BigInteger.valueOf(1));
            payrollFindParams.setToRow(BigInteger.valueOf(10));
            payrollFindParams.setOrderByColumn("STAFF_ID");
            payrollFindParams.setOrderByDirection("DESC");
            //payrollFindParams.setStaffFirstName("%DAV%");
            //payrollFindParams.setStaffFirstName("%B2E94D29%");
            //payrollFindParams.setStaffLastName("%RUT%");
            //payrollFindParams.setStaffLastName("%1E4B5F99D20A%");
            //payrollFindParams.setStaffLastName("%DAAC%");
            //payrollFindParams.setStaffLastName("rut".toUpperCase());
            //payrollFindParams.setStaffID("20160817015001132");
            //payrollFindParams.setStaffID("1");
            //payrollFindParams.setStaffID("20160906110100512");
            //payrollFindParams.setPayHours(BigInteger.valueOf(TimeUtil.HoursToSeconds(2.75f)));
            //payrollFindParams.setPayHours(BigInteger.valueOf(TimeUtil.HoursToSeconds(120f)));
            //payrollFindParams.setRateAmount(BigDecimal.valueOf(10.50));
            //payrollFindParams.setRateAmount(BigDecimal.valueOf(111.75));
            //payrollFindParams.setRateAmount(BigDecimal.valueOf(15.55));

            //payrollFindParams.setPatientID("1");
            //payrollFindParams.setPatientID("20160817014959388");

            //payrollFindParams.setPatientFirstName("%15A453AF%");
            //payrollFindParams.setPatientFirstName("%JA%");
            payrollFindParams.setPatientFirstName("%TEST%");

            //payrollFindParams.setPatientLastName("%8FD9%");
            //payrollFindParams.setPatientLastName("%BLA%");
            //payrollFindParams.setPatientLastName("%3EA0%");

            //payrollFindParams.setServiceName("PCA");
            //payrollFindParams.setServiceName("'RN','PCA'");
            //payrollFindParams.setServiceName("'LPN','RN'");
            //payrollFindParams.setServiceName("'LPN','RN','PCA','NA'");

            payrollFindParams.setStatus(PayrollFindStatus.ALL);
            //payrollFindParams.setStatus(PayrollFindStatus.PENDING);
            //payrollFindParams.setStatus(PayrollFindStatus.PAID);

            Object jpubType = new DataMapper().map(payrollFindParams);

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "FIND_PAYROLL");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, jpubType);
            callableStatement.setArray(3, servicesArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            int totalRows = 0;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                if (totalRows == 0) {
                    totalRows = resultSet.getBigDecimal("TOTAL_ROWS").intValue();
                    System.out.println("\tTOTAL_ROWS : [" + totalRows + "]");
                }

                System.out.println("\tTIMESHEET_SUMMARY_SK : " + resultSet.getBigDecimal("TIMESHEET_SUMMARY_SK"));
                System.out.println("\tSTAFF_ID : " + resultSet.getString("STAFF_ID"));
                System.out.println("\tSTAFF_FIRST_NAME : " + resultSet.getString("STAFF_FIRST_NAME"));
                System.out.println("\tSTAFF_LAST_NAME : " + resultSet.getString("STAFF_LAST_NAME"));
                System.out.println("\tTIMESHEET_WEEK_TOTAL_HRS : " + resultSet.getBigDecimal("TIMESHEET_WEEK_TOTAL_HRS"));
                System.out.println("\tTIMESHEET_WEEK_END_DATE : " + resultSet.getTimestamp("TIMESHEET_WEEK_END_DATE"));

                System.out.println("\tEFT_TXN_ID : " + resultSet.getString("EFT_TXN_ID"));
                System.out.println("\tCHECK_NUM : " + resultSet.getString("CHECK_NUM"));
                System.out.println("\tCHECK_DATE : " + resultSet.getTimestamp("CHECK_DATE"));
                System.out.println("\tGROSS_PAY_AMT : " + resultSet.getBigDecimal("GROSS_PAY_AMT"));

                Object timesheetDetCursor = resultSet.getObject("TIMESHEET_DET_CURSOR");
                Assert.assertNotNull(timesheetDetCursor);
                Assert.assertTrue(timesheetDetCursor instanceof ResultSet);

                int tdcCount = 1;
                ResultSet tdcResultSet = (ResultSet) timesheetDetCursor;
                while (tdcResultSet.next()) {

                    System.out.println(String.format("**** DET > [%d] ****", tdcCount++));
                    System.out.println("\t\tVISIT_SK : " + tdcResultSet.getBigDecimal("VISIT_SK"));
                    System.out.println("\t\tPT_ID : " + tdcResultSet.getString("PT_ID"));
                    System.out.println("\t\tTIMESHEET_DATE : " + tdcResultSet.getString("TIMESHEET_DATE"));
                    System.out.println("\t\tPR_RATE_AMT : " + tdcResultSet.getString("PR_RATE_AMT"));
                    System.out.println("\t\tPR_HRS : " + tdcResultSet.getString("PR_HRS"));
                    System.out.println("\t\tPT_FIRST_NAME : " + tdcResultSet.getString("PT_FIRST_NAME"));
                    System.out.println("\t\tPT_LAST_NAME : " + tdcResultSet.getString("PT_LAST_NAME"));
                    System.out.println("\t\tSVC_NAME : " + tdcResultSet.getString("SVC_NAME"));
                }

                tdcResultSet.close();

            }

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

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

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
        }
    }

    @Test
    public void should_validate_get_payroll_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_PAYROLL");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, 5384L);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            if(resultSet.next()) {

                System.out.println("\tTIMESHEET_SUMMARY_SK : " + resultSet.getBigDecimal("TIMESHEET_SUMMARY_SK"));
                System.out.println("\tSTAFF_ID : " + resultSet.getString("STAFF_ID"));
                System.out.println("\tSTAFF_FIRST_NAME : " + resultSet.getString("STAFF_FIRST_NAME"));
                System.out.println("\tSTAFF_LAST_NAME : " + resultSet.getString("STAFF_LAST_NAME"));
                System.out.println("\tTIMESHEET_WEEK_TOTAL_HRS : " + resultSet.getBigDecimal("TIMESHEET_WEEK_TOTAL_HRS"));
                System.out.println("\tTIMESHEET_WEEK_END_DATE : " + resultSet.getTimestamp("TIMESHEET_WEEK_END_DATE"));

                System.out.println("\tEFT_TXN_ID : " + resultSet.getString("EFT_TXN_ID"));
                System.out.println("\tCHECK_NUM : " + resultSet.getString("CHECK_NUM"));
                System.out.println("\tCHECK_DATE : " + resultSet.getTimestamp("CHECK_DATE"));
                System.out.println("\tGROSS_PAY_AMT : " + resultSet.getBigDecimal("GROSS_PAY_AMT"));

                Object timesheetDetCursor = resultSet.getObject("TIMESHEET_DET_CURSOR");
                Assert.assertNotNull(timesheetDetCursor);
                Assert.assertTrue(timesheetDetCursor instanceof ResultSet);

                int tdcCount = 1;
                ResultSet tdcResultSet = (ResultSet) timesheetDetCursor;
                while (tdcResultSet.next()) {

                    System.out.println(String.format("**** DET > [%d] ****", tdcCount++));
                    System.out.println("\t\tVISIT_SK : " + tdcResultSet.getBigDecimal("VISIT_SK"));
                    System.out.println("\t\tPT_ID : " + tdcResultSet.getString("PT_ID"));
                    System.out.println("\t\tTIMESHEET_DATE : " + tdcResultSet.getString("TIMESHEET_DATE"));
                    System.out.println("\t\tPR_RATE_AMT : " + tdcResultSet.getString("PR_RATE_AMT"));
                    System.out.println("\t\tPR_HRS : " + tdcResultSet.getString("PR_HRS"));
                    System.out.println("\t\tPT_FIRST_NAME : " + tdcResultSet.getString("PT_FIRST_NAME"));
                    System.out.println("\t\tPT_LAST_NAME : " + tdcResultSet.getString("PT_LAST_NAME"));
                    System.out.println("\t\tSVC_NAME : " + tdcResultSet.getString("SVC_NAME"));
                }

                tdcResultSet.close();

            }

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

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

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
        }
    }

    @Override
    protected void afterConnectionPoolStarted() throws Exception {
        connection = getSandataOracleConnection().getConnection();
        connection.setAutoCommit(true);
    }

    @Override
    protected void beforeConnectionPoolStopped() throws Exception {
        // Close the Connection
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    @Override
    protected void onSetup() throws Exception {
    }

    @Override
    protected void onComplete() throws Exception {
    }

    public PayrollUtilPackageTests() throws SQLException {
    }
}
