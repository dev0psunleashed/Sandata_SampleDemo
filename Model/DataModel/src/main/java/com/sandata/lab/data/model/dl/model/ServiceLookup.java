//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 11:05:46 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Reference table for storing the unique list of services within the system
 * 
 * <p>Java class for Service_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Service_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Patient_Rate_Matrix" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Staff_Supplemental_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Patient_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Contract_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Staff_Associated_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Staff_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Business_Entity_Rate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Payroll_Rate_Matrix" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Staff_Training_Category_Service" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Order_Service" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Business_Entity_Compliance_Service_List" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Billing_Rate_Matrix" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Service_Lookup_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Service_Name" use="required" type="{}Service_Name" />
 *       &lt;attribute name="Service_Description" type="{}Description_Short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Service_Lookup", propOrder = {
    "patientRateMatrix",
    "staffSupplementalRate",
    "patientRate",
    "contractRate",
    "staffAssociatedRate",
    "staffRate",
    "businessEntityRate",
    "payrollRateMatrix",
    "staffTrainingCategoryService",
    "orderService",
    "businessEntityComplianceServiceList",
    "billingRateMatrix"
})
@OracleMetadata(
        packageName = "PKG_LOOKUP",
        insertMethod = "insertSvcLkup",
        updateMethod = "updateSvcLkup",
        deleteMethod = "deleteSvcLkup",
        getMethod = "getSvcLkup",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.SvcLkupT",
        primaryKeys = {})
