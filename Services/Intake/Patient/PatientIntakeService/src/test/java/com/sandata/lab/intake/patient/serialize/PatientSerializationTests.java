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

package com.sandata.lab.intake.patient.serialize;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.patient.Patient;
import com.sandata.lab.intake.patient.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the GSON Serialization/Deserialization of model objects.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class PatientSerializationTests extends BaseTestSupport {

    private DateFormat dateFormat;

    @Test
    public void should_serialize_a_simple_patient_object_to_json() throws Exception {

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Rutgos");
        patient.setMiddleInitial("M");
        patient.setEmail("drutgos@sandata.com");
        patient.setCellPhone("(912)-616-5337");
        patient.setHomePhone("(230)-898-7825");
        patient.setAddress1("1675 East 18th Street");
        patient.setAddress2("JMeter: Test Patient");
        patient.setCity("Brooklyn");
        patient.setZip("11229");
        patient.setInsurance("98000945960");
        patient.setMedicaidId("78069408861");
        patient.setMedicareId("14458783703");
        patient.setMedicalRecordNumber("23010361612");
        patient.setHourlyRate(24.37);
        patient.setBillRate(1.57);
        patient.setAgencyId(1);

        Date dob = dateFormat.parse("1962-01-21 00:00:00");
        patient.setDateOfBirth(dob);

        patient.setSsn("816-46-9325");
        patient.setMemberNumber("20064978498");
        patient.setAge(41);
        patient.setMaritalStatus("Married");
        patient.setGender("Male");
        patient.setLanguage("English");
        patient.setEthnicity("Caucasian");
        patient.setReligion("Christian");
        patient.setState("NY");
        patient.setAgencyLocation("934 Howard Place, Loomis, Rhode Island, 4747");

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patient);

        Assert.notNull(json);
        Assert.isTrue(json.contains("\"firstName\": \"David\""));
        Assert.isTrue(json.contains("\"lastName\": \"Rutgos\""));
        Assert.isTrue(json.contains("\"middleInitial\": \"M\""));

        System.out.println(json);
    }

    @Override
    protected void onSetup() throws IOException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }
}
