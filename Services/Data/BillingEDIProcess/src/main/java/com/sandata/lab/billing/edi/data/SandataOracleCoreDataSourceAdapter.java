package com.sandata.lab.billing.edi.data;

import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

/**
 * An adapter is used to get connection to CoreData database from
 * {@link ConnectionPoolDataService} for Camel JDBC component
 */
public class SandataOracleCoreDataSourceAdapter extends AbstractOracleDataSourceAdapter {

    public SandataOracleCoreDataSourceAdapter(ConnectionPoolDataService connectionPoolDataService) {
        super(connectionPoolDataService);
    }

    @Override
    protected ConnectionType getConnectionType() {
        return ConnectionType.COREDATA;
    }

}
