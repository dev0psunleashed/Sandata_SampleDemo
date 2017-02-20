package com.sandata.rules.compliance.domain;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Represents fact for a Staff compliance result.
 *
 * @author jasonscott
 */
public class StaffComplianceFact extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String staffId;
    private String businessEntityId;
    private String complianceCode;
    private BusinessEntityComplianceType businessEntityComplianceType;
    private String complianceResultReadingValue;
    private Date receivedDate;
    private Date expirationDate;
    private boolean continueEvaluation;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getComplianceCode() {
        return complianceCode;
    }

    public void setComplianceCode(String complianceCode) {
        this.complianceCode = complianceCode;
    }

    public BusinessEntityComplianceType getBusinessEntityComplianceType() {
        return businessEntityComplianceType;
    }

    public void setBusinessEntityComplianceType(BusinessEntityComplianceType businessEntityComplianceType) {
        this.businessEntityComplianceType = businessEntityComplianceType;
    }

    public String getComplianceResultReadingValue() {
        return complianceResultReadingValue;
    }

    public void setComplianceResultReadingValue(String complianceResultReadingValue) {
        this.complianceResultReadingValue = complianceResultReadingValue;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isContinueEvaluation() {
        return continueEvaluation;
    }

    public void setContinueEvaluation(boolean continueEvaluation) {
        this.continueEvaluation = continueEvaluation;
    }
}
