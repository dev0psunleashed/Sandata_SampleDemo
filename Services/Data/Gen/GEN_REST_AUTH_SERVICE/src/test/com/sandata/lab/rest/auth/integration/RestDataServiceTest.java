package com.sandata.lab.rest.auth.integration;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.auth.impl.OracleDataService;
import com.sandata.lab.rest.auth.impl.RestDataService;
import mockit.Mocked;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * <p>RestDataServiceTest.java</p>
 * <p><t>Unit tests for RestDataService.</t></p>
 *
 * @author jasonscott
 */
public class RestDataServiceTest extends BaseIntegrationTest {

    private RestDataService restDataService;
    private Authorization authorization;
    private AuthorizationService authorizationService;
    private Order order;
    private OrderService orderService;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Test
    public void printTestEntities() throws ParseException {
        System.out.println(GSONProvider.ToJson(authorization));
        System.out.println(GSONProvider.ToJson(authorizationService));
        System.out.println(GSONProvider.ToJson(order));
        System.out.println(GSONProvider.ToJson(orderService));
    }

    @Test
    public void testTransformAuthToOrder() {
        Order order = restDataService.transformAuthorizationToOrder(authorization);
        assertNotNull(order);
        System.out.println(GSONProvider.ToJson(order));
    }

    @Test
    public void testTransformAuthServiceToOrderService() {
        OrderService orderService = restDataService.transformAuthorizationServiceToOrderService(authorizationService);
        assertNotNull(orderService);
        System.out.println(GSONProvider.ToJson(orderService));
    }

    @Test
    public void testTransformOrderServiceToAuthService() {
        AuthorizationService authorizationService = restDataService.transformOrderServiceToAuthorizationService(orderService);
        assertNotNull(authorizationService);
        System.out.println(GSONProvider.ToJson(authorizationService));
    }

    @Test
    public void testTransformOrderToAuth() {
        Authorization authorization = restDataService.transformOrderToAuthorization(order);
        assertNotNull(authorization);
        System.out.println(GSONProvider.ToJson(authorization));
    }

    @Test
    public void testGetAuthOrOdSvcForSk() {
        exchange.getIn().setHeader("ord_sk", 4L);
        exchange.getIn().setHeader("operationName", "PKG_AUTH_getOdSvc_OrderService");
        restDataService.getAuthOrOrdSvcForSk(exchange);
        assertNotNull(exchange.getIn().getBody());
    }

    @Test
    public void testGetActiveAuthAndOrderList() {
        exchange.getIn().setHeader("operationName", "PKG_AUTH_getActiveAuth_Authorization");
        MessageContentsList messageContentsList = new MessageContentsList();
        messageContentsList.add(0, "17044");
        messageContentsList.add(1, "5");
        messageContentsList.add(2, 1);
        messageContentsList.add(3, 10);
        messageContentsList.add(4, "REC_CREATE_TMSTP");
        messageContentsList.add(5, "ASC");
        exchange.getIn().setBody(messageContentsList);
        restDataService.getActiveOrHistoricAuthAndOrderList(exchange);
        List<GetAuthResult> authorizationExtList = (List<GetAuthResult>) exchange.getIn().getBody();
        assertNotNull(authorizationExtList);

        Response response = new Response();
        response.setData(authorizationExtList);
        response.setTotalRows((Integer) exchange.getIn().getHeader("TOTAL_ROWS"));
        response.setPage((Integer) exchange.getIn().getHeader("PAGE"));
        response.setPageSize((Integer) exchange.getIn().getHeader("PAGE_SIZE"));
        response.setOrderByColumn((String) exchange.getIn().getHeader("ORDER_BY_COLUMN"));
        response.setOrderByDirection((String) exchange.getIn().getHeader("ORDER_BY_DIRECTION"));

        System.out.println(GSONProvider.ToJson(response));
    }

