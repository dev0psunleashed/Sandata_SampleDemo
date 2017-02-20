package com.sandata.lab.exports.evv.model;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:41 AM
 */

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CLIENT-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supervisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DischargeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientSSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MedicalRecordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Team" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Borough" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CompanyNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipient", propOrder = {
        "clientaccount",
        "vendorCode",
        "recipientID",
        "lastName",
        "firstName",
        "status",
        "supervisor",
        "dischargeDate",
        "address1",
        "address2",
        "city",
        "state",
        "zipCode",
        "recipientSSN",
        "contract",
        "billRate",
        "service",
        "caseNumber",
        "caseSequence",
        "programCode",
        "medicalRecordNumber",
        "arNumber",
        "team",
        "branch",
        "borough",
        "priority",
        "area",
        "weekend",
        "companyNumber",
        "sex",
        "dob",
        "timeZone",
        "recipientIDCustom1",
        "recipientIDCustom2"
})
public class Recipient {

    @XmlElement(name = "CLIENT-ACCOUNT", required = true)
    protected String clientaccount;
    @XmlElement(name = "VendorCode")
    protected String vendorCode;
    @XmlElement(name = "RecipientID", required = true)
    protected String recipientID;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Supervisor")
    protected String supervisor;
    @XmlElement(name = "DischargeDate")
    @XmlSchemaType(name = "dateTime")
    protected String dischargeDate;
    @XmlElement(name = "Address1")
    protected String address1;
    @XmlElement(name = "Address2")
    protected String address2;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "ZipCode")
    protected String zipCode;
    @XmlElement(name = "RecipientSSN")
    protected String recipientSSN;
    @XmlElement(name = "Contract")
    protected String contract;
    @XmlElement(name = "BillRate")
    protected String billRate;
    @XmlElement(name = "Service")
    protected String service;
    @XmlElement(name = "CaseNumber")
    protected String caseNumber;
    @XmlElement(name = "CaseSequence")
    protected String caseSequence;
    @XmlElement(name = "ProgramCode")
    protected String programCode;
    @XmlElement(name = "MedicalRecordNumber")
    protected String medicalRecordNumber;
    @XmlElement(name = "ARNumber")
    protected String arNumber;
    @XmlElement(name = "Team")
    protected String team;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElement(name = "Borough")
    protected String borough;
    @XmlElement(name = "Priority")
    protected String priority;
    @XmlElement(name = "Area")
    protected String area;
    @XmlElement(name = "Weekend")
    @XmlSchemaType(name = "dateTime")
    protected String weekend;
    @XmlElement(name = "CompanyNumber")
    protected String companyNumber;
    @XmlElement(name = "Sex")
    protected String sex;
    @XmlElement(name = "DOB")
    protected String dob;
    @XmlElement(name = "TimeZone")
    protected String timeZone;
    @XmlElement(name = "RecipientIDCustom1")
    protected String recipientIDCustom1;
    @XmlElement(name = "RecipientIDCustom2")
    protected String recipientIDCustom2;

    /**
     * Gets the value of the clientaccount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCLIENTACCOUNT() {
        return clientaccount;
    }

    /**
     * Sets the value of the clientaccount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCLIENTACCOUNT(String value) {
        this.clientaccount = value;
    }

    /**
     * Gets the value of the vendorCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * Sets the value of the vendorCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVendorCode(String value) {
        this.vendorCode = value;
    }

    /**
     * Gets the value of the recipientID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipientID() {
        return recipientID;
    }

    /**
     * Sets the value of the recipientID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipientID(String value) {
        this.recipientID = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the supervisor property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the value of the supervisor property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSupervisor(String value) {
        this.supervisor = value;
    }

    /**
     * Gets the value of the dischargeDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDischargeDate() {
        return dischargeDate;
    }

    /**
     * Sets the value of the dischargeDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDischargeDate(String value) {
        this.dischargeDate = value;
    }

    /**
     * Gets the value of the address1 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zipCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * Gets the value of the recipientSSN property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipientSSN() {
        return recipientSSN;
    }

    /**
     * Sets the value of the recipientSSN property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipientSSN(String value) {
        this.recipientSSN = value;
    }

    /**
     * Gets the value of the contract property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setContract(String value) {
        this.contract = value;
    }

    /**
     * Gets the value of the billRate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBillRate() {
        return billRate;
    }

    /**
     * Sets the value of the billRate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBillRate(String value) {
        this.billRate = value;
    }

    /**
     * Gets the value of the service property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Gets the value of the caseNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCaseNumber() {
        return caseNumber;
    }

    /**
     * Sets the value of the caseNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCaseNumber(String value) {
        this.caseNumber = value;
    }

    /**
     * Gets the value of the caseSequence property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCaseSequence() {
        return caseSequence;
    }

    /**
     * Sets the value of the caseSequence property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCaseSequence(String value) {
        this.caseSequence = value;
    }

    /**
     * Gets the value of the programCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the value of the programCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProgramCode(String value) {
        this.programCode = value;
    }

    /**
     * Gets the value of the medicalRecordNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    /**
     * Sets the value of the medicalRecordNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMedicalRecordNumber(String value) {
        this.medicalRecordNumber = value;
    }

    /**
     * Gets the value of the arNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getARNumber() {
        return arNumber;
    }

    /**
     * Sets the value of the arNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setARNumber(String value) {
        this.arNumber = value;
    }

    /**
     * Gets the value of the team property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the value of the team property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTeam(String value) {
        this.team = value;
    }

    /**
     * Gets the value of the branch property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the value of the branch property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBranch(String value) {
        this.branch = value;
    }

    /**
     * Gets the value of the borough property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Sets the value of the borough property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBorough(String value) {
        this.borough = value;
    }

    /**
     * Gets the value of the priority property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the area property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the weekend property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getWeekend() {
        return weekend;
    }

    /**
     * Sets the value of the weekend property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWeekend(String value) {
        this.weekend = value;
    }

    /**
     * Gets the value of the companyNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * Sets the value of the companyNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCompanyNumber(String value) {
        this.companyNumber = value;
    }

    /**
     * Gets the value of the sex property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the dob property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDOB() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDOB(String value) {
        this.dob = value;
    }

    /**
     * Gets the value of the timeZone property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the recipientIDCustom1 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipientIDCustom1() {
        return recipientIDCustom1;
    }

    /**
     * Sets the value of the recipientIDCustom1 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipientIDCustom1(String value) {
        this.recipientIDCustom1 = value;
    }

    /**
     * Gets the value of the recipientIDCustom2 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipientIDCustom2() {
        return recipientIDCustom2;
    }

    /**
     * Sets the value of the recipientIDCustom2 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipientIDCustom2(String value) {
        this.recipientIDCustom2 = value;
    }

}

