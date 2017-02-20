package com.sandata.lab.rest.oracle.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.oracle.impl.OracleDataService;
import com.sandata.lab.rest.oracle.model.Eligibility;
import com.sandata.lab.rest.oracle.model.ScheduleEvent;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Date: 9/2/15
 * Time: 11:56 PM
 */

public class ScheduleEventTests extends BaseIntegrationTest {


    @Test
    public void should_insert_a_new_schedule_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        ScheduleEvent data = (ScheduleEvent)
                new DataFactory<ScheduleEvent>(){}.createOnlyOne(ScheduleEvent.class, "1", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_SCHEDULE", "insertScheduleEvnt", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_SCHEDULE.insertScheduleEvnt(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    public ScheduleEventTests() throws SQLException {
        super();
    }
}
