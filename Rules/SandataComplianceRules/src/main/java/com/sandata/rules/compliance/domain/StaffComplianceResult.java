package com.sandata.rules.compliance.domain;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.SchedulePermissionQualifier;

import java.util.Date;

/**
 * Represents output of Staff compliance result.
 *
 * @author jasonscott
 */
public class StaffComplianceResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String complianceCode;
    private BusinessEntityComplianceType businessEntityComplianceType;
    private String message;
    private SchedulePermissionQualifier schedulePermissionQualifier;
    private StaffFact staffFact;
    private boolean compliant;
    private Date compliantExpiration;

    public StaffComplianceResult() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SchedulePermissionQualifier getSchedulePermissionQualifier() {
        return schedulePermissionQualifier;
    }

    public void setSchedulePermissionQualifier(SchedulePermissionQualifier schedulePermissionQualifier) {
        this.schedulePermissionQualifier = schedulePermissionQualifier;
    }

    public StaffFact getStaffFact() {
        return staffFact;
    }

    public void setStaffFact(StaffFact staffFact) {
        this.staffFact = staffFact;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }

    public Date getCompliantExpiration() {
        return compliantExpiration;
    }

    public void setCompliantExpiration(Date compliantExpiration) {
        this.compliantExpiration = compliantExpiration;
    }
}
