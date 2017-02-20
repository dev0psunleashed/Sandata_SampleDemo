package __GROUP_ID__.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import __GROUP_ID__.utils.constants.App;

/**
 * Date: 9/1/15
 * Time: 10:38 PM
 */

public class RestInsertRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        // TODO: Need to figure out why the UCP does not work if I remove one route from here.... WHICH IS NOT EVEN USED!!!????!!

        // INSERT
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_DUMMY_INSERT.toString())
                .routeId(App.ID.REST_DUMMY_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");
    }
}
