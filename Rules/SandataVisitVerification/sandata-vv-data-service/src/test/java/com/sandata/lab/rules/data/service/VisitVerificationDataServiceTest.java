package com.sandata.lab.rules.data.service;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.app.AppComponent;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.agency.AgencyVisitVerificationSettings;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rules.data.service.impl.VisitVerificationDataService;
import com.sandata.lab.rules.data.service.utils.CommonUtils;
import com.sandata.lab.rules.vv.fact.ScheduleEventFact;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.fact.VisitForExceptionsFact;
import com.sandata.lab.rules.vv.model.CallPreferences;
import mockit.Mocked;
import oracle.ucp.UniversalConnectionPoolException;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * <p>Test class for visit verification database services.</p>
 *
 * @author jasonscott
 */
public class VisitVerificationDataServiceTest {
    private MockEndpoint resultEndpoint;
    private Exchange exchange;
    private VisitVerificationDataService visitVerificationDataService;
    private SandataOracleConnection sandataOracleConnection;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Before
    public void beforeTest() throws Exception {
        resultEndpoint = new MockEndpoint("mock:result", new AppComponent());
        exchange = new DefaultExchange(resultEndpoint);

        // Default Pattern
        exchange.setPattern(ExchangePattern.InOut);

        Message in = new DefaultMessage();
        exchange.setIn(in);
        onSetup();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    public VisitVerificationDataServiceTest() throws Exception {
    }


    private void onSetup() throws Exception {

        setupCoredataConnection();

        visitVerificationDataService = new VisitVerificationDataService();

        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {

                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    if (connectionType.equals(ConnectionType.COREDATA)) {
                        connection = setupCoredataConnection().getConnection();
                    } else if (connectionType.equals(ConnectionType.METADATA)) {
                        connection = setupMetadataConnection().getConnection();
                    }

                } catch (Exception e) {

                }

                return connection;
            }
        };

