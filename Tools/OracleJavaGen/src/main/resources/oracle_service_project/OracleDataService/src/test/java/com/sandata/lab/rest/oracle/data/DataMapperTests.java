package com.sandata.lab.rest.oracle.data;

import com.sandata.lab.rest.oracle.BaseTestSupport;
import com.sandata.lab.rest.oracle.jpub.model.PatientTyp;
import com.sandata.lab.rest.oracle.model.Patient;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 9/1/15
 * Time: 6:11 PM
 */

public class DataMapperTests extends BaseTestSupport {

    @Test
    public void should_map_patient_instance_to_patient_typ() throws Exception {

        Patient patient = new DataFactory<Patient>(){}.createPatient();

        Assert.assertNotNull(patient);

        Object patientTypeObj = new DataMapper().map(patient);

        Assert.assertNotNull(patientTypeObj);
        Assert.assertTrue(patientTypeObj instanceof PatientTyp);
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
