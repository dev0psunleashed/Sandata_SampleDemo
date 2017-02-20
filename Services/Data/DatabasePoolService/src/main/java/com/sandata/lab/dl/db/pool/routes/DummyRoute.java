package com.sandata.lab.dl.db.pool.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.dl.db.pool.utils.constants.App;

/**
 * Date: 9/1/15
 * Time: 10:38 PM
 */

public class DummyRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        // TODO: Need to figure out why the UCP does not work if I remove one route from here.... WHICH IS NOT EVEN USED!!!????!!

        // DUMMY ...
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_DUMMY_ENDPOINT.toString())
                .routeId(App.ID.REST_DUMMY_ENDPOINT.toString())
                .beanRef("sandataConnectionPoolDataService", "getConnection");
    }
}
