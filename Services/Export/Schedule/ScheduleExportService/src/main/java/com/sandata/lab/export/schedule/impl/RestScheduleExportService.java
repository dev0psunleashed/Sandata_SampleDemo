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

package com.sandata.lab.export.schedule.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.export.schedule.api.ScheduleExportService;
import com.sandata.lab.export.schedule.utils.constants.App;
import com.sandata.lab.export.schedule.utils.log.ExportScheduleLogger;
import com.sandata.lab.wcf.export.api.ExportService;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * REST implementation of the EmployeeIntakeService interface.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class RestScheduleExportService implements ScheduleExportService {

    private ExportService exportService;
    private CacheService cacheService;

    public void createUUID(Exchange exchange) {

        String uuid = cacheService.create(exchange.getIn().getBody());
        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
    }

    @Override
    public void exportSchedule(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = ExportScheduleLogger.CreateLogger(exchange);
        logger.setMethodName("exportSchedule(Exchange)");

        logger.start();

        final MessageContentsList params = exchange.getIn().getBody(MessageContentsList.class);

        String uuid = (String) exchange.getIn().getHeader(App.ID.SCHEDULE_GUID.toString());

        validateParams(params, uuid);

        try {

            cacheService.processing(params, uuid);

            Integer staffId = (Integer) params.get(0);
            Integer patientId = (Integer) params.get(1);
            String startDateStr = (String) params.get(2);
            String endDateStr = (String) params.get(3);

            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            Response response = exportService.exportSchedule(staffId, patientId, startDate, endDate);

            // Complete
            cacheService.put(response, uuid);
        }
        catch (Exception e) {
            String errMsg = String.format("%s: %s: Exception: %s",
                    getClass().getName(), e.getClass().getName(), e.getMessage());

            logger.error(errMsg);

            throw new SandataRuntimeException(errMsg);
        }

        logger.stop();
    }

    private void validateParams(final MessageContentsList params, final String uuid) throws SandataRuntimeException {

        if (uuid == null || uuid.length() == 0) {
            throw new SandataRuntimeException(String.format("exportSchedule(Exchange): ERROR: UUID is null or empty!"));
        }

        if (params.size() != 4) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule(Exchange): ERROR: Unexpected number of params: " +
                            "Params: [%d]: Expected: 4", params.size()));
        }

        Integer staffId = (Integer) params.get(0);

        if (staffId == null || staffId <= 0) {
            throw new SandataRuntimeException(String.format("exportSchedule(Exchange): ERROR: StaffId [%d]: " +
                    "Please provide a valid StaffId!", staffId));
        }

        Integer patientId = (Integer) params.get(1);

        if (patientId == null || patientId <= 0) {
            throw new SandataRuntimeException(String.format("exportSchedule(Exchange): ERROR: PatientId [%d]: " +
                    "Please provide a valid PatientId!", patientId));
        }

        String startDateStr = (String) params.get(2);

        if (startDateStr == null) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule(Exchange): ERROR: StartDate is null!"));
        }

        String endDateStr = (String) params.get(3);

        if (endDateStr == null) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule(Exchange): ERROR: EndDate is null!"));
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

            // If dates are not a valid format, an exception will be thrown
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            if (endDate.before(startDate)) {

                throw new SandataRuntimeException(
                        String.format("exportSchedule(Exchange): ERROR: EndDate [%s] can not be before StartDate [%s]!",
                                endDateStr, startDateStr));
            }
        }
        catch (Exception e) {

            if (e instanceof SandataRuntimeException) {
                throw new SandataRuntimeException(e.getMessage());
            }

            throw new SandataRuntimeException(String.format("exportSchedule(Exchange): %s: EXCEPTION: %s",
                    e.getClass().getName(), e.getMessage()));
        }
    }

    public ExportService getExportService() {
        return exportService;
    }

    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
