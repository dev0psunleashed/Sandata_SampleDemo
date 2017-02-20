package com.sandata.lab.rest.staff.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.EmploymentStatusTypeName;
import com.sandata.lab.data.model.dl.model.MaritalStatusName;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.TaxpayerIdentificationNumberQualifier;

public class AuditStaff extends BaseObject {

	private static final long serialVersionUID = 151516163414123413L;

	@SerializedName("StaffFirstName")
	protected String staffFirstName;
	
	@SerializedName("StaffLastName")
	protected String staffLastName;
	
	@SerializedName("StaffMiddleName")
    protected String staffMiddleName;
	
	@SerializedName("StaffDateOfBirth")
	protected Date staffDateOfBirth;
	
	@SerializedName("StaffPositionName")
    protected ServiceName staffPositionName;
	
	@SerializedName("StaffEmploymentStatusTypeName")
    protected EmploymentStatusTypeName staffEmploymentStatusTypeName;
	
	@SerializedName("StaffEmploymentStatusChangeDate")
    protected Date staffEmploymentStatusChangeDate;
	
	@SerializedName("StaffHireDate")
    protected Date staffHireDate;
	
	@SerializedName("StaffTaxpayerIdentificationNumberQualifier")
    protected TaxpayerIdentificationNumberQualifier staffTaxpayerIdentificationNumberQualifier;

    @SerializedName("StaffTaxpayerIdentificationNumber")
    protected String staffTaxpayerIdentificationNumber;
    
    @SerializedName("StaffEmail")
    protected String staffEmail;
    
    @SerializedName("StaffMaritalStatusName")
    protected MaritalStatusName staffMaritalStatusName;

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

	public Date getStaffDateOfBirth() {
		return staffDateOfBirth;
	}

	public void setStaffDateOfBirth(Date staffDateOfBirth) {
		this.staffDateOfBirth = staffDateOfBirth;
	}

	public ServiceName getStaffPositionName() {
		return staffPositionName;
	}

	public void setStaffPositionName(ServiceName staffPositionName) {
		this.staffPositionName = staffPositionName;
	}

	public EmploymentStatusTypeName getStaffEmploymentStatusTypeName() {
		return staffEmploymentStatusTypeName;
	}

	public void setStaffEmploymentStatusTypeName(
			EmploymentStatusTypeName staffEmploymentStatusTypeName) {
		this.staffEmploymentStatusTypeName = staffEmploymentStatusTypeName;
	}

	public Date getStaffEmploymentStatusChangeDate() {
		return staffEmploymentStatusChangeDate;
	}

	public void setStaffEmploymentStatusChangeDate(
			Date staffEmploymentStatusChangeDate) {
		this.staffEmploymentStatusChangeDate = staffEmploymentStatusChangeDate;
	}

	public Date getStaffHireDate() {
		return staffHireDate;
	}

	public void setStaffHireDate(Date staffHireDate) {
		this.staffHireDate = staffHireDate;
	}

	public TaxpayerIdentificationNumberQualifier getStaffTaxpayerIdentificationNumberQualifier() {
		return staffTaxpayerIdentificationNumberQualifier;
	}

	public void setStaffTaxpayerIdentificationNumberQualifier(
			TaxpayerIdentificationNumberQualifier staffTaxpayerIdentificationNumberQualifier) {
		this.staffTaxpayerIdentificationNumberQualifier = staffTaxpayerIdentificationNumberQualifier;
	}

	public String getStaffTaxpayerIdentificationNumber() {
		return staffTaxpayerIdentificationNumber;
	}

	public void setStaffTaxpayerIdentificationNumber(
			String staffTaxpayerIdentificationNumber) {
		this.staffTaxpayerIdentificationNumber = staffTaxpayerIdentificationNumber;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public MaritalStatusName getStaffMaritalStatusName() {
		return staffMaritalStatusName;
	}

	public void setStaffMaritalStatusName(MaritalStatusName staffMaritalStatusName) {
		this.staffMaritalStatusName = staffMaritalStatusName;
	}
}
