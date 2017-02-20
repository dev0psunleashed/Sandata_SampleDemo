/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.wcf.lookup.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.wcf.lookup.utils.constants.App;

/**
 * Camel route to handle cxf restful service endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class LookupTablesRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_RELIGIONS.toString())
                .routeId(App.ID.REST_LOOKUP_RELIGIONS.toString())
                .beanRef("restLookupTablesService", "getReligions")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_STAFF_TYPES.toString())
                .routeId(App.ID.REST_LOOKUP_STAFF_TYPES.toString())
                .beanRef("restLookupTablesService", "getStaffTypes")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_ETHNICITIES.toString())
                .routeId(App.ID.REST_LOOKUP_ETHNICITIES.toString())
                .beanRef("restLookupTablesService", "getEthnicities")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_MARITAL_STATUSES.toString())
                .routeId(App.ID.REST_LOOKUP_MARITAL_STATUSES.toString())
                .beanRef("restLookupTablesService", "getMaritalStatuses")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_GENDERS.toString())
                .routeId(App.ID.REST_LOOKUP_GENDERS.toString())
                .beanRef("restLookupTablesService", "getGenders")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_MED_CLASSIFICATIONS.toString())
                .routeId(App.ID.REST_LOOKUP_MED_CLASSIFICATIONS.toString())
                .beanRef("restLookupTablesService", "getMedClassifications")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_MED_STRENGTHS.toString())
                .routeId(App.ID.REST_LOOKUP_MED_STRENGTHS.toString())
                .beanRef("restLookupTablesService", "getMedStrengths")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_MED_ROUTES.toString())
                .routeId(App.ID.REST_LOOKUP_MED_ROUTES.toString())
                .beanRef("restLookupTablesService", "getMedRoutes")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_STATES.toString())
                .routeId(App.ID.REST_LOOKUP_STATES.toString())
                .beanRef("restLookupTablesService", "getStates")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_LANGUAGES.toString())
                .routeId(App.ID.REST_LOOKUP_LANGUAGES.toString())
                .beanRef("restLookupTablesService", "getLanguages")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_SERVICE_CODES.toString())
                .routeId(App.ID.REST_LOOKUP_SERVICE_CODES.toString())
                .beanRef("restLookupTablesService", "getServices")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_DISASTER_LEVELS.toString())
                .routeId(App.ID.REST_LOOKUP_DISASTER_LEVELS.toString())
                .beanRef("restLookupTablesService", "getDisasterLevels")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_DNRS.toString())
                .routeId(App.ID.REST_LOOKUP_DNRS.toString())
                .beanRef("restLookupTablesService", "getDnrs")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_REF_FORMATS.toString())
                .routeId(App.ID.REST_LOOKUP_REF_FORMATS.toString())
                .beanRef("restLookupTablesService", "getRefFormats")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_LIMIT_BYS.toString())
                .routeId(App.ID.REST_LOOKUP_LIMIT_BYS.toString())
                .beanRef("restLookupTablesService", "getLimitBys")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_ELIGIBILITIES.toString())
                .routeId(App.ID.REST_LOOKUP_ELIGIBILITIES.toString())
                .beanRef("restLookupTablesService", "getEligibilities")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_AGENCIES.toString())
                .routeId(App.ID.REST_LOOKUP_AGENCIES.toString())
                .beanRef("restLookupTablesService", "getAgencies")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_ADMIN_TYPES.toString())
                .routeId(App.ID.REST_LOOKUP_ADMIN_TYPES.toString())
                .beanRef("restLookupTablesService", "getAdminTypes")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_PAY_TYPES.toString())
                .routeId(App.ID.REST_LOOKUP_PAY_TYPES.toString())
                .beanRef("restLookupTablesService", "getPayTypes")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_STAFF_CLASSES.toString())
                .routeId(App.ID.REST_LOOKUP_STAFF_CLASSES.toString())
                .beanRef("restLookupTablesService", "getStaffClasses")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_STAFF_STATUSES.toString())
                .routeId(App.ID.REST_LOOKUP_STAFF_STATUSES.toString())
                .beanRef("restLookupTablesService", "getStaffStatuses")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_STATE_TAXES.toString())
                .routeId(App.ID.REST_LOOKUP_STATE_TAXES.toString())
                .beanRef("restLookupTablesService", "getStateTaxes")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_LOOKUP_CITY_TAXES.toString())
                .routeId(App.ID.REST_LOOKUP_CITY_TAXES.toString())
                .beanRef("restLookupTablesService", "getCityTaxes")
                .bean(FormatTransformer.class, "toResponse");
    }
}
