package com.sandata.rules.compliance.services;

import com.sandata.lab.data.model.dl.model.SchedulePermissionQualifier;
import com.sandata.lab.data.model.dl.model.StaffItemRequiredFromQualifier;
import com.sandata.rules.compliance.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Class for testing RuleProcessor.
 *
 * @author jasonscott
 */
public class RuleProcessorTest {

    private RuleProcessor ruleProcessor = new RuleProcessor();

    @Before
    public void setup() {

    }

    private BusinessEntityComplianceFact createBusinessEntityComplianceFact() throws ParseException {
        BusinessEntityComplianceFact businessEntityComplianceFact = new BusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityId("5");
        businessEntityComplianceFact.setServiceName("RN");
        businessEntityComplianceFact.setBusinessEntityComplianceType(BusinessEntityComplianceType.COMPLIANCE);
        businessEntityComplianceFact.setBusinessEntityComplianceCode("1234");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("10/01/2015"));
        businessEntityComplianceFact.setTerminationDate(null);
        businessEntityComplianceFact.setStaffItemRequiredFromQualifier(StaffItemRequiredFromQualifier.STAFF_VERIFIED_START_DATE);
        businessEntityComplianceFact.setComplianceRequiredByDate(null);
        return businessEntityComplianceFact;
    }

    private BusinessEntityComplianceResultFact createBusinessEntityComplianceResultFact() {
        BusinessEntityComplianceResultFact businessEntityComplianceResultFact = new BusinessEntityComplianceResultFact();
        businessEntityComplianceResultFact.setBusinessEntityId("5");
        businessEntityComplianceResultFact.setBusinessEntityComplianceCode("1234");
        businessEntityComplianceResultFact.setComplianceResultReadingValue("POSITIVE");
        businessEntityComplianceResultFact.setSchedulePermissionQualifier(SchedulePermissionQualifier.PREVENT);
        businessEntityComplianceResultFact.setCompliant(false);
        return businessEntityComplianceResultFact;
    }

    private StaffFact createStaffFact() throws ParseException {
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("8675309");
        staffFact.setBusinessEntityId("5");
        staffFact.setServiceName("RN");
        staffFact.setStaffHireDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2016"));
        staffFact.setStaffVerifiedStartDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/08/2016"));
        return staffFact;
    }

    private StaffComplianceFact createStaffComplianceFact() throws ParseException {
        StaffComplianceFact staffComplianceFact = new StaffComplianceFact();
        staffComplianceFact.setStaffId("8675309");
        staffComplianceFact.setBusinessEntityId("5");
        staffComplianceFact.setComplianceCode("1234");
        staffComplianceFact.setBusinessEntityComplianceType(BusinessEntityComplianceType.COMPLIANCE);
        staffComplianceFact.setComplianceResultReadingValue(null);
        staffComplianceFact.setReceivedDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/07/2016"));
        staffComplianceFact.setExpirationDate(new SimpleDateFormat("MM/dd/yyyy").parse("05/05/2021"));
        staffComplianceFact.setContinueEvaluation(false);
        return staffComplianceFact;
    }

    @Test
    public void testNonActiveBusinessEntityComplianceFact() throws ParseException {
        List input = new ArrayList();

        // Future effective date.
        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("1234");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("10/01/2016"));
        businessEntityComplianceFact.setTerminationDate(null);
        input.add(businessEntityComplianceFact);

        // Termination date has passed.
        businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("5678");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2015"));
        businessEntityComplianceFact.setTerminationDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2016"));
        input.add(businessEntityComplianceFact);

        // Valid.
        businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("4321");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2016"));
        businessEntityComplianceFact.setTerminationDate(null);
        input.add(businessEntityComplianceFact);

        ruleProcessor.fireAllRules(input);
    }

    @Test
    public void testSettingContinueEvaluationFlag() throws ParseException {
        List input = new ArrayList();

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        ruleProcessor.fireAllRules(input);
    }

    @Test
    public void testMissingStaffComplianceFact() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactExpired() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        staffComplianceFact.setReceivedDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/07/2011"));
        staffComplianceFact.setExpirationDate(new SimpleDateFormat("MM/dd/yyyy").parse("05/05/2016"));
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactPastRequiredDate() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setComplianceRequiredByDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/03/2016"));
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactPastStaffHireDate() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setStaffItemRequiredFromQualifier(StaffItemRequiredFromQualifier.STAFF_HIRE_DATE);
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        staffFact.setStaffHireDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/03/2016"));
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactPastStaffStartDate() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setStaffItemRequiredFromQualifier(StaffItemRequiredFromQualifier.STAFF_VERIFIED_START_DATE);
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        staffFact.setStaffVerifiedStartDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/03/2016"));
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testBusinessEntityComplianceResultFactNotRequired() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(true, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactReadingValueResultNotSet() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        BusinessEntityComplianceResultFact businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        input.add(businessEntityComplianceResultFact);

        businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        businessEntityComplianceResultFact.setComplianceResultReadingValue("NEGATIVE");
        businessEntityComplianceResultFact.setSchedulePermissionQualifier(SchedulePermissionQualifier.ALLOW);
        businessEntityComplianceResultFact.setCompliant(true);
        input.add(businessEntityComplianceResultFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testStaffComplianceFactReadingValueResultInvalid() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        BusinessEntityComplianceResultFact businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        input.add(businessEntityComplianceResultFact);

        businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        businessEntityComplianceResultFact.setComplianceResultReadingValue("NEGATIVE");
        businessEntityComplianceResultFact.setSchedulePermissionQualifier(SchedulePermissionQualifier.ALLOW);
        businessEntityComplianceResultFact.setCompliant(true);
        input.add(businessEntityComplianceResultFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        staffComplianceFact.setComplianceResultReadingValue("INVALID");
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
    }

    @Test
    public void testCompliantMatchingStaffCompFactForBsnEntCompFactResult() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        BusinessEntityComplianceResultFact businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        input.add(businessEntityComplianceResultFact);

        businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        businessEntityComplianceResultFact.setComplianceResultReadingValue("NEGATIVE");
        businessEntityComplianceResultFact.setSchedulePermissionQualifier(SchedulePermissionQualifier.ALLOW);
        businessEntityComplianceResultFact.setCompliant(true);
        input.add(businessEntityComplianceResultFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        staffComplianceFact.setComplianceResultReadingValue("NEGATIVE");
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(true, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
        assertEquals(true, staffFact.isCompliant());
    }

    @Test
    public void testNonCompliantMatchingStaffCompFactForBsnEntCompFactResult() throws ParseException {
        List input = new ArrayList();

        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        input.add(businessEntityComplianceFact);

        BusinessEntityComplianceResultFact businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        input.add(businessEntityComplianceResultFact);

        businessEntityComplianceResultFact = createBusinessEntityComplianceResultFact();
        businessEntityComplianceResultFact.setComplianceResultReadingValue("NEGATIVE");
        businessEntityComplianceResultFact.setSchedulePermissionQualifier(SchedulePermissionQualifier.ALLOW);
        businessEntityComplianceResultFact.setCompliant(true);
        input.add(businessEntityComplianceResultFact);

        StaffFact staffFact = createStaffFact();
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        staffComplianceFact.setComplianceResultReadingValue("POSITIVE");
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);
        assertNotNull(staffFactResultList);
        assertEquals(1, staffFactResultList.size());
        StaffFact staffFactResult = staffFactResultList.get(0);
        assertNotNull(staffFactResult.getStaffComplianceResultList());
        assertEquals(1, staffFactResult.getStaffComplianceResultList().size());
        assertEquals(false, staffFactResult.getStaffComplianceResultList().get(0).isCompliant());
        assertEquals(false, staffFact.isCompliant());
    }

    @Test
    public void testAllCases() throws ParseException {
        List input = new ArrayList();

        // Future effective date.
        BusinessEntityComplianceFact businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("1234");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2017"));
        businessEntityComplianceFact.setTerminationDate(null);
        input.add(businessEntityComplianceFact);

        // Termination date has passed.
        businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("5678");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2015"));
        businessEntityComplianceFact.setTerminationDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2016"));
        input.add(businessEntityComplianceFact);

        // Valid.
        businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("4321");
        businessEntityComplianceFact.setEffectiveDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/01/2016"));
        businessEntityComplianceFact.setTerminationDate(null);
        input.add(businessEntityComplianceFact);

        // Missing compliance fact.
        StaffFact staffFact = createStaffFact();
        staffFact.setStaffId("1234");
        input.add(staffFact);

        // Expired compliance fact.
        staffFact = createStaffFact();
        staffFact.setStaffId("5678");
        input.add(staffFact);

        StaffComplianceFact staffComplianceFact = createStaffComplianceFact();
        staffComplianceFact.setStaffId("5678");
        staffComplianceFact.setComplianceCode("4321");
        staffComplianceFact.setReceivedDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/07/2011"));
        staffComplianceFact.setExpirationDate(new SimpleDateFormat("MM/dd/yyyy").parse("05/05/2016"));
        input.add(staffComplianceFact);

        // Past required by date.
        businessEntityComplianceFact = createBusinessEntityComplianceFact();
        businessEntityComplianceFact.setBusinessEntityComplianceCode("9012");
        businessEntityComplianceFact.setComplianceRequiredByDate(new SimpleDateFormat("MM/dd/yyyy").parse("09/03/2016"));
        input.add(businessEntityComplianceFact);

        staffFact = createStaffFact();
        staffFact.setStaffId("9012");

        staffComplianceFact = createStaffComplianceFact();
        input.add(staffComplianceFact);

        List<StaffFact> staffFactResultList = ruleProcessor.fireAllRules(input);

        for (StaffFact staffFactResult : staffFactResultList) {
            System.out.println(staffFactResult);
        }
    }
}
