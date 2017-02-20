package com.sandata.lab.rest.oracle.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.rest.oracle.impl.OracleDataService;
import com.sandata.lab.rest.oracle.jpub.model.PatientServiceTyp;
import com.sandata.lab.rest.oracle.model.*;
import com.sandata.lab.rest.oracle.utils.data.DataFactory;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * Created by tom.dornseif on 9/2/2015.
 */
public class PatientDataServiceTestsPart2 extends BaseIntegrationTest{

    public PatientDataServiceTestsPart2() throws SQLException {
        super();
    }
/* PatientService is incorect in oracle
    @Test
    public void should_delete_patient_service_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.

        //patientService.setReasonForChange("JUnit Test Case#1");


        int returnValue = service.execute("PKG_PATIENT", "deletePatientService", 1);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.deletePatientService(): Returned: %d", returnValue));


    }
*/
    @Test
    public void should_insert_patient_services_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        PatientService patientService = (PatientService)
                new DataFactory<PatientService>(){}.createOnlyOne(PatientService.class, "1", "1");

        //patientService.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientService);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientService", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientService(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientService);

        System.out.println(json);
    }

    @Test
    public void should_get_patient_service_record_by_sk() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientService", "com.sandata.lab.rest.oracle.model.PatientService", "1", "1");
        PatientService patientService = (PatientService)arrayList.get(0);
