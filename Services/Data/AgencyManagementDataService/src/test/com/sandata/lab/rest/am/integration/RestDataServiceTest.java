package com.sandata.lab.rest.am.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.agency.AgencyAdditionalSettings;
import com.sandata.lab.data.model.agency.AgencyGeneralSettings;
import com.sandata.lab.data.model.agency.AgencyVisitVerificationSettings;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.am.impl.OracleDataService;
import com.sandata.lab.rest.am.impl.RestDataService;
import mockit.Mocked;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>RestDataServiceTest.java</p>
 * <p><t>Unit tests for RestDataService.</t></p>
 * <p>Date: 6/1/16</p>
 * <p>Time: 1:22 PM</p>
 *
 * @author jasonscott
 */
public class RestDataServiceTest extends BaseIntegrationTest {

    private RestDataService restDataService;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Test
    public void testDeleteBusinessEntityComplianceCategoryLookup() {

        exchange.getIn().setHeader("connectionType", ConnectionType.COREDATA);
        exchange.getIn().setHeader("CamelHttpMethod", "DELETE");
        exchange.getIn().setHeader("operationName", "PKG_AM_deleteBeCompCtgyLkup_BusinessEntityComplianceCategoryLookup");
        exchange.getIn().setHeader("sequence_key", 4);

        restDataService.delete(exchange);
    }

    @Test
    public void testGetBeStaffTrngWithPaginationSortAndOption() {
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_BSN_getBeStaffTrngLkup_BusinessEntityStaffTrainingLookup");
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("staff_trng_name", "Test");
        exchange.getIn().setHeader("staff_trng_ctgy_code", "5617");
        exchange.getIn().setHeader("page", 1);
        exchange.getIn().setHeader("page_size", 10);
        exchange.getIn().setHeader("sort_on", "REC_CREATE_TMSTP");
        exchange.getIn().setHeader("direction", "ASC");

        restDataService.getBeStaffTrngWithPaginationSortAndOption(exchange);

        List<BusinessEntityStaffTrainingLookup> businessEntityStaffTrainingList = (List<BusinessEntityStaffTrainingLookup>) exchange.getIn().getBody();
        assertNotNull(businessEntityStaffTrainingList);

        Integer totalRows = (Integer) exchange.getIn().getHeader("TOTAL_ROWS");
        assertCollectionSize(businessEntityStaffTrainingList, totalRows);

        System.out.println(GSONProvider.ToJson(businessEntityStaffTrainingList));
    }

