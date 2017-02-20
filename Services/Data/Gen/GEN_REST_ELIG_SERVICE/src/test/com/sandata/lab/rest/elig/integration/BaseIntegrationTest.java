package com.sandata.lab.rest.elig.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.rest.elig.BaseTestSupport;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class BaseIntegrationTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;

    public static final int DB1 = 1;
    public static final int DB2 = 2;

    private final int dbType;

    private void initDb(int dbType) throws Exception {
        switch (dbType) {
            case DB1:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev1")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("z3U0kCdbdN")
                                .Open();
                break;
            case DB2:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev2")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("Z4fEIRn7D2")
                                .Open();
                break;
        }

        sandataOracleConnection.startPool();
    }

    protected BaseIntegrationTest(int dbType) {
        this.dbType = dbType;

        try {
            initDb(dbType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeTest() throws UniversalConnectionPoolException, SQLException {

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

        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (Exception e) {}
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

    @Test
    public void printTestEntities() throws ParseException {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.YEAR, 2016);
        Date end = calendar.getTime();

        String terminatedDateString = "9999-12-31 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date terminatedDate = dateFormat.parse(terminatedDateString);

        Eligibility eligibility = new Eligibility();
        eligibility.setEligibilityID("1");
        eligibility.setRecordCreateTimestamp(now);
        eligibility.setRecordUpdateTimestamp(now);
        eligibility.setRecordEffectiveTimestamp(now);
        eligibility.setRecordTerminationTimestamp(terminatedDate);
        eligibility.setChangeReasonMemo("New Record");
        eligibility.setRecordCreatedBy("JASON");
        eligibility.setCurrentRecordIndicator(true);
        eligibility.setChangeVersionID(BigInteger.ONE);
        eligibility.setPayerSK(BigInteger.valueOf(11303L));
        eligibility.setBusinessEntityID("5");
        eligibility.setPatientID("er-608474");
        eligibility.setEligibilityVerificationSource("Physician");
        eligibility.setLastEligibilityCheckDate(now);
        eligibility.setEligiblityStatus("Ready");
        eligibility.setServiceType("RN");
        eligibility.setEligibilityBeginDate(now);
        eligibility.setEligibilityEndDate(end);

        System.out.println(GSONProvider.ToJson(eligibility));
    }

    @Override
    protected void onSetup() throws Exception {
    }
}
