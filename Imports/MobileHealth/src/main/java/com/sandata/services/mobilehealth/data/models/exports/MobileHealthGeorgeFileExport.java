package com.sandata.services.mobilehealth.data.models.exports;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sandata.up.commons.BaseObject;

@FixedLengthRecord(length = 167)
public class MobileHealthGeorgeFileExport extends BaseObject implements Comparable<MobileHealthGeorgeFileExport> {
    private static final long serialVersionUID = 111010111111L;

    @DataField(pos = 1, length = 3)
    private String vendorNumber;

    @DataField(pos = 4, length = 6)
    private String attendantNumber;

    @DataField(pos = 10, length = 15)
    private String attendantLastName;

    @DataField(pos = 25, length = 10)
    private String attendantFirstName;

    @DataField(pos = 35, length = 25)
    private String attendantAddress1;

    @DataField(pos = 60, length = 25)
    private String attendantAddress2;

    @DataField(pos = 85, length = 17)
    private String attendantCity;

    @DataField(pos = 102, length = 2)
    private String attendantState;

    @DataField(pos = 104, length = 9)
    private String attendantPostalCode;

    @DataField(pos = 113, length = 10)
    private String attendantPhone;

    @DataField(pos = 123, length = 9)
    private String attendantSS;

    @DataField(pos = 132, length = 1)
    private String attendantSex;

    @DataField(pos = 133, length = 6)
    private String physicalDate;

    @DataField(pos = 139, length = 6)
    private String tetanusDate;

    @DataField(pos = 145, length = 6)
    private String ppdDate;

    @DataField(pos = 151, length = 1)
    private String ppdCode;

    @DataField(pos = 152, length = 1)
    private String workingStatus;

    @DataField(pos = 153, length = 1)
    private String termStatus;

    @DataField(pos = 154, length = 6)
    private String ppd2Date;

    @DataField(pos = 160, length = 1)
    private String ppd2Code;

    @DataField(pos = 161, length = 6)
    private String xrayDate;

    @DataField(pos = 167, length = 1)
    private String ppdChestXrayCode;
    
    @DataField(pos = 168, length = 6)
    private String employeeDateOfBirth;
    
    @DataField(pos = 174, length = 17)
    private String employeeLastModified;

    /**
     * @return the vendorNumber
     */
    public String getVendorNumber() {
        return vendorNumber;
    }

    /**
     * @param vendorNumber the vendorNumber to set
     */
    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    /**
     * @return the attendantNumber
     */
    public String getAttendantNumber() {
        return attendantNumber;
    }

    /**
     * @param attendantNumber the attendantNumber to set
     */
    public void setAttendantNumber(String attendantNumber) {
        this.attendantNumber = attendantNumber;
    }

    /**
     * @return the attendantLastName
     */
    public String getAttendantLastName() {
        return attendantLastName;
    }

    /**
     * @param attendantLastName the attendantLastName to set
     */
    public void setAttendantLastName(String attendantLastName) {
        this.attendantLastName = attendantLastName;
    }

    /**
     * @return the attenadantFirstName
     */
    public String getAttendantFirstName() {
        return attendantFirstName;
    }

    /**
     * @param attenadantFirstName the attenadantFirstName to set
     */
    public void setAttendantFirstName(String attendantFirstName) {
        this.attendantFirstName = attendantFirstName;
    }

    /**
     * @return the attendantAddress1
     */
    public String getAttendantAddress1() {
        return attendantAddress1;
    }

    /**
     * @param attendantAddress1 the attendantAddress1 to set
     */
    public void setAttendantAddress1(String attendantAddress1) {
        this.attendantAddress1 = attendantAddress1;
    }

    /**
     * @return the attendantAddress2
     */
    public String getAttendantAddress2() {
        return attendantAddress2;
    }

    /**
     * @param attendantAddress2 the attendantAddress2 to set
     */
    public void setAttendantAddress2(String attendantAddress2) {
        this.attendantAddress2 = attendantAddress2;
    }

    /**
     * @return the attendantCity
     */
    public String getAttendantCity() {
        return attendantCity;
    }

    /**
     * @param attendantCity the attendantCity to set
     */
    public void setAttendantCity(String attendantCity) {
        this.attendantCity = attendantCity;
    }

    /**
     * @return the attendantState
     */
    public String getAttendantState() {
        return attendantState;
    }

    /**
     * @param attendantState the attendantState to set
     */
    public void setAttendantState(String attendantState) {
        this.attendantState = attendantState;
    }

