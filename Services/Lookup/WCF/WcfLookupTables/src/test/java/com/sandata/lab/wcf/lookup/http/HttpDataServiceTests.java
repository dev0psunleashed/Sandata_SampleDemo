package com.sandata.lab.wcf.lookup.http;

import com.sandata.lab.common.utils.http.HttpDataService;
import com.sandata.lab.wcf.lookup.BaseTestSupport;
import com.sandata.lab.wcf.lookup.utils.log.HttpDataServiceLogger;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Implements HTTP requests using HttpURLConnection class.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class HttpDataServiceTests extends BaseTestSupport {

    private HttpDataService service;
    private final String url = "http://dev-lab-napp01.sandata.com:5005";
    private final String auth = "1234567890-FAKE";
    private final String contentType = "application/json";

    @Test
    public void should_return_wcf_religion_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetReligions", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_staff_types_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetStaffTypes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_ethnicities_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetEthnicities", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_marital_statuses_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetMaritalStatuses", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_genders_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetGenders", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_medication_classifications_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetMedicationClassifications", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_medication_strengths_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetMedicationStrengths", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_medication_routes_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetMedicationRoutes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_states_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetStates", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_languages_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetLanguages", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_services_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetServices", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_disaster_levels_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetDisasterLevels", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_dnrs_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetDNRs", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_reference_formats_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetReferenceFormats", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_limit_bys_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetLimitBys", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_eligibilities_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetEligibilities", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_agencies_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetAgencies", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_admission_types_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetAdmissionTypes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_pay_types_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetPayTypes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_staff_classes_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetStaffClasses", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_staff_statuses_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetStaffStatuses", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_state_taxes_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetStateTaxes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Test
    public void should_return_wcf_city_taxes_lookup_table_collection() throws Exception {

        Assert.notNull(service);

        String result = service.get(String.format("%s/GetCityTaxes", url), contentType);

        Assert.notNull(result);

        System.out.println(result);
    }

    @Override
    protected void onSetup() throws IOException {
        service = new HttpDataService(HttpDataServiceLogger.CreateLogger());
        service.addHeader("Authorization", auth);
    }
}
