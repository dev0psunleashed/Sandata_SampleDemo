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

package com.sandata.lab.wcf.intake;

import com.sandata.lab.common.utils.app.AppComponent;
import com.sandata.lab.data.model.schedule.Schedule;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.After;
import org.junit.Before;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Abstract base class that has common test properties and methods.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class BaseTestSupport extends CamelBlueprintTestSupport {

    public String username = "JUnit_Test_Case";

    protected MockEndpoint resultEndpoint;

    protected Exchange exchange;

    protected List<Schedule> schedules;

    protected void onSetup() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

        schedules = new ArrayList<>();

        Schedule schedule = new Schedule();

        schedule.setStaffId(96);
        schedule.setPatientId(106);

        Date startDate = dateFormat.parse("08-03-2015");
        schedule.setStartDate(startDate);

        Date endDate = dateFormat.parse("08-04-2015");
        schedule.setEndDate(endDate);

        schedule.setFromTime("12:00");
        schedule.setEndTime("16:00");

        schedule.setRestrictions("None");

        schedule.setFrequencyId(2);
        schedule.setNumberOfOccurrences(10);
        schedule.setDayOfMonth(5);
        schedule.setScheduleWeekDays(new ArrayList<Integer>(){{add(1);add(2);add(3);}});

        schedules.add(schedule);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

        resultEndpoint = new MockEndpoint("mock:result", new AppComponent());
        exchange = new DefaultExchange(resultEndpoint);

        // Default Pattern
        exchange.setPattern(ExchangePattern.InOut);

        Message in = new DefaultMessage();
        exchange.setIn(in);

        onSetup();
    }
}
