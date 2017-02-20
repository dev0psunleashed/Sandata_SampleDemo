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

package com.sandata.lab.intake.patient.api;

import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.intake.patient.BaseTestSupport;
import com.sandata.lab.intake.patient.impl.RestPatientIntakeService;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test the StaffIntakeServiceApi.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class PatientIntakeServiceTests extends BaseTestSupport {

    private PatientIntakeService service;
    private Patient patient;
    private List<Patient> patients;

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patient_for_insert() throws Exception {

        exchange.getIn().setBody(this.patient);
        service.insertPatient(exchange);

        validateGuid((String)exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patients_for_insert() throws Exception {

        exchange.getIn().setBody(this.patients);
        service.insertPatients(exchange);

        validateGuid((String)exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patient_for_update() throws Exception {

        exchange.getIn().setBody(this.patient);
        service.updatePatient(exchange);

        validateGuid((String)exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patients_for_update() throws Exception {

        exchange.getIn().setBody(this.patients);
        service.updatePatients(exchange);

        validateGuid((String)exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patient_for_delete() throws Exception {

        exchange.getIn().setBody(this.patient);
        service.deletePatient(exchange);

        validateGuid((String)exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_patients_for_delete() throws Exception {

        exchange.getIn().setBody(this.patients);
        service.deletePatients(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    private void validateGuid(String guid) {
        Assert.notNull(guid);
        Assert.isTrue(guid.length() == 36);
    }

    @Override
    protected void onSetup() throws IOException {
        this.service = new RestPatientIntakeService();
        this.patient = new Patient();
        patient.setPatientId(123456789);
        patient.setFirstName("JUnit");
        patient.setLastName("Test Case");
        patient.setEmail("junit@sandata.com");

        this.patients = new ArrayList<>();
        patients.add(this.patient);
    }
}
