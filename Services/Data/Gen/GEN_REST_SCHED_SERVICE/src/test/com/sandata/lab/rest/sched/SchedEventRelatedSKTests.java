package com.sandata.lab.rest.sched;

import com.sandata.lab.rest.sched.model.SchedEventRelatedSK;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SchedEventRelatedSKTests extends BaseTestSupport {

    @Test
    public void should_validate_sched_event_related_sk_entity() throws Exception {

        SchedEventRelatedSK schedEventRelatedSK = new SchedEventRelatedSK("1", 777L);
        schedEventRelatedSK.addScheduleEventAuth(BigDecimal.valueOf(123L));
        schedEventRelatedSK.addScheduleEventService(BigDecimal.valueOf(444L));

        Object[] result = schedEventRelatedSK.getParams();
        Assert.assertTrue(result.length == 3);

        for (Object obj : result) {
            System.out.println(obj);
        }
    }
    
    @Override
    protected void onSetup() throws Exception {
        
    }
}
