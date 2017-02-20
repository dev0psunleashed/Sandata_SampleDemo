package com.sandata.lab.eligibility.data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

public class SandataOracleCoreDataSourceAdapterTest {

    private ConnectionPoolDataService connectionPoolDataServiceMock;

    @Before
    public void setupMocks() {
        this.connectionPoolDataServiceMock = mock(ConnectionPoolDataService.class);
    }

    @Test
    public void testSandataOracleCoreDataSourceAdapter_should_return_connection_to_coredata() throws SQLException {
        // arrange
        Connection expectedConnection = mock(Connection.class);
        when(this.connectionPoolDataServiceMock.getConnection(ConnectionType.COREDATA))
            .thenReturn(expectedConnection);
        
        SandataOracleCoreDataSourceAdapter sandataOracleCoreDataSourceAdapter = new SandataOracleCoreDataSourceAdapter(this.connectionPoolDataServiceMock);

        // act
        Connection actualConnection = sandataOracleCoreDataSourceAdapter.getConnection();
        
        // assert
        verify(this.connectionPoolDataServiceMock, times(1)).getConnection(eq(ConnectionType.COREDATA));
        assertThat(actualConnection, equalTo(expectedConnection));
    }
}
