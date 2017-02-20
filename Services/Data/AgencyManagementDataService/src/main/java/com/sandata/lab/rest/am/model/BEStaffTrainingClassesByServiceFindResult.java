package com.sandata.lab.rest.am.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * <p>Models the response of the getBEStaffTrngClassesByCategory endpoint.
 *
 * @author Khang Le
 */
public class BEStaffTrainingClassesByServiceFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityStaffTrainingLookupSK")
    private BigInteger businessEntityStaffTrainingLookupSK;

    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    @SerializedName("StaffTrainingCode")
    private String staffTrainingCode;

    @SerializedName("StaffTrainingName")
    private String staffTrainingName;

    @SerializedName("StaffTrainingDescription")
    private String staffTrainingDescription;

    @SerializedName("StaffTrainingQualifier")
    private String staffTrainingQualifier;

    @SerializedName("StaffTrainingMandatoryIndicator")
    private Boolean staffTrainingMandatoryIndicator;

    @SerializedName("StaffTrainingTotalHours")
    private BigDecimal staffTrainingTotalHours;

    @SerializedName("StaffTrainingNotes")
    private String staffTrainingNotes;

    @SerializedName("ServiceNameList")
    private List<String> serviceNameList;

    @SerializedName("StaffTrainingCategoryCode")
    private String staffTrainingCategoryCode;

    @SerializedName("StaffTrainingCategoryName")
    private String staffTrainingCategoryName;

    public BigInteger getBusinessEntityStaffTrainingLookupSK() {
        return businessEntityStaffTrainingLookupSK;
    }

    public void setBusinessEntityStaffTrainingLookupSK(BigInteger businessEntityStaffTrainingLookupSK) {
        this.businessEntityStaffTrainingLookupSK = businessEntityStaffTrainingLookupSK;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getStaffTrainingCode() {
        return staffTrainingCode;
    }

    public void setStaffTrainingCode(String staffTrainingCode) {
        this.staffTrainingCode = staffTrainingCode;
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

    public String getStaffTrainingQualifier() {
        return staffTrainingQualifier;
    }

    public void setStaffTrainingQualifier(String staffTrainingQualifier) {
        this.staffTrainingQualifier = staffTrainingQualifier;
    }

    public Boolean getStaffTrainingMandatoryIndicator() {
        return staffTrainingMandatoryIndicator;
    }

    public void setStaffTrainingMandatoryIndicator(Boolean staffTrainingMandatoryIndicator) {
        this.staffTrainingMandatoryIndicator = staffTrainingMandatoryIndicator;
    }

    public BigDecimal getStaffTrainingTotalHours() {
        return staffTrainingTotalHours;
    }

    public void setStaffTrainingTotalHours(BigDecimal staffTrainingTotalHours) {
        this.staffTrainingTotalHours = staffTrainingTotalHours;
    }

    public String getStaffTrainingNotes() {
        return staffTrainingNotes;
    }

    public void setStaffTrainingNotes(String staffTrainingNotes) {
        this.staffTrainingNotes = staffTrainingNotes;
    }

    public List<String> getServiceNameList() {
        return serviceNameList;
    }

    public void setServiceNameList(List<String> serviceNameList) {
        this.serviceNameList = serviceNameList;
    }

    public String getStaffTrainingCategoryCode() {
        return staffTrainingCategoryCode;
    }

    public void setStaffTrainingCategoryCode(String staffTrainingCategoryCode) {
        this.staffTrainingCategoryCode = staffTrainingCategoryCode;
    }

    public String getStaffTrainingCategoryName() {
        return staffTrainingCategoryName;
    }

    public void setStaffTrainingCategoryName(String staffTrainingCategoryName) {
        this.staffTrainingCategoryName = staffTrainingCategoryName;
    }
}
