package com.sandata.rules.compliance.domain;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.SchedulePermissionQualifier;

/**
 * Represents fact for a Business Entity Compliance item result.
 *
 * @author jasonscott
 */
public class BusinessEntityComplianceResultFact extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String businessEntityId;
    private String businessEntityComplianceCode;
    private String complianceResultReadingValue;
    private SchedulePermissionQualifier schedulePermissionQualifier;
    private boolean compliant;

    public BusinessEntityComplianceResultFact() {
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getBusinessEntityComplianceCode() {
        return businessEntityComplianceCode;
    }

    public void setBusinessEntityComplianceCode(String businessEntityComplianceCode) {
        this.businessEntityComplianceCode = businessEntityComplianceCode;
    }

    public String getComplianceResultReadingValue() {
        return complianceResultReadingValue;
    }

    public void setComplianceResultReadingValue(String complianceResultReadingValue) {
        this.complianceResultReadingValue = complianceResultReadingValue;
    }

    public SchedulePermissionQualifier getSchedulePermissionQualifier() {
        return schedulePermissionQualifier;
    }

    public void setSchedulePermissionQualifier(SchedulePermissionQualifier schedulePermissionQualifier) {
        this.schedulePermissionQualifier = schedulePermissionQualifier;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }
}
