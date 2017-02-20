//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 12:13:13 AM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>Java class for Prepaid_Capitation_Plan_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prepaid_Capitation_Plan_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Claim_Payment_Advice" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Claim" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Payer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Contract" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Prepaid_Capitation_Plan_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Prepaid_Capitation_Plan_Code" use="required" type="{}Code" />
 *       &lt;attribute name="Prepaid_Capitation_Plan_ID" type="{}ID" />
 *       &lt;attribute name="Prepaid_Capitation_Plan_Provider_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Prepaid_Capitation_Plan_Phone" type="{}Phone" />
 *       &lt;attribute name="Prepaid_Capitation_Plan_Type_Name">
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
@XmlType(name = "Prepaid_Capitation_Plan_Lookup", propOrder = {
    "claimPaymentAdvice",
    "claim",
    "payer",
    "contract"
})
@OracleMetadata(
        packageName = "PKG_LOOKUP",
        insertMethod = "insertPcpLkup",
        updateMethod = "updatePcpLkup",
        deleteMethod = "deletePcpLkup",
        getMethod = "getPcpLkup",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.PcpLkupT",
        primaryKeys = {})
public class PrepaidCapitationPlanLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Claim_Payment_Advice")
    @SerializedName("ClaimPaymentAdvice")
    protected List<ClaimPaymentAdvice> claimPaymentAdvice;
    @XmlElement(name = "Claim")
    @SerializedName("Claim")
    protected List<Claim> claim;
    @XmlElement(name = "Payer")
    @SerializedName("Payer")
    protected List<Payer> payer;
    @XmlElement(name = "Contract")
    @SerializedName("Contract")
    protected List<Contract> contract;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_SK", required = true)
    @SerializedName("PrepaidCapitationPlanSK")
	@Mapping(getter = "getPcpSk", setter = "setPcpSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger prepaidCapitationPlanSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecTermTmstp", setter = "setRecTermTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordTerminationTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 5)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 6)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 7)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 8)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 9)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_Code", required = true)
    @SerializedName("PrepaidCapitationPlanCode")
	@Mapping(getter = "getPcpCode", setter = "setPcpCode", type = "String", index = 10)
    protected String prepaidCapitationPlanCode;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_ID")
    @SerializedName("PrepaidCapitationPlanID")
	@Mapping(getter = "getPcpId", setter = "setPcpId", type = "String", index = 11)
    protected String prepaidCapitationPlanID;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_Provider_Name")
    @SerializedName("PrepaidCapitationPlanProviderName")
	@Mapping(getter = "getPcpProviderName", setter = "setPcpProviderName", type = "String", index = 12)
    protected String prepaidCapitationPlanProviderName;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_Phone")
    @SerializedName("PrepaidCapitationPlanPhone")
	@Mapping(getter = "getPcpPhone", setter = "setPcpPhone", type = "String", index = 13)
    protected String prepaidCapitationPlanPhone;
    @XmlAttribute(name = "Prepaid_Capitation_Plan_Type_Name")
    @SerializedName("PrepaidCapitationPlanTypeName")
	@Mapping(getter = "getPcpTypName", setter = "setPcpTypName", type = "String", index = 14)
    protected String prepaidCapitationPlanTypeName;

    /**
     * Gets the value of the claimPaymentAdvice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claimPaymentAdvice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaimPaymentAdvice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaimPaymentAdvice }
     * 
     * 
     */
    public List<ClaimPaymentAdvice> getClaimPaymentAdvice() {
        if (claimPaymentAdvice == null) {
            claimPaymentAdvice = new ArrayList<ClaimPaymentAdvice>();
        }
        return this.claimPaymentAdvice;
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
     * Gets the value of the payer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayerName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Payer }
     * 
     * 
     */
    public List<Payer> getPayer() {
        if (payer == null) {
            payer = new ArrayList<Payer>();
        }
        return this.payer;
    }

    /**
     * Gets the value of the contract property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contract property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContract().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contract }
     * 
     * 
     */
    public List<Contract> getContract() {
        if (contract == null) {
            contract = new ArrayList<Contract>();
        }
        return this.contract;
    }

    /**
     * Gets the value of the prepaidCapitationPlanSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrepaidCapitationPlanSK() {
        return prepaidCapitationPlanSK;
    }

    /**
     * Sets the value of the prepaidCapitationPlanSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrepaidCapitationPlanSK(BigInteger value) {
        this.prepaidCapitationPlanSK = value;
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
     * Gets the value of the prepaidCapitationPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCapitationPlanCode() {
        return prepaidCapitationPlanCode;
    }

    /**
     * Sets the value of the prepaidCapitationPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCapitationPlanCode(String value) {
        this.prepaidCapitationPlanCode = value;
    }

    /**
     * Gets the value of the prepaidCapitationPlanID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCapitationPlanID() {
        return prepaidCapitationPlanID;
    }

    /**
     * Sets the value of the prepaidCapitationPlanID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCapitationPlanID(String value) {
        this.prepaidCapitationPlanID = value;
    }

    /**
     * Gets the value of the prepaidCapitationPlanProviderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCapitationPlanProviderName() {
        return prepaidCapitationPlanProviderName;
    }

    /**
     * Sets the value of the prepaidCapitationPlanProviderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCapitationPlanProviderName(String value) {
        this.prepaidCapitationPlanProviderName = value;
    }

    /**
     * Gets the value of the prepaidCapitationPlanPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCapitationPlanPhone() {
        return prepaidCapitationPlanPhone;
    }

    /**
     * Sets the value of the prepaidCapitationPlanPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCapitationPlanPhone(String value) {
        this.prepaidCapitationPlanPhone = value;
    }

    /**
     * Gets the value of the prepaidCapitationPlanTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCapitationPlanTypeName() {
        return prepaidCapitationPlanTypeName;
    }

    /**
     * Sets the value of the prepaidCapitationPlanTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCapitationPlanTypeName(String value) {
        this.prepaidCapitationPlanTypeName = value;
    }

}
