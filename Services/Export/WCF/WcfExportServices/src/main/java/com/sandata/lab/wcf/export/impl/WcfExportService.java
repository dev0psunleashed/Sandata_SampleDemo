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

package com.sandata.lab.wcf.export.impl;

import com.google.gson.reflect.TypeToken;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.data.model.error.Error;
import com.sandata.lab.data.model.wcf.response.WcfResponse;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.export.api.ExportService;
import com.sandata.lab.wcf.export.data.map.WcfScheduleMapper;
import com.sandata.lab.wcf.export.utils.log.WcfExportServiceLogger;
import org.apache.camel.PropertyInject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of the IntakeService interface integration with WCF Import.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class WcfExportService implements ExportService {

    @PropertyInject("wcf.export.url")
    private String url = "http://dev-lab-napp01.sandata.com:5005";

    @Override
    public Response exportSchedule(int staffId, int patientId, Date startDate, Date endDate)
            throws SandataRuntimeException {

        SandataLogger logger = WcfExportServiceLogger.CreateLogger();
        logger.setMethodName("exportSchedule");

        // Perform validation first
        validateParams(staffId, patientId, startDate, endDate);

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        Response response = httpRequest(staffId, patientId, dateFormat.format(startDate), dateFormat.format(endDate),
                "ExportSchedules", logger);

        logger.stop();

        return response;
    }

    private void validateParams(int staffId, int patientId, Date startDate, Date endDate) throws SandataRuntimeException {

        if (staffId <= 0) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule: ERROR: StaffId [%d]: Please provide a valid StaffId!", staffId));
        }

        if (patientId <= 0) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule: ERROR: PatientId [%d]: Please provide a valid PatientId!", patientId));
        }

        if (startDate == null) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule: ERROR: StartDate is null!"));
        }

        if (endDate == null) {
            throw new SandataRuntimeException(
                    String.format("exportSchedule: ERROR: EndDate is null!"));
        }

        if (endDate.before(startDate)) {

            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

            String startDateStr = dateFormat.format(startDate);
            String endDateStr = dateFormat.format(endDate);

            throw new SandataRuntimeException(
                    String.format("exportSchedule: ERROR: EndDate [%s] can not be before StartDate [%s]!",
                            endDateStr, startDateStr));
        }
    }

    private Response httpRequest(final int staffId, final int patientId, final String startDate, final String endDate,
                                 final String method, final SandataLogger logger) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("httpRequest");

        Response response = new Response();
        try {

            HttpDataService httpDataService = new HttpDataService(logger);
            httpDataService.addHeader("Authorization", "0123456789-FAKE");
            String result = httpDataService.get(String.format("%s/%s/%s/%s/%d/%d",
                    url, method, startDate, endDate, staffId, patientId), "application/json");

            GSONProvider.SetDateTimeFormat("MM-dd-yyyy");

            Type listType = new TypeToken<List<WcfSchedule>>() {}.getType();

            List<WcfSchedule> wcfSchedules = (List<WcfSchedule>) GSONProvider.FromJson(result, listType);

            List<Schedule> schedules = new WcfScheduleMapper().map(wcfSchedules);

            response.setStatus(ServiceStatus.SUCCESS);
            response.setSucceededCount(schedules.size());
            response.setData(schedules);
        }
        catch (Exception e) {
            String errMessage = String.format("%s: EXCEPTION: %s: %s",
                    getClass().getName(), e.getClass().getName(), e.getMessage());
            logger.error(errMessage);

            response.setStatus(ServiceStatus.FAILED);
            response.setFailedCount(1);

            List<Error> errors = new ArrayList<>();
            Error error = new Error();
            error.setMessage(errMessage);
            error.setUniqueID(logger.getLoggerId());
            errors.add(error);

            response.setData(errors);
        }

        logger.setMethodName(methodName);
        return response;
    }
}
