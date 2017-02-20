#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import ${package}.utils.constants.App;
import ${package}.utils.constants.Messaging;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

public class ExampleFTPRoute extends AbstractRouteBuilder
{

    private String sourcePath = "file:target/data";
    private String ftpChron = "*/3 * * * * ?";

    @Override
    protected final void buildRoute()
    {
        CronScheduledRoutePolicy FTPRoutePolicy = new CronScheduledRoutePolicy();
        staffImportFTPRoutePolicy.setRouteStartTime(ftpChron);

        from(sourcePath + "&idempotentRepository=${symbol_pound}idempotentFileStore&idempotent=true&consumer.delay=10s&idempotentKey=${symbol_dollar}{file:modified}&readLock=changed")
                .routeId(App.ID.Example.toString()).routePolicy(FTPRoutePolicy)
                .onException(Exception.class)
                    .asyncDelayedRedelivery()
                .end()
                .log("${symbol_dollar}{body}")
                .log(App.ID.Example.toString() + ": *** Route completed successfully ***");
    }
}
