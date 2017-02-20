package com.sandata.rules.compliance.domain;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.StaffItemRequiredFromQualifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents fact for a Business Entity Compliance item.
 *
 * @author jasonscott
 */
public class BusinessEntityComplianceFact extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String businessEntityId;
    private String serviceName;
    private BusinessEntityComplianceType businessEntityComplianceType;
    private String businessEntityComplianceCode;
    private Date effectiveDate;
    private Date terminationDate;
    private Date complianceRequiredByDate;
    private StaffItemRequiredFromQualifier staffItemRequiredFromQualifier;

    public BusinessEntityComplianceFact() {
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BusinessEntityComplianceType getBusinessEntityComplianceType() {
        return businessEntityComplianceType;
    }

    public void setBusinessEntityComplianceType(BusinessEntityComplianceType businessEntityComplianceType) {
        this.businessEntityComplianceType = businessEntityComplianceType;
    }

    public String getBusinessEntityComplianceCode() {
        return businessEntityComplianceCode;
    }

    public void setBusinessEntityComplianceCode(String businessEntityComplianceCode) {
        this.businessEntityComplianceCode = businessEntityComplianceCode;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Date getComplianceRequiredByDate() {
        return complianceRequiredByDate;
    }

    public void setComplianceRequiredByDate(Date complianceRequiredByDate) {
        this.complianceRequiredByDate = complianceRequiredByDate;
    }

    public StaffItemRequiredFromQualifier getStaffItemRequiredFromQualifier() {
        return staffItemRequiredFromQualifier;
    }

    public void setStaffItemRequiredFromQualifier(StaffItemRequiredFromQualifier staffItemRequiredFromQualifier) {
        this.staffItemRequiredFromQualifier = staffItemRequiredFromQualifier;
    }
}
