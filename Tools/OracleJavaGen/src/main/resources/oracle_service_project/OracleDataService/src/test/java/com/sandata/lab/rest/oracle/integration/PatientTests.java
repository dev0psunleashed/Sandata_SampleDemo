package com.sandata.lab.rest.oracle.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.oracle.impl.OracleDataService;
import com.sandata.lab.rest.oracle.impl.RestDataService;
import com.sandata.lab.rest.oracle.jpub.model.PatientMedicalHistTyp;
import com.sandata.lab.rest.oracle.jpub.model.PatientTyp;
import com.sandata.lab.rest.oracle.model.*;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Date: 9/1/15
 * Time: 8:07 PM
 */

public class PatientTests extends BaseIntegrationTest {

    @Test
    public void should_insert_a_new_patient_allergies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientAllergies data = (PatientAllergies)
                new DataFactory<PatientAllergies>(){}.createOnlyOne(PatientAllergies.class, "1", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientAllergies", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientAllergies(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_medications_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientMedications data = (PatientMedications)
                new DataFactory<PatientMedications>(){}.createOnlyOne(PatientMedications.class, "2", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientMedications", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientMedications(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_dme_supplies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientDMEAndSupplies data = (PatientDMEAndSupplies)
                new DataFactory<PatientDMEAndSupplies>(){}.createOnlyOne(PatientDMEAndSupplies.class, "2", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientDmeSuppl", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientDmeSuppl(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_contact_details_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientContactDetail patientContactDetail = (PatientContactDetail)
                new DataFactory<PatientContactDetail>(){}.createOnlyOne(PatientContactDetail.class, "1", "1");

        patientContactDetail.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientContactDetail);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientContactDetl", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientContactDetl(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientContactDetail);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_record_via_oracle_data_service() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Patient patient = new DataFactory<Patient>(){}.createOnlyPatient("99", "1");
        patient.setReasonForChange("JUnit Test Case#2");

        PatientTyp patientTypeObj = (PatientTyp)new DataMapper().map(patient);

        //int returnValue = service.insertPatient(patientTypeObj);
        int returnValue = service.execute("PKG_PATIENT", "insertPatient", patientTypeObj);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatient(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patient);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_record_with_essential_sub_lists() throws Exception {

        final String testCaseName = "JUnit Recursive Insert Test#6";
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

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

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

    @Test
    public void should_insert_a_new_patient_record() throws Exception {

        Patient patient = new DataFactory<Patient>(){}.createOnlyPatient("6", "1");
        patient.setReasonForChange("JUnit Test Case#1");

        PatientTyp patientTypeObj = (PatientTyp)new DataMapper().map(patient);

        Object returnValue = call("{?=call PKG_PATIENT.insertPatient(?)}",
                OracleTypes.INTEGER, new Object[]{patientTypeObj});

        Assert.assertNotNull(returnValue);

        Assert.assertTrue(returnValue instanceof Integer);

        Integer patientKey = (Integer)returnValue;

        Assert.assertTrue(patientKey > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatient(): Returned: %d", patientKey));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patient);

        System.out.println(json);
    }

    @Test
    public void insert_patient__medical_history_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientMedicalHistory data = (PatientMedicalHistory)
                new DataFactory<PatientMedicalHistory>(){}.createOnlyOne(PatientMedicalHistory.class, "2", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientMedicalHist", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientMedicalHist(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void update_patient__medical_history_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientMedicalHistory data = (PatientMedicalHistory)
                new DataFactory<PatientMedicalHistory>(){}.createOnlyOne(PatientMedicalHistory.class, "2", "1");

        data.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientMedicalHist", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientMedicalHist(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_task_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientTask data = (PatientTask)
                new DataFactory<PatientTask>(){}.createOnlyOne(PatientTask.class, "1", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientTask", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientTask(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
        {
  "PatientTaskID": "1",
  "PatientID": "1",
  "TaskID": "1",
  "BusinessEntityID": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "ChangeVersionID": 0
}
         */
    }

    @Test
    public void update_patient__task_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientTask data = (PatientTask)
                new DataFactory<PatientTask>() {
                }.createOnlyOne(PatientTask.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientTask", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientTask(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_services_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientService data = (PatientService)
                new DataFactory<PatientService>(){}.createOnlyOne(PatientService.class, "2", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientService", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientService(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "PatientServiceID": "1",
  "PatientID": "2",
  "BusinessEntityID": "1",
  "ServiceID": "1",
  "PatientServiceBeginDate": "9999-12-31 00:00:00",
  "PatientServiceEndDate": "9999-12-31 00:00:00",
  "PatientServiceEventCode": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "ChangeVersionID": 0
}  */
    }

    @Test
    public void update_patient__services_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientService data = (PatientService)
                new DataFactory<PatientService>() {
                }.createOnlyOne(PatientService.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientService", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientService(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_medications_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientMedications data = (PatientMedications)
                new DataFactory<PatientMedications>(){}.createOnlyOne(PatientMedications.class, "3", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientMedications", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientMedications(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "MedicationID": "1",
  "PatientID": "3",
  "BusinessEntityID": "1",
  "PatientMedicationsSK": 0,
  "MedicationName": "1",
  "MedicationDosageForm": "1",
  "MedicationDosageStrength": "1",
  "MedicationFrequency": "1",
  "MedicationRoute": "1",
  "MedicationStartDate": "9999-12-31 00:00:00",
  "MedicationEndDate": "9999-12-31 00:00:00",
  "MedicationClassification": "1",
  "MedicationRank": "1",
  "MediSpanID": "1",
  "AuthReimburse": "1",
  "MedicationPRN": "1",
  "MedicationPRNReason": "1",
  "MedicationRXCUI": "1",
  "MedicationContraindications": "1",
  "MedicationComments": "1",
  "MedicationInfoSheet": null,
  "CurrentRecordIndicator": false,
  "ReasonForChange": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordCreatedBy": "1",
  "RecordEffectiveTimestamp": "9999-12-31 00:00:00",
  "RecordTerminationTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdatedBy": "1",
  "ChangeVersionID": 0
}  */
    }

    @Test
    public void update_patient_medications_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientMedications data = (PatientMedications)
                new DataFactory<PatientMedications>() {
                }.createOnlyOne(PatientMedications.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientMedications", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientMedications(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_requirements_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientRequirements data = (PatientRequirements)
                new DataFactory<PatientRequirements>(){}.createOnlyOne(PatientRequirements.class, "3", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientRqmts", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientRqmts(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "PatientRequirementsID": "2",
  "PatientID": "3",
  "BusinessEntityID": "1",
  "PatientRequirementsSK": 10,
  "StaffGender": "1",
  "StaffLanguage": "1",
  "StaffNationality": "1",
  "StaffEthnicity": "1",
  "StaffSpecialtyTraining": "2",
  "PatientRestrictionsComments": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordCreatedBy": "1",
  "RecordEffectiveTimestamp": "9999-12-31 00:00:00",
  "RecordTerminationTimestamp": "9999-12-31 00:00:00",
  "CurrentRecordIndicator": false,
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdatedBy": "1",
  "ReasonForChange": "1",
  "ChangeVersionID": 0
} */
    }

    @Test
    public void update_patient_requirements_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientRequirements data = (PatientRequirements)
                new DataFactory<PatientRequirements>() {
                }.createOnlyOne(PatientRequirements.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientRqmts", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientRqmts(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_dmeandsupplies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientDMEAndSupplies data = (PatientDMEAndSupplies)
                new DataFactory<PatientDMEAndSupplies>(){}.createOnlyOne(PatientDMEAndSupplies.class, "3", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientDmeSuppl", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientDmeSuppl(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "PatientDMEID": "1",
  "PatientID": "3",
  "BusinessEntityID": "1",
  "PatientDMEAndSuppliesSK": 0,
  "PatientMedicalEquipmentName": "1",
  "CurrentRecordIndicator": false,
  "ReasonForChange": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordCreatedBy": "1",
  "RecordEffectiveTimestamp": "9999-12-31 00:00:00",
  "RecordTerminationTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdatedBy": "1",
  "ChangeVersionID": 0
} */
    }

    @Test
    public void update_patient_dmeandsupplies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientDMEAndSupplies data = (PatientDMEAndSupplies)
                new DataFactory<PatientDMEAndSupplies>() {
                }.createOnlyOne(PatientDMEAndSupplies.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientDmeSuppl", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientDmeSuppl(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void get_patient_dmeandsupplies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientDMEAndSupplies data = (PatientDMEAndSupplies)
                new DataFactory<PatientDMEAndSupplies>() {
                }.createOnlyOne(PatientDMEAndSupplies.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int key = 16;

        Object returnValue = service.executeGet("PKG_PATIENT", "getPatientDmeSuppl", "com.sandata.lab.rest.oracle.model.PatientDMEAndSupplies",key);

      //  Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.getPatientDmeSuppl(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_intake_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientIntake data = (PatientIntake)
                new DataFactory<PatientIntake>(){}.createOnlyOne(PatientIntake.class, "64", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientIntake", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientIntake(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "PatientDMEID": "1",
  "PatientID": "3",
  "BusinessEntityID": "1",
  "PatientIntakeSK": 0,
  "PatientMedicalEquipmentName": "1",
  "CurrentRecordIndicator": false,
  "ReasonForChange": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordCreatedBy": "1",
  "RecordEffectiveTimestamp": "9999-12-31 00:00:00",
  "RecordTerminationTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdatedBy": "1",
  "ChangeVersionID": 0
} */
    }

    @Test
    public void update_patient_intake_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientIntake data = (PatientIntake)
                new DataFactory<PatientIntake>() {
                }.createOnlyOne(PatientIntake.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientIntake", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientIntake(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_patient_allergies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientAllergies data = (PatientAllergies)
                new DataFactory<PatientAllergies>(){}.createOnlyOne(PatientAllergies.class, "4", "1");

        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientAllergies", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientAllergies(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);

        /*
{
  "PatientDMEID": "1",
  "PatientID": "3",
  "BusinessEntityID": "1",
  "PatientAllergiesSK": 0,
  "PatientMedicalEquipmentName": "1",
  "CurrentRecordIndicator": false,
  "ReasonForChange": "1",
  "RecordCreateTimestamp": "9999-12-31 00:00:00",
  "RecordCreatedBy": "1",
  "RecordEffectiveTimestamp": "9999-12-31 00:00:00",
  "RecordTerminationTimestamp": "9999-12-31 00:00:00",
  "RecordUpdateTimestamp": "9999-12-31 00:00:00",
  "RecordUpdatedBy": "1",
  "ChangeVersionID": 0
} */
    }

    @Test
    public void update_patient_allergies_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientAllergies data = (PatientAllergies)
                new DataFactory<PatientAllergies>() {
                }.createOnlyOne(PatientAllergies.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_PATIENT", "updatePatientAllergies", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.updatePatientAllergies(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }

    @Test
    public void insert_reference_record() throws Exception {

        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        Reference data = (Reference)
                new DataFactory<Reference>() {
                }.createOnlyOne(Reference.class, "2", "1");


        Object jpubObject = new DataMapper().map(data);

        int returnValue = service.execute("PKG_REFERENCE", "insertReference", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertReference(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(data);

        System.out.println(json);
    }


    public PatientTests() throws SQLException {
        super();
    }
}