//"com.sandata.lab.rest.oracle.model.PatientService"

        //patientService.setReasonForChange("JUnit Test Case#1");

        Assert.assertTrue(patientService != null);

        System.out.println(String.format("PKG_PATIENT.getPatientService(): Returned: %s", patientService.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientService);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_safety_measure_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientSafetyMeasures patientSafetyMeasures = (PatientSafetyMeasures)
                new DataFactory<PatientSafetyMeasures>(){}.createOnlyOne(PatientSafetyMeasures.class, "1", "1");

        patientSafetyMeasures.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientSafetyMeasures);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientSafetyMsrs", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientSafetyMsrs(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientSafetyMeasures);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_safety_msrs_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientSafetyMsrs", "com.sandata.lab.rest.oracle.model.PatientSafetyMeasures" , "1", "1");
        PatientSafetyMeasures patientSafetyMeasures = (PatientSafetyMeasures)arrayList.get(0);

        Assert.assertTrue(patientSafetyMeasures != null);

        System.out.println(String.format("PKG_PATIENT.getPatientSafetyMsrs(): Returned: %s", patientSafetyMeasures.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientSafetyMeasures);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_bsn_ent_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientBusinessEntity patientBusinessEntity = (PatientBusinessEntity)
                new DataFactory<PatientBusinessEntity>(){}.createOnlyOne(PatientBusinessEntity.class, "1", "1");

        patientBusinessEntity.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientBusinessEntity);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientBsnEnt", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientBsnEnt(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientBusinessEntity);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_bsn_ent_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientBsnEnt", "com.sandata.lab.rest.oracle.model.PatientBusinessEntity" , "1", "1");
        PatientBusinessEntity patientBusinessEntity = (PatientBusinessEntity)arrayList.get(0);
    
        Assert.assertTrue(patientBusinessEntity != null);
    
        System.out.println(String.format("PKG_PATIENT.getPatientBsnEnt(): Returned: %s", patientBusinessEntity.toString()));
    
        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientBusinessEntity);
    
        System.out.println(json);
    }
    
    @Test
    public void should_insert_a_new_patient_xwalk_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientIDCrosswalk patientIDCrosswalk = (PatientIDCrosswalk)
                new DataFactory<PatientIDCrosswalk>(){}.createOnlyOne(PatientIDCrosswalk.class, "1", "1");


        Object jpubObject = new DataMapper().map(patientIDCrosswalk);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientIdXwalk", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientIdXwalk(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientIDCrosswalk);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_xwalk_record_by_sk() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientIdXwalk", "com.sandata.lab.rest.oracle.model.PatientIDCrosswalk" , 10);//"1", "1");
        PatientIDCrosswalk patientCrosswalk = (PatientIDCrosswalk)arrayList.get(0);

        Assert.assertTrue(patientCrosswalk != null);

        System.out.println(String.format("PKG_PATIENT.getPatientIdXwalk(): Returned: %s", patientCrosswalk.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientCrosswalk);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_environment_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientEnvironment patientEnvironment = (PatientEnvironment)
                new DataFactory<PatientEnvironment>(){}.createOnlyOne(PatientEnvironment.class, "1", "1");

        patientEnvironment.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientEnvironment);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientEnv", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientEnv(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientEnvironment);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_env_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientEnv", "com.sandata.lab.rest.oracle.model.PatientEnvironment" , "1", "1");
        PatientEnvironment patientEnvironment = (PatientEnvironment)arrayList.get(0);

        Assert.assertTrue(patientEnvironment != null);

        System.out.println(String.format("PKG_PATIENT.getPatientEnv(): Returned: %s", patientEnvironment.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientEnvironment);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_env_record_by_sk() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientEnv", "com.sandata.lab.rest.oracle.model.PatientEnvironment" , 8);//"1", "1");
        PatientEnvironment patientEnvironment = (PatientEnvironment)arrayList.get(0);

        Assert.assertTrue(patientEnvironment != null);

        System.out.println(String.format("PKG_PATIENT.getPatientEnv(): Returned: %s", patientEnvironment.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientEnvironment);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_payer_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientPayer patientPayer = (PatientPayer)
                new DataFactory<PatientPayer>(){}.createOnlyOne(PatientPayer.class, "1", "1");

       Object jpubObject = new DataMapper().map(patientPayer);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientPayer", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientPayer(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientPayer);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_payer_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientPayer", "com.sandata.lab.rest.oracle.model.PatientPayer" , 10);//"1", "1");
        PatientPayer patientPayer = (PatientPayer)arrayList.get(0);

        Assert.assertTrue(patientPayer != null);

        System.out.println(String.format("PKG_PATIENT.getPatientPayer(): Returned: %s", patientPayer.toString()));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientPayer);

        System.out.println(json);
    }

    @Test
    public void should_insert_a_new_patient_nutrl_reqs_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientNutritionalReqs patientNutritionalReqs = (PatientNutritionalReqs)
                new DataFactory<PatientNutritionalReqs>(){}.createOnlyOne(PatientNutritionalReqs.class, "1", "1");

        patientNutritionalReqs.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientNutritionalReqs);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientNutrlReqs", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientNutrlReqs(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientNutritionalReqs);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_nutr_reqs_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientNutrlReqs", "com.sandata.lab.rest.oracle.model.PatientNutritionalReqs" , 16);//"1", "1");
        PatientNutritionalReqs patientNutritionalReqs = (PatientNutritionalReqs)arrayList.get(0);
    
        Assert.assertTrue(patientNutritionalReqs != null);
    
        System.out.println(String.format("PKG_PATIENT.getPatientNutrlReqs(): Returned: %s", patientNutritionalReqs.toString()));
    
        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientNutritionalReqs);
    
        System.out.println(json);
    }
    
    @Test
    public void should_insert_a_new_patient_clinical_asmt_record() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());

        PatientClinicalAssessment patientClinicalAssessment = (PatientClinicalAssessment)
                new DataFactory<PatientClinicalAssessment>(){}.createOnlyOne(PatientClinicalAssessment.class, "1", "1");

        patientClinicalAssessment.setReasonForChange("JUnit Test Case#1");

        Object jpubObject = new DataMapper().map(patientClinicalAssessment);

        int returnValue = service.execute("PKG_PATIENT", "insertPatientClinicalAsmt", jpubObject);

        Assert.assertTrue(returnValue > 0);

        System.out.println(String.format("PKG_PATIENT.insertPatientClinicalAsmt(): Returned: %d", returnValue));

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientClinicalAssessment);

        System.out.println(json);
    }
    @Test
    public void should_get_patient_find_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_FIND", "getPatients", "com.sandata.lab.rest.oracle.model.PatientFind" , "L1", "", "", "", 1, 10, "", 1);
        PatientFind patientFind = (PatientFind)arrayList.get(0);

        Assert.assertTrue(patientFind != null);

        System.out.println(String.format("PKG_PATIENT.getPatients(): Returned: %s", patientFind.toString()));

        for(PatientFind p : (ArrayList<PatientFind>)arrayList) {
            String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(p);

            System.out.println(json);
        }
    }

    @Test
    public void should_get_patient_clinical_asmt_record_by_patientId_and_bussinessEntityId() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        //must have a service record exist with id 1.
        ArrayList arrayList = (ArrayList)service.executeGet("PKG_PATIENT", "getPatientClinicalAsmt", "com.sandata.lab.rest.oracle.model.PatientClinicalAssessment" , 9);//"1", "1");
        PatientClinicalAssessment patientClinicalAssessment = (PatientClinicalAssessment)arrayList.get(0);
    
        Assert.assertTrue(patientClinicalAssessment != null);
    
        System.out.println(String.format("PKG_PATIENT.getPatientClinicalAsmt(): Returned: %s", patientClinicalAssessment.toString()));
    
        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientClinicalAssessment);
    
        System.out.println(json);
    }
    @Test
    public void runthisstoredproctest() throws Exception {
        OracleDataService service = new OracleDataService();
        service.setSandataOracleConnection(getSandataOracleConnection());
        Connection connection = service.getOracleConnection();
        java.sql.ResultSet resultSet = null;
        String lastName = "L1", firstName = null, patientId = null, patientINS = null;
        int fromRow = 1, toRow = 10;
        String sortOn = "ln";
        int isAsc = 0;
            // Note:  probalby want to also include agncyId as a filter parameter
            // Connect to Oracle using JDBC driver
        String sortField = "PATIENT.patient_last_name";

        if(sortOn == null) {
            sortOn = "ln";
        }
        if(sortOn.equals("ln")) {
            sortField = "PATIENT.patient_last_name";
        }
        else if  (sortOn.equals("fn")) {
            sortField = "PATIENT.patient_first_name";
        }
        else if (sortOn.equals("pn")) {
            sortField = "PATIENT.patient_ID";
        }
        else if( sortOn.equals("ins"))
        {
            sortField = "PATIENT.PATIENT_INS_ID_NUM";
        }
        else if( sortOn.equals("dob"))
        {
            sortField = "PATIENT.PATIENT_DOB";
        }

        if (isAsc == 1) {
            sortField += " ASC ";
        }
        else {
            sortField += " DESC ";
        }



        String whereClause = "WHERE ";
        boolean addAnd = false;
        if(lastName != null && !lastName.isEmpty()) {
            whereClause += "patient.patient_last_name LIKE '" + lastName + "%' ";
            addAnd = true;
        }
        if(firstName != null && !firstName.isEmpty()) {
            if(addAnd) {
                whereClause += " AND ";
            }
            whereClause += " patient.patient_first_name LIKE '" + firstName + "%' ";
            addAnd = true;
        }
        if(patientId != null && !patientId.isEmpty()) {
            if(addAnd) {
                whereClause += " AND ";
            }
            whereClause += " patient.patient_id LIKE '" + patientId + "%' ";
            addAnd = true;
        }
        if(patientINS != null && patientINS.length() > 0) {
            if(addAnd) {
                whereClause += " AND ";
            }
            whereClause += " PATIENT.PATIENT_INS_ID_NUM = LIKE'" + patientINS + "%' ";
            addAnd = true;
        }

        // Build SQL statement
        String sql = "SELECT patientId, " +
                "    agencyId, " +
                "    patientINS,  " +
                "    firstName,  " +
                "    lastName,  " +
                "    dateOfBirth,  " +
                "  PATIENT_CONTACT_DETL.PATIENT_ADDR1 as address, " +
                "  PATIENT_CONTACT_DETL.PATIENT_CITY as city, " +
                "  PATIENT_CONTACT_DETL.PATIENT_STATE as state, " +
                "  PATIENT_CONTACT_DETL.PATIENT_PSTL_CODE as zip,   " +
                "  PATIENT_CONTACT_DETL.PATIENT_HOME_PHONE as phone, " +
                "  totalRows " +
                "  FROM ( SELECT patientId,  " +
                "    agencyId, " +
                "    patientINS,  " +
                "    firstName,  " +
                "    lastName,  " +
                "    dateOfBirth, " +
                "    totalRows, " +
                "    ROWNUM as rnum " +
                "    FROM ( SELECT PATIENT.PATIENT_ID as patientId, " +
                "      PATIENT.BSN_ENT_ID as agencyId, " +
                "      PATIENT.PATIENT_INS_ID_NUM as patientINS,  " +
                "      PATIENT.PATIENT_FIRST_NAME as firstName,  " +
                "      PATIENT.PATIENT_LAST_NAME as lastName,  " +
                "      PATIENT.PATIENT_DOB as dateOfBirth, " +
                "      COUNT(*) over() as totalRows " +
                "      FROM PATIENT ";
        if(addAnd) {
            sql += whereClause;
        }
        sql += " ORDER BY " + sortField + " ) WHERE ROWNUM <=" + toRow + ") left outer JOIN PATIENT_CONTACT_DETL ON patientId= PATIENT_CONTACT_DETL.PATIENT_ID AND agencyId = PATIENT_CONTACT_DETL.BSN_ENT_ID WHERE rnum >= " + fromRow;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        catch (SQLException sqle) {
            System.err.println("GetPatient: SQLException: " + sqle.getMessage());
            throw new SQLException(sqle);
        }

            Assert.assertTrue(resultSet != null);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for(int i = 0; i < columnCount; i++ ) {
            System.out.println(String.format("Column %d Type is (", i + 1) + resultSetMetaData.getColumnTypeName(i +1) + ") ");
        }
            Object result = new DataMapper().map(resultSet, "com.sandata.lab.rest.oracle.model.PatientFind");
            ArrayList<PatientFind> list = (ArrayList<PatientFind>)result;
            for (PatientFind patientFind : list) {
                String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(patientFind);
                System.out.println(json);
            }
        }

    }

