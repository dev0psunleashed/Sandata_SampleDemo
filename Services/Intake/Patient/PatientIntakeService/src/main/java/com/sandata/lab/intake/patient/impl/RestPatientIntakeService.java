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

package com.sandata.lab.intake.patient.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.intake.patient.api.PatientIntakeService;
import com.sandata.lab.intake.patient.utils.constants.App;
import com.sandata.lab.intake.patient.utils.log.PatientIntakeLogger;
import com.sandata.lab.wcf.intake.api.IntakeService;
import org.apache.camel.Exchange;

import java.util.List;
import java.util.UUID;

/**
 * REST implementation of the PatientIntakeService interface.
 * <p/>
 *
 * @author David Rutgos
 */

public class RestPatientIntakeService implements PatientIntakeService {

    private IntakeService intakeService;
    private CacheService cacheService;

    public void createUUID(Exchange exchange) {

        String uuid = cacheService.create(exchange.getIn().getBody());
        exchange.getIn().setHeader(App.ID.PATIENT_GUID.toString(), uuid);
    }

    @Override
    public void insertPatient(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("insertPatient");

        logger.start();

        String uuid = (String) exchange.getIn().getHeader(App.ID.PATIENT_GUID.toString());

        final Patient patient = exchange.getIn().getBody(Patient.class);

        cacheService.processing(patient, uuid);

        Response response = intakeService.insertPatient(patient);

        cacheService.put(response, uuid);

        logger.stop();
    }

    @Override
    public void insertPatients(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("insertPatients");

        logger.start();

        String uuid = (String) exchange.getIn().getHeader(App.ID.PATIENT_GUID.toString());

        final List<Patient> patients = exchange.getIn().getBody(List.class);

        cacheService.processing(patients, uuid);

        Response response = intakeService.insertPatients(patients);

        cacheService.put(response, uuid);

        logger.stop();
    }

    @Override
    public void updatePatient(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("updatePatient");

        logger.start();

        final Patient patient = exchange.getIn().getBody(Patient.class);

        // TODO: log guid and start processing Patient insert
        String guid = UUID.randomUUID().toString();

        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void updatePatients(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("updatePatients");

        logger.start();

        final List<Patient> patients = exchange.getIn().getBody(List.class);

        // TODO: log guid and start processing Patient insert
        String guid = UUID.randomUUID().toString();
        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void deletePatient(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("deletePatient");

        logger.start();

        final Patient patient = exchange.getIn().getBody(Patient.class);

        // TODO: log guid and start processing Patient insert
        String guid = UUID.randomUUID().toString();

        exchange.getIn().setBody(guid);

        logger.stop();
    }

    @Override
    public void deletePatients(Exchange exchange) {

        SandataLogger logger = PatientIntakeLogger.CreateLogger(exchange);
        logger.setMethodName("deletePatients");

        logger.start();

        final List<Patient> patients = exchange.getIn().getBody(List.class);

        // TODO: log guid and start processing Patient insert
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