    @Test
    public void testPrintBusinessEntityStaffTrainingLookupEntities() throws ParseException {
        Date now = new Date();
        Date term = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("12/31/9999 00:00:00");
        BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup = new BusinessEntityStaffTrainingLookup();
        businessEntityStaffTrainingLookup.setRecordCreateTimestamp(now);
        businessEntityStaffTrainingLookup.setRecordUpdateTimestamp(now);
        businessEntityStaffTrainingLookup.setRecordEffectiveTimestamp(now);
        businessEntityStaffTrainingLookup.setRecordTerminationTimestamp(term);
        businessEntityStaffTrainingLookup.setRecordCreatedBy("JASON");
        businessEntityStaffTrainingLookup.setChangeReasonMemo("New record");
        businessEntityStaffTrainingLookup.setCurrentRecordIndicator(true);
        businessEntityStaffTrainingLookup.setChangeVersionID(BigInteger.ONE);
        businessEntityStaffTrainingLookup.setBusinessEntityID("5");
        businessEntityStaffTrainingLookup.setStaffTrainingCode("8675");
        businessEntityStaffTrainingLookup.setStaffTrainingName("Test Staff Training");
        businessEntityStaffTrainingLookup.setStaffTrainingDescription("Test Staff Training Description");
        businessEntityStaffTrainingLookup.setStaffTrainingQualifier(StaffTrainingQualifier.TRAINING);
        businessEntityStaffTrainingLookup.setStaffTrainingMandatoryIndicator(true);
        businessEntityStaffTrainingLookup.setStaffTrainingTotalHours(BigDecimal.TEN);
        businessEntityStaffTrainingLookup.setStaffTrainingNotes("Bring pen and paper!");

        List<BusinessEntityStaffTrainingCategoryList> businessEntityStaffTrainingCategoryLists = new ArrayList<>();
        BusinessEntityStaffTrainingCategoryList businessEntityStaffTrainingCategoryList = new BusinessEntityStaffTrainingCategoryList();
        businessEntityStaffTrainingCategoryLists.add(businessEntityStaffTrainingCategoryList);
        businessEntityStaffTrainingLookup.getBusinessEntityStaffTrainingCategoryList().addAll(businessEntityStaffTrainingCategoryLists);

        businessEntityStaffTrainingCategoryList.setRecordCreateTimestamp(now);
        businessEntityStaffTrainingCategoryList.setRecordUpdateTimestamp(now);
        businessEntityStaffTrainingCategoryList.setRecordEffectiveTimestamp(now);
        businessEntityStaffTrainingCategoryList.setRecordTerminationTimestamp(term);
        businessEntityStaffTrainingCategoryList.setRecordCreatedBy("JASON");
        businessEntityStaffTrainingCategoryList.setChangeReasonMemo("New record");
        businessEntityStaffTrainingCategoryList.setCurrentRecordIndicator(true);
        businessEntityStaffTrainingCategoryList.setChangeVersionID(BigInteger.ONE);
        businessEntityStaffTrainingCategoryList.setBusinessEntityID("5");
        businessEntityStaffTrainingCategoryList.setStaffTrainingCategoryCode("5617");
        businessEntityStaffTrainingCategoryList.setStaffTrainingCode("8675");
        businessEntityStaffTrainingCategoryList.setStaffTrainingQualifier(StaffTrainingQualifier.TRAINING);


        System.out.println(GSONProvider.ToJson(businessEntityStaffTrainingLookup));
    }

    private void setupMetadataConnection() throws SQLException, UniversalConnectionPoolException {
        this.sandataOracleConnection.stopPool();

        this.sandataOracleConnection =
            new SandataOracleConnection()
//                .DatabaseName("sdbdev1")
                .DatabaseName("sdbdev2")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
//                .User("coredata")
                .User("metadata")
//                .Password("z3U0kCdbdN")
//                .Password("Z4fEIRn7D2")
                .Password("R63JumYQ6L")
                .Open();

        this.sandataOracleConnection.startPool();
    }

    @Test
    public void testInsertApplicationTenantKeyConfiguration() {

        try {
            setupMetadataConnection();
        } catch (SQLException | UniversalConnectionPoolException e) {
            e.printStackTrace();
            return;
        }

        Date nowDate = new Date();
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);
        exchange.getIn().setHeader("CamelHttpMethod", "POST");
        exchange.getIn().setHeader("operationName", "PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration");


        ApplicationTenantKeyConfiguration applicationTenantKeyConfigurationGeneral = new ApplicationTenantKeyConfiguration();
        applicationTenantKeyConfigurationGeneral.setRecordCreateTimestamp(nowDate);
        applicationTenantKeyConfigurationGeneral.setRecordUpdateTimestamp(nowDate);
        applicationTenantKeyConfigurationGeneral.setKeyName("AGNCY_MGMT_PAT_STAFF_GEN");

