package com.sandata.rules.compliance.domain;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents fact for a Staff.
 *
 * @author jasonscott
 */
public class StaffFact extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String staffId;
    private String businessEntityId;
    private String serviceName;
    private Date staffHireDate;
    private Date staffVerifiedStartDate;
    private List<StaffComplianceResult> staffComplianceResultList;
    private boolean compliant;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getStaffHireDate() {
        return staffHireDate;
    }

    public void setStaffHireDate(Date staffHireDate) {
        this.staffHireDate = staffHireDate;
    }

    public Date getStaffVerifiedStartDate() {
        return staffVerifiedStartDate;
    }

    public void setStaffVerifiedStartDate(Date staffVerifiedStartDate) {
        this.staffVerifiedStartDate = staffVerifiedStartDate;
    }

    public List<StaffComplianceResult> getStaffComplianceResultList() {
        return staffComplianceResultList;
    }

    public void setStaffComplianceResultList(List<StaffComplianceResult> staffComplianceResultList) {
        this.staffComplianceResultList = staffComplianceResultList;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }

    public void addStaffComplianceResult(StaffComplianceResult staffComplianceResult) {
        if (staffComplianceResultList == null) {
            staffComplianceResultList = new ArrayList<StaffComplianceResult>();
        }

        staffComplianceResultList.add(staffComplianceResult);
    }
}
