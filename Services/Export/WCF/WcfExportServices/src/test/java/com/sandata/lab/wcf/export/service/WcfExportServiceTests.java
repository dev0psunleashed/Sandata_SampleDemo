package com.sandata.lab.wcf.export.service;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.wcf.export.BaseTestSupport;
import com.sandata.lab.wcf.export.impl.WcfExportService;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the WCF Export service class.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class WcfExportServiceTests extends BaseTestSupport {

    private WcfExportService wcfIntakeService;

    @Test
    public void should_throw_sandataruntimeexception_when_enddate_is_before_startdate() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

        try {
            wcfIntakeService.exportSchedule(1, 1, dateFormat.parse("08-04-2015"), dateFormat.parse("08-01-2015"));

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage()
                    .equals("exportSchedule: ERROR: EndDate [08-01-2015] can not be before StartDate [08-04-2015]!"));
        }
    }

    @Test
    public void should_throw_sandataruntimeexception_when_enddate_is_null() throws Exception {

        try {
            wcfIntakeService.exportSchedule(1, 1, new Date(), null);

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule: ERROR: EndDate is null!"));
        }
    }

    @Test
    public void should_throw_sandataruntimeexception_when_startdate_is_null() throws Exception {

        try {
            wcfIntakeService.exportSchedule(1, 1, null, new Date());

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule: ERROR: StartDate is null!"));
        }
    }

    @Test
    public void should_throw_sandataruntimeexception_when_patientid_is_zero() throws Exception {

        try {
            wcfIntakeService.exportSchedule(1, 0, new Date(), new Date());

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule: ERROR: PatientId [0]: Please provide a valid PatientId!"));
        }
    }

    @Test
    public void should_throw_sandataruntimeexception_when_staffid_is_zero() throws Exception {

        try {
            wcfIntakeService.exportSchedule(0, 2, new Date(), new Date());

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("exportSchedule: ERROR: StaffId [0]: Please provide a valid StaffId!"));
        }
    }

    @Test
    public void should_verify_that_export_returns_a_response_entity(@Mocked WcfExportService service) throws Exception {

        Response response = service.exportSchedule(1, 2, new Date(), new Date());
        Assert.notNull(response);
    }

    @Override
    protected void onSetup() throws Exception {
        wcfIntakeService = new WcfExportService();
    }
}
