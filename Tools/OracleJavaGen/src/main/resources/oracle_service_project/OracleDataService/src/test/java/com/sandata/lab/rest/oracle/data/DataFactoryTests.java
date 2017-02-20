package com.sandata.lab.rest.oracle.data;

import com.sandata.lab.rest.oracle.BaseTestSupport;
import com.sandata.lab.rest.oracle.model.Patient;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 9/1/15
 * Time: 1:24 PM
 */

public class DataFactoryTests extends BaseTestSupport {

    @Test
    public void should_create_patient_instance_with_dummy_data() throws Exception {

        Patient patient = new DataFactory<Patient>(){}.createPatient();

        Assert.assertNotNull(patient);

        // TODO: Not working for some weird reason!!!!!!
        /*DataFactory<Patient> dataFactory = new DataFactory<Patient>(){};

        Assert.assertNotNull(dataFactory);

        Patient patient = dataFactory.createWithData();

        Assert.assertNotNull(patient);*/
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
