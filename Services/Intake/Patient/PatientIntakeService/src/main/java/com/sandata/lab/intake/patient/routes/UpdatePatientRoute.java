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

package com.sandata.lab.intake.patient.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.intake.patient.utils.constants.App;

/**
 * Route intercepts cxf update patient resource url.
 * <p/>
 *
 * @author David Rutgos
 */
public class UpdatePatientRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.UPDATE_PATIENT.toString())
                .routeId(App.ID.UPDATE_PATIENT.toString())
                .beanRef("restPatientIntakeService", "updatePatient")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.UPDATE_PATIENTS.toString())
                .routeId(App.ID.UPDATE_PATIENTS.toString())
                .beanRef("restPatientIntakeService", "updatePatients")
                .bean(FormatTransformer.class, "toResponse");
    }
}