package com.sandata.lab.eligibility.data;

import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

/**
 * A testing concrete class to test the abstract class AbstractOracleDataSourceAdapter
 */
public class TestOracleDataSourceAdapter extends AbstractOracleDataSourceAdapter {
    public TestOracleDataSourceAdapter(ConnectionPoolDataService connectionPoolDataService) {
        super(connectionPoolDataService);
    }

    @Override
    protected ConnectionType getConnectionType() {
        return ConnectionType.METADATA;
    }
}
