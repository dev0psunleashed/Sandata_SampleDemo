/**
 * 
 */
package com.sandata.services.mobilehealth.data.models;

import java.util.Date;

/**
 * @author huyvo
 *
 */
public class MobileHealthConfiguration {
    private String description;
    // would be export/import date
    private Date lastUpdatedDate;
    private String additionalParameters;
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the lastUpdatedDate
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    /**
     * @param lastUpdatedDate the lastUpdatedDate to set
     */
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    /**
     * @return the additionalParameters
     */
    public String getAdditionalParameters() {
        return additionalParameters;
    }
    /**
     * @param additionalParameters the additionalParameters to set
     */
    public void setAdditionalParameters(String additionalParameters) {
        this.additionalParameters = additionalParameters;
    }
}
