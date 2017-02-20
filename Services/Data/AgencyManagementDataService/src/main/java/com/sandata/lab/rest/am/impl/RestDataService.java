package com.sandata.lab.rest.am.impl;


import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.compliance.BusinessEntityComplianceRelationshipExt;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.training.BusinessEntityStaffTrainingRelationshipExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.setting.GenerateIdentifierSettings;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.am.api.DataService;
import com.sandata.lab.rest.am.app.AppContext;
import com.sandata.lab.rest.am.model.*;
import com.sandata.lab.rest.am.utils.AMStringUtil;

import oracle.jdbc.OracleTypes;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.am.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    private GeneratedIDConfigMapping generatedIDConfigMapping;

    @PropertyInject("{{app.tenant.key.conf.staff.id.setting}}")
    private String appTenantKeyConfStaffId;

    @PropertyInject("{{app.tenant.key.conf.patient.id.setting}}")
    private String appTenantKeyConfPatientId;

    @PropertyInject("{{app.tenant.key.last.gen.staff.id}}")
    private String appTenantKeyLastGenStaffId;

    @PropertyInject("{{app.tenant.key.last.gen.patient.id}}")
    private String appTenantKeyLastPatientId;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            if (exchange.getIn().getHeader("sequence_key") != null) {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        connectionType,
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);
                    exchange.getIn().setBody(result);
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                String[] params = new String[mcl.size()];

                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = (String) mcl.get(index);
                }

                result = oracleDataService.executeGet(
                        connectionType,
                        packageName,
                        methodName,
                        className,
                        params
                );

                exchange.getIn().setBody(result);
            }

            if (methodName.equalsIgnoreCase("getBeStaffTrngCtgyLkup")) {
                if (result instanceof BusinessEntityStaffTrainingCategoryLookup) {
                    BusinessEntityStaffTrainingCategoryLookup beStaffTrngCatLkup = (BusinessEntityStaffTrainingCategoryLookup) result;
                    beStaffTrngCatLkup.getStaffTrainingCategoryService()
                            .addAll(oracleDataService.getBeStaffTrngCtgySvcForCtgyCode(beStaffTrngCatLkup.getBusinessEntityID(),
                                    beStaffTrngCatLkup.getStaffTrainingCategoryCode()));
                } else if (result instanceof List) {
                    for (Object object : (List) result) {
                        if (object instanceof BusinessEntityStaffTrainingCategoryLookup) {
                            BusinessEntityStaffTrainingCategoryLookup beStaffTrngCatLkup = (BusinessEntityStaffTrainingCategoryLookup) object;
                            beStaffTrngCatLkup.getStaffTrainingCategoryService()
                                    .addAll(oracleDataService.getBeStaffTrngCtgySvcForCtgyCode(beStaffTrngCatLkup.getBusinessEntityID(),
                                            beStaffTrngCatLkup.getStaffTrainingCategoryCode()));
                        }
                    }
                }
            } else if (methodName.equalsIgnoreCase("getBeStaffTrngRel")
                    && result instanceof BusinessEntityStaffTrainingRelationship) {
                BusinessEntityStaffTrainingRelationship beStaffTrngRel = (BusinessEntityStaffTrainingRelationship) result;
                beStaffTrngRel.getBusinessEntityStaffTrainingRelationshipDetail()
                        .addAll(oracleDataService.getBeStaffTrngRelDetlForParentSk(
                                beStaffTrngRel.getBusinessEntityStaffTrainingRelationshipSK().longValue()));
            } else if (methodName.equalsIgnoreCase("getBeStaffTrngLkup")
                    && result instanceof BusinessEntityStaffTrainingLookup) {
                BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup = (BusinessEntityStaffTrainingLookup) result;
                businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingRelationship().addAll(
                        getBeStaffTrngRelExtForBeAndTrngCode(businessEntityStaffTrainingLookup.getBusinessEntityID(),
                                businessEntityStaffTrainingLookup.getStaffTrainingCode()));
                businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingCategoryList().addAll(
                        getBeStaffTrngCtgyLstForBeAndTrngCode(businessEntityStaffTrainingLookup.getBusinessEntityID(),
                                businessEntityStaffTrainingLookup.getStaffTrainingCode()));

            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                    connectionType,
                    packageName,
                    methodName,
                    sequenceKey
            );

            if (methodName.equalsIgnoreCase("deleteBeCompCtgyLkup")
                    && result == 1) {
                int numRemovedCompCtgyLinks = oracleDataService.removeCategoryToComplianceLink(sequenceKey);
                logger.info(format("Removed link between deleted compliance category with SK %s and %s compliance items.",
                        sequenceKey,
                        numRemovedCompCtgyLinks));
            } else if (methodName.equalsIgnoreCase("deleteBeStaffTrngLkup")
                    && result == 1) {
                int numRemovedStaffTrngCtgyLinks = oracleDataService.removeStaffTrainingToCategoryLink(sequenceKey);
                logger.info(format("Removed link between deleted staff training class with SK %s and %s staff training categories.",
                        sequenceKey,
                        numRemovedStaffTrngCtgyLinks));
            } else if ("deleteBeCompLkup".equalsIgnoreCase(methodName)) {
                // GEOR-6500: we have to update column to be full fill to be empty due to the record has been deleted
                int numRemovedBeCompLkupFullfill = oracleDataService.removedBeCompLkupFullfill(sequenceKey);
                logger.info(format("Removed Fullfill column for Result which Copliance Item is deleted",
                        sequenceKey,
                        numRemovedBeCompLkupFullfill));
            }

            exchange.getIn().setBody(result);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Deletes records in PAYER_BILLING_CODE_LKUP table by BE_ID and PAYER_ID and BILLING_CODE
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void deletePayerBillingCodeLkupIdList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            // Validating billing codes
            List<String> payerBillingCodeLstIdList = exchange.getIn().getBody(List.class);

            oracleDataService.deletePayerBillingCodeLkupIdList(bsnEntId, payerId, payerBillingCodeLstIdList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get a PayerBillingCodeLookup by BE_ID and PAYER_ID
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getPayerBillingCodeByBEAndPayerID(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String businessEntityID = (String) exchange.getIn().getHeader("bsn_ent_id");

            String payerID = (String) exchange.getIn().getHeader("payer_id");

            if (StringUtil.IsNullOrEmpty(businessEntityID)) {
                throw new SandataRuntimeException(exchange, "Business Entity ID is required!");
            }

            List<PayerBillingCodeLookup> payerBillingCodeLookupList =
                    oracleDataService.getPayerBillingCodeByBEAndPayerID(businessEntityID, payerID);

            exchange.getIn().setBody(payerBillingCodeLookupList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get Contract Tasks by ContractID
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getContractTasksbyContractID(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String contractId = (String) exchange.getIn().getHeader("contract_id");

            String serviceName = (String) exchange.getIn().getHeader("svc_name");

            if (StringUtil.IsNullOrEmpty(contractId)) {
                throw new SandataRuntimeException(exchange, "Contract ID is required!");
            }

            List<ContractTask> contractTasks =
                    oracleDataService.getContractTasksByContractID(contractId, serviceName);

            exchange.getIn().setBody(contractTasks);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }


    /**
     * Gets a BusinessEntityComplianceLookup By sk
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getBEComplianceLookup(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            if (sequenceKey == 0) {
                throw new SandataRuntimeException(exchange, "(sequence_key) is required!");
            }

            BusinessEntityComplianceLookup businessEntityComplianceLookup = getBusinessEntityComplianceLookup(sequenceKey);

            exchange.getIn().setBody(businessEntityComplianceLookup);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private BusinessEntityComplianceLookup getBusinessEntityComplianceLookup(long sequenceKey) {

        BusinessEntityComplianceLookup businessEntityComplianceLookup
                = oracleDataService.getBEComplianceLookup(BigInteger.valueOf(sequenceKey));


        if (businessEntityComplianceLookup != null) {

            String businessEntityId = businessEntityComplianceLookup.getBusinessEntityID();
            String compCode = businessEntityComplianceLookup.getComplianceCode();

            if (!IsNullOrEmpty(compCode)) {

                businessEntityComplianceLookup.getBusinessEntityComplianceAdditionalInformationLookup()
                        .addAll(oracleDataService.getBEComplianceAddInfoByCompCode(businessEntityId, compCode));


                List<BusinessEntityComplianceRelationshipExt> businessEntityComplianceRelationships =
                        getBeCompRelExtForBeAndTrngCode(businessEntityComplianceLookup.getBusinessEntityID(),
                                businessEntityComplianceLookup.getComplianceCode());

                if (businessEntityComplianceRelationships != null && businessEntityComplianceRelationships.size() > 0) {
                    for (BusinessEntityComplianceRelationshipExt businessEntityComplianceRelationship : businessEntityComplianceRelationships) {

                        businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipDetail().addAll(
                                oracleDataService.getBeCompRelDetlForParentSk(businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipSK(),
                                        businessEntityComplianceRelationship.getBusinessEntityID()));
                    }
                }

                businessEntityComplianceLookup.getBusinessEntityComplianceRelationship().addAll(businessEntityComplianceRelationships);

                businessEntityComplianceLookup.getMedicalExaminationItemComplianceCrosswalk()
                        .addAll(oracleDataService.getBECompMedXwalkByCompCode(businessEntityId, compCode));

                businessEntityComplianceLookup.getStaffCompliance()
                        .addAll(oracleDataService.getStaffComplianceByCompCode(businessEntityId, compCode));

                businessEntityComplianceLookup.getBusinessEntityComplianceServiceList()
                        .addAll(oracleDataService.getBEComplianceServiceListByCompCode(businessEntityId, compCode));
            }

        }

        return businessEntityComplianceLookup;
    }


    /**
     * Deletes records in PAYER_LOB_LST table by BE_ID and PAYER_ID and BE_LOB
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void deletePayerLobLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> lobList = exchange.getIn().getBody(List.class);

            oracleDataService.deletePayerLobLstList(bsnEntId, payerId, lobList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Deletes records in PAYER_MDFR_LKUP table by BE_ID and PAYER_ID and MDFR_CODE
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void deletePayerMdfrLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> modifierCodeList = exchange.getIn().getBody(List.class);

            oracleDataService.deletePayerMdfrLstList(bsnEntId, payerId, modifierCodeList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Deletes records in PAYER_RATE_TYP_LST table by BE_ID and PAYER_ID and RATE_TYP_NAME
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void deletePayerRateTypLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            PayerListItemRequest payerListItemRequest = handlePayerListItemRequest(exchange);

            oracleDataService.deletePayerRateTypLstList(connection, payerListItemRequest);

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void deleteComplianceLookupCollections(BusinessEntityComplianceLookup businessEntityComplianceLookup) {

        long sequenceKey = businessEntityComplianceLookup.getBusinessEntityComplianceLookupSK().longValue();

        BusinessEntityComplianceLookup existingBeCompLook = getBusinessEntityComplianceLookup(sequenceKey);


        ConnectionType connectionType = ConnectionType.COREDATA;
        if (connectionType == null) {
            throw new SandataRuntimeException("Failed to define Oracle connection type.");

        }

        List<BusinessEntityComplianceAdditionalInformationLookup> businessEntityComplianceAdditionalInformationLookups
                = existingBeCompLook.getBusinessEntityComplianceAdditionalInformationLookup();

        if (businessEntityComplianceAdditionalInformationLookups.size() > 0) {


            for (BusinessEntityComplianceAdditionalInformationLookup businessEntityComplianceAdditionalInformationLookup : businessEntityComplianceAdditionalInformationLookups) {
                try {
                    OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntityComplianceAdditionalInformationLookup);

                    long result = oracleDataService.execute(
                            connectionType,
                            oracleMetadata.packageName(),
                            oracleMetadata.deleteMethod(),
                            businessEntityComplianceAdditionalInformationLookup.getBusinessEntityComplianceAdditionalInformationLookupSK()
                    );
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        }

        List<BusinessEntityComplianceRelationship> businessEntityComplianceRelationships
                = oracleDataService.getBEComplianceRelationshipByCompCode(existingBeCompLook.getBusinessEntityID(), existingBeCompLook.getComplianceCode());

        if (businessEntityComplianceRelationships.size() > 0) {


            for (BusinessEntityComplianceRelationship businessEntityComplianceRelationship : businessEntityComplianceRelationships) {
                try {

                    OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntityComplianceRelationship);

                    long result = oracleDataService.execute(
                            connectionType,
                            oracleMetadata.packageName(),
                            oracleMetadata.deleteMethod(),
                            businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipSK()
                    );

                    List<BusinessEntityComplianceRelationshipDetail> businessEntityComplianceRelationshipDetails =
                            oracleDataService.getBeCompRelDetlForParentSk(businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipSK(),
                                    businessEntityComplianceRelationship.getBusinessEntityID());

                    if (businessEntityComplianceRelationshipDetails.size() > 0) {

                        for (BusinessEntityComplianceRelationshipDetail businessEntityComplianceRelationshipDetail : businessEntityComplianceRelationshipDetails) {

                            try {
                                OracleMetadata oracleMetadata1 = DataMapper.getOracleMetadata(businessEntityComplianceRelationshipDetail);

                                long result1 = oracleDataService.execute(
                                        connectionType,
                                        oracleMetadata.packageName(),
                                        oracleMetadata.deleteMethod(),
                                        businessEntityComplianceRelationshipDetail.getBusinessEntityComplianceRelationshipDetailSK()
                                );
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        List<BusinessEntityComplianceServiceList> businessEntityComplianceServiceLists
                = existingBeCompLook.getBusinessEntityComplianceServiceList();


        if (businessEntityComplianceServiceLists.size() > 0) {
            for (BusinessEntityComplianceServiceList businessEntityComplianceServiceList : businessEntityComplianceServiceLists) {

                try {
                    OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntityComplianceServiceList);

                    long result = oracleDataService.execute(
                            connectionType,
                            oracleMetadata.packageName(),
                            oracleMetadata.deleteMethod(),
                            businessEntityComplianceServiceList.getBusinessEntityComplianceServiceListSK()
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteStaffTrainingLookupCollections(BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup, Connection connection) {

        try {

            ConnectionType connectionType = ConnectionType.COREDATA;
            long sequenceKey = businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingLookupSK().longValue();
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntityStaffTrainingLookup);
            Object getResult = oracleDataService.executeGet(
                    connectionType,
                    connection,
                    oracleMetadata.packageName(),
                    oracleMetadata.getMethod(),
                    "com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingLookup",
                    sequenceKey
            );

            List<BusinessEntityStaffTrainingLookup> businessEntityStaffTrainingLookupList = (List<BusinessEntityStaffTrainingLookup>) getResult;
            if (businessEntityStaffTrainingLookupList == null
                    || businessEntityStaffTrainingLookupList.size() != 1) {
                throw new SandataRuntimeException(format("Failed to retrieve BusinessEntityStaffTrainingLookup with SK %s!",
                        businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingLookupSK()));
            }

            BusinessEntityStaffTrainingLookup existingBusinessEntityStaffTrainingLookup = businessEntityStaffTrainingLookupList.get(0);

            // Delete each collection.
            oracleMetadata = DataMapper.getOracleMetadata(new BusinessEntityStaffTrainingRelationship());
            List<BusinessEntityStaffTrainingRelationship> businessEntityStaffTrainingRelationshipList = getBeStaffTrngRelForBeAndTrngCode(existingBusinessEntityStaffTrainingLookup.getBusinessEntityID(),
                    existingBusinessEntityStaffTrainingLookup.getStaffTrainingCode());
            for (BusinessEntityStaffTrainingRelationship businessEntityStaffTrainingRelationship : businessEntityStaffTrainingRelationshipList) {

                long result = oracleDataService.execute(
                        connection,
                        connectionType,
                        oracleMetadata.packageName(),
                        oracleMetadata.deleteMethod(),
                        businessEntityStaffTrainingRelationship.getBusinessEntityStaffTrainingRelationshipSK()
                );

                if (result < 0) {
                    throw new SandataRuntimeException(format("Failed to call %s.%s for BusinessEntityStaffTrainingRelationship with SK %s!", oracleMetadata.packageName(),
                            oracleMetadata.deleteMethod(),
                            businessEntityStaffTrainingRelationship.getBusinessEntityStaffTrainingRelationshipSK()));
                }

            }

//            oracleMetadata = DataMapper.getOracleMetadata(new StaffTrainingClassEvent());
//            List<StaffTrainingClassEvent> staffTrainingClassEventList = getBeStaffTrngClsEvntForBeAndTrngCode(existingBusinessEntityStaffTrainingLookup.getBusinessEntityID(),
//                existingBusinessEntityStaffTrainingLookup.getStaffTrainingCode());
//            for (StaffTrainingClassEvent staffTrainingClassEvent : staffTrainingClassEventList) {
//
//                int result = oracleDataService.execute(
//                    connection,
//                    connectionType,
//                    oracleMetadata.packageName(),
//                    oracleMetadata.deleteMethod(),
//                    staffTrainingClassEvent.getStaffTrainingClassEventSK()
//                );
//
//                if (result < 0) {
//                    throw new SandataRuntimeException(String.format("Failed to call %s.%s for staffTrainingClassEvent with SK %s!", oracleMetadata.packageName(),
//                        oracleMetadata.deleteMethod(),
//                        staffTrainingClassEvent.getStaffTrainingClassEventSK()));
//                }
//
//            }

            // delete all old Training category List
            oracleDataService.deleteBusinessEntityStaffTrainingCategoryList(connection,
                    existingBusinessEntityStaffTrainingLookup.getStaffTrainingCode(),
                    existingBusinessEntityStaffTrainingLookup.getBusinessEntityID());

//            oracleMetadata = DataMapper.getOracleMetadata(new BusinessEntityStaffTrainingRequirement());
//            List<BusinessEntityStaffTrainingRequirement> businessEntityStaffTrainingRequirementList = getBeStaffTrngRqmtForBeAndTrngCode(existingBusinessEntityStaffTrainingLookup.getBusinessEntityID(),
//                existingBusinessEntityStaffTrainingLookup.getStaffTrainingCode());
//            for (BusinessEntityStaffTrainingRequirement businessEntityStaffTrainingRequirement : businessEntityStaffTrainingRequirementList) {
//
//                int result = oracleDataService.execute(
//                    connection,
//                    connectionType,
//                    oracleMetadata.packageName(),
//                    oracleMetadata.deleteMethod(),
//                    businessEntityStaffTrainingRequirement.getBusinessEntityStaffTrainingRequirementSK()
//                );
//
//                if (result < 0) {
//                    throw new SandataRuntimeException(String.format("Failed to call %s.%s for BusinessEntityStaffTrainingRequirement with SK %s!", oracleMetadata.packageName(),
//                        oracleMetadata.deleteMethod(),
//                        businessEntityStaffTrainingRequirement.getBusinessEntityStaffTrainingRequirementSK()));
//                }
//            }

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    private void insertAllNewBusinessEntityStaffTrainingCategoryList(BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup,
                                                                     Connection connection) {

        ConnectionType connectionType = ConnectionType.COREDATA;
        // insert all new BusinessEntityStaffTrainingCategoryLists
        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(new BusinessEntityStaffTrainingCategoryList());
        for (BusinessEntityStaffTrainingCategoryList beStaffTrngCtgyLst : businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingCategoryList()) {
            beStaffTrngCtgyLst.setBusinessEntityStaffTrainingCategoryListSK(BigInteger.ZERO);
            Object jpubType = new DataMapper().map(beStaffTrngCtgyLst);
            oracleDataService.execute(connection,
                    connectionType,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType);
        }
        // extract all category list from BusinessEntityStaffTrainingLookup for later updating
        businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingCategoryList().clear();
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
        if (connectionType == null) {
            throw new SandataRuntimeException("Failed to define Oracle connection type.");
        }

        try {

            Object data = exchange.getIn().getBody();

            if (data instanceof BusinessEntityComplianceLookup) {
                deleteComplianceLookupCollections((BusinessEntityComplianceLookup) data);
            }

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            if (data instanceof BusinessEntityStaffTrainingLookup) {
                // GEOR-5398 : we will use only 1 connection/transaction at a time, then rollback if there is any exception
                deleteStaffTrainingLookupCollections((BusinessEntityStaffTrainingLookup) data, connection);
                insertAllNewBusinessEntityStaffTrainingCategoryList((BusinessEntityStaffTrainingLookup) data, connection);
            }

            long returnVal = executeRecursive(connection, connectionType, data, false /* UPDATE */, -999, exchange);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating COREDATA.PAYER_BILLING_CODE_LKUP table. This methods will firstly
     * deletes all records that match the combination of (bsnEntId, payerId, billingCode), then inserts
     * new records into the table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */

    public void updatePayerBillingCodeLstIdList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            // Validating billing codes
            List<String> payerBillingCodeLstIdList = exchange.getIn().getBody(List.class);

            oracleDataService.updatePayerBillingCodeLstIdList(bsnEntId, payerId, payerBillingCodeLstIdList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating COREDATA.PAYER_LOB_LST table. This methods will firstly
     * deletes all records that match the combination of (bsnEntId, payerId, businessEntityLOB), then inserts
     * new records into the table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updatePayerLobLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> lobList = exchange.getIn().getBody(List.class);

            oracleDataService.updatePayerLobLstList(bsnEntId, payerId, lobList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating COREDATA.PAYER_MDFR_LKUP table. This methods will firstly
     * deletes all records that match the combination of (bsnEntId, payerId, modifierCode), then inserts
     * new records into the table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updatePayerMdfrLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> modifierCodeList = exchange.getIn().getBody(List.class);

            oracleDataService.updatePayerMdfrLstList(bsnEntId, payerId, modifierCodeList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating COREDATA.PAYER_RATE_TYP_LST table. This methods will firstly
     * deletes all records that match the combination of (bsnEntId, payerId, rateTypName), then inserts
     * new records into the table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updatePayerRateTypLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> rateTypeNameList = exchange.getIn().getBody(List.class);

            oracleDataService.updatePayerRateTypLstList(bsnEntId, payerId, rateTypeNameList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
        if (connectionType == null) {
            throw new SandataRuntimeException("Failed to define Oracle connection type.");
        }

        try {

            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection(connectionType);

            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, connectionType, data, true, -999, exchange);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PAYER_BILLING_CODE_LKUP table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPayerBillingCodeLkupIdList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            // Validating billing codes
            List<String> payerBillingCodeLstIdList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPayerBillingCodeLkupIdList(connection, bsnEntId, payerId, payerBillingCodeLstIdList);

            connection.commit();

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PAYER_LOB_LST table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPayerLobLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> lobList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPayerLobLstList(connection, bsnEntId, payerId, lobList);

            connection.commit();

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PAYER_MDFR_LKUP table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPayerMdfrLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> modifierCodeList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPayerMdfrLstList(connection, bsnEntId, payerId, modifierCodeList);

            connection.commit();

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PAYER_RATE_TYP_LST table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPayerRateTypLstList(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            if (payerId == null || payerId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            List<String> rateTypeNameList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPayerRateTypLstList(connection, bsnEntId, payerId, rateTypeNameList);

            connection.commit();

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigInteger getSk(final Object data) {
        try {

            Field[] fields = data.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                Mapping mapping = field.getAnnotation(Mapping.class);
                if (mapping != null && mapping.index() == 0) {

                    return (BigInteger) field.get(data);
                }

            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private long executeRecursive(final Connection connection,
                                  final ConnectionType connectionType,
                                  final Object data,
                                  boolean bShouldInsert,
                                  long returnVal,
                                  Exchange exchange)
            throws SandataRuntimeException {

        try {

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (data instanceof BusinessEntityComplianceLookup) {
                String compName = ((BusinessEntityComplianceLookup) data).getComplianceName();
                String bsnEntId = ((BusinessEntityComplianceLookup) data).getBusinessEntityID();
                BigInteger beCompLkupSk = ((BusinessEntityComplianceLookup) data).getBusinessEntityComplianceLookupSK();

                if (!oracleDataService.isComplianceNameUnique(connection, compName, bsnEntId, beCompLkupSk)) {
                    throw new SandataRuntimeException(String.format("Not to %s BusinessEntityComplianceLookup due to Compliance name ='%s' is not unique",
                            bShouldInsert ? "insert" : "update",
                            compName));
                }
            }

            if (returnVal != -999) {
                if (data instanceof BusinessEntityAllergyLookup) {
                    setSk(jpubType, returnVal, "setBeAllergyLkupSk");
                } else if (data instanceof BusinessEntityDurableMedicalEquipmentAndSupplyLookup) {
                    setSk(jpubType, returnVal, "setBeDmeAndSupplyLkupSk");
                } else if (data instanceof NutritionalRequirementLookup) {
                    setSk(jpubType, returnVal, "setNutrRqmtLkupSk");
                } else if (data instanceof BusinessEntityStaffTrainingRelationshipDetail) {
                    setSk(jpubType, returnVal, "setBeStaffTrngRelSk");
                } else if (data instanceof BusinessEntityComplianceRelationshipDetail) {

                    setSk(jpubType, returnVal, "setBeCompRelSk");
                }

                if (data instanceof BusinessEntityComplianceServiceList
                        || data instanceof BusinessEntityComplianceRelationship
                        || data instanceof BusinessEntityComplianceAdditionalInformationLookup
                        || data instanceof MedicalExaminationItemComplianceCrosswalk
                        || data instanceof BusinessEntityComplianceRelationshipDetail) {


                    BigInteger sK = getSk(data);

                    //Determine whether to insert or update by getting the primary sk
                    if (sK == null || sK.longValue() < 1) {

                        bShouldInsert = true;
                        returnVal = -999;
                    }
                }
            }

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        connectionType,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            } else {
                // UPDATE
                if (data instanceof BusinessEntityAllergyLookup) {
                    // Since this is an update the SK can not be null
                    returnVal = ((BusinessEntityAllergyLookup) data).getBusinessEntityAllergyLookupSK().longValue();
                } else if (data instanceof BusinessEntityDurableMedicalEquipmentAndSupplyLookup) {
                    // Since this is an update the SK can not be null
                    returnVal = ((BusinessEntityDurableMedicalEquipmentAndSupplyLookup) data).getBusinessEntityDurableMedicalEquipmentAndSupplyLookupSK().longValue();
                } else if (data instanceof NutritionalRequirementLookup) {
                    // Since this is an update the SK can not be null
                    returnVal = ((NutritionalRequirementLookup) data).getNutritionalRequirementLookupSK().longValue();
                } else if (data instanceof BusinessEntityComplianceLookup) {
                    // Since this is an update the SK can not be null
                    returnVal = ((BusinessEntityComplianceLookup) data).getBusinessEntityComplianceLookupSK().longValue();
                } else if (data instanceof BusinessEntityComplianceRelationship) {
                    // Since this is an update the SK can not be null
                    returnVal = ((BusinessEntityComplianceRelationship) data).getBusinessEntityComplianceRelationshipSK().longValue();
                }


                result = oracleDataService.execute(
                        connection,
                        connectionType,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );
            }


            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, connectionType, object, bShouldInsert, returnVal, exchange);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(exchange, format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(exchange, format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void insertApplicationTenantKeyConfiguration(Exchange exchange) {

        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null
                || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Parameter business entity ID (bsn_ent_id) required!");
        }

        try {

            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = getApplicationTenantBusinessEntityCrosswalkForBsnEntId(bsnEntId);

            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();

            // Ensure configuration doesn't exist already for tenant SK and key name.
            if (oracleDataService.getAppTenantKeyConfigurationForAppTenantSkAndKey(
                    applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK().longValue(),
                    applicationTenantKeyConfiguration.getKeyName()) != null) {
                throw new SandataRuntimeException(format("ApplicationTenantKeyConfiguration already exists for business entity ID %s and key name %s!",
                        bsnEntId,
                        applicationTenantKeyConfiguration.getKeyName()));
            }

            // Set APP_TENANT_SK and call insert.
            applicationTenantKeyConfiguration.setApplicationTenantSK(applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK());
            insert(exchange);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void updateApplicationTenantKeyConfiguration(Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null
                || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Parameter business entity ID (bsn_ent_id) required!");
        }

        try {

            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = getApplicationTenantBusinessEntityCrosswalkForBsnEntId(bsnEntId);

            // Set APP_TENANT_SK and call update.
            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
            applicationTenantKeyConfiguration.setApplicationTenantSK(applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK());
            update(exchange);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void getApplicationTenantKeyConfigurationForBsnEntIdAndKeyName(Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null
                || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Parameter business entity ID (bsn_ent_id) required!");
        }

        String keyName = (String) exchange.getIn().getHeader("key_name");
        if (keyName == null
                || keyName.isEmpty()) {
            throw new SandataRuntimeException("Parameter key name (key_name) required!");
        }

        try {

            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = getApplicationTenantBusinessEntityCrosswalkForBsnEntId(bsnEntId);

            exchange.getIn().setBody(
                    oracleDataService.getAppTenantKeyConfigurationForAppTenantSkAndKey(
                            applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK().longValue(),
                            keyName));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void getApplicationSystemKeyConfigurationForKeyName(Exchange exchange) {

        String keyName = (String) exchange.getIn().getHeader("key_name");
        if (StringUtil.IsNullOrEmpty(keyName)) {
            throw new SandataRuntimeException("Parameter key name (key_name) required!");
        }

        try {

            exchange.getIn().setBody(
                    oracleDataService.getAppSystemKeyConfigurationForAppKey(keyName));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    private ApplicationTenantBusinessEntityCrosswalk getApplicationTenantBusinessEntityCrosswalkForBsnEntId(String bsnEntId) {
        List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
                ConnectionType.METADATA,
                "PKG_APP",
                "getAppTenantBeXwalk",
                "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
                bsnEntId
        );

        // Returned List must not be null, empty, or contain more than one.
        if (applicationTenantBusinessEntityCrosswalkList == null
                || applicationTenantBusinessEntityCrosswalkList.isEmpty()
                || applicationTenantBusinessEntityCrosswalkList.size() != 1) {
            throw new SandataRuntimeException(format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", bsnEntId));
        }

        return applicationTenantBusinessEntityCrosswalkList.get(0);
    }

    /**
     * Add list of payer service list into database
     *
     * @param exchange
     */
    public void insertPayerSvcLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            PayerListItemRequest payerListItemRequest = handlePayerListItemRequest(exchange);

            if (insertPayerServiceList(connection, payerListItemRequest)) {
                connection.commit();

                exchange.getIn().setBody(0);
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Update list of payer service list into database
     *
     * @param exchange
     */
    public void updatePayerSvcLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            PayerListItemRequest payerListItemRequest = handlePayerListItemRequest(exchange);

            // which items were de-seleteced will be remove from inactive contract
            oracleDataService.deletePayerServiceContractFromInactiveContract(connection, payerListItemRequest.getBusinessEntityId(), 
                    payerListItemRequest.getPayerId(), payerListItemRequest.getListItems());
            
            // Delete all services first, then insert the new ones
            if (deletePayerServiceList(connection, payerListItemRequest)) {

                if (insertPayerServiceList(connection, payerListItemRequest)) {

                    connection.commit();
                }
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Delete list of payer service list into database
     *
     * @param exchange
     */
    public void deletePayerSvcLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            PayerListItemRequest payerListItemRequest = handlePayerListItemRequest(exchange);

            // Delete all services first, then insert the new ones
            for (String serviceName : payerListItemRequest.getListItems()) {

                oracleDataService.deletePayerService(connection,
                        payerListItemRequest.getPayerId(),
                        payerListItemRequest.getBusinessEntityId(),
                        serviceName);
            }

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private boolean deletePayerServiceList(Connection connection, PayerListItemRequest payerListItemRequest)
            throws SandataRuntimeException {

        try {

            oracleDataService.deletePayerServiceList(connection,
                    payerListItemRequest.getPayerId(),
                    payerListItemRequest.getBusinessEntityId());

            return true;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    private boolean insertPayerServiceList(Connection connection, PayerListItemRequest payerListItemRequest)
            throws SandataRuntimeException {

        try {

            oracleDataService.insertPayerServiceLstList(connection,
                    payerListItemRequest.getPayerId(),
                    payerListItemRequest.getBusinessEntityId(),
                    payerListItemRequest.getListItems());

            return true;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    private PayerListItemRequest handlePayerListItemRequest(Exchange exchange) throws SandataRuntimeException {

        PayerListItemRequest payerListItemRequest = new PayerListItemRequest();

        List<String> serviceNameList = exchange.getIn().getBody(List.class);
        if (serviceNameList == null || serviceNameList.size() == 0) {
            throw new SandataRuntimeException("Must provide at least one service to insert/update/delete!");
        }

        String payerId = (String) exchange.getIn().getHeader("payer_id");
        if (IsNullOrEmpty(payerId)) {
            throw new SandataRuntimeException("PayerID (payer_id) is required!");
        }

        String bsnEntID = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (IsNullOrEmpty(bsnEntID)) {
            throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
        }

        payerListItemRequest.setListItems(serviceNameList);
        payerListItemRequest.setPayerId(payerId);
        payerListItemRequest.setBusinessEntityId(bsnEntID);

        return payerListItemRequest;
    }

    /**
     * Generating staff or patientID base on pre-defined configuration in database metadata
     *
     * @param exchange
     */
    public void getGeneratedStaffOrPatientID(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        try {
            // Use BE_ID to look up APP_TENANT_SK.
            String businessEntityId = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (businessEntityId == null) {
                throw new SandataRuntimeException("parameter required!");
            }

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
                    connectionType,
                    "PKG_APP",
                    "getAppTenantBeXwalk",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
                    businessEntityId
            );

            // Returned List must not be null, empty, or contain more than one.
            if (applicationTenantBusinessEntityCrosswalkList == null
                    || applicationTenantBusinessEntityCrosswalkList.isEmpty()
                    || applicationTenantBusinessEntityCrosswalkList.size() != 1) {
                throw new SandataRuntimeException(format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", businessEntityId));
            }

            //GET Tenant_BE_XWALK record by bsn_id , there's always one bsn_ent match with one tenant , i.e 1-1 relationship
            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = applicationTenantBusinessEntityCrosswalkList.get(0);

            //looking up the TENANT_KEY_CONFIG
            String operationName = (String) exchange.getIn().getHeader("operationName");
            String keyName = StringUtils.EMPTY;
            String lastGenIdKeyName = StringUtils.EMPTY;
            String tableName = StringUtils.EMPTY;
            if (operationName.contains("PKG_APP_generateStaffId")) {
                keyName = appTenantKeyConfStaffId;
                lastGenIdKeyName = appTenantKeyLastGenStaffId;
                tableName = "STAFF";
            } else if (operationName.contains("PKG_APP_generatePatientId")) {
                keyName = appTenantKeyConfPatientId;
                lastGenIdKeyName = appTenantKeyLastPatientId;
                tableName = "PT";
            }

            ApplicationTenantKeyConfiguration appTenantKeyConfgSetting = oracleDataService.getAppTenantKeyConfigurationForAppTenantSkAndKey(
                    applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK().longValue(), keyName);
            GenerateIdentifierSettings generateIdentifierSettings = null;
            if (appTenantKeyConfgSetting == null) {
                //if not exist configuration key , then insert the key and return the value of this key
                String valueByKey = generatedIDConfigMapping.getConfigMap().get(keyName).trim();
                insertAppTenantKey(businessEntityId, keyName, valueByKey, exchange);
                generateIdentifierSettings = (GenerateIdentifierSettings) GSONProvider.FromJson(valueByKey, GenerateIdentifierSettings.class);
                //throw new SandataRuntimeException(String.format("Key : %s for bsn_ent_id %s not defined in database", keyName, businessEntityId));
            } else {
                //if existing config key
                generateIdentifierSettings =
                        (GenerateIdentifierSettings) GSONProvider.FromJson(appTenantKeyConfgSetting.getTenantKeyConfigurationValue(), GenerateIdentifierSettings.class);
            }

            // in case of invalid configuration value, not JSON format or type not is NUMERIC
            if (generateIdentifierSettings == null || !"NUMERIC".equals(generateIdentifierSettings.getType().toUpperCase())) {
                throw new SandataRuntimeException(format("Invalid configuaration value ,please check again, the value of key :%s for business id: %s  , type must be %s", keyName, businessEntityId, "NUMERIC"));
            }

            //looking up the last gen id
            ApplicationTenantKeyConfiguration lastGenIdAppTenantKeyConfg = oracleDataService.getAppTenantKeyConfigurationForAppTenantSkAndKey(
                    applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK().longValue(), lastGenIdKeyName);

            String lastGenId = null;
            if (lastGenIdAppTenantKeyConfg == null || applicationTenantBusinessEntityCrosswalkList.isEmpty()) {
                // if the key not existing, then insert
                String sqlToGetLatestId = generatedIDConfigMapping.getConfigMap().get(lastGenIdKeyName).trim();
                sqlToGetLatestId = format(sqlToGetLatestId, businessEntityId, businessEntityId);
                lastGenId = oracleDataService.getLatestStaffOrPatientID(sqlToGetLatestId, tableName);
                //in case of latest id not is numeric data, throw exception, currently just support numeric data.
                if (!AMStringUtil.isNumber(lastGenId)) {
                    String id = tableName.equals("PT") ? "PT_ID" : "STAFF_ID";
                    throw new SandataRuntimeException(format("The latest  %s = %s of table %s is not numric value, currently just support to generate numeric ids, please update the latest %s", id, lastGenId, tableName, id));
                }

                //insert new record to config table
                insertAppTenantKey(businessEntityId, lastGenIdKeyName, lastGenId, exchange);
            } else {
                //if existing the key
                lastGenId = lastGenIdAppTenantKeyConfg.getTenantKeyConfigurationValue();
            }

            lastGenId = lastGenId != null ? lastGenId : "0";
            do {

                lastGenId = String.valueOf(Long.valueOf(lastGenId) + 1);

                // if the fixed length is configured, prioritize the fixed length id
                if (generateIdentifierSettings.getFixedLength() > 0) {
                    lastGenId = formatFixedLengthId(generateIdentifierSettings.getFixedLength(),
                            generateIdentifierSettings.getFixedLengthStartDigit(),
                            lastGenId);
                } else if (generateIdentifierSettings.isPadWithLeadingZeros()) {
                    lastGenId = addingLeadingZero(generateIdentifierSettings.getMaxLength(), lastGenId);
                }

            } while (isExistingId(lastGenId, businessEntityId, tableName));

            // update last gen key
            lastGenIdAppTenantKeyConfg.setTenantKeyConfigurationValue(String.valueOf(lastGenId));
            Object jpubType = new DataMapper().map(lastGenIdAppTenantKeyConfg);
            //update value of the ley
            oracleDataService.execute(
                    connectionType,
                    "PKG_APP",
                    "updateAppTenantKeyConf",
                    jpubType
            );


            //set generated id to response
            exchange.getIn().setBody(lastGenId);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        }

        logger.stop();

    }

    /**
     * Retrieves all BusinessEntityStaffTraining entities for specified parameters.
     *
     * @param exchange Specified Exchange.
     */
    public void getBeStaffTrngWithPaginationSortAndOption(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                    || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngName = (String) exchange.getIn().getHeader("staff_trng_name");
            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getBeStaffTrngWithPaginationSortAndOption(bsnEntId,
                    staffTrngName,
                    page,
                    pageSize,
                    sortOn,
                    direction);

            List<BusinessEntityStaffTrainingLookup> businessEntityStaffTrainingLookupList = (List<BusinessEntityStaffTrainingLookup>) response.getData();

            // Get and attach child entities for each.
            for (BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup : businessEntityStaffTrainingLookupList) {

                businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingRelationship().addAll(
                        getBeStaffTrngRelExtForBeAndTrngCode(businessEntityStaffTrainingLookup.getBusinessEntityID(),
                                businessEntityStaffTrainingLookup.getStaffTrainingCode()));

                businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingCategoryList().addAll(
                        getBeStaffTrngCtgyLstForBeAndTrngCode(businessEntityStaffTrainingLookup.getBusinessEntityID(),
                                businessEntityStaffTrainingLookup.getStaffTrainingCode()));
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(businessEntityStaffTrainingLookupList);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private void insertAppTenantKey(String bsnEntId, String keyName, String valueByKey, final Exchange exchange) throws SQLException {

        ApplicationTenantKeyConfiguration appTenantKeyConfig = new ApplicationTenantKeyConfiguration();
        appTenantKeyConfig.setKeyName(keyName);
        appTenantKeyConfig.setTenantKeyConfigurationValue(valueByKey);
        //get default time
        appTenantKeyConfig.setRecordCreateTimestamp(Calendar.getInstance().getTime());
        appTenantKeyConfig.setRecordUpdateTimestamp(Calendar.getInstance().getTime());
        ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = getApplicationTenantBusinessEntityCrosswalkForBsnEntId(bsnEntId);
        // Set APP_TENANT_SK and call insert.
        appTenantKeyConfig.setApplicationTenantSK(applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK());

        exchange.getIn().setBody(appTenantKeyConfig);
        insert(exchange);
    }

    private boolean isExistingId(String patientOrStaffId, String bsnEntId, String tableName) {

        String id = "";
        if ("PT".equals(tableName)) {
            id = "PT_ID";
        } else if ("STAFF".equals(tableName)) {
            id = "STAFF_ID";
        }

        String sql = format("SELECT %s FROM %s.%s WHERE %s = ? AND BE_ID = ?", id, ConnectionType.COREDATA, tableName, id);
        return oracleDataService.isExistingPatientOrStaffId(patientOrStaffId, bsnEntId, sql, id);

    }

    private String addingLeadingZero(int maxLength, String generatedId) {

        StringBuilder leadingZero = new StringBuilder();
        int numberOfLeadingZero = maxLength - generatedId.length();
        for (int i = 0; i < numberOfLeadingZero; i++) {
            leadingZero.append("0");
        }

        leadingZero.append(generatedId);

        return leadingZero.toString();
    }

    private String formatFixedLengthId(int fixedLength, int fixedLengthStartDigit, String lastGenId) {
        if (fixedLengthStartDigit < 1 || fixedLengthStartDigit > 9) {
            throw new SandataRuntimeException(
                    format("The fixed length number must start with (current is %s) a number from 1 - 9", fixedLengthStartDigit));
        }

        if (fixedLength == lastGenId.length()) {
            // it's already configured with fixed length, just update the start digit
            return fixedLengthStartDigit + lastGenId.substring(1);
        } else if (fixedLength > lastGenId.length()) {
            // add more zeros to generated id to match fixed length
            return format(fixedLengthStartDigit + "%0" + (fixedLength - 1) + "d", Long.valueOf(lastGenId));
        } else {
            throw new SandataRuntimeException(format("The generated Id reached the limit of fixed length = %d, start digit = %d, last generated Id = %s",
                    fixedLength,
                    fixedLengthStartDigit,
                    lastGenId));
        }
    }

    public void getStaffTrngClassesByService(final Exchange exchange) {
        // Extract parameters and null check.

        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");

        if (bsnEntId == null || bsnEntId.isEmpty()) {

            throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
        }

        String serviceNameString = (String) exchange.getIn().getHeader("svc_name");

        if (serviceNameString == null || serviceNameString.isEmpty()) {

            throw new SandataRuntimeException("Service Name (svc_name) required!");
        }

        ServiceName serviceName = ServiceName.fromValue(serviceNameString);

        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String direction = (String) exchange.getIn().getHeader("direction");

        String sortColumn = "REC_EFF_TMSTP";

        if (sortOn.equals("eff_date")) {

            sortColumn = "REC_EFF_TMSTP";
        } else if (sortOn.equals("name")) {

            sortColumn = "STAFF_TRNG_NAME";
        }


        Response response = oracleDataService.getBEStaffTrngClassesByService(bsnEntId,
                serviceName,
                page,
                pageSize,
                sortColumn,
                direction);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
        exchange.getIn().setBody(response.getData());
    }

    /**
     * Gets Staff Training Classes by Category
     *
     * @param exchange
     */
    public void getStaffTrngClassesByCategory(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
        }

        String staffTrngName = (String) exchange.getIn().getHeader("staff_trng_name");
        String category = (String) exchange.getIn().getHeader("category");
        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String direction = (String) exchange.getIn().getHeader("direction");


        Response response = oracleDataService.getBEStaffTrngClassesByCategory(bsnEntId,
                category,
                staffTrngName,
                page,
                pageSize,
                sortOn,
                direction);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
        exchange.getIn().setBody(response.getData());
    }

    public void getCompClassesByService(final Exchange exchange) {
        // Extract parameters and null check.

        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");

        if (bsnEntId == null || bsnEntId.isEmpty()) {

            throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
        }

        String serviceNameString = (String) exchange.getIn().getHeader("svc_name");

        if (serviceNameString == null || serviceNameString.isEmpty()) {

            throw new SandataRuntimeException("Service Name (svc_name) required!");
        }

        ServiceName serviceName = ServiceName.fromValue(serviceNameString);

        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String direction = (String) exchange.getIn().getHeader("direction");

        String sortColumn = "COMP_EFF_TMSTP";

        if (sortOn.equals("eff_date")) {

            sortColumn = "COMP_EFF_TMSTP";
        } else if (sortOn.equals("name")) {

            sortColumn = "COMP_NAME";
        }


        Response response = oracleDataService.getBECompByService(bsnEntId,
                serviceName,
                page,
                pageSize,
                sortColumn,
                direction);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", sortColumn);
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", direction);
        exchange.getIn().setBody(response.getData());
    }

    public void getStaffTrainingLocationByBE(final Exchange exchange) {
        // Extract parameters and null check.

        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");

        if (bsnEntId == null || bsnEntId.isEmpty()) {

            throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
        }


        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String direction = (String) exchange.getIn().getHeader("direction");

        String sortColumn = "REC_EFF_TMSTP";

        if (sortOn.equals("eff_date")) {

            sortColumn = "REC_EFF_TMSTP";
        } else if (sortOn.equals("name")) {

            sortColumn = "STAFF_TRNG_LOC_NAME";
        }

        Response response = oracleDataService.getStaffTrainingLocationByBusinessEntity(bsnEntId,
                page,
                pageSize,
                sortColumn,
                direction);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
        exchange.getIn().setBody(response.getData());
    }

    public void getCompByCategory(final Exchange exchange) {


        // Validating params
        String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
        if (bsnEntId == null || bsnEntId.length() == 0) {
            throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
        }

        // Extract parameters and null check.
        String category = (String) exchange.getIn().getHeader("category");
        int page = (int) exchange.getIn().getHeader("page");
        int pageSize = (int) exchange.getIn().getHeader("page_size");
        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String direction = (String) exchange.getIn().getHeader("direction");


        String sortColumn;
        String sortTable;
        if (sortOn.equals("name")) {
            sortTable = "T1";
            sortColumn = "COMP_NAME";
        } else if (sortOn.equalsIgnoreCase("category_name")) {
            sortTable = "T2";
            sortColumn = "COMP_CTGY_NAME";
        } else {
            sortTable = "T1";
            sortColumn = "COMP_EFF_DATE";
        }

        Response response = oracleDataService.getAllBEComplianceFilteredByCategory(bsnEntId, category, page, pageSize, sortTable, sortColumn, direction);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("PAGE", response.getPage());
        exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
        exchange.getIn().setBody(response.getData());
    }

    public void getStaffPosition(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null || bsnEntId.isEmpty()) {

            throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
        }

        Response response = oracleDataService.getStaffPositionsByBeId(bsnEntId);
        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setBody(response.getData());
    }

    /**
     * Returns all elements of an Enum class
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getReferenceValue(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String entityClass = format("%s.%s", "com.sandata.lab.data.model.dl.model", methodParts[2]);

            exchange.getIn().setBody(new ArrayList<>(EnumSet.allOf((Class<? extends Enum>) Class.forName(entityClass))));
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBeStaffTrngRelForStaffTrngCode(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                    || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngCode = (String) exchange.getIn().getHeader("staff_trng_code");
            if (staffTrngCode == null
                    || staffTrngCode.isEmpty()) {
                throw new SandataRuntimeException("Staff training code (staff_trng_code) required!");
            }

            List<BusinessEntityStaffTrainingRelationship> beStaffTrngRelList = oracleDataService.getBeStaffTrngRelForStaffTrngCode(bsnEntId, staffTrngCode);
            for (BusinessEntityStaffTrainingRelationship beStaffTrngRel : beStaffTrngRelList) {
                beStaffTrngRel.getBusinessEntityStaffTrainingRelationshipDetail().addAll(
                        oracleDataService.getBeStaffTrngRelDetlForParentSk(beStaffTrngRel.getBusinessEntityStaffTrainingRelationshipSK().longValue()));
            }

            exchange.getIn().setBody(beStaffTrngRelList);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBeLobLkupPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getBeLobLkupPK(bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void insertBeLobLkup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("insertBeLobLkup: START");

        Connection connection = null;

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            BusinessEntityLineOfBusinessLookup beLobLkup = exchange.getIn().getBody(BusinessEntityLineOfBusinessLookup.class);

            if (beLobLkup == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: BusinessEntityLineOfBusinessLookup === NULL");
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.insertBeLobLkup(connection, bsnEntId, beLobLkup));

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: insertBeLobLkup: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.trace("insertBeLobLkup: END");

            logger.stop();
        }
    }

    public void updateBeLobLkup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("updateBeLobLkup: START");

        Connection connection = null;

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            BusinessEntityLineOfBusinessLookup beLobLkup = exchange.getIn().getBody(BusinessEntityLineOfBusinessLookup.class);

            if (beLobLkup == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: BusinessEntityLineOfBusinessLookup === NULL");
            }

            // Delete the current records when doing an update
            long result = oracleDataService.deleteBeLobLkup(beLobLkup.getBusinessEntityLineOfBusinessLookupSK().longValue());
            if (result != 3) {
                // Expecting to delete three records!
                throw new SandataRuntimeException(exchange, "Validation Error: deleteBeLobLkup.result != 3");
            }

            beLobLkup.setBusinessEntityLineOfBusinessLookupSK(null);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.updateBeLobLkup(connection, bsnEntId, beLobLkup));

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: updateBeLobLkup: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.trace("updateBeLobLkup: END");

            logger.stop();
        }
    }

    public void deleteBeLobLkup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            Long sequenceKey = exchange.getIn().getHeader("sequence_key", Long.class);
            if (sequenceKey == null || sequenceKey <= 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityLineofBusinessLookupSK (sequence_key) is required!");
            }

            exchange.getIn().setBody(oracleDataService.deleteBeLobLkup(sequenceKey.longValue()));

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffWorkedHoursByRange(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null
                || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Parameter business entity ID (bsn_ent_id) required!");
        }

        String staffId = (String) exchange.getIn().getHeader("staff_id");
        if (staffId == null
                || staffId.isEmpty()) {
            throw new SandataRuntimeException("Parameter staff ID (staff_id) required!");
        }

        List<String> dateRanges = (List<String>) exchange.getIn().getHeader("ranges");
        if (dateRanges == null || dateRanges.isEmpty()) {
            throw new SandataRuntimeException("Parameter date ranges (ranges) required!");
        }

        try {
            List<StaffWorkedHoursResult> result = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);

            for (String dateRange : dateRanges) {
                String[] dateRangeArray = dateRange.split(",");

                if (dateRangeArray.length != 2) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must be followed format: YYYY-MM-DD'T'HH:mm:ssX,YYYY-MM-DD'T'HH:mm:ssX");
                }

                String dateFromString = dateRangeArray[0];
                Date dateFrom;
                try {
                    dateFrom = dateFormat.parse(dateFromString);
                } catch (Exception e) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must have 'from date' followed format: YYYY-MM-DD'T'HH:mm:ssX");
                }

                String dateToString = dateRangeArray[1];
                Date dateTo;
                try {
                    dateTo = dateFormat.parse(dateToString);
                } catch (Exception e) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must have 'to date' followed format: YYYY-MM-DD'T'HH:mm:ssX");
                }

                List<ScheduleEvent> scheduleEvents = oracleDataService.getScheduleEventByDateRangeForStaff(bsnEntId, staffId, dateFrom, dateTo);
                BigDecimal workedHours = BigDecimal.valueOf(0);

                for (ScheduleEvent scheduleEvent : scheduleEvents) {
                    Date lowerDate = dateFrom;
                    Date upperDate = dateTo;

                    if (scheduleEvent.getScheduleEventStartDatetime() != null
                            && lowerDate.compareTo(scheduleEvent.getScheduleEventStartDatetime()) < 0) {
                        lowerDate = scheduleEvent.getScheduleEventStartDatetime();
                    }

                    if (scheduleEvent.getScheduleEventEndDatetime() != null
                            && upperDate.compareTo(scheduleEvent.getScheduleEventEndDatetime()) > 0) {
                        upperDate = scheduleEvent.getScheduleEventEndDatetime();
                    }

                    BigDecimal hours = BigDecimal.valueOf(1d * (upperDate.getTime() - lowerDate.getTime()) / (1000d * 60 * 60));

                    workedHours = workedHours.add(hours);
                }

                StaffWorkedHoursResult staffWorkedHour = new StaffWorkedHoursResult();
                staffWorkedHour.setDateFrom(dateFrom);
                staffWorkedHour.setDateTo(dateTo);
                staffWorkedHour.setBsnEntId(bsnEntId);
                staffWorkedHour.setStaffId(staffId);
                staffWorkedHour.setWorkedHours(workedHours.setScale(2, RoundingMode.HALF_UP));
                result.add(staffWorkedHour);
            }

            exchange.getIn().setBody(result);
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void setGeneratedIDConfigMapping(GeneratedIDConfigMapping generatedIDConfigMapping) {
        this.generatedIDConfigMapping = generatedIDConfigMapping;
    }

    /**
     * This method used to insert a list of staff training category service into db
     *
     * @param exchange
     */
    public void saveStaffTrngCtgySvcList(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        try {

            List<StaffTrainingCategoryService> staffTrngCtgySvcList = (List) exchange.getIn().getBody();

            List<SaveStaffTrngCtgySvcResult> results = new ArrayList<>();

            for (int index = 0; index < staffTrngCtgySvcList.size(); index++) {

                SaveStaffTrngCtgySvcResult saveStaffTrngCtgySvcResult = new SaveStaffTrngCtgySvcResult();
                saveStaffTrngCtgySvcResult.setIndex(index);
                saveStaffTrngCtgySvcResult.setStaffTrainingCategoryServiceSK(-1);
                results.add(saveStaffTrngCtgySvcResult);

                try {
                    StaffTrainingCategoryService staffTrainingCategoryService = staffTrngCtgySvcList.get(index);

                    exchange.getIn().setBody(staffTrainingCategoryService);

                    // TODO: Use splitter pattern. This is too slow.
                    insert(exchange);

                    long resultVal = (long) exchange.getIn().getBody();
                    saveStaffTrngCtgySvcResult.setStaffTrainingCategoryServiceSK(resultVal);
                    if (staffTrainingCategoryService.getStaffTrainingCategoryServiceSK() == null || staffTrainingCategoryService.getStaffTrainingCategoryServiceSK().longValue() != resultVal) {
                        staffTrainingCategoryService.setStaffTrainingCategoryServiceSK(BigInteger.valueOf(resultVal));
                    }

                } catch (Exception ex) {

                    String errorMessage = format("[EXCEPTION=%s]: [MESSAGE=%s]: [Index=%d]",
                            getClass().getName(), ex.getMessage(), index);
                    saveStaffTrngCtgySvcResult.setErrorMessage(errorMessage);
                }
            }

            exchange.getIn().setBody(results);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }

    }

    /**
     * Delete a list of Staff Training Category Service by a give list of SK
     *
     * @param exchange
     */
    public void deleteStaffTrngCtgySvcList(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.deleteStaffTrngCtgySvcList(connection, exchange);

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }

    }

    /**
     * get the list of staff training category service
     *
     * @param exchange
     */
    public void getStaffTrngCtgySvcList(final Exchange exchange) {

        MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();
        String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
        if (IsNullOrEmpty(bsnEntId)) {
            throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
        }

        int page = (Integer) params.get(1);
        int pageSize = (Integer) params.get(2);
        String orderColumn = "STAFF_TRNG_CTGY_CODE";
        String sortOrder = "ASC";

        Response response = oracleDataService.findStaffTrngCtgySvc(bsnEntId, page, pageSize, orderColumn, sortOrder);

        exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
        exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
        exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

        exchange.getIn().setBody(response.getData());
    }

    public void getBeCompLkupWithPaginationSortAndOption(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                    || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String compName = (String) exchange.getIn().getHeader("comp_name");
            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getBeCompLkupWithPaginationSortAndOption(bsnEntId,
                    compName,
                    page,
                    pageSize,
                    sortOn,
                    direction);

            List<BusinessEntityComplianceLookup> businessEntityComplianceLookupList = (List<BusinessEntityComplianceLookup>) response.getData();

            //TODO: Should not have a query inside the for loop, there are a lot of trips to databases
            for (BusinessEntityComplianceLookup businessEntityComplianceLookup : businessEntityComplianceLookupList) {
                businessEntityComplianceLookup.getBusinessEntityComplianceRelationship().addAll(
                        getBeCompRelExtForBeAndTrngCode(businessEntityComplianceLookup.getBusinessEntityID(),
                                businessEntityComplianceLookup.getComplianceCode()));
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(businessEntityComplianceLookupList);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }

    }

    public void getPatientScheduledHoursByRange(final Exchange exchange) {
        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (bsnEntId == null
                || bsnEntId.isEmpty()) {
            throw new SandataRuntimeException("Parameter business entity ID (bsn_ent_id) required!");
        }

        String patientId = (String) exchange.getIn().getHeader("patient_id");
        if (patientId == null
                || patientId.isEmpty()) {
            throw new SandataRuntimeException("Parameter patient ID (patient_id) required!");
        }

        List<String> dateRanges = (List<String>) exchange.getIn().getHeader("ranges");
        if (dateRanges == null || dateRanges.isEmpty()) {
            throw new SandataRuntimeException("Parameter date ranges (ranges) required!");
        }

        try {
            List<PatientScheduledHoursResult> result = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);

            for (String dateRange : dateRanges) {
                String[] dateRangeArray = dateRange.split(",");

                if (dateRangeArray.length != 2) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must be followed format: YYYY-MM-DD'T'HH:mm:ssX,YYYY-MM-DD'T'HH:mm:ssX");
                }

                String dateFromString = dateRangeArray[0];
                Date dateFrom;
                try {
                    dateFrom = dateFormat.parse(dateFromString);
                } catch (Exception e) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must have 'from date' followed format: YYYY-MM-DD'T'HH:mm:ssX");
                }

                String dateToString = dateRangeArray[1];
                Date dateTo;
                try {
                    dateTo = dateFormat.parse(dateToString);
                } catch (Exception e) {
                    throw new SandataRuntimeException("Parameter date ranges (ranges) must have 'to date' followed format: YYYY-MM-DD'T'HH:mm:ssX");
                }

                List<ScheduleEvent> scheduleEvents = oracleDataService.getScheduleEventByDateRangeForPatient(bsnEntId, patientId, dateFrom, dateTo);
                BigDecimal workedHours = BigDecimal.valueOf(0);

                for (ScheduleEvent scheduleEvent : scheduleEvents) {
                    Date lowerDate = dateFrom;
                    Date upperDate = dateTo;

                    if (scheduleEvent.getScheduleEventStartDatetime() != null
                            && lowerDate.compareTo(scheduleEvent.getScheduleEventStartDatetime()) < 0) {
                        lowerDate = scheduleEvent.getScheduleEventStartDatetime();
                    }

                    if (scheduleEvent.getScheduleEventEndDatetime() != null
                            && upperDate.compareTo(scheduleEvent.getScheduleEventEndDatetime()) > 0) {
                        upperDate = scheduleEvent.getScheduleEventEndDatetime();
                    }

                    BigDecimal hours = BigDecimal.valueOf(1d * (upperDate.getTime() - lowerDate.getTime()) / (1000d * 60 * 60));

                    workedHours = workedHours.add(hours);
                }

                PatientScheduledHoursResult patientScheduledHoursResult = new PatientScheduledHoursResult();
                patientScheduledHoursResult.setDateFrom(dateFrom);
                patientScheduledHoursResult.setDateTo(dateTo);
                patientScheduledHoursResult.setBsnEntId(bsnEntId);
                patientScheduledHoursResult.setPatientId(patientId);
                ;
                patientScheduledHoursResult.setScheduledHours(workedHours.setScale(2, RoundingMode.HALF_UP));
                result.add(patientScheduledHoursResult);
            }

            exchange.getIn().setBody(result);
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    private List<BusinessEntityStaffTrainingRelationshipExt> getBeStaffTrngRelExtForBeAndTrngCode(String bsnEntId,
                                                                                                  String staffTrngCode) {
        // BusinessEntityStaffTrainingRelationship
        List<BusinessEntityStaffTrainingRelationship> businessEntityStaffTrainingRelationshipList = (List<BusinessEntityStaffTrainingRelationship>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                "SELECT * FROM BE_STAFF_TRNG_REL WHERE BE_ID= ? AND STAFF_TRNG_CODE = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                BusinessEntityStaffTrainingRelationship.class.getCanonicalName(),
                bsnEntId,
                staffTrngCode);
        List<BusinessEntityStaffTrainingRelationshipExt> businessEntityStaffTrainingRelationshipExtList = new ArrayList<>();

        for (BusinessEntityStaffTrainingRelationship businessEntityStaffTrainingRelationship : businessEntityStaffTrainingRelationshipList) {
            BusinessEntityStaffTrainingRelationshipExt businessEntityStaffTrainingRelationshipExt = new BusinessEntityStaffTrainingRelationshipExt(businessEntityStaffTrainingRelationship);

            if (businessEntityStaffTrainingRelationshipExt.getStaffTrainingCode() != null
                    && !businessEntityStaffTrainingRelationshipExt.getStaffTrainingCode().isEmpty()) {
                businessEntityStaffTrainingRelationshipExt.setStaffTrainingName(
                        oracleDataService.getBeStaffTrngLkupNameForCode(businessEntityStaffTrainingRelationshipExt.getBusinessEntityID(),
                                businessEntityStaffTrainingRelationshipExt.getStaffTrainingCode()));
            }

            if (businessEntityStaffTrainingRelationshipExt.getStaffTrainingParentCode() != null
                    && !businessEntityStaffTrainingRelationshipExt.getStaffTrainingParentCode().isEmpty()) {
                businessEntityStaffTrainingRelationshipExt.setParentStaffTrainingName(
                        oracleDataService.getBeStaffTrngLkupNameForCode(businessEntityStaffTrainingRelationshipExt.getBusinessEntityID(),
                                businessEntityStaffTrainingRelationshipExt.getStaffTrainingParentCode()));
            }

            businessEntityStaffTrainingRelationshipExtList.add(businessEntityStaffTrainingRelationshipExt);
        }

        return businessEntityStaffTrainingRelationshipExtList;
    }


    private List<BusinessEntityStaffTrainingRelationship> getBeStaffTrngRelForBeAndTrngCode(String bsnEntId,
                                                                                            String staffTrngCode) {
        return (List<BusinessEntityStaffTrainingRelationship>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                "SELECT * FROM BE_STAFF_TRNG_REL WHERE BE_ID= ? AND STAFF_TRNG_CODE = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                BusinessEntityStaffTrainingRelationship.class.getCanonicalName(),
                bsnEntId,
                staffTrngCode);
    }

    private List<BusinessEntityStaffTrainingCategoryList> getBeStaffTrngCtgyLstForBeAndTrngCode(String bsnEntId,
                                                                                                String staffTrngCode) {
        return (List<BusinessEntityStaffTrainingCategoryList>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                "SELECT * FROM BE_STAFF_TRNG_CTGY_LST WHERE BE_ID=? AND STAFF_TRNG_CODE=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')",
                BusinessEntityStaffTrainingCategoryList.class.getCanonicalName(),
                bsnEntId,
                staffTrngCode);
    }

    private List<StaffTrainingClassEvent> getBeStaffTrngClsEvntForBeAndTrngCode(String bsnEntId, String staffTrngCode) {
        return (List<StaffTrainingClassEvent>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                "SELECT * FROM STAFF_TRNG_CLS_EVNT WHERE BE_ID=? AND STAFF_TRNG_CODE=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')",
                StaffTrainingClassEvent.class.getCanonicalName(),
                bsnEntId,
                staffTrngCode);
    }

    private List<BusinessEntityStaffTrainingRequirement> getBeStaffTrngRqmtForBeAndTrngCode(String bsnEntId, String staffTrngCode) {
        return (List<BusinessEntityStaffTrainingRequirement>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                "SELECT * FROM BE_STAFF_TRNG_RQMT WHERE BE_ID=? AND STAFF_TRNG_CODE=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')",
                BusinessEntityStaffTrainingRequirement.class.getCanonicalName(),
                bsnEntId,
                staffTrngCode);
    }

    private List<BusinessEntityComplianceRelationshipExt> getBeCompRelExtForBeAndTrngCode(String bsnEntId,
                                                                                          String compCode) {
        List<BusinessEntityComplianceRelationship> businessEntityComplianceRelationshipList = (List<BusinessEntityComplianceRelationship>)
                oracleDataService.getEntitiesForId(ConnectionType.COREDATA,
                        "SELECT * " +
                                "FROM BE_COMP_REL T1 " +
                                "INNER JOIN  " +
                                "   (SELECT BE_ID, COMP_CODE " +
                                "   FROM BE_COMP_LKUP " +
                                "   WHERE  " +
                                "       SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) BETWEEN COMP_EFF_DATE AND COMP_TERM_DATE " +
                                "       AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                                "ON T1.COMP_CODE = T2.COMP_CODE AND T1.BE_ID = T2.BE_ID " +
                                "WHERE  " +
                                "  T1.BE_ID = ?  " +
                                "  AND T1.COMP_CODE = ?  " +
                                "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                        BusinessEntityComplianceRelationship.class.getCanonicalName(),
                        bsnEntId,
                        compCode);

        List<BusinessEntityComplianceRelationshipExt> businessEntityComplianceRelationshipExtList = new ArrayList<>();

        for (BusinessEntityComplianceRelationship businessEntityComplianceRelationship : businessEntityComplianceRelationshipList) {
            BusinessEntityComplianceRelationshipExt businessEntityComplianceRelationshipExt = new BusinessEntityComplianceRelationshipExt(businessEntityComplianceRelationship);

            if (businessEntityComplianceRelationshipExt.getComplianceCode() != null
                    && !businessEntityComplianceRelationshipExt.getComplianceCode().isEmpty()) {
                businessEntityComplianceRelationshipExt.setComplianceName(
                        oracleDataService.getBeStaffCompNameForCode(businessEntityComplianceRelationshipExt.getBusinessEntityID(),
                                businessEntityComplianceRelationshipExt.getComplianceCode()));
            }

            if (businessEntityComplianceRelationshipExt.getComplianceParentCode() != null
                    && !businessEntityComplianceRelationshipExt.getComplianceParentCode().isEmpty()) {
                businessEntityComplianceRelationshipExt.setParentComplianceName(
                        oracleDataService.getBeStaffCompNameForCode(businessEntityComplianceRelationshipExt.getBusinessEntityID(),
                                businessEntityComplianceRelationshipExt.getComplianceParentCode()));
            }

            businessEntityComplianceRelationshipExtList.add(businessEntityComplianceRelationshipExt);
        }

        return businessEntityComplianceRelationshipExtList;
    }

    public void getAppTenantKeyConfForName(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String keyName = (String) mcl.get(0);
            if (IsNullOrEmpty(keyName)) {
                throw new SandataRuntimeException("Key Name (key_name) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
                    connectionType,
                    "PKG_APP",
                    "getAppTenantBeXwalk",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
                    bsnEntId
            );

            // Returned List must not be null, empty, or contain more than one.
            if (applicationTenantBusinessEntityCrosswalkList == null
                    || applicationTenantBusinessEntityCrosswalkList.isEmpty()
                    || applicationTenantBusinessEntityCrosswalkList.size() != 1) {
                throw new SandataRuntimeException(format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", bsnEntId));
            }

            //GET Tenant_BE_XWALK record by bsn_id , there's always one bsn_ent match with one tenant , i.e 1-1 relationship
            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = applicationTenantBusinessEntityCrosswalkList.get(0);

            exchange.getIn().setBody(oracleDataService.getAppTenantKeyConfForName(connectionType, keyName, applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK(), logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void insertAppTenantKeyConfForBEID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration = exchange.getIn().getBody(ApplicationTenantKeyConfiguration.class);

            if (applicationTenantKeyConfiguration == null) {
                throw new SandataRuntimeException(format("%s: ERROR: ApplicationTenantKeyConfiguration == null",
                        getClass().getName()));
            }

            if (IsNullOrEmpty(applicationTenantKeyConfiguration.getKeyName())) {
                throw new SandataRuntimeException(format("%s: ERROR: applicationTenantKeyConfiguration.getKeyName() == null",
                        getClass().getName()));
            }

            if (applicationTenantKeyConfiguration.getKeyName().startsWith("MW_")) {
                throw new SandataRuntimeException(format("%s: ERROR: applicationTenantKeyConfiguration.getKeyName(): [%s]: Can not start with reserved prefix \"MW_\"!",
                        getClass().getName(), applicationTenantKeyConfiguration.getKeyName()));
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
                    connectionType,
                    "PKG_APP",
                    "getAppTenantBeXwalk",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
                    bsnEntId
            );

            // Returned List must not be null, empty, or contain more than one.
            if (applicationTenantBusinessEntityCrosswalkList == null
                    || applicationTenantBusinessEntityCrosswalkList.isEmpty()
                    || applicationTenantBusinessEntityCrosswalkList.size() != 1) {
                throw new SandataRuntimeException(format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", bsnEntId));
            }

            //GET Tenant_BE_XWALK record by bsn_id , there's always one bsn_ent match with one tenant , i.e 1-1 relationship
            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = applicationTenantBusinessEntityCrosswalkList.get(0);

            applicationTenantKeyConfiguration.setApplicationTenantSK(applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK());

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.insertAppTenantKeyConfForBEID(connection, connectionType, applicationTenantKeyConfiguration));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void updateAppTenantKeyConfForBEID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration = exchange.getIn().getBody(ApplicationTenantKeyConfiguration.class);

            if (applicationTenantKeyConfiguration == null) {
                throw new SandataRuntimeException(format("%s: ERROR: ApplicationTenantKeyConfiguration == null",
                        getClass().getName()));
            }

            if (IsNullOrEmpty(applicationTenantKeyConfiguration.getKeyName())) {
                throw new SandataRuntimeException(format("%s: ERROR: applicationTenantKeyConfiguration.getKeyName() == null",
                        getClass().getName()));
            }

            if (applicationTenantKeyConfiguration.getKeyName().startsWith("MW_")) {
                throw new SandataRuntimeException(format("%s: ERROR: applicationTenantKeyConfiguration.getKeyName(): [%s]: Can not start with reserved prefix \"MW_\"!",
                        getClass().getName(), applicationTenantKeyConfiguration.getKeyName()));
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            List<ApplicationTenantBusinessEntityCrosswalk> applicationTenantBusinessEntityCrosswalkList = (List<ApplicationTenantBusinessEntityCrosswalk>) oracleDataService.executeGet(
                    connectionType,
                    "PKG_APP",
                    "getAppTenantBeXwalk",
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk",
                    bsnEntId
            );

            // Returned List must not be null, empty, or contain more than one.
            if (applicationTenantBusinessEntityCrosswalkList == null
                    || applicationTenantBusinessEntityCrosswalkList.isEmpty()
                    || applicationTenantBusinessEntityCrosswalkList.size() != 1) {
                throw new SandataRuntimeException(format("No unique APP_TENANT_BE_XWALK found for bsn_ent_id %s!", bsnEntId));
            }

            //GET Tenant_BE_XWALK record by bsn_id , there's always one bsn_ent match with one tenant , i.e 1-1 relationship
            ApplicationTenantBusinessEntityCrosswalk applicationTenantBusinessEntityCrosswalk = applicationTenantBusinessEntityCrosswalkList.get(0);

            applicationTenantKeyConfiguration.setApplicationTenantSK(applicationTenantBusinessEntityCrosswalk.getApplicationTenantSK());

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.updateAppTenantKeyConfForBEID(connection, connectionType, applicationTenantKeyConfiguration));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void deleteAppTenantKeyConfForBEID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            Long appTenantKeyConfSK = (Long) exchange.getIn().getHeader("app_tenant_key_conf_sk");
            if (appTenantKeyConfSK == null || appTenantKeyConfSK <= 0) {
                throw new SandataRuntimeException("AppTenantKeyConfSK (app_tenant_key_conf_sk) is required!");
            }

            ConnectionType connectionType = (ConnectionType) exchange.getIn().getHeader("connectionType");
            if (connectionType == null) {
                throw new SandataRuntimeException("Failed to define Oracle connection type.");
            }

            connection = oracleDataService.getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.deleteAppTenantKeyConfForBEID(connection, connectionType, appTenantKeyConfSK));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getBeObjectsWithSortOption(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                    || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String operationName = (String) exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String simpleClassName = methodParts[3];

            String tableName = getTableNameByClassName(simpleClassName);
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getBeObjectsWithSortOption(tableName,
                    bsnEntId,
                    sortOn,
                    direction,
                    simpleClassName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private String getTableNameByClassName(String simpleClassName) {
        if (simpleClassName == null) {
            throw new SandataRuntimeException("Cannot get table name for Simple Class Name NULL!");
        }

        switch (simpleClassName) {
            case "BusinessEntityReligionList":
                return "BE_RELIGION_LST";
            case "BusinessEntitySafetyMeasureLookup":
                return "BE_SFTY_MEASURE_LKUP";
            case "BusinessEntityLanguageList":
                return "BE_LANG_LST";
            case "BusinessEntityRaceEthnicityList":
                return "BE_RACE_ETHNICITY_LST";
            case "BusinessEntityRateTypeLookup":
                return "BE_RATE_TYP_LKUP";
            case "BusinessEntityMedicationDosageFrequencyLookup":
                return "BE_MED_DOSAGE_FREQ_LKUP";
            case "BusinessEntitySkillLookup":
                return "BE_SKILL_LKUP";
            case "BusinessEntityEmploymentClassLookup":
                return "BE_EMPLT_CLS_LKUP";
            case "BusinessEntityEvacuationLevelLookup":
                return "BE_EVAC_LVL_LKUP";
            case "BusinessEntityEmploymentStatusTypeLookup":
                return "BE_EMPLT_STATUS_TYP_LKUP";
            case "BusinessEntityStaffWorkingPreferenceLookup":
                return "BE_STAFF_WRK_PREF_LKUP";
            case "BusinessEntityReferralTypeLookup":
                return "BE_RFRL_TYP_LKUP";
            case "BusinessEntityPatientPriorityLevelLookup":
                return "BE_PT_PRIO_LVL_LKUP";
            case "BusinessEntityNutritionalRequirementLookup":
                return "BE_NUTR_RQMT_LKUP";
            case "BusinessEntityBillingCodeLookup":
                return "BE_BILLING_CODE_LKUP";
            case "BusinessEntityLineOfBusinessLookup":
                return "BE_LOB_LKUP";
            case "BusinessEntityStaffTrainingCategoryLookup":
                return "BE_STAFF_TRNG_CTGY_LKUP";
            case "BusinessEntityComplianceCategoryLookup":
                return "BE_COMP_CTGY_LKUP";
            case "BusinessEntityPatientRequirementLookup":
                return "BE_PT_RQMT_LKUP";
            default:
                throw new SandataRuntimeException("Not supported table name for Simple Class Name = " + simpleClassName);
        }
    }

    public void getBeStaffNoteTypLkupByBEID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            exchange.getIn().setBody(oracleDataService.getBeStaffNoteTypLkupByBEID(bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBePtNoteTypLkupByBEID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            exchange.getIn().setBody(oracleDataService.getBePtNoteTypLkupByBEID(bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get BusinessEntityChangeReasonLookup by bsn_ent_id and module and status
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getBeChangeReasonLkupByModuleAndStatus(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String module = (String) exchange.getIn().getHeader("module");
            String status = (String) exchange.getIn().getHeader("status");
            if (IsNullOrEmpty(module) && IsNullOrEmpty(status)) {
                logger.info("Both Module (module) and Status (status) are empty. Return all active reason codes");
            } else if (IsNullOrEmpty(module) || IsNullOrEmpty(status)) {
                throw new SandataRuntimeException("Either Module (module) or Status (status) is empty! Both should have values or none of them should have values");
            }

            List<BusinessEntityChangeReasonLookup> result = oracleDataService.getBeChangeReasonLkupByModuleAndStatus(bsnEntId, module, status);
            exchange.getIn().setHeader("TOTAL_ROWS", result.size());
            exchange.getIn().setBody(result);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Modules from COREDATA.APP_MOD_LKUP table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getApplicationModuleLookup(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            List<ApplicationModuleLookup> result = oracleDataService.getApplicationModuleLookup();
            exchange.getIn().setHeader("TOTAL_ROWS", result.size());
            exchange.getIn().setBody(result);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Statuses by Module from COREDATA.BE_CHANGE_REASON_LKUP table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getBeChangeReasonLkupStatusByModule(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String module = (String) exchange.getIn().getHeader("module");
            if (IsNullOrEmpty(module)) {
                throw new SandataRuntimeException("Module (module) is required!");
            }

            List<BusinessEntityChangeReasonLookup> result = oracleDataService.getBeChangeReasonLkupStatusByModule(bsnEntId, module);
            exchange.getIn().setHeader("TOTAL_ROWS", result.size());
            exchange.getIn().setBody(result);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * check if a Compliance is used as a requisite of other Compliances or assigned to Staff
     *
     * @param exchange
     */
    public void checkComplianceIsUsedAsRequisiteForOthers(final Exchange exchange) {
        try {
            String compCode = (String) exchange.getIn().getHeader("comp_code");
            if (StringUtil.IsNullOrEmpty(compCode)) {
                throw new SandataRuntimeException("ComplianceCode (comp_code) is required!");
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.isComplianceUsedAsRequisiteForOthers(compCode, bsnEntId));
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    /**
     * check if a Compliance's name is unique
     *
     * @param exchange
     */
    public void checkComplianceUniqueName(final Exchange exchange) {
        Connection connection = null;
        try {
            String compName = (String) exchange.getIn().getHeader("comp_name");
            if (StringUtil.IsNullOrEmpty(compName)) {
                throw new SandataRuntimeException("Compliance Name (comp_name) is required!");
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            exchange.getIn().setBody(oracleDataService.isComplianceNameUnique(connection, compName, bsnEntId, null));
            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    public void findBeHcpLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String keyword = (String) exchange.getIn().getHeader("keyword");
            if (IsNullOrEmpty(keyword)) {
                throw new SandataRuntimeException("Keyword (keyword) is required!");
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.findBeHcpLkup(keyword, bsnEntId, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void insertBeExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        logger.trace("insertBeExcpLst: START");

        try {
            BusinessEntityExceptionListExt beExcpLstExt = exchange.getIn().getBody(BusinessEntityExceptionListExt.class);

            if (beExcpLstExt == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: BusinessEntityExceptionListExt is NULL");
            }

            if (oracleDataService.isBeExcpExisting(beExcpLstExt.getBusinessEntityID(), beExcpLstExt.getExceptionID())) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record with BusinessEntityID = %s and ExceptionID = %s already existed. Insert is not allowed.",
                                beExcpLstExt.getBusinessEntityID(), beExcpLstExt.getExceptionID()));
            }

            BusinessEntityExceptionList beExcpLst = beExcpLstExt.getBusinessEntityExceptionList();

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(beExcpLst);
            Object jpubType = new DataMapper().map(beExcpLst);

            long beExcpLstSk = oracleDataService.execute(ConnectionType.COREDATA, oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(), jpubType);

            oracleDataService.insertOrUpdateBeExcpLstSetting(beExcpLstExt.getExceptionID().toString(), beExcpLstExt.getAdditionalSettings(), beExcpLstExt.getBusinessEntityID());

            exchange.getIn().setBody(beExcpLstSk);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.trace("insertBeExcpLst: END");
            logger.stop();
        }
    }

    public void updateBeExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        logger.trace("updateBeExcpLst: START");

        Connection connection = null;

        try {
            BusinessEntityExceptionListExt beExcpLstExt = exchange.getIn().getBody(BusinessEntityExceptionListExt.class);

            if (beExcpLstExt == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: BusinessEntityExceptionListExt is NULL");
            }

            BusinessEntityExceptionList beExcpLst = beExcpLstExt.getBusinessEntityExceptionList();

            String bsnEntId = beExcpLst.getBusinessEntityID();
            BigInteger excpId = beExcpLst.getExceptionID();
            BigInteger currentBeExcpLstSk = beExcpLst.getBusinessEntityExceptionListSK();

            if (currentBeExcpLstSk == null) {
                throw new SandataRuntimeException(exchange, "BusinessEntityExceptionListSK (sequence_key) is required!");
            }

            if (oracleDataService.isBeExcpExistingForUpdate(bsnEntId, excpId, currentBeExcpLstSk)) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record with BusinessEntityID = %s and ExceptionID = %s already existed. Update is not allowed.",
                                bsnEntId, excpId));
            }

            connection = oracleDataService.getOracleConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(false);

            beExcpLst.setRecordCreateTimestamp(new Date());
            beExcpLst.setRecordUpdateTimestamp(new Date());
            beExcpLst.setRecordEffectiveTimestamp(new Date());

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(beExcpLst);
            Object jpubType = new DataMapper().map(beExcpLst);

            // logically delete the current one first
            oracleDataService.execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(),
                    oracleMetadata.deleteMethod(), currentBeExcpLstSk.longValue());

            // then insert a new record
            long newBeExcpLstSk = oracleDataService.execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(), jpubType);

            connection.commit();

            if (newBeExcpLstSk > 0) {
                oracleDataService.insertOrUpdateBeExcpLstSetting(beExcpLstExt.getExceptionID().toString(), beExcpLstExt.getAdditionalSettings(), beExcpLstExt.getBusinessEntityID());

                exchange.getIn().setBody(newBeExcpLstSk);

            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            // close DB connection
            oracleDataService.closeOracleConnection(connection);

            logger.trace("updateBeExcpLst: END");
            logger.stop();
        }
    }

    public void getBeExcpLstSk(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        logger.trace("getBeExcpLstSk: START");

        try {

            Long sequenceKey = exchange.getIn().getHeader("sequence_key", Long.class);

            if (sequenceKey == null || sequenceKey < 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityExceptionListSK (sequence_key) is required!");
            }

            ArrayList<BusinessEntityExceptionList> result =
                    (ArrayList<BusinessEntityExceptionList>) oracleDataService.executeGet(ConnectionType.COREDATA, "PKG_AM", "getBeExcpLst",
                            "com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList", sequenceKey.longValue());

            // update AdditionalSettings of BusinessEntityExceptionLis from APP_TENANT_KEY_CONF
            if (result != null && result.size() > 0) {
                BusinessEntityExceptionListExt beExcpLstExt = new BusinessEntityExceptionListExt(result.get(0));
                beExcpLstExt.setAdditionalSettings(oracleDataService.getBeExcpLstSetting(beExcpLstExt.getExceptionID().toString(), beExcpLstExt.getBusinessEntityID()));
                exchange.getIn().setBody(beExcpLstExt);
            } else {
                exchange.getIn().setBody(null);
            }

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.trace("getBeExcpLstSk: END");
            logger.stop();
        }
    }

    public void getBeExcpLstPk(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        logger.trace("getBeExcpLstPk: START");

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            Long exceptionId = exchange.getIn().getHeader("exception_id", Long.class);

            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Object[] params = new Object[]{bsnEntId};
            StringBuilder whereClause = new StringBuilder().append("WHERE BE_ID = ? ");

            if (exceptionId != null) {
                whereClause.append(" AND EXCP_ID = ?");
                params = new Object[]{bsnEntId, exceptionId};
            }

            String sql = "SELECT * FROM BE_EXCP_LST " + whereClause.toString()
                    + "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            ArrayList<BusinessEntityExceptionList> result =
                    (ArrayList<BusinessEntityExceptionList>) oracleDataService.getEntitiesForId(ConnectionType.COREDATA, sql,
                            "com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList", params);

            // update AdditionalSettings of BusinessEntityExceptionList from APP_TENANT_KEY_CONF
            ArrayList<BusinessEntityExceptionListExt> beExcpLstExts = new ArrayList<BusinessEntityExceptionListExt>();
            if (result != null && result.size() > 0) {
                for (BusinessEntityExceptionList beExcpLst : result) {
                    BusinessEntityExceptionListExt beExcpLstExt = new BusinessEntityExceptionListExt(beExcpLst);
                    beExcpLstExt.setAdditionalSettings(oracleDataService.getBeExcpLstSetting(beExcpLstExt.getExceptionID().toString(), beExcpLstExt.getBusinessEntityID()));
                    beExcpLstExts.add(beExcpLstExt);
                }
            }

            exchange.getIn().setBody(beExcpLstExts);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.trace("getBeExcpLstPk: END");
            logger.stop();
        }
    }

    public void deleteBeExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        logger.trace("deleteBeExcpLst: START");

        try {
            // Validating params
            Long sequenceKey = exchange.getIn().getHeader("sequence_key", Long.class);
            if (sequenceKey == null || sequenceKey <= 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityExceptionListSK (sequence_key) is required!");
            }

            // Find old BusinessEntityExceptionList by SK
            ArrayList<BusinessEntityExceptionList> result =
                    (ArrayList<BusinessEntityExceptionList>) oracleDataService.executeGet(ConnectionType.COREDATA, "PKG_AM", "getBeExcpLst",
                            "com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList", sequenceKey.longValue());

            if (result == null || result.size() == 0) {
                throw new SandataRuntimeException(String.format("BusinessEntityExceptionList with SK=%d not found", sequenceKey));
            }

            // delete AdditionalSettings of BusinessEntityExceptionList out of APP_TENANT_KEY_CONF
            BusinessEntityExceptionList beExcpLst = result.get(0);
            oracleDataService.deleteBeExcpLstSetting(beExcpLst.getExceptionID().toString(), beExcpLst.getBusinessEntityID());

            // delete BusinessEntityExceptionList
            exchange.getIn().setBody(oracleDataService.execute(ConnectionType.COREDATA, "PKG_AM", "deleteBeExcpLst",
                    sequenceKey.longValue()));

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.trace("deleteBeExcpLst: END");
            logger.stop();
        }
    }

    /**
     * Insert ContractExcpLst
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertContrExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("insertContrExcpLst: START");

        Connection coreDataConn = null;
        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            ContractExceptionListExt contractExceptionListExt = exchange.getIn().getBody(ContractExceptionListExt.class);

            if (contractExceptionListExt == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: ContractExceptionListExt === NULL");
            }

            if (oracleDataService.isContrExcpExisting(contractExceptionListExt.getPayerID(),
                    contractExceptionListExt.getContractID(), contractExceptionListExt.getExceptionID())) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record with PayerID = %s, ContractID = %s and ExceptionID = %s already existed. Insert is not allowed.",
                                contractExceptionListExt.getPayerID(), contractExceptionListExt.getContractID(),
                                contractExceptionListExt.getExceptionID()));
            }

            coreDataConn = oracleDataService.getOracleConnection(ConnectionType.COREDATA);
            coreDataConn.setAutoCommit(false);

            ContractExceptionList contractExceptionList = contractExceptionListExt.getContractExceptionList();

            long returnVal = executeRecursive(coreDataConn, ConnectionType.COREDATA, contractExceptionList, true, -999, exchange);
            logger.info(String.format("Inserted into CONTR_EXCP_LST with SK=%d", returnVal));
            coreDataConn.commit();

            if (returnVal > 0) {
                metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
                metaDataConn.setAutoCommit(false);

                String callMethod = "{?=call PKG_APP_UTIL.UPDATE_CONTR_EXCP_LST_SETTINGS(?,?,?,?,?)}";

                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

                int index = 2;
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, contractExceptionListExt.getPayerID());
                callableStatement.setString(index++, contractExceptionListExt.getContractID());
                callableStatement.setString(index++, String.valueOf(contractExceptionListExt.getExceptionID()));
                callableStatement.setString(index++, contractExceptionListExt.getAdditionalSettings());

                callableStatement.execute();
                long additionalSettingsReturnVal = callableStatement.getLong(1);
                logger.info(String.format("Inserted into APP_TENANT_KEY_CONF with SK=%d", additionalSettingsReturnVal));
                metaDataConn.commit();
            }

            exchange.getIn().setBody(returnVal);


        } catch (Exception e) {
            if (coreDataConn != null) {

                try {
                    coreDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: insertContrExcpLst: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(coreDataConn);
            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("insertContrExcpLst: END");
            logger.stop();
        }
    }

    /**
     * Update ContractExcpLst
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updateContrExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("updateContrExcpLst: START");

        Connection coreDataConn = null;
        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            ContractExceptionListExt contractExceptionListExt = exchange.getIn().getBody(ContractExceptionListExt.class);

            if (contractExceptionListExt == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: ContractExceptionListExt === NULL");
            }

            ContractExceptionList contractExceptionList = contractExceptionListExt.getContractExceptionList();

            String payerId = contractExceptionList.getPayerID();
            String contractId = contractExceptionList.getContractID();
            BigInteger excpId = contractExceptionList.getExceptionID();
            BigInteger currentContrExcpLstSk = contractExceptionList.getContractExceptionListSK();

            if (currentContrExcpLstSk == null) {
                throw new SandataRuntimeException(exchange, "ContractExceptionListSK (sequence_key) is required!");
            }

            if (oracleDataService.isContrExcpExistingForUpdate(payerId, contractId, excpId, currentContrExcpLstSk)) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record with PayerID = %s, ContractID = %s and ExceptionID = %s already existed. Update is not allowed.",
                                payerId, contractId, excpId));
            }

            contractExceptionList.setRecordCreateTimestamp(new Date());
            contractExceptionList.setRecordUpdateTimestamp(new Date());
            contractExceptionList.setRecordEffectiveTimestamp(new Date());

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(contractExceptionList);
            Object jpubType = new DataMapper().map(contractExceptionList);

            coreDataConn = oracleDataService.getOracleConnection(ConnectionType.COREDATA);
            coreDataConn.setAutoCommit(false);

            // logically delete the current one first
            oracleDataService.execute(coreDataConn, ConnectionType.COREDATA, oracleMetadata.packageName(),
                    oracleMetadata.deleteMethod(), currentContrExcpLstSk.longValue());

            // then insert a new record
            long returnVal = oracleDataService.execute(coreDataConn, ConnectionType.COREDATA, oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(), jpubType);

            logger.info(String.format("Updated CONTR_EXCP_LST with returnVal=%d", returnVal));

            coreDataConn.commit();

            if (returnVal > 0) {
                metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
                metaDataConn.setAutoCommit(false);

                String callMethod = "{?=call PKG_APP_UTIL.UPDATE_CONTR_EXCP_LST_SETTINGS(?,?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

                int index = 2;
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, payerId);
                callableStatement.setString(index++, contractId);
                callableStatement.setString(index++, String.valueOf(excpId));
                callableStatement.setString(index++, contractExceptionListExt.getAdditionalSettings());

                callableStatement.execute();
                long additionalSettingsReturnVal = callableStatement.getLong(1);
                logger.info(String.format("Updated APP_TENANT_KEY_CONF with returnVal=%d", additionalSettingsReturnVal));
                metaDataConn.commit();

                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }

        } catch (Exception e) {
            if (coreDataConn != null) {

                try {
                    coreDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: updateContrExcpLst: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(coreDataConn);
            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("updateContrExcpLst: END");
            logger.stop();
        }
    }

    /**
     * Delete ContractExcpLst
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void deleteContrExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("deleteContrExcpLst: START");

        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Long sequenceKey = exchange.getIn().getHeader("sequence_key", Long.class);
            if (sequenceKey == null || sequenceKey <= 0) {
                throw new SandataRuntimeException(exchange, "ContractExceptionListSK (sequence_key) is required!");
            }

            // Find old ContractExceptionList by SK
            ArrayList<ContractExceptionList> result = (ArrayList) oracleDataService.executeGet(
                    ConnectionType.COREDATA,
                    "PKG_AM",
                    "getContrExcpLst",
                    "com.sandata.lab.data.model.dl.model.ContractExceptionList",
                    sequenceKey);

            if (result == null || result.size() == 0) {
                throw new SandataRuntimeException(String.format("ContractExceptionList with SK=%d not found", sequenceKey));
            }

            ContractExceptionList oldContractExceptionList = result.get(0);

            // Execute delete
            long resultVal = oracleDataService.execute(
                    ConnectionType.COREDATA,
                    "PKG_AM",
                    "deleteContrExcpLst",
                    sequenceKey
            );

            if (resultVal > 0) {
                metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
                metaDataConn.setAutoCommit(false);

                String callMethod = "{?=call PKG_APP_UTIL.DELETE_CONTR_EXCP_LST_SETTINGS(?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

                int index = 2;
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, oldContractExceptionList.getPayerID());
                callableStatement.setString(index++, oldContractExceptionList.getContractID());
                callableStatement.setString(index++, String.valueOf(oldContractExceptionList.getExceptionID()));

                callableStatement.execute();
                resultVal = callableStatement.getLong(1);
                logger.info(String.format("Deleted APP_TENANT_KEY_CONF with returnVal=%d", resultVal));
                metaDataConn.commit();
            }

            exchange.getIn().setBody(resultVal);

        } catch (Exception e) {

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: deleteContrExcpLst: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("deleteContrExcpLst: END");
            logger.stop();
        }
    }

    /**
     * Get ContractExceptionList
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getContrExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("getContrExcpLst: START");

        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            // Validate param
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }


            ArrayList<ContractExceptionList> result = null;
            if (exchange.getIn().getHeader("sequence_key") != null) {
                Long sequenceKey = exchange.getIn().getHeader("sequence_key", Long.class);
                if (sequenceKey == null || sequenceKey <= 0) {
                    throw new SandataRuntimeException(exchange, "ContractExceptionListSK (sequence_key) is required!");
                }

                result = (ArrayList) oracleDataService.executeGet(
                        ConnectionType.COREDATA,
                        "PKG_AM",
                        "getContrExcpLst",
                        "com.sandata.lab.data.model.dl.model.ContractExceptionList",
                        sequenceKey
                );

            } else {
                String payerId = exchange.getIn().getHeader("payer_id", String.class);
                if (StringUtil.IsNullOrEmpty(payerId)) {
                    throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
                }

                String contractId = exchange.getIn().getHeader("contract_id", String.class);
                if (StringUtil.IsNullOrEmpty(contractId)) {
                    throw new SandataRuntimeException(exchange, "ContractID (contract_id) is required!");
                }

                result = (ArrayList) oracleDataService.executeGet(
                        ConnectionType.COREDATA,
                        "PKG_AM",
                        "getContrExcpLst",
                        "com.sandata.lab.data.model.dl.model.ContractExceptionList",
                        new String[]{payerId, contractId}
                );

            }

            if (result != null && result.size() > 0) {
                metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
                String callMethod = "{?=call PKG_APP_UTIL.GET_CONTR_EXCP_LST_SETTINGS(?,?,?,?)}";

                ArrayList<ContractExceptionListExt> extResult = new ArrayList<>();
                for (ContractExceptionList contractExceptionList : result) {
                    ContractExceptionListExt ext = new ContractExceptionListExt(contractExceptionList);

                    callableStatement = metaDataConn.prepareCall(callMethod);
                    callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);

                    int index = 2;
                    callableStatement.setString(index++, bsnEntId);
                    callableStatement.setString(index++, ext.getPayerID());
                    callableStatement.setString(index++, ext.getContractID());
                    callableStatement.setString(index++, String.valueOf(ext.getExceptionID()));

                    callableStatement.execute();

                    ext.setAdditionalSettings(callableStatement.getString(1));
                    callableStatement.close();

                    extResult.add(ext);

                    if (exchange.getIn().getHeader("sequence_key") != null) {
                        exchange.getIn().setBody(extResult.get(0));
                        return;
                    }
                }

                exchange.getIn().setBody(extResult);
            } else {
                exchange.getIn().setBody(null);
            }

        } catch (Exception e) {

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: getContrExcpLst: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    if (!callableStatement.isClosed()) {
                        callableStatement.close();
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("getContrExcpLst: END");
            logger.stop();
        }
    }

    private void throwExcpIfInsertDuplicate(final Exchange exchange, String bsnEntId, String payerId, String contractId,
                                            String qualifier, String ruleId) {
        if (!StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
            // insert additional settings for rounding rule at Contract level
            if (oracleDataService.isContrVvRndingRuleSettingExisting(bsnEntId, payerId, contractId, qualifier,
                    ruleId)) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record of CONTRACT Rounding Rule Settings with BusinessEntityID = %s, PayerID = %s, ContractID = %s, Qualifier = %s and RuleID = %s already existed. Insert is not allowed.",
                                bsnEntId, payerId, contractId, qualifier, ruleId));
            }
        } else {
            // insert additional settings for rounding rule at Agency level
            if (oracleDataService.isBeVvRndingRuleSettingExisting(bsnEntId, qualifier, ruleId)) {
                throw new SandataRuntimeException(exchange,
                        String.format(
                                "Record of BE Rounding Rule Settings with BusinessEntityID = %s, Qualifier = %s and RuleID = %s already existed. Insert is not allowed.",
                                bsnEntId, qualifier, ruleId));
            }
        }
    }

    /**
     * Insert or update additional settings for VisitVerificationRoundingRule
     */
    public void insertOrUpdateVvRndingRuleSettings(final Exchange exchange, boolean isInsert) {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("insertOrUpdateVvRndingRuleSettings: START");

        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            String contractId = exchange.getIn().getHeader("contract_id", String.class);

            VisitVerificationRoundingRuleSetting visitVerificationRoundingRuleSetting = exchange.getIn().getBody(VisitVerificationRoundingRuleSetting.class);

            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");

            } else {
                if (StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
                    throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");

                } else if (!StringUtil.IsNullOrEmpty(payerId) && StringUtil.IsNullOrEmpty(contractId)) {
                    throw new SandataRuntimeException(exchange, "ContractID (contract_id) is required!");
                }
            }

            if (visitVerificationRoundingRuleSetting == null) {
                throw new SandataRuntimeException(exchange, "Validation Error: VisitVerificationRoundingRuleSetting === NULL");
            }

            String qualifier = visitVerificationRoundingRuleSetting.getVisitVerificationRoundingRuleQualifier().value();
            String ruleId = visitVerificationRoundingRuleSetting.getVisitVerificationRoundingRuleID().value();

            if (isInsert) {
                throwExcpIfInsertDuplicate(exchange, bsnEntId, payerId, contractId, qualifier, ruleId);
            }

            metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
            metaDataConn.setAutoCommit(false);

            String callMethod = null;
            int index = 2;
            if (!StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
                // insert or update additional settings for rounding rule at Contract level
                callMethod = "{?=call PKG_APP_UTIL.INS_UPD_CONTR_RNDG_RULE_STNG(?,?,?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, payerId);
                callableStatement.setString(index++, contractId);
                callableStatement.setString(index++, qualifier);
                callableStatement.setString(index++, ruleId);
                callableStatement.setString(index++, visitVerificationRoundingRuleSetting.getAdditionalSettings());
            } else {
                // insert or update additional settings for rounding rule at Agency level
                callMethod = "{?=call PKG_APP_UTIL.INS_UPD_BE_RNDING_RULE_SETTING(?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, qualifier);
                callableStatement.setString(index++, ruleId);
                callableStatement.setString(index++, visitVerificationRoundingRuleSetting.getAdditionalSettings());
            }

            callableStatement.execute();
            long additionalSettingsReturnVal = callableStatement.getLong(1);
            metaDataConn.commit();

            logger.info(String.format("Inserted or updated into APP_TENANT_KEY_CONF with SK=%d", additionalSettingsReturnVal));

            exchange.getIn().setBody(additionalSettingsReturnVal);

        } catch (Exception e) {
            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: insertOrUpdateVvRndingRuleSettings: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("insertOrUpdateVvRndingRuleSettings: END");
            logger.stop();
        }
    }

    /**
     * Delete additional settings of VisitVerificationRoundingRule
     */
    public void deleteVvRndingRuleSettings(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("deleteVvRndingRuleSettings: START");

        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            String contractId = exchange.getIn().getHeader("contract_id", String.class);
            VisitVerificationRoundingRuleID ruleId = exchange.getIn().getHeader("rule_id", VisitVerificationRoundingRuleID.class);
            VisitVerificationRoundingRuleQualifier qualifier = exchange.getIn().getHeader("qualifier", VisitVerificationRoundingRuleQualifier.class);

            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");

            } else {
                if (StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
                    throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");

                } else if (!StringUtil.IsNullOrEmpty(payerId) && StringUtil.IsNullOrEmpty(contractId)) {
                    throw new SandataRuntimeException(exchange, "ContractID (contract_id) is required!");
                }
            }

            if (ruleId == null) {
                throw new SandataRuntimeException(exchange, "VisitVerificationRoundingRuleID (rule_id) is required!");
            }

            if (qualifier == null) {
                throw new SandataRuntimeException(exchange, "VisitVerificationRoundingRuleQualifier (qualifier) is required!");
            }

            metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);
            metaDataConn.setAutoCommit(false);

            String callMethod = null;
            int index = 2;
            if (!StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
                // delete additional settings for rounding rule at Contract level
                callMethod = "{?=call PKG_APP_UTIL.DELETE_CONTR_RNDG_RULE_STNG(?,?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, payerId);
                callableStatement.setString(index++, contractId);
                callableStatement.setString(index++, qualifier.value());
                callableStatement.setString(index++, ruleId.value());

            } else {
                // delete additional settings for rounding rule at Agency level
                callMethod = "{?=call PKG_APP_UTIL.DELETE_BE_RNDING_RULE_SETTING(?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, qualifier.value());
                callableStatement.setString(index++, ruleId.value());
            }

            callableStatement.execute();
            long additionalSettingsReturnVal = callableStatement.getLong(1);
            metaDataConn.commit();

            logger.info(String.format("Deleted from APP_TENANT_KEY_CONF with returnVal=%d", additionalSettingsReturnVal));

            exchange.getIn().setBody(additionalSettingsReturnVal);

        } catch (Exception e) {

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: deleteVvRndingRuleSettings: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("deleteVvRndingRuleSettings: END");
            logger.stop();
        }
    }

    /**
     * Get additional settings of VisitVerificationRoundingRule
     *
     * @param exchange
     */
    public void getVvRndingRuleSettings(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange, AppContext.getContext());

        logger.start();

        logger.trace("getVvRndingRuleSettings: START");

        Connection metaDataConn = null;
        CallableStatement callableStatement = null;

        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            String payerId = exchange.getIn().getHeader("payer_id", String.class);
            String contractId = exchange.getIn().getHeader("contract_id", String.class);
            VisitVerificationRoundingRuleID ruleId = exchange.getIn().getHeader("rule_id", VisitVerificationRoundingRuleID.class);
            VisitVerificationRoundingRuleQualifier qualifier = exchange.getIn().getHeader("qualifier", VisitVerificationRoundingRuleQualifier.class);

            VisitVerificationRoundingRuleSetting visitVerificationRoundingRuleSetting = new VisitVerificationRoundingRuleSetting();
            visitVerificationRoundingRuleSetting.setVisitVerificationRoundingRuleQualifier(qualifier);
            visitVerificationRoundingRuleSetting.setVisitVerificationRoundingRuleID(ruleId);

            metaDataConn = oracleDataService.getOracleConnection(ConnectionType.METADATA);

            String callMethod = null;
            int index = 2;
            if (!StringUtil.IsNullOrEmpty(payerId) && !StringUtil.IsNullOrEmpty(contractId)) {
                // get additional settings for rounding rule at Contract level
                callMethod = "{?=call PKG_APP_UTIL.GET_CONTR_RNDG_RULE_STNG(?,?,?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, payerId);
                callableStatement.setString(index++, contractId);
                callableStatement.setString(index++, qualifier.value());
                callableStatement.setString(index++, ruleId.value());

            } else {
                // get additional settings for rounding rule at Agency level
                callMethod = "{?=call PKG_APP_UTIL.GET_BE_RNDING_RULE_SETTING(?,?,?)}";
                callableStatement = metaDataConn.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
                callableStatement.setString(index++, bsnEntId);
                callableStatement.setString(index++, qualifier.value());
                callableStatement.setString(index++, ruleId.value());
            }

            callableStatement.execute();
            visitVerificationRoundingRuleSetting.setAdditionalSettings(callableStatement.getString(1));

            exchange.getIn().setBody(visitVerificationRoundingRuleSetting);

        } catch (Exception e) {

            if (metaDataConn != null) {

                try {
                    metaDataConn.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: getVvRndingRuleSettings: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            this.oracleDataService.closeOracleConnection(metaDataConn);

            logger.trace("getVvRndingRuleSettings: END");
            logger.stop();
        }
    }

    public void getStaffTrngCtgryNameCanAdd(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                    || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }
            String staffTrngCtgryName = (String) exchange.getIn().getHeader("name");
            if (staffTrngCtgryName == null
                    || staffTrngCtgryName.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            Boolean canAdd = oracleDataService.getBeStaffTrngCtgryNameCanAdd(bsnEntId,
                    staffTrngCtgryName);

            exchange.getIn().setBody(canAdd);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
