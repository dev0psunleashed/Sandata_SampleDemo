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

package com.sandata.lab.intake.staff.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.intake.staff.api.StaffIntakeService;
import com.sandata.lab.intake.staff.utils.constants.App;
import com.sandata.lab.intake.staff.utils.log.StaffIntakeLogger;
import com.sandata.lab.wcf.intake.api.IntakeService;
import org.apache.camel.Exchange;

import java.util.List;
import java.util.UUID;

/**
 * REST implementation of the EmployeeIntakeService interface.
 * <p/>
 *
 * @author David Rutgos
 */

public class RestStaffIntakeService implements StaffIntakeService {

    private IntakeService intakeService;
    private CacheService cacheService;

    public void createUUID(Exchange exchange) {

        String uuid = cacheService.create(exchange.getIn().getBody());
        exchange.getIn().setHeader(App.ID.STAFF_GUID.toString(), uuid);
    }

    @Override
    public void insertStaffMember(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("insertStaffMember");

        logger.start();

        String uuid = (String) exchange.getIn().getHeader(App.ID.STAFF_GUID.toString());

        final Staff staff = exchange.getIn().getBody(Staff.class);

        cacheService.processing(staff, uuid);

        Response response = intakeService.insertStaffMember(staff);

        // Complete
        cacheService.put(response, uuid);

        logger.stop();
    }

    @Override
    public void insertStaffMembers(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("insertStaffMembers");

        logger.start();

        String uuid = (String) exchange.getIn().getHeader(App.ID.STAFF_GUID.toString());

        final List<Staff> staffMembers = exchange.getIn().getBody(List.class);

        cacheService.processing(staffMembers, uuid);

        Response response = intakeService.insertStaffMembers(staffMembers);

        cacheService.put(response, uuid);

        logger.stop();
    }

    @Override
    public void updateStaffMember(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("updateStaffMember");

        logger.start();

        final Staff staff = exchange.getIn().getBody(Staff.class);

        // TODO: log guid and start processing Staff insert
        String guid = UUID.randomUUID().toString();

        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void updateStaffMembers(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("updateStaffMembers");

        logger.start();

        final List<Staff> staffMembers = exchange.getIn().getBody(List.class);

        // TODO: log guid and start processing Staff insert
        String guid = UUID.randomUUID().toString();
        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void deleteStaffMember(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("deleteStaffMember");

        logger.start();

        final Staff staff = exchange.getIn().getBody(Staff.class);

        // TODO: log guid and start processing Staff insert
        String guid = UUID.randomUUID().toString();

        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void deleteStaffMembers(Exchange exchange) {

        SandataLogger logger = StaffIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("deleteStaffMembers");

        logger.start();

        final List<Staff> staffMembers = exchange.getIn().getBody(List.class);

        // TODO: log guid and start processing Staff insert
        String guid = UUID.randomUUID().toString();
        exchange.getIn().setBody(guid);

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
