package com.sandata.lab.rest.am.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.rest.am.BaseTestSupport;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class BaseIntegrationTest extends BaseTestSupport {

    protected SandataOracleConnection sandataOracleConnection;

    @Before
    public void beforeTest() throws UniversalConnectionPoolException, SQLException {
        this.sandataOracleConnection =
            new SandataOracleConnection()
//                .DatabaseName("sdbdev1")
                .DatabaseName("sdbdev2")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
                .User("coredata")
//                .User("metadata")
//                .Password("z3U0kCdbdN")
                .Password("Z4fEIRn7D2")
//                .Password("R63JumYQ6L")
                .Open();

        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    protected Connection openConnection() throws SQLException {

        Assert.assertNotNull(sandataOracleConnection);

        Connection connection = sandataOracleConnection.getConnection();

        Assert.assertNotNull(connection);

        connection.setAutoCommit(true);

        return connection;
    }

    protected Object call(final String method, int outputParameterType, Object[] params) throws SQLException {

        Connection connection = null;
        try {

            connection = openConnection();

            CallableStatement callableStatement = connection.prepareCall(method);

            callableStatement.registerOutParameter(1, outputParameterType);

            int index = 2;
            for (Object param : params) {
                callableStatement.setObject(index++, param);
            }

            callableStatement.execute();
            return callableStatement.getObject(1);

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
    }

    protected Object call(final Connection connection, final String method, int outputParameterType, Object[] params) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        int index = 2;
        for (Object param : params) {
            callableStatement.setObject(index++, param);
        }

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    protected Object call(final Connection connection, final String method, int outputParameterType) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    public SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    @Override
    protected void onSetup() throws Exception {
    }

    class GeneralSettings {
        private String maxSchedulingRecurring = "16";
        private String payrollFrequency = "Weekly";
        private String payrollEndDay = "Friday";
        private String payrollEndTime = "5:00PM";
        private String weekendStartDay = "Saturday";
        private boolean requiresNotesOnAllReasonCodes = false;
        private boolean requiresDischargeNote = false;
        private boolean enforceAllReasonCodes = false;
        private boolean defaultScheduleViewToCurrentWeek = false;
        private boolean showVerifiedTimesOnSchedule = true;
        private boolean allowMoreThan24ScheduledHours = false;
        private boolean matchStaffSkillsToPatientRequirements = false;
        private String maxAllowedWorkHours = "40";
        private String overtimeRestriction = "warn";
        private boolean requiresReasonCodeForOvertime = true;
    }

    class AdditionalSettings {
        private String patientAssessmentFrequency = "3 Months";
        private String defaultStatusForNewStaff = "Recruit";
        private String unscheduledDaysForAutoTermination = "120";
        private boolean showStaffUnionStatus = true;
        private boolean defaultUnionStatus = false;
        private boolean autoGeneratePatientAndStaffId = true;
    }

    class VisitVerificationSettings {
        private String numberOfCriticalTasksRequired = "";
        private boolean requiresReasonCodesInVisitVerification = false;
        private boolean allowMoreThan24HoursVerified = false;
    }
}
