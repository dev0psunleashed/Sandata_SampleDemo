package com.sandata.lab.eligibility.model.configuration;

import com.sandata.lab.eligibility.utils.constants.App;

/**
 * All configurations related to Eligibility Process per agency. It is
 * converted to JSON and stored in the APP_TENANT_KEY_CONF table with KEY_NAME =
 * value of {@link App#ELIGIBILITY_PROCESS_CONF_KEY_NAME}
 */
public class AgencyEligibilityConfiguration {
    private String sendInquiryCronSchedule;

    public String getSendInquiryCronSchedule() {
        return sendInquiryCronSchedule;
    }

    public void setSendInquiryCronSchedule(String sendInquiryCronSchedule) {
        this.sendInquiryCronSchedule = sendInquiryCronSchedule;
    }
}
