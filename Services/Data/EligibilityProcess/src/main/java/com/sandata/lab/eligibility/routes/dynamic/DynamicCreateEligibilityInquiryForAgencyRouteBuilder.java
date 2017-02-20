package com.sandata.lab.eligibility.routes.dynamic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.eligibility.impl.OracleDataService;
import com.sandata.lab.eligibility.model.configuration.AgencyEligibilityConfiguration;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

public class DynamicCreateEligibilityInquiryForAgencyRouteBuilder implements CamelContextAware {

    public static final String DYNAMIC_ROUTE_PREFIX = "_dynamic_";

    /**
     * Method {@link #removeAllDynamicRoutes() is used in Camel routes
     */
    public static final String REMOVE_ALL_DYNAMIC_ROUTES_METHOD = "removeAllDynamicRoutes";

    /**
     * Method {@link #createRoutes(Exchange) is used in Camel routes
     */
    public static final String CREATE_ROUTES_METHOD = "createRoutes";

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.class);

    /**
     * column name BE_ID
     */
    private static final String BE_ID = "BE_ID";

    private CamelContext camelContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public CamelContext getCamelContext() {
        return this.camelContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    /**
     * Creates a route for an agency (business entity) to create and send
     * eligibility inquiry for all active patients in the agency
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains a
     *            Map<String, Object> for a business entity record
     */
    public final void createRoutes(final Exchange exchange) {
        @SuppressWarnings("unchecked")
        Map<String, Object> be = exchange.getIn().getBody(Map.class);
        long beSk = ((BigDecimal) be.get("BE_SK")).longValue();
        String beId = (String) be.get(BE_ID);

        BigInteger appTenantSk = getAppTenantSk(beId);
        if (appTenantSk == null) {
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, CREATE_ROUTES_METHOD,
                            "Not create dynamic route for {}={} as no record found in the APP_TENANT_BE_XWALK table for this BE_ID"),
                    new Object[] { BE_ID, beId });
            return;
        }

        ApplicationTenantKeyConfiguration appTenantKeyConfig = getAppTenantKeyConfigurationForEligibility(appTenantSk);
        if (appTenantKeyConfig == null) {
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, CREATE_ROUTES_METHOD,
                            "Not create dynamic route for {}={} as KEY_NAME={} not found in the APP_TENANT_KEY_CONF table for APP_TENANT_SK={}"),
                    new Object[] { BE_ID, beId, App.ELIGIBILITY_PROCESS_CONF_KEY_NAME, appTenantSk });
            return;
        }

        AgencyEligibilityConfiguration eligConfig = (AgencyEligibilityConfiguration) GSONProvider
                .FromJson(appTenantKeyConfig.getTenantKeyConfigurationValue(), AgencyEligibilityConfiguration.class);

        if (eligConfig == null) {
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, CREATE_ROUTES_METHOD,
                            "Not create dynamic route for {}={} as TENANT_KEY_CONF_VAL is null or empty or invalid JSON for KEY_NAME={}, APP_TENANT_SK={}"),
                    new Object[] { BE_ID, beId, App.ELIGIBILITY_PROCESS_CONF_KEY_NAME, appTenantSk });
            return;
        }

        if (StringUtils.isEmpty(eligConfig.getSendInquiryCronSchedule())) {
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, CREATE_ROUTES_METHOD,
                            "Not create dynamic route for {}={} as sendInquiryCronSchedule (in JSON value of KEY_NAME={}, APP_TENANT_SK={}) is null or empty"),
                    new Object[] { BE_ID, beId, App.ELIGIBILITY_PROCESS_CONF_KEY_NAME, appTenantSk });
            return;
        }

        CreateEligibilityInquiryForAgencyRoute route = new CreateEligibilityInquiryForAgencyRoute(beSk, beId, eligConfig);
        try {
            camelContext.addRoutes(route);
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, CREATE_ROUTES_METHOD,
                            "Created dynamic route for {}={}, sendInquiryCronSchedule={}"),
                    new Object[] { BE_ID, beId, eligConfig.getSendInquiryCronSchedule() });
        } catch (Exception e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, CREATE_ROUTES_METHOD,
                    String.format("Error happened when adding route to CamelContext for %s=%s, error: %s", BE_ID, beId,
                            e.getMessage()));
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e.getCause());
        }
    }

    /**
     * Try to gracefully shutdown all dynamically created routes
     */
    public void removeAllDynamicRoutes() {
        List<Route> routes = camelContext.getRoutes();
        String routeId = null;

        for (Route route : routes) {
            try {
                routeId = route.getId();
                if (routeId.startsWith(DYNAMIC_ROUTE_PREFIX)) {
                    LOGGER.info(LoggingUtils.getLogMessageInfo(this, REMOVE_ALL_DYNAMIC_ROUTES_METHOD, "Shutting down route id: '{}'"), routeId);
                    camelContext.stopRoute(routeId);
                    camelContext.removeRoute(routeId);
                    LOGGER.info(LoggingUtils.getLogMessageInfo(this, REMOVE_ALL_DYNAMIC_ROUTES_METHOD, "Completed shutting down route id: '{}'"), routeId);
                }
            } catch (Exception e) {
                StringBuilder errorMessageBuider = new StringBuilder("Error when shutting down route id: ")
                        .append("'").append(routeId).append("'");
                String errorMessage = LoggingUtils.getErrorMessageInfo(this, REMOVE_ALL_DYNAMIC_ROUTES_METHOD, errorMessageBuider.toString());
                LOGGER.error(errorMessage, e);
                throw new SandataRuntimeException(errorMessage, e.getCause());
            }
        }
    }

    private BigInteger getAppTenantSk(String beId) {
        ApplicationTenantBusinessEntityCrosswalk appTenantBeXwalk = new ApplicationTenantBusinessEntityCrosswalk();
        OracleMetadata oracleMetadata = com.sandata.lab.common.utils.data.DataMapper
                .getOracleMetadata(appTenantBeXwalk);

        @SuppressWarnings("unchecked")
        List<ApplicationTenantBusinessEntityCrosswalk> appTenantBeXwalks = (List<ApplicationTenantBusinessEntityCrosswalk>) getOracleDataService()
                .executeGet(ConnectionType.METADATA, oracleMetadata.packageName(), oracleMetadata.getMethod(),
                        "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk", beId);

        if (!appTenantBeXwalks.isEmpty()) {
            return appTenantBeXwalks.get(0).getApplicationTenantSK();
        }

        return null;
    }

    private ApplicationTenantKeyConfiguration getAppTenantKeyConfigurationForEligibility(BigInteger appTenantSk) {
        return getOracleDataService().getAppTenantKeyConfigurationByAppTenantSkAndKeyName(appTenantSk.longValue(), App.ELIGIBILITY_PROCESS_CONF_KEY_NAME);
    }

    private OracleDataService getOracleDataService() {
        return camelContext.getRegistry().lookupByNameAndType("oracleDataService", OracleDataService.class);
    }
}