    /**
     * @return the attendantPostalCode
     */
    public String getAttendantPostalCode() {
        return attendantPostalCode;
    }

    /**
     * @param attendantPostalCode the attendantPostalCode to set
     */
    public void setAttendantPostalCode(String attendantPostalCode) {
        this.attendantPostalCode = attendantPostalCode;
    }

    /**
     * @return the attendantPhone
     */
    public String getAttendantPhone() {
        return attendantPhone;
    }

    /**
     * @param attendantPhone the attendantPhone to set
     */
    public void setAttendantPhone(String attendantPhone) {
        this.attendantPhone = attendantPhone;
    }

    /**
     * @return the attendantSS
     */
    public String getAttendantSS() {
        return attendantSS;
    }

    /**
     * @param attendantSS the attendantSS to set
     */
    public void setAttendantSS(String attendantSS) {
        this.attendantSS = attendantSS;
    }

    /**
     * @return the attendantSex
     */
    public String getAttendantSex() {
        return attendantSex;
    }

    /**
     * @param attendantSex the attendantSex to set
     */
    public void setAttendantSex(String attendantSex) {
        this.attendantSex = attendantSex;
    }

    /**
     * @return the physicalDate
     */
    public String getPhysicalDate() {
        return physicalDate;
    }

    /**
     * @param physicalDate the physicalDate to set
     */
    public void setPhysicalDate(String physicalDate) {
        this.physicalDate = physicalDate;
    }

    /**
     * @return the tetanusDate
     */
    public String getTetanusDate() {
        return tetanusDate;
    }

    /**
     * @param tetanusDate the tetanusDate to set
     */
    public void setTetanusDate(String tetanusDate) {
        this.tetanusDate = tetanusDate;
    }

    /**
     * @return the ppdDate
     */
    public String getPpdDate() {
        return ppdDate;
    }

    /**
     * @param ppdDate the ppdDate to set
     */
    public void setPpdDate(String ppdDate) {
        this.ppdDate = ppdDate;
    }

    /**
     * @return the ppdCode
     */
    public String getPpdCode() {
        return ppdCode;
    }

    /**
     * @param ppdCode the ppdCode to set
     */
    public void setPpdCode(String ppdCode) {
        this.ppdCode = ppdCode;
    }

    /**
     * @return the workingStatus
     */
    public String getWorkingStatus() {
        return workingStatus;
    }

    /**
     * @param workingStatus the workingStatus to set
     */
    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    /**
     * @return the termStatus
     */
    public String getTermStatus() {
        return termStatus;
    }

    /**
     * @param termStatus the termStatus to set
     */
    public void setTermStatus(String termStatus) {
        this.termStatus = termStatus;
    }

    /**
     * @return the ppd2Date
     */
    public String getPpd2Date() {
        return ppd2Date;
    }

    /**
     * @param ppd2Date the ppd2Date to set
     */
    public void setPpd2Date(String ppd2Date) {
        this.ppd2Date = ppd2Date;
    }

    /**
     * @return the ppd2Code
     */
    public String getPpd2Code() {
        return ppd2Code;
    }

    /**
     * @param ppd2Code the ppd2Code to set
     */
    public void setPpd2Code(String ppd2Code) {
        this.ppd2Code = ppd2Code;
    }

    /**
     * @return the xrayDate
     */
    public String getXrayDate() {
        return xrayDate;
    }

    /**
     * @param xrayDate the xrayDate to set
     */
    public void setXrayDate(String xrayDate) {
        this.xrayDate = xrayDate;
    }

    /**
     * @return the ppdChestXrayCode
     */
    public String getPpdChestXrayCode() {
        return ppdChestXrayCode;
    }

    /**
     * @param ppdChestXrayCode the ppdChestXrayCode to set
     */
    public void setPpdChestXrayCode(String ppdChestXrayCode) {
        this.ppdChestXrayCode = ppdChestXrayCode;
    }

    /**
     * @return the employeeLastModified
     */
    public String getEmployeeLastModified() {
        return employeeLastModified;
    }

    /**
     * @param employeeLastModified the employeeLastModified to set
     */
    public void setEmployeeLastModified(String employeeLastModified) {
        this.employeeLastModified = employeeLastModified;
    }
    
    /**
     * @return the employeeDateOfBirth
     */
    public String getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    /**
     * @param employeeDateOfBirth the employeeDateOfBirth to set
     */
    public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    @Override
    public int compareTo(MobileHealthGeorgeFileExport model) {
        return this.vendorNumber.compareTo(model.getVendorNumber());
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
