package com.sandata.lab.eligibility.impl;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.data.model.dl.model.EligibilityStatusName;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import oracle.jdbc.OracleTypes;

public class OracleDataServiceTest {

    private AppenderSkeleton logAppenderMock;
    private ConnectionPoolDataService connectionPoolDataServiceMock;
    private Connection connectionMock;
    private CallableStatement callableStatementMock;
    private PreparedStatement prepareStatementMock;
    private ResultSet resultSetMock;

    private final StringBuilder actualCallOrder = new StringBuilder();

    @Before
    public void setupMocks() throws SQLException {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);

        this.connectionMock = mock(Connection.class);
        this.connectionPoolDataServiceMock = mock(ConnectionPoolDataService.class);
        this.callableStatementMock = mock(CallableStatement.class);
        this.prepareStatementMock = mock(PreparedStatement.class);
        this.resultSetMock = mock(ResultSet.class);

        // mocks for ConnectionPoolDataService
        when(this.connectionPoolDataServiceMock.getConnection(any(ConnectionType.class)))
            .thenAnswer(new Answer<Connection>() {
                @Override
                public Connection answer(InvocationOnMock invocation) throws Throwable {
                    ConnectionType connectionType = invocation.getArgumentAt(0, ConnectionType.class);
                    actualCallOrder.append("-connectionPoolDataService#getConnection(").append(connectionType).append(")");
                    return connectionMock;
                }
            });

