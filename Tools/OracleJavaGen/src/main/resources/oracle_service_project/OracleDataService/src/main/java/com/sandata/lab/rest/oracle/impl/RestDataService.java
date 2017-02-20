package com.sandata.lab.rest.oracle.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.rest.oracle.annotation.OracleMetadata;
import com.sandata.lab.rest.oracle.api.DataService;
import com.sandata.lab.rest.oracle.model.*;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import com.sandata.lab.rest.oracle.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.rest.oracle.model." + methodParts[3];

            Object result = null;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null)
            {
                int sequenceKey = (int) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>)result;

                if(result2.size() > 0) {
                    result = result2.get(0);
                }

                // TODO: Need to fix this design
                if (methodParts[3].equals("Patient") && result2.size() >0) {

                    Patient patient = (Patient) result;

                    // Eligibility
                    List<Eligibility> eList = (List<Eligibility>) oracleDataService.executeGet(
                            "PKG_ELIGIBILITY",
                            "getElig",
                            "com.sandata.lab.rest.oracle.model.Eligibility",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (Eligibility item : eList) {
                        patient.getEligibility().add(item);
                    }

                    // PatientService
                    List<PatientService> psList = (List<PatientService>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientService",
                            "com.sandata.lab.rest.oracle.model.PatientService",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientService item : psList) {
                        patient.getPatientService().add(item);
                    }

                    // PatientBusinessEntity
                    List<PatientBusinessEntity> beList = (List<PatientBusinessEntity>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientBsnEnt",
                            "com.sandata.lab.rest.oracle.model.PatientBusinessEntity",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientBusinessEntity item : beList) {
                        patient.getPatientBusinessEntity().add(item);
                    }

                    // PatientSafetyMeasures
                    List<PatientSafetyMeasures> smList = (List<PatientSafetyMeasures>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientSafetyMsrs",
                            "com.sandata.lab.rest.oracle.model.PatientSafetyMeasures",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientSafetyMeasures item : smList) {
                        patient.getPatientSafetyMeasures().add(item);
                    }

                    // PatientNutritionalReqs
                    List<PatientNutritionalReqs> nutrList = (List<PatientNutritionalReqs>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientNutrlReqs",
                            "com.sandata.lab.rest.oracle.model.PatientNutritionalReqs",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientNutritionalReqs item : nutrList) {
                        patient.getPatientNutritionalReqs().add(item);
                    }

                    // PatientAllergies
                    List<PatientAllergies> alList = (List<PatientAllergies>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientAllergies",
                            "com.sandata.lab.rest.oracle.model.PatientAllergies",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientAllergies item : alList) {
                        patient.getPatientAllergies().add(item);
                    }

                    // PatientContactDetail
                    List<PatientContactDetail> contactList = (List<PatientContactDetail>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientContactDetl",
                            "com.sandata.lab.rest.oracle.model.PatientContactDetail",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientContactDetail item : contactList) {
                        patient.getPatientContactDetail().add(item);
                    }

                    // PatientTask
                    List<PatientTask> taskList = (List<PatientTask>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientTask",
                            "com.sandata.lab.rest.oracle.model.PatientTask",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientTask item : taskList) {
                        patient.getPatientTask().add(item);
                    }

                    // PatientDMEAndSupplies
                    List<PatientDMEAndSupplies> dmeAndSuppliesList = (List<PatientDMEAndSupplies>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientDmeSuppl",
                            "com.sandata.lab.rest.oracle.model.PatientDMEAndSupplies",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientDMEAndSupplies item : dmeAndSuppliesList) {
                        patient.getPatientDMEAndSupplies().add(item);
                    }

                    // PatientRequirements
                    List<PatientRequirements> patientRequirementses = (List<PatientRequirements>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientRqmts",
                            "com.sandata.lab.rest.oracle.model.PatientRequirements",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientRequirements item : patientRequirementses) {
                        patient.getPatientRequirements().add(item);
                    }

                    // PatientMedications
                    List<PatientMedications> medicationsList = (List<PatientMedications>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientMedications",
                            "com.sandata.lab.rest.oracle.model.PatientMedications",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientMedications item : medicationsList) {
                        patient.getPatientMedications().add(item);
                    }

                    // PatientMedicalHistory
                    List<PatientMedicalHistory> patientMedicalHistories = (List<PatientMedicalHistory>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientNutrlReqs",
                            "com.sandata.lab.rest.oracle.model.PatientMedicalHistory",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientMedicalHistory item : patientMedicalHistories) {
                        patient.getPatientMedicalHistory().add(item);
                    }

                    // PatientIntake
                    List<PatientIntake> patientIntake = (List<PatientIntake>) oracleDataService.executeGet(
                            "PKG_PATIENT",
                            "getPatientIntake",
                            "com.sandata.lab.rest.oracle.model.PatientIntake",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (PatientIntake item : patientIntake) {
                        patient.getPatientIntake().add(item);
                    }

                    // Reference
                    List<Reference> reference = (List<Reference>) oracleDataService.executeGet(
                            "PKG_REFERENCE",
                            "getReference",
                            "com.sandata.lab.rest.oracle.model.Reference",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (Reference item : reference) {
                        patient.getReference().add(item);
                    }

                    List<Authorization> authorizations = (List<Authorization>) oracleDataService.executeGet(
                            "PKG_AUTH",
                            "getAuth",
                            "com.sandata.lab.rest.oracle.model.Authorization",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (Authorization item : authorizations) {
                        patient.getAuthorization().add(item);
                    }

                    List<BillingRate> billingRates = (List<BillingRate>) oracleDataService.executeGet(
                            "PKG_BILLING",
                            "getBillingRate",
                            "com.sandata.lab.rest.oracle.model.BillingRate",
                            patient.getPatientID(),
                            patient.getBusinessEntityID());

                    for (BillingRate item : billingRates) {
                        patient.getBillingRate().add(item);
                    }
                }
            }
            else
            {
                MessageContentsList params = (MessageContentsList)body;
                if (params.size() > 5) {//This is hacky to allow the executeGet to trigger right now
                    // we only have patient/find with objects but actually objects work for strings as well which is better right?
                    Object[] a = params.toArray();
                    result = oracleDataService.executeGet(
                            packageName,
                            methodName,
                            className,
                            a
                    );
                }
                else if(params.size() > 1) {
                    String patientId = (String) params.get(0);
                    String entityId = (String) params.get(1);

                    result = oracleDataService.executeGet(
                            packageName,
                            methodName,
                            className,
                            patientId,
                            entityId
                    );
                }
                else
                {
                    String id = (String) params.get(0);

                    result = oracleDataService.executeGet(
                            packageName,
                            methodName,
                            className,
                            new String[]{id}
                    );
                }
            }

            exchange.getIn().setBody(result);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long)exchange.getIn().getHeader("sequence_key");

            int result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();
        Connection connection = null;
        try
        {
            Object data = exchange.getIn().getBody();
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (recursiveUpdate(connection, data) > 0) {

                connection.commit();
                exchange.getIn().setBody(1);
            }
            else {
                throw new SandataRuntimeException("Insert was not successful!");
            }


            logger.stop();
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
        finally {

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

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger();

        Connection connection = null;

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            if (recursiveInsert(connection, data) > 0) {

                connection.commit();
                exchange.getIn().setBody(1);
            }
            else {
                throw new SandataRuntimeException("Insert was not successful!");
            }

            logger.stop();
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            e.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
        finally {

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

    private int recursiveInsert(final Connection connection, final Object data) throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            int result = oracleDataService.execute(
                    connection,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
            );

            if (result > 0) {

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List)property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            int insertResponse = recursiveInsert(connection, object);
                            if (insertResponse == -1) {
                                throw new SandataRuntimeException(String.format("INSERT: Failed: [%s]",
                                        object.getClass().getName()));
                            }
                        }
                    }
                }

                // SUCCESS
                return 1;

            } // if (result > 0)

            // FAILED
            return -1;
        }
        catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
    }

    private int recursiveUpdate(final Connection connection, final Object data) throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            int result = oracleDataService.execute(
                    connection,
                    oracleMetadata.packageName(),
                    oracleMetadata.updateMethod(),
                    jpubType
            );

            if (result > 0) {

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List)property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            int updateResponse = recursiveUpdate(connection, object);
                            if (updateResponse == -1) {
                                throw new SandataRuntimeException(String.format("UPDATE: Failed: [%s]",
                                        object.getClass().getName()));
                            }
                        }
                    }
                }

                // SUCCESS
                return 1;

            } // if (result > 0)

            // FAILED
            return -1;
        }
        catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
