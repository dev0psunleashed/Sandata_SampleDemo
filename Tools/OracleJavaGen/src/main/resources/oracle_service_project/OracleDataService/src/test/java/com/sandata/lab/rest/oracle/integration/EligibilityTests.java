package com.sandata.lab.rest.oracle.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.oracle.impl.OracleDataService;
import com.sandata.lab.rest.oracle.model.Eligibility;
import com.sandata.lab.rest.oracle.model.PatientAllergies;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.SQLException;

/**
 * Date: 9/2/15
 * Time: 4:42 PM
 */

public class EligibilityTests extends BaseIntegrationTest {

    @Test
    public void should_update_an_eligibility_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Eligibility data = (Eligibility)
                new DataFactory<Eligibility>(){}.createOnlyOne(Eligibility.class, "1", "1");

        data.setReasonForChange("JUnit Test Case#2");
        data.setEligibilitySK(BigInteger.valueOf(10));

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_ELIGIBILITY", "updateElig", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_ELIGIBILITY.insertPatientAllergies(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_eligibility_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Eligibility data = (Eligibility)
                new DataFactory<Eligibility>(){}.createOnlyOne(Eligibility.class, "1", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_ELIGIBILITY", "insertElig", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_ELIGIBILITY.insertPatientAllergies(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    public EligibilityTests() throws SQLException {
        super();
    }
}