        when(this.connectionPoolDataServiceMock.getConnection())
        .thenAnswer(new Answer<Connection>() {
            @Override
            public Connection answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-connectionPoolDataService#getConnection()");
                return connectionMock;
            }
        });

        // mocks for Connection
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-connection#setAutoCommit(false)");
                return null;
            }
        }).when(this.connectionMock).setAutoCommit(false);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-connection#setAutoCommit(true)");
                return null;
            }
        }).when(this.connectionMock).setAutoCommit(true);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-connection#commit()");
                return null;
            }
        }).when(this.connectionMock).commit();

        when(this.connectionMock.prepareCall(anyString()))
            .thenAnswer(new Answer<CallableStatement>() {
                @Override
                public CallableStatement answer(InvocationOnMock invocation) throws Throwable {
                    actualCallOrder.append("-connection#prepareCall()");
                    return callableStatementMock;
                }
            });

        when(this.connectionMock.prepareStatement(anyString()))
        .thenAnswer(new Answer<PreparedStatement>() {
            @Override
            public PreparedStatement answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-connection#prepareStatement()");
                return prepareStatementMock;
            }
        });

        // mocks for CallableStatement
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-callableStatement#execute()");
                return null;
            }
        }).when(this.callableStatementMock).execute();

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-callableStatement#executeQuery()");
                return null;
            }
        }).when(this.callableStatementMock).executeQuery();

        // mocks for PreparedStatement
        doAnswer(new Answer<ResultSet>() {
            @Override
            public ResultSet answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-preparedStatement#executeQuery()");
                return resultSetMock;
            }
        }).when(this.prepareStatementMock).executeQuery();

        doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                actualCallOrder.append("-preparedStatement#executeUpdate()");
                return 1;
            }
        }).when(this.prepareStatementMock).executeUpdate();
    }

    @Test
    public void testExecute_should_call_correct_Oracle_package_and_method() throws SQLException {
        // arrange
        String testPackageName = "testPackageName";
        String testMethodName = "testMethodName";
        ConnectionType testConnectionType = ConnectionType.COREDATA;
        Object testJpubType = new Object();

        int expectedResult = 123;

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(").append(testConnectionType).append(")");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareCall()");
        expectedCallOrder.append("-callableStatement#execute()");
        expectedCallOrder.append("-connection#commit()");

        String expectedPrepareCall = String.format("{?=call %s.%s(?)}", testPackageName, testMethodName);

        when(this.callableStatementMock.getObject(1)).thenReturn(Integer.valueOf(expectedResult));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        int actualResult = oracleDataService.execute(testConnectionType, testPackageName, testMethodName, testJpubType);

        // assert
        verify(this.connectionMock, times(1)).prepareCall(expectedPrepareCall);
        verify(this.callableStatementMock, times(1)).registerOutParameter(1, OracleTypes.INTEGER);
        verify(this.callableStatementMock, times(1)).setObject(eq(2), eq(testJpubType));
        assertThat(actualResult, equalTo(expectedResult));
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));
        
        // assertion for safe close resource
        verify(this.callableStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testExecute_should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_executing_the_database_call() throws SQLException {
        // arrange
        String testPackageName = "testPackageName";
        String testMethodName = "testMethodName";
        ConnectionType testConnectionType = ConnectionType.COREDATA;
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.execute()).thenThrow(new SQLException(fakeExceptionMessage));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.execute(testConnectionType, testPackageName, testMethodName, new Object());

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(containsString(testConnectionType.toString()),
                    containsString(testPackageName), containsString(testMethodName), endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
            return;
        }

        // 
        fail("SandataRuntimeException is expected to be thrown, but it was not");
    }

    @Test
    public void testExecute_should_not_and_rollback_close_connection_when_connection_is_null() throws SQLException {
        // arrange

        when(this.connectionPoolDataServiceMock.getConnection(any(ConnectionType.class))).thenThrow(new RuntimeException("this is a fake exception"));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.execute(ConnectionType.METADATA, "testPackageName", "testMethodName", new Object());

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, never()).rollback();
            verify(this.connectionMock, never()).close();
            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not");
    }

    @Test
    public void testExecute_should_log_warning_message_if_exception_happens_when_closing_statement() throws SQLException {
        // arrange
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.getObject(1)).thenReturn(Integer.MIN_VALUE);
        doThrow(new SQLException(fakeExceptionMessage)).when(this.callableStatementMock).close();

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        // act
        oracleDataService.execute(ConnectionType.METADATA, "testPackageName", "testMethodName", new Object());

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("Error happened when closing statement:"), endsWith(fakeExceptionMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.WARN));
    }

    @Test
    public void testExecute_should_log_warning_message_if_exception_happens_when_closing_connection() throws SQLException {
        // arrange
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.getObject(1)).thenReturn(Integer.MIN_VALUE);
        doThrow(new SQLException(fakeExceptionMessage)).when(this.connectionMock).close();

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        // act
        oracleDataService.execute(ConnectionType.METADATA, "testPackageName", "testMethodName", new Object());

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("Error happened when closing connection:"), endsWith(fakeExceptionMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.WARN));
    }

    @Test
    public void testExecute_should_log_warning_message_if_exception_happens_when_rollingback_connection() throws SQLException {
        // arrange
        String fakeExceptionMessage = "this is a fake exception when rolling back connection";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.execute()).thenThrow(new SQLException("fake exception when executing db call"));
        doThrow(new SQLException(fakeExceptionMessage)).when(this.connectionMock).rollback();

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.execute(ConnectionType.METADATA, "testPackageName", "testMethodName", new Object());

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getAllValues().get(0).getRenderedMessage(),
                    allOf(containsString("Error happened when rollback connection:"), endsWith(fakeExceptionMessage)));
            assertThat(loggingEventCaptor.getAllValues().get(0).getLevel(), equalTo(Level.WARN));
            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not");
    }

    @Test
    public void testExecuteGet_should_call_correct_Oracle_package_and_method() throws SQLException {
        // arrange
        String testPackageName = "testPackageName";
        String testMethodName = "testMethodName";
        String testClassName = "java.lang.Object";
        String testEntityId = "testEntityId";
        ConnectionType testConnectionType = ConnectionType.COREDATA;

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(").append(testConnectionType).append(")");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareCall()");
        expectedCallOrder.append("-callableStatement#execute()");
        expectedCallOrder.append("-connection#commit()");

        String expectedPrepareCall = String.format("{?=call %s.%s.%s(?)}", testConnectionType, testPackageName, testMethodName);

        when(this.callableStatementMock.getObject(1)).thenReturn(this.resultSetMock);

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        Object actualResult = oracleDataService.executeGet(testConnectionType, testPackageName, testMethodName, testClassName, testEntityId);

        // assert
        verify(this.connectionMock, times(1)).prepareCall(expectedPrepareCall);
        verify(this.callableStatementMock, times(1)).registerOutParameter(1, OracleTypes.CURSOR);
        verify(this.callableStatementMock, times(1)).setString(2, testEntityId);
        assertThat(actualResult, instanceOf(List.class));
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.callableStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testExecuteGet_should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_executing_the_database_call() throws SQLException {
        // arrange
        String testPackageName = "testPackageName";
        String testMethodName = "testMethodName";
        String testClassName = "java.lang.Object";
        String testEntityId = "testEntityId";
        ConnectionType testConnectionType = ConnectionType.COREDATA;
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.execute()).thenThrow(new SQLException(fakeExceptionMessage));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.executeGet(testConnectionType, testPackageName, testMethodName, testClassName, testEntityId);

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(containsString(testConnectionType.toString()),
                    containsString(testPackageName), containsString(testMethodName), containsString(testClassName),
                    containsString(testEntityId), endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
            return;
        }

        // 
        fail("SandataRuntimeException is expected to be thrown, but it was not");
    }

    @Test
    public void testExecuteGet_should_log_warning_message_if_exception_happens_when_closing_resultset() throws SQLException {
        // arrange
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.callableStatementMock.getObject(1)).thenReturn(this.resultSetMock);
        doThrow(new SQLException(fakeExceptionMessage)).when(this.resultSetMock).close();

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        // act
        oracleDataService.executeGet(ConnectionType.METADATA, "testPackageName", "testMethodName", "java.lang.Object", "testEntityId");

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("Error happened when closing result set:"), endsWith(fakeExceptionMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.WARN));
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_return_record_in_map_when_having_data() throws SQLException {
        // arrange
        Integer testPatientPayerSk = Integer.valueOf(123);
        ResultSetMetaData resultSetMetaDataMock = mock(ResultSetMetaData.class);

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection()");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeQuery()");
        expectedCallOrder.append("-connection#commit()");

        String expectedColumnName = "PT_FIRST_NAME";
        Object expectedColumnValue = "test first name";

        Map<String, Object> expectedResult = new HashMap<String, Object>();
        expectedResult.put(expectedColumnName, expectedColumnValue);

        when(this.resultSetMock.next()).thenReturn(true);
        when(this.resultSetMock.getMetaData()).thenReturn(resultSetMetaDataMock);
        when(this.resultSetMock.getObject(expectedColumnName)).thenReturn(expectedColumnValue);

        when(resultSetMetaDataMock.getColumnCount()).thenReturn(1);
        when(resultSetMetaDataMock.getColumnName(1)).thenReturn(expectedColumnName);

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        Map<String, Object> actualResult = oracleDataService.getEligibilityInquiryByPatientPayerSk(testPatientPayerSk);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement(startsWith("SELECT DISTINCT   PT.PT_FIRST_NAME, PT.PT_MIDDLE_NAME, PT.PT_LAST_NAME, PT.PT_GENDER_TYP_NAME, PT.PT_DOB, PT.PT_TIN, PT.PT_ID, PT_MEDICAID_ID"));
        verify(this.prepareStatementMock, times(1)).setInt(1, testPatientPayerSk);
        assertThat(actualResult, equalTo(expectedResult));
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_return_null_when_having_no_data() throws SQLException {
        // arrange
        Integer testPatientPayerSk = Integer.valueOf(123);

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection()");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeQuery()");
        expectedCallOrder.append("-connection#commit()");

        when(this.resultSetMock.next()).thenReturn(false);

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        Map<String, Object> actualResult = oracleDataService.getEligibilityInquiryByPatientPayerSk(testPatientPayerSk);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement(startsWith("SELECT DISTINCT   PT.PT_FIRST_NAME, PT.PT_MIDDLE_NAME, PT.PT_LAST_NAME, PT.PT_GENDER_TYP_NAME, PT.PT_DOB, PT.PT_TIN, PT.PT_ID, PT_MEDICAID_ID"));
        verify(this.prepareStatementMock, times(1)).setInt(1, testPatientPayerSk);
        assertThat(actualResult, nullValue());
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_executing_query() throws SQLException {
        // arrange
        Integer testPatientPayerSk = Integer.valueOf(123);
        String fakeExceptionMessage = "this a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.prepareStatementMock.executeQuery()).thenThrow(new SQLException(fakeExceptionMessage));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.getEligibilityInquiryByPatientPayerSk(testPatientPayerSk);

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(containsString("An error happened when executing the SQL"),
                    containsString(testPatientPayerSk.toString()),
                    containsString("getEligibilityInquiryByPatientPayerSk"), endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not.");
    }

    @Test
    public void testGetAppTenantKeyConfigurationByAppTenantSkAndKeyName_should_return_ApplicationTenantKeyConfiguration_when_having_data() throws SQLException {
        // arrange
        long testApplicationTenantSk = 456;
        String testKeyName = "testKeyName";
        
        final BigDecimal expectedAppTenantKeyConfSk = BigDecimal.TEN;
        final String expectedTenantKeyConfVal = "test config value";
        
        final List<String> records = new ArrayList<String>();
        records.add("first");
        
        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(METADATA)");
        expectedCallOrder.append("-connection#setAutoCommit(true)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeQuery()");

        when(this.resultSetMock.next()).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (!records.isEmpty()) {
                    records.remove(0);
                    return true;
                } else {
                    return false;
                }
            }
        });

        when(this.resultSetMock.getObject(Matchers.anyInt())).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                int index = invocation.getArgumentAt(0, Integer.class);
                switch (index) {
                    case 1:
                        return expectedAppTenantKeyConfSk;

                    case 7:
                        return expectedTenantKeyConfVal;

                    default:
                        return null;
                }
            }
        });

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        ApplicationTenantKeyConfiguration actualResult = oracleDataService.getAppTenantKeyConfigurationByAppTenantSkAndKeyName(testApplicationTenantSk, testKeyName);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement("SELECT * FROM METADATA.APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ? AND KEY_NAME = ?");
        verify(this.prepareStatementMock, times(1)).setLong(1, testApplicationTenantSk);
        verify(this.prepareStatementMock, times(1)).setString(2, testKeyName);
        assertThat(actualResult.getApplicationTenantKeyConfigurationSK(), equalTo(BigInteger.valueOf(expectedAppTenantKeyConfSk.intValue())));
        assertThat(actualResult.getTenantKeyConfigurationValue(), equalTo(expectedTenantKeyConfVal));
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testGetAppTenantKeyConfigurationByAppTenantSkAndKeyName_should_return_null_when_having_no_data() throws SQLException {
        // arrange
        long testApplicationTenantSk = 456;
        String testKeyName = "testKeyName";

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(METADATA)");
        expectedCallOrder.append("-connection#setAutoCommit(true)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeQuery()");

        when(this.resultSetMock.next()).thenReturn(false);

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        ApplicationTenantKeyConfiguration actualResult = oracleDataService.getAppTenantKeyConfigurationByAppTenantSkAndKeyName(testApplicationTenantSk, testKeyName);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement("SELECT * FROM METADATA.APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ? AND KEY_NAME = ?");
        verify(this.prepareStatementMock, times(1)).setLong(1, testApplicationTenantSk);
        verify(this.prepareStatementMock, times(1)).setString(2, testKeyName);
        assertThat(actualResult, nullValue());
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testGetAppTenantKeyConfigurationByAppTenantSkAndKeyName__should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_executing_query() throws SQLException {
        // arrange
        long testApplicationTenantSk = 456;
        String testKeyName = "testKeyName";
        String fakeExceptionMessage = "this is a fake exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.prepareStatementMock.executeQuery()).thenThrow(new SQLException(fakeExceptionMessage));
        
        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.getAppTenantKeyConfigurationByAppTenantSkAndKeyName(testApplicationTenantSk, testKeyName);

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(
                    containsString(
                            "Error happened when getting ApplicationTenantKeyConfiguration for ApplicationTenant SK"),
                    containsString(String.valueOf(testApplicationTenantSk)),
                    containsString("getAppTenantKeyConfigurationByAppTenantSkAndKeyName"), containsString(testKeyName),
                    endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));

            // assertion for safe close resource
            verify(this.prepareStatementMock, times(1)).close();
            verify(this.connectionMock, times(1)).close();
            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not.");
    }

    @Test
    public void getEntitiesById_should_return_object_in_specified_class_name_when_having_data() throws SQLException {
        // arrange

        ConnectionType testConnectionType = ConnectionType.COREDATA;
        String testSql = "testSql";
        String testClassName = "com.sandata.lab.data.model.dl.model.Eligibility";
        Object testParam1 = new Object();
        Object testParam2 = "testParam2";

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(").append(testConnectionType).append(")");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeQuery()");
        expectedCallOrder.append("-connection#commit()");

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        Object actualResult = oracleDataService.getEntitiesById(testConnectionType, testSql, testClassName, testParam1, testParam2);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement(testSql);
        verify(this.prepareStatementMock, times(1)).setObject(1, testParam1);
        verify(this.prepareStatementMock, times(1)).setObject(2, testParam2);
        assertThat(actualResult, instanceOf(List.class));
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.resultSetMock, times(1)).close();
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void getEntitiesById_should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_executing_query() throws SQLException {
        ConnectionType testConnectionType = ConnectionType.COREDATA;
        String testSql = "testSql";
        String testClassName = "com.sandata.lab.data.model.dl.model.Eligibility";
        Object testParam = new Object();
        String fakeExceptionMessage = "this is a fake Exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.prepareStatementMock.executeQuery()).thenThrow(new SQLException(fakeExceptionMessage));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.getEntitiesById(testConnectionType, testSql, testClassName, testParam);

        } catch(SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(
                    containsString("Error happened when getting entities by id"),
                    containsString(testConnectionType.toString()), containsString(testSql),
                    containsString(testClassName), endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));

            // assertion for safe close resource
            verify(this.prepareStatementMock, times(1)).close();
            verify(this.connectionMock, times(1)).close();

            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not.");
    }

    public void testUpdateEligibilityStatus_should_throw_IllegalArgumentException_when_eligibility_is_null() {
        // arrange
        OracleDataService oracleDataService = new OracleDataService();
        
        try {
            // act
            oracleDataService.updateEligibilityStatus(null);

        } catch(IllegalArgumentException e) {
            // assert
            assertThat(e.getMessage(), containsString("eligibility must not be null"));
            return;
        }

        fail("IllegalArgumentException is expected to be thrown, but it was not.");
    }

    @Test
    public void testUpdateEligibilityStatus_should_successfully_update_eligibility_status() throws SQLException {
        // arrange
        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(BigInteger.TEN);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.ACTIVE);

        StringBuilder expectedCallOrder = new StringBuilder();
        expectedCallOrder.append("-connectionPoolDataService#getConnection(").append(ConnectionType.COREDATA).append(")");
        expectedCallOrder.append("-connection#setAutoCommit(false)");
        expectedCallOrder.append("-connection#prepareStatement()");
        expectedCallOrder.append("-preparedStatement#executeUpdate()");
        expectedCallOrder.append("-connection#commit()");

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        this.actualCallOrder.setLength(0);

        // act
        oracleDataService.updateEligibilityStatus(testEligibility);

        // assert
        verify(this.connectionMock, times(1)).prepareStatement("UPDATE COREDATA.ELIG SET ELIG_STATUS_NAME = ? WHERE ELIG_SK = ?");
        verify(this.prepareStatementMock, times(1)).setString(1, testEligibility.getEligibilityStatusName().toString());
        verify(this.prepareStatementMock, times(1)).setObject(2, testEligibility.getEligibilitySK(), OracleTypes.NUMBER);
        assertThat(actualCallOrder.toString(), equalTo(expectedCallOrder.toString()));

        // assertion for safe close resource
        verify(this.prepareStatementMock, times(1)).close();
        verify(this.connectionMock, times(1)).close();
    }

    @Test
    public void testUpdateEligibilityStatus_should_rollback_log_error_and_throw_SandataRuntimeException_if_exception_happens_when_updating_status() throws SQLException {
        // arrange
        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(BigInteger.ONE);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.INACTIVE);

        String fakeExceptionMessage = "this is a fake Exception";

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.prepareStatementMock.executeUpdate()).thenThrow(new SQLException(fakeExceptionMessage));

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(this.connectionPoolDataServiceMock);

        try {
            // act
            oracleDataService.updateEligibilityStatus(testEligibility);

        } catch (SandataRuntimeException e) {
            // assert
            verify(this.connectionMock, times(0)).commit();
            verify(this.connectionMock, times(1)).rollback();

            // assertion for exception
            Matcher<String> errorMessageMacher = allOf(
                    containsString("Error happened when updating Eligibility status"),
                    containsString(testEligibility.getEligibilitySK().toString()),
                    containsString(testEligibility.getEligibilityStatusName().toString()),
                    endsWith(fakeExceptionMessage));
            assertThat(e.getMessage(), errorMessageMacher);

            // assertion for logging
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(), errorMessageMacher);
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));

            // assertion for safe close resource
            verify(this.prepareStatementMock, times(1)).close();
            verify(this.connectionMock, times(1)).close();

            return;
        }

        fail("SandataRuntimeException is expected to be thrown, but it was not.");
    }
}
