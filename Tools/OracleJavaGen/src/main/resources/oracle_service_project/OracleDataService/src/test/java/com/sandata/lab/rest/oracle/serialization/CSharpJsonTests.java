package com.sandata.lab.rest.oracle.serialization;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.oracle.BaseTestSupport;
import com.sandata.lab.rest.oracle.model.Patient;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Date: 8/30/15
 * Time: 11:38 PM
 */

public class CSharpJsonTests extends BaseTestSupport {

    @Test
    public void should_generate_patient_object_from_json_generated_by_csharp() throws Exception {

        String path = "/Users/dmrutgos/Dev/Sandata/george.java/Tools/OracleJavaGen/src/main/resources/csharp_model_classes/Sandata.George.Domain/GeorgeModelTests/bin/debug/data/json";
        byte[] encoded = Files.readAllBytes(Paths.get(path + "/patient.cs.json"));
        String json = new String(encoded, StandardCharsets.UTF_8);

        System.out.println(json);

        Patient patient = (Patient) new GSONProvider("MM-dd-yyyy HH:mm").fromJson(json, Patient.class);

        Assert.assertNotNull(patient);
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
