package com.sandata.lab.audit.integration;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.audit.AuditChanged;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Test PKG_PR_UTIL oracle methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class AuditPackageTests extends BaseIntegrationTest {

    private AuditChanged auditChanged;

    private Object jpubObj;

    private Connection connection = null;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String PACKAGE_NAME = "PKG_AUDIT";

    public AuditPackageTests() throws SQLException {
    }

    @Test
    public void should_validate_get_changes_with_paging_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {
            Object[] params = new Object[3];
            params[0] = "1|62235";
            params[1] = "1|206188";
            params[2] = "73737|999999"; // Garbage Test

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("METADATA.STRING_ARRAY", connection);
            ARRAY hostKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?,?,?,?,?)}",
                    ConnectionType.METADATA, PACKAGE_NAME, "GET_CHANGES_PAGING");

            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, hostKeysArray);
            callableStatement.setObject(3, "CHANGED_ON");
            callableStatement.setObject(4, "DESC");
            callableStatement.setObject(5, 1);
            callableStatement.setObject(6, 100);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            int count = 1;
            while (resultSet.next()) {

                int rowNumber = resultSet.getInt("ROW_NUMBER");
                int totalRows = resultSet.getInt("TOTAL_ROWS");

                AuditChanged ac = new AuditChanged();
                ac.setUserName(resultSet.getString("USER_NAME"));
                ac.setUserFirstName(resultSet.getString("USER_FIRST_NAME"));
                ac.setUserLastName(resultSet.getString("USER_LAST_NAME"));
                ac.setDataPoint(resultSet.getString("DATA_POINT"));
                ac.setChangedFrom(resultSet.getString("CHANGED_FROM"));
                ac.setChangedTo(resultSet.getString("CHANGED_TO"));

                Timestamp changedOn = resultSet.getTimestamp("CHANGED_ON");
                if (changedOn != null) {
                    ac.setChangedOn(new Date(changedOn.getTime()));
                }

                Assert.assertNotNull(ac.getDataPoint());

                System.out.print("[" + rowNumber + "]: ");
                System.out.print("[" + totalRows + "]: ");
                System.out.println("Count [" + count++ + "]: " + "DataPoint = [" + ac.getDataPoint() + "]");
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void should_validate_get_changes_oracle_method() throws Exception {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        Assert.assertNotNull(connection);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}",
                    ConnectionType.METADATA, PACKAGE_NAME, "GET_CHANGES");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            //callableStatement.setObject(2, "999");
            callableStatement.setObject(2, "1");
            //callableStatement.setObject(3, 559999955L);
            callableStatement.setObject(3, 62235L);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            int count = 1;
            while (resultSet.next()) {

                AuditChanged ac = new AuditChanged();
                ac.setUserName(resultSet.getString("USER_NAME"));
                ac.setUserFirstName(resultSet.getString("USER_FIRST_NAME"));
                ac.setUserLastName(resultSet.getString("USER_LAST_NAME"));
                ac.setDataPoint(resultSet.getString("DATA_POINT"));
                ac.setChangedFrom(resultSet.getString("CHANGED_FROM"));
                ac.setChangedTo(resultSet.getString("CHANGED_TO"));

                Timestamp changedOn = resultSet.getTimestamp("CHANGED_ON");
                if (changedOn != null) {
                    ac.setChangedOn(new Date(changedOn.getTime()));
                }

                Assert.assertNotNull(ac.getDataPoint());

                System.out.println("Count [" + count++ + "]: " + "DataPoint = [" + ac.getDataPoint() + "]");
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Test
    public void should_validate_insert_changes_oracle_method() throws Exception {

        CallableStatement callableStatement = null;

        Assert.assertNotNull(connection);

        connection.setAutoCommit(false);

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?,?,?)}",
                    ConnectionType.METADATA, PACKAGE_NAME, "INSERT_CHANGES");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setObject(2, "999");
            callableStatement.setObject(3, Long.valueOf(559999955));
            callableStatement.setObject(4, jpubObj);
            callableStatement.execute();

            BigDecimal result = (BigDecimal)callableStatement.getObject(1);

            Assert.assertNotNull(result);

            System.out.println("INSERT_CHANGES: result = [" + result.longValue() + "]");

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
    protected void onSetup() throws Exception {
        auditChanged = new AuditChanged();
        auditChanged.setUserGuid("e2512eb2-fbd6-4d51-b139-a190bcdc637c");
        auditChanged.setDataPoint("payerName");
        auditChanged.setChangedFrom("HSS");
        auditChanged.setChangedTo("Health System Solutions");

        jpubObj = new DataMapper().map(auditChanged);
    }
}
