package com.sandata.lab.eligibility.routes.dynamic;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ShutdownRoute;
import org.apache.camel.ShutdownRunningTask;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.eligibility.model.configuration.AgencyEligibilityConfiguration;
import com.sandata.lab.eligibility.processors.EligibilityInquiryProcessor;
import com.sandata.lab.eligibility.utils.RouteUtils;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * The route to create all inquiries for an agency by getting all PT_PAYER_SK for
 * active patients who has CIN#(PT.PT_MEDICAID_ID) and send PT_PAYER_SK to request handler endpoint
 * (activemq:queue:ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST_HANDLER)
 */
public class CreateEligibilityInquiryForAgencyRoute extends AbstractRouteBuilder {

    private long beSk;
    private String beId;
    private AgencyEligibilityConfiguration agencyEligibilityConfiguration;

    /**
     * Initializes an instance of {@link CreateEligibilityInquiryForAgencyRoute}
     * with parameters
     * 
     * @param beSk
     *            the Business Entity SK
     * @param beId
     *            the Business Entity ID to get active patients and send
     *            eligibility inquiries
     * @param agencyEligibilityConfiguration
     *            an instance of {@link AgencyEligibilityConfiguration} contains
     *            CRON schedule to build the route
     */
    public CreateEligibilityInquiryForAgencyRoute(long beSk, String beId, AgencyEligibilityConfiguration agencyEligibilityConfiguration) {
        this.beSk = beSk;
        this.beId = beId;
        this.agencyEligibilityConfiguration = agencyEligibilityConfiguration;
    }

    @Override
    protected void buildRoute() {
        from(getStartEndpoint())
            .routeId(getDynamicUniqueRouteId())
            .shutdownRoute(ShutdownRoute.Defer)
            // When shutting down, Camel will wait until the batch completed
            .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
            .setBody(constant(beId))
            
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfoForBeId(this, beId, getStartLogMessage()))
            
            // build and execute SQL to get all PT_PAYER_SK for active patients who has CIN#(PT.PT_MEDICAID_ID) for the agency
            .beanRef(EligibilityInquiryProcessor.BEAN_NAME, EligibilityInquiryProcessor.GET_SQL_TO_QUERY_ALL_PATIENT_PAYERS_BY_BE_ID_METHOD)
            .to(getCoreDataSourceEndpoint())
            
            // for each PT_PAYER_SK
            .split(body()).streaming()
                .setBody(simple("${body[PT_PAYER_SK]?.longValue}"))
                
                // forward PT_PAYER_SK to handler to create and send eligibility inquiry 270 JSON file
                .to(RouteUtils.getInquiryRequestHandlerEndpoint())
                
                // log complete message when complete
                .choice()
                    .when(exchangeProperty(Exchange.SPLIT_COMPLETE).isEqualTo(true))
                        .log(LoggingLevel.INFO,
                                LoggingUtils.getLogMessageInfoForBeId(this, beId,
                                        "Complete forwarding requests to create and send eligibility inquiry for ${exchangeProperty.CamelSplitSize} patient-payer(s)"))
                    .endChoice()
                .end()
            .end();
    }

    private String getStartEndpoint() {
        return new StringBuilder()
                // Quartz2 component
                .append(Messaging.Names.COMPONENT_TYPE_QUARTZ2)
                // group name
                .append(App.SEND_ELIGIBILITY_INQUIRY_QUARTZ2_GROUP_NAME)
                // timer name
                .append("/quartz_").append(getUniqueId())
                // CRON schedule option
                .append("?cron=").append(this.agencyEligibilityConfiguration.getSendInquiryCronSchedule())
                .toString();
    }

    private String getUniqueId() {
        return new StringBuilder()
                // use BeSk to build unique id as BE_ID is string which may contain special characters
                // that is not valid for Camel IDs
                .append(this.getClass().getSimpleName()).append("_BeSk_").append(this.beSk)
                .toString();
    }

    private String getDynamicUniqueRouteId() {
        return new StringBuilder()
                .append(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.DYNAMIC_ROUTE_PREFIX)
                .append(getUniqueId())
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

    private String getStartLogMessage() {
        return new StringBuilder()
                .append("Start forwarding requests to create and send eligibility inquiry at ")
                .append(App.DATE_NOW_IN_CAMEL_LOGGING)
                .append(", sendInquiryCronSchedule=").append(agencyEligibilityConfiguration.getSendInquiryCronSchedule())
                .toString();
    }
}
