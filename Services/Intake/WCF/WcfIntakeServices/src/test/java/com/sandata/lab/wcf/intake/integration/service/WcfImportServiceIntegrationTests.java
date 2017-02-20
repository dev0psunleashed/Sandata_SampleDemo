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

package com.sandata.lab.wcf.intake.integration.service;

import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.intake.BaseTestSupport;
import com.sandata.lab.wcf.intake.impl.WcfIntakeService;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Implementation of the IntakeService interface integration with WCF Import.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class WcfImportServiceIntegrationTests extends BaseTestSupport {

    private WcfIntakeService service;

    @Test
    public void should_fail_import_schedules_to_wcf_endpoint() throws Exception {

        Assert.notNull(schedules);
        Assert.isTrue(schedules.size() > 0);

        Schedule schedule = schedules.get(0);

        String originalFromTime = schedule.getFromTime();

        // purposely corrupt FromTime so that request fails
        schedule.setFromTime("12");

        Response response = service.insertSchedules(schedules);

        Assert.notNull(response);

        Assert.isTrue(response.getStatus() == ServiceStatus.FAILED);
        Assert.isTrue(response.getSucceededCount() == 0);
        Assert.isTrue(response.getFailedCount() == schedules.size());

        Assert.notNull(response.getData());
        Assert.isTrue(response.getData() instanceof List);

        List errors = (List) response.getData();

        Assert.isTrue(errors.size() == 1);

        Object errObj = errors.get(0);

        Assert.isTrue(errObj instanceof com.sandata.lab.data.model.error.Error);

        // Set back to original FromTime
        schedule.setFromTime(originalFromTime);
    }

    @Test
    public void should_successfully_import_schedules_to_wcf_endpoint() throws Exception {

        Assert.notNull(schedules);
        Assert.isTrue(schedules.size() > 0);

        Response response = service.insertSchedules(schedules);

        Assert.notNull(response);
        Assert.isTrue(response.getStatus() == ServiceStatus.SUCCESS);
        Assert.isTrue(response.getSucceededCount() == schedules.size());
    }

    @Override
    protected void onSetup() throws Exception {

        super.onSetup();

        service = new WcfIntakeService();
        service.setHttpDataService(new HttpDataService());
    }
}
