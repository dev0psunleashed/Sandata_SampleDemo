package com.sandata.lab.rest.staff.integration;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.custom.AuditEmploymentStatusHistory;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class StaffAuditEmplStatusHistoryTests extends BaseIntegrationTest {

    private Connection connection = null;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String PACKAGE_NAME = "PKG_AUDIT";

    public StaffAuditEmplStatusHistoryTests() throws SQLException {
    }

    @Test
    public void should_validate_insert_audit_staff_status_history_plsql_method() throws Exception {

        CallableStatement callableStatement = null;

        Assert.assertNotNull(connection);
        connection.setAutoCommit(false);

        try {

            AuditEmploymentStatusHistory auditEmploymentStatusHistory = new AuditEmploymentStatusHistory();
            auditEmploymentStatusHistory.setAuditHost("1212|5555555");
            auditEmploymentStatusHistory.setUserGuid("c59993c0-15c1-4c45-a2d8-6a3e7767d42a");
            auditEmploymentStatusHistory.setEffectiveDate(dateFormat.parse("2017-01-01 00:00:00"));
            auditEmploymentStatusHistory.setStatus("JUnit Test");
            auditEmploymentStatusHistory.setReasonCode("JUnit Reason Code");
            auditEmploymentStatusHistory.setNotes("JUnit Note");
            auditEmploymentStatusHistory.setModified(dateFormat.parse("2017-01-10 00:00:00"));

            Object jpubType = new DataMapper().map(auditEmploymentStatusHistory);

            String callMethod = String.format("{?=call %s.%s.INSERT_EMPL_STATUS_HIST(?)",
                    ConnectionType.METADATA, PACKAGE_NAME);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();

            int resultVal = callableStatement.getInt(1);

            Assert.assertTrue(resultVal > 0);

            connection.commit();

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
    protected Server getDbServer() {
        return Server.META1;
    }

    @Override
    protected void onSetup() throws Exception {
    }
}
