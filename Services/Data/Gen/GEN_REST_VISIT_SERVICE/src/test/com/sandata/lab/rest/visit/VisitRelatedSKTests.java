package com.sandata.lab.rest.visit;

import com.sandata.lab.rest.visit.model.VisitRelatedSK;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VisitRelatedSKTests extends BaseTestSupport {

    @Test
    public void should_validate_visit_related_sk_entity() throws Exception {

        VisitRelatedSK visitRelatedSk = new VisitRelatedSK("1", 62235L);
        visitRelatedSk.addVisitException(BigDecimal.valueOf(206188L));
        visitRelatedSk.addVisitException(BigDecimal.valueOf(206189L));

        Object[] result = visitRelatedSk.getParams();
        Assert.assertTrue(result.length == 3);

        for (Object obj : result) {
            System.out.println(obj);
        }
    }
    
    @Override
    protected void onSetup() throws Exception {

    }
}
