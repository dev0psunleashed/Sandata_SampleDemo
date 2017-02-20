package com.sandata.lab.rest.patient.integration;

import java.sql.SQLException;

/**
 * Date: 9/11/15
 * Time: 5:33 AM
 */

public class PatientTests extends BaseIntegrationTest {

    /*@Test
    public void should_get_business_entity_sk_for_given_id() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        BigDecimal bsnEntSk = DataHelper.getBusinessEntitySK("1", service);

        Assert.notNull(bsnEntSk);
        Assert.isTrue(bsnEntSk.intValue() == 1);
    }
    */

    /*
    @Test
    public void should_find_a_patient_record_by_first_few_characters_of_the_first_name_via_oracle_data_service() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientFilterTyp filterTyp = new PatientFilterTyp();
        filterTyp.setPatientFirstName("Da");
        //filterTyp.setPatientLastName("Ru");
        //filterTyp.setPatientHomePhone("718");

        //int returnValue = service.insertPatient(patientTypeObj);
        Response response = service.findPatient(filterTyp, "t1.PT_LAST_NAME", 1, 5, "ASC");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getTotalRows() > 0);
        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData() instanceof List);
        Assert.assertTrue(((List)response.getData()).size() > 0);
    }
     */

    /*
    @Test
    public void should_insert_a_new_patient_record_via_oracle_data_service() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Patient patient = new DataFactory<Patient>(){}.createOnlyPatient(UUID.randomUUID().toString(), "1");
        patient.setReasonForChange("JUnit Test Case#4");
        patient.setPatientFirstName("JUnit");
        patient.setPatientMiddleName("Test");
        patient.setPatientLastName("Case");
        patient.setCurrentRecordIndicator(true);

        PatientTyp patientTypeObj = (PatientTyp)new DataMapper().map(patient);

        //int returnValue = service.insertPatient(patientTypeObj);
        int returnValue = service.execute("PKG_PATIENT", "insertPatient", patientTypeObj);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatient(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patient);

        System.out.println(json);
    }
    */

    /*
    @Test
    public void should_insert_a_new_patient_record_with_essential_sub_lists() throws Exception {

        final String testCaseName = "JUnit Recursive Insert Test#7";
        final String patientId = UUID.randomUUID().toString();
        final String entityId = "1";
        final String eligibilityId = "4";
        final String serviceId = "1";
        final String smId = "2";
        final String nutrId = "2";
        final String allId = "5";
        final String pdId = "8";
        final String ptaskId = "2";
        final String dmeId = "4";
        final String preqId = "3";
        final String medId = "4";
        final String pmedId = "4";


        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setSandataOracleConnection(getSandataOracleConnection());

        Patient patient = new DataFactory<Patient>(){}.createOnlyPatient(patientId, entityId);
        patient.setReasonForChange(testCaseName);

        // ONLY Support These Tables for Recursive Insert

        // Eligibility
        Eligibility eligibility = (Eligibility)new DataFactory<Eligibility>(){}.createOnlyOne(Eligibility.class, patientId, entityId);
        eligibility.setEligibilityID(eligibilityId);
        eligibility.setReasonForChange(testCaseName);
        patient.getEligibility().add(eligibility);

        // PatientService
        PatientService patientService = (PatientService)new DataFactory<Eligibility>(){}.createOnlyOne(PatientService.class, patientId, entityId);
        patientService.setServiceID(serviceId);
        //-- Vitaliy--patientService.setReasonForChange(testCaseName);
        patient.getPatientService().add(patientService);

        // PatientSafetyMeasures
        PatientSafetyMeasures safetyMeasures = (PatientSafetyMeasures)new DataFactory<PatientSafetyMeasures>(){}.createOnlyOne(PatientSafetyMeasures.class, patientId, entityId);
        safetyMeasures.setPatientSafetyMeasuresID(smId);
        safetyMeasures.setReasonForChange(testCaseName);
        patient.getPatientSafetyMeasures().add(safetyMeasures);

        // PatientNutritionalReqs
        PatientNutritionalReqs patientNutritionalReqs = (PatientNutritionalReqs)new DataFactory<PatientNutritionalReqs>(){}.createOnlyOne(PatientNutritionalReqs.class, patientId, entityId);
        patientNutritionalReqs.setPatientNutritionalReqsID(nutrId);
        patientNutritionalReqs.setReasonForChange(testCaseName);
        patient.getPatientNutritionalReqs().add(patientNutritionalReqs);

        // PatientAllergies
        PatientAllergies patientAllergies = (PatientAllergies)new DataFactory<PatientAllergies>(){}.createOnlyOne(PatientAllergies.class, patientId, entityId);
        patientAllergies.setPatientAllergyID(allId);
        patientAllergies.setReasonForChange(testCaseName);
        patient.getPatientAllergies().add(patientAllergies);

        // PatientContactDetail
        PatientContactDetail patientContactDetail = (PatientContactDetail)new DataFactory<PatientContactDetail>(){}.createOnlyOne(PatientContactDetail.class, patientId, entityId);
        patientContactDetail.setPatientContactDetailID(pdId);
        patientContactDetail.setReasonForChange(testCaseName);
        patient.getPatientContactDetail().add(patientContactDetail);

        // PatientTask
        PatientTask patientTask = (PatientTask)new DataFactory<PatientTask>(){}.createOnlyOne(PatientTask.class, patientId, entityId);
        patientTask.setPatientTaskID(ptaskId);
        //--Vitaliy--patientTask.setReasonForChange(testCaseName);
        patient.getPatientTask().add(patientTask);

        // PatientDMEAndSupplies
        PatientDMEAndSupplies patientDMEAndSupplies = (PatientDMEAndSupplies)new DataFactory<PatientDMEAndSupplies>(){}.createOnlyOne(PatientDMEAndSupplies.class, patientId, entityId);
        patientDMEAndSupplies.setPatientDMEID(dmeId);
        patientDMEAndSupplies.setReasonForChange(testCaseName);
        patient.getPatientDMEAndSupplies().add(patientDMEAndSupplies);

        // PatientRequirements
        PatientRequirements patientRequirements = (PatientRequirements)new DataFactory<PatientRequirements>(){}.createOnlyOne(PatientRequirements.class, patientId, entityId);
        patientRequirements.setPatientRequirementsID(preqId);
        patientRequirements.setReasonForChange(testCaseName);
        patient.getPatientRequirements().add(patientRequirements);

        // PatientMedications
        PatientMedications patientMedications = (PatientMedications)new DataFactory<PatientMedications>(){}.createOnlyOne(PatientMedications.class, patientId, entityId);
        patientMedications.setMedicationID(medId);
        patientMedications.setReasonForChange(testCaseName);
        patient.getPatientMedications().add(patientMedications);

        // PatientMedicalHistory
        PatientMedicalHistory patientMedicalHistory = (PatientMedicalHistory)new DataFactory<PatientMedicalHistory>(){}.createOnlyOne(PatientMedicalHistory.class, patientId, entityId);
        patientMedicalHistory.setPatientMedicalHistoryID(pmedId);
        patientMedicalHistory.setReasonForChange(testCaseName);
        patient.getPatientMedicalHistory().add(patientMedicalHistory);

        //exchange.getIn().setBody(patient);

        //restDataService.insert(exchange);

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patient);

        System.out.println(json);
    }
    */

    public PatientTests() throws SQLException {
        super();
    }
}
