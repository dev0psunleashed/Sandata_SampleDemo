package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.data.compare.CompareUtil;
import com.sandata.lab.data.model.data.Compare;
import com.sandata.lab.data.model.data.CompareResult;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.Visit;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Date: 9/5/16
 * Time: 8:26 PM
 */

@RunWith(JMockit.class)
public class CompareUtilTests {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void should_compare_two_visit_date_entities() throws Exception {

        Visit visitOriginal = new Visit();
        visitOriginal.setRecordUpdateTimestamp(simpleDateFormat.parse("2016-09-05 00:01:02"));

        Visit visitUpdated = new Visit();
        visitUpdated.setRecordUpdateTimestamp(simpleDateFormat.parse("2016-09-05 01:02:03"));

        Compare compare = new Compare();
        compare.setOriginal(visitOriginal);
        compare.setUpdated(visitUpdated);

        List<CompareResult> compareResultList = CompareUtil.Compare(compare);

        Assert.assertNotNull(compareResultList);
        Assert.assertTrue(compareResultList.size() == 1);

        CompareResult compareResult = compareResultList.get(0);

        Assert.assertTrue(compareResult.getDataPoint().equals("RecordUpdateTimestamp"));
        Assert.assertTrue(compareResult.getOriginalValue().equals("2016-09-05 00:01:02"));
        Assert.assertTrue(compareResult.getUpdatedValue().equals("2016-09-05 01:02:03"));
    }

    @Test
    public void should_compare_two_visit_entities() throws Exception {

        Visit visitOriginal = new Visit();
        visitOriginal.setBusinessEntityID("1");
        visitOriginal.setPatientID("123");
        visitOriginal.setStaffID("456");
        visitOriginal.setRecordCreateTimestamp(new Date());

        Visit visitUpdated = new Visit();
        visitUpdated.setBusinessEntityID("999");
        visitUpdated.setPatientID("555");
        visitUpdated.setStaffID("777");
        visitUpdated.setRecordCreateTimestamp(new Date());

        Compare compare = new Compare();
        compare.setUpdated(visitUpdated);

        Assert.assertNull(CompareUtil.Compare(null));

        List<CompareResult> compareResultList = CompareUtil.Compare(compare);

        Assert.assertNotNull(compareResultList);
        Assert.assertTrue(compareResultList.size() == 1);

        CompareResult compareResult = compareResultList.get(0);

        Assert.assertNotNull(compareResult.getDataPoint());
        Assert.assertTrue(compareResult.getDataPoint().equals("RecordCreateTimestamp"));

        // Make sure that we do not compare two different entities
        compare.setOriginal(visitOriginal);
        compare.setUpdated(new Staff());

        compareResultList = CompareUtil.Compare(compare);

        Assert.assertNull(compareResultList);

        compare.setUpdated(visitUpdated);

        compareResultList = CompareUtil.Compare(compare);

        Assert.assertNotNull(compareResultList);
        Assert.assertTrue(compareResultList.size() == 3);

        CompareResult compareResult1 = compareResultList.get(0);
        Assert.assertTrue(compareResult1.getDataPoint().equals("BusinessEntityID"));
        Assert.assertTrue(compareResult1.getOriginalValue().equals("1"));
        Assert.assertTrue(compareResult1.getUpdatedValue().equals("999"));

        CompareResult compareResult2 = compareResultList.get(1);
        Assert.assertTrue(compareResult2.getDataPoint().equals("PatientID"));
        Assert.assertTrue(compareResult2.getOriginalValue().equals("123"));
        Assert.assertTrue(compareResult2.getUpdatedValue().equals("555"));

        CompareResult compareResult3 = compareResultList.get(2);
        Assert.assertTrue(compareResult3.getDataPoint().equals("StaffID"));
        Assert.assertTrue(compareResult3.getOriginalValue().equals("456"));
        Assert.assertTrue(compareResult3.getUpdatedValue().equals("777"));
    }
}
