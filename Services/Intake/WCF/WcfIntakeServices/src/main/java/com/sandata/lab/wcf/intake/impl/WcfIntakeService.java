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

package com.sandata.lab.wcf.intake.impl;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.error.Error;
import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.data.model.wcf.error.WcfError;
import com.sandata.lab.data.model.wcf.patient.WcfPatient;
import com.sandata.lab.data.model.wcf.response.WcfResponse;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.data.model.wcf.staff.WcfStaff;
import com.sandata.lab.wcf.intake.api.IntakeService;
import com.sandata.lab.wcf.intake.data.map.LookupTableFieldMapper;
import com.sandata.lab.wcf.intake.data.map.PatientMapper;
import com.sandata.lab.wcf.intake.data.map.ScheduleMapper;
import com.sandata.lab.wcf.intake.data.map.StaffMapper;
import com.sandata.lab.wcf.intake.utils.log.WcfIntakeServiceLogger;
import com.sandata.lab.wcf.lookup.api.LookupTablesService;
import org.apache.camel.PropertyInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IntakeService interface integration with WCF Import.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfIntakeService implements IntakeService {

    private LookupTablesService lookupTablesService;

    private HttpDataService httpDataService;

    private GSONProvider gsonProvider;

    @PropertyInject("wcf.import.url")
    private String url = "http://dev-lab-napp01.sandata.com:5005";

    @Override
    public Response insertStaffMember(Staff staffMember) {

        SandataLogger logger = WcfIntakeServiceLogger.CreateLogger();
        logger.setMethodName("insert(Staff)");

        final WcfStaff wcfStaff = new StaffMapper(LookupTableFieldMapper.getInstance()).map(staffMember);

        Response response =
                httpRequest(String.format("[%s]", new GSONProvider("MM/dd/yyyy HH:mm:ss").toJson(wcfStaff)),
                        "ImportStaff", logger);

        logger.stop();

        return response;
    }

    @Override
    public Response insertStaffMembers(List<Staff> staffMembers) {

        SandataLogger logger = WcfIntakeServiceLogger.CreateLogger();
        logger.setMethodName("insert(List<Staff>)");

        final List<WcfStaff> wcfStaffMembers = new ArrayList<>();

        LookupTableFieldMapper mapper = LookupTableFieldMapper.getInstance();

        for (Staff staffMember : staffMembers) {

            final WcfStaff wcfStaff = new StaffMapper(mapper).map(staffMember);
            wcfStaffMembers.add(wcfStaff);
        }

        Response response =
                httpRequest(new GSONProvider("MM/dd/yyyy HH:mm:ss").toJson(wcfStaffMembers),
                        "ImportStaff", logger);

        logger.stop();

        return response;
    }

    @Override
    public Response insertPatient(Patient patient) {

        SandataLogger logger = WcfIntakeServiceLogger.CreateLogger();
        logger.setMethodName("insert(Patient)");

        final WcfPatient wcfPatient = new PatientMapper(LookupTableFieldMapper.getInstance()).map(patient);

        Response response =
                httpRequest(String.format("[%s]", new GSONProvider("MM/dd/yyyy HH:mm:ss").toJson(wcfPatient)),
                        "ImportPatients", logger);

        logger.stop();

        return response;
    }

    @Override
    public Response insertPatients(List<Patient> patients) {

        SandataLogger logger = WcfIntakeServiceLogger.CreateLogger();
        logger.setMethodName("insert(List<Patient>)");

        final List<WcfPatient> wcfPatients = new ArrayList<>();

        LookupTableFieldMapper mapper = LookupTableFieldMapper.getInstance();

        for (Patient patient : patients) {

            final WcfPatient wcfPatient = new PatientMapper(mapper).map(patient);
            wcfPatients.add(wcfPatient);
        }

        Response response =
                httpRequest(new GSONProvider("MM/dd/yyyy HH:mm:ss").toJson(wcfPatients),
                        "ImportPatients", logger);

        logger.stop();

        return response;
    }

    @Override
    public Response insertSchedules(List<Schedule> schedules) throws SandataRuntimeException {

        SandataLogger logger = WcfIntakeServiceLogger.CreateLogger();
        logger.setMethodName("insertSchedules(List<Schedule>)");

        final List<WcfSchedule> wcfSchedules = new ScheduleMapper().map(schedules);

        Response response = httpRequest(getGsonProvider().toJson(wcfSchedules), "ImportSchedules", logger);

        if (response.getStatus() == ServiceStatus.FAILED) {
            response.setFailedCount(wcfSchedules.size());
        }

        logger.stop();

        return response;
    }

    private Response httpRequest(final String json, final String method, final SandataLogger logger) throws SandataRuntimeException {

        String methodName = logger.getMethodName();
        logger.setMethodName("httpRequest");

        httpDataService.setLogger(logger);

        Response response;
        try {
            response = new Response();

            httpDataService.addHeader("Authorization", "0123456789-FAKE");
            String result = httpDataService.post(String.format("%s/%s", url, method), json, "application/json");

            List<Error> errors = new ArrayList<>();

            if (result == null) {
                String errMsg = "httpDataService.post.result == null: Unexpected response from the WCF intake service.";
                logger.error(errMsg);

                Error error = new Error();
                error.setUniqueID(logger.getLoggerId());
                error.setMessage(errMsg);

                errors.add(error);
                response.setStatus(ServiceStatus.FAILED);
                response.setSucceededCount(0);
                response.setData(errors);
                return response;
            }

            WcfResponse wcfResponse = (WcfResponse) getGsonProvider().fromJson(result, WcfResponse.class);

            ServiceStatus serviceStatus = (wcfResponse.getStatus() != null && wcfResponse.getStatus().equals("Succeeded")) ? ServiceStatus.SUCCESS :
                    ServiceStatus.FAILED;
            response.setStatus(serviceStatus);
            response.setFailedCount(wcfResponse.getFailedCount());
            response.setSucceededCount(wcfResponse.getSucceededCount());

            if (wcfResponse.getErrors() != null) {
                for (WcfError wcfError : wcfResponse.getErrors()) {
                    Error error = new Error();
                    error.setUniqueID(wcfError.getUniqueID());
                    error.setMessage(wcfError.getMessage());
                    errors.add(error);
                }
            }
            else {
                logger.warn("WcfResponse.getErrors() == null: " +
                        "Expecting a collection of WcfError entities from WCF Intake Service!");
            }

            response.setData(errors);
        }
        catch (Exception e) {
            String errMessage = String.format("%s: EXCEPTION: %s: %s",
                    getClass().getName(), e.getClass().getName(), e.getMessage());
            logger.error(errMessage);
            throw new SandataRuntimeException(errMessage);
        }

        logger.setMethodName(methodName);
        return response;
    }

    public LookupTablesService getLookupTablesService() {
        return lookupTablesService;
    }

    public void setLookupTablesService(LookupTablesService lookupTablesService) {
        this.lookupTablesService = lookupTablesService;
    }

    public HttpDataService getHttpDataService() {
        return httpDataService;
    }

    public void setHttpDataService(HttpDataService httpDataService) {
        this.httpDataService = httpDataService;
    }

    public String getUrl() {
        return this.url;
    }

    public GSONProvider getGsonProvider() {

        // lazy load
        if (gsonProvider == null) {
            gsonProvider = new GSONProvider("MM-dd-yyyy");
        }

        return gsonProvider;
    }

    public void setGsonProvider(GSONProvider gsonProvider) {
        this.gsonProvider = gsonProvider;
    }
}
