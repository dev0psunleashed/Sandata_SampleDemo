package com.sandata.lab.wcf.intake.service;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.common.utils.log.DefaultLogger;
import com.sandata.lab.data.model.error.Error;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.intake.BaseTestSupport;
import com.sandata.lab.wcf.intake.impl.WcfIntakeService;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Tests the WCF Intake service class.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class WcfIntakeServiceTests extends BaseTestSupport {

    private WcfIntakeService wcfIntakeService;

    @Mocked
    private HttpDataService httpDataService;

    private GSONProvider gsonProvider;

    @Test
    public void should_verify_that_http_post_is_called_correctly() throws Exception {

        Response response = wcfIntakeService.insertSchedules(schedules);

        validateResponse(response);

        new Verifications() {{

            httpDataService.post(wcfIntakeService.getUrl() + "/ImportSchedules", anyString, "application/json");
            times = 1;
        }};
    }

    @Test
    public void should_verify_that_authorization_hdr_is_set() throws Exception {

        Response response = wcfIntakeService.insertSchedules(schedules);

        validateResponse(response);

        new Verifications() {{

            httpDataService.addHeader("Authorization", anyString);
            times = 1;
        }};
    }

    @Test
    public void should_verify_that_logger_is_set() throws Exception {

        Response response = wcfIntakeService.insertSchedules(schedules);

        validateResponse(response);

        new Verifications() {{

            httpDataService.setLogger(withAny(DefaultLogger.CreateLogger()));
            times = 1;
        }};
    }

    private void validateResponse(final Response response) {
        Assert.notNull(response);
        Assert.isTrue(response.getStatus() == ServiceStatus.FAILED);
        Assert.isTrue(response.getSucceededCount() == 0);
        Assert.isTrue(response.getFailedCount() == 1);
        Assert.notNull(response.getData());

        Assert.isTrue(response.getData() instanceof List);

        List errors = (List) response.getData();
        Assert.isTrue(errors.size() == 1);

        Object errObj = errors.get(0);

        Assert.isTrue(errObj instanceof com.sandata.lab.data.model.error.Error);

        Error error = (Error) errObj;

        Assert.notNull(error.getUniqueID());
        Assert.notNull(error.getMessage());
    }

    @Override
    protected void onSetup() throws Exception {

        super.onSetup();

        wcfIntakeService = new WcfIntakeService();
        wcfIntakeService.setHttpDataService(httpDataService);
        gsonProvider = new GSONProvider("MM-dd-yyyy");
        wcfIntakeService.setGsonProvider(gsonProvider);
    }
}