    @Test
    public void testGetHistoricAuthAndOrderList() {
        exchange.getIn().setHeader("operationName", "PKG_AUTH_getHistoricAuth_Authorization");
        MessageContentsList messageContentsList = new MessageContentsList();
        messageContentsList.add(0, "1231234");
        messageContentsList.add(1, "1");
        messageContentsList.add(2, 1);
        messageContentsList.add(3, 10);
        messageContentsList.add(4, "REC_CREATE_TMSTP");
        messageContentsList.add(5, "ASC");
        exchange.getIn().setBody(messageContentsList);
        restDataService.getActiveOrHistoricAuthAndOrderList(exchange);
        List<GetAuthResult> authorizationExtList = (List<GetAuthResult>) exchange.getIn().getBody();
        assertNotNull(authorizationExtList);

        Response response = new Response();
        response.setData(authorizationExtList);
        response.setTotalRows((Integer) exchange.getIn().getHeader("TOTAL_ROWS"));
        response.setPage((Integer) exchange.getIn().getHeader("PAGE"));
        response.setPageSize((Integer) exchange.getIn().getHeader("PAGE_SIZE"));
        response.setOrderByColumn((String) exchange.getIn().getHeader("ORDER_BY_COLUMN"));
        response.setOrderByDirection((String) exchange.getIn().getHeader("ORDER_BY_DIRECTION"));

        System.out.println(GSONProvider.ToJson(response));
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

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.YEAR, 2016);
        Date end = calendar.getTime();

        String terminatedDateString = "9999-12-31 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date terminatedDate = dateFormat.parse(terminatedDateString);

        authorization = new Authorization();
        authorization.setAuthorizationID("8675309");
        authorization.setRecordCreateTimestamp(now);
        authorization.setRecordUpdateTimestamp(now);
        authorization.setRecordEffectiveTimestamp(now);
        authorization.setRecordTerminationTimestamp(terminatedDate);
        authorization.setRecordCreatedBy("JASON");
        authorization.setChangeReasonMemo("New Record");
        authorization.setCurrentRecordIndicator(true);
        authorization.setChangeVersionID(BigInteger.ONE);
        authorization.setBusinessEntityID("5");
        authorization.setPatientID("er-608474");
        authorization.setPayerID("f2cfb031-8743-4ea0-86fa-33ccb2ea8fa1");
        authorization.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);

        authorizationService = new AuthorizationService();
        authorizationService.setRecordCreateTimestamp(now);
        authorizationService.setRecordUpdateTimestamp(now);
        authorizationService.setChangeVersionID(BigInteger.ONE);
        authorizationService.setAuthorizationSK(BigInteger.valueOf(38L));
        authorizationService.setBusinessEntityID("5");
        authorizationService.setServiceName(ServiceName.HHA);
        authorizationService.setAuthorizationServiceStartDate(now);
        authorizationService.setAuthorizationServiceEndDate(end);
        authorizationService.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);

        order = new Order();
        order.setRecordCreateTimestamp(now);
        order.setRecordUpdateTimestamp(now);
        order.setRecordEffectiveTimestamp(now);
        order.setRecordTerminationTimestamp(terminatedDate);
        order.setRecordCreatedBy("JASON");
        order.setChangeReasonMemo("New Record");
        order.setCurrentRecordIndicator(true);
        order.setChangeVersionID(BigInteger.ONE);
        order.setBusinessEntityID("5");
        order.setPatientID("er-608474");
        order.setPayerID("f2cfb031-8743-4ea0-86fa-33ccb2ea8fa1");
        order.setOrderServiceUnitName(AuthorizationServiceUnitName.HOUR);

        orderService = new OrderService();
        orderService.setRecordCreateTimestamp(now);
        orderService.setRecordUpdateTimestamp(now);
        orderService.setChangeVersionID(BigInteger.ONE);
        orderService.setOrderSK(BigInteger.valueOf(38L));
        orderService.setBusinessEntityID("5");
        orderService.setServiceName(ServiceName.HHA);
        orderService.setOrderServiceStartDate(now);
        orderService.setOrderServiceEndDate(end);
        orderService.setOrderServiceUnitName(AuthorizationServiceUnitName.HOUR);
    }
}
