package com.sandata.lab.data.model.dl.model.extended.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.StaffContactPhone;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Extends the Staff entity to provide the UI with some more properties.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffExt extends Staff {

    private static final long serialVersionUID = 1L;

    public StaffExt() {
    }

    public StaffExt(Staff staff) {
        BeanUtils.copyProperties(staff, this);
    }
    
    @SerializedName("LastCallDate")
    protected Date lastCallDate;

    @SerializedName("OriginalStaffHireDate")
    protected Date originalStaffHireDate;

    @SerializedName("LastDateWorked")
    private Date lastDateWorked;

    @SerializedName("VerifiedStartDate")
    private Date verifiedStartDate;

    @SerializedName("CoordinatorID")
    private String coordinatorId;

    @SerializedName("NurseID")
    private String nurseId;

    @SerializedName("CompletedInServiceTrainingHours")
    private BigDecimal completedInServiceTrainingHours;

    @SerializedName("RequiredInServiceTrainingHours")
    private BigDecimal requiredInServiceTrainingHours;

    @SerializedName("StaffContactPhone")
    private List<StaffContactPhone> staffContactPhone;

    @SerializedName("CompliantIndicator")
    private Boolean compliantIndicator;

    public Date getLastDateWorked() {
        return lastDateWorked;
    }

    public void setLastDateWorked(Date lastDateWorked) {
        this.lastDateWorked = lastDateWorked;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public BigDecimal getCompletedInServiceTrainingHours() {
        return completedInServiceTrainingHours;
    }

    public void setCompletedInServiceTrainingHours(BigDecimal completedInServiceTrainingHours) {
        this.completedInServiceTrainingHours = completedInServiceTrainingHours;
    }

    public BigDecimal getRequiredInServiceTrainingHours() {
        return requiredInServiceTrainingHours;
    }

    public void setRequiredInServiceTrainingHours(BigDecimal requiredInServiceTrainingHours) {
        this.requiredInServiceTrainingHours = requiredInServiceTrainingHours;
    }

    public List<StaffContactPhone> getStaffContactPhone() {
        if (staffContactPhone == null) {
            staffContactPhone = new ArrayList<StaffContactPhone>();
        }
        return this.staffContactPhone;
    }

    public Staff getStaff() {
        Staff staff = new Staff();
        BeanUtils.copyProperties(this, staff);
        return staff;
    }

    public Date getOriginalStaffHireDate() {
        return originalStaffHireDate;
    }

    public void setOriginalStaffHireDate(Date originalStaffHireDate) {
        this.originalStaffHireDate = originalStaffHireDate;
    }

    public Date getVerifiedStartDate() {
        return verifiedStartDate;
    }

    public void setVerifiedStartDate(Date verifiedStartDate) {
        this.verifiedStartDate = verifiedStartDate;
    }

    public Boolean getCompliantIndicator() {
        return compliantIndicator;
    }

    public void setCompliantIndicator(Boolean compliantIndicator) {
        this.compliantIndicator = compliantIndicator;
    }

    public Date getLastCallDate() {
        return lastCallDate;
    }

    public void setLastCallDate(Date lastCallDate) {
        this.lastCallDate = lastCallDate;
    }
    
}