        AgencyGeneralSettings agencyGeneralSettings = new AgencyGeneralSettings();
        agencyGeneralSettings.setMaxSchedulingRecurring(16);
        agencyGeneralSettings.setPayrollFrequency("Weekly");
        agencyGeneralSettings.setPayrollEndDay("Friday");
        agencyGeneralSettings.setPayrollEndTime("5:00PM");
        agencyGeneralSettings.setWeekendStartDay("Saturday");
        agencyGeneralSettings.setRequiresNotesOnAllReasonCodess(false);
        agencyGeneralSettings.setRequiresDischargeNote(false);
        agencyGeneralSettings.setEnforceAllReasonCodes(false);
        agencyGeneralSettings.setDefaultScheduleViewToCurrentWeek(false);
        agencyGeneralSettings.setShowVerifiedTimesOnSchedule(true);
        agencyGeneralSettings.setAllowMoreThan24ScheduledHours(false);
        agencyGeneralSettings.setMatchStaffSkillsToPatientRequirements(false);
        agencyGeneralSettings.setMaxAllowedWorkHours(40);
        agencyGeneralSettings.setOvertimeRestriction("warn");
        agencyGeneralSettings.setRequiresReasonCodeForOvertime(true);
        applicationTenantKeyConfigurationGeneral.setTenantKeyConfigurationValue(GSONProvider.ToJson(agencyGeneralSettings));

        exchange.getIn().setBody(applicationTenantKeyConfigurationGeneral);

