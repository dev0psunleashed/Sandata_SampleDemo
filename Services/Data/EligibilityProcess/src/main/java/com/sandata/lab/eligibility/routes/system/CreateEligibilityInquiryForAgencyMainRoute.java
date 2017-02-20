package com.sandata.lab.eligibility.routes.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.ShutdownRoute;
import org.apache.camel.ShutdownRunningTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.eligibility.routes.dynamic.DynamicCreateEligibilityInquiryForAgencyRouteBuilder;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to dynamically create other routes for creating eligibility inquiries for agencies
 * based on agencies' configuration in MetaData database
 */
public class CreateEligibilityInquiryForAgencyMainRoute extends AbstractRouteBuilder {
    
    private static final Logger LOG = LoggerFactory.getLogger(CreateEligibilityInquiryForAgencyMainRoute.class);

    private static final String SELECT_ALL_AGENCIES_SQL = "SELECT * FROM COREDATA.BE WHERE TO_CHAR(BE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE.CURR_REC_IND = '1'";

    @PropertyInject("{{send.eligibility.inquiry.all.agencies.route.first.fire}}")
    protected String masterRouteScheduleTime = "00:00:00";
    
    public static final String START_IMMEDIATELY_ROUTE_ID = "CreateEligibilityInquiryForAgencyStartImmediatelyRoute";
    public static final String FREQUENCY_ROUTE_ID = "CreateEligibilityInquiryForAgencyFrequencyRoute";
    
    @Override
    protected void buildRoute() {

        onException(SandataRuntimeException.class)
            .handled(true)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "An exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"));

        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        // create timer route to read configuration then create dynamic routes at the bundle installed
        from(getStartImmediatelyEndpoint())
                .routeId(START_IMMEDIATELY_ROUTE_ID)
                .shutdownRoute(ShutdownRoute.Defer)
                .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
                .onException(Exception.class)
                    .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this.getClass().getSimpleName(), "StartTimerRoute",
                            "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace}"))
                    // start quart route
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            startFrequencyRoute(exchange);
                        }
                    })
                    .handled(true)
                    .stop()
                .end()
                .log(LoggingUtils.getLogMessageInfo(this, "[${routeId}] Start at ${date:now:MM/dd/yyyy HH:mm:ss.SSS}"))
                .to(getMainRouteEndpoint())
                // start quart route to schedule creating dynamic routes by cron trigger
                .process(new Processor() {
                    
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        startFrequencyRoute(exchange);
                    }
                })
                .log(LoggingUtils.getLogMessageInfo(this, "[${routeId}] End at ${date:now:MM/dd/yyyy HH:mm:ss.SSS}"));
        

        from(getFrequencyEndpoint())
            .routeId(FREQUENCY_ROUTE_ID)
            .noAutoStartup()
            .to(getMainRouteEndpoint());

        // dynamically create other routes for creating eligibility inquiries for agencies based on agencies' configuration in MetaData database
        from(getMainRouteEndpoint())
            .routeId(this.getClass().getSimpleName())
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, getStartLogMessage()))
             // shutdown all dynamic routes before creating new ones
            .bean(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.class, DynamicCreateEligibilityInquiryForAgencyRouteBuilder.REMOVE_ALL_DYNAMIC_ROUTES_METHOD)
            
            // execute SQL to query all agencies from DB
            .setBody(simple(SELECT_ALL_AGENCIES_SQL))
            .to(getCoreDataSourceEndpoint())
            
            // for each agency
            .split(body()).streaming()
                // dynamically create routes for an agency which create eligibility inquiries for active patients in the agency
                .bean(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.class, DynamicCreateEligibilityInquiryForAgencyRouteBuilder.CREATE_ROUTES_METHOD)
                
                // log complete message when complete
                .choice()
                    .when(exchangeProperty(Exchange.SPLIT_COMPLETE).isEqualTo(true))
                        .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Complete dynamically creating all dynamic routes for creating eligibility inquiries for ${exchangeProperty.CamelSplitSize} agencies"))
                    .endChoice()
                .end()
            .end();
            
    }
    
    private String getStartImmediatelyEndpoint() {
        return "timer://" + START_IMMEDIATELY_ROUTE_ID + "?repeatCount=1";
    }

    private String getFrequencyEndpoint() {
        Calendar currentTimestamp = Calendar.getInstance();
        Calendar timeToRun = Calendar.getInstance();

        String[] hourMinSecond = masterRouteScheduleTime.split(":");
        timeToRun.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourMinSecond[0]));
        timeToRun.set(Calendar.MINUTE, Integer.parseInt(hourMinSecond[1]));
        timeToRun.set(Calendar.SECOND, Integer.parseInt(hourMinSecond[2]));

        if (currentTimestamp.getTimeInMillis() >= timeToRun.getTimeInMillis()) {
            // if current time is later then scheduled time, then schedule next 1 day to run
            timeToRun.add(Calendar.DAY_OF_MONTH, 1);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = sdf.format(timeToRun.getTime());
        
        return new StringBuilder()
                .append("timer://").append(this.getClass().getSimpleName())
                .append("?time=").append(time)
                .append("&pattern=dd-MM-yyyy HH:mm:ss")
                .append("&period={{send.eligibility.inquiry.all.agencies.route.frequency.period}}")
                .toString();
    }

    private String getCoreDataSourceEndpoint() {
        return new StringBuilder()
                .append("jdbc:coreDataSource")
                // StreamList: streams the result of the query using an Iterator<Map<String, Object>>
                // to use along with the Splitter
                .append("?outputType=StreamList")
                .toString();
    }
    
    private String getMainRouteEndpoint() {
        return "direct:" + this.getClass().getSimpleName();
    }

    private String getStartLogMessage() {
        return new StringBuilder()
                .append("Start dynamically creating all dynamic routes for creating eligibility inquiries for all agencies at ")
                .append(App.DATE_NOW_IN_CAMEL_LOGGING)
                .toString();
    }
    
    /**
     * Start a quartz route defined in com.sandata.up.services.samdataexporter.routes.SchedulerRoute
     * @param exchange
     */
    private void startFrequencyRoute(final Exchange exchange) {
        try {
            LOG.info(LoggingUtils.getLogMessageInfo(this, "Begin starting route {}"), FREQUENCY_ROUTE_ID);
            // start quartz route
            exchange.getContext().startRoute(FREQUENCY_ROUTE_ID);
            LOG.info(LoggingUtils.getLogMessageInfo(this, "Route {} was started successfully."), FREQUENCY_ROUTE_ID);
        } catch (Exception e) {
            LOG.error(LoggingUtils.getErrorMessageInfo(this.getClass().getSimpleName(), "startFrequencyRoute", "Cannot start route {}"),
                    FREQUENCY_ROUTE_ID, e);
        }
    }
}
