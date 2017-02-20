//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


package com.sandata.lab.rest.oracle.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A written (or electronic) order to get or receive specified medical services
 * 
 * <p>Java class for Referral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Referral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Claim" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Schedule_Event" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Claim_Line" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Referral_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Referral_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Admission_ID" type="{}ID" />
 *       &lt;attribute name="Referral_Type_Name" type="{}Referral_Type_Name" />
 *       &lt;attribute name="Healthcare_Professional_National_Provider_Identifier" type="{}National_Provider_Identifier" />
 *       &lt;attribute name="Referral_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Referral_Receival_Method">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Referral_Phone" type="{}Phone" />
 *       &lt;attribute name="Referral_Email" type="{}Email" />
 *       &lt;attribute name="Referral_Start_Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Referral_End_Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Referral_Source_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Referral", propOrder = {
    "schedule",
    "claim",
    "scheduleEvent",
    "claimLine"
})
@OracleMetadata(
        packageName = "PKG_REFERRAL",
        insertMethod = "insertRfrl",
        updateMethod = "updateRfrl",
        deleteMethod = "deleteRfrl",
        getMethod = "getRfrl",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.RfrlT",
        primaryKeys = {})
public class Referral extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Schedule")
    @SerializedName("Schedule")
    protected List<Schedule> schedule;
    @XmlElement(name = "Claim")
    @SerializedName("Claim")
    protected List<Claim> claim;
    @XmlElement(name = "Schedule_Event")
    @SerializedName("ScheduleEvent")
    protected List<ScheduleEvent> scheduleEvent;
    @XmlElement(name = "Claim_Line")
    @SerializedName("ClaimLine")
    protected List<ClaimLine> claimLine;
    @XmlAttribute(name = "Referral_SK", required = true)
    @SerializedName("ReferralSK")
	@Mapping(getter = "getRfrlSk", setter = "setRfrlSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger referralSK;
    @XmlAttribute(name = "Referral_ID", required = true)
    @SerializedName("ReferralID")
	@Mapping(getter = "getRfrlId", setter = "setRfrlId", type = "String", index = 1)
    protected String referralID;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecTermTmstp", setter = "setRecTermTmstp", type = "java.sql.Timestamp", index = 5)
    protected Date recordTerminationTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 6)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 7)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 8)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 9)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 10)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 11)
    protected String businessEntityID;
    @XmlAttribute(name = "Admission_ID")
    @SerializedName("AdmissionID")
	@Mapping(getter = "getAdmId", setter = "setAdmId", type = "String", index = 12)
    protected String admissionID;
    @XmlAttribute(name = "Referral_Type_Name")
    @SerializedName("ReferralTypeName")
	@Mapping(getter = "getRfrlTypName", setter = "setRfrlTypName", type = "String", index = 13)
    protected String referralTypeName;
    @XmlAttribute(name = "Healthcare_Professional_National_Provider_Identifier")
    @SerializedName("HealthcareProfessionalNationalProviderIdentifier")
	@Mapping(getter = "getHcpNpi", setter = "setHcpNpi", type = "String", index = 14)
    protected String healthcareProfessionalNationalProviderIdentifier;
    @XmlAttribute(name = "Referral_Name")
    @SerializedName("ReferralName")
	@Mapping(getter = "getRfrlName", setter = "setRfrlName", type = "String", index = 15)
    protected String referralName;
    @XmlAttribute(name = "Referral_Receival_Method")
    @SerializedName("ReferralReceivalMethod")
	@Mapping(getter = "getRfrlReceivalMthd", setter = "setRfrlReceivalMthd", type = "String", index = 16)
    protected String referralReceivalMethod;
    @XmlAttribute(name = "Referral_Phone")
    @SerializedName("ReferralPhone")
	@Mapping(getter = "getRfrlPhone", setter = "setRfrlPhone", type = "String", index = 17)
    protected String referralPhone;
    @XmlAttribute(name = "Referral_Email")
    @SerializedName("ReferralEmail")
	@Mapping(getter = "getRfrlEmail", setter = "setRfrlEmail", type = "String", index = 18)
    protected String referralEmail;
    @XmlAttribute(name = "Referral_Start_Date")
    @SerializedName("ReferralStartDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
	@Mapping(getter = "getRfrlStartDate", setter = "setRfrlStartDate", type = "java.sql.Timestamp", index = 19)
    protected Date referralStartDate;
    @XmlAttribute(name = "Referral_End_Date")
    @SerializedName("ReferralEndDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
	@Mapping(getter = "getRfrlEndDate", setter = "setRfrlEndDate", type = "java.sql.Timestamp", index = 20)
    protected Date referralEndDate;
    @XmlAttribute(name = "Referral_Source_Name")
    @SerializedName("ReferralSourceName")
	@Mapping(getter = "getRfrlSrcName", setter = "setRfrlSrcName", type = "String", index = 21)
    protected String referralSourceName;

    /**
     * Gets the value of the schedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Schedule }
     * 
     * 
     */
    public List<Schedule> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<Schedule>();
        }
        return this.schedule;
    }

    /**
     * Gets the value of the claim property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claim property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaim().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Claim }
     * 
     * 
     */
    public List<Claim> getClaim() {
        if (claim == null) {
            claim = new ArrayList<Claim>();
        }
        return this.claim;
    }

    /**
     * Gets the value of the scheduleEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduleEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduleEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduleEvent }
     * 
     * 
     */
    public List<ScheduleEvent> getScheduleEvent() {
        if (scheduleEvent == null) {
            scheduleEvent = new ArrayList<ScheduleEvent>();
        }
        return this.scheduleEvent;
    }

    /**
     * Gets the value of the claimLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claimLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaimLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaimLine }
     * 
     * 
     */
    public List<ClaimLine> getClaimLine() {
        if (claimLine == null) {
            claimLine = new ArrayList<ClaimLine>();
        }
        return this.claimLine;
    }

    /**
     * Gets the value of the referralSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReferralSK() {
        return referralSK;
    }

    /**
     * Sets the value of the referralSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReferralSK(BigInteger value) {
        this.referralSK = value;
    }

    /**
     * Gets the value of the referralID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralID() {
        return referralID;
    }

    /**
     * Sets the value of the referralID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralID(String value) {
        this.referralID = value;
    }

    /**
     * Gets the value of the recordCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    /**
     * Sets the value of the recordCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreateTimestamp(Date value) {
        this.recordCreateTimestamp = value;
    }

    /**
     * Gets the value of the recordUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    /**
     * Sets the value of the recordUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdateTimestamp(Date value) {
        this.recordUpdateTimestamp = value;
    }

    /**
     * Gets the value of the recordEffectiveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordEffectiveTimestamp() {
        return recordEffectiveTimestamp;
    }

    /**
     * Sets the value of the recordEffectiveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordEffectiveTimestamp(Date value) {
        this.recordEffectiveTimestamp = value;
    }

    /**
     * Gets the value of the recordTerminationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordTerminationTimestamp() {
        return recordTerminationTimestamp;
    }

    /**
     * Sets the value of the recordTerminationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordTerminationTimestamp(Date value) {
        this.recordTerminationTimestamp = value;
    }

    /**
     * Gets the value of the recordCreatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordCreatedBy() {
        return recordCreatedBy;
    }

    /**
     * Sets the value of the recordCreatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreatedBy(String value) {
        this.recordCreatedBy = value;
    }

    /**
     * Gets the value of the recordUpdatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordUpdatedBy() {
        return recordUpdatedBy;
    }

    /**
     * Sets the value of the recordUpdatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdatedBy(String value) {
        this.recordUpdatedBy = value;
    }

    /**
     * Gets the value of the changeReasonMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    /**
     * Sets the value of the changeReasonMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReasonMemo(String value) {
        this.changeReasonMemo = value;
    }

    /**
     * Gets the value of the currentRecordIndicator property.
     * 
     */
    public boolean isCurrentRecordIndicator() {
        return currentRecordIndicator;
    }

    /**
     * Sets the value of the currentRecordIndicator property.
     * 
     */
    public void setCurrentRecordIndicator(boolean value) {
        this.currentRecordIndicator = value;
    }

    /**
     * Gets the value of the changeVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }

    /**
     * Sets the value of the changeVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChangeVersionID(BigInteger value) {
        this.changeVersionID = value;
    }

    /**
     * Gets the value of the businessEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * Sets the value of the businessEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityID(String value) {
        this.businessEntityID = value;
    }

    /**
     * Gets the value of the admissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdmissionID() {
        return admissionID;
    }

    /**
     * Sets the value of the admissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdmissionID(String value) {
        this.admissionID = value;
    }

    /**
     * Gets the value of the referralTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralTypeName() {
        return referralTypeName;
    }

    /**
     * Sets the value of the referralTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralTypeName(String value) {
        this.referralTypeName = value;
    }

    /**
     * Gets the value of the healthcareProfessionalNationalProviderIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthcareProfessionalNationalProviderIdentifier() {
        return healthcareProfessionalNationalProviderIdentifier;
    }

    /**
     * Sets the value of the healthcareProfessionalNationalProviderIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthcareProfessionalNationalProviderIdentifier(String value) {
        this.healthcareProfessionalNationalProviderIdentifier = value;
    }

    /**
     * Gets the value of the referralName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralName() {
        return referralName;
    }

    /**
     * Sets the value of the referralName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralName(String value) {
        this.referralName = value;
    }

    /**
     * Gets the value of the referralReceivalMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralReceivalMethod() {
        return referralReceivalMethod;
    }

    /**
     * Sets the value of the referralReceivalMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralReceivalMethod(String value) {
        this.referralReceivalMethod = value;
    }

    /**
     * Gets the value of the referralPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralPhone() {
        return referralPhone;
    }

    /**
     * Sets the value of the referralPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralPhone(String value) {
        this.referralPhone = value;
    }

    /**
     * Gets the value of the referralEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralEmail() {
        return referralEmail;
    }

    /**
     * Sets the value of the referralEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralEmail(String value) {
        this.referralEmail = value;
    }

    /**
     * Gets the value of the referralStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getReferralStartDate() {
        return referralStartDate;
    }

    /**
     * Sets the value of the referralStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralStartDate(Date value) {
        this.referralStartDate = value;
    }

    /**
     * Gets the value of the referralEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getReferralEndDate() {
        return referralEndDate;
    }

    /**
     * Sets the value of the referralEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralEndDate(Date value) {
        this.referralEndDate = value;
    }

    /**
     * Gets the value of the referralSourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralSourceName() {
        return referralSourceName;
    }

    /**
     * Sets the value of the referralSourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralSourceName(String value) {
        this.referralSourceName = value;
    }

}