        this.visitVerificationDataService.setConnectionPoolDataService(connectionPoolDataService);
    }

    private SandataOracleConnection setupCoredataConnection() throws SQLException, UniversalConnectionPoolException {
        if (sandataOracleConnection != null) {
            sandataOracleConnection.stopPool();
        }

        this.sandataOracleConnection =
            new SandataOracleConnection()
//                .DatabaseName("sdbdev1")
                .DatabaseName("sdbdev2")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
                .User("coredata")
//                .Password("z3U0kCdbdN")
                .Password("Z4fEIRn7D2")
                .Open();

        this.sandataOracleConnection.startPool();

        return this.sandataOracleConnection;
    }

    private SandataOracleConnection setupMetadataConnection() throws SQLException, UniversalConnectionPoolException {
        if (sandataOracleConnection != null) {
            sandataOracleConnection.stopPool();
        }
        this.sandataOracleConnection =
            new com.sandata.lab.common.oracle.db.connection.SandataOracleConnection()
                .DatabaseName("sdbdev1")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
                .User("metadata")
                .Password("KjmSX5RE8O")
                .Open();


        this.sandataOracleConnection.startPool();

        return this.sandataOracleConnection;
    }


    @Test
    public void testGetBsnEntIdDnisMapping() {
        visitVerificationDataService.getBsnEntIdDnisMapping(exchange);

        Map<String, String> bsnEntDnisMap = (Map<String, String>) exchange.getIn().getBody();
        assertNotNull(bsnEntDnisMap);
        System.out.println(bsnEntDnisMap);
    }

    @Test
    public void testGetScheduleEventEntities() {
        exchange.getIn().setHeader("bsnEntId", "1");
        exchange.getIn().setHeader("lowerLimitDateString", "2016-09-28 09:00:00");
        exchange.getIn().setHeader("upperLimitDateString", "2016-09-28 17:00:00");
        visitVerificationDataService.getScheduleEventEntities(exchange);
        List<ScheduleEventFact> scheduleEventList = (List<ScheduleEventFact>) exchange.getIn().getBody();
        assertNotNull(scheduleEventList);
        System.out.println(scheduleEventList);
    }

    @Test
    public void testGetBusinessEntityCallPreferences() {
        exchange.getIn().setHeader("bsnEntId", "1");
        visitVerificationDataService.getBusinessEntityCallPreferences(exchange);
        CallPreferences callPreferences = (CallPreferences) exchange.getIn().getBody();
        assertNotNull(callPreferences);
        System.out.println(callPreferences);
    }

    @Test
    public void testGetVisitBySchedEvntSk() {
        Connection connection = null;

        try {
            connection = visitVerificationDataService.getConnectionPoolDataService().getConnection(ConnectionType.COREDATA);
            String bsnEntId = "1";
            Long schedEvntSk = 16737L;
            List<Visit> visitList = visitVerificationDataService.getVisitBySchedEvntSk(connection, bsnEntId, schedEvntSk);
            assertNotNull(visitList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testGetVisitEventListForVisitSk() {
        Connection connection = null;

        try {
            connection = visitVerificationDataService.getConnectionPoolDataService().getConnection(ConnectionType.COREDATA);
            Integer visitSk = 763817;
            List<VisitEvent> visitEventList = visitVerificationDataService.getVisitEventListForVisitSk(connection, visitSk);
            assertNotNull(visitEventList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testInsertPlannedVisitEvent() throws ParseException {
        // TODO (jasonscott) Need to modify this so that we are not inserting a duplicate VisitEvent every time the project is built and this test is run.
//        ScheduleEventFact scheduleEventFact = createScheduleEventFact();
//        scheduleEventFact.setScheduleEventSK(BigInteger.valueOf(708934));
//
//        VisitEventFact visitEventFact = createVisitEventFact();
//        visitEventFact.setVisitEventDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-17 11:58:12"));
//        visitEventFact.setScheduleEventFact(scheduleEventFact);
//        visitEventFact.setBusinessEntityId("5");
//
//        exchange.getIn().setBody(visitEventFact);
//
//        visitVerificationDataService.insertPlannedVisitEvent(exchange);
//
//        BigInteger visitEventSk = (BigInteger) exchange.getIn().getBody();
//        assertNotNull(visitEventSk);
    }

    @Test
    public void testCreateScheduleEventEntity() throws ParseException {
        System.out.println(GSONProvider.ToJson(createScheduleEventFact()));
    }

    private ScheduleEventFact createScheduleEventFact() throws ParseException {
        Date nowDate = new Date();
        Date terminatedDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        Date scheduledStartDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-17 10:00:00");
        Date scheduledEndDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-17 12:00:00");
        ScheduleEventFact scheduleEventFact = new ScheduleEventFact();
        scheduleEventFact.setScheduleEventSK(null);
        scheduleEventFact.setScheduleEventID("8675309");
        scheduleEventFact.setRecordCreateTimestamp(nowDate);
        scheduleEventFact.setRecordUpdateTimestamp(nowDate);
        scheduleEventFact.setRecordEffectiveTimestamp(nowDate);
        scheduleEventFact.setRecordTerminationTimestamp(terminatedDate);
        scheduleEventFact.setRecordCreatedBy("Jason");
        scheduleEventFact.setRecordUpdatedBy(null);
        scheduleEventFact.setChangeReasonCode(null);
        scheduleEventFact.setChangeReasonMemo("New Record");
        scheduleEventFact.setCurrentRecordIndicator(true);
        scheduleEventFact.setChangeVersionID(BigInteger.ZERO);
        scheduleEventFact.setBusinessEntityID("5");
        scheduleEventFact.setPatientID("8675309");
        scheduleEventFact.setAuthorizationSK(null);
        scheduleEventFact.setAuthorizationQualifier(null);
        scheduleEventFact.setStaffID("9035768");
        scheduleEventFact.setPayerID("8675309");
        scheduleEventFact.setReferralSK(null);
        scheduleEventFact.setBusinessEntityCalendarLookupSK(null);
        scheduleEventFact.setScheduleSK(null);
        scheduleEventFact.setPlanOfCareSK(null);
        scheduleEventFact.setTimezoneName("US/Eastern");
        scheduleEventFact.setScheduleEventBillRate(null);
        scheduleEventFact.setMasterRateID(null);
        scheduleEventFact.setDayOfMonth("17");
        scheduleEventFact.setScheduleEventStartDatetime(scheduledStartDateTime);
        scheduleEventFact.setScheduleEventEndDatetime(scheduledEndDateTime);
        scheduleEventFact.setScheduleEventTotalHours(BigDecimal.valueOf(2));
        scheduleEventFact.setScheduleEventStatus("PENDING");
        scheduleEventFact.setScheduleEventPayRate(null);
        scheduleEventFact.setScheduleEventUnit("HOURS");
        scheduleEventFact.setScheduleEventRestriction(null);
        scheduleEventFact.setScheduleEventComment(null);
        scheduleEventFact.setScheduleEventManuallyOverrideIndicator(null);

        return scheduleEventFact;
    }

    @Test
    public void testCreateVisitEntity() throws ParseException {
        System.out.println(GSONProvider.ToJson(createVisit()));
    }

    private Visit createVisit() throws ParseException {
        Date nowDate = new Date();
        Visit visit = new Visit();
        visit.setVisitSK(null);
        visit.setRecordCreateTimestamp(nowDate);
        visit.setRecordUpdateTimestamp(nowDate);
        visit.setRecordCreatedBy("JASON");
        visit.setRecordUpdatedBy(null);
        visit.setChangeReasonCode(null);
        visit.setChangeReasonMemo("New Record");
        visit.setChangeVersionID(BigInteger.ZERO);
        visit.setBusinessEntityID("5");
        visit.setPatientID("8675309");
        visit.setStaffID("9035768");
        visit.setScheduleEventSK(BigInteger.valueOf(708934));
        visit.setVisitActualStartTimestamp(null);
        visit.setVisitActualEndTimestamp(null);
        visit.setVisitAdjustedStartTimestamp(null);
        visit.setVisitAdjustedEndTimestamp(null);
        visit.setVisitCancelledIndicator(false);
        visit.setVisitCancellationReason(null);
        visit.setVisitApprovedIndicator(false);
        visit.setVisitVerifiedByScheduleIndicator(null);
        visit.setVisitPayByScheduleIndicator(null);
        visit.setVisitDoNotBillIndicator(null);
        visit.setVisitDoNotPayIndicator(null);
        visit.setVisitPayHours(null);
        visit.setVisitBillHours(null);
        visit.setVisitOvertimeAbsenceHours(null);
        visit.setUserName(null);
        visit.setUserGloballyUniqueIdentifier(null);
        visit.setResolutionCode(null);
        visit.setMemo(null);

        return visit;
    }

    @Test
    public void testCreateVisitEvent() throws ParseException {
        System.out.println(GSONProvider.ToJson(createVisitEventFact()));
    }

    private VisitEventFact createVisitEventFact() throws ParseException {
        Date nowDate = new Date();
        Date terminatedDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        Date eventDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-17 12:02:22");
        VisitEventFact visitEventFact = new VisitEventFact();
        visitEventFact.setVisitEventSK(null);
        visitEventFact.setVisitSK(null);
        visitEventFact.setRecordCreateTimestamp(nowDate);
        visitEventFact.setRecordUpdateTimestamp(nowDate);
        visitEventFact.setRecordEffectiveTimestamp(nowDate);
        visitEventFact.setRecordTerminationTimestamp(terminatedDate);
        visitEventFact.setRecordCreatedBy("JASON");
        visitEventFact.setRecordUpdatedBy(null);
        visitEventFact.setChangeReasonMemo(null);
        visitEventFact.setCurrentRecordIndicator(true);
        visitEventFact.setChangeVersionID(BigInteger.ZERO);
        visitEventFact.setVisitEventTypeCode(VisitEventTypeCode.TEL);
        visitEventFact.setVisitEventDatetime(eventDateTime);
        visitEventFact.setTimezoneName("US/Eastern");
        visitEventFact.setAutomaticNumberIdentification(null);
        visitEventFact.setInformationDigits(null);
        visitEventFact.setEquipmentID(null);
        visitEventFact.setStaffID("9035768");
        visitEventFact.setPatientID("8675309");
        visitEventFact.setCoordinateLatitude(null);
        visitEventFact.setCoordinateLongitide(null);
        visitEventFact.setCoordinateAccuracy(null);
        visitEventFact.setCoordinateAltitude(null);
        visitEventFact.setCoordinateAltitudeAccuracy(null);
        visitEventFact.setCoordinateHeading(null);
        visitEventFact.setCoordinateSpeed(null);
        visitEventFact.setCoordinateTimestamp(null);
        visitEventFact.setInternationalMobileStationEquipmentIdentity(null);
        visitEventFact.setCallInIndicator(true);
        visitEventFact.setCallOutIndicator(false);
        return visitEventFact;
    }

    @Test
    public void testRecalculateVisitForVisitEvents() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        VisitEvent visitEvent = createVisitEventFact().getVisit();
        visitEvent.setVisitEventDatetime(dateFormat.parse("2016-10-20 15:21:21"));

        VisitEvent visitEvent1 = createVisitEventFact().getVisit();
        visitEvent1.setVisitEventDatetime(dateFormat.parse("2016-10-20 12:52:25"));

        VisitEvent visitEvent2 = createVisitEventFact().getVisit();
        visitEvent2.setVisitEventDatetime(dateFormat.parse("2016-10-20 14:44:16"));

        Visit visit = new Visit();

        visitVerificationDataService.recalculateVisitTimesForVisitEvents(visit, visitEvent, visitEvent1, visitEvent2);

        System.out.println(visit.toString());
    }

    @Test
    public void testGetListOfVisitVerificationExceptions() {
        visitVerificationDataService.getVisitVerificationExceptionList(exchange);
        List<ExceptionLookup> exceptionLookupList = (List<ExceptionLookup>) exchange.getIn().getBody();
        assertNotNull(exceptionLookupList);
        System.out.println(exceptionLookupList);
    }

    @Test
    public void testGetBusinessEntityIdList() {
        visitVerificationDataService.getBusinessEntityIdList(exchange);
        List<String> businessEntityIdList = (List<String>) exchange.getIn().getBody();
        assertNotNull(businessEntityIdList);
        System.out.println(businessEntityIdList);
    }

    @Test
    public void testGetBusinessEntityVisitVerificationSettings() {
        exchange.getIn().setHeader("bsnEntId", "1");
        visitVerificationDataService.getBusinessEntityVisitVerificationSettings(exchange);
        AgencyVisitVerificationSettings agencyVisitVerificationSettings = (AgencyVisitVerificationSettings) exchange.getIn().getBody();
        assertNotNull(agencyVisitVerificationSettings);
        System.out.println(agencyVisitVerificationSettings);
    }

    @Test
    public void testGetBusinessEntityVisitsForExceptions() {
        exchange.getIn().setHeader("bsnEntId", "1");

        visitVerificationDataService.getBusinessEntityVisitsForExceptions(exchange);

        List<VisitForExceptionsFact> visitForExceptionsFactList = (List<VisitForExceptionsFact>) exchange.getIn().getBody();
        assertNotNull(visitForExceptionsFactList);
        System.out.println(visitForExceptionsFactList);
    }



}
