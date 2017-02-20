package com.sandata.lab.rest.staff.integration;

import java.sql.SQLException;

/**
 * <p>StaffComplianceTests.java</p>
 * <p>Unit tests for services to support Staff
 * Compliance Scheduler.</p>
 * <p>Date: 6/17/2016</p>
 * <p>Time: 12:27 PM</p>
 *
 * @author jasonscott
 */
public class StaffComplianceTests extends BaseIntegrationTest {

    protected StaffComplianceTests() throws SQLException {
    }

    /*@Test
    public void testFindStaffForTrainingCategory() throws ParseException {
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_STAFF_findStaffForTrainingCategory_Response");
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("staff_trng_code", "5768");
        exchange.getIn().setHeader("staff_trng_loc_name", "Main St.");
        exchange.getIn().setHeader("staff_trng_start_dtime", "2016-07-20 10:30");
//        exchange.getIn().setHeader("emp_status_type_name", EmploymentStatusTypeName.ACTIVE);
//        exchange.getIn().setHeader("compliant", true);
//        exchange.getIn().setHeader("compliant", false);
//        exchange.getIn().setHeader("staff_id", "8675309");
//        exchange.getIn().setHeader("first_name", "Jason");
//        exchange.getIn().setHeader("last_name", "Scott");
        exchange.getIn().setHeader("page", 1);
        exchange.getIn().setHeader("page_size", 50);
        exchange.getIn().setHeader("sort_on", "STAFF_LAST_NAME");
        exchange.getIn().setHeader("direction", "ASC");

        restDataService.findStaffForTrainingCategory(exchange);

        List<FindCompSchedStaffResult> findCompSchedStaffResultList = (List<FindCompSchedStaffResult>) exchange.getIn().getBody();
        assertNotNull(findCompSchedStaffResultList);

        Integer totalRows = (Integer) exchange.getIn().getHeader("TOTAL_ROWS");
        assertCollectionSize(findCompSchedStaffResultList, totalRows);

        System.out.println(GSONProvider.ToJson(findCompSchedStaffResultList));
    }

    @Test
    public void testGetStaffTrngClsEvnt() {
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_STAFF_getStaffTrngClsEvnt_StaffTrainingClassEvent");
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("staff_trng_code", "8675");
        exchange.getIn().setHeader("staff_trng_loc_name", "Main St.");
        exchange.getIn().setHeader("page", 1);
        exchange.getIn().setHeader("page_size", 50);
        exchange.getIn().setHeader("sort_on", "REC_CREATE_TMSTP");
        exchange.getIn().setHeader("direction", "ASC");

        restDataService.getStaffTrngClsEvntForStaffTrngLocName(exchange);

        List<StaffTrainingClassEvent> staffTrainingClassEventList = (List<StaffTrainingClassEvent>) exchange.getIn().getBody();
        assertNotNull(staffTrainingClassEventList);

        Integer totalRows = (Integer) exchange.getIn().getHeader("TOTAL_ROWS");
        assertCollectionSize(staffTrainingClassEventList, totalRows);

        System.out.println(GSONProvider.ToJson(staffTrainingClassEventList));
    }

    @Test
    public void testGetStaffTrngLocationForSk() {
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_STAFF_getStaffTrngLocation_StaffTrainingLocation");
        exchange.getIn().setHeader("sequence_key", 1);

        restDataService.get(exchange);

        StaffTrainingLocation staffTrainingLocation = (StaffTrainingLocation) exchange.getIn().getBody();
        assertNotNull(staffTrainingLocation);

        System.out.println(GSONProvider.ToJson(staffTrainingLocation));
    }

    @Test
    public void testGetStaffTrngLocations() {
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_STAFF_getStaffTrngLocation_StaffTrainingLocation");
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("staff_trng_code", "8675");
        exchange.getIn().setHeader("page", 1);
        exchange.getIn().setHeader("page_size", 50);
        exchange.getIn().setHeader("sort_on", "REC_CREATE_TMSTP");
        exchange.getIn().setHeader("direction", "ASC");

        restDataService.getStaffTrngLocationForStaffTrngCode(exchange);

        List<StaffTrainingLocation> staffTrainingLocationList = (List<StaffTrainingLocation>) exchange.getIn().getBody();
        assertNotNull(staffTrainingLocationList);

        Integer totalRows = (Integer) exchange.getIn().getHeader("TOTAL_ROWS");
        assertCollectionSize(staffTrainingLocationList, totalRows);

        System.out.println(GSONProvider.ToJson(staffTrainingLocationList));
    }
    */
}
