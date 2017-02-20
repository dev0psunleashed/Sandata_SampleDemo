package com.sandata.lab.exports.evv.model;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:45 AM
 */

import org.omg.CORBA.StringValueHelper;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;

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
 *         &lt;element name="ASGN-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ASGN-RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ASGN-EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActivityTblID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActivityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ElapsedTime" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScheduleFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DutyFree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VisitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LiveInCase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OTABHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OTABCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OTABApprover" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProcedureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProcCodeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modifier4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Activity", propOrder = {
        "asgnaccount",
        "vendorCode",
        "asgnRecipientID",
        "asgnempssn",
        "activityTblID",
        "activityDate",
        "startTime",
        "endTime",
        "elapsedTime",
        "arNumber",
        "payRate",
        "billRate",
        "scheduleFlag",
        "dutyFree",
        "status",
        "weekend",
        "discipline",
        "service",
        "contract",
        "branch",
        "visitType",
        "liveInCase",
        "otabHours",
        "otabCode",
        "otabApprover",
        "procedureCode",
        "caseNumber",
        "caseSequence",
        "timeZone",
        "billCode",
        "procCodeQualifier",
        "modifier1",
        "modifier2",
        "modifier3",
        "modifier4"
})
public class Activity {

    @XmlElement(name = "ASGN-ACCOUNT", required = true)
    protected String asgnaccount;
    @XmlElement(name = "VendorCode")
    protected String vendorCode;
    @XmlElement(name = "ASGN-RecipientID", required = true)
    protected String asgnRecipientID;
    @XmlElement(name = "ASGN-EMP-SSN", required = true)
    protected String asgnempssn;
    @XmlElement(name = "ActivityTblID", required = true)
    protected String activityTblID;
    @XmlElement(name = "ActivityDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected String activityDate;
    @XmlElement(name = "StartTime")
    @XmlSchemaType(name = "dateTime")
    protected String startTime;
    @XmlElement(name = "EndTime")
    @XmlSchemaType(name = "dateTime")
    protected String endTime;
    @XmlElement(name = "ElapsedTime")
    protected BigDecimal elapsedTime;
    @XmlElement(name = "ARNumber")
    protected String arNumber;
    @XmlElement(name = "PayRate")
    protected String payRate;
    @XmlElement(name = "BillRate")
    protected String billRate;
    @XmlElement(name = "ScheduleFlag")
    protected String scheduleFlag;
    @XmlElement(name = "DutyFree")
    protected String dutyFree;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Weekend")
    @XmlSchemaType(name = "dateTime")
    protected String weekend;
    @XmlElement(name = "Discipline")
    protected String discipline;
    @XmlElement(name = "Service")
    protected String service;
    @XmlElement(name = "Contract")
    protected String contract;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElement(name = "VisitType")
    protected String visitType;
    @XmlElement(name = "LiveInCase")
    protected String liveInCase;
    @XmlElement(name = "OTABHours")
    protected String otabHours;
    @XmlElement(name = "OTABCode")
    protected String otabCode;
    @XmlElement(name = "OTABApprover")
    protected String otabApprover;
    @XmlElement(name = "ProcedureCode")
    protected String procedureCode;
    @XmlElement(name = "CaseNumber")
    protected String caseNumber;
    @XmlElement(name = "CaseSequence")
    protected String caseSequence;
    @XmlElement(name = "TimeZone")
    protected String timeZone;
    @XmlElement(name = "BillCode")
    protected String billCode;
    @XmlElement(name = "ProcCodeQualifier")
    protected String procCodeQualifier;
    @XmlElement(name = "Modifier1")
    protected String modifier1;
    @XmlElement(name = "Modifier2")
    protected String modifier2;
    @XmlElement(name = "Modifier3")
    protected String modifier3;
    @XmlElement(name = "Modifier4")
    protected String modifier4;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "rowOrder")
    private String rowOrder;


