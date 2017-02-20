package com.sandata.lab.payroll.integration;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.payroll.impl.OracleDataService;
import com.sandata.lab.payroll.model.PayrollGetStaffRateParams;
import com.sandata.lab.payroll.model.StaffPayrollRates;
import com.sandata.lab.payroll.utils.log.OracleDataLogger;
import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Test PKG_PR_UTIL oracle methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollUtilPackageTests extends BaseIntegrationTest {

    private PayrollGetStaffRateParams payrollGetStaffRateParams;

    private Object payrollGetStaffRateParamsObj;

    private Connection connection = null;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String PACKAGE_NAME = "PKG_PR_UTIL";

    @Override
    protected Server getDbServer() {
        return Server.DEV1;
    }

    public PayrollUtilPackageTests() throws SQLException {
    }

    @Test
    public void should_validate_get_staff_payroll_rates_service_method() throws Exception {

        OracleDataService service = new OracleDataService();
        StaffPayrollRates staffPayrollRates = service.getStaffPayrollRates(
                connection,
                "1",
                "1",
                "1",
                "20160822103402703",
                "20160822103404248",
                77103,
                dateFormat.parse("2016-08-22 23:59:59"),
                OracleDataLogger.CreateLogger());

        Assert.assertNotNull(staffPayrollRates);
        Assert.assertNotNull(staffPayrollRates.getBusinessEntityRate());
        Assert.assertNotNull(staffPayrollRates.getPayrollRateMatrix());
        Assert.assertNotNull(staffPayrollRates.getStaffAssociatedRate());
        Assert.assertNotNull(staffPayrollRates.getStaffSupplementalRate());
        Assert.assertNotNull(staffPayrollRates.getStaffRate());
    }

    @Test
    public void should_validate_get_staff_rates_oracle_method() throws Exception {

        /*payrollGetStaffRateParams = new PayrollGetStaffRateParams();
        payrollGetStaffRateParams.setBusinessEntityID("2");
        payrollGetStaffRateParams.setBusinessEntityLineOfBusinessID("2");
        payrollGetStaffRateParams.setBusinessEntityLocationID("2");
        payrollGetStaffRateParams.setPatientID("900002014");
        payrollGetStaffRateParams.setStaffID("900225033");
        payrollGetStaffRateParams.setVisitSK(BigDecimal.valueOf(2742309));
        payrollGetStaffRateParams.setVisitDate(dateFormat.parse("2016-09-11 00:00:00"));

        payrollGetStaffRateParamsObj = new DataMapper().map(payrollGetStaffRateParams);
        */

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_STAFF_RATES");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            if (resultSet.next()) {
                //BE_RATE_CURSOR
                Object beRateCursor = resultSet.getObject("BE_RATE_CURSOR");

                Assert.assertNotNull(beRateCursor);
                Assert.assertTrue(beRateCursor instanceof ResultSet);

                ResultSet beRateCursorResultSet = (ResultSet) beRateCursor;
                List<BusinessEntityRate> businessEntityRateList =
                        (List<BusinessEntityRate>) new DataMapper().map(beRateCursorResultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");
                beRateCursorResultSet.close();

                Assert.assertNotNull(businessEntityRateList);
                Assert.assertTrue(businessEntityRateList.size() == 1);
                //

                //STAFF_SUPPL_RATE_CURSOR
                Object staffSupplRateCursor = resultSet.getObject("STAFF_SUPPL_RATE_CURSOR");

                Assert.assertNotNull(staffSupplRateCursor);
                Assert.assertTrue(staffSupplRateCursor instanceof ResultSet);

                ResultSet staffSupplRateCursorResultSet = (ResultSet) staffSupplRateCursor;
                List<StaffSupplementalRate> staffSupplementalRateList =
                        (List<StaffSupplementalRate>) new DataMapper().map(staffSupplRateCursorResultSet, "com.sandata.lab.data.model.dl.model.StaffSupplementalRate");

                Assert.assertNotNull(staffSupplementalRateList);
                Assert.assertTrue(staffSupplementalRateList.size() == 1);
                staffSupplRateCursorResultSet.close();
                //

                //STAFF_ASSOC_RATE_CURSOR
                Object staffAssocRateCursor = resultSet.getObject("STAFF_ASSOC_RATE_CURSOR");

                Assert.assertNotNull(staffAssocRateCursor);
                Assert.assertTrue(staffAssocRateCursor instanceof ResultSet);

                ResultSet staffAssocRateCursorResultSet = (ResultSet) staffAssocRateCursor;
                List<StaffAssociatedRate> staffAssociatedRateList =
                        (List<StaffAssociatedRate>)new DataMapper().map(staffAssocRateCursorResultSet, "com.sandata.lab.data.model.dl.model.StaffAssociatedRate");

                Assert.assertNotNull(staffAssociatedRateList);
                Assert.assertTrue(staffAssociatedRateList.size() == 1);
                staffAssocRateCursorResultSet.close();
                //

                //PR_RATE_MATRIX_CURSOR
                Object payrollRateMatrixCursor = resultSet.getObject("PR_RATE_MATRIX_CURSOR");

                Assert.assertNotNull(payrollRateMatrixCursor);
                Assert.assertTrue(payrollRateMatrixCursor instanceof ResultSet);

                ResultSet payrollRateMatrixCursorResultSet = (ResultSet) payrollRateMatrixCursor;
                List<PayrollRateMatrix> payrollRateMatrixList =
                        (List<PayrollRateMatrix>)new DataMapper().map(payrollRateMatrixCursorResultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");

                Assert.assertNotNull(payrollRateMatrixList);
                Assert.assertTrue(payrollRateMatrixList.size() == 1);
                payrollRateMatrixCursorResultSet.close();
                //

                List<StaffRate> staffRateList =
                        (List<StaffRate>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.StaffRate", 4);

                Assert.assertNotNull(staffRateList);
                Assert.assertTrue(staffRateList.size() == 1);
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
    public void should_validate_get_pr_rate_matrix_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_PR_RATE_MATRIX");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            List<PayrollRateMatrix> payrollRateMatrixList =
                    (List<PayrollRateMatrix>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");

            Assert.assertNotNull(payrollRateMatrixList);
            Assert.assertTrue(payrollRateMatrixList.size() == 1);

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
    public void should_validate_get_staff_assoc_rate_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_STAFF_ASSOC_RATE");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            List<StaffAssociatedRate> staffAssociatedRateList =
                    (List<StaffAssociatedRate>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffAssociatedRate");

            Assert.assertNotNull(staffAssociatedRateList);
            Assert.assertTrue(staffAssociatedRateList.size() == 1);

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
    public void should_validate_get_staff_suppl_rate_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_STAFF_SUPPL_RATE");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            List<StaffSupplementalRate> staffSupplementalRateList =
                    (List<StaffSupplementalRate>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffSupplementalRate");

            Assert.assertNotNull(staffSupplementalRateList);
            Assert.assertTrue(staffSupplementalRateList.size() == 1);

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
    public void should_validate_get_be_rate_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BE_RATE");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            List<BusinessEntityRate> businessEntityRateList =
                    (List<BusinessEntityRate>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");

            Assert.assertNotNull(businessEntityRateList);
            Assert.assertTrue(businessEntityRateList.size() == 1);

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
        payrollGetStaffRateParams = new PayrollGetStaffRateParams();
        payrollGetStaffRateParams.setBusinessEntityID("1");
        payrollGetStaffRateParams.setBusinessEntityLineOfBusinessID("1");
        payrollGetStaffRateParams.setBusinessEntityLocationID("1");
        payrollGetStaffRateParams.setPatientID("20160822103402703");
        payrollGetStaffRateParams.setStaffID("20160822103404248");
        payrollGetStaffRateParams.setVisitSK(BigDecimal.valueOf(77103));
        payrollGetStaffRateParams.setVisitDate(dateFormat.parse("2016-08-22 23:59:59"));

        payrollGetStaffRateParamsObj = new DataMapper().map(payrollGetStaffRateParams);
    }
}
