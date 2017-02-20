package com.sandata.lab.eligibility.data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

public class AbstractOracleDataSourceAdapterTest {

    private ConnectionPoolDataService connectionPoolDataServiceMock;

    @Before
    public void setupMocks() {
        this.connectionPoolDataServiceMock = mock(ConnectionPoolDataService.class);
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_IllegalArgumentException_when_passing_null_to_constructor() {
        try {
            // act
            new TestOracleDataSourceAdapter(null);

        } catch(IllegalArgumentException e) {
            // assert
            assertThat(e.getMessage(), equalTo("connectionPoolDataService must not be null."));

            return;
        }

        fail("IllegalArgumentException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_getLogWriter() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.getLogWriter();

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("getLogWriter()"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_getLoginTimeout() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.getLoginTimeout();

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("getLoginTimeout()"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_setLogWriter() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.setLogWriter(mock(PrintWriter.class));

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("setLogWriter(PrintWriter arg0)"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_setLoginTimeout() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.setLoginTimeout(1);

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("setLoginTimeout(int arg0)"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_isWrapperFor() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.isWrapperFor(Object.class);

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("isWrapperFor(Class<?> arg0)"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_unwrap() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.unwrap(Object.class);

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("unwrap(Class<T> arg0)"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_throw_UnsupportedOperationException_when_calling_getconnection_two_params() throws SQLException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        try {
            // act
            testDataSource.getConnection("test string1", "test string 2");

        } catch(UnsupportedOperationException e) {
            // assert
            assertThat(e.getMessage(), equalTo("getConnection(String arg0, String arg1)"));

            return;
        }

        fail("UnsupportedOperationException is expected to be thrown, it was not.");
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_return_logger_when_calling_getParentLogger() throws SQLFeatureNotSupportedException {
        // arrange
        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        // act
        Logger actualLogger = testDataSource.getParentLogger();

        // assert
        assertThat(actualLogger, notNullValue());
    }

    @Test
    public void testAbstractOracleDataSourceAdapter_should_return_call_getConnection_from_ConnectionPoolDataServiceMock_when_getConnection() throws SQLException {
        // arrange
        Connection expectedConnection = mock(Connection.class);
        when(this.connectionPoolDataServiceMock.getConnection(ConnectionType.METADATA))
            .thenReturn(expectedConnection);

        AbstractOracleDataSourceAdapter testDataSource = new TestOracleDataSourceAdapter(this.connectionPoolDataServiceMock);

        // act
        Connection actualConnection = testDataSource.getConnection();

        // assert
        assertThat(actualConnection, equalTo(expectedConnection));
    }
}