    /**
     * Gets the value of the asgnaccount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getASGNACCOUNT() {
        return asgnaccount;
    }

    /**
     * Sets the value of the asgnaccount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setASGNACCOUNT(String value) {
        this.asgnaccount = value;
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
     * Gets the value of the asgnRecipientID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getASGNRecipientID() {
        return asgnRecipientID;
    }

    /**
     * Sets the value of the asgnRecipientID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setASGNRecipientID(String value) {
        this.asgnRecipientID = value;
    }

    /**
     * Gets the value of the asgnempssn property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getASGNEMPSSN() {
        return asgnempssn;
    }

    /**
     * Sets the value of the asgnempssn property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setASGNEMPSSN(String value) {
        this.asgnempssn = value;
    }

    /**
     * Gets the value of the activityTblID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActivityTblID() {
        return activityTblID;
    }

    /**
     * Sets the value of the activityTblID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActivityTblID(String value) {
        this.activityTblID = value;
    }

    /**
     * Gets the value of the activityDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public String getActivityDate() {
        return activityDate;
    }

    /**
     * Sets the value of the activityDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActivityDate(String value) {
        this.activityDate = value;
    }

    /**
     * Gets the value of the startTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStartTime()
    {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the elapsedTime property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the value of the elapsedTime property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setElapsedTime(BigDecimal value) {
        this.elapsedTime = value;
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
     * Gets the value of the payRate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPayRate() {
        return payRate;
    }

    /**
     * Sets the value of the payRate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPayRate(String value) {
        this.payRate = value;
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
     * Gets the value of the scheduleFlag property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getScheduleFlag() {
        return scheduleFlag;
    }

    /**
     * Sets the value of the scheduleFlag property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setScheduleFlag(String value) {
        this.scheduleFlag = value;
    }

    /**
     * Gets the value of the dutyFree property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDutyFree() {
        return dutyFree;
    }

    /**
     * Sets the value of the dutyFree property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDutyFree(String value) {
        this.dutyFree = value;
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
     * Gets the value of the weekend property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String  getWeekend() {
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
     * Gets the value of the discipline property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * Sets the value of the discipline property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDiscipline(String value) {
        this.discipline = value;
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
     * Gets the value of the visitType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVisitType() {
        return visitType;
    }

    /**
     * Sets the value of the visitType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVisitType(String value) {
        this.visitType = value;
    }

    /**
     * Gets the value of the liveInCase property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLiveInCase() {
        return liveInCase;
    }

    /**
     * Sets the value of the liveInCase property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLiveInCase(String value) {
        this.liveInCase = value;
    }

    /**
     * Gets the value of the otabHours property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOTABHours() {
        return otabHours;
    }

    /**
     * Sets the value of the otabHours property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOTABHours(String value) {
        this.otabHours = value;
    }

    /**
     * Gets the value of the otabCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOTABCode() {
        return otabCode;
    }

    /**
     * Sets the value of the otabCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOTABCode(String value) {
        this.otabCode = value;
    }

    /**
     * Gets the value of the otabApprover property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOTABApprover() {
        return otabApprover;
    }

    /**
     * Sets the value of the otabApprover property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOTABApprover(String value) {
        this.otabApprover = value;
    }

    /**
     * Gets the value of the procedureCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProcedureCode() {
        return procedureCode;
    }

    /**
     * Sets the value of the procedureCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProcedureCode(String value) {
        this.procedureCode = value;
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
     * Gets the value of the billCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * Sets the value of the billCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBillCode(String value) {
        this.billCode = value;
    }

    /**
     * Gets the value of the procCodeQualifier property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProcCodeQualifier() {
        return procCodeQualifier;
    }

    /**
     * Sets the value of the procCodeQualifier property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProcCodeQualifier(String value) {
        this.procCodeQualifier = value;
    }

    /**
     * Gets the value of the modifier1 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModifier1() {
        return modifier1;
    }

    /**
     * Sets the value of the modifier1 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModifier1(String value) {
        this.modifier1 = value;
    }

    /**
     * Gets the value of the modifier2 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModifier2() {
        return modifier2;
    }

    /**
     * Sets the value of the modifier2 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModifier2(String value) {
        this.modifier2 = value;
    }

    /**
     * Gets the value of the modifier3 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModifier3() {
        return modifier3;
    }

    /**
     * Sets the value of the modifier3 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModifier3(String value) {
        this.modifier3 = value;
    }

    /**
     * Gets the value of the modifier4 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModifier4() {
        return modifier4;
    }

    /**
     * Sets the value of the modifier4 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModifier4(String value) {
        this.modifier4 = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowOrder() {
        return rowOrder;
    }

    public void setRowOrder(String rowOrder) {
        this.rowOrder = rowOrder;
    }
}

