package com.sandata.lab.dl.db.pool.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.dl.db.pool.test.utils.constants.App;

/**
 * A route that test the Sandata Connection Pool Data Service OSGi Endpoint.
 * <p/>
 *
 * @author David Rutgos
 */
public class TestConnPoolDataSrvRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_COREDATA_POOL_ROUTE.toString() +
                "?fixedRate=true&period=1000")
                .routeId(App.ID.TEST_COREDATA_POOL_ROUTE.toString())
                .beanRef("sandataConnectionPoolDataServiceTest", "testCoreDataConnection")
                .log("TestConnPoolDataSrvRoute: testCoreDataConnection: *** Complete ***");

        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_LOBDATA_POOL_ROUTE.toString() +
                "?fixedRate=true&period=1000")
                .routeId(App.ID.TEST_LOBDATA_POOL_ROUTE.toString())
                .beanRef("sandataConnectionPoolDataServiceTest", "testLobDataConnection")
                .log("TestConnPoolDataSrvRoute: testLobDataConnection: *** Complete ***");

        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_METADATA_POOL_ROUTE.toString() +
                "?fixedRate=true&period=1000")
                .routeId(App.ID.TEST_METADATA_POOL_ROUTE.toString())
                .beanRef("sandataConnectionPoolDataServiceTest", "testMetaDataConnection")
                .log("TestConnPoolDataSrvRoute: testMetaDataConnection: *** Complete ***");
    }
}
