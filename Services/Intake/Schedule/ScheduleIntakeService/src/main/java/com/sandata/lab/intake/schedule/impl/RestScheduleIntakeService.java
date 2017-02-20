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

package com.sandata.lab.intake.schedule.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.intake.schedule.api.ScheduleIntakeService;
import com.sandata.lab.intake.schedule.utils.constants.App;
import com.sandata.lab.intake.schedule.utils.log.ScheduleIntakeLogger;
import com.sandata.lab.wcf.intake.api.IntakeService;
import org.apache.camel.Exchange;

import java.util.List;

/**
 * REST implementation of the ScheduleIntakeService interface.
 * <p/>
 *
 * @author David Rutgos
 */

@SuppressWarnings("unchecked")
public class RestScheduleIntakeService implements ScheduleIntakeService {

    private IntakeService intakeService;

    private CacheService cacheService;

    public void createUUID(Exchange exchange) {

        String uuid = cacheService.create(exchange.getIn().getBody());
        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
    }

    @Override
    public void insertSchedules(Exchange exchange) {

        SandataLogger logger = ScheduleIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("insertSchedules");

        logger.start();

        String uuid = (String) exchange.getIn().getHeader(App.ID.SCHEDULE_GUID.toString());

        final List<Schedule> scheduleList = exchange.getIn().getBody(List.class);

        cacheService.processing(scheduleList, uuid);

        Response response = intakeService.insertSchedules(scheduleList);

        cacheService.put(response, uuid);

        logger.stop();
    }

    public IntakeService getIntakeService() {
        return intakeService;
    }

    public void setIntakeService(IntakeService intakeService) {
        this.intakeService = intakeService;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
