package com.sandata.lab.rest.poc.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.PlanOfCare;
import com.sandata.lab.data.model.dl.model.PlanOfCareService;
import com.sandata.lab.data.model.dl.model.PlanOfCareTaskList;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.poc.impl.OracleDataService;
import com.sandata.lab.rest.poc.impl.RestDataService;
import mockit.Mocked;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>RestDataServiceTest.java</p>
 * <p><t>Unit tests for RestDataService.</t></p>
 *
 * @author jasonscott
 */
public class RestDataServiceTest extends BaseIntegrationTest {

    private RestDataService restDataService;
    private PlanOfCare planOfCare;
    private PlanOfCareTaskList planOfCareTaskList;
    private PlanOfCareService planOfCareService;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Test
    public void printTestEntities() {
        System.out.println(GSONProvider.ToJson(planOfCare));
        System.out.println(GSONProvider.ToJson(planOfCareService));
        System.out.println(GSONProvider.ToJson(planOfCareTaskList));
    }

    @Test
    public void testGetPocTaskListForPocSk() {
        Long planOfCareSk = 80L;
        exchange.getIn().setHeader("poc_sk", planOfCareSk);
        restDataService.getPocTaskListForPocSk(exchange);
        List<PlanOfCareTaskList> planOfCareTaskListList = (List<PlanOfCareTaskList>) exchange.getIn().getBody();
        assertNotNull(planOfCareTaskListList);
        System.out.println(GSONProvider.ToJson(planOfCareTaskListList));
    }

    @Override
    protected void onSetup() throws Exception {

        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
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

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 30);
        Date end = calendar.getTime();

        String terminatedDateString = "9999-12-31 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date terminatedDate = dateFormat.parse(terminatedDateString);

        planOfCare = new PlanOfCare();

        planOfCare.setPlanOfCareID("123456");
        planOfCare.setRecordCreateTimestamp(now);
        planOfCare.setRecordUpdateTimestamp(now);
        planOfCare.setRecordEffectiveTimestamp(now);
        planOfCare.setRecordTerminationTimestamp(terminatedDate);
        planOfCare.setCurrentRecordIndicator(true);
        planOfCare.setChangeVersionID(BigInteger.ONE);
        planOfCare.setBusinessEntityID("5");
        planOfCare.setPatientID("er-608474");
        planOfCare.setAuthorizationID("{123456789");
        planOfCare.setPlanOfCareStartDate(now);
        planOfCare.setPlanOfCareEndDate(end);
        planOfCare.setPlanOfCareDaysPerWeek(BigDecimal.valueOf(2L));
        planOfCare.setPlanOfCareDay3Indicator(true);
        planOfCare.setPlanOfCareDay5Indicator(true);
        planOfCare.setPlanOfCareHoursPerDay(BigDecimal.valueOf(2L));

        List<PlanOfCareService> planOfCareServiceList = new ArrayList<>();
        planOfCareService = new PlanOfCareService();
        planOfCareServiceList.add(planOfCareService);
        planOfCare.getPlanOfCareService().addAll(planOfCareServiceList);

        planOfCareService.setPlanOfCareSK(BigInteger.valueOf(77L));
        planOfCareService.setServiceSK(BigInteger.valueOf(1L));
        planOfCareService.setRecordCreateTimestamp(now);
        planOfCareService.setRecordUpdateTimestamp(now);
        planOfCareService.setChangeVersionID(BigInteger.ONE);

        List<PlanOfCareTaskList> planOfCareTaskListList = new ArrayList<>();
        planOfCareTaskList = new PlanOfCareTaskList();
        planOfCareTaskListList.add(planOfCareTaskList);
        planOfCare.getPlanOfCareTaskList().addAll(planOfCareTaskListList);

        planOfCareTaskList.setRecordCreateTimestamp(now);
        planOfCareTaskList.setRecordUpdateTimestamp(now);
        planOfCareTaskList.setChangeVersionID(BigInteger.ONE);
        planOfCareTaskList.setTaskSK(BigInteger.valueOf(10L));
        planOfCareTaskList.setPlanOfCareSK(BigInteger.valueOf(79L));
    }
}
