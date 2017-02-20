package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.*;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Date: 10/8/15
 * Time: 11:21 AM
 */

public class FindClassResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffTrainingLocationName")
    protected String staffTrainingLocationName;

    @SerializedName("StaffTrainingCode")
    protected String staffTrainingCode;

    @SerializedName("StaffTrainingStartDateTime")
    protected Date staffTrainingStartDateTime;

    @SerializedName("StaffTrainingEndDateTime")
    protected Date staffTrainingEndDateTime;

    @SerializedName("StaffTrainingName")
    protected String staffTrainingName;

    @SerializedName("StaffTrainingDescription")
    protected String staffTrainingDescription;

    @SerializedName("StaffTrainingCompletedIndicator")
    private boolean staffTrainingCompletedIndicator;

    @SerializedName("StaffTrainingNoShowIndicator")
    private boolean staffTrainingNoShowIndicator;

    @SerializedName("StaffTrainingDropIndicator")
    protected Boolean staffTrainingDropIndicator;

    @SerializedName("ClassStatus")
    protected String classStatus;

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }


    public Boolean getStaffTrainingDropIndicator() {
        return staffTrainingDropIndicator;
    }

    public void setStaffTrainingDropIndicator(Boolean staffTrainingDropIndicator) {
        this.staffTrainingDropIndicator = staffTrainingDropIndicator;
    }

    public String getStaffTrainingLocationName() {
        return staffTrainingLocationName;
    }

    public void setStaffTrainingLocationName(String staffTrainingLocationName) {
        this.staffTrainingLocationName = staffTrainingLocationName;
    }

    public String getStaffTrainingCode() {
        return staffTrainingCode;
    }

    public void setStaffTrainingCode(String staffTrainingCode) {
        this.staffTrainingCode = staffTrainingCode;
    }

    public Date getStaffTrainingStartDateTime() {
        return staffTrainingStartDateTime;
    }

    public void setStaffTrainingStartDateTime(Date staffTrainingStartDateTime) {
        this.staffTrainingStartDateTime = staffTrainingStartDateTime;
    }

    public Date getStaffTrainingEndDateTime() {
        return staffTrainingEndDateTime;
    }

    public void setStaffTrainingEndDateTime(Date staffTrainingEndDateTime) {
        this.staffTrainingEndDateTime = staffTrainingEndDateTime;
    }

    public String getStaffTrainingName() {
        return staffTrainingName;
    }

    public void setStaffTrainingName(String staffTrainingName) {
        this.staffTrainingName = staffTrainingName;
    }

    public String getStaffTrainingDescription() {
        return staffTrainingDescription;
    }

    public void setStaffTrainingDescription(String staffTrainingDescription) {
        this.staffTrainingDescription = staffTrainingDescription;
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




}
