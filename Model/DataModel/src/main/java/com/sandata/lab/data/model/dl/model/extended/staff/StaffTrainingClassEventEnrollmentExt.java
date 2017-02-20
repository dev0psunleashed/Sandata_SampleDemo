package com.sandata.lab.data.model.dl.model.extended.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.StaffTrainingClassEventEnrollment;

import java.util.Date;

/**
 * <p>StaffTrainingClassEventEnrollmentExt</p>
 * <p>Date: 07/10/2016</p>
 * <p>Time: 8:14 PM</p></p>
 *
 * @author jasonscott
 */
public class StaffTrainingClassEventEnrollmentExt extends StaffTrainingClassEventEnrollment {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffMiddleName")
    private String staffMiddleName;

    @SerializedName("StaffTrainingEnrollmentDate")
    private Date staffTrainingEnrollmentDate;

    @SerializedName("StaffTrainingCompletedIndicator")
    private boolean staffTrainingCompletedIndicator;

    @SerializedName("StaffTrainingNoShowIndicator")
    private boolean staffTrainingNoShowIndicator;

    @SerializedName("StaffScheduleEventConflictExists")
    private boolean staffScheduleEventConflictExists;

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffMiddleName() {
        return staffMiddleName;
    }

    public void setStaffMiddleName(String staffMiddleName) {
        this.staffMiddleName = staffMiddleName;
    }

    public Date getStaffTrainingEnrollmentDate() {
        return staffTrainingEnrollmentDate;
    }

    public void setStaffTrainingEnrollmentDate(Date staffTrainingEnrollmentDate) {
        this.staffTrainingEnrollmentDate = staffTrainingEnrollmentDate;
    }

    public boolean isStaffTrainingCompletedIndicator() {
        return staffTrainingCompletedIndicator;
    }

    public void setStaffTrainingCompletedIndicator(boolean staffTrainingCompletedIndicator) {
        this.staffTrainingCompletedIndicator = staffTrainingCompletedIndicator;
    }

    public boolean isStaffTrainingNoShowIndicator() {
        return staffTrainingNoShowIndicator;
    }

    public void setStaffTrainingNoShowIndicator(boolean staffTrainingNoShowIndicator) {
        this.staffTrainingNoShowIndicator = staffTrainingNoShowIndicator;
    }

    public boolean isStaffScheduleEventConflictExists() {
        return staffScheduleEventConflictExists;
    }

    public void setStaffScheduleEventConflictExists(boolean staffScheduleEventConflictExists) {
        this.staffScheduleEventConflictExists = staffScheduleEventConflictExists;
    }
}
