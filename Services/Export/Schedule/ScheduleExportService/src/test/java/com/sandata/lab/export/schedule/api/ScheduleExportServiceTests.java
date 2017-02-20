package com.sandata.lab.export.schedule.api;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.export.schedule.BaseTestSupport;
import com.sandata.lab.export.schedule.impl.RestScheduleExportService;
import com.sandata.lab.export.schedule.utils.constants.App;
import com.sandata.lab.wcf.export.api.ExportService;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Test the schedule export api.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class ScheduleExportServiceTests extends BaseTestSupport {

    private DateFormat dateFormat;

    private RestScheduleExportService service;

    @Mocked
    private CacheService cacheService;

    @Mocked
    ExportService exportService;

    private String uuid;

    @Test
    public void should_throw_exception_when_enddate_is_before_startdate() throws Exception {

        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);
            params.add(105);
            params.add("08-04-2015");
            params.add("08-03-2015");

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: EndDate [08-03-2015] " +
                    "can not be before StartDate [08-04-2015]!"));
        }
    }

    @Test
    public void should_throw_exception_when_enddate_is_malformed() throws Exception {

        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);
            params.add(105);
            params.add("08-04-2015");
            params.add("20150805");

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): java.text.ParseException: EXCEPTION: " +
                    "Unparseable date: \"20150805\""));
        }
    }

    @Test
    public void should_throw_exception_when_enddate_is_null() throws Exception {

        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);
            params.add(105);
            params.add("08-04-2015");
            params.add(null);

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: EndDate is null!"));
        }
    }

    @Test
    public void should_throw_exception_when_startdate_is_null() throws Exception {

        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);
            params.add(105);
            params.add(null);
            params.add(null);

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: StartDate is null!"));
        }
    }

    @Test
    public void should_throw_exception_when_patientid_is_null_or_not_valid() throws Exception {

        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);
            params.add(-233);
            params.add(null);
            params.add(null);

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: PatientId [-233]: " +
                    "Please provide a valid PatientId!"));
        }
    }

    @Test
    public void should_throw_exception_when_staffid_is_null_or_not_valid() throws Exception {
    
        try {
            MessageContentsList params = new MessageContentsList();
            params.add(null);
            params.add(0);
            params.add(null);
            params.add(null);

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);
            
            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: StaffId [null]: " +
                    "Please provide a valid StaffId!"));
        }
    }
    
    @Test
    public void should_throw_exception_when_params_are_not_set_correctly() throws Exception {
    
        try {
            MessageContentsList params = new MessageContentsList();
            params.add(92);

            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);
            
            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: Unexpected number of params: " +
                    "Params: [1]: Expected: 4"));
        }
    }
    
    @Test
    public void should_throw_exception_when_params_is_null() throws Exception {
    
        try {
            exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
            exchange.getIn().setBody(null);
            service.exportSchedule(exchange);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(NullPointerException.class, e);
            Assert.isTrue(e.getMessage() == null);
        }
    }
    
    @Test
    public void should_throw_exception_when_uuid_is_null_or_empty(@Mocked MessageContentsList params) throws Exception {
    
        try {

            exchange.getIn().setBody(params);
            service.exportSchedule(exchange);
            
            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule(Exchange): ERROR: UUID is null or empty!"));
        }
    }
    
    @Test
    public void should_verify_cache_and_export_services_are_called_with_given_params() throws Exception {

        final int staffId = 92;
        final int patientId = 105;
        final Date startDate = dateFormat.parse("2015-08-05");
        final Date endDate = dateFormat.parse("2015-08-06");

        final MessageContentsList params = new MessageContentsList();
        params.add(staffId);
        params.add(patientId);
        params.add(dateFormat.format(startDate));
        params.add(dateFormat.format(endDate));

        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);
        exchange.getIn().setBody(params);
        service.exportSchedule(exchange);

        new Verifications() {{

            cacheService.processing(params, uuid);
            cacheService.put(withAny(Response.class), uuid);
            exportService.exportSchedule(staffId, patientId, startDate, endDate);
        }};
    }

    @Override
    protected void onSetup() throws IOException {

        dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        service = new RestScheduleExportService();
        service.setCacheService(cacheService);
        service.setExportService(exportService);

        uuid = UUID.randomUUID().toString();
    }
}
