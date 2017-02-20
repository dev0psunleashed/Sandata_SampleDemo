package com.sandata.lab.rest.rfrl.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.rfrl.impl.OracleDataService;
import com.sandata.lab.rest.rfrl.model.Referral;
import com.sandata.lab.rest.rfrl.utils.data.DataFactory;
import com.sandata.lab.rest.rfrl.utils.data.DataMapper;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Date: 9/10/15
 * Time: 6:37 PM
 */

public class ReferralTests extends BaseIntegrationTest {

    @Test
    public void should_insert_a_new_referral_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Referral data = (Referral)
                new DataFactory<Referral>(){}.createOnlyOne(Referral.class, UUID.randomUUID().toString());

        data.setReasonForChange("JMeter Test Insert Case#2");
        data.setChangeVersionID(BigInteger.valueOf(777));

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    public ReferralTests() throws SQLException {
        super();
    }
}
