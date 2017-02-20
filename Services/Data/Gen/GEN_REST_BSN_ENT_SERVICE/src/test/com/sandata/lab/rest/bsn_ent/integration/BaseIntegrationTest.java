package com.sandata.lab.rest.bsn_ent.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.rest.bsn_ent.BaseTestSupport;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class BaseIntegrationTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;

    @Before
    public void beforeTest() throws UniversalConnectionPoolException, SQLException {
        this.sandataOracleConnection =
            new SandataOracleConnection()
                //.DatabaseName("sdbdev1")
                .DatabaseName("sdbdev2")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
                .User("coredata")
                //.Password("z3U0kCdbdN")
                .Password("Z4fEIRn7D2")
                .Open();
        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

//    protected BaseIntegrationTest() throws SQLException {
//
//         this.sandataOracleConnection =
//                new SandataOracleConnection()
//                        //.DatabaseName("sdbdev1")
//                        .DatabaseName("sdbdev2")
//                        .ServerName("stxdevdb.sandata.com")
//                        .PortNumber(1526)
//                        .User("coredata")
//                        //.Password("z3U0kCdbdN")
//                        .Password("Z4fEIRn7D2")
//                        .Open();
//    }

    protected Connection openConnection() throws SQLException {

        Assert.assertNotNull(sandataOracleConnection);

        Connection connection = sandataOracleConnection.getConnection();

        Assert.assertNotNull(connection);

        connection.setAutoCommit(true);

        return connection;
    }

    protected Object call(final String method, int outputParameterType, Object[] params) throws SQLException {

        Connection connection = null;
        try {

            connection = openConnection();

            CallableStatement callableStatement = connection.prepareCall(method);

            callableStatement.registerOutParameter(1, outputParameterType);

            int index = 2;
            for (Object param : params) {
                callableStatement.setObject(index++, param);
            }

            callableStatement.execute();
            return callableStatement.getObject(1);

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
    }

    protected Object call(final Connection connection, final String method, int outputParameterType, Object[] params) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        int index = 2;
        for (Object param : params) {
            callableStatement.setObject(index++, param);
        }

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    protected Object call(final Connection connection, final String method, int outputParameterType) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    public SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    @Test
    public void printTestEntities() throws ParseException {
        Date startDate = new Date();
        Date endDate = new Date();
        String terminatedDateString = "9999-12-31 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date terminatedDate = dateFormat.parse(terminatedDateString);
        endDate.setTime(startDate.getTime() + 3600000L);

        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setRecordCreatedBy("JASON");
        businessEntity.setBusinessEntityName("Queens Office");
        businessEntity.setBusinessEntityPrimaryAddressLine1("106 Continental Ave.");
        businessEntity.setBusinessEntityPrimaryCity("Forest Hills");
        businessEntity.setBusinessEntityPrimaryState(StateCode.NY);
        businessEntity.setBusinessEntityPrimaryZip4("11375");
        businessEntity.setBusinessEntityPrimaryPhone1("2125554321");
        businessEntity.setBusinessEntityFax("2125551234");
        businessEntity.setBusinessEntityNationalProviderIdentifier("8675309");
        businessEntity.setBusinessEntityAtypicalProviderIdentifier("8675309");
        businessEntity.setBusinessEntityFederalTaxIdentifier("8675309");
        businessEntity.setBusinessEntityTaxonomyCode("8675309");
        businessEntity.setBusinessEntityPrimaryContactName("Nasir Jones");
        businessEntity.setBusinessEntityPrimaryContactTitle("Nas");
        businessEntity.setBusinessEntityPrimaryEmail("jscott@sandata.com");
        businessEntity.setRecordTerminationTimestamp(terminatedDate);

        List<BusinessEntityContactDetail> businessEntityContactDetailList = new ArrayList<>();
        BusinessEntityContactDetail businessEntityContactDetail = new BusinessEntityContactDetail();
        businessEntityContactDetail.setBusinessEntityContactDetailID("1");
        businessEntityContactDetail.setRecordCreateTimestamp(startDate);
        businessEntityContactDetail.setRecordUpdateTimestamp(startDate);
        businessEntityContactDetail.setRecordEffectiveTimestamp(startDate);
        businessEntityContactDetail.setRecordTerminationTimestamp(terminatedDate);
        businessEntityContactDetail.setRecordCreatedBy("JASON");
        businessEntityContactDetail.setChangeReasonMemo("New Record");
        businessEntityContactDetail.setCurrentRecordIndicator(true);
        businessEntityContactDetail.setChangeVersionID(BigInteger.ONE);
        businessEntityContactDetail.setBusinessEntityID("5");
        businessEntityContactDetail.setBusinessEntityContactDetailType("MAILING_ADDRESS_1");
        businessEntityContactDetail.setBusinessEntityOtherAddressLine1("10478 Queens Blvd.");
        businessEntityContactDetail.setBusinessEntityOtherCity("Forest Hills");
        businessEntityContactDetail.setBusinessEntityOtherState(StateCode.NY);
        businessEntityContactDetail.setBusinessEntityOtherZip4("11375");
        businessEntityContactDetailList.add(businessEntityContactDetail);

        List<BusinessEntityIDCrosswalk> businessEntityIDCrosswalkList = new ArrayList<>();
        BusinessEntityIDCrosswalk businessEntityIDCrosswalk = new BusinessEntityIDCrosswalk();

        businessEntityIDCrosswalk.setRecordCreateTimestamp(startDate);
        businessEntityIDCrosswalk.setRecordUpdateTimestamp(startDate);
        businessEntityIDCrosswalk.setRecordEffectiveTimestamp(startDate);
        businessEntityIDCrosswalk.setRecordTerminationTimestamp(terminatedDate);
        businessEntityIDCrosswalk.setRecordCreatedBy("JASON");
        businessEntityIDCrosswalk.setChangeVersionID(BigInteger.ONE);
        businessEntityIDCrosswalk.setBusinessEntityID("5");
        businessEntityIDCrosswalk.setBusinessEntityIDType("VENDOR");
        businessEntityIDCrosswalk.setBusinessEntityIDQualifier("VENDOR_1");
        businessEntityIDCrosswalk.setBusinessEntityIDNumber("12345");
        businessEntityIDCrosswalk.setBusinessEntityIDCreatingOrganization("Paypro");
        businessEntityIDCrosswalkList.add(businessEntityIDCrosswalk);


        List<BusinessEntityRole> businessEntityRoleList = new ArrayList<>();
        BusinessEntityRole businessEntityRole = new BusinessEntityRole();
        businessEntityRole.setChangeVersionID(BigInteger.ONE);
        businessEntityRole.setRecordCreatedBy("JASON");
        businessEntityRole.setBusinessEntityID("5");
        businessEntityRole.setBusinessEntityRoleID("1");
        businessEntityRole.setChangeReasonMemo("New Record");
        businessEntityRole.setBusinessEntityRoleName("Test Role");
        businessEntityRole.setCurrentRecordIndicator(true);
        businessEntityRole.setRecordCreateTimestamp(startDate);
        businessEntityRole.setRecordUpdateTimestamp(startDate);
        businessEntityRole.setRecordEffectiveTimestamp(startDate);
        businessEntityRole.setRecordTerminationTimestamp(terminatedDate);
        businessEntityRoleList.add(businessEntityRole);

        List<BusinessEntityStaffRole> businessEntityStaffRoleList = new ArrayList<>();
        BusinessEntityStaffRole businessEntityStaffRole = new BusinessEntityStaffRole();
        businessEntityStaffRole.setBusinessEntityID("5");
        businessEntityStaffRole.setRecordCreatedBy("JASON");
        businessEntityStaffRole.setChangeVersionID(BigInteger.ONE);
        businessEntityStaffRole.setRecordCreateTimestamp(startDate);
        businessEntityStaffRole.setRecordEffectiveTimestamp(startDate);
        businessEntityStaffRole.setCurrentRecordIndicator(true);
        businessEntityStaffRole.setRecordUpdateTimestamp(startDate);
        businessEntityStaffRole.setBusinessEntityRoleSK(BigInteger.valueOf(1));
        businessEntityStaffRole.setStaffID("35830985494856");
        businessEntityStaffRole.setRecordTerminationTimestamp(terminatedDate);
        businessEntityStaffRoleList.add(businessEntityStaffRole);

        List<BusinessEntityRelationship> businessEntityRelationshipList = new ArrayList<>();
        BusinessEntityRelationship businessEntityRelationship = new BusinessEntityRelationship();
        businessEntityRelationship.setBusinessEntityLevelSK(BigInteger.ONE);
        businessEntityRelationship.setBusinessEntityID("5");
        businessEntityRelationship.setBusinessEntityParentID("5");
        businessEntityRelationship.setRecordCreateTimestamp(startDate);
        businessEntityRelationship.setRecordUpdateTimestamp(startDate);
        businessEntityRelationship.setCurrentRecordIndicator(true);
        businessEntityRelationship.setRecordEffectiveTimestamp(startDate);
        businessEntityRelationship.setBusinessEntityRelationshipID("1");
        businessEntityRelationship.setBusinessEntityRelationshipStatus("test");
        businessEntityRelationship.setBusinessEntityRelationshipType("TYP");
        businessEntityRelationship.setChangeVersionID(BigInteger.ONE);
        businessEntityRelationship.setChangeReasonMemo("New Record");
        businessEntityRelationship.setRecordCreatedBy("JASON");
        businessEntityRelationship.setRecordTerminationTimestamp(terminatedDate);
        businessEntityRelationshipList.add(businessEntityRelationship);

        List<BusinessEntityPayer> businessEntityPayerList = new ArrayList<>();
        BusinessEntityPayer businessEntityPayer = new BusinessEntityPayer();
        businessEntityPayerList.add(businessEntityPayer);
        businessEntityPayer.setRecordCreateTimestamp(startDate);
        businessEntityPayer.setRecordUpdateTimestamp(startDate);
        businessEntityPayer.setRecordEffectiveTimestamp(startDate);
        businessEntityPayer.setRecordTerminationTimestamp(terminatedDate);
        businessEntityPayer.setRecordCreatedBy("JASON");
        businessEntityPayer.setChangeReasonMemo("New Reason");
        businessEntityPayer.setCurrentRecordIndicator(true);
        businessEntityPayer.setChangeVersionID(BigInteger.ONE);
        businessEntityPayer.setBusinessEntityID("5");
        businessEntityPayer.setPayerSK(BigInteger.valueOf(11291L));

        List<BusinessEntityLineOfBusiness> businessEntityLineOfBusinessList = new ArrayList<>();
        BusinessEntityLineOfBusiness businessEntityLineOfBusiness = new BusinessEntityLineOfBusiness();
        businessEntityLineOfBusinessList.add(businessEntityLineOfBusiness);
        businessEntityLineOfBusiness.setBusinessEntityLineOfBusinessID("1");
        businessEntityLineOfBusiness.setRecordCreateTimestamp(startDate);
        businessEntityLineOfBusiness.setRecordUpdateTimestamp(startDate);
        businessEntityLineOfBusiness.setRecordEffectiveTimestamp(startDate);
        businessEntityLineOfBusiness.setRecordTerminationTimestamp(terminatedDate);
        businessEntityLineOfBusiness.setRecordCreatedBy("JASON");
        businessEntityLineOfBusiness.setChangeReasonMemo("New Record");
        businessEntityLineOfBusiness.setCurrentRecordIndicator(true);
        businessEntityLineOfBusiness.setChangeVersionID(BigInteger.ONE);
        businessEntityLineOfBusiness.setBusinessEntityID("5");
        businessEntityLineOfBusiness.setBusinessEntityLineOfBusiness("MEDI");
        businessEntityLineOfBusiness.setBusinessEntityLineOfBusinessDescription("Medicare");

        List<BusinessEntityElectronicFundsTransfer> businessEntityElectronicFundsTransferList = new ArrayList<>();
        BusinessEntityElectronicFundsTransfer businessEntityElectronicFundsTransfer = new BusinessEntityElectronicFundsTransfer();
        businessEntityElectronicFundsTransferList.add(businessEntityElectronicFundsTransfer);
        businessEntityElectronicFundsTransfer.setBusinessEntityElectronicFundsTransferID("1");
        businessEntityElectronicFundsTransfer.setRecordCreateTimestamp(startDate);
        businessEntityElectronicFundsTransfer.setRecordUpdateTimestamp(startDate);
        businessEntityElectronicFundsTransfer.setRecordEffectiveTimestamp(startDate);
        businessEntityElectronicFundsTransfer.setRecordTerminationTimestamp(terminatedDate);
        businessEntityElectronicFundsTransfer.setRecordCreatedBy("JASON");
        businessEntityElectronicFundsTransfer.setChangeReasonMemo("New Record");
        businessEntityElectronicFundsTransfer.setCurrentRecordIndicator(true);
        businessEntityElectronicFundsTransfer.setChangeVersionID(BigInteger.ONE);
        businessEntityElectronicFundsTransfer.setBusinessEntityID("5");

        List<BusinessEntityCredential> businessEntityCredentialList = new ArrayList<>();
        BusinessEntityCredential businessEntityCredential = new BusinessEntityCredential();
        businessEntityCredentialList.add(businessEntityCredential);
        businessEntityCredential.setBusinessEntityCredentialID("1");
        businessEntityCredential.setRecordCreateTimestamp(startDate);
        businessEntityCredential.setRecordUpdateTimestamp(startDate);
        businessEntityCredential.setRecordEffectiveTimestamp(startDate);
        businessEntityCredential.setRecordTerminationTimestamp(terminatedDate);
        businessEntityCredential.setRecordCreatedBy("JASON");
        businessEntityCredential.setChangeReasonMemo("New Record");
        businessEntityCredential.setCurrentRecordIndicator(true);
        businessEntityCredential.setChangeVersionID(BigInteger.ONE);
        businessEntityCredential.setBusinessEntityID("5");

        List<BusinessEntityCompliance> businessEntityComplianceList = new ArrayList<>();
        BusinessEntityCompliance businessEntityCompliance = new BusinessEntityCompliance();
        businessEntityComplianceList.add(businessEntityCompliance);
        businessEntityCompliance.setRecordCreateTimestamp(startDate);
        businessEntityCompliance.setRecordUpdateTimestamp(startDate);
        businessEntityCompliance.setChangeVersionID(BigInteger.ONE);
        businessEntityCompliance.setBusinessEntityID("5");
        businessEntityCompliance.setBusinessEntityComplianceLookupSK(BigInteger.valueOf(1));

        System.out.println(GSONProvider.ToJson(businessEntity));
        System.out.println(GSONProvider.ToJson(businessEntityContactDetailList));
        System.out.println(GSONProvider.ToJson(businessEntityIDCrosswalkList));
        System.out.println(GSONProvider.ToJson(businessEntityRoleList));
        System.out.println(GSONProvider.ToJson(businessEntityStaffRoleList));
        System.out.println(GSONProvider.ToJson(businessEntityRelationshipList));
        System.out.println(GSONProvider.ToJson(businessEntityPayerList));
        System.out.println(GSONProvider.ToJson(businessEntityLineOfBusinessList));
        System.out.println(GSONProvider.ToJson(businessEntityElectronicFundsTransferList));
        System.out.println(GSONProvider.ToJson(businessEntityCredentialList));
        System.out.println(GSONProvider.ToJson(businessEntityComplianceList));

    }

    @Override
    protected void onSetup() throws Exception {
    }
}
