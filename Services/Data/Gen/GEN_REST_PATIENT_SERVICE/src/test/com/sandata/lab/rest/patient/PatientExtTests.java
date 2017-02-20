package com.sandata.lab.rest.patient;

import com.sandata.lab.data.model.dl.model.DoNotResuscitateLookup;
import com.sandata.lab.data.model.dl.model.DoNotResuscitateName;
import org.junit.Test;
import org.springframework.util.Assert;
import com.sandata.lab.common.utils.data.provider.GSONProvider;

public class PatientExtTests extends BaseTestSupport {

    @Test
    public void should_convert_json_dnr_lookup_string_to_an_entity() throws Exception {

        String json = " { " +
                "      \"DoNotResuscitateLookupSK\": 4, " +
                "      \"RecordCreateTimestamp\": \"2016-06-27 17:52:06\", " +
                "      \"RecordUpdateTimestamp\": \"2016-06-27 17:52:06\", " +
                "      \"RecordEffectiveTimestamp\": \"1900-01-01 00:00:00\", " +
                "      \"RecordTerminationTimestamp\": \"9999-12-31 00:00:00\", " +
                "      \"RecordCreatedBy\": \"SANDATA\", " +
                "      \"RecordUpdatedBy\": null, " +
                "      \"ChangeReasonMemo\": null, " +
                "      \"CurrentRecordIndicator\": true, " +
                "      \"ChangeVersionID\": 4, " +
                "      \"DoNotResuscitateName\": \"Advanced Directive\", " +
                "      \"DoNotResuscitateDescription\": null " +
                "    }";

        Object data = GSONProvider.FromJson(json, DoNotResuscitateLookup.class);
        Assert.notNull(data);
        Assert.isTrue(data instanceof DoNotResuscitateLookup);

        DoNotResuscitateLookup dnr = (DoNotResuscitateLookup)data;
        Assert.notNull(dnr.getDoNotResuscitateName());
        Assert.isTrue(dnr.getDoNotResuscitateName() == DoNotResuscitateName.ADVANCED_DIRECTIVE);
    }
    
    @Test
    public void should_convert_patient_dnr_option_from_string() throws Exception {

        DoNotResuscitateName option = DoNotResuscitateName.fromValue("Unknown/Not Applicable");
        Assert.notNull(option);
        Assert.isTrue(option == DoNotResuscitateName.UNKNOWN_NOT_APPLICABLE);
    }

    @Test
    public void should_convert_patient_dnr_option_to_string() throws Exception {

        String option = DoNotResuscitateName.UNKNOWN_NOT_APPLICABLE.value();
        Assert.notNull(option);
        Assert.isTrue(option.equals("Unknown/Not Applicable"));
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
