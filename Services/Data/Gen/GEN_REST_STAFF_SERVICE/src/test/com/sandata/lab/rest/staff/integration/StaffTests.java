package com.sandata.lab.rest.staff.integration;

import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 9/7/15
 * Time: 2:07 PM
 */

public class StaffTests extends BaseIntegrationTest {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void should_return_last_date_worked_for_given_staff_sk_oracle_service() throws Exception {

        /*Date date = oracleDataService.getDateLastWorked("1");

        Assert.assertNotNull(date);

        String dateString = dateFormat.format(date);

        Assert.assertEquals("2015-11-04 00:00:04", dateString);*/
    }

    @Test
    public void should_return_last_date_worked_for_given_staff_sk() throws Exception  {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = openConnection();
            connection.setAutoCommit(true);

            String callMethod = "{?=call PKG_STAFF_UTIL.LAST_DATE_WORKED(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.TIMESTAMP);
            callableStatement.setString(1, "225724");
            callableStatement.setString(2, "10");
            callableStatement.execute();
            Timestamp result = (Timestamp)callableStatement.getObject(1);

            connection.close();

            Date date = new Date(result.getTime());

            Assert.assertNotNull(date);

            String dateString = dateFormat.format(date);

            Assert.assertEquals("2016-06-21 14:00:00", dateString);

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /*
    @Test
    public void should_validate_patient_does_not_exist_and_therefore_can_not_be_excluded() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        IsPatientExclFilterTyp filterTyp = new IsPatientExclFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setStaffId("2");
        filterTyp.setPatientId("NOT_A_VALID_PT_ID");

        long sequenceKey = service.isPatientExcluded(filterTyp);

        Assert.assertTrue(sequenceKey == 0);
    }

    @Test
    public void should_validate_patient_exists_and_can_be_excluded_for_given_staff() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        IsPatientExclFilterTyp filterTyp = new IsPatientExclFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setStaffId("2");
        filterTyp.setPatientId("d7e89804-4501-4e8a-jmeter-30071");

        long sequenceKey = service.isPatientExcluded(filterTyp);

        Assert.assertTrue(sequenceKey == 448);
    }

    @Test
    public void should_validate_patient_is_already_excluded_for_given_staff() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        IsPatientExclFilterTyp filterTyp = new IsPatientExclFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setStaffId("2");
        filterTyp.setPatientId("1");

        long sequenceKey = service.isPatientExcluded(filterTyp);

        Assert.assertTrue(sequenceKey == -1);
    }

    @Test
    public void should_validate_patient_does_not_exist() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        ValidatePatientFilterTyp filterTyp = new ValidatePatientFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setPatientId("NOT_A_VALID_PT_ID");

        long sequenceKey = service.validatePatientExists(filterTyp);

        Assert.assertTrue(sequenceKey == 0);
    }

    @Test
    public void should_validate_patient_exists() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        ValidatePatientFilterTyp filterTyp = new ValidatePatientFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setPatientId("1");

        long sequenceKey = service.validatePatientExists(filterTyp);

        Assert.assertTrue(sequenceKey == 5);
    }

    @Test
    public void should_retrieve_all_excluded_patients_for_a_given_staff_id_and_entity() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        ExclPatientsFilterTyp filterTyp = new ExclPatientsFilterTyp();
        filterTyp.setBsnEntId("1");
        filterTyp.setStaffId("2");

        Response response = service.excludedPatients(filterTyp, "t1.STAFF_ID", "ASC");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getTotalRows() > 0);
    }

    @Test
    public void should_insert_a_new_staff_record_with_essential_sub_lists() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Staff data = (Staff)
                new DataFactory<Staff>(){}.createOnlyOne(Staff.class, "1", "1");

        String testCaseName = "Advanced REST Recursive Insert#4";

        data.setReasonForChange(testCaseName);

        String staffId = UUID.randomUUID().toString();
        String entityId = "1";

        data.setStaffID(staffId);

        // StaffWorkingPreference
        StaffWorkingPreference workingPrefs = (StaffWorkingPreference)new DataFactory<StaffWorkingPreference>(){}.createOnlyOne(StaffWorkingPreference.class, staffId, entityId);
        workingPrefs.setReasonForChange(testCaseName);
        data.getStaffWorkingPreference().add(workingPrefs);

        // StaffPayrollDetail
        StaffPayrollDetail payrollDetail = (StaffPayrollDetail)new DataFactory<StaffPayrollDetail>(){}.createOnlyOne(StaffPayrollDetail.class, staffId, entityId);
        payrollDetail.setReasonForChange(testCaseName);
        data.getStaffPayrollDetail().add(payrollDetail);

        // StaffBenefits
        StaffBenefits benefits = (StaffBenefits)new DataFactory<StaffBenefits>(){}.createOnlyOne(StaffBenefits.class, staffId, entityId);
        benefits.setReasonForChange(testCaseName);
        data.getStaffBenefits().add(benefits);

        // StaffRate
        StaffRate rate = (StaffRate)new DataFactory<StaffRate>(){}.createOnlyOne(StaffRate.class, staffId, entityId);
        //--Vitaliy--rate.setReasonForChange(testCaseName);
        data.getStaffRateList().add(rate);

        // StaffCompliance
        StaffCompliance StaffCompliance = (StaffCompliance)new DataFactory<StaffCompliance>(){}.createOnlyOne(StaffCompliance.class, staffId, entityId);
        StaffCompliance.setReasonForChange(testCaseName);
        data.getStaffCompliance().add(StaffCompliance);

        // StaffCredentials
        StaffCredentials credentials = (StaffCredentials)new DataFactory<StaffCredentials>(){}.createOnlyOne(StaffCredentials.class, staffId, entityId);
        credentials.setReasonForChange(testCaseName);
        data.getStaffCredentials().add(credentials);

        // StaffSkills
        StaffSkills skills = (StaffSkills)new DataFactory<StaffSkills>(){}.createOnlyOne(StaffSkills.class, staffId, entityId);
        skills.setReasonForChange(testCaseName);
        data.getStaffSkills().add(skills);

        // StaffCertifications
        StaffCertifications certifications = (StaffCertifications)new DataFactory<StaffCertifications>(){}.createOnlyOne(StaffCertifications.class, staffId, entityId);
        certifications.setReasonForChange(testCaseName);
        data.getStaffCertifications().add(certifications);

        // StaffAvailability
        StaffAvailability availability = (StaffAvailability)new DataFactory<StaffAvailability>(){}.createOnlyOne(StaffAvailability.class, staffId, entityId);
        //--Vitaliy--availability.setReasonForChange(testCaseName);
        data.getStaffAvailability().add(availability);


        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Staff data = (Staff)
                new DataFactory<Staff>(){}.createOnlyOne(Staff.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_admission_type_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffAdmissionType data = (StaffAdmissionType)
                new DataFactory<StaffAdmissionType>(){}.createOnlyOne(StaffAdmissionType.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffAdmissionTypeID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_assessment_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffAssessment data = (StaffAssessment)
                new DataFactory<StaffAssessment>(){}.createOnlyOne(StaffAssessment.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffAssessmentID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_availability_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffAvailability data = (StaffAvailability)
                new DataFactory<StaffAvailability>(){}.createOnlyOne(StaffAvailability.class, "1", "1");

        //--Vitaliy--data.setReasonForChange("Advanced REST Client#1");

        data.setStaffAvailabilityID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_benefits_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffBenefits data = (StaffBenefits)
                new DataFactory<StaffBenefits>(){}.createOnlyOne(StaffBenefits.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffBenefitsID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_certifications_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffCertifications data = (StaffCertifications)
                new DataFactory<StaffCertifications>(){}.createOnlyOne(StaffCertifications.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffCertificationID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_compliance_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffCompliance data = (StaffCompliance)
                new DataFactory<StaffCompliance>(){}.createOnlyOne(StaffCompliance.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffComplianceID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_credentials_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffCredentials data = (StaffCredentials)
                new DataFactory<StaffCredentials>(){}.createOnlyOne(StaffCredentials.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffCredentialsID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_hiring_hist_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffHiringHistory data = (StaffHiringHistory)
                new DataFactory<StaffHiringHistory>(){}.createOnlyOne(StaffHiringHistory.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffHiringHistoryID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_medical_hist_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffMedicalHistory data = (StaffMedicalHistory)
                new DataFactory<StaffMedicalHistory>(){}.createOnlyOne(StaffMedicalHistory.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffMedicalHistoryID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_patient_hist_detail_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffPatientHistory data = (StaffPatientHistory)
                new DataFactory<StaffPatientHistory>(){}.createOnlyOne(StaffPatientHistory.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");
        data.setPatientID("1");

        data.setStaffPatientHistoryID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_payroll_detail_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffPayrollDetail data = (StaffPayrollDetail)
                new DataFactory<StaffPayrollDetail>(){}.createOnlyOne(StaffPayrollDetail.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffPayrollID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_rate_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffRate data = (StaffRate)
                new DataFactory<StaffRate>(){}.createOnlyOne(StaffRate.class, "1", "1");

        data.setStaffRateID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_skills_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffSkills data = (StaffSkills)
                new DataFactory<StaffSkills>(){}.createOnlyOne(StaffSkills.class, "1", "1");

        data.setReasonForChange("Advanced REST Client#1");

        data.setStaffSkillsID(UUID.randomUUID().toString());

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_working_prefs_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffWorkingPreference data = (StaffWorkingPreference)
                new DataFactory<StaffWorkingPreference>(){}.createOnlyOne(StaffWorkingPreference.class, "1", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_inservice_training_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffInserviceTraining data = (StaffInserviceTraining)
                new DataFactory<StaffInserviceTraining>(){}.createOnlyOne(StaffInserviceTraining.class, "1", "1");

        data.setStaffTrainingID(UUID.randomUUID().toString());

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_staff_training_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        StaffTraining data = (StaffTraining)
                new DataFactory<StaffTraining>(){}.createOnlyOne(StaffTraining.class, "1", "1");

        data.setStaffTrainingID(UUID.randomUUID().toString());

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        //int returnValue = service.execute("PKG_STAFF", "insertStaffTrng", jpubObject);

        //Assert.assertTrue(returnValue > 0);

        //System.out.println(String.format("PKG_STAFF.insertStaffTrng(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }
    */

    public StaffTests() throws SQLException {
        super();
    }
}
