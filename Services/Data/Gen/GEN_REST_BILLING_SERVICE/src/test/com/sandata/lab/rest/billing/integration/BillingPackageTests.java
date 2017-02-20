package com.sandata.lab.rest.billing.integration;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.billing.impl.InvoiceDataService;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/30/16
 * Time: 11:40 AM
 */

public class BillingPackageTests extends BaseIntegrationTest {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final String PACKAGE_NAME = "D_PKG_BILLING_UTIL";

    @Test
    public void should_validate_get_billing_preview_summary() throws Exception {

        List<Long> invoiceSkList = new ArrayList<>();
        invoiceSkList.add(1L);
        invoiceSkList.add(5L);

        InvoiceDataService service = new InvoiceDataService();

        //TODO: Need to be able to inject oracle connection
        //dmr--BillingPreviewSummary bps = service.getBillingPreviewSummary(invoiceSkList, getSandataOracleConnection());
        //dmr--Assert.assertNotNull(bps);
    }

    @Test
    public void should_validate_get_brs_inv_detail_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[2];
            params[0] = 1L;
            params[1] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BRS_INV_DETAILS");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                System.out.println("\tBE_LOC_ID : " + resultSet.getString("BE_LOC_ID"));
                System.out.println("\tINV_SK : " + resultSet.getBigDecimal("INV_SK"));
                System.out.println("\tBE_LOB_ID : " + resultSet.getString("BE_LOB_ID"));
                System.out.println("\tPAYER_NAME : " + resultSet.getString("PAYER_NAME"));
                System.out.println("\tCONTR_DESC : " + resultSet.getString("CONTR_DESC"));

                Timestamp invStartDateTmsp = resultSet.getTimestamp("INV_START_DATE");
                if (invStartDateTmsp != null) {
                    System.out.println("\tINV_START_DATE : " + dateFormat.format(new java.util.Date(invStartDateTmsp.getTime())));
                }

                Timestamp invEndDateTmsp = resultSet.getTimestamp("INV_END_DATE");
                if (invEndDateTmsp != null) {
                    System.out.println("\tINV_END_DATE : " + dateFormat.format(new java.util.Date(invEndDateTmsp.getTime())));
                }

                System.out.println("\tINV_NUM : " + resultSet.getString("INV_NUM"));

                Timestamp invDateTmsp = resultSet.getTimestamp("INV_DATE");
                if (invDateTmsp != null) {
                    System.out.println("\tINV_DATE : " + dateFormat.format(new java.util.Date(invDateTmsp.getTime())));
                }

