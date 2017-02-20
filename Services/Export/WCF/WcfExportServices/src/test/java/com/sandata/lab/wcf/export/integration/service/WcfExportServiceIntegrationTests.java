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

package com.sandata.lab.wcf.export.integration.service;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.export.BaseTestSupport;
import com.sandata.lab.wcf.export.impl.WcfExportService;
import org.junit.Test;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Tests the exportSchedule service method end-to-end.
 *
 * NOTE: Integration tests package should be skipped during unit tests.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfExportServiceIntegrationTests extends BaseTestSupport {

    private WcfExportService wcfIntakeService;

    private DateFormat dateFormat;

    @Test
    public void should_successfully_export_schedules_for_the_given_staff_patient_and_date_range() throws Exception {

        Date startDate = dateFormat.parse("2015-08-05");
        Date endDate = dateFormat.parse("2015-08-06");

        Response response = wcfIntakeService.exportSchedule(92, 105, startDate, endDate);

        Assert.notNull(response);
        Assert.isTrue(response.getStatus() == ServiceStatus.SUCCESS);
        Assert.notNull(response.getData());

        Assert.isTrue(response.getData() instanceof List);

        List list = (List)response.getData();

        if (list.size() > 0) {
            Assert.isTrue(list.get(0) instanceof Schedule);
        }
    }


    @Override
    protected void onSetup() throws Exception {
        wcfIntakeService = new WcfExportService();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    }
}