public class ServiceLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Patient_Rate_Matrix")
    @SerializedName("PatientRateMatrix")
    protected List<PatientRateMatrix> patientRateMatrix;
    @XmlElement(name = "Staff_Supplemental_Rate")
    @SerializedName("StaffSupplementalRate")
    protected List<StaffSupplementalRate> staffSupplementalRate;
    @XmlElement(name = "Patient_Rate")
    @SerializedName("PatientRate")
    protected List<PatientRate> patientRate;
    @XmlElement(name = "Contract_Rate")
    @SerializedName("ContractRate")
    protected List<ContractRate> contractRate;
    @XmlElement(name = "Staff_Associated_Rate")
    @SerializedName("StaffAssociatedRate")
    protected List<StaffAssociatedRate> staffAssociatedRate;
    @XmlElement(name = "Staff_Rate")
    @SerializedName("StaffRate")
    protected List<StaffRate> staffRate;
    @XmlElement(name = "Business_Entity_Rate")
    @SerializedName("BusinessEntityRate")
    protected List<BusinessEntityRate> businessEntityRate;
    @XmlElement(name = "Payroll_Rate_Matrix")
    @SerializedName("PayrollRateMatrix")
    protected List<PayrollRateMatrix> payrollRateMatrix;
    @XmlElement(name = "Staff_Training_Category_Service")
    @SerializedName("StaffTrainingCategoryService")
    protected List<StaffTrainingCategoryService> staffTrainingCategoryService;
    @XmlElement(name = "Order_Service")
    @SerializedName("OrderService")
    protected List<OrderService> orderService;
    @XmlElement(name = "Business_Entity_Compliance_Service_List")
    @SerializedName("BusinessEntityComplianceServiceList")
    protected List<BusinessEntityComplianceServiceList> businessEntityComplianceServiceList;
    @XmlElement(name = "Billing_Rate_Matrix")
    @SerializedName("BillingRateMatrix")
    protected List<BillingRateMatrix> billingRateMatrix;
    @XmlAttribute(name = "Service_Lookup_SK", required = true)
    @SerializedName("ServiceLookupSK")
	@Mapping(getter = "getSvcLkupSk", setter = "setSvcLkupSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger serviceLookupSK;
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
    @XmlAttribute(name = "Service_Name", required = true)
    @SerializedName("ServiceName")
	@Mapping(getter = "getSvcName", setter = "setSvcName", type = "String", index = 10)
    protected ServiceName serviceName;
    @XmlAttribute(name = "Service_Description")
    @SerializedName("ServiceDescription")
	@Mapping(getter = "getSvcDesc", setter = "setSvcDesc", type = "String", index = 11)
    protected String serviceDescription;

    /**
     * Gets the value of the patientRateMatrix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientRateMatrix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientRateMatrix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientRateMatrix }
     * 
     * 
     */
    public List<PatientRateMatrix> getPatientRateMatrix() {
        if (patientRateMatrix == null) {
            patientRateMatrix = new ArrayList<PatientRateMatrix>();
        }
        return this.patientRateMatrix;
    }

    /**
     * Gets the value of the staffSupplementalRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffSupplementalRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffSupplementalRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffSupplementalRate }
     * 
     * 
     */
    public List<StaffSupplementalRate> getStaffSupplementalRate() {
        if (staffSupplementalRate == null) {
            staffSupplementalRate = new ArrayList<StaffSupplementalRate>();
        }
        return this.staffSupplementalRate;
    }

    /**
     * Gets the value of the patientRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientRate }
     * 
     * 
     */
    public List<PatientRate> getPatientRate() {
        if (patientRate == null) {
            patientRate = new ArrayList<PatientRate>();
        }
        return this.patientRate;
    }

    /**
     * Gets the value of the contractRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractRate }
     * 
     * 
     */
    public List<ContractRate> getContractRate() {
        if (contractRate == null) {
            contractRate = new ArrayList<ContractRate>();
        }
        return this.contractRate;
    }

    /**
     * Gets the value of the staffAssociatedRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffAssociatedRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffAssociatedRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffAssociatedRate }
     * 
     * 
     */
    public List<StaffAssociatedRate> getStaffAssociatedRate() {
        if (staffAssociatedRate == null) {
            staffAssociatedRate = new ArrayList<StaffAssociatedRate>();
        }
        return this.staffAssociatedRate;
    }

    /**
     * Gets the value of the staffRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffRate }
     * 
     * 
     */
    public List<StaffRate> getStaffRate() {
        if (staffRate == null) {
            staffRate = new ArrayList<StaffRate>();
        }
        return this.staffRate;
    }

    /**
     * Gets the value of the businessEntityRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessEntityRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessEntityRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessEntityRate }
     * 
     * 
     */
    public List<BusinessEntityRate> getBusinessEntityRate() {
        if (businessEntityRate == null) {
            businessEntityRate = new ArrayList<BusinessEntityRate>();
        }
        return this.businessEntityRate;
    }

    /**
     * Gets the value of the payrollRateMatrix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payrollRateMatrix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayrollRateMatrix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PayrollRateMatrix }
     * 
     * 
     */
    public List<PayrollRateMatrix> getPayrollRateMatrix() {
        if (payrollRateMatrix == null) {
            payrollRateMatrix = new ArrayList<PayrollRateMatrix>();
        }
        return this.payrollRateMatrix;
    }

    /**
     * Gets the value of the staffTrainingCategoryService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffTrainingCategoryService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffTrainingCategoryService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffTrainingCategoryService }
     * 
     * 
     */
    public List<StaffTrainingCategoryService> getStaffTrainingCategoryService() {
        if (staffTrainingCategoryService == null) {
            staffTrainingCategoryService = new ArrayList<StaffTrainingCategoryService>();
        }
        return this.staffTrainingCategoryService;
    }

    /**
     * Gets the value of the orderService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderService }
     * 
     * 
     */
    public List<OrderService> getOrderService() {
        if (orderService == null) {
            orderService = new ArrayList<OrderService>();
        }
        return this.orderService;
    }

    /**
     * Gets the value of the businessEntityComplianceServiceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessEntityComplianceServiceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessEntityComplianceServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessEntityComplianceServiceList }
     * 
     * 
     */
    public List<BusinessEntityComplianceServiceList> getBusinessEntityComplianceServiceList() {
        if (businessEntityComplianceServiceList == null) {
            businessEntityComplianceServiceList = new ArrayList<BusinessEntityComplianceServiceList>();
        }
        return this.businessEntityComplianceServiceList;
    }

    /**
     * Gets the value of the billingRateMatrix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingRateMatrix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingRateMatrix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillingRateMatrix }
     * 
     * 
     */
    public List<BillingRateMatrix> getBillingRateMatrix() {
        if (billingRateMatrix == null) {
            billingRateMatrix = new ArrayList<BillingRateMatrix>();
        }
        return this.billingRateMatrix;
    }

    /**
     * Gets the value of the serviceLookupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getServiceLookupSK() {
        return serviceLookupSK;
    }

    /**
     * Sets the value of the serviceLookupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setServiceLookupSK(BigInteger value) {
        this.serviceLookupSK = value;
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
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceName }
     *     
     */
    public ServiceName getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceName }
     *     
     */
    public void setServiceName(ServiceName value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the serviceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * Sets the value of the serviceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescription(String value) {
        this.serviceDescription = value;
    }

}