                System.out.println("\tPT_FIRST_NAME : " + resultSet.getString("PT_FIRST_NAME"));
                System.out.println("\tPT_LAST_NAME : " + resultSet.getString("PT_LAST_NAME"));
                System.out.println("\tPT_INS_ID_NUM : " + resultSet.getString("PT_INS_ID_NUM"));
                System.out.println("\tBE_NAME : " + resultSet.getString("BE_NAME"));
                System.out.println("\tINV_TOTAL_AMT : " + resultSet.getBigDecimal("INV_TOTAL_AMT"));
                System.out.println("\tINV_SUBM_TYP_NAME : " + resultSet.getString("INV_SUBM_TYP_NAME"));
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
    }

    @Test
    public void should_validate_get_brs_for_subm_typ_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[2];
            params[0] = 1L;
            params[1] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BRS_FOR_SUBM_TYP");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                System.out.println("\tSUBMISSION_TYPE : " + resultSet.getString("SUBMISSION_TYPE"));
                System.out.println("\tTOTAL_INVOICES_BILLED : " + resultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                System.out.println("\tTOTAL_AMOUNT_BILLED : " + resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                System.out.println("\tBILLED_HOURS : " + resultSet.getBigDecimal("BILLED_HOURS"));
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
    }

    @Test
    public void should_validate_get_brs_for_payer_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[2];
            params[0] = 1L;
            params[1] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BRS_FOR_PAYER");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                System.out.println("\tPAYER : " + resultSet.getString("PAYER"));
                System.out.println("\tTOTAL_INVOICES_BILLED : " + resultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                System.out.println("\tTOTAL_AMOUNT_BILLED : " + resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                System.out.println("\tBILLED_HOURS : " + resultSet.getBigDecimal("BILLED_HOURS"));
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
    }

    @Test
    public void should_validate_get_brs_payer_for_subm_typ_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[3];
            params[0] = 1L;
            params[1] = 2L;
            params[2] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BRS_PAYER_FOR_SUBM_TYP");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.setObject(3, "FIRST_TIME_SUBMISSION");
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                System.out.println("\tPAYER : " + resultSet.getString("PAYER"));
                System.out.println("\tTOTAL_INVOICES_BILLED : " + resultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                System.out.println("\tTOTAL_AMOUNT_BILLED : " + resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                System.out.println("\tBILLED_HOURS : " + resultSet.getBigDecimal("BILLED_HOURS"));
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
    }

    @Test
    public void should_validate_get_brs_for_lob_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[2];
            params[0] = 1L;
            params[1] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "GET_BRS_FOR_LOB");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while(resultSet.next()) {

                System.out.println(String.format("**** [%d] ****", count++));
                System.out.println("\tLINE_OF_BUSINESS : " + resultSet.getString("LINE_OF_BUSINESS"));
                System.out.println("\tTOTAL_INVOICES_BILLED : " + resultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                System.out.println("\tTOTAL_AMOUNT_BILLED : " + resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                System.out.println("\tBILLED_HOURS : " + resultSet.getBigDecimal("BILLED_HOURS"));
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
    }

    @Test
    public void should_validate_billable_review_summary_oracle_method() throws Exception {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[2];
            params[0] = 1L;
            params[1] = 5L;

            connection = getSandataOracleConnection().getConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, PACKAGE_NAME, "BILLABLE_REVIEW_SUMMARY");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            while(resultSet.next()) {

                System.out.println("SUBMISSION_DATE : " + dateFormat.format(new Date(resultSet.getTimestamp("SUBMISSION_DATE").getTime())));
                System.out.println("TOTAL_INVOICES_BILLED : " + resultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                System.out.println("TOTAL_AMOUNT_BILLED : " + resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                System.out.println("BILLED_HOURS : " + resultSet.getBigDecimal("BILLED_HOURS"));

                Object lobCursor =  resultSet.getObject("LOB_CURSOR_RESULT");

                Assert.assertNotNull(lobCursor);
                Assert.assertTrue(lobCursor instanceof ResultSet);

                int count = 1;
                ResultSet lobCursorResultSet = (ResultSet) lobCursor;
                while (lobCursorResultSet.next()) {

                    System.out.println(String.format("**** [%d] ****", count++));
                    System.out.println("\tLINE_OF_BUSINESS : " + lobCursorResultSet.getString("LINE_OF_BUSINESS"));
                    System.out.println("\tTOTAL_INVOICES_BILLED : " + lobCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                    System.out.println("\tTOTAL_AMOUNT_BILLED : " + lobCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                    System.out.println("\tBILLED_HOURS : " + lobCursorResultSet.getBigDecimal("BILLED_HOURS"));
                }

                lobCursorResultSet.close();

                Object payerCursor =  resultSet.getObject("PAYER_CURSOR_RESULT");

                Assert.assertNotNull(payerCursor);
                Assert.assertTrue(payerCursor instanceof ResultSet);

                count = 1;
                ResultSet payerCursorResultSet = (ResultSet) payerCursor;
                while (payerCursorResultSet.next()) {

                    System.out.println(String.format("**** [%d] ****", count++));
                    System.out.println("\tPAYER : " + payerCursorResultSet.getString("PAYER"));
                    System.out.println("\tTOTAL_INVOICES_BILLED : " + payerCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                    System.out.println("\tTOTAL_AMOUNT_BILLED : " + payerCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                    System.out.println("\tBILLED_HOURS : " + payerCursorResultSet.getBigDecimal("BILLED_HOURS"));
                }

                payerCursorResultSet.close();

                Object submTypeCursor =  resultSet.getObject("SUBM_TYP_CURSOR_RESULT");

                Assert.assertNotNull(submTypeCursor);
                Assert.assertTrue(submTypeCursor instanceof ResultSet);

                count = 1;
                ResultSet submTypeCursorResultSet = (ResultSet) submTypeCursor;
                while (submTypeCursorResultSet.next()) {

                    System.out.println(String.format("**** [%d] ****", count++));
                    System.out.println("\tSUBMISSION_TYPE : " + submTypeCursorResultSet.getString("SUBMISSION_TYPE"));
                    System.out.println("\tTOTAL_INVOICES_BILLED : " + submTypeCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                    System.out.println("\tTOTAL_AMOUNT_BILLED : " + submTypeCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                    System.out.println("\tBILLED_HOURS : " + submTypeCursorResultSet.getBigDecimal("BILLED_HOURS"));
                }

                submTypeCursorResultSet.close();

                Object invDetCursor =  resultSet.getObject("INV_DET_CURSOR_RESULT");

                Assert.assertNotNull(invDetCursor);
                Assert.assertTrue(invDetCursor instanceof ResultSet);

                count = 1;
                ResultSet invDetCursorResultSet = (ResultSet) invDetCursor;
                while (invDetCursorResultSet.next()) {

                    System.out.println(String.format("**** [%d] ****", count++));
                    System.out.println("\tBE_LOC_ID : " + invDetCursorResultSet.getString("BE_LOC_ID"));
                    System.out.println("\tINV_SK : " + invDetCursorResultSet.getBigDecimal("INV_SK"));
                    System.out.println("\tBE_LOB_ID : " + invDetCursorResultSet.getString("BE_LOB_ID"));
                    System.out.println("\tPAYER_NAME : " + invDetCursorResultSet.getString("PAYER_NAME"));
                    System.out.println("\tCONTR_DESC : " + invDetCursorResultSet.getString("CONTR_DESC"));

                    Timestamp invStartDateTmsp = invDetCursorResultSet.getTimestamp("INV_START_DATE");
                    if (invStartDateTmsp != null) {
                        System.out.println("\tINV_START_DATE : " + dateFormat.format(new java.util.Date(invStartDateTmsp.getTime())));
                    }

                    Timestamp invEndDateTmsp = invDetCursorResultSet.getTimestamp("INV_END_DATE");
                    if (invEndDateTmsp != null) {
                        System.out.println("\tINV_END_DATE : " + dateFormat.format(new java.util.Date(invEndDateTmsp.getTime())));
                    }

                    System.out.println("\tINV_NUM : " + invDetCursorResultSet.getString("INV_NUM"));

                    Timestamp invDateTmsp = invDetCursorResultSet.getTimestamp("INV_DATE");
                    if (invDateTmsp != null) {
                        System.out.println("\tINV_DATE : " + dateFormat.format(new java.util.Date(invDateTmsp.getTime())));
                    }

                    System.out.println("\tPT_FIRST_NAME : " + invDetCursorResultSet.getString("PT_FIRST_NAME"));
                    System.out.println("\tPT_LAST_NAME : " + invDetCursorResultSet.getString("PT_LAST_NAME"));
                    System.out.println("\tPT_INS_ID_NUM : " + invDetCursorResultSet.getString("PT_INS_ID_NUM"));
                    System.out.println("\tBE_NAME : " + invDetCursorResultSet.getString("BE_NAME"));
                    System.out.println("\tINV_TOTAL_AMT : " + invDetCursorResultSet.getBigDecimal("INV_TOTAL_AMT"));
                    System.out.println("\tINV_SUBM_TYP_NAME : " + invDetCursorResultSet.getString("INV_SUBM_TYP_NAME"));
                }

                invDetCursorResultSet.close();

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
    }

    @Override
    protected void onSetup() throws Exception {
    }

    public BillingPackageTests() throws SQLException {
    }
}