        restDataService.insertApplicationTenantKeyConfiguration(exchange);
        int resultSk = (Integer) exchange.getIn().getBody();
        assertNotNull(resultSk);
        System.out.println(resultSk);
    }

    @Test
    public void testGetApplicationTenantKeyConfiguration() {
        try {
            setupMetadataConnection();
        } catch (SQLException | UniversalConnectionPoolException e) {
            e.printStackTrace();
            return;
        }

        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        restDataService.getApplicationTenantKeyConfigurationForBsnEntIdAndKeyName(exchange);
        List<ApplicationTenantKeyConfiguration> applicationTenantKeyConfigurationList = (List<ApplicationTenantKeyConfiguration>) exchange.getIn().getBody();
        assertNotNull(applicationTenantKeyConfigurationList);
    }

    @Test
    public void printTestEntities() throws ParseException {

        Date nowDate = new Date();
        String terminatedDateString = "9999-12-31 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date terminatedDate = dateFormat.parse(terminatedDateString);
        // APP_TENANT_KEY
        List<ApplicationTenantKeyConfiguration> applicationTenantKeyConfigurationList = new ArrayList<>();
        ApplicationTenantKeyConfiguration applicationTenantKeyConfigurationGeneral = new ApplicationTenantKeyConfiguration();
        applicationTenantKeyConfigurationGeneral.setRecordCreateTimestamp(nowDate);
        applicationTenantKeyConfigurationGeneral.setRecordUpdateTimestamp(nowDate);
//        applicationTenantKeyConfiguration.setApplicationTenantSK(BigInteger.ONE);
        applicationTenantKeyConfigurationGeneral.setKeyName("AGNCY_MGMT_PAT_STAFF_GEN");
        AgencyGeneralSettings agencyGeneralSettings = new AgencyGeneralSettings();
        agencyGeneralSettings.setMaxSchedulingRecurring(16);
        agencyGeneralSettings.setPayrollFrequency("Weekly");
        agencyGeneralSettings.setPayrollEndDay("Friday");
        agencyGeneralSettings.setPayrollEndTime("5:00PM");
        agencyGeneralSettings.setWeekendStartDay("Saturday");
        agencyGeneralSettings.setRequiresNotesOnAllReasonCodess(false);
        agencyGeneralSettings.setRequiresDischargeNote(false);
        agencyGeneralSettings.setEnforceAllReasonCodes(false);
        agencyGeneralSettings.setDefaultScheduleViewToCurrentWeek(false);
        agencyGeneralSettings.setShowVerifiedTimesOnSchedule(true);
        agencyGeneralSettings.setAllowMoreThan24ScheduledHours(false);
        agencyGeneralSettings.setMatchStaffSkillsToPatientRequirements(false);
        agencyGeneralSettings.setMaxAllowedWorkHours(40);
        agencyGeneralSettings.setOvertimeRestriction("warn");
        agencyGeneralSettings.setRequiresReasonCodeForOvertime(true);

        applicationTenantKeyConfigurationGeneral.setTenantKeyConfigurationValue(GSONProvider.ToJson(agencyGeneralSettings));
        applicationTenantKeyConfigurationList.add(applicationTenantKeyConfigurationGeneral);

        ApplicationTenantKeyConfiguration applicationTenantKeyConfigurationAdditional = new ApplicationTenantKeyConfiguration();
        applicationTenantKeyConfigurationAdditional.setRecordCreateTimestamp(nowDate);
        applicationTenantKeyConfigurationAdditional.setRecordUpdateTimestamp(nowDate);
        //applicationTenantKeyConfigurationAdditional.setApplicationTenantSK(BigInteger.ONE);
        applicationTenantKeyConfigurationAdditional.setKeyName("AGNCY_MGMT_PAT_STAFF_ADD");
        AgencyAdditionalSettings agencyAdditionalSettings = new AgencyAdditionalSettings();
        agencyAdditionalSettings.setPatientAssessmentFrequency("3 Months");
        agencyAdditionalSettings.setDefaultStatusForNewStaff("Recruit");
        agencyAdditionalSettings.setNumberOfUnscheduledDaysForAutoTermination("120");
        agencyAdditionalSettings.setShowStaffUnionStatus(true);
        agencyAdditionalSettings.setAutoGeneratePatientAndStaffId(true);
        applicationTenantKeyConfigurationAdditional.setTenantKeyConfigurationValue(GSONProvider.ToJson(agencyAdditionalSettings));
        applicationTenantKeyConfigurationList.add(applicationTenantKeyConfigurationAdditional);

        ApplicationTenantKeyConfiguration applicationTenantKeyConfigurationVisitVerification = new ApplicationTenantKeyConfiguration();
        applicationTenantKeyConfigurationVisitVerification.setRecordCreateTimestamp(nowDate);
        applicationTenantKeyConfigurationVisitVerification.setRecordUpdateTimestamp(nowDate);
        //        applicationTenantKeyConfigurationVisitVerification.setApplicationTenantSK(BigInteger.ONE);
        applicationTenantKeyConfigurationVisitVerification.setKeyName("AGNCY_MGMT_PAT_STAFF_VIS_VER");
        AgencyVisitVerificationSettings agencyVisitVerificationSettings = new AgencyVisitVerificationSettings();
        agencyVisitVerificationSettings.setNumberOfCriticalTasksRequired("1");
        agencyVisitVerificationSettings.setRequireReasonCodes(false);
        agencyVisitVerificationSettings.setAllowMoreThanTwentyFourVerifiedHours(false);
        applicationTenantKeyConfigurationList.add(applicationTenantKeyConfigurationVisitVerification);

        BusinessEntityLanguageList businessEntityLanguageList = new BusinessEntityLanguageList();
        businessEntityLanguageList.setRecordCreateTimestamp(nowDate);
        businessEntityLanguageList.setRecordUpdateTimestamp(nowDate);
        businessEntityLanguageList.setRecordEffectiveTimestamp(nowDate);
        businessEntityLanguageList.setRecordTerminationTimestamp(terminatedDate);
        businessEntityLanguageList.setRecordCreatedBy("JASON");
        businessEntityLanguageList.setChangeReasonMemo("New Record");
        businessEntityLanguageList.setCurrentRecordIndicator(true);
        businessEntityLanguageList.setChangeVersionID(BigInteger.ONE);
        businessEntityLanguageList.setBusinessEntityID("5");
        businessEntityLanguageList.setLanguageCode("ENG");
        businessEntityLanguageList.setLanguageName("English");
        businessEntityLanguageList.setLanguageDescription("English Language");

        System.out.print(GSONProvider.ToJson(applicationTenantKeyConfigurationList));
        System.out.print(GSONProvider.ToJson(businessEntityLanguageList));

    }

    @Override
    protected void onSetup() throws Exception {
        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = getSandataOracleConnection().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = getSandataOracleConnection().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }
        };

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(connectionPoolDataService);
        restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);
    }
}